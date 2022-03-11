package dungeonmodel;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * It represents a two dimensional grid. It constructs the grid and return 
 * the start and end point. It also distributes treasures in all cell 
 * location. It contains method to attack the monster. 
 *
 */
class GridTwoD implements IGridTwoD {
  private IHabitat[][] twoDimHabitat;
  private boolean isRandom = false;
  private int startPoint = 0;
  private int endPoint = 0;
  private int noOfRows;
  private int noOfColumns;
  private int seed;

  /**
   * Constructs a two dimensional grid of caves and tunnels and distributes
   * weapons and treasures. Also puts the monsters in the cells.
   * 
   * @param noOfRows represents the no of rows.
   * @param noOfColumns represents the no of columns.
   * @param interConnectivity represents the interconnectivity.
   * @param isWrapping represents if it is wrapping.
   * @param percentageOfTreasure represents the percentage of treasure.
   * @param numberOfOtyughs reperesents the number of otyughs.
   * @param isRandom represent if the game is random.
   */
  public GridTwoD(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyughs, boolean isRandom,
      int seed) {

    this.noOfColumns = noOfColumns;
    this.noOfRows = noOfRows;
    this.isRandom = isRandom;
    this.seed = seed;
    List<ITreasure> finalTreasureList = getTreasureList(noOfRows, noOfColumns,
        percentageOfTreasure); 

    if (isRandom) {
      Collections.shuffle(finalTreasureList, new Random(seed));
    }

    List<Integer> numbers = Stream.iterate(0, n -> n + 1)
        .limit(noOfRows * noOfColumns)
        .collect(Collectors.toList());

    if (isRandom) {
      Collections.shuffle(numbers, new Random(seed));
    }
    int countOfHabitats = (int) (Math.ceil((percentageOfTreasure 
        * noOfRows * noOfColumns) / 100.0));

    int countOfWeapons = (int) (Math.ceil((percentageOfTreasure 
        * noOfRows * noOfColumns) / 100.0));

    //Get all paths
    List<Pairs> pairsList = getPairsList(noOfRows, noOfColumns, isWrapping); 

    //Get all valid paths
    Map<Integer, List<Integer>> retMap = validPaths(pairsList,
        interConnectivity, noOfRows * noOfColumns);

    Map<Integer, List<ITreasure>> treasureMap = new HashMap<>();
    int k = 0;
    int i = 0;
    while (countOfHabitats > 0 && i < noOfRows * noOfColumns) {
      if (retMap.get(numbers.get(i)).size() != 2) {
        countOfHabitats--;
        treasureMap.put(numbers.get(i), finalTreasureList.subList(k, k + 3));
        k += 3;
      }
      i++;
    }

    if (isRandom) {
      Collections.shuffle(numbers, new Random(seed));
    }

    Map<Integer, IWeapon> weaponMap = new HashMap<>();
    Map<Integer, IMonster> monsterMap = new HashMap<>();

    for (int p = 0; p < noOfRows * noOfColumns; p++) {
      if (countOfWeapons == 0) {
        break;
      }
      weaponMap.put(numbers.get(p), new Arrow("Arrow"));
      countOfWeapons--;
    }

    if (isRandom) {
      Collections.shuffle(numbers, new Random(seed));
    }
    for (int p = 0; p < noOfRows * noOfColumns; p++) {
      if (numberOfOtyughs == 1 ) {
        break;
      }
      if (retMap.get(numbers.get(p)).size() != 2) {
        monsterMap.put(numbers.get(p), new Otyugh("Otyugh", 2));
        numberOfOtyughs--;
      }
    }

    if (countOfHabitats != 0) {
      throw new IllegalArgumentException("Treasures are not distributed to all location.");
    }

    twoDimHabitat = new Habitat[noOfRows][noOfColumns];
    for (Integer currS : retMap.keySet()) {
      List<Integer> currList = retMap.get(currS);

      int startR = currS / noOfColumns;
      int startC = currS % noOfColumns;
      List<IWeapon> weaponList = new ArrayList<>();
      if (weaponMap.get(currS) != null) {
        weaponList.add(weaponMap.get(currS));
      }
      IHabitat habitat = new Habitat(currList, treasureMap.get(currS) == null 
          ? new ArrayList<>() : treasureMap.get(currS), weaponList,
              monsterMap.get(currS), null, false, null);
      twoDimHabitat[startR][startC] = habitat;
    }

    int maxDistance = 0;
    for (int s = 0 ; s < noOfRows * noOfColumns; s++) {
      for (int e = s + 1; e < noOfRows * noOfColumns; e++) {
        int distance = leastDistance(twoDimHabitat, s , e);
        maxDistance = maxDistance < distance ? distance : maxDistance; 
      }
    }
    if (maxDistance < 5) {
      throw new IllegalArgumentException("With given number of column "
          + "and number of rows, least distance is correct.");
    }

    int leastDistance = 0;
    Random random = new Random(seed);
    while (leastDistance < 5 || retMap.get(startPoint).size() == 2
        || retMap.get(endPoint).size() == 2) {
      if (isRandom) {
        startPoint = (int)Math.floor(random.nextDouble() * (noOfRows * noOfColumns));
        endPoint = (int)Math.floor(random.nextDouble() * (noOfRows * noOfColumns));

        //startPoint = new GenerateRandom().nextRandom(0, noOfRows * noOfColumns - 1, seed);
        //endPoint = new GenerateRandom().nextRandom(0, noOfRows * noOfColumns - 1, seed);
      } else {
        startPoint = 1;
        endPoint = noOfRows * noOfColumns - (noOfColumns / 2);
      }

      leastDistance = leastDistance(twoDimHabitat, startPoint, endPoint);
    }
    int endR = endPoint / noOfColumns;
    int endC = endPoint % noOfColumns;
    int startR = startPoint / noOfColumns;
    int startC = startPoint % noOfColumns;
    IHabitat habitat = new Habitat(twoDimHabitat[endR][endC].getAvblDir(), 
        twoDimHabitat[endR][endC].getAvblTreasure(), twoDimHabitat[endR][endC].getWeapon(),
        new Otyugh("Otyugh", 2), null, false, null);
    IHabitat startLocationhabitat = new Habitat(twoDimHabitat[startR][startC].getAvblDir(), 
        twoDimHabitat[startR][startC].getAvblTreasure(), twoDimHabitat[startR][startC].getWeapon(),
        null, null, false, null);
    twoDimHabitat[endR][endC] = habitat;
    twoDimHabitat[startR][startC] = startLocationhabitat;

    int theifLocation = 0;
    while (theifLocation == startPoint || theifLocation == endPoint) {
      theifLocation = (int)Math.floor(random.nextDouble() * (noOfRows * noOfColumns));
    }
    int theifLocationR = theifLocation / noOfColumns;
    int theifLocationC = theifLocation % noOfColumns;
    IHabitat theifLocationRhabitat = new Habitat(twoDimHabitat[theifLocationR][theifLocationC].getAvblDir(), 
        twoDimHabitat[theifLocationR][theifLocationC].getAvblTreasure(), twoDimHabitat[theifLocationR][theifLocationC].getWeapon(),
        twoDimHabitat[theifLocationR][theifLocationC].getMonster(),null, false, Thief.T);
    twoDimHabitat[theifLocationR][theifLocationC] = theifLocationRhabitat;

    int rMonsterLoc = 0;
    while (rMonsterLoc == startPoint || rMonsterLoc == endPoint 
        || rMonsterLoc == theifLocation) {
      rMonsterLoc = (int)Math.floor(random.nextDouble() * (noOfRows * noOfColumns));
    }
    int rMonsterLocR = rMonsterLoc / noOfColumns;
    int rMonsterLocC = rMonsterLoc % noOfColumns;
    IHabitat rMonsterLochabitat = new Habitat(twoDimHabitat[rMonsterLocR][rMonsterLocC].getAvblDir(), 
        twoDimHabitat[rMonsterLocR][rMonsterLocC].getAvblTreasure(), twoDimHabitat[rMonsterLocR][rMonsterLocC].getWeapon(),
        twoDimHabitat[rMonsterLocR][rMonsterLocC].getMonster(),new Otyugh("Otyugh", 4), false, null);
    twoDimHabitat[rMonsterLocR][rMonsterLocC] = rMonsterLochabitat;

    int pitLocation = 0;
    while (pitLocation == startPoint || pitLocation == endPoint
        || pitLocation == rMonsterLoc || pitLocation == theifLocation) {
      pitLocation = (int)Math.floor(random.nextDouble() * (noOfRows * noOfColumns));
    }
    int pitLocationR = pitLocation / noOfColumns;
    int pitLocationC = pitLocation % noOfColumns;
    IHabitat pitLocationRhabitat = new Habitat(twoDimHabitat[pitLocationR][pitLocationC].getAvblDir(), 
        twoDimHabitat[pitLocationR][pitLocationC].getAvblTreasure(), twoDimHabitat[pitLocationR][pitLocationC].getWeapon(),
        twoDimHabitat[pitLocationR][pitLocationC].getMonster(), null, true, null);
    twoDimHabitat[pitLocationR][pitLocationC] = pitLocationRhabitat;

    /*
     * while () { add theif, pit, extra otyugh }
     */
    updatedVisited(startR, startC, true);
  }

  private static List<Pairs> getPairsList(int noOfRows, int noOfColumns, boolean isWrappping) {
    List<Pairs> pairList = new ArrayList<>();
    List<Integer> pathCalculated = new ArrayList<>();
    for (int i = 0 ; i < noOfRows; i++) {
      for (int j = 0; j < noOfColumns; j++) {
        int curr = noOfColumns * i + j;

        int right = noOfColumns * i + j + 1;
        if (j + 1 != noOfColumns && !pathCalculated.contains(right)) {
          pairList.add(new Pairs(curr, right));
        } else if (j + 1 == noOfColumns && isWrappping) {
          right = noOfColumns * i;
          if (!pathCalculated.contains(right)) {
            pairList.add(new Pairs(curr, right));
          }
        }

        int left = noOfColumns  * i + j - 1;
        if (j - 1 != -1 && !pathCalculated.contains(left)) {
          pairList.add(new Pairs(curr, left));
        } else if (j - 1 == -1 && isWrappping) {
          left = noOfColumns * i + (noOfColumns - 1);
          if (!pathCalculated.contains(left)) {
            pairList.add(new Pairs(curr, left));
          }
        }

        int up = noOfColumns  * (i - 1) + j;
        if (i - 1 != -1 && !pathCalculated.contains(up)) {
          pairList.add(new Pairs(curr, up));
        } else if (i - 1 == -1 && isWrappping) {
          up = noOfColumns * (noOfRows - 1) + j;
          if (!pathCalculated.contains(up)) {
            pairList.add(new Pairs(curr, up));
          }
        }

        int down = noOfColumns  * (i + 1) + j;
        if (i + 1 != noOfRows && !pathCalculated.contains(down)) {
          pairList.add(new Pairs(curr, down));
        } else if (i + 1 == noOfRows && isWrappping) {
          down = j;
          if (!pathCalculated.contains(down)) {
            pairList.add(new Pairs(curr, down));
          }
        }
        pathCalculated.add(curr);
      }
    }
    return pairList;
  }

  private Map<Integer, List<Integer>> validPaths(List<Pairs> pairList,
      int interconnectivity, int noOfLocs) {
    Map<Integer, List<Integer>> retMap = new HashMap<>();
    UnionFind uf = new UnionFind(noOfLocs);
    List<Pairs> leftOver = new ArrayList<>();
    if (isRandom) {
      Collections.shuffle(pairList, new Random(seed));
    }
    for (int i = 0 ; i < pairList.size(); i++) {
      int start = pairList.get(i).getKey();
      int end = pairList.get(i).getValue();
      if (uf.union(start, end)) {
        addMap(start, end, retMap);
      } else {
        leftOver.add(pairList.get(i));
      }
    }

    for (int i = 0 ; i < leftOver.size(); i++) {
      if (interconnectivity > 0) {
        int start = leftOver.get(i).getKey();
        int end = leftOver.get(i).getValue();
        addMap(start, end, retMap);
        interconnectivity--;
      } else {
        break;
      }
    }
    return retMap;
  }

  private static void addMap(int start, int end, Map<Integer, List<Integer>> retMap) {
    List<Integer> currList = retMap.getOrDefault(start, new ArrayList<Integer>());
    currList.add(end);
    retMap.put(start, currList);

    List<Integer> currList1 = retMap.getOrDefault(end, new ArrayList<Integer>());
    currList1.add(start);
    retMap.put(end, currList1);
  }

  /**
   * It returns the distance between start and end location.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private int leastDistance(IHabitat[][] twoDimHabitat, 
      int start, int end) {
    int  length = 0;
    boolean found = false;

    Set<Integer> visitedNodes = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    d.add(start);
    visitedNodes.add(start);
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty() && !found) {
      currCount = 0;
      length++;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDimHabitat[row][col].getAvblDir();
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          int nextNode = allAvblDir.get(elemI);
          if (nextNode == end) {
            found = true;
          }
          if (!visitedNodes.contains(nextNode)) {
            d.add(nextNode);
            visitedNodes.add(nextNode);
            currCount++;
          }
        }
      }
      count = currCount;
    }
    return length;
  }

  @Override
  public int getStartPoint() {
    return startPoint;
  }

  @Override
  public int getEndPoint() {
    return endPoint;
  }

  @Override
  public List<ITreasure> getAvblTreasure(int currR, int currC) {
    return twoDimHabitat[currR][currC].getAvblTreasure();
  }

  @Override
  public List<IWeapon> getWeapon(int currR, int currC) {
    return twoDimHabitat[currR][currC].getWeapon();
  }

  @Override
  public IMonster getMonster(int currR, int currC) {
    return twoDimHabitat[currR][currC].getMonster();
  }

  @Override
  public IMonster getRMonster(int currR, int currC) {
    return twoDimHabitat[currR][currC].getRMonster();
  }

  @Override
  public Thief getThief(int currR, int currC) {
    return twoDimHabitat[currR][currC].theif();
  }

  @Override
  public boolean isAPit(int currR, int currC) {
    return twoDimHabitat[currR][currC].isPit();
  }

  @Override
  public String removeTreasureOrArmor(String pickUpItem, int currR, int currC) {
    String name = "";
    if (null != pickUpItem && !"".equals(pickUpItem)) {
      name = twoDimHabitat[currR][currC].removeTreasureOrArmor(pickUpItem);
    }
    return name;
  }

  @Override
  public List<Integer> getAvblDir(int currR, int currC) {
    IHabitat habitat = twoDimHabitat[currR][currC];
    return habitat.getAvblDir();
  }

  private List<ITreasure> getTreasureList(int noOfRows, int noOfColumns,
      int percentageOfTreasure) {
    int countOfHabitats = (int) (Math.ceil((percentageOfTreasure 
        * noOfRows * noOfColumns) / 100.0));

    TreasureCreator diamondCreator = new DiamondCreator();
    List<ITreasure> diamondList = diamondCreator.createTreasure(countOfHabitats);

    TreasureCreator sapphireCreator = new SapphireCreator();
    List<ITreasure> sapphireList = sapphireCreator.createTreasure(countOfHabitats);

    TreasureCreator rubiesCreator = new RubiesCreator();
    List<ITreasure> rubiesList = rubiesCreator.createTreasure(countOfHabitats);

    List<ITreasure> finalTreasureList = new ArrayList<>();
    finalTreasureList.addAll(diamondList);
    finalTreasureList.addAll(sapphireList);
    finalTreasureList.addAll(rubiesList);
    return finalTreasureList;
  }

  @Override
  public String dungeonDescription() {
    String desc = "";
    for (int i = 0 ; i < noOfRows; i++) {

      for (int j = 0 ; j < noOfColumns; j++) {
        int curr = noOfColumns * i + j;
        String treasures = "";
        List<ITreasure> treasureList = this.twoDimHabitat[i][j].getAvblTreasure();
        for (int k = 0 ; k < treasureList.size(); k++) {
          treasures = treasures + treasureList.get(k).getTreasureType() + ", ";
        }

        List<Integer> avblDir = this.twoDimHabitat[i][j].getAvblDir();
        String direction = "";
        for (int k = 0; k < avblDir.size(); k++) {
          direction = direction + getDirection(curr, 
              avblDir.get(k), twoDimHabitat.length, twoDimHabitat[0].length) + ", ";
        }

        List<IWeapon> avblWeapons = this.twoDimHabitat[i][j].getWeapon();
        String weapon = "";
        if (null != avblWeapons) {
          for (int k = 0; k < avblWeapons.size(); k++) {
            weapon = weapon + avblWeapons.get(k).getName() + ", ";
          }
        }

        IMonster monster = this.twoDimHabitat[i][j].getMonster();
        String monsterDesc = "";
        if (null != monster) {
          monsterDesc = monsterDesc + monster.getName() + ", ";
        }

        desc = desc + "{" + curr + "(" + direction + ")" + " & " + "(" + treasures + ") "
            + " & " + "(" + weapon + ")" + " & " + "(" + monsterDesc + ")" + "} ";
      }
      desc += "\n";
    }
    return desc;
  }

  @Override
  public String getlocationDetails(int curr, int rowC, int colC) {
    String desc = "";
    String treasures = "";
    List<ITreasure> treasureList = this.twoDimHabitat[rowC][colC].getAvblTreasure();
    for (int k = 0 ; k < treasureList.size(); k++) {
      treasures = treasures + treasureList.get(k).getTreasureType() + ", ";
    }

    List<Integer> avblDir = this.twoDimHabitat[rowC][colC].getAvblDir();
    String direction = "";
    for (int k = 0; k < avblDir.size(); k++) {
      direction = direction + getDirection(curr, 
          avblDir.get(k), twoDimHabitat.length, noOfColumns) + ", ";
    }

    List<IWeapon> avblWeapons = this.twoDimHabitat[rowC][colC].getWeapon();
    String weapon = "";
    if (null != avblWeapons) {
      for (int k = 0; k < avblWeapons.size(); k++) {
        weapon = weapon + avblWeapons.get(k).getName() + ", ";
      }
    }

    String monsterDesc = "";
    Map<Integer, Integer> monsterMap = new HashMap<>();
    Set<Integer> doneSet = new HashSet<>();
    doneSet.add(curr);
    getMonsterMap(curr, rowC, colC, 
        twoDimHabitat.length, noOfColumns, 1, monsterMap,
        doneSet);
    for (Integer key : monsterMap.keySet()) {
      monsterDesc += key + ";" + monsterMap.get(key) + ",";
    }

    //does adjacent contain pit
    boolean isAdjPit = isAdjacentPit(rowC, colC, noOfRows, noOfColumns); 

    boolean isVisited = twoDimHabitat[rowC][colC].isVisited();

    IMonster rMonster = this.twoDimHabitat[rowC][colC].getRMonster();
    Thief thief = this.twoDimHabitat[rowC][colC].theif();
    boolean isAPit = this.twoDimHabitat[rowC][colC].isPit();
    IMonster monster = this.twoDimHabitat[rowC][colC].getMonster();

    desc = desc + "{"  + "(" + direction + ")" + " & " + "(" + treasures + ") "
        + " & " + "(" + weapon + ")" + " & " + "(" + monsterDesc + ")" + "} ";

    return direction + " : " + treasures + " : " + weapon + " : " + monsterDesc
        + " : " + (isVisited ? "Y" : "N") + " : " + (isAdjPit ? "Y" : "N")
        + " : " + (null != rMonster ? "Y" : "N") 
        + " : " + (null != thief ? "Y" : "N")
        + " : " + (isAPit ? "Y" : "N")
        + " : " + (null != monster ? "Y" : "N");
  }

  private void getMonsterMap(int curr, int currR, int currC, int noOfRows, 
      int noOfColumns, int count, Map<Integer, Integer> monsterMap,
      Set<Integer> done) {
    boolean south = false;
    boolean west = false;
    boolean north = false;
    boolean east = false;
    if (count == 3) {
      return;
    }
    IHabitat habitat = twoDimHabitat[currR][currC];
    List<Integer> avblDir = habitat.getAvblDir(); 

    int nextR1 = currR;
    int nextC1 = currC - 1;
    if (nextC1 == -1) {
      nextC1 = noOfColumns - 1;
    }
    int location1 = noOfColumns * nextR1 + nextC1;
    if (avblDir.contains(location1) && !done.contains(location1)) {
      if (null != this.twoDimHabitat[nextR1][nextC1].getMonster()
          && this.twoDimHabitat[nextR1][nextC1].getMonster().getStrength() > 0) {
        monsterMap.put(count , monsterMap.getOrDefault(count, 0) + 1);
      }
      west = true;
      done.add(location1);
    }

    int nextR2 = currR ;
    int nextC2 = currC + 1;
    if (nextC2 == noOfColumns) {
      nextC2 = 0;
    }
    int location2 = noOfColumns * nextR2 + nextC2;
    if (avblDir.contains(location2) && !done.contains(location2)) {
      if (null != this.twoDimHabitat[nextR2][nextC2].getMonster() 
          && this.twoDimHabitat[nextR2][nextC2].getMonster().getStrength() > 0) {
        monsterMap.put(count , monsterMap.getOrDefault(count, 0) + 1);
      }
      east = true;
      done.add(location2);
    }

    int nextR3 = currR + 1;
    int nextC3 = currC;
    if (nextR3 == noOfRows) {
      nextR3 = 0;
    }
    int location3 = noOfColumns * nextR3 + nextC3;
    if (avblDir.contains(location3) && !done.contains(location3)) {
      if (null != this.twoDimHabitat[nextR3][nextC3].getMonster() 
          && this.twoDimHabitat[nextR3][nextC3].getMonster().getStrength() > 0) {
        monsterMap.put(count , monsterMap.getOrDefault(count, 0) + 1);
      }
      south = true;
      done.add(location3);
    }

    int nextR4 = currR - 1;
    int nextC4 = currC;
    if (nextR4 == -1) {
      nextR4 = noOfRows - 1;
    }
    int location4 = noOfColumns * nextR4 + nextC4;
    if (avblDir.contains(location4) && !done.contains(location4)) {
      if (null != this.twoDimHabitat[nextR4][nextC4].getMonster() 
          && this.twoDimHabitat[nextR4][nextC4].getMonster().getStrength() > 0) {
        monsterMap.put(count , monsterMap.getOrDefault(count, 0) + 1);
      }
      north = true;
      done.add(location4);
    }
    if (west) {
      getMonsterMap(location1, nextR1, nextC1, noOfRows, 
          noOfColumns, count + 1, monsterMap, done);
    }
    if (east) {
      getMonsterMap(location2, nextR2, nextC2, noOfRows, 
          noOfColumns, count + 1, monsterMap, done);
    }
    if (south) {
      getMonsterMap(location3, nextR3, nextC3, noOfRows, 
          noOfColumns, count + 1, monsterMap, done);
    }
    if (north) {
      getMonsterMap(location4, nextR4, nextC4, noOfRows, 
          noOfColumns, count + 1, monsterMap, done);
    }
    return;
  }

  @Override
  public boolean isAdjacentPit(int currR, int currC, int noOfRows, 
      int noOfColumns) {
    boolean isAdjPit = false;
    IHabitat habitat = twoDimHabitat[currR][currC];
    List<Integer> avblDir = habitat.getAvblDir(); 

    int nextR1 = currR;
    int nextC1 = currC - 1;
    if (nextC1 == -1) {
      nextC1 = noOfColumns - 1;
    }
    int location1 = noOfColumns * nextR1 + nextC1;
    if (avblDir.contains(location1) && this.twoDimHabitat[nextR1][nextC1].isPit()) {
      isAdjPit = true;
    }

    int nextR2 = currR ;
    int nextC2 = currC + 1;
    if (nextC2 == noOfColumns) {
      nextC2 = 0;
    }
    int location2 = noOfColumns * nextR2 + nextC2;
    if (avblDir.contains(location2) && this.twoDimHabitat[nextR2][nextC2].isPit()) {
      isAdjPit = true;
    }

    int nextR3 = currR + 1;
    int nextC3 = currC;
    if (nextR3 == noOfRows) {
      nextR3 = 0;
    }
    int location3 = noOfColumns * nextR3 + nextC3;
    if (avblDir.contains(location3) && this.twoDimHabitat[nextR3][nextC3].isPit()) {
      isAdjPit = true;
    }

    int nextR4 = currR - 1;
    int nextC4 = currC;
    if (nextR4 == -1) {
      nextR4 = noOfRows - 1;
    }
    int location4 = noOfColumns * nextR4 + nextC4;
    if (avblDir.contains(location4) && this.twoDimHabitat[nextR4][nextC4].isPit()) {
      isAdjPit = true;
    }
    return isAdjPit;
  }

  /**
   * It returns the direction from start to end location.
   * 
   * @param start represents start location.
   * @param end represents end location.
   * @param noOfRows represents number of rows.
   * @param noOfColumns represents number of columns.
   * @return
   */
  private String getDirection(int start, int end, int noOfRows,
      int noOfColumns) {
    int startRow = start / noOfColumns; 
    int startColumn = start % noOfColumns; 
    int endRow = end / noOfColumns;
    int endColumn = end % noOfColumns;

    if (startRow == endRow) {
      if (endColumn == startColumn + 1 || (startColumn == (noOfColumns - 1) 
          && endColumn == 0)) {
        return "E";
      } else {
        return "W";
      }
    } else {
      if (endRow == startRow + 1 || (startRow == (noOfRows - 1) 
          && endRow == 0)) {
        return "S";
      } else {
        return "N";
      }
    }
  }

  @Override
  public int attack(int cloc, String dir, int distance) {
    int noOfRows = twoDimHabitat.length;
    if (nextLocation(cloc, dir) == noOfRows * noOfColumns) {
      throw new IllegalArgumentException("Arrow cannot be throw in given direction");
    }
    while (distance > 0) {
      cloc = nextLocation(cloc, dir);
      if (cloc != noOfRows * noOfColumns) {

        if (twoDimHabitat[cloc / noOfColumns][cloc % noOfColumns]
            .getAvblDir().size() != 2) {
          distance--;
        }  else {
          List<Integer> dirs = twoDimHabitat[cloc / noOfColumns][cloc % noOfColumns]
              .getAvblDir();
          boolean isSameExist = false;
          String findOpp = findOppDir(dir);
          String alternate = "";
          for (int k = 0 ; k < dirs.size(); k++) {
            String nDir = getDirection(cloc, dirs.get(k), noOfRows, noOfColumns); 
            if (dir.equalsIgnoreCase(nDir)) {
              isSameExist = true;
              dir = nDir;
              break;
            }
            if (!nDir.equalsIgnoreCase(findOpp)) {
              alternate = nDir;
            }
          }
          if (!isSameExist) {
            dir = alternate;
          }
        }
      } else {
        return -1;
      }
    }
    int currStrength = 0;
    if (distance == 0 && null != twoDimHabitat[cloc / noOfColumns][cloc % noOfColumns]
        .getMonster()) {
      currStrength = twoDimHabitat[cloc / noOfColumns][cloc % noOfColumns].getMonster()
          .getStrength();
      if (currStrength > 0) {
        twoDimHabitat[cloc / noOfColumns][cloc % noOfColumns].getMonster().updateStrength();
      }
    }
    return currStrength;
  }

  private String findOppDir(String dir) {
    String retDir = "";
    if (dir.equalsIgnoreCase("N")) {
      retDir = "S";
    } else if (dir.equalsIgnoreCase("S")) {
      retDir = "N";
    } else if (dir.equalsIgnoreCase("W")) {
      retDir = "E";
    } else if ( dir.equalsIgnoreCase("E")) {
      retDir = "W";
    }
    return retDir;
  }

  private int nextLocation(int cloc, String dir) {
    int currR = cloc / noOfColumns;
    int currC = cloc % noOfColumns;
    int nextR = noOfRows;
    int nextC = noOfColumns;
    if (dir.equalsIgnoreCase("N")) {
      nextR = currR - 1;
      nextC = currC;
      if (nextR == -1) {
        nextR = twoDimHabitat.length - 1;
      }
    } else if (dir.equalsIgnoreCase("S")) {
      nextR = currR + 1;
      nextC = currC;
      if (nextR == twoDimHabitat.length) {
        nextR = 0;
      }
    } else if (dir.equalsIgnoreCase("W")) {
      nextR = currR;
      nextC = currC - 1;
      if (nextC == -1) {
        nextC = twoDimHabitat[0].length - 1;
      }
    } else if (dir.equalsIgnoreCase("E")) {
      nextR = currR ;
      nextC = currC + 1;
      if (nextC == twoDimHabitat[0].length) {
        nextC = 0;
      }
    }
    IHabitat habitat = twoDimHabitat[currR][currC];
    List<Integer> avblDir = habitat.getAvblDir(); 
    int location = nextR * twoDimHabitat[0].length + nextC;
    if (avblDir.contains(location)) {
      return location;
    }
    return noOfRows * noOfColumns;
  }

  @Override
  public String[][] dungeonRepresentation() {
    String[][] retDungeon = new String[twoDimHabitat.length][twoDimHabitat[0].length];
    for (int i = 0 ; i < noOfRows; i++) {
      for (int j = 0 ; j < noOfColumns; j++) {
        retDungeon[i][j] = getlocationDetails(i * noOfColumns + j, i, j);
      }
    }
    return retDungeon;
  }

  @Override
  public void updatedVisited(int currR, int currC, boolean visited) {
    twoDimHabitat[currR][currC].setVisited(visited);
  }

  @Override
  public void removeRMonster(int nCurrR, int nCurrC) {
    twoDimHabitat[nCurrR][nCurrC].removeRMonster();

  }
}

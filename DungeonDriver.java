import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import dungeonmodel.DungeonImpl;
import dungeonmodel.IDungeon;


/**
 * Driver class to test the behavior of dungeon project.
 * This class will create dungeon model.
 * It will test functionalities of the dungeon including
 * creating dungeon. Start the game by putting player in the start position.
 * Provide treasure to the caves. 
 * It also prints all the moves of the players.
 *
 */
public class DungeonDriver {

  /**
   * Main method get input via the command line including no of rows,
   * no of columns, interconnectivity, isWrapping and percentage of 
   * treasure. It create model object and call its method to test 
   * its functionalities.
   * 
   */
  public static void test(String[] args) {

    if (args == null || args.length != 6) {
      System.out.println("The command line input is not correct.\nA valid"
          + " input will be have values including noOfRows, noOfColumsn,"
          + "interconnectivity, isWrapping, percentageOfTreasure, numberOfOtyugh.\n.An examples is"
          + "10 10 0 0 20 1\n");
      return;
    }
    String noOfRowsArg = args[0];
    String noOfColumnsArg = args[1];
    String interConnectivityArg = args[2];
    String isWrappingArg = args[3];

    String percentageOfTreasureArg = args[4];
    String numberOfOtyughArg = args[5];
    int noOfRows = 0;
    int noOfColumns = 0;
    int interConnectivity = 0;
    boolean isWrapping = false;
    int percentageOfTreasure = 0;
    int numberOfOtyughs = 0;

    try {
      noOfRows = Integer.valueOf(noOfRowsArg);
      noOfColumns = Integer.valueOf(noOfColumnsArg);
      interConnectivity = Integer.valueOf(interConnectivityArg);
      isWrapping = Integer.valueOf(isWrappingArg).equals(1);
      percentageOfTreasure = Integer.valueOf(percentageOfTreasureArg);
      numberOfOtyughs = Integer.valueOf(numberOfOtyughArg);
    } catch (NumberFormatException e) {
      System.out.println("The input should be a number");
    }

    IDungeon dungeon = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, numberOfOtyughs, true, true);
    //It is two dimensional representation of the maze.
    List<Integer>[][] twoDRepresentationOfDirections = dungeon.dungeonRepresentation();
    //Picking up a treasure
    /* System.out.println("\nRun to show pick ability to pick up treasure if available");
    System.out.println("Players current location : " + dungeon.getPlayersLocation());
    System.out.println("Players treasure : " + dungeon.getPlayersTreasures());
    String locationDetails = dungeon.getlocationDetails();
    String [] locDetails = locationDetails.split(":");

    System.out.println("All locations treasures are : " + locDetails[1]);
    dungeon.pickUpTreasure();
    System.out.println("After pickup players treasure : " + dungeon.getPlayersTreasures());
    String locationDetails1 = dungeon.getlocationDetails();
    String [] locDetails1 = locationDetails1.split(":");

    System.out.println("After pickup, all locations treasures are : " + locDetails1[1]);

    dungeon.move("D");
    System.out.println("Run to show pick ability to not pick up treasure if not available");
    System.out.println("Players current location : " + dungeon.getPlayersLocation());
    System.out.println("Players treasure : " + dungeon.getPlayersTreasures());
    String locationDetails2 = dungeon.getlocationDetails();
    String [] locDetails2 = locationDetails2.split(":");

    System.out.println("All locations treasures are : " + locDetails2[1]);
    dungeon.pickUpTreasure();
    System.out.println("After pickup players treasure : " + dungeon.getPlayersTreasures());
    String locationDetails3 = dungeon.getlocationDetails();
    String [] locDetails3 = locationDetails3.split(":");

    System.out.println("After pickup all locations treasures are : " + locDetails3[1]);*/

    //Getting a valid path from start to end.
    List<String> validDirections = dirStartEnd(twoDRepresentationOfDirections, 
        dungeon.getPlayersLocation(), dungeon.getEndPoint());

    //System.out.println("One run that shows a non-wrapping dungeon");
    System.out.println(dungeon.dungeonDescription());
    System.out.println("\nOne run that shows the player starting at"
        + " the start and reaching the end");

    System.out.println("\nStart Location : " 
        + dungeon.getStartPoint() + ", End Location : " + dungeon.getEndPoint());
    for (int i = 0 ; i < validDirections.size(); i++) {

      int currLoc = dungeon.getPlayersLocation();
      System.out.println("Start location : " + currLoc);

      String playerTreasure = "All player treasures are : ";
      String playerTreasureList = dungeon.getPlayersTreasures();
      System.out.println(playerTreasure + playerTreasureList);

      String locationDetails = dungeon.getlocationDetails();
      String [] locDetails = locationDetails.split(":");

      String directions = "All available directions are : ";
      System.out.println(directions + locDetails[0]);

      String treasures = "All locations treasures are : ";
      System.out.println(treasures + locDetails[1]);

      System.out.println("Direction Taken : " + validDirections.get(i));
      //dungeon.pickUpTreasure();
      dungeon.move(validDirections.get(i));
      currLoc = dungeon.getPlayersLocation();
      System.out.println("Location reached : " + currLoc + "\n");
    }
    System.out.println("End reached\n");

    //One run for move functionality.
    /* IDungeon dungeon = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
        isWrapping, percentageOfTreasure, false);
    System.out.println("One run that shows a non-wrapping dungeon");
    System.out.println("Players current location : " + dungeon.getPlayersLocation());
    try {
      dungeon.move("U");
    } catch(IllegalArgumentException e) {
      System.out.println("Move method returns exception as moves in "
          + "upward direction is not allowed.");
    }*/

    /* IDungeon dungeon = new DungeonImpl(noOfRows, noOfColumns, interConnectivity,
    //    isWrapping, percentageOfTreasure, false);
    System.out.println("One run that shows a wrapping/non-wrapping dungeon");
    System.out.println("Players current location : " + dungeon.getPlayersLocation());
    try {
      dungeon.move("U");
    } catch(IllegalArgumentException e) {
      System.out.println("Move method returns exception as moves in "
          + "upward direction is not allowed.");
    }
    System.out.println("Players current location : " + dungeon.getPlayersLocation());
     */

    System.out.println("One run that shows the player visiting every location in the dungeon");
    List<String> validAllDirections = dirAllLoc(twoDRepresentationOfDirections,
        dungeon.getPlayersLocation(), dungeon.getEndPoint());

    System.out.println("\nStart Location : " + dungeon.getPlayersLocation() + "End "
        + "Location : " + ((noOfRows * noOfColumns) - 1));
    for (int i = 0 ; i < validAllDirections.size(); i++) {

      int currLoc = dungeon.getPlayersLocation();
      System.out.println("Start location : " + currLoc);

      String playerTreasure = "All player treasures are : ";
      String playerTreasureList = dungeon.getPlayersTreasures();
      System.out.println(playerTreasure + playerTreasureList);

      String locationDetails = dungeon.getlocationDetails();
      String [] locDetails = locationDetails.split(":");

      String directions = "All available directions are : ";
      System.out.println(directions + locDetails[0]);

      String treasures = "All locations treasures are : ";
      System.out.println(treasures + locDetails[1]);

      System.out.println("Direction Taken : " + validAllDirections.get(i));
      //dungeon.pickUpTreasure();
      dungeon.move(validAllDirections.get(i));
      currLoc = dungeon.getPlayersLocation();
      System.out.println("Location reached : " + currLoc + "\n");
    }
    System.out.println("All nodes explored\n");

  }

  /**
   * It returns the all the paths between start and end location.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private static List<String> dirAllLoc(List<Integer>[][] twoDimHabitat,
      int start, int end) {

    List<String> retList = new ArrayList<>();
    Map<Integer,  Deque<Integer>> map = paths(twoDimHabitat, start, end);
    Deque<Integer> d = new ArrayDeque<>();
    d.addFirst(start); 
    boolean found = false;
    while (!d.isEmpty() && !found) {
      Integer currS = d.getFirst();
      Deque<Integer> currDeq = map.get(currS);
      if (null != currDeq && currDeq.size() > 0) {
        Integer elem = currDeq.removeFirst();
        d.addFirst(elem);
        retList.add(getDirection(currS, elem, twoDimHabitat.length, twoDimHabitat[0].length));
      } else {
        d.removeFirst();
        if (!d.isEmpty()) {
          retList.add(getDirection(currS, d.getFirst(), 
              twoDimHabitat.length, twoDimHabitat[0].length));
        }
      }
    }
    return retList;
  }

  /**
   * It returns the all the paths between start and end location.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private static List<String> dirStartEnd(List<Integer>[][] twoDimHabitat,
      int start, int end) {

    List<String> retList = new ArrayList<>();
    Map<Integer,  Deque<Integer>> map = paths(twoDimHabitat, start, end);
    //Create queue
    Deque<Integer> d = new ArrayDeque<>();
    d.addFirst(start); 
    boolean found = false;
    while (!d.isEmpty() && !found) {
      Integer currS = d.getFirst();
      Deque<Integer> currDeq = map.get(currS);
      if (null != currDeq && currDeq.size() > 0) {
        Integer elem = currDeq.removeFirst();
        if (elem == end) {
          found = true;
        }
        d.addFirst(elem);
        retList.add(getDirection(currS, elem, twoDimHabitat.length, twoDimHabitat[0].length));
      } else {
        d.removeFirst();
        if (!d.isEmpty()) {
          retList.add(getDirection(currS, d.getFirst(),
              twoDimHabitat.length, twoDimHabitat[0].length));
        }
      }
    }
    return retList;
  }

  /**
   * It returns the all the paths between from one point stored as a key
   * and the adjacent location are value.
   * 
   * @param twoDimHabitat represents two dimensional habitat.
   * @param start represents the start location.
   * @param end represents the end location
   * @return the distance between start and end location.
   */
  private static Map<Integer,  Deque<Integer>> paths(List<Integer>[][] twoDimHabitat, 
      int start, int end) {
    int noOfColumns = twoDimHabitat[0].length;

    Set<Integer> visitedNodes = new HashSet<>();
    Deque<Integer> d = new ArrayDeque<>();
    d.add(start);
    visitedNodes.add(start);
    Map<Integer,  Deque<Integer>> map = new HashMap<>();
    int count = 1; 
    int currCount = 0;
    while (!d.isEmpty()) {
      currCount = 0;
      for (int i = 0 ; i < count ; i++) {
        Integer topElement = d.remove();
        int row = topElement / noOfColumns;
        int col = topElement % noOfColumns;
        List<Integer> allAvblDir = twoDimHabitat[row][col];
        for (int elemI = 0; elemI < allAvblDir.size(); elemI++) {
          int nextNode = allAvblDir.get(elemI);
          if (!visitedNodes.contains(nextNode)) {
            Deque<Integer> deque = map.getOrDefault(topElement, new ArrayDeque<>());
            deque.addFirst(nextNode);
            map.put(topElement, deque);
            d.add(nextNode);
            visitedNodes.add(nextNode);
            currCount++;
          }
        }
      }
      count = currCount;
    }
    return map;
  }

  /**
   * It returns the direction from start to end location.
   * 
   * @param start represents start location.
   * @param end represents end location.
   * @param noOfRows represents number of rows.
   * @param noOfColumns represents number of columns.
   * 
   * @return the direction for the given adjacent locations.
   */
  private static String getDirection(int start, int end, int noOfRows,
      int noOfColumns) {
    int startRow = start / noOfColumns; 
    int startColumn = start % noOfColumns; 
    int endRow = end / noOfColumns;
    int endColumn = end % noOfColumns;

    if (startRow == endRow) {
      if (endColumn == startColumn + 1 || (startColumn == (noOfColumns - 1) 
          && endColumn == 0)) {
        return "R";
      } else {
        return "L";
      }
    } else {
      if (endRow == startRow + 1 || (startRow == (noOfRows - 1) 
          && endRow == 0)) {
        return "D";
      } else {
        return "U";
      }
    }
  }
}

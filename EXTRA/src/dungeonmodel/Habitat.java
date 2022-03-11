package dungeonmodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * It represents a cave or a tunnel in the dungeon.
 * Each cave or tunnel can have path to adjacent nodes.
 * Habitat with two path is tunnel.
 * Only caves can have treasures.
 * A single cave will have representation as {L, R, U}
 * to which denote and {DIAM1, DIAM2, DIAM3} which 
 * denote treasure at that location.
 */
class Habitat implements IHabitat {

  private List<Integer> avblDir;
  private List<ITreasure> avblTreasureList;
  private List<IWeapon> avblWeaponList;
  private IMonster monster;

  /**
   * Constructs a habitat object. It is created after kruskal
   * algorithm is run on the graph.
   * 
   * @param avblDir represents all the direction player can
   *     move from any location
   * @param avblTreasureList represents all the treasures
   *     a habitat can have.
   */
  public Habitat(List<Integer> avblDir, List<ITreasure> avblTreasureList,
      List<IWeapon> weaponList, IMonster monster) {
    this.avblDir = avblDir;
    this.avblTreasureList = avblTreasureList;
    this.avblWeaponList = weaponList;
    this.monster = monster;
  }

  @Override
  public List<Integer> getAvblDir() {
    List<Integer> retAvblList = new ArrayList<>();
    if (null != avblDir && avblDir.size() > 0) {
      avblDir.forEach(i -> retAvblList.add(i));
    }
    return retAvblList;
  }

  @Override
  public List<IWeapon> getWeapon() {
    List<IWeapon> retWeaponList = new ArrayList<>();
    if (null != avblWeaponList && avblWeaponList.size() > 0) {
      avblWeaponList.forEach(i -> {retWeaponList.add(new Arrow(i.getName()));});
    }
    return retWeaponList;
  }

  @Override
  public IMonster getMonster() {
    return this.monster;
  }

  @Override
  public List<ITreasure> getAvblTreasure() {
    //List<ITreasure> retAvbTreasurelList = new ArrayList<>();
//    if (null != avblTreasureList && avblTreasureList.size() > 0) {
//      avblTreasureList.forEach(treasure -> {
//        if (TreasureType.SAPPHIRE.equals(treasure.getTreasureType())) {
//          retAvbTreasurelList.add(new Sapphires(TreasureType.SAPPHIRE, treasure.getName()));
//        } else if (TreasureType.DIAMOND.equals(treasure.getTreasureType())) {
//          retAvbTreasurelList.add(new Diamond(TreasureType.DIAMOND, treasure.getName()));
//        } else  {
//          retAvbTreasurelList.add(new Rubies(TreasureType.RUBY, treasure.getName()));
//        }
//      });
//    }
    //    Map<String, List<String>> m = new HashMap<>();
    //    for (int i = 0 ; i < avblTreasureList.size(); i++) {
    //      List<String> l = m.getOrDefault(avblTreasureList.get(i).toString(), new ArrayList<String>());
    //      l.add(avblTreasureList.get(i).getName());
    //      m.put(avblTreasureList.get(i).getTreasureType().toString(), l);
    //    }
    //    for (String s : m.keySet() ) {
    //      List<String> listName = m.get(s);
    //      for (int i = 0 ; i < listName.size(); i++) {
    //        if (TreasureType.SAPPHIRE.toString().equals(s)) {
    //          retAvbTreasurelList.add(new Sapphires(TreasureType.SAPPHIRE, listName.get(i)));
    //        }
    //        if (TreasureType.DIAMOND.toString().equals(s)) {
    //          retAvbTreasurelList.add(new Sapphires(TreasureType.DIAMOND, listName.get(i)));
    //        }
    //        if (TreasureType.RUBY.toString().equals(s)) {
    //          retAvbTreasurelList.add(new Sapphires(TreasureType.RUBY, listName.get(i)));
    //        }
    //      }
    //    }
    List<ITreasure> retAvbTreasurelList = new CopyOnWriteArrayList<>();
    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0 ; i < avblTreasureList.size(); i++) {
          ITreasure treasure = avblTreasureList.get(i);
          ITreasure copiedTreasure;
          if (TreasureType.SAPPHIRE.equals(treasure.getTreasureType())) {
            copiedTreasure = new Sapphires(TreasureType.SAPPHIRE, treasure.getName());
          } else if (TreasureType.DIAMOND.equals(treasure.getTreasureType())) {
            copiedTreasure = new Diamond(TreasureType.DIAMOND, treasure.getName());
          } else  {
            copiedTreasure = new Rubies(TreasureType.RUBY, treasure.getName());
          }
          retAvbTreasurelList.add(copiedTreasure);
        }
      }
  });  
  t1.start();
  
   
    return avblTreasureList;
  }

  @Override
  public String removeTreasureOrArmor(String removeItem) {
    String ret = "";
    if (null != removeItem && !"".equals(removeItem)
        && ("RUBY".equalsIgnoreCase(removeItem)
            || "DIAMOND".equalsIgnoreCase(removeItem)
            || "SAPPHIRE".equalsIgnoreCase(removeItem)
            || "ARROW".equalsIgnoreCase(removeItem))) {
      if (removeItem.equalsIgnoreCase("arrow")) {
        ret = removeWeapon();
      } else {
        ret = removeTreasure(removeItem);
      }
    } else {
      throw new IllegalArgumentException("Please choose correct item");
    }
    if (ret.equals("")) {
      throw new IllegalArgumentException("Please choose correct item");
    }
    return ret;
  }

  public String removeTreasure(String removeItem) {
    String name = "";
    List<ITreasure> retAvbTreasurelList = new ArrayList<>();
    int index = avblTreasureList.size();
    for (int i = 0 ; i < avblTreasureList.size(); i++) {
      if (avblTreasureList.get(i).getTreasureType().toString().equalsIgnoreCase(removeItem)) {
        index = i;
       // retAvbTreasurelList.add(avblTreasureList.get(i));
     // } else {
       // name = avblTreasureList.get(i).getTreasureType().toString();
      //}
    }
    }
      for (int i = 0 ; i < avblTreasureList.size(); i++) {
        if (index != i) {
          retAvbTreasurelList.add(avblTreasureList.get(i));
        } else {
          name = avblTreasureList.get(i).getTreasureType().toString();
        }
         // retAvbTreasurelList.add(avblTreasureList.get(i));
       // } else {
         // name = avblTreasureList.get(i).getTreasureType().toString();
        //}
      }
    this.avblTreasureList = retAvbTreasurelList;
   /* if(index != avblTreasureList.size()) {
      name = avblTreasureList.get(index).getTreasureType().toString();
      avblTreasureList.remove(index);
    }*/
    return name;
  }

  public String removeWeapon() {
    String ret = "";
    if (null != avblWeaponList && avblWeaponList.size() > 0) {
      avblWeaponList.remove(0);
      ret = "arrow"; 
    } 
    return ret;
  }

  @Override
  public int hashCode() {
    return Objects.hash(avblDir, avblTreasureList);
  }

  @Override
  public boolean equals(Object obj) {
    boolean ret = false;
    if (this == obj) {
      return true;
    }
    if (obj instanceof Habitat) {
      Habitat other = (Habitat) obj;
      ret = Objects.equals(avblDir, other.avblDir)
          && Objects.equals(avblTreasureList, other.avblTreasureList);
    }
    return ret;
  }

  @Override
  public String toString() {
    return "Habitat [avblDir=" + avblDir + ", avblTreasureList=" + avblTreasureList + "]";
  }
}

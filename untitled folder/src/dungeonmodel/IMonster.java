package dungeonmodel;

/**
 * Represents a monster in a dungeon. Dungeon contains monsters at the 
 * caves. If a monster capture human, human dies. Human can attack and
 * kill monster by attacking two times. 
 */
interface IMonster {

  /**
   * Returns the name of the monster.
   * @return it returns the name of the monster.
   */
  public String getName();

  /**
   * It is used to get the update strength of the monster.
   */
  void updateStrength();

  /**
   * It is used to get the strength of the monster.
   * 
   * @return the strength of the monster.
   */
  int getStrength();

}

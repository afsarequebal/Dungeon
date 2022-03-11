package dungeonmodel;

/**
 * It is used to define methods of the dungeon panel.
 * Initially the dungeon maze is blank. As the user 
 * keeps on moving, it dungeon starts appearing. The
 * panel show treasure, arrow, player, monster. 
 *
 */
interface IDungeonPanel {

  /**
   * Get the string representation of the location.
   * 
   * @return the string representation of the location.
   */
  String locationDesc();

  /**
   * It is used to reduce the count of paint component being called,
   * by disabling it, once it is done.
   * 
   * @param done set done value as true or false.
   */
  void setDone(boolean done);

  /**
   * It is used to manually call the paint component method of the panel.
   */
  void paintM();

}

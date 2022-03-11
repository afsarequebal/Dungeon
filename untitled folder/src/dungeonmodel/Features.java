package dungeonmodel;

/**
 * This interface represents a set of features that the program offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller. How
 * the view uses them as callbacks is completely up to how the view is designed
 * (e.g. it could use them as a callback for @Override
  a button, or a callback for a
 * dialog box, or a set of text inputs, etc.)
 *
 * <p>Each function is designed to take in the necessary data to complete that
 * functionality.
 */

public interface Features {

  /**
   * It is used to move the player from one location to another. It takes input 
   * as no of row and columns and return successfully if no error comes.
   * 
   * @param row represents the row of the start location.
   * @param col represents the column of the start location.
   */
  void move(int row, int col);

  /**
   * It is used to move the player from one location to other. If move is 
   * successful, player current location is updated.
   * 
   * @param dir represents the direction to throw the arrow.
   */
  void move(String dir);
  
  /**
   * It is used to exit the program, if user clicks on quit button on the screen.
   * 
   */
  void exitProgram();

  /**
   * It gets input from a menu in the screen to redraw the dungeon based on user.
   * 
   * @param noOfRows the number of rows.
   * @param noOfColumns the number of columns.
   * @param interConnectivity the interconnectivity in the maze.
   * @param isWrapping true if maze is wrapped.
   * @param percentageOfTreasure percentage of treasure in the maze.
   * @param numberOfOtyughs no of otyughs in the maze.
   */
  boolean newGame(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyughs);

  /**
   * It is used to restart the game with same configurations.
   * 
   */
  void restartGame();

  /**
   * It is used to pick up treasure from a location in dungeon.
   * The item picked up can be diamond, sapphire, emerald.
   * 
   * @param item the item to be picked up.
   */
  void pickUp(String item);

  /**
   * It is used to attack the monster. It is called when player
   * provides shoot direction and distance.
   * 
   * @param shootDir direction to throw the arrow.
   * @param i distance for the arrow to travel.
   * 
   */
  void attack(String shootDir, int i);

  /**
   * It is used to fight the roaming monster. It battles the monster in a 
   * hand to hand fight. Whoever reaches zero strength first wins.
   * 
   */
  void fightMonster();

  /**
   * It is used to restart the game with same configurations.
   * 
   * @return true if restart of the game is successful.
   */
  boolean reuseGame();
}

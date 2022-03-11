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

interface Features {
  /**
   * Process the input string entered by the user.
   * 
   * @param input the string entered by the user
   */
  void processInput(String input);

  /**
   * Exit the program.
   */
//  void exitProgram();

  /**
   * Toggle the color of the displayed text.
   */
  //void toggleColor();

  /**
   * Make the displayed text upper case.
   */
  //void makeUppercase();

  /**
   * Restore the case of the displayed text.
   */
  //void restoreCase();
  
  void move(int row, int col);

  void exitProgram();
  
  boolean newGame(int noOfRows, int noOfColumns, int interConnectivity,
      boolean isWrapping, int percentageOfTreasure, int numberOfOtyughs);

  boolean restartGame();

  void move(String dir);

  boolean pickUp(String item);

  int attack(String shootDir, int i);

  int fightMonster();
}

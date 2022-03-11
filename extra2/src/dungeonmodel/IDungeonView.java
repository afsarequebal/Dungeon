package dungeonmodel;

/**
 * A view for TicTacToe: display the game board and provide visual interface
 * for users.
 */
public interface IDungeonView {

  /**
   * Set the label that is showing what the model stores.
   * 
   * @param s the text for the label
   */
//  void setEchoOutput(String s);

  /**
   * Clear the text field. Note that a more general "setInputString" would work
   * for this purpose but would be incorrect. This is because the text field is
   * not set programmatically in general but input by the user.
   */

//  void clearInputString();

  /**
   * Toggle the color of the displayed text. This is an explicit view operation
   * because this is something that only the view can control
   */
//  void toggleColor();

  /**
   * Get the set of feature callbacks that the view can use.
   * 
   * @param f the set of feature callbacks as a Features object
   */
  void setFeatures(Features f);
  
  /**
   * Reset the focus on the appropriate part of the view that has the keyboard
   * listener attached to it, so that keyboard events will still flow through.
   */
  void resetFocus();
  
  void addClickListener(Features f);

  void makeVisible();
  
  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  void printError(String string);
  void showErrorMessage(String error);

  void setModel(IReadOnlyDungeon rm);

  void makeInVisible();

  void printResult(String result);
}

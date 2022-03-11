package dungeonmodel;

/**
 * A view for dungeon game. It display the game board and provide visual interface
 * for users.
 */
public interface IDungeonView {

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

  /**
   * Makes the component visible to user. Each time view is updated 
   * it refreshes the page to reflect correct values.
   */
  void makeVisible();

  /**
   * Refresh the view to reflect any changes in the game state.
   */
  void refresh();

  /**
   * If the user enter incorrect moves, pick up wrong items,
   * error message is show in the details screen.
   * 
   * @param error error to be shown in the screen.
   */
  void showErrorMessage(String error);

  /**
   * It is used to set model to view, to get values from latest
   * model.
   * 
   * @param rm read only model to be used by view.
   */
  void setModel(IReadOnlyDungeon rm);

  /**
   * It is used to make screen invisible if user click on quit 
   * button on the player details pop-up.
   */
  void makeInVisible();

  /**
   * It is the used to print the result of each user action. It combines
   * the location details with result.
   * 
   * @param result string representation of user action.
   */
  void printResult(String result);

  /**
   * It is used to refresh the view with the result of the attack
   * monster by user.
   * 
   * @param s represents the result of an attack on monster.
   */
  void showAttackResult(int s);

  /**
   * It is used to refresh the view with the result of the attack
   * monster by user.
   * 
   */
  void showFightResult(int ret);

  /**
   * It is used to make the option frame invisible, if new game is created
   * successfully.
   * 
   */
  void makeOptionFrameVisible();

}

package dungeonmodel;

/**
 * This frame represents the region where the configuration is asked.
 * It shows asks for no of rows, columns, interconnectivity,
 * no of monsters, is wrapping.
 */
interface IOptionFrame {

  /**
   * It sets the listener for clicks on the done 
   * button of the configuration frame pop-up.
   * 
   * @param f the features supported by the model.
   */
  void setButtonListener(Features f);

  /**
   * It is used to show error message if the configuration
   * given by user is incorrect.
   * 
   * @param error the error message to be shown to user,if
   *     input is incorrect.
   */
  void showErrorMessage(String error);

  /**
   * It sets the action listener for clicking on the checkbox
   * of wrapping.
   */
  void checkBoxListener();
}

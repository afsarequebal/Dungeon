package dungeonmodel;

/**
 * Represents a Controller for Dungeon: handle user moves by executing them using the model;
 * convey move outcomes to the user in the GUI represented by view.
 */
public interface IGUIController {
  
  /**
   * Execute a single game of Dungeon given a Dungeon Model and view. When the game is over,
   * the playGame method ends.
   *
   */
  void playGame();

}

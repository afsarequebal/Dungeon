package dungeonmodel;

/**
 * Represents a Controller for Dungeon: handle user moves by executing them using the model;
 * convey move outcomes to the user in some form.
 */
public interface IGUIController {
  /**
   * Execute a single game of Dungeon given a Dungeon Model. When the game is over,
   * the playGame method ends.
   *
   */
  void playGame();

  /**
   * Handle an action in a single cell of the maze, such as to make a move.
   *
   * @param row the row of the clicked cell
   * @param col the column of the clicked cell
   */
 // void handleCellClick(int row, int col);
}

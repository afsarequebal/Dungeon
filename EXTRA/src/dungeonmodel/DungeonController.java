package dungeonmodel;


/**
 * Represents a Controller for Dungeon Game: handle user moves by executing them using the model;
 * Convey move outcomes to the user in the some form.
 */
public interface DungeonController {

  /**
   * Execute a single game of dungeon given a dungeon Model. When the game is over,
   * the playGame method ends.
   *
   * @param m a non-null tic tac toe Model
   */
  void playGame(IDungeon m);
}

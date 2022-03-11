package dungeonmodel;


/**
 * Represents a Controller for Dungeon Game: handle user moves by executing them using the model;
 * Convey move outcomes to the user in text form.
 */
public interface DungeonController {

  /**
   * Execute a single game of dungeon given a dungeon Model. When the game is over,
   * the playGame method ends.
   * It accepts M for move, P for pickup, S for shoot,
   * number for distance, N,E,W,S for direction.
   * It outputs in below format 
   * You have Arrow Arrow, it denotes player has two arrow in bag.
   * You have Diamond, it denotes player has diamond in bag.
   * You smell monster in nearby cell, denotes monster is present
   * in adjacent cell.
   * You found faint smell of monster, denotes monster is at a 
   * distance of 2.
   * Pickup item is not allowed, if no item is found for pickup.
   * You find 1 arrow here, denotes maze has arrow in the location.
   * Move, Pickup, or Shoot (M-P-S)?, asks player to choose M for move,
   * P for pickup, S for shoot.
   * You hear a great howl, seems monster is dead., denotes arrow hit
   * the monster and monster is dead.
   * You are out of arrows. Explore to find more, denotes player 
   * does not have arrow and hence cannot throw.
   * End location reached, means end is reached.
   * @param m a non-null tic tac toe Model
   */
  void playGame(IDungeon m);
}

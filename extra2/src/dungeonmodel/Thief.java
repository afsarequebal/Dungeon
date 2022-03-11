package dungeonmodel;


/**
 * Represents a player in the game of tic-tac-toe.
 */
 enum Thief {
  T("T");

  private final String disp;

  private Thief(String disp) {
    this.disp = disp;
  }

  @Override
  public String toString() {
    return disp;
  }
}

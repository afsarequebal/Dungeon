package dungeonmodel;


/**
 * Represents a thief in dungeon game.
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

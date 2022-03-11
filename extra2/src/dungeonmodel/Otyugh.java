package dungeonmodel;

/**
 * Otyugh is a type of monster. They can live in caves and kill humans.
 * They can be killed by human if attacked twice.
 *
 */
class Otyugh implements IMonster {

  private String name = "";
  private int strength;

  /**
   * Constructs an object of otyugh type monster.
   * 
   * @param name represents the name of the monster.
   * @param strength represents the strength of the monster.
   */
  public Otyugh(String name, int strength) {
    if (null != name) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("Name cannot be null");
    }
    this.strength = strength;
  }

  @Override
  public String getName() {
    return this.name;
  }
  
  @Override 
  public int getStrength() {
    return this.strength;
  }
  
  @Override 
  public void updateStrength() {
    this.strength = this.strength - 1;
  }
}

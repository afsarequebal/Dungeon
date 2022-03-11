package dungeonmodel;

import java.util.Objects;

/**
 * Otyugh is a type of monster. They can live in caves and kill humans.
 * They can be killed by human if attacked twice.
 *
 */
class Otyugh implements IMonster {

  private String name;
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
    if (strength < 0 || strength > 4) {
      throw new IllegalArgumentException("Strength cannot be less than"
          + " zero or greater than four.");
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
  
  @Override
  public int hashCode() {
    return Objects.hash(name, strength);
  }

  @Override
  public boolean equals(Object obj) {
    boolean ret = false;
    if (this == obj) {
      return true;
    }
    if (obj instanceof Otyugh) {
      Otyugh other = (Otyugh) obj;
      ret = Objects.equals(name, other.name) && strength == other.strength;
    }
    return ret;
  }
}

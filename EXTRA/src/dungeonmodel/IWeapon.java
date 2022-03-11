package dungeonmodel;

/**
 * Represents a weapon. A weapon can be found in a tunnel or cave.
 * Players having weapon can attack a monster. It takes two arrows
 * in the correct distance and direction to hit the defeat monsters. 
 */
interface IWeapon {

  /**
   * Returns the name of the weapon.
   * @return it returns the name of the weapon.
   */
  public String getName();

}

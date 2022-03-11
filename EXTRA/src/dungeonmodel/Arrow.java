package dungeonmodel;

/**
 * It represents a type of a weapon. It can travel through tunnel and 
 * caves. It can change direction if no path is found in a tunnel.
 */
class Arrow implements IWeapon {

  private String name = "";

  public Arrow(String name) {
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

}

package dungeonmodel;

import java.util.Objects;

/**
 * It represents a type of a weapon. It can travel through tunnel and 
 * caves. It can change direction if no path is found in a tunnel.
 */
class Arrow implements IWeapon {

  private String name = "";

  /**
   * Constructor to construct an arrow.
   * @param name represents the name of the arrow.
   */
  public Arrow(String name) {
    if (null == name || "".equals(name)) {
      throw new IllegalArgumentException("Input provided is not valid");
    }
    this.name = name;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    return String.format("Arrow name : %s", name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  @Override
  public boolean equals(Object obj) {
    boolean ret = false;
    if (this == obj) {
      return true;
    }
    if (obj instanceof Arrow) {
      Arrow other = (Arrow) obj;
      ret = Objects.equals(name, other.getName());
    }
    return ret;
  }
}

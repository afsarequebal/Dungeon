package dungeonmodel;

import java.util.Objects;

/**
 * Pair class represnts a pair of integers.
 * It is used collect list of adjacent pairs in the 
 * two dimension matrix.
 *
 */
class Pairs {

  private int key;
  private int value;

  /**
   * Constructs a pair object.
   * 
   * @param key represents the first element of the pair.
   * @param value represents the second element of the pair.
   */
  public Pairs(int key, int value) {
    this.key = key;
    this.value = value;
  }

  /**
   * It is used to get the key.
   * 
   * @return the key of pairs
   */
  public int getKey() {
    return key;
  }

  /**
   * It is used to set the key.
   * 
   * @param key represents the key.
   */
  public void setKey(int key) {
    this.key = key;
  }

  /**
   * It is used to get the value.
   * 
   * @return the value of pairs
   */
  public int getValue() {
    return value;
  }

  /**
   * It is used to set the value.
   * 
   * @param value represents the value.
   */
  public void setValue(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return key + " --> " + value ;
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, value);
  }

  @Override
  public boolean equals(Object obj) {
    boolean ret = false; 
    if (this == obj) {
      return true;
    }
    if (obj instanceof Pairs) {
      Pairs other = (Pairs) obj;
      ret = key == other.key && value == other.value;
    }
    return ret;
  }
}

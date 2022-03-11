package dungeonmodel;

/**
 * Interface represents random number to generate.
 *
 */
interface GenerateNumber {

  /**
   * Method to return random number.
   * @param min represents min to return.
   * @param max represents max to return.
   * @return a random number.
   */
  public int nextRandom(int min, int max, int seed);

}

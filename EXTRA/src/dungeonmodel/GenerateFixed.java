package dungeonmodel;

/**
 * GenerateFixed class is used to generate fixed numbers. 
 *
 */
class GenerateFixed implements GenerateNumber {

  @Override
  public int nextRandom(int min, int max) {
    return max;
  }
}

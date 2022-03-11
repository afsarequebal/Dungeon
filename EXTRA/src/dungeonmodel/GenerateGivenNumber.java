package dungeonmodel;

/**
 * Class to generate a number given in constructor.
 *
 */
class GenerateGivenNumber implements GenerateNumber {

  private int rnNumber;

  /**
   * Constructs a class to return a fixed number.
   * 
   * @param rnNumber fixed number to return.
   */
  public GenerateGivenNumber(int rnNumber) {
    this.rnNumber = rnNumber;
  }

  @Override
  public int nextRandom(int min, int max) {
    return rnNumber;
  }

}

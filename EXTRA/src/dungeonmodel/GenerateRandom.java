package dungeonmodel;

/**
 * Class to generate random number.
 *
 */
class GenerateRandom implements GenerateNumber {

  @Override
  public int nextRandom(int min, int max) {
    int random_int = (int)Math.floor(Math.random() * (max - min + 1) + min);
    return random_int;
  }
}

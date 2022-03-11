package dungeonmodel;

import java.util.Random;

/**
 * Class to generate random number.
 *
 */
class GenerateRandom implements GenerateNumber {

  @Override
  public int nextRandom(int min, int max, int seed) {
    Random random = new Random(seed);
    int random_int = (int)Math.floor(random.nextDouble() * (max - min + 1) + min);
    return random_int;
  }
}

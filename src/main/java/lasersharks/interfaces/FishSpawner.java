package lasersharks.interfaces;

import lasersharks.seaobjects.Fish;

import java.util.Random;

/**
 * Interface for FishSpawner.
 * 
 * @author SEMGroup27
 *
 */
public interface FishSpawner {

  /**
   * This function creates a new Fish with random values. This should be used to spawn fishes.
   * Starts on either the left side or the right side.
   * 
   * @return a random fish with random speed, size and position.
   * @param rng
   *          random number generator to use.
   */
  Fish generateFish(Random rng);

  /**
   * This function creates a new Fish with random values. This should be used to spawn fishes.
   * Starts on either the left side on
   * 
   * @return a random fish with random speed, size and position.
   */
  Fish generateFish();

  /**
   * Set the seed for this spawner.
   * 
   * @param rng
   *          new seed
   */
  void setFishRng(Random rng);
}

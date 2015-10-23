package lasersharks.interfaces;

import java.util.Random;

import lasersharks.seaobjects.Enemy;

/**
 * Interface for EnemySpawner.
 * 
 * @author SEMGroup27
 *
 */
public interface EnemySpawner {

  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side or the right side.
   * 
   * @return a random fish with random speed, size and position.
   * @param rng
   *          random number generator to use.
   */
  Enemy generateFish(Random rng);

  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side on
   * 
   * @return a random fish with random speed, size and position.
   */
  Enemy generateFish();

  /**
   * Set the seed for this spawner.
   * 
   * @param rng
   *          new seed
   */
  void setFishRng(Random rng);
}

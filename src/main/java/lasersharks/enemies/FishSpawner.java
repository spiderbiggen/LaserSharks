package lasersharks.enemies;

import java.util.Random;

import lasersharks.FishBot;
import lasersharks.LaserBullet;
import lasersharks.Swimmer;

/**
 * Interface for Fishfactory.
 * 
 * @author Sytze
 *
 */
public interface FishSpawner {

  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side or the right side.
   * 
   * @return a random fish with random speed, size and position.
   * @param rng
   *          random number generator to use.
   */
  FishBot generateFish(Random rng);

  /**
   * This function creates a laser.
   * @param origin the swimmer that shoots a laser.
   * @return a laserBullet object.
   */
  public LaserBullet createLaser(Swimmer origin);
  
  /**
   * This function creates a new FishBot with random values. This should be used to spawn fishes.
   * Starts on either the left side on
   * 
   * @return a random fish with random speed, size and position.
   */
  FishBot generateFish();

  /**
   * Set the seed for this spawner. if the new seed is null do not use any seed.
   * 
   * @param rng
   *          new seed
   */
  void setRng(Random rng);
}

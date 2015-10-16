package lasersharks.enemies;

import java.util.Random;

import lasersharks.Ammo;
import lasersharks.FishBot;
import lasersharks.LaserBullet;
import lasersharks.LaserShark;
import lasersharks.Displayable;

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
   * 
   * @param origin
   *          the shark that shoots a laser.
   * @return a laserBullet object.
   */
  LaserBullet createLaser(LaserShark origin);

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

  /**
   * This function creates a new Ammo pack on the screen. This should be used to spawn ammo.
   * 
   * @return an ammo pack on a random position.
   * @param rng
   *          random number generator to use.
   */
  Ammo generateAmmo(Random rng);

  /**
   * This function creates a new Ammo on a random location. This should be used to spawn ammo.
   * 
   * @return a random ammo with a random position.
   */
  Ammo generateAmmo();
}

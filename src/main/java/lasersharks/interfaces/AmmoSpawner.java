/**
 * 
 */
package lasersharks.interfaces;

import java.util.Random;

import lasersharks.seaObjects.Ammo;

/**
 * Interface for an ammo factory
 * 
 * @author Stefan Breetveld
 *
 */
public interface AmmoSpawner {
  
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

  /**
   * Set the seed for this spawner.
   * 
   * @param newRng
   *          new seed
   */
  void setAmmoRng(Random newRng);
}

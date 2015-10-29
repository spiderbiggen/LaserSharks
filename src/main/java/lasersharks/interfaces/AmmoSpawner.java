/**
 *
 */
package lasersharks.interfaces;

import lasersharks.seaobjects.Ammo;

import java.util.Random;

/**
 * Interface for an ammo spawner.
 *
 * @author SEMGroup27
 *
 * @author SEMGroup27
 */
public interface AmmoSpawner {

  /**
   * This function creates a new Ammo pack on the screen. This should be used to spawn ammo.
   *
   * @param rng random number generator to use.
   * @return an ammo pack on a random position.
   */
  Ammo generateAmmo(Random rng);

  /**
   * This function creates a new Ammo on a random location. This should be used to spawn ammo.
   *
   * @return a random ammo with a random position.
   */
  Ammo generateAmmo();

}

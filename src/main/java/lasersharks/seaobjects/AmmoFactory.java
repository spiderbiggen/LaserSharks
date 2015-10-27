/**
 *
 */
package lasersharks.seaobjects;

import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.AmmoSpawner;

import java.util.Random;

/**
 * @author SEMGroup27
 */
public class AmmoFactory implements AmmoSpawner {

  /**
   * These values imply the default values for an ammo object.
   */
  private static final int AMMO_SIZE = 40;

  /**
   * this value is used as the seed for the ammo.
   */
  private Random ammoRng;

  /**
   *
   */
  public AmmoFactory() {
    this.ammoRng = new Random();
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.AmmoSpawner#generateAmmo(java.util.Random)
   */
  @Override
  public void setAmmoRng(final Random newRng) {
    this.ammoRng = newRng;
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.AmmoSpawner#generateAmmo(java.util.Random)
   */
  @Override
  public Ammo generateAmmo(final Random rng) {
    final double posX = rng.nextInt((int) Options.getGlobalWidth() + 1);
    final double posY = rng.nextInt((int) Options.getGlobalHeight() + 1);

    return new Ammo(new Position(posX, posY), AMMO_SIZE);

  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.AmmoSpawner#generateAmmo(java.util.Random)
   */
  @Override
  public Ammo generateAmmo() {
    return generateAmmo(this.ammoRng);
  }

}

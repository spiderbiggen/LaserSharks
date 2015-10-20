/**
 * 
 */
package lasersharks;

import java.util.Random;

import lasersharks.controllers.Options;

/**
 * @author SEMGroup27
 *
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
  public void setAmmoRng(Random newRng) {
    this.ammoRng = newRng;
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.AmmoSpawner#generateAmmo(java.util.Random)
   */
  @Override
  public Ammo generateAmmo(Random rng) {
    @SuppressWarnings("static-access")
    double posX = rng.nextInt((int) (Options.getInstance().getGlobalWidth() - 0) + 1);
    @SuppressWarnings("static-access")
    double posY = rng.nextInt((int) (Options.getInstance().getGlobalHeight() - 0) + 1);

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

package lasersharks.seaobjects;

import java.util.Random;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.interfaces.AmmoSpawner;

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
   * This value is used as the seed for the ammo.
   */
  private Random ammoRng;

  /**
   * Constructor.
   */
  public AmmoFactory() {
    this.ammoRng = new Random();
  }


  @Override
  public void setAmmoRng(Random newRng) {
    this.ammoRng = newRng;
  }

  @Override
  public Ammo generateAmmo(Random rng) {
    @SuppressWarnings("static-access")
    double posX = rng.nextInt((int) (Options.getInstance().getGlobalWidth() - 0) + 1);
    @SuppressWarnings("static-access")
    double posY = rng.nextInt((int) (Options.getInstance().getGlobalHeight() - 0) + 1);

    return new Ammo(new Position(posX, posY), AMMO_SIZE);

  }

  @Override
  public Ammo generateAmmo() {
    return generateAmmo(this.ammoRng);
  }

}

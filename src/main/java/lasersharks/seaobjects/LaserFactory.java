/**
 * 
 */
package lasersharks.seaobjects;

import lasersharks.Position;
import lasersharks.interfaces.LaserSpawner;

/**
 * @author SEMGroup27
 *
 */
public class LaserFactory implements LaserSpawner {

  /**
   * These values imply the default values for a laser object.
   */
  private static final int LASER_SIZE = 13;
  private static final int LASER_SPEED = 800;
  private static final int LASER_POSITION_Y_OFFSET = 45;

  /**
   * Constructor.
   */
  public LaserFactory() {

  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.LaserSpawner#createLaser(lasersharks.LaserShark)
   */
  @Override
  public LaserBullet createLaser(LaserShark origin) {
    Position posShark = origin.getPosition();
    Position posLaser = new Position(posShark.getPosX(), posShark.getPosY()
        + LASER_POSITION_Y_OFFSET);

    return new LaserBullet(posLaser, LASER_SIZE, LASER_SPEED, origin.getLastHorizontalDirection());
  }

}

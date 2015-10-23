/**
 * 
 */
package lasersharks.seaobjects;

import lasersharks.Position;
import lasersharks.controllers.AudioController;
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
  private static final double LASER_POSITION_Y_SCALE = 0.5;
  private static final double LASER_POSITION_X_SCALE = 0.7;

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
    AudioController.getInstance().playLaserSoundEffect();
    Position posLaser = new Position(posShark.getPosX() + LASER_POSITION_X_SCALE * origin.getSize(),
        posShark.getPosY() + LASER_POSITION_Y_SCALE * origin.getSize());

    return new LaserBullet(posLaser, LASER_SIZE, LASER_SPEED, origin.getLastHorizontalDirection());
  }
}

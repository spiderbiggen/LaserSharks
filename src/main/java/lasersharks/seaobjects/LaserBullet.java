package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.collisionhitbox.DefaultCollisionHitBoxBehaviour;
import lasersharks.behaviour.lasercollision.LaserLaserCollisionBehaviour;
import lasersharks.behaviour.move.DefaultMoveBehaviour;

/**
 * This class represents a laser projectile.
 * 
 * @author SEMGroup27
 *
 */
public class LaserBullet extends AbstractSeaObject {

  private static final int SIZE_DECREMENT_ON_HIT = 30;
  private static final String LASER_IMAGE = "greenLaserRay.png";
  private static final double WIDTH_SCALE = 3.918918;

  /**
   * The constructor of the laser.
   * 
   * @param position
   *          the position of the laser.
   * @param size
   *          the size of the laser.
   * @param startSpeed
   *          the starting speed of the laser.
   * @param direction
   *          the direction of the laser.
   */
  public LaserBullet(final Position position, final float size, final double startSpeed,
      final Direction direction) {
    super(position, size, startSpeed, direction);
    collisionHitBoxBehaviour = new DefaultCollisionHitBoxBehaviour(this);
    moveBehaviour = new DefaultMoveBehaviour(this);
    laserCollisionBehaviour = new LaserLaserCollisionBehaviour(this);
  }

  /**
   * gets the filename of the image used for the laser.
   */
  @Override
  public String getImageResource() {
    return LASER_IMAGE;
  }

  /**
   * gets the widthScale of the image. (width/height)
   */
  @Override
  public double getWidthScale() {
    return WIDTH_SCALE;
  }
  
  @Override
  public int getOnCollisionSizeDecrement() {
    return SIZE_DECREMENT_ON_HIT;
  }

}

package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.collisionHitbox.DefaultCollisionHitboxBehaviour;
import lasersharks.behaviour.lasercollision.LaserLaserCollisionBehaviour;
import lasersharks.behaviour.move.DefaultMoveBehaviour;
import lasersharks.behaviour.DefaultEatBehaviour;

/**
 * This class represents a laser projectile.
 * 
 * @author SEMGroup27
 *
 */
public class LaserBullet extends SeaObject {

  private static final int SIZE_DECREMENT_ON_HIT = 10;
  private static final String LASER_IMAGE = "greenLaserRay.png";
  private static final double LASER_DEFAULT_STRENGTH = 1.0;
  private static final double IMG_WIDTH = 290;
  private static final double IMG_HEIGHT = 74;

  private double strength;

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
  public LaserBullet(Position position, float size, double startSpeed, Direction direction) {
    super(position, size, startSpeed, direction);
    strength = LASER_DEFAULT_STRENGTH;
    collisionHitBoxBehaviour = new DefaultCollisionHitboxBehaviour(this);
    eatBehaviour = new DefaultEatBehaviour();
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
   * gets the widthscale of the image. (width/height)
   */
  @Override
  public double getWidthScale() {
    return IMG_WIDTH / IMG_HEIGHT;
  }

  /**
   * returns the strength.
   * Possiably depricated.
   * @return the strength.
   */
  public double getStrength() {
    return strength;
  }
  
  @Override
  public int getOnCollisionSizeDecrement() {
    return SIZE_DECREMENT_ON_HIT;
  }

}

package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.BotMoveBehaviour;
import lasersharks.behaviour.CantEatBehaviour;
import lasersharks.behaviour.DefaultCollisionBehaviour;

/**
 * This class represents a laser projectile.
 * 
 * @author SEMGroup27
 *
 */
public class LaserBullet extends SeaObject {

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
    collisionBehaviour = new DefaultCollisionBehaviour(this);
    eatBehaviour = new CantEatBehaviour();
    moveBehaviour = new BotMoveBehaviour(this);
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
   * 
   * @return the strength.
   */
  public double getStrength() {
    return strength;
  }

}

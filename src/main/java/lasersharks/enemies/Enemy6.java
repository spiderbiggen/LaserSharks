package lasersharks.enemies;

import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.Position;

/**
 * Class for the first enemy.
 * 
 * @author Youri
 *
 */
public class Enemy6 extends FishBot {
  private static final String IMAGE = "enemy-6.png";
  private static final double IMG_HEIGHT = 69.0;
  private static final double IMG_WIDTH = 157.0;

  /**
   * propagation for construction.
   * 
   * @param position
   *          Starting position.
   * @param size
   *          Starting size.
   * @param speed
   *          Starting speed.
   * @param direction
   *          Starting direction.
   */
  public Enemy6(Position position, Float size, Double speed, Direction direction) {
    super(position, size, speed, direction);
  }

  @Override
  public String getImageResource() {
    return IMAGE;
  }

  @Override
  public double getWidthScale() {
    return IMG_WIDTH / IMG_HEIGHT;
  }

}

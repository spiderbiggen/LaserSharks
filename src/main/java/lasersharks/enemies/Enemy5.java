package lasersharks.enemies;

import lasersharks.Direction;
import lasersharks.FishBot;
import lasersharks.Position;

/**
 * Class for the first enemy.
 * @author Youri
 *
 */
public class Enemy5 extends FishBot {
  private static final String IMAGE = "enemy-5.png";
  private static final double IMG_HEIGHT = 90.0;
  private static final double IMG_WIDTH = 67.0;

  /**
   * propagation for construction.
   * @param position Starting position.
   * @param size Starting size.
   * @param speed Starting speed.
   * @param direction Starting direction.
   */
  public Enemy5(Position position, Float size, Double speed, Direction direction) {
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

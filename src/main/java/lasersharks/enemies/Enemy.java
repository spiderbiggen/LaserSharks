package lasersharks.enemies;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.SeaObject;
import lasersharks.behaviour.BotMoveBehaviour;
import lasersharks.behaviour.CantEatBehaviour;
import lasersharks.behaviour.DefaultCollisionBehaviour;

/**
 * Class for the first enemy.
 * 
 * @author SEMGroup27
 *
 */
public class Enemy extends SeaObject {
  private String image;
  private int imgHeight;
  private int imgWidth;

  /**
   * propagation for construction.
   * 
   * @param image
   *          Image of the enemy.
   * @param imgHeight
   *          Height of image.
   * @param imgWidth
   *          Width of image.
   * @param position
   *          Starting position.
   * @param size
   *          Starting size.
   * @param speed
   *          Starting speed.
   * @param direction
   *          Starting direction.
   */
  public Enemy(String image, int imgHeight, int imgWidth, Position position, Float size,
      Double speed, Direction direction) {
    super(position, size, speed, direction);
    this.image = image;
    this.imgHeight = imgHeight;
    this.imgWidth = imgWidth;
    collisionBehaviour = new DefaultCollisionBehaviour(this);
    moveBehaviour = new BotMoveBehaviour(this);
    eatBehaviour = new CantEatBehaviour();
  }

  @Override
  public String getImageResource() {
    return image;
  }

  @Override
  public double getWidthScale() {
    return ((double) imgHeight) / ((double) imgWidth);
  }

}

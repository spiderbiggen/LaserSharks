/**
 * 
 */
package lasersharks.seaObjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.CantEatBehaviour;
import lasersharks.behaviour.CantMoveBehaviour;
import lasersharks.behaviour.DefaultCollisionBehaviour;

/**
 * @author spide
 *
 */
public class Ammo extends SeaObject {

  private final int pickupAmount = 10;
  private final String imageResource = "battery.png";
  private final int imgHeight = 228;
  private final int imgWidth = 300;

  /**
   * Constructor class for Ammo.
   * 
   * @param position
   *          initial position
   * @param size
   *          init size
   */
  public Ammo(Position position, float size) {
    super(position, size, 0, Direction.None);
    collisionBehaviour = new DefaultCollisionBehaviour(this);
    moveBehaviour = new CantMoveBehaviour();
    eatBehaviour = new CantEatBehaviour();
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.SeaObject#getImageResource()
   */
  @Override
  public String getImageResource() {
    return imageResource;
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.SeaObject#getWidthScale()
   */
  @Override
  public double getWidthScale() {
    return (double) imgWidth / (double) imgHeight;
  }

  /**
   * @return The amount of ammo in this pack 
   */
  public int getPickupAmount() {
    return pickupAmount;
  }

}

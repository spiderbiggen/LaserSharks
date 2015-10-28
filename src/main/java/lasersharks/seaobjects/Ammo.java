package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.ammunitionIncrement.AmmoAmmunitionIncrementBehaviour;
import lasersharks.behaviour.eaten.AmmoEatenBehaviour;
import lasersharks.behaviour.move.NoMovementMoveBehaviour;

/**
 * @author SEMGroup27
 *
 */
public class Ammo extends SeaObject {

  private final int pickupAmount = 6;
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
    moveBehaviour = new NoMovementMoveBehaviour();
    ammunitionIncrementBehaviour = new AmmoAmmunitionIncrementBehaviour();
    eatenBehaviour = new AmmoEatenBehaviour(this);
    
  }

  @Override
  public String getImageResource() {
    return imageResource;
  }

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

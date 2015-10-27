/**
 *
 */
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

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.SeaObject#getImageResource()
   */
  @Override
  public String getImageResource() {
    return "battery.png";
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.SeaObject#getWidthScale()
   */
  @Override
  public double getWidthScale() {
    final double imgHeight = 228;
    final double imgWidth = 300;
    return imgWidth / imgHeight;
  }

  /**
   * @return The amount of ammo in this pack
   */
  public int getPickupAmount() {
    final int amount = 10;
    return amount;
  }

}

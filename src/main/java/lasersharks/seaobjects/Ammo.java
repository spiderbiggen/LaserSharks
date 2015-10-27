/**
 * 
 */
package lasersharks.seaobjects;

import lasersharks.Direction;
import lasersharks.Position;
import lasersharks.behaviour.ammunitionincrement.AmmoAmmunitionIncrementBehaviour;
import lasersharks.behaviour.eaten.AmmoEatenBehaviour;
import lasersharks.behaviour.move.NoMovementMoveBehaviour;

/**
 * @author SEMGroup27
 *
 */
public class Ammo extends AbstractSeaObject {

  private static final String IMAGE_RESOURCE = "battery.png";
  private static final double WIDTH_SCALE = 1.315789;

  /**
   * Constructor class for Ammo.
   * 
   * @param position
   *          initial position
   * @param size
   *          init size
   */
  public Ammo(final Position position, final float size) {
    super(position, size, 0, Direction.None);
    moveBehaviour = new NoMovementMoveBehaviour();
    ammunitionIncrementBehaviour = new AmmoAmmunitionIncrementBehaviour();
    eatenBehaviour = new AmmoEatenBehaviour(this);
  }

  /*
   * (non-Javadoc)
   *
   * @see lasersharks.AbstractSeaObject#getImageResource()
   */
  @Override
  public String getImageResource() {
    return IMAGE_RESOURCE;
  }

  /*
   * (non-Javadoc)
   * 
   * @see lasersharks.AbstractSeaObject#getWidthScale()
   */
  @Override
  public double getWidthScale() {
    return WIDTH_SCALE;
  }

}

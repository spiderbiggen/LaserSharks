package lasersharks.behaviour.sizedecrement;

import lasersharks.behaviour.SizeDecrementBehaviour;
import lasersharks.seaobjects.SeaObject;

/**
 * sizeDecrement behaviour for fish.
 * 
 * @author SEMGroup27
 *
 */
public class FishSizeDecrementBehaviour implements SizeDecrementBehaviour {
  private SeaObject element;

  /**
   * Constructor.
   * 
   * @param ele
   *          Element the behaviours handles about.
   */
  public FishSizeDecrementBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionSizeDecrement(int size) {
    element.decreaseSize(size);
  }
}

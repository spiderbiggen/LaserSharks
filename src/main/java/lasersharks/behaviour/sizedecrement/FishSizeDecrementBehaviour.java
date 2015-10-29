package lasersharks.behaviour.sizedecrement;

import lasersharks.behaviour.SizeDecrementBehaviour;
import lasersharks.seaobjects.AbstractSeaObject;

/**
 * SizeDecrement behaviour for fish.
 * 
 * @author SEMGroup27
 *
 */
public class FishSizeDecrementBehaviour implements SizeDecrementBehaviour {

  private final AbstractSeaObject element;

  /**
   * Constructor.
   * 
   * @param ele
   *          Element the behaviours handles about.
   */
  public FishSizeDecrementBehaviour(final AbstractSeaObject ele) {
    this.element = ele;
  }

  @Override public void onCollisionSizeDecrement(final int size) {
    element.decreaseSize(size);
  }
}

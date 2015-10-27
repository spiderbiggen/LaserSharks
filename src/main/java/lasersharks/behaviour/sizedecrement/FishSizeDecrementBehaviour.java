package lasersharks.behaviour.sizedecrement;

import lasersharks.behaviour.SizeDecrementBahaviour;
import lasersharks.seaobjects.AbstractSeaObject;

/**
 * sizeDecrement behaviour for fish.
 * 
 * @author SEMGroup27
 *
 */
public class FishSizeDecrementBehaviour implements SizeDecrementBahaviour {
  private final AbstractSeaObject element;

  /**
   * Constructor.
   * 
   * @param ele
   *          ELement the behaviours handles about.
   */
  public FishSizeDecrementBehaviour(final AbstractSeaObject ele) {
    this.element = ele;
  }

  @Override public void onCollisionSizeDecrement(final int size) {
    element.decreaseSize(size);
  }
}

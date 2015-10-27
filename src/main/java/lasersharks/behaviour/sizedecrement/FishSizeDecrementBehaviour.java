package lasersharks.behaviour.sizedecrement;

import lasersharks.behaviour.SizeDecrementBahaviour;
import lasersharks.seaobjects.SeaObject;

/**
 * sizeDecrement behaviour for fish.
 * 
 * @author SEMGroup27
 *
 */
public class FishSizeDecrementBehaviour implements SizeDecrementBahaviour {
  private final SeaObject element;

  /**
   * Constructor.
   * 
   * @param ele
   *          ELement the behaviours handles about.
   */
  public FishSizeDecrementBehaviour(final SeaObject ele) {
    this.element = ele;
  }

  @Override public void onCollisionSizeDecrement(final int size) {
    element.decreaseSize(size);
  }
}

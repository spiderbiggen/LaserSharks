package lasersharks.behaviour.collision;

import lasersharks.behaviour.collision.interfaces.SizeDecrementBahaviour;
import lasersharks.seaobjects.SeaObject;

/**
 * sizeDecrement behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishSizeDecrementBehaviour implements SizeDecrementBahaviour {
  private static final float SIZE_DECREMENT = 10.0f;
  private SeaObject element;
      
  /**
   * Constructor.
   * @param ele ELement the behaviours handles about.
   */
  public FishSizeDecrementBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionSizeDecrement(int size) {
    element.decreaseSize(SIZE_DECREMENT);
  }
}

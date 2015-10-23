package lasersharks.behaviour.sizedecrement;

import lasersharks.behaviour.SizeDecrementBahaviour;
import lasersharks.seaobjects.SeaObject;

/**
 * sizeDecrement behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishSizeDecrementBehaviour implements SizeDecrementBahaviour {
  private SeaObject element;
  private static final float DECREASE_SIZE = 5;
    
  /**
   * Constructor.
   * @param ele ELement the behaviours handles about.
   */
  public FishSizeDecrementBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionSizeDecrement(int size) {
    element.decreaseSize(DECREASE_SIZE);
  }
}

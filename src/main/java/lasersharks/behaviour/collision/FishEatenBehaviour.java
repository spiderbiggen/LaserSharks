package lasersharks.behaviour.collision;

import lasersharks.behaviour.collision.interfaces.EeatenBehaviour;
import lasersharks.seaObjects.SeaObject;

/**
 * Eaten behaviour for Fishes.
 * @author SEMGroup27
 *
 */
public class FishEatenBehaviour implements EeatenBehaviour {
  private SeaObject element;
  
  /**
   * Constructor.
   * @param ele element to wich to propagate actions.
   */
  public FishEatenBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionEaten() {
    element.kill();
  }
}

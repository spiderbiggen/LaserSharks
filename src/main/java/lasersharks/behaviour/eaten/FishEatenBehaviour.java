package lasersharks.behaviour.eaten;

import lasersharks.Logger;
import lasersharks.behaviour.EeatenBehaviour;
import lasersharks.seaobjects.SeaObject;
import lasersharksgui.panes.StandardPane;

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
    element.setSize(0);
  }
}

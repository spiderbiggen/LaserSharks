package lasersharks.behaviour.eaten;

import lasersharks.Logger;
import lasersharks.behaviour.EatenBehaviour;
import lasersharks.seaobjects.SeaObject;

/**
 * Eaten behaviour for Ammunition.
 * @author SEMGroup27
 *
 */
public class AmmoEatenBehaviour implements EatenBehaviour {
  private SeaObject element;
  
  /**
   * Constructor.
   * @param ele element to which to propagate actions.
   */
  public AmmoEatenBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionEaten() {
    Logger.getInstance().write("Ammo", "Player has picked up some ammo");
    element.kill();
  }
}

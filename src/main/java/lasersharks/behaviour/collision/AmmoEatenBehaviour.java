package lasersharks.behaviour.collision;

import lasersharks.Logger;
import lasersharks.behaviour.collision.interfaces.EeatenBehaviour;
import lasersharks.seaObjects.SeaObject;

/**
 * Eaten behaviour for Ammunition.
 * @author SEMGroup27
 *
 */
public class AmmoEatenBehaviour implements EeatenBehaviour {
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
    //TODO: play some sound.
    element.kill();
  }
}

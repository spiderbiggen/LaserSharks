package lasersharks.behaviour.eaten;

import lasersharks.Logger;
import lasersharks.behaviour.EatenBehaviour;
import lasersharks.controllers.AudioController;
import lasersharks.seaobjects.AbstractSeaObject;

/**
 * Eaten behaviour for Ammunition.
 * @author SEMGroup27
 *
 */
public class AmmoEatenBehaviour implements EatenBehaviour {
  private final AbstractSeaObject element;
  
  /**
   * Constructor.
   * @param ele element to which to propagate actions.
   */
  public AmmoEatenBehaviour(final AbstractSeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionEaten() {
    Logger.getInstance().write("Ammo", "Player has picked up some ammo");
    AudioController.getInstance().playPickupSoundEffect();
    element.kill();
  }
}

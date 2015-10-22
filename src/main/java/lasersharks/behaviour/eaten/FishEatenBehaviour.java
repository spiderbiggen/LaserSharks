package lasersharks.behaviour.eaten;

import lasersharks.Options;
import lasersharks.behaviour.EeatenBehaviour;
import lasersharks.controllers.AudioController;
import lasersharks.seaobjects.SeaObject;

/**
 * Eaten behaviour for Fishes.
 * 
 * @author SEMGroup27
 *
 */
public class FishEatenBehaviour implements EeatenBehaviour {
  private SeaObject element;

  /**
   * Constructor.
   * 
   * @param ele
   *          element to wich to propagate actions.
   */
  public FishEatenBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionEaten() {
    element.kill();
    element.setSize(0);
    AudioController.getInstance().playSoundEffect(Options.getInstance().getHitSoundFileName());
  }
}

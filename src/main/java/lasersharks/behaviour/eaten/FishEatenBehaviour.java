package lasersharks.behaviour.eaten;

import lasersharks.behaviour.EatenBehaviour;
import lasersharks.controllers.AudioController;
import lasersharks.seaobjects.AbstractSeaObject;

/**
 * Eaten behaviour for Fishes.
 *
 * @author SEMGroup27
 */
public class FishEatenBehaviour implements EatenBehaviour {

  private final AbstractSeaObject element;

  /**
   * Constructor.
   *
   * @param ele
   *          element to which to propagate actions.
   */
  public FishEatenBehaviour(final AbstractSeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionEaten() {
    element.kill();
    element.setSize(0);
    AudioController.getInstance().playEatSoundEffect();
  }
}

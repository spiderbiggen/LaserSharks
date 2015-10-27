package lasersharks.behaviour.sizeincrement;

import lasersharks.Logger;
import lasersharks.behaviour.GetSizeIncrementBahaviour;
import lasersharks.controllers.AudioController;
import lasersharks.seaobjects.AbstractSeaObject;

/**
 * getSizeIncrement behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishGetSizeIncrementBehaviour implements GetSizeIncrementBahaviour {
  private final AbstractSeaObject element;
  
  public static final float ENERGY_DISSERPATION_RATE = 7.5f;
  
  /**
   * Constructor.
   * @param ele element to which to propagate actions.
   */
  public FishGetSizeIncrementBehaviour(final AbstractSeaObject ele) {
    this.element = ele;
  }

  @Override
  public float onCollisionSizeIncrement() {
    Logger.getInstance().write("Fish eaten", element.toString());
    AudioController.getInstance().playEatSoundEffect();
    return element.getSize() / ENERGY_DISSERPATION_RATE;
  }
}

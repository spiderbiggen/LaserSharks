package lasersharks.behaviour.sizeincrement;

import lasersharks.Logger;
import lasersharks.behaviour.GetSizeIncrementBehaviour;
import lasersharks.controllers.AudioController;
import lasersharks.seaobjects.SeaObject;

/**
 * getSizeIncrement behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishGetSizeIncrementBehaviour implements GetSizeIncrementBehaviour {
  private SeaObject element;

  public static final float ENERGY_DISSIPATION_RATE = 7.5f;
  
  /**
   * Constructor.
   * @param ele element to which to propagate actions.
   */
  public FishGetSizeIncrementBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public float onCollisionSizeIncrement() {
    Logger.getInstance().write("Fish eaten", element.toString());
    AudioController.getInstance().playEatSoundEffect();
    return element.getSize() / ENERGY_DISSIPATION_RATE;
  }
}

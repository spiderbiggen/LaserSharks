package lasersharks.behaviour.sizeincrement;

import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.behaviour.GetSizeIncrementBahaviour;
import lasersharks.controllers.AudioController;
import lasersharks.seaobjects.SeaObject;
import lasersharksgui.panes.StandardPane;

/**
 * getSizeIncrement behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishGetSizeIncrementBehaviour implements GetSizeIncrementBahaviour {
  private SeaObject element;
  
  public static final float ENERGY_DISSERPATION_RATE = 30f;
  
  /**
   * Constructor.
   * @param ele element to which to propagate actions.
   */
  public FishGetSizeIncrementBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public synchronized float onCollisionSizeIncrement() {
    Logger.getInstance().write("Fish eaten", element.toString());
    AudioController.getInstance().playSoundEffect(Options.getInstance().getHitSoundFileName());
    return (element.getSize() / ENERGY_DISSERPATION_RATE);
  }
}

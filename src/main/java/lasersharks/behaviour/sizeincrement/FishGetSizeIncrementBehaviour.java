package lasersharks.behaviour.sizeincrement;

import lasersharks.Logger;
import lasersharks.behaviour.interfaces.GetSizeIncrementBahaviour;
import lasersharks.seaobjects.SeaObject;
import lasersharksgui.panes.StandardPane;

/**
 * getSizeIncrement behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishGetSizeIncrementBehaviour implements GetSizeIncrementBahaviour {
  private SeaObject element;
  
  private static final float ENERGY_DISSERPATION_RATE = 7.5f;
  private static final String EAT_FISH_SOUND = "src/main/resources/soundEffect1.wav";
  
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
    StandardPane.playSoundEffect(EAT_FISH_SOUND);
    return (element.getSize() / ENERGY_DISSERPATION_RATE);
  }
}

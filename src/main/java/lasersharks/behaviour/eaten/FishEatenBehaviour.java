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
  private static final String EAT_FISH_SOUND = "src/main/resources/soundEffect1.wav";
  
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
    StandardPane.playSoundEffect(EAT_FISH_SOUND);
  }
}

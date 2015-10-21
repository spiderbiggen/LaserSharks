package lasersharks.behaviour.checkforloss;

import lasersharks.behaviour.interfaces.CheckForLossBehaviour;
import lasersharks.seaobjects.SeaObject;
import lasersharksgui.MainGui;
import lasersharksgui.panes.LosingPane;

/**
 * Check for loss behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishCheckForLossBehaviour implements CheckForLossBehaviour {
  private SeaObject element;
  
  /**
   * Constructor.
   * @param ele element to wich to propagate actions.
   */
  public FishCheckForLossBehaviour(SeaObject ele) {
    this.element = ele;
  }

  @Override
  public void onCollisionPlayerLoses(float size) {
    if (size < element.getSize()) {
      MainGui.browseToGlobal(LosingPane.class);
    }
  }

}

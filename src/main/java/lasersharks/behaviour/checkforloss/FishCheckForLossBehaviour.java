package lasersharks.behaviour.checkforloss;

import lasersharks.behaviour.CheckForLossBehaviour;
import lasersharks.seaobjects.AbstractSeaObject;
import lasersharksgui.MainGui;
import lasersharksgui.panes.LosingPane;

/**
 * Check for loss behaviour for fish.
 * @author SEMGroup27
 *
 */
public class FishCheckForLossBehaviour implements CheckForLossBehaviour {
  private final AbstractSeaObject element;
  
  /**
   * Constructor.
   * @param ele element to wich to propagate actions.
   */
  public FishCheckForLossBehaviour(final AbstractSeaObject ele) {
    this.element = ele;
  }

  @Override public void onCollisionPlayerLoses(final float size) {
    if (size <= element.getSize()) {
      MainGui.browseToGlobal(LosingPane.class);
    }
  }

}

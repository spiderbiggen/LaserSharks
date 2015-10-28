package lasersharksgui.panes;

import lasersharks.controllers.AudioController;

/**
 * This pane represents the screen that is shown when a player loses.
 * 
 * @author SEMGroup27
 *
 */
public class LosingPane extends EndGamePane {

  private static final String LOSE_SOUND = "src/main/resources/awww.mp3";
  
  /**
   * The constructor makes a new panel with a few text objects shown.
   */
  public LosingPane() {
    super("YOU LOSE!");
    AudioController.getInstance().playSoundEffect(LOSE_SOUND);
  }

}

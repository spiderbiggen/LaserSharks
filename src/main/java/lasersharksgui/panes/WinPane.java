package lasersharksgui.panes;

import lasersharks.controllers.AudioController;

/**
 * This pane represents the screen that is shown when a player loses.
 * 
 * @author SEMGroup27
 *
 */
public class WinPane extends EndGamePane {

  private static final String WIN_MUSIC = "src/main/resources/winMusic.mp3";
  
  /**
   * The constructor makes a new panel with a few text objects shown.
   */
  public WinPane() {
    super("You Won!");
    AudioController.getInstance().playMusic(WIN_MUSIC);
  }

}

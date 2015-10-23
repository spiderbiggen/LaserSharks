package lasersharksgui.panes;

import lasersharks.Options;

public class OptionsPanel extends StandardPane{

  private static int PAUSE_TEXT_SIZE_UP = 6;
  private static double PAUSE_TEXT_POSITION_UP = 2.5;
  
  private static int PAUSE_TEXT_SIZE_BELOW = 4;
  private static double PAUSE_TEXT_POSITION_BELOW = 3.0;
  
  private static final double MUTE_BUTTON_HEIGHT_SCALE = 4.0;
  private static final double MUTE_BUTTON_WIDTH_SCALE = 4.0;
  private static final double MUTE_SCALE_SIZE = 3.0;
  
  
  public OptionsPanel() {
    addMidText(
        "game Paused", 
        PAUSE_TEXT_SIZE_UP, 
        Options.getGlobalHeight() / PAUSE_TEXT_POSITION_UP
        );
    addMidText(
        "press P to resume", 
        PAUSE_TEXT_SIZE_BELOW, 
        Options.getGlobalHeight() / PAUSE_TEXT_POSITION_BELOW
        );
    getChildren().add(muteButton(
        Options.getGlobalWidth() / MUTE_BUTTON_WIDTH_SCALE,
        Options.getGlobalHeight() / MUTE_BUTTON_HEIGHT_SCALE,
        MUTE_SCALE_SIZE
        ));
  }
  
  @Override
  public void stop() {
    //empty
  }
}

package lasersharksgui.panes;

import javafx.scene.input.KeyEvent;

public class OptionsPanel extends StandardPane{

  private static int PAUSE_TEXT_SIZE = 12;
  private static double PAUSE_TEXT_POSITION = 12.0;
  
  public OptionsPanel() {
    addMidText("game Paused, press P to resume", PAUSE_TEXT_SIZE, PAUSE_TEXT_POSITION);
  }
  
  @Override
  public void stop() {
    //empty
  }
}

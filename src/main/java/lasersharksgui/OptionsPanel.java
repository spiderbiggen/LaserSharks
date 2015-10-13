package lasersharksgui;

import javafx.scene.input.KeyEvent;
import lasersharks.RestartGameController;

public class OptionsPanel extends StandardPane{

  private static int PAUSE_TEXT_SIZE = 12;
  private static double PAUSE_TEXT_POSITION = 12.0;
  
  private GamePane gamePane;
  
  public OptionsPanel(GamePane gamePane) {
    addMidText("game Paused, press space to resume", PAUSE_TEXT_SIZE, PAUSE_TEXT_POSITION);
    this.gamePane = gamePane;
  }  
  
  public void backToGame() {
    this.stop();
    gamePane.resumeGame();
    MainGui.getInstance().browseToPane(gamePane);
  }
  
  @Override
  public void stop() {
    //empty
  }
}

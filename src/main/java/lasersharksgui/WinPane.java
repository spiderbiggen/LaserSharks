package lasersharksgui;

import javafx.scene.text.Text;
import lasersharks.Highscores;
import lasersharks.Position;

public class WinPane extends StandardPane {

  public WinPane() {
    
    addMidText("YOU WON!!!", TEXT_SCALE_SIZE_BIG, -230);
    addMidText(Highscores.makeHighscoreString(), TEXT_SCALE_SIZE_SMALL, 100);
  }
}

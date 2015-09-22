package lasersharksgui;

import java.io.IOException;

import javafx.scene.text.Text;
import lasersharks.Highscores;
import lasersharks.Position;

public class WinPane extends StandardPane {

  public WinPane() {
    
    addMidText("YOU WON!!!", TEXT_SCALE_SIZE_BIG, -230);
    try {
      Highscores.writeHighscore();
      addMidText(Highscores.makeHighscoreString(), TEXT_SCALE_SIZE_SMALL, 100);
    } catch (IOException e) {
      addMidText("highscores failed to load", TEXT_SCALE_SIZE_MED, 100);
      e.printStackTrace();
    }

    addMidText("try again?", TEXT_SCALE_SIZE_SMALL, -200);
  }
}

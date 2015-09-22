package lasersharksgui;

import java.io.IOException;

import lasersharks.Highscores;

public class LosingPane extends StandardPane {
public LosingPane() {
    
    addMidText("YOU LOSE!", TEXT_SCALE_SIZE_BIG, 400);
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

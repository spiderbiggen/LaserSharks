package lasersharks.gui;

import java.io.IOException;

import lasersharks.Highscores;
import lasersharks.Logger;
import lasersharks.Options;

/**
 * This pane represents the screen that is shown when a player loses.
 * @author Sytze
 *
 */
public class WinPane extends StandardPane {
  
  
  
  /**
   * The constructor makes a new panel with a few text objects shown.
   */
  public WinPane() {
      
      addMidText("YOU WON!", 
          TEXT_SCALE_SIZE_BIG, 
          Options.getGlobalHeight() / SCREEN_POSITION_THREE);
      String message;
      try {
        Highscores.writeHighscore();
        message = Highscores.makeHighscoreString();
      } catch (IOException e) {
        message = "Highscores failed to load";
        Logger.getInstance().write("IOException ", e.getMessage());
        e.printStackTrace();
      }
      addMidText(message, 
          TEXT_SCALE_SIZE_SMALL, 
          Options.getGlobalHeight() / SCREEN_POSITION_TEN);
      addMidText("try again?", 
          TEXT_SCALE_SIZE_SMALL, 
          -Options.getGlobalHeight() / SCREEN_POSITION_FIVE);
    }
}

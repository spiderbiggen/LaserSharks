/**
 * 
 */
package lasersharksgui.panes;

import javafx.scene.input.KeyEvent;
import lasersharks.HighScores;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.controllers.RestartGameController;
import lasersharksgui.MainGui;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Class for the endGame Pane.
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public abstract class EndGamePane extends StandardPane {

  private static final double ADJUST_DY_FOR_BOTTOM = -400;
  private static final int CUSTOM_TEXT_SIZE = TEXT_SCALE_SIZE_SMALL + 10;

  private RestartGameController restartHandler;

  /**
   * Creates a new EndGamePane with the given message.
   * 
   * @param message
   *          message to be shown
   */
  public EndGamePane(String message) {
    try {
      if (HighScores.getInstance().getHighScore() < HighScores.getInstance().getScore()) {
        addMidText("NEW HIGH SCORE!", CUSTOM_TEXT_SIZE, ADJUST_DY_FOR_BOTTOM);
      }
    } catch (FileNotFoundException e1) {
      addMidText("NO HIGH SCORES FILE WAS FOUND, SO A NEW ONE WAS CREATED", TEXT_SCALE_SIZE_SMALL,
          ADJUST_DY_FOR_BOTTOM);
      Logger.getInstance().write("No high scores file found", e1.getMessage());
    }
    addMidText(message, TEXT_SCALE_SIZE_BIG, Options.getGlobalHeight()
        / SCALING_FACTOR_TO_UNDERNEATH_MIDDLE);
    String highScores;
    try {
      HighScores.getInstance().writeHighScore();
      highScores = HighScores.getInstance().makeHighScoreString();
    } catch (IOException e) {
      highScores = "High scores failed to load";
      Logger.getInstance().write("IOException ", e.getMessage());
      e.printStackTrace();
    }
    addMidText(highScores, TEXT_SCALE_SIZE_SMALL, Options.getGlobalHeight()
        / SCALING_FACTOR_TO_LITTLE_BELOW_MIDDLE);
    addMidText("Press R to restart", TEXT_SCALE_SIZE_SMALL, Options.getGlobalHeight()
        / SCALING_FACTOR_TO_ABOVE_MIDDLE);

    this.restartHandler = new RestartGameController();
    MainGui.getInstance().getCurrentScene().addEventHandler(KeyEvent.ANY, this.restartHandler);
  }

  @Override
  public void stop() {
    MainGui.getInstance().getCurrentScene().removeEventHandler(KeyEvent.ANY, this.restartHandler);
  }

}

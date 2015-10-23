package lasersharksgui.panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import lasersharks.Options;

/**
 * 
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class OptionsPane extends StandardPane {

  private static final int PAUSE_TEXT_SIZE_UP = 6;
  private static final double PAUSE_TEXT_POSITION_UP = 2.5;

  private static final int PAUSE_TEXT_SIZE_BELOW = 4;
  private static final double PAUSE_TEXT_POSITION_BELOW = 3.0;

  // variables for the mute button
  private static final int BUTTON_HEIGHT = 24;
  private static final int BUTTON_WIDTH = 36;
  private static final double MUTE_BUTTON_HEIGHT_SCALE = 4.0;
  private static final double MUTE_BUTTON_WIDTH_SCALE = 4.0;
  private static final double MUTE_SCALE_SIZE = 3.0;

  /**
   * Constructor to initialize the OptionsPane.
   */

  public OptionsPane() {
    this.setBackground(null);
    addMidText("game Paused", PAUSE_TEXT_SIZE_UP,
        Options.getGlobalHeight() / PAUSE_TEXT_POSITION_UP);
    addMidText("press P to resume", PAUSE_TEXT_SIZE_BELOW,
        Options.getGlobalHeight() / PAUSE_TEXT_POSITION_BELOW);
    getChildren().add(muteButton(Options.getGlobalWidth() / MUTE_BUTTON_WIDTH_SCALE,
        Options.getGlobalHeight() / MUTE_BUTTON_HEIGHT_SCALE, MUTE_SCALE_SIZE));
  }

  /**
   * Method for adding the mute button to the scene.
   *
   * @param xPos
   *          the x position of the top left corner.
   * @param yPos
   *          the y position of the top left corner.
   * @param size
   *          the size of the button(width and height).
   * @return a new mute button based on the parameters.
   */
  public Button muteButton(double xPos, double yPos, double size) {
    muteButtonImage.setFitHeight(BUTTON_HEIGHT * size);
    muteButtonImage.setFitWidth(BUTTON_WIDTH * size);
    unmuteButtonImage.setFitHeight(BUTTON_HEIGHT * size);
    unmuteButtonImage.setFitWidth(BUTTON_WIDTH * size);
    muteButton = new Button();
    muteButton.setGraphic(muteButtonImage);
    muteButton.setTranslateX(xPos);
    muteButton.setTranslateY(yPos);

    muteButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        muteSound();
      }
    });
    return muteButton;
  }

  @Override
  public void stop() {
    // empty
  }
}

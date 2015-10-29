package lasersharksgui.panes;

import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.controllers.AudioController;

/**
 * The pane representing the optionPane.
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
public class OptionsPane extends AbstractStandardPane {

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

  private static final double SLIDER_X_SCALE = 0.6;
  private static final double SLIDER_Y_SCALE = 0.3;
  private static final int SLIDER_TEXT_SIZE = 2;
  private static final double SLIDER_Y_SCALE_TEXT = 0.27;
  private static final double SLIDER_X_SCALE_TEXT = 0.62;
  private static final double SLIDER_MIN = 0.0;
  private static final double SLIDER_MAX = 100.0;
  private static final double SLIDER_SIZE_X = 3.0;
  private static final double SLIDER_SIZE_Y = 3.0;
  private static final ImageView MUTE_IMAGE = new ImageView("mutesound.png");
  private static final ImageView UNMUTE_IMAGE = new ImageView("unmutesound.png");
  // audio variables
  private Button muteButton;

  /**
   * Constructor to initialize the OptionsPane.
   */
  public OptionsPane() {
    super();
    this.setBackground(null);
    addMidText("game Paused", PAUSE_TEXT_SIZE_UP,
        Options.getGlobalHeight() / PAUSE_TEXT_POSITION_UP);
    addMidText("press P to resume", PAUSE_TEXT_SIZE_BELOW,
        Options.getGlobalHeight() / PAUSE_TEXT_POSITION_BELOW);
    addText("Music Volume",
        SLIDER_TEXT_SIZE,
        new Position(
            Options.getGlobalWidth() * SLIDER_X_SCALE_TEXT,
            Options.getGlobalHeight() * SLIDER_Y_SCALE_TEXT
        )
    );
    getChildren().add(
        volumeSlider(new Position(
                Options.getGlobalWidth() * SLIDER_X_SCALE,
                Options.getGlobalHeight() * SLIDER_Y_SCALE
            ),
            SLIDER_SIZE_X,
            SLIDER_SIZE_Y));
    getChildren().add(
        muteButton(
            Options.getGlobalWidth() / MUTE_BUTTON_WIDTH_SCALE,
            Options.getGlobalHeight() / MUTE_BUTTON_HEIGHT_SCALE,
            MUTE_SCALE_SIZE));
  }

  /**
   * Method for adding the mute button to the scene.
   *
   * @param xPos the x position of the top left corner.
   * @param yPos the y position of the top left corner.
   * @param size the size of the button(width and height).
   * @return a new mute button based on the parameters.
   */
  private Button muteButton(double xPos, double yPos, double size) {
    MUTE_IMAGE.setFitHeight(BUTTON_HEIGHT * size);
    MUTE_IMAGE.setFitWidth(BUTTON_WIDTH * size);
    UNMUTE_IMAGE.setFitHeight(BUTTON_HEIGHT * size);
    UNMUTE_IMAGE.setFitWidth(BUTTON_WIDTH * size);
    muteButton = new Button();
    muteButton.setGraphic(UNMUTE_IMAGE);
    muteButton.setTranslateX(xPos);
    muteButton.setTranslateY(yPos);

    muteButton.setOnAction(event -> muteSound());
    return muteButton;
  }

  /**
   * method for muting and un-muting the music of the game.
   */
  private void muteSound() {
    if (!Options.getInstance().isMutedMusic()) {
      AudioController.getInstance().muteAll();
      muteButton.setGraphic(UNMUTE_IMAGE);
      Logger.getInstance().write("Sound muted", "Mute sound button pressed");
    } else {
      AudioController.getInstance().unMuteAll();
      muteButton.setGraphic(MUTE_IMAGE);
      Logger.getInstance().write("Sound un-muted", "Mute sound button pressed");
    }
  }

  @Override
  public void stop() {
    // empty
  }

  /**
   * Adds a volumeSlider to the screen.
   *
   * @param pos   Position of the volumeSlider.
   * @param xSize the size of the slider horizontal.
   * @param ySize the size of the slider vertical.
   * @return a volumeSlider linked to the master volume.
   */
  private Slider volumeSlider(Position pos, double xSize, double ySize) {
    Slider volumeSlider = new Slider(
        SLIDER_MIN,
        SLIDER_MAX,
        Options.getInstance().getMasterVolume() * SLIDER_MAX);
    volumeSlider.setLayoutX(pos.getPosX());
    volumeSlider.setLayoutY(pos.getPosY());
    volumeSlider.setScaleX(xSize);
    volumeSlider.setScaleY(ySize);
    volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      AudioController.getInstance().adjustMasterVolume(newValue.doubleValue() / SLIDER_MAX);
      Logger.getInstance().write("Master volume changed", "newValue: " + newValue.intValue() + ")");
    });
    return volumeSlider;
  }
}

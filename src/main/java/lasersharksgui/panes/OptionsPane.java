package lasersharksgui.panes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.DragEvent;
import lasersharks.Logger;
import lasersharks.Options;
import lasersharks.Position;
import lasersharks.controllers.AudioController;
import javafx.scene.text.Text;

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
  
  private static final double SLIDER_X_SCALE = 0.6;
  private static final double SLIDER_Y_SCALE = 0.3;
  private static final int SLIDER_TEXT_SIZE = 2;
  private static final double SLIDER_Y_SCALE_TEXT = 0.27;
  private static final double SLIDER_X_SCALE_TEXT = 0.62;
  private static final double SLIDER_MIN = 0.0;
  private static final double SLIDER_MAX = 100.0;
  private static final double SLIDER_SIZE_X = 3.0;
  private static final double SLIDER_SIZE_Y = 3.0;

  /**
   * Constructor to initialize the OptionsPane.
   */
  public OptionsPane() {
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
   * @param xPos
   *          the x position of the top left corner.
   * @param yPos
   *          the y position of the top left corner.
   * @param size
   *          the size of the button(width and height).
   * @return a new mute button based on the parameters.
   */
  private Button muteButton(double xPos, double yPos, double size) {
    muteButtonImage.setFitHeight(BUTTON_HEIGHT * size);
    muteButtonImage.setFitWidth(BUTTON_WIDTH * size);
    unmuteButtonImage.setFitHeight(BUTTON_HEIGHT * size);
    unmuteButtonImage.setFitWidth(BUTTON_WIDTH * size);
    muteButton = new Button();
    muteButton.setGraphic(unmuteButtonImage);
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

  /**
   * method for muting and unmuting the music of the game.
   */
  private void muteSound() {
    if (!Options.getInstance().isMutedMusic()) {
      AudioController.getInstance().muteAll();
      muteButton.setGraphic(muteButtonImage);
      Logger.getInstance().write("Sound muted", "Mute sound button pressed");
    } else {
      AudioController.getInstance().unmuteAll();
      muteButton.setGraphic(unmuteButtonImage);
      Logger.getInstance().write("Sound unmuted", "Mute sound button pressed");
    }
  }
  
  @Override
  public void stop() {
    // empty
  }
  
  /**
   * Adds a volumeSlider to the screen.
   * @param pos Position of the volumeSlider.
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

package laserSharksGUI;

import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * @author michiel, daan
 *
 */
public class FXGUI extends Application {

  Label lb_text;
  private static final int KEYBOARD_MOVEMENT_DELTA = 5;
  private int size = 40;
  private double growSize = 1;

  /**
   * @param args
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Set the complete stage for the view in the GUI, this method will be split among smaller methods
   * later
   */
  @Override
  public void start(Stage stage) throws Exception {

    Image imageright = new Image("SharkToTheRight.gif", true);
    Image imageleft = new Image("SharkToTheLeft.gif", true);
    ImageView image = new ImageView("SharkToTheRight.gif");
    image.setFitHeight(size);
    image.setFitWidth(2 * size);

    Pane pane = new Pane();
    pane.getChildren().add(image);
    image.setImage(imageright);

    final Scene scene = new Scene(pane, 1920, 1080, Color.BLUE);

    BackgroundImage myBI = new BackgroundImage(new Image("better background.jpg", 1920, 1080,
        false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
        BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    pane.setBackground(new Background(myBI));

    final double rectangleHSpeed = 500; // pixels per second
    final double rectangleVSpeed = 500;
    final double minX = 0;
    final double maxX = 1920;
    final double minY = 0;
    final double maxY = 1080;

    final LinkedList<KeyCode> keyStack = new LinkedList<>();
    scene.setOnKeyPressed(event -> {
      KeyCode code = event.getCode();
      if (!keyStack.contains(code)) {
        keyStack.push(code);
      }
    });

    scene.setOnKeyReleased(event -> keyStack.remove(event.getCode()));

    final LongProperty lastUpdateTime = new SimpleLongProperty();
    final AnimationTimer rectangleAnimation = new AnimationTimer() {
      @Override
      public void handle(long timestamp) {
        if (!keyStack.isEmpty() && lastUpdateTime.get() > 0) {
          final double elapsedSeconds = (timestamp - lastUpdateTime.get()) / 1_000_000_000.0;
          double deltaX = 0;
          double deltaY = 0;
          switch (keyStack.peek()) {
            case UP:
              deltaY = -rectangleVSpeed * elapsedSeconds;
              break;
            case DOWN:
              deltaY = rectangleVSpeed * elapsedSeconds;
              break;
            case LEFT:
              deltaX = -rectangleHSpeed * elapsedSeconds;
              image.setImage(imageleft);
              break;
            case RIGHT:
              deltaX = rectangleHSpeed * elapsedSeconds;
              image.setImage(imageright);
              break;
            case H:
              // image.setImage(imageright1);
              growSize = growSize * 1.01;
              image.setScaleX(growSize);
              image.setScaleY(growSize);
            default:
              break;
          }
          double oldXr = image.getTranslateX();
          double oldYr = image.getTranslateY();
          image.setTranslateX(clamp(oldXr + deltaX, minX, maxX));
          image.setTranslateY(clamp(oldYr + deltaY, minY, maxY));

        }
        lastUpdateTime.set(timestamp);
      }
    };
    rectangleAnimation.start();

    stage.setScene(scene);
    stage.show();

  }

  /**
   * Clamps a value between a minimum double and maximum double value.
   * @param value
   * @param min
   * @param max
   * @return a value between a minimum double and maximum double
   */
  private double clamp(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }

}
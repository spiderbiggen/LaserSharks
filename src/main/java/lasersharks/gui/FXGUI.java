package lasersharks.gui;

import java.text.DecimalFormat;
import java.util.LinkedList;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author michiel, daan
 *
 */
@SuppressWarnings("restriction")
public class FXGUI extends Application {

  private double size = 80;
  private final double growth = 1.01;

  private final double rectangleHSpeed = 500; // pixels per second
  private final double rectangleVSpeed = 500;
  private final double minX = 0;
  private final double maxX = 1920;
  private final double minY = 0;
  private final double maxY = 1080;

  private final DecimalFormat df = new DecimalFormat("#.##");

  /**
   * @param args
   *          parameters to influence the startup of this game
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Set the complete stage for the view in the GUI, this method will be split among smaller methods
   * later.
   */
  @Override
  public void start(Stage stage) throws Exception {

    ImageView image = new ImageView("SharkToTheRight.gif");
    image.setFitHeight(size);
    image.setFitWidth(2 * size);
    String fps = "FPS: ";
    Text text = new Text(fps + "ofzo");
    text.setFont(new Font(20));
    text.setX(maxX - text.getBoundsInParent().getWidth() - 20);
    text.setY(20);

    Pane pane = new Pane();
    pane.getChildren().add(image);
    pane.getChildren().add(text);

    final Scene scene = new Scene(pane, maxX, maxY, Color.BLUE);

    BackgroundImage myBI = new BackgroundImage(
        new Image("background2.jpg", maxX, maxY, true, false), BackgroundRepeat.REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

    pane.setBackground(new Background(myBI));

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
              image.setScaleX(-1);
              break;
            case RIGHT:
              deltaX = rectangleHSpeed * elapsedSeconds;
              image.setScaleX(1);
              break;
            case H:
              size = size * growth;
              image.setFitHeight(size);
              image.setFitWidth(2 * size);
              break;
            default:
              break;
          }
          double oldXr = image.getTranslateX();
          double oldYr = image.getTranslateY();
          Bounds bounds = image.getBoundsInParent();
          image.setTranslateX(clamp(oldXr + deltaX, minX, maxX - bounds.getWidth()));
          image.setTranslateY(clamp(oldYr + deltaY, minY, maxY - bounds.getHeight()));

        }

        final double fpsnum = 1_000_000_000.0 / (timestamp - lastUpdateTime.get());

        text.setText(fps + df.format(fpsnum));
        lastUpdateTime.set(timestamp);
      }
    };
    rectangleAnimation.start();
    stage.setScene(scene);
    stage.show();

  }

  /**
   * Clamps a value between a minimum double and maximum double value.
   * 
   * @param value
   * @param min
   * @param max
   * @return a value between a minimum double and maximum double
   */
  private double clamp(double value, double min, double max) {
    return Math.max(min, Math.min(max, value));
  }

}
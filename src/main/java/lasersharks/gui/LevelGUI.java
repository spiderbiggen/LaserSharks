package lasersharks.gui;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;

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
import lasersharks.*;

/**
 * @author michiel, daan
 *
 */
@SuppressWarnings("restriction")
public class LevelGUI extends Application {
  private static final double maxX = 1920;
  private static final double maxY = 1080;
  private static final Color backColor = Color.BLUE;

  private Pane pane;
  private Scene scene;

  /**
   * @param args
   *          parameters to influence the startup of this game
   */
  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage stage) {
    pane = new Pane();
    scene = new Scene(pane, maxX, maxY, backColor);
    addElements(pane);
    stage.setScene(scene);
    stage.show();
  }

  public Pane addElements(Pane pane) {
    // TODO: add a background
    BackgroundImage myBI = new BackgroundImage(
        new Image("background.jpg", maxX, maxY, true, false), BackgroundRepeat.REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBI));
    // TODO: add the fps counter
    pane.getChildren().add(fpsCounter());
    return pane;
  }

  public Text fpsCounter() {

    DecimalFormat df = new DecimalFormat("#.##");
    String fps = "FPS: ";
    Text text = new Text(fps + "ofzo");
    text.setFont(new Font(20));
    text.setX(maxX - text.getBoundsInParent().getWidth() - 20);
    text.setY(20);

    return text;
  }
  
  public void addFishList(List<Fish> list) {
    for (int i = 0; i< list.size(); i++){
      this.pane.getChildren().add(fishImage(list.get(i)));
    }
  }
  
  public ImageView fishImage(Fish fish) {
    Position position = fish.getPosition();
    double size = fish.getSize();
    Direction dir = fish.getDirection();

    ImageView image;
    if (fish instanceof LaserShark) {
      image = new ImageView("LaserRight.gif");
    } else {
      image = new ImageView("FishBot.gif");
    }
    //flip the image according to the direction.
    if (dir != Direction.None) {
      image.setScaleX(dir.getDeltaX());
    }
    image.setFitHeight(size);
    image.setFitWidth(size);
    
    image.setX(position.getPosX());
    image.setY(position.getPosY());
    
    return image;
  }
}
package lasersharks.gui;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lasersharks.Direction;
import lasersharks.Fish;
import lasersharks.Game;
import lasersharks.LaserShark;
import lasersharks.Position;
import lasersharks.ScreenController;

/**
 * This class represents the gui of our application.
 * 
 * @author michiel, daan
 *
 */

@SuppressWarnings("restriction")
public class LevelGUI extends Application {

  /**
   * the x resolution of the screen.
   */
  private static final double XRES = 1920;
  /**
   * the y resolution of the screen.
   */
  private static final double YRES = 1080;
  /**
   * The background color of the gui.
   */
  private static final Color BACKCOLOUR = Color.BLUE;
  private static final int TEXT_SCALE_SIZE = 10;
  private static final String MUSIC_FILENAME = "src/main/resources/music.mp3";
  private ScreenController screenController;
  private Pane pane;
  private StackPane stackPane;
  private Scene playScene;
  private Scene winScene;
  private Scene loseScene;
  private boolean choosePlayScene = true;
  private boolean chooseWinScene = false;
  private boolean chooseLoseScene = false;
  private Stage stage;
  private AnimationTimer animation;
  private Media media;
  private MediaPlayer mediaPlayer;
  private ImageView sharkImage;

  private long time = 0;
  private final double timeToMilis = 1_000_000;

  /**
   * @return the screenController.
   */
  public ScreenController getScreenController() {
    return screenController;
  }

  /**
   * @param screenController
   *          the screenController to set.
   */
  public void setScreenController(ScreenController screenController) {
    this.screenController = screenController;
  }

  /**
   * gets the scene of the gui.
   * 
   * @return the scene
   */
  public Scene getScene() {
    return playScene;
  }

  /**
   * sets the scene.
   * 
   * @param scene
   *          the scene
   */
  public void setScene(Scene scene) {
    this.playScene = scene;
  }

  /**
   * @param args
   *          parameters to influence the startup of this game.
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * This function is called by the application. This sets up a scene and pane.
   * 
   * @param stage
   *          the stage the scene is set to.
   */
  @Override
  public void start(Stage stage) {
    pane = new Pane();
    stage.setFullScreen(true);
    playScene = new Scene(pane, stage.getHeight(), stage.getWidth(), BACKCOLOUR);
    winScene = makeMessageScene(stage, "You won!");
    addElements();
    loseScene = makeMessageScene(stage, "Game Over!");
    addElements();

    this.stage = stage;
    chooseScene();

    stage.show();
    Position.setHeightPanel((int) Math.round(stage.getHeight()));
    Position.setWidthPanel((int) Math.round(stage.getWidth()));
    Game game = new Game();
    game.launch(this);
    startMusic(MUSIC_FILENAME);
  }

  /**
   * Function for start of drawing fish on screen.
   */
  public void startGame() {
    animation = new AnimationTimer() {

      @Override
      public void handle(long now) {
        double frametime = (now - time) / timeToMilis;
        final double milis = 1000;
        showFishList(screenController.getNextFrameInfo(milis / frametime));
        showShark(screenController.getShark());
        time = now;
      }

    };
    animation.start();
  }

  /**
   * This function makes a scene with a message to display.
   * 
   * @param stage
   *          the stage the scene is set to.
   * @param message
   *          the message to display
   * 
   * @return the scene of the end screen
   */
  public Scene makeMessageScene(Stage stage, String message) {

    stackPane = new StackPane();
    Text winGameText = new Text(message);
    stackPane.getChildren().add(winGameText);
    winGameText.setScaleX(TEXT_SCALE_SIZE);
    winGameText.setScaleY(TEXT_SCALE_SIZE);
    Scene escene = new Scene(stackPane, stage.getHeight(), stage.getWidth(), BACKCOLOUR);

    return escene;
  }

  /**
   * Method to choose which scene is used.
   */
  public void chooseScene() {
    if (choosePlayScene) {
      stage.setScene(playScene);

    } else if (chooseWinScene) {
      animation.stop();
      stage.setScene(winScene);
      stage.setFullScreen(true);
      stage.show();

    } else if (chooseLoseScene) {
      animation.stop();
      stage.setScene(loseScene);
      stage.setFullScreen(true);
      stage.show();
    }
  }

  /**
   * Add some key elements to the pane. This includes: Background.
   * 
   */
  public void addElements() {
    BackgroundImage myBI = new BackgroundImage(
        new Image("somber sea floor.jpg", XRES, YRES, true, false), BackgroundRepeat.REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBI));
    stackPane.setBackground(new Background(myBI));
  }

  /**
   * This method set the end scene true and the playscene false.
   */
  public void setWinSceneTrue() {
    chooseWinScene = true;
    choosePlayScene = false;
    chooseLoseScene = false;
  }

  /**
   * This method sets only the lose scene true.
   */
  public void setLoseSceneTrue() {
    chooseWinScene = false;
    choosePlayScene = false;
    chooseLoseScene = true;
  }

  /**
   * This function removes all the ImageView objects. This is used to remove all the fish images on
   * the screen.
   */
  public void clearPaneOfImageView() {
    ObservableList<Node> list = pane.getChildren();
    list.removeAll(list.stream().filter(v -> v instanceof ImageView || v instanceof Rectangle)
        .collect(Collectors.toList()));
  }

  /**
   * This method will display the shark on the screen.
   * 
   * @param shark
   *          the shark to display
   */
  public void showShark(LaserShark shark) {
    if (sharkImage == null) {
      sharkImage = new ImageView(shark.getImageResource());
    }
    Position position = shark.getPosition();
    double size = shark.getSize();
    Direction dir = shark.getDirection();

    // flip the image according to the direction.
    if (dir.getDeltaX() != 0) {
      sharkImage.setScaleX(dir.getDeltaX());
    }
    sharkImage.setFitHeight(size);
    sharkImage.setFitWidth(size * shark.getWidthScale());

    sharkImage.setX(position.getPosX());
    sharkImage.setY(position.getPosY());
    this.pane.getChildren().add(sharkImage);
  }

  /**
   * This method displays a list<Fish> on the scene of the gui.
   * 
   * @param list
   *          the list of fish that needs to be displayed.
   */
  public void showFishList(List<Fish> list) {
    clearPaneOfImageView();
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).isAlive()) {
        this.pane.getChildren().add(fishImage(list.get(i)));
        Rectangle hitBox = list.get(i).makeHitbox();
        hitBox.setOpacity(0); // comment this line if you want to see the hitboxes as black boxes
        this.pane.getChildren().add(hitBox);
      }
    }
  }

  /**
   * an image object of a fish.
   * 
   * @param fish
   *          the fish to display.
   * @return an imageview of the fish.
   */
  public ImageView fishImage(Fish fish) {
    Position position = fish.getPosition();
    double size = fish.getSize();
    Direction dir = fish.getDirection();

    ImageView image;
    image = new ImageView(fish.getImageResource());
    // flip the image according to the direction.
    if (dir.getDeltaX() != 0) {
      image.setScaleX(dir.getDeltaX());
    }
    image.setFitHeight(size);
    image.setFitWidth(size * fish.getWidthScale());

    image.setX(position.getPosX());
    image.setY(position.getPosY());

    return image;
  }

  /**
   * plays a music track on autoloop. Mp3 is an acceptable format.
   * 
   * @param path
   *          the path to the music track.
   */
  public void startMusic(String path) {
    media = new Media(new File(path).toURI().toString());
    mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }
}
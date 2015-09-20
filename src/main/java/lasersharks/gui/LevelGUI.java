package lasersharks.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
import lasersharks.Highscores;
import lasersharks.LaserShark;
import lasersharks.Level;
import lasersharks.Logger;
import lasersharks.Options;
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
  private static final float HALF_SCALE = 0.5f;
  private static final String MUSIC_FILENAME = "src/main/resources/music.mp3";
  private static LevelGUI instance;
  private static int score = 0;
  private ScreenController screenController;
  private Pane pane;
  private Pane winPane;
  private Pane losePane;
  private StackPane stackPane;
  private Scene playScene;
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
   * @throws IOException
   */
  @Override
  public void start(Stage stage) throws IOException {
    Logger.getInstance().write("Starting game", "Starting");
    LevelGUI.instance = this;
    pane = new Pane();
    stackPane = new StackPane();
    stage.setFullScreen(true);

    addElements(pane);

    stackPane.getChildren().add(pane);
    
    playScene = new Scene(stackPane, Options.getGlobalWidth(), Options.getGlobalHeight(), BACKCOLOUR);

    this.stage = stage;
    chooseScene();

    stage.show();
    Logger.getInstance().write("Starting Music", "Starting");
    Level level = new Level(this);
    level.launch();
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
        try {
          showFishList(screenController.getNextFrameInfo(milis / frametime));
        } catch (IOException e) {
          e.printStackTrace();
        }
        showShark(screenController.getShark());
        showScore();
        time = now;
      }

    };
    animation.start();
  }

  /**
   * Displays the score in the upper right corner of the screen.
   */
  public void showScore() {
    Text gameText = new Text("Score: " + score);
    gameText.setX(Position.upperCornerPosition().getPosX());
    gameText.setY(Position.upperCornerPosition().getPosY());
    gameText.setScaleX(TEXT_SCALE_SIZE / 2.5);
    gameText.setScaleY(TEXT_SCALE_SIZE / 2.5);
    this.pane.getChildren().add(gameText);
  }

  /**
   * This function makes a scene with a message to display.
   * 
   * @param message
   *          the message to display
   * @return new scene
   * @throws FileNotFoundException
   *           highscore file not found
   */
  public Pane showMessageScene(String message) throws FileNotFoundException {
    Pane pane = new Pane();
    addElements(pane);

    if (score == Highscores.getHighScore()) {
      Text newHighScore = new Text("NEW HIGHSCORE!");
      newHighScore.setScaleX(TEXT_SCALE_SIZE * 1.7);
      newHighScore.setScaleY(TEXT_SCALE_SIZE * 1.7);
      newHighScore.setX(Position.middlePosition().getPosX());
      newHighScore.setY(Position.middlePosition().getPosY() - 420);
      pane.getChildren().add(newHighScore);
    }

    Text gameText = new Text(message);
    gameText.setScaleX(TEXT_SCALE_SIZE);
    gameText.setScaleY(TEXT_SCALE_SIZE);
    gameText.setX(Position.middlePosition().getPosX());
    gameText.setY(Position.middlePosition().getPosY() - 230);
    pane.getChildren().add(gameText);

    Text highScore = new Text(Highscores.makeHighscoreString());
    highScore.setScaleX(TEXT_SCALE_SIZE / 2.5);
    highScore.setScaleY(TEXT_SCALE_SIZE / 2.5);
    highScore.setX(Position.middlePosition().getPosX());
    highScore.setY(Position.middlePosition().getPosY() + 100);
    pane.getChildren().add(highScore);

    return pane;
  }

  /**
   * Method to choose which scene is used.
   * 
   * @throws IOException
   *           highscore file not found
   */
  public void chooseScene() throws IOException {
    if (choosePlayScene) {
      stage.setScene(playScene);

    } else if (chooseWinScene) {
      Highscores.writeHighscore();
      winPane = showMessageScene("You Win!");
      winPane.setOpacity(0.0);
      stackPane.getChildren().add(winPane);
      animation.stop();
      pane.setOpacity(0.0);
      winPane.setOpacity(1.0);

    } else if (chooseLoseScene) {
      Highscores.writeHighscore();
      losePane = showMessageScene("Game Over!");
      losePane.setOpacity(0.0);
      stackPane.getChildren().add(losePane);
      animation.stop();
      pane.setOpacity(0.0);
      losePane.setOpacity(1.0);

    }
  }

  /**
   * Add some key elements to the pane. This includes: Background.
   * 
   * @param pane
   *          the pane to add elements to add elements to
   */

  public void addElements(Pane pane) {
    BackgroundImage myBI = new BackgroundImage(
        new Image("somber sea floor.jpg", XRES, YRES, true, false), BackgroundRepeat.REPEAT,
        BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBI));
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
    list.removeAll(list.stream()
        .filter(v -> v instanceof ImageView || v instanceof Rectangle || v instanceof Text)
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

  /**
   * Returns a singleton of the levelGUI class.
   * 
   * @return singleton instance
   */
  public static LevelGUI getInstance() {
    return LevelGUI.instance;
  }

  /**
   * Returns the stage used to start this gui.
   * 
   * @return current stage
   */
  public Stage getStage() {
    return this.stage;
  }

  /**
   * Get's the current score from this game.
   * 
   * @return the score
   */
  public static int getScore() {
    return score;
  }

  /**
   * Set the score to newScore.
   * 
   * @param newScore
   *          the newScore
   */
  public static void setScore(int newScore) {
    score = newScore;
  }

  /**
   * Increase the current score the player has according to the size of the fish eaten.
   * 
   * @param fish
   *          the fish that is used to calculate the additional score
   */
  public static void increaseScore(Fish fish) {
    if (fish.isAlive()) {
      score = (int) (score + fish.getSize() * HALF_SCALE + Highscores.getFishBonus());
    }

  }

  /**
   * Sets the stage to the new stage.
   * 
   * @param stage
   *          the new stage object
   */
  public void setStage(Stage stage) {
    this.stage = stage;
  }
}
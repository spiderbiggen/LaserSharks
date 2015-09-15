package lasersharks.gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
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
  private static ArrayList<String> list;

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
    LevelGUI.instance = this;
    pane = new Pane();
    stackPane = new StackPane();
    stage.setFullScreen(true);

    addElements(pane);

    winPane = showMessageScene("You Win!");
    winPane.setOpacity(0.0);
    losePane = showMessageScene("Game Over!");
    losePane.setOpacity(0.0);

    stackPane.getChildren().add(pane);
    stackPane.getChildren().add(winPane);
    stackPane.getChildren().add(losePane);

    playScene = new Scene(stackPane, stage.getWidth(), stage.getHeight(), BACKCOLOUR);

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
   */
  public Pane showMessageScene(String message) {
    Pane pane = new Pane();
    addElements(pane);
    Text gameText = new Text(message);
    pane.getChildren().add(gameText);
    gameText.setScaleX(TEXT_SCALE_SIZE);
    gameText.setScaleY(TEXT_SCALE_SIZE);
    gameText.setX(Position.middlePosition().getPosX());
    gameText.setY(Position.middlePosition().getPosY());
    return pane;
  }

  /**
   * Method to choose which scene is used.
   */
  public void chooseScene() {
    if (choosePlayScene) {
      stage.setScene(playScene);

    } else if (chooseWinScene) {
      animation.stop();
      pane.setOpacity(0.0);
      winPane.setOpacity(1.0);

    } else if (chooseLoseScene) {
      animation.stop();
      pane.setOpacity(0.0);
      losePane.setOpacity(1.0);

    }
  }

  /**
   * Add some key elements to the pane. This includes: Background.
   * 
   * @param pane
   *          the pane to add elements to
   * 
   */
  public void addElements(Pane pane) {
    BackgroundImage myBI = new BackgroundImage(new Image("somber sea floor.jpg", XRES, YRES, true,
        false), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
        BackgroundSize.DEFAULT);
    pane.setBackground(new Background(myBI));
  }

  /**
   * This method set the end scene true and the playscene false.
   * 
   * @throws IOException
   */
  public void setWinSceneTrue() throws IOException {
    list = readHighscore();
    writeHighscore();
    chooseWinScene = true;
    choosePlayScene = false;
    chooseLoseScene = false;
  }

  /**
   * This method sets only the lose scene true.
   * 
   * @throws IOException
   */
  public void setLoseSceneTrue() throws IOException {
    list = readHighscore();
    writeHighscore();
    chooseWinScene = false;
    choosePlayScene = false;
    chooseLoseScene = true;
  }

  /**
   * Reads the current highscore list so it can be edited.
   * 
   * @return the current highscore list.
   * @throws FileNotFoundException
   */
  public static ArrayList<String> readHighscore() throws FileNotFoundException {
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(new File("highscores"));
    ArrayList<String> list = new ArrayList<String>();
    while (sc.hasNextLine()) {
      list.add(sc.nextLine());
    }

    return list;

  }

  /**
   * Method for writing the highscores. The highscores are saved in highscores.txt .
   * 
   * @throws IOException
   */
  public static void writeHighscore() throws IOException {
    for (int i = 0; i < list.size(); i++) {
      if (score >= Integer.parseInt(list.get(i).substring(3))) {
        list.remove(i);
        list.add(i, i + ". " + score);
        break;
      }

    }
    fixHighscoreCount(list);

    try (FileWriter fw = new FileWriter("highscores")) {
      for (int i = 0; i < list.size(); i++) {
        if (i < 4) {
          fw.write(list.get(i) + System.lineSeparator());
        } else {
          fw.write(list.get(i));
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fixes the still erroneous list after the new score has been inserted in the writeHighscore()
   * method. An example would be 1. 500 2. 250 2. 250 3. 100 4. 50.
   * 
   * @param list
   * @return the correct highscore list
   */
  public static ArrayList<String> fixHighscoreCount(ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      String newEntry = list.get(i);
      list.remove(i);

      newEntry = (i + 1) + ". " + newEntry.substring(3);
      list.add(i, newEntry);
    }

    return list;
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

  public int getScore() {
    return score;
  }

  /**
   * Increase the current score the player has according to the size of the fish eaten.
   * 
   */
  public static void increaseScore(Fish fish) {
    if (fish.isAlive()) {
      score = (int) (score + fish.getSize() * HALF_SCALE + 20);
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
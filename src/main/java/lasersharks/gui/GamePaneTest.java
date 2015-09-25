package lasersharks.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import lasersharks.Direction;
import lasersharks.LaserShark;
import lasersharks.Position;
import lasersharks.Swimmer;
import lasersharks.enemies.Enemy1;

@SuppressWarnings("restriction")
public class GamePaneTest {

  private GamePane gamePane;

  @Before
  public void setUp() throws Exception {
    MainGui.launch(MainGui.class);
    ((GamePane) MainGui.getInstance().getCurrentPane()).stopGame();
    gamePane = (GamePane) MainGui.getInstance().getCurrentPane();

  }
  
  @After
  public void tearDown() throws Exception {
    MainGui.getInstance().stop();
  }

  @Test
  public void testGamePane() {
    fail("Not yet implemented");
  }

  @Test
  public void testStartGame() {
    fail("Not yet implemented");
  }

  @Test
  public void testStopGame() {
    fail("Not yet implemented");
  }

  @Test
  public void testResumeGame() {
    fail("Not yet implemented");
  }

  @Test
  public void testShowScore() {
    fail("Not yet implemented");
  }

  @Test
  public void testClearPaneOfImageView() {
    Text text = new Text("testText");
    gamePane.getChildren().add(text);
    gamePane.getChildren().add(text);
    gamePane.getChildren().add(text);
    gamePane.getChildren().add(text);
    gamePane.getChildren().add(text);
    gamePane.clearPaneOfImageView();
    assertTrue(gamePane.getChildren().stream()
        .filter(v -> v instanceof ImageView || v instanceof Rectangle || v instanceof Text)
        .collect(Collectors.toList()).size() == 0);
  }

  @Test
  public void testShowShark() {
    Position position = new Position(0, 0);
    final float size = 50;
    Direction dir = Direction.East;

    LaserShark shark = new LaserShark(position, size, 0d, dir);

    ImageView sharkImage = new ImageView(shark.getImageResource());

    // flip the image according to the direction.
    sharkImage.setScaleX(dir.getDeltaX());
    sharkImage.setFitHeight(size);
    sharkImage.setFitWidth(size * shark.getWidthScale());

    sharkImage.setX(position.getPosX());
    sharkImage.setY(position.getPosY());

    gamePane.showShark(shark);
    assertEquals(sharkImage, gamePane.getChildren().get(gamePane.getChildren().size() - 1));
  }

  @Test
  public void testShowFishList() {
    fail("Not yet implemented");
  }

  @Test
  public void testFishImage() {
    Position position = new Position(0, 0);
    final float size = 50;
    Direction dir = Direction.East;

    Swimmer swimmer = new Enemy1(position, size, 0d, dir);

    ImageView image;
    image = new ImageView(swimmer.getImageResource());
    // flip the image according to the direction.
    image.setScaleX(dir.getDeltaX());
    image.setFitHeight(size);
    image.setFitWidth(size * swimmer.getWidthScale());

    image.setX(position.getPosX());
    image.setY(position.getPosY());

    assertEquals(image, gamePane.fishImage(swimmer));
  }

  @Test
  public void testGetScreenController() {
    fail("Not yet implemented");
  }

  @Test
  public void testSetScreenController() {
    fail("Not yet implemented");
  }

}

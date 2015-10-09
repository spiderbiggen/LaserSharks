package lasersharks;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.scene.Scene;
import lasersharksgui.GamePane;
import lasersharksgui.MainGui;

@SuppressWarnings("restriction")
public class ScreenControllerTest {

  private GamePane currentPane;
  private Scene scene;
  private ScreenController screenCon;
  private LaserShark shark;
  private FishController fishCon;
  private MainGui mainGui;
  private static final float ABOVE_WIN_SIZE = 1001.0f;
  private static final float UNDER_WIN_SIZE = 999.0f;

  @Before
  public void setUp() {
    currentPane = Mockito.mock(GamePane.class);
    screenCon = Mockito.mock(ScreenController.class);
    mainGui = Mockito.mock(MainGui.class);
    scene = Mockito.mock(Scene.class);
    fishCon = Mockito.mock(FishController.class);
    shark = Mockito.mock(LaserShark.class);
    Mockito.when(screenCon.getShark()).thenReturn(shark);
    Mockito.when(shark.isAlive()).thenReturn(true);
    Mockito.when(fishCon.getShark()).thenReturn(shark);
    screenCon = new ScreenController(currentPane);
  }

  @Test
  public void testConstructor() {
    Mockito.verify(currentPane).setScreenController(screenCon);
  }

  @Test
  public void testStartGame() {
    screenCon.start();
    Mockito.verify(currentPane).startGame();
  }

  @Test
  public void testGetShark() {
    screenCon.getShark();
    Mockito.verify(fishCon).getShark();
  }

  @Test
  public void testgetGlobalScene() {
    screenCon = Mockito.mock(ScreenController.class);
    screenCon.getGlobalScene();
    Mockito.verify(mainGui).getCurrentScene();
  }

}

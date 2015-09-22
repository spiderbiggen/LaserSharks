package lasersharks;

import java.io.IOException;
import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.scene.Scene;
import lasersharks.gui.LevelGUI;

/**
 * This is the test class for the screencontroller.
 * 
 * @author sytze
 *
 */
@SuppressWarnings("restriction")
public class ScreenControllerTest {

  private LevelGUI gui;
  private Level level;
  private Scene scene;
  private ScreenController screenCon;
  private LaserShark shark;
  private FishController fishCon;
  private static final float ABOVE_WIN_SIZE = 1001.0f;
  private static final float UNDER_WIN_SIZE = 999.0f;

  /**
   * In this method we setup all mockups and construct the screencontroller.
   * 
   * @throws Exception
   */
  @Before
  public void setUp() {
    gui = Mockito.mock(LevelGUI.class);
    level = Mockito.mock(Level.class);
    scene = Mockito.mock(Scene.class);
    fishCon = Mockito.mock(FishController.class);
    shark = Mockito.mock(LaserShark.class);

    Mockito.when(gui.getScene()).thenReturn(scene);
    Mockito.when(level.getShark()).thenReturn(shark);
    Mockito.when(level.getFishCon()).thenReturn(fishCon);
    Mockito.when(shark.isAlive()).thenReturn(true);
    Mockito.when(fishCon.getShark()).thenReturn(shark);
    Mockito.when(level.getNextFrameInfo(1)).thenReturn(new LinkedList<Fish>());
    screenCon = new ScreenController(level, gui);
  }

  /**
   * We test if the gui and the screencontroller are linked.
   */
  @Test
  public void testConstructor() {
    Mockito.verify(gui).setScreenController(screenCon);
  }

  /**
   * test getNextFrameInfo() with the shark not having the size needed to win.
   * @throws IOException 
   */
  @Test
  public void testGetNextFrameInfoNoWin() throws IOException {
    Mockito.when(shark.getSize()).thenReturn(UNDER_WIN_SIZE);
    screenCon.getNextFrameInfo(1);
  }

  /**
   * test getNextFrameInfo() with the shark having the size needed to win.
   * @throws IOException 
   */
  @Test
  public void testGetNextFrameInfoWin() throws IOException {
    Mockito.when(shark.getSize()).thenReturn(ABOVE_WIN_SIZE);
    screenCon.getNextFrameInfo(1);
    Mockito.verify(gui).setWinSceneTrue();
    Mockito.verify(gui).chooseScene();
  }

  /**
   * Tests the start().
   */
  @Test
  public void testStartGame() {
    screenCon.start();
    Mockito.verify(gui).startGame();
  }

  /**
   * test for getScene().
   */
  @Test
  public void testGetScene() {
    screenCon.getScene();
  }

  /**
   * test for getGui().
   */
  @Test
  public void testGetGui() {
    screenCon.getGui();
  }
  
  /**
   * test for restart().
   */
  @Test
  public void testRestart() {
    screenCon.restart();
    Mockito.verify(gui).stopAnimation();
    Mockito.verify(gui).restartGame();
    Mockito.verify(gui).setPlaySceneTrue();
    Mockito.verify(gui).chooseScene();
    Mockito.verify(shark).setAlive();
    Mockito.verify(fishCon).getShark();
    Mockito.verify(fishCon).clearFish();
    Mockito.verify(level).setBeginShark();
  }

}

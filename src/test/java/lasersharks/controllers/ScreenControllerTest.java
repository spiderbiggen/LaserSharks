package lasersharks.controllers;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javafx.scene.Scene;
import lasersharks.seaobjects.LaserShark;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;

/**
 * Test for {@link ScreenController} class.
 * 
 * @author SEMGroup27
 */

@SuppressWarnings("restriction")
public class ScreenControllerTest {

  private GamePane currentPane;
  @SuppressWarnings("unused")
  private Scene scene;
  private ScreenController screenCon;
  private LaserShark shark;
  private FishController fishCon;
  @SuppressWarnings("unused")
  private MainGui mainGui;

  /**
   * Set up which is executed before every test.
   */
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

  /**
   * Test for the constructer.
   */
  @Test
  public void testConstructor() {
    Mockito.verify(currentPane).setScreenController(screenCon);
  }

  /**
   * Test for the start method.
   */
  @Test
  public void testStartGame() {
    screenCon.start();
    Mockito.verify(currentPane).startGame();
  }

  /**
   * Test for the getShark method.
   */
  @Test
  public void testGetShark() {
    screenCon.getShark();
  }

}

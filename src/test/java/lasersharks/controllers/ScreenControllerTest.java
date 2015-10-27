package lasersharks.controllers;

import javafx.scene.Scene;
import lasersharks.seaobjects.LaserShark;
import lasersharksgui.MainGui;
import lasersharksgui.panes.GamePane;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Test for {@link ScreenController} class.
 * 
 * @author SEMGroup27
 */

@SuppressWarnings("restriction")
public class ScreenControllerTest {

  private GamePane currentPane;
  private ScreenController screenCon;

  /**
   * Set up which is executed before every test.
   */
  @Before
  public void setUp() {
    currentPane = Mockito.mock(GamePane.class);
    screenCon = Mockito.mock(ScreenController.class);
    Mockito.mock(MainGui.class);
    Mockito.mock(Scene.class);
    FishController fishCon = Mockito.mock(FishController.class);
    LaserShark shark = Mockito.mock(LaserShark.class);
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

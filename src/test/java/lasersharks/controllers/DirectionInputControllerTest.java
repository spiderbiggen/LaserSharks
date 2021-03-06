package lasersharks.controllers;

import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import lasersharks.Direction;
import lasersharks.interfaces.DirectionCallback;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Class for testing {@link DirectionInputController}.
 *
 * @author SEMGroup27
 */
@SuppressWarnings("restriction")
@RunWith(Parameterized.class)
public class DirectionInputControllerTest {
  private static final int SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE_RELEASE = 4;
  private static final int SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE = 3;
  private final KeyCode keyCode1;
  private final KeyCode keyCode2;
  private final Direction expectedDirectionAfterKeyPress1;
  private final Direction expectedDirectionAfterKeyPress2;
  private final ArgumentCaptor<Direction> directionCaptor;
  private final Direction defaultDirection = Direction.None;
  private DirectionInputController directionInputController;
  private DirectionCallback callback;

  /**
   * Constructor for parametrized tests.
   *
   * @param keyCode1
   *          Code of firstKey pressed
   * @param keyCode2
   *          Code of secondKey pressed
   * @param expectedDirectionAfterKeyPress1
   *          direction after first key press
   * @param expectedDirectionAfterKeyPress2
   *          direction after second key press
   */
  public DirectionInputControllerTest(final KeyCode keyCode1, final KeyCode keyCode2,
      final Direction expectedDirectionAfterKeyPress1,
      final Direction expectedDirectionAfterKeyPress2) {
    super();
    this.keyCode1 = keyCode1;
    this.keyCode2 = keyCode2;
    this.expectedDirectionAfterKeyPress1 = expectedDirectionAfterKeyPress1;
    this.expectedDirectionAfterKeyPress2 = expectedDirectionAfterKeyPress2;
    this.directionCaptor = ArgumentCaptor.forClass(Direction.class);
  }

  /**
   * input parameters for the tests.
   *
   * @return Array of object containing input and expected data.
   */
  @Parameters
  public static Collection<Object[]> data() {
    return Arrays.asList(
        new Object[][] { { KeyCode.DOWN, KeyCode.LEFT, Direction.South, Direction.SouthWest },
            { KeyCode.DOWN, KeyCode.RIGHT, Direction.South, Direction.SouthEast },
            { KeyCode.UP, KeyCode.LEFT, Direction.North, Direction.NorthWest },
            { KeyCode.UP, KeyCode.RIGHT, Direction.North, Direction.NorthEast },

        });
  }

  /**
   * Setup all classes and mocks for testing.
   */
  @Before
  public void setUp() {
    this.callback = mock(DirectionCallback.class);
    Scene scene = mock(Scene.class);
    ScreenController screenCon = mock(ScreenController.class);
    when(screenCon.getGlobalScene()).thenReturn(scene);
    this.directionInputController = new DirectionInputController(this.callback);
  }

  /**
   * Send that a key is pressed to the Keyboard controller.
   *
   * @param keyCode key witch has been pressed.
   */
  private void pressKey(final KeyCode keyCode) {
    keyAction(keyCode, KeyEvent.KEY_PRESSED);
  }

  /**
   * Send that a key is released to the Keyboard controller.
   *
   * @param keyCode key witch has been pressed.
   */
  private void releaseKey(final KeyCode keyCode) {
    keyAction(keyCode, KeyEvent.KEY_RELEASED);
  }

  /**
   * Internal method for propagating key events.
   *
   * @param keyCode  the KeyCode to propagate.
   * @param keyEvent the KeyEvent that needs to be simulated.
   */
  private void keyAction(final KeyCode keyCode, final EventType<KeyEvent> keyEvent) {
    final KeyEvent k = new KeyEvent(keyEvent, "", "", keyCode, false, false, false, false);
    this.directionInputController.handle(k);
  }

  /**
   <<<<<<< HEAD
   * Check if the DIRECTION is proper after first key press.
   =======
   * Check if the direction is proper after first key press.
   >>>>>>> remotes/origin/master
   */
  @Test
  public void testDirectionAfterFirstKeyPress() {
    pressKey(this.keyCode1);

    verify(callback, times(1)).putDirection(this.directionCaptor.capture());
    assertEquals(this.expectedDirectionAfterKeyPress1, this.directionCaptor.getAllValues().get(0));
  }

  /**
   <<<<<<< HEAD
   * Check if the DIRECTION is proper after second key press.
   =======
   * Check if the direction is proper after second key press.
   >>>>>>> remotes/origin/master
   */
  @Test
  public void testDirectionAfterSecondKeyPress() {
    pressKey(this.keyCode1);
    pressKey(this.keyCode2);
    verify(callback, times(2)).putDirection(this.directionCaptor.capture());
    assertEquals(this.expectedDirectionAfterKeyPress2, this.directionCaptor.getAllValues().get(1));

  }

  /**
   <<<<<<< HEAD
   * Check if the DIRECTION is proper after second key released.
   =======
   * Check if the direction is proper after second key released.
   >>>>>>> remotes/origin/master
   */
  @Test
  public void testDirectionAfterSecondKeyRelease() {
    pressKey(this.keyCode1);
    pressKey(this.keyCode2);
    releaseKey(this.keyCode2);

    verify(callback, times(SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE))
        .putDirection(this.directionCaptor.capture());

    assertEquals(this.expectedDirectionAfterKeyPress1,
        this.directionCaptor.getAllValues().get(SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE - 1));
  }

  /**
   * Check if the direction is proper after both Keys released.
   */
  @Test
  public void testDirectionAfterBothKeysReleased() {
    pressKey(this.keyCode1);
    pressKey(this.keyCode2);
    releaseKey(this.keyCode2);
    releaseKey(this.keyCode1);

    verify(callback, times(SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE_RELEASE))
        .putDirection(this.directionCaptor.capture());

    assertEquals(this.defaultDirection, this.directionCaptor.getAllValues()
        .get(SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE_RELEASE - 1));
  }

  /**
   * Check if the proper is proper after bothKeysReleasedReversed.
   */
  @Test
  public void testDirectionAfterBothKeysReleasedReversed() {
    pressKey(this.keyCode1);
    pressKey(this.keyCode2);
    releaseKey(this.keyCode1);
    releaseKey(this.keyCode2);

    verify(callback, times(SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE_RELEASE))
        .putDirection(this.directionCaptor.capture());

    assertEquals(this.defaultDirection, this.directionCaptor.getAllValues()
        .get(SET_DIRECTION_CALLED_PRESS_PRESS_RELEASE_RELEASE - 1));
  }

  /**
   * Check if the DIRECTION is proper after firstKeyPressedAndReleased.
   */
  @Test
  public void testDirectionAfterFirstKeyPressedAndReleased() {
    pressKey(this.keyCode1);
    releaseKey(this.keyCode1);

    verify(callback, times(2)).putDirection(this.directionCaptor.capture());

    assertEquals(this.defaultDirection, this.directionCaptor.getAllValues().get(1));
  }

  /**
   * Check if the DIRECTION is proper after secondKeyPressedAndReleased.
   */
  @Test
  public void testDirectionAfterSecondKeyPressedAndReleased() {
    pressKey(this.keyCode1);
    releaseKey(this.keyCode1);

    verify(callback, times(2)).putDirection(this.directionCaptor.capture());

    assertEquals(this.defaultDirection, this.directionCaptor.getAllValues().get(1));
  }
}
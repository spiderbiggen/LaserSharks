/**
 * 
 */
package lasersharksgui;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import org.junit.Before;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.google.code.tempusfugit.temporal.Condition;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.Timeout;
import com.google.code.tempusfugit.temporal.WaitFor;

import lasersharks.FishController;
import lasersharks.ScreenController;
import lasersharks.enemies.FishSpawner;

/**
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public class MainGuiTest {

  private GamePane pane;
  private ScreenController screenCon;
  private FishController fishCon;
  private FishSpawner fishSpawner;
  /**
   * Resets the environment for each test.
   * 
   * @throws TimeoutException
   *           timeoutException
   * @throws InterruptedException
   *           InterruptedException
   */
  @Before
  public void setup() throws InterruptedException, TimeoutException {
    FXApp.startApp(new MainGui());
    FXer.getUserWith(FXApp.getScene().getRoot());

    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof GamePane;
      }

    }, Timeout.timeout(Duration.seconds(1L)));
    pane = (GamePane) MainGui.getInstance().getCurrentPane();
    screenCon = pane.getScreenController();
    fishCon = screenCon.getFishController();
    fishCon.setRng(new Random(0));
    fishSpawner = fishCon.getFishSpawner();
    fishSpawner.setRng(new Random(0));

  }

  /**
   * Test to check if we can lose the game.
   * 
   * @throws TimeoutException
   *           timeoutException
   * @throws InterruptedException
   *           InterruptedException
   */
  @Test
  public void loseGame() throws InterruptedException, TimeoutException {
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof LosingPane;
      }

    }, Timeout.timeout(Duration.seconds(20L)));
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof LosingPane);

  }

  /**
   * Test to check if we can win the game.
   * 
   * @throws TimeoutException
   *           timeoutException
   * @throws InterruptedException
   *           InterruptedException
   */
  @Test
  public void winGame() throws InterruptedException, TimeoutException {
    final int size = 319;
    fishCon.getShark().setSize(size);

    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof WinPane;
      }

    }, Timeout.timeout(Duration.seconds(20L)));
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof WinPane);
  }

}

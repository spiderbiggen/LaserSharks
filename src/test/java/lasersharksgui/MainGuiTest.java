/**
 * 
 */
package lasersharksgui;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.Timeout;
import com.google.code.tempusfugit.temporal.WaitFor;
import lasersharks.Options;
import lasersharks.controllers.FishController;
import lasersharks.controllers.ScreenController;
import lasersharksgui.panes.GamePane;
import lasersharksgui.panes.LosingPane;
import lasersharksgui.panes.WinPane;
import org.junit.Before;

import java.util.Random;
import java.util.concurrent.TimeoutException;

import static org.junit.Assert.assertTrue;


/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class MainGuiTest {

  private static final long WINNING_FACTORY_SEED = 165;
  private static final float LOSE_GAME_SIZE = 20;
  private static final float WIN_GAME_SIZE = 319;
  private static final float POST_GROWTH_INCREASE_THRESHOLD = 1;

  private FishController fishCon;

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
    int height = 150;
    Options.setGlobalHeight(height);
    int width = 800;
    Options.setGlobalWidth(width);

    FXApp.startApp(new MainGui());
    FXer.getUserWith(FXApp.getScene().getRoot());

    Options.getInstance().setFactoryRng(new Random(WINNING_FACTORY_SEED));

    WaitFor.waitOrTimeout(() -> MainGui.getInstance().getCurrentPane() instanceof GamePane,
        Timeout.timeout(Duration.seconds(1L)));
    GamePane pane = (GamePane) MainGui.getInstance().getCurrentPane();
    ScreenController screenCon = pane.getScreenController();
    fishCon = screenCon.getFishController();
    fishCon.getFishSpawner();

  }

  /**
   * Test to check if we can lose the game.
   * 
   * @throws TimeoutException
   *           timeoutException
   * @throws InterruptedException
   *           InterruptedException
   */
  //@Test
  public void loseGame() throws InterruptedException, TimeoutException {
    fishCon.getShark().setSize(LOSE_GAME_SIZE);
    WaitFor.waitOrTimeout(() -> MainGui.getInstance().getCurrentPane() instanceof LosingPane,
        Timeout.timeout(Duration.seconds(20L)));
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
  //@Test
  public void winGame() throws InterruptedException, TimeoutException {
    fishCon.getShark().setSize(WIN_GAME_SIZE);

    WaitFor.waitOrTimeout(() -> MainGui.getInstance().getCurrentPane() instanceof WinPane,
        Timeout.timeout(Duration.seconds(20L)));
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof WinPane);
  }

  /**
   * Test to see if the size of the shark can increase.
   * 
   * @throws InterruptedException
   *           interruptedException
   * @throws TimeoutException
   *           timeoutException
   */
  //@Test
  public void increaseSizeTest() throws InterruptedException, TimeoutException {
    int threshold = (int) ((int) fishCon.getShark().getSize() + POST_GROWTH_INCREASE_THRESHOLD);
    WaitFor.waitOrTimeout(() -> fishCon.getShark().getSize() > threshold,
        Timeout.timeout(Duration.seconds(20L)));
    assertTrue(fishCon.getShark().getSize() > threshold);
  }

}
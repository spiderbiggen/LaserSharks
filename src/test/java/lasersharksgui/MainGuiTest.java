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

import lasersharks.controllers.FishController;
import lasersharks.controllers.Options;
import lasersharks.controllers.ScreenController;
import lasersharks.enemies.FishSpawner;

/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class MainGuiTest {

  private static final long WINNING_FACTORY_SEED = 165;
  private static final float LOSE_GAMESIZE = 1;
  private static final float WIN_GAMESIZE = 319;  
  private static final float POST_GROWTH_TRESHHOLD = 80;  
  private final int width = 800;
  private final int height = 150;

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
    Options.setGlobalHeight(height);
    Options.setGlobalWidth(width);

    FXApp.startApp(new MainGui());
    FXer.getUserWith(FXApp.getScene().getRoot());
    
    Options.getInstance().setSpawnRng(new Random(0));
    Options.getInstance().setFactoryRng(new Random(WINNING_FACTORY_SEED));
    
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof GamePane;
      }

    }, Timeout.timeout(Duration.seconds(1L)));
    pane = (GamePane) MainGui.getInstance().getCurrentPane();
    screenCon = pane.getScreenController();
    fishCon = screenCon.getFishController();
    fishSpawner = fishCon.getFishSpawner();

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
    fishCon.getShark().setSize(LOSE_GAMESIZE);
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
    fishCon.getShark().setSize(WIN_GAMESIZE);

    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof WinPane;
      }

    }, Timeout.timeout(Duration.seconds(20L)));
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof WinPane);
  }
  
  @Test
  public void increaseSizeTest() throws InterruptedException, TimeoutException {
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return fishCon.getShark().getSize() > POST_GROWTH_TRESHHOLD;
      }

    }, Timeout.timeout(Duration.seconds(20L)));
    assertTrue(fishCon.getShark().getSize() > POST_GROWTH_TRESHHOLD);
  }

}
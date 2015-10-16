/**
 * 
 */
package lasersharksgui;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import java.util.concurrent.TimeoutException;

//import org.apache.log4j.chainsaw.Main;
import org.junit.Before;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.google.code.tempusfugit.temporal.Condition;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.Timeout;
import com.google.code.tempusfugit.temporal.WaitFor;

import lasersharks.Logger;
import lasersharks.controllers.Options;
import lasersharks.controllers.FishController;
import lasersharks.controllers.ScreenController;
import lasersharks.enemies.FishSpawner;

/**
 * @author SEMGroup27
 *
 */
@SuppressWarnings("restriction")
public class MainGuiTest {

  private static final int WINNING_FACTORY_SEED = 165;
  private GamePane pane;
  private ScreenController screenCon;
  private FishController fishCon;
  private FishSpawner fishSpawner;

  private final int width = 800;
  private final int height = 150;

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

    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof GamePane;
      }

    }, Timeout.timeout(Duration.seconds(4L)));
    pane = (GamePane) MainGui.getInstance().getCurrentPane();
    screenCon = pane.getScreenController();
    fishCon = screenCon.getFishController();
    fishCon.setRng(new Random(0));
    fishSpawner = fishCon.getFishSpawner();
    fishSpawner.setRng(new Random(0));

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
  public void awinGame() throws InterruptedException, TimeoutException {
    try {
      Options.getInstance().setSpawnRng(new Random(0));
    } catch (Exception e) { 
      System.err.println("" + 1 + e.getClass() + ":" + e.getMessage());  }
    try {
    Options.getInstance().setFactoryRng(new Random(WINNING_FACTORY_SEED));
    } catch (Exception e) { 
      System.err.println("" + 2 + e.getClass() + ":" + e.getMessage());  }
    try {
    MainGui.getInstance().browseTo(GamePane.class);
    } catch (Exception e) { 
      System.err.println("" + 3 + e.getClass() + ":" + e.getMessage());  }
    
    try {
    WaitFor.waitOrTimeout(new Condition() {
      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof WinPane
            || MainGui.getInstance().getCurrentPane() instanceof LosingPane;
      }
    }, Timeout.timeout(Duration.seconds(100L)));

    } catch (Exception e) { 
      System.err.println("" + 4 + e.getClass() + ":" + e.getMessage());  }
    
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof WinPane);
    MainGui.getInstance().browseTo(LosingPane.class);
    MainGui.getInstance().browseTo(GamePane.class);
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
    Options.getInstance().setSpawnRng(new Random(0));
    Options.getInstance().setFactoryRng(new Random(0));
    MainGui.getInstance().browseTo(GamePane.class);
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {
        return MainGui.getInstance().getCurrentPane() instanceof LosingPane;
      }

    }, Timeout.timeout(Duration.seconds(100L)));

    assertTrue(MainGui.getInstance().getCurrentPane() instanceof LosingPane);

  }
  /**
   * Methode om correcte seed te bepalen om te winnen
   */
  // @Test
  public void getWinningSeed() {

    Options.getInstance().setSpawnRng(new Random(0));

    for (int i = 125; i < Integer.MAX_VALUE; i++) {
      System.out.println("Starting for: " + i);

      Options.getInstance().setFactoryRng(new Random(i));
      MainGui.getInstance().browseTo(GamePane.class);
      // ((GamePane)
      // MainGui.getInstance().getCurrentPane()).getScreenController().getShark().setSize(200);
      System.out.println("Checking for gameRunningscreen");

      try {
        WaitFor.waitOrTimeout(new Condition() {
          @Override
          public boolean isSatisfied() {
            return MainGui.getInstance().getCurrentPane() instanceof GamePane;
          }
        }, Timeout.timeout(Duration.seconds(2L)));

        if (!(MainGui.getInstance().getCurrentPane() instanceof GamePane)) {
          System.err.println("Unable to start game! for seed " + i);
          continue;
        } else {
          System.out.println("Game is running, current pane: "
              + MainGui.getInstance().getCurrentPane().getClass().getName());
        }

      } catch (Exception e) {
        System.err.println("T1:" + e.getClass().toString() + "-Exeption: " + e.getMessage());
      }

      System.out.println("Waiting for win or los");

      try {

        WaitFor.waitOrTimeout(new Condition() {
          @Override
          public boolean isSatisfied() {
            return MainGui.getInstance().getCurrentPane() instanceof WinPane
                || MainGui.getInstance().getCurrentPane() instanceof LosingPane;
          }
        }, Timeout.timeout(Duration.seconds(300L)));
        System.out.println("Pane- "
            + MainGui.getInstance().getCurrentPane().getClass().getName().toString() + " found.");
      } catch (Exception e) {
        System.err.println("T1:" + e.getClass().toString() + "-Exeption: " + e.getMessage());
      }

      System.out.println("Done");

      if (MainGui.getInstance().getCurrentPane() instanceof WinPane) {
        System.out.println("Success for: " + i);
        Logger.getInstance().write("FOUND PROPPER ITEM! " + i, i + " FOUND");
        assertTrue(true);
        break;
      } else {
        System.out.println("Failed for: " + i);
      }
    }

  }

}

/**
 * 
 */
package lasersharks.gui;

import static org.junit.Assert.assertTrue;

import java.awt.event.KeyEvent;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.athaydes.automaton.FXApp;
import com.athaydes.automaton.FXer;
import com.google.code.tempusfugit.temporal.Condition;
import com.google.code.tempusfugit.temporal.Duration;
import com.google.code.tempusfugit.temporal.Timeout;
import com.google.code.tempusfugit.temporal.WaitFor;

//import javafx.scene.input.KeyCode;
import lasersharks.FishController;
import lasersharks.ScreenController;
import lasersharks.enemies.FishSpawner;

/**
 * @author Stefan
 *
 */
@SuppressWarnings("restriction")
public class MainGuiTest {

  private FXer fxer;

  /**
   * Resets the environment for each test.
   */
  @Before
  public void setup() {
    FXApp.startApp(new MainGui());
    fxer = FXer.getUserWith(FXApp.getScene().getRoot());
  }

  /**
   * Test to check if we can lose the game.
   * 
   * @throws Exception timeoutException
   */
  @Test
  public void loseGame() throws Exception {
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {    
        return MainGui.getInstance().getCurrentPane() instanceof GamePane;
      }

    }, Timeout.timeout(Duration.seconds(1L)));
    GamePane pane = (GamePane) MainGui.getInstance().getCurrentPane();
    ScreenController screenCon = pane.getScreenController();
    FishController fishCon = screenCon.getFishController();
    fishCon.setRng(new Random(0));
    FishSpawner fishSpawn = fishCon.getFishSpawner();
    fishSpawn.setRng(new Random(0));
    
    fxer.type(KeyEvent.VK_D);
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {    
        return MainGui.getInstance().getCurrentPane() instanceof LosingPane;
      }

    }, Timeout.timeout(Duration.seconds(20L)));
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof LosingPane);
    MainGui.getInstance().stop();
  }

  /**
   * Test to check if we can win the game.
   * 
   * @throws Exception timeoutException
   */
  @Test
  public void winGame() throws Exception {
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {    
        return MainGui.getInstance().getCurrentPane() instanceof GamePane;
      }

    }, Timeout.timeout(Duration.seconds(1L)));
    GamePane pane = (GamePane) MainGui.getInstance().getCurrentPane();
    ScreenController screenCon = pane.getScreenController();
    FishController fishCon = screenCon.getFishController();
    fishCon.setRng(new Random(0));
    FishSpawner fishSpawn = fishCon.getFishSpawner();
    fishSpawn.setRng(new Random(0));
    
    fxer.type(KeyEvent.VK_D);
    WaitFor.waitOrTimeout(new Condition() {

      @Override
      public boolean isSatisfied() {    
        return MainGui.getInstance().getCurrentPane() instanceof WinPane;
      }

    }, Timeout.timeout(Duration.seconds(20L)));
    assertTrue(MainGui.getInstance().getCurrentPane() instanceof WinPane);
    MainGui.getInstance().stop();
  }

}

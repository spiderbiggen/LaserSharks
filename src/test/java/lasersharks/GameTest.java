package lasersharks;

import static org.junit.Assert.assertEquals;
import lasersharks.gui.LevelGUI;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * Test class for Game.
 * @author Sytze
 *
 */
public class GameTest {

  Game game;
  Level level;
  LevelGUI gui;
  
  private final int SCORE = 10;
  
  /**
   * Set up the game
   */
  @Before
  public void setUp() {
    game = new Game();
    level = Mockito.mock(Level.class);
    gui = Mockito.mock(LevelGUI.class);    
    game.setLevel(level);
    Mockito.when(level.getScore()).thenReturn(SCORE);
  }
  
  /**
   * Test the getter
   */
  public void testGetLevel() {
    assertEquals(SCORE, game.getLevel().getScore(), 0);
  }
  
}

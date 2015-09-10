package lasersharks;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
/**
 * Test class for Game.
 * @author Sytze
 *
 */
public class GameTest {

  private Game game;
  private Level level;
  
  private final int score = 10;
  
  /**
   * Set up the game.
   */
  @Before
  public void setUp() {
    game = new Game();
    level = Mockito.mock(Level.class);
    game.setLevel(level);
    Mockito.when(level.getScore()).thenReturn(score);
  }
  
  /**
   * Test the getter.
   */
  @Test
  public void testGetLevel() {
    assertEquals(score, game.getLevel().getScore(), 0);
  }
  
}

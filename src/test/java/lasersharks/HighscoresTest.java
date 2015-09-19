package lasersharks;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lasersharks.gui.LevelGUI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for Highscores class.
 * 
 * @author Daan
 *
 */
public class HighscoresTest {

  private static final String INPUT_FILE = "highscoresTestFile";
  private ArrayList<String> list = new ArrayList<String>();
  private final int testSize = 5;

  /**
   * Set the input file to the test file.
   * 
   * @throws Exception
   *           when the file is not found.
   */
  @Before
  public void setUp() throws Exception {
    Highscores.setInputFile(INPUT_FILE);
  }

  /**
   * After executing all tests the highscore file should be written back to how it was.
   * 
   * @throws Exception
   *           file error
   */
  @After
  public void tearDown() throws Exception {
    try (FileWriter fw = new FileWriter(new File(INPUT_FILE))) {
      for (int i = 0; i < testSize; i++) {
        if (i < testSize - 1) {
          fw.write((i + 1) + ". " + (testSize - i) + System.lineSeparator());
        } else {
          fw.write((i + 1) + ". " + (testSize - i));
        }

      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Test case for readHighScores, when the resulting list equals the expected list.
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  @Test
  public void testReadHighscoresTrue() throws IOException {
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", Highscores.readHighscore().toString());
  }

  /**
   * Test case for readHighScores, when the resulting list is not the same as expected.
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  @Test
  public void testReadHighscoresFalse() throws IOException {
    assertNotEquals("[1. 1, 2. 2, 3. 3, 4. 4, 5. 5]", Highscores.readHighscore().toString());
  }

  /**
   * Test method for FixHighscoreCount(). We enter a 'bad' highscore list and a new good numbered
   * list should come out.
   */
  @Test
  public void testFixHighscoreCountTrue() {
    list.add("1. 600");
    list.add("1. 500");
    list.add("2. 400");
    list.add("3. 300");
    list.add("4. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]",
        Highscores.fixHighscoreCount(list).toString());

  }

  /**
   * Test method for FixHighscoreCount(). We enter a good highscore list and this should stay the
   * same.
   */
  @Test
  public void testFixHighscoreCountStaysTheSame() {
    list.add("1. 600");
    list.add("2. 500");
    list.add("3. 400");
    list.add("4. 300");
    list.add("5. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]",
        Highscores.fixHighscoreCount(list).toString());

  }

  /**
   * Test method for FixHighscoreCount(). We enter completely erronous list and a good one should
   * still come out. same.
   */
  @Test
  public void testFixHighscoreCountCompletelyBadList() {
    list.add("3. 600");
    list.add("4. 500");
    list.add("1. 400");
    list.add("5. 300");
    list.add("2. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]",
        Highscores.fixHighscoreCount(list).toString());

  }

  /**
   * Test method for the getHighScore() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */
  @Test
  public void testGetHighScoreTrue() throws FileNotFoundException {
    assertTrue(Highscores.getHighScore() == testSize);
  }

  /**
   * Test method for the getHighScore() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */
  @Test
  public void testGetHighScoreFalse() throws FileNotFoundException {
    assertFalse(Highscores.getHighScore() == testSize - 1);
  }

  /**
   * Test method for the makeHighscoreString() method in Highscores.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */
  @Test
  public void testMakeHighscoreString() throws FileNotFoundException {
    Highscores.setList(Highscores.readHighscore());
    String li = System.lineSeparator();
    LevelGUI.setScore(0);
    assertEquals("Highscores:" + li + "     " + "1. 5" + li + "     " + "2. 4" + li + "     "
        + "3. 3" + li + "     " + "4. 2" + li + "     " + "5. 1" + li + li + "Your score: "
        + LevelGUI.getScore(), Highscores.makeHighscoreString());

  }
}

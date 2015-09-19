package lasersharks;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lasersharks.gui.LevelGUI;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighscoresTest {

  private static final String INPUT_FILE = "src/main/resources/highscoresTestFile";
  private LevelGUI levelGUI;
  private ArrayList<String> list = new ArrayList<String>();

  @Before
  public void setUp() throws Exception {
    Highscores.setInputFile(INPUT_FILE);
    this.levelGUI = mock(LevelGUI.class);
    // when(LevelGUI.getScore()).thenReturn(6);

  }

  /**
   * After executing all tests the highscore file should be written back to how it was.
   * 
   * @throws Exception
   */
  @After
  public void tearDown() throws Exception {
    try (FileWriter fw = new FileWriter(new File(INPUT_FILE))) {
      for (int i = 0; i < 5; i++) {
        if (i < 5 - 1) {
          fw.write((i + 1) + ". " + (5 - i) + System.lineSeparator());
        } else {
          fw.write((i + 1) + ". " + (5 - i));
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
   */
  @Test
  public void testReadHighscoresTrue() throws IOException {
    assertEquals(Highscores.readHighscore().toString(), "[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]");
  }

  /**
   * Test case for readHighScores, when the resulting list is not the same as expected.
   * 
   * @throws IOException
   */
  @Test
  public void testReadHighscoresFalse() throws IOException {
    assertNotEquals(Highscores.readHighscore().toString(), "[1. 1, 2. 2, 3. 3, 4. 4, 5. 5]");
  }

  /*
   * @Test public void testWriteHighscores() throws IOException { Highscores.writeHighscore();
   * assertEquals(Highscores.readHighscore().toString(), "[1. 6, 2. 5, 3. 4, 4. 3, 5. 2]"); }
   */
  
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
    assertEquals(Highscores.fixHighscoreCount(list).toString(),
        "[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]");

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
    assertEquals(Highscores.fixHighscoreCount(list).toString(),
        "[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]");

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
    assertEquals(Highscores.fixHighscoreCount(list).toString(),
        "[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]");

  }

}

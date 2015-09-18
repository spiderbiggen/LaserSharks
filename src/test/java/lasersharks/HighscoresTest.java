package lasersharks;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class HighscoresTest {

  private static final String INPUT_FILE = "src/main/resources/highscoresTestFile";

  @Before
  public void setUp() throws Exception {
    Highscores.setInputFile(INPUT_FILE);
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
          fw.write((i + 1) + ". " + (i + 1) + System.lineSeparator());
        } else {
          fw.write((i + 1) + ". " + (i + 1));
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
    assertEquals(Highscores.readHighscore().toString(), "[1. 1, 2. 2, 3. 3, 4. 4, 5. 5]");
  }
  
  /**
   * Test case for readHighScores, when the resulting list is not the same as expected.
   * 
   * @throws IOException
   */
  @Test
  public void testReadHighscoresFalse() throws IOException {
    assertNotEquals(Highscores.readHighscore().toString(), "[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]");
  }
}

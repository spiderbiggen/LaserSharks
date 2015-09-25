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

import lasersharksgui.LevelGUI;

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
  private Highscores highscores = new Highscores();

  /**
   * Set the input file to the test file.
   * 
   * @throws Exception
   *           when the file is not found.
   */
  @Before
  public void setUp() throws Exception {
    highscores.setInputFile(INPUT_FILE);
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
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highscores.readHighscore().toString());
  }

  /**
   * Test case for readHighScores, when the resulting list is not the same as expected.
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  @Test
  public void testReadHighscoresFalse() throws IOException {
    assertNotEquals("[1. 1, 2. 2, 3. 3, 4. 4, 5. 5]", highscores.readHighscore().toString());
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
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]", highscores.fixHighscoreCount(list)
        .toString());

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
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]", highscores.fixHighscoreCount(list)
        .toString());

  }

  /**
   * Test method for FixHighscoreCount(). We enter completely erroneous list and a good one should
   * still come out.
   */
  @Test
  public void testFixHighscoreCountCompletelyBadList() {
    list.add("3. 600");
    list.add("4. 500");
    list.add("1. 400");
    list.add("5. 300");
    list.add("2. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]", highscores.fixHighscoreCount(list)
        .toString());

  }

  /**
   * Test method for the getHighScore() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */
  @Test
  public void testGetHighScoreTrue() throws FileNotFoundException {
    int highestScore = highscores.getHighScore();
    assertTrue(highestScore == testSize);
  }

  /**
   * Test method for the getHighScore() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */
  @Test
  public void testGetHighScoreFalse() throws FileNotFoundException {
    int highestScore = highscores.getHighScore();
    assertFalse(highestScore == testSize - 1);
  }

  /**
   * Test method for the makeHighscoreString() method in Highscores.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */
  @Test
  public void testMakeHighscoreString() throws FileNotFoundException {
    highscores.setList(highscores.readHighscore());
    String li = System.lineSeparator();
    LevelGUI.setScore(0);
    assertEquals("Highscores:" + li + "     " + "1. 5" + li + "     " + "2. 4" + li + "     "
        + "3. 3" + li + "     " + "4. 2" + li + "     " + "5. 1" + li + li + "Your score: "
        + LevelGUI.getScore(), highscores.makeHighscoreString());

  }

  /**
   * Test method for the getList() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */

  @Test
  public void testGetListInitialEmptyList() throws FileNotFoundException {
    highscores.setList(new ArrayList<String>());
    ;
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highscores.getList().toString());
  }

  /**
   * Test method for the getList() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */

  @Test
  public void testGetListInitialNullList() throws FileNotFoundException {
    highscores.setList(null);
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highscores.getList().toString());
  }

  /**
   * Test method for the getList() method.
   * 
   * @throws FileNotFoundException
   *           when the file is not found (highly unlikely).
   */

  @Test
  public void testGetListNormalList() throws FileNotFoundException {
    list.add("1. 5");
    list.add("2. 4");
    list.add("3. 3");
    list.add("4. 2");
    list.add("5. 1");

    highscores.setList(list);
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highscores.getList().toString());
  }

  /**
   * Test method for the writeHighscore() method.
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  @Test
  public void testWriteHighscoreNewHighscoreEntry() throws IOException {
    LevelGUI.setScore(50); // the highest score is now 50
    highscores.writeHighscore();
    assertEquals("[1. 50, 2. 5, 3. 4, 4. 3, 5. 2]", highscores.readHighscore().toString());
  }

  /**
   * Test method for the writeHighscore() method.
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  @Test
  public void testWriteHighscoreNoNewEntry() throws IOException {
    LevelGUI.setScore(0); // the score should not be written in the list
    highscores.writeHighscore();
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highscores.readHighscore().toString());
  }

  /**
   * Test method for the writeHighscore() method.
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  @Test
  public void testWriteHighscoreMiddleEntry() throws IOException {
    LevelGUI.setScore(3); // the score should be entered in the middle of the list
    highscores.writeHighscore();
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 3, 5. 2]", highscores.readHighscore().toString());
  }

  /**
   * Test method for the getFishBonus() method
   */
  @SuppressWarnings("static-access")
  @Test
  public void testGetFishBonus() {
    int expectedFishBonus = 20;
    assertEquals(expectedFishBonus, highscores.getFishBonus());
  }

  /**
   * Make sure get instance doesn't return null.
   */
  @Test
  public void testGetInstanceNotNull() {
    assertTrue(Highscores.getInstance() != null);
  }

  /**
   * Make sure getInstance always returns same object if no setters used.
   */
  @Test
  public void testAlwaysSameInstance() {
    Highscores l = Highscores.getInstance();
    assertEquals(l, Highscores.getInstance());
  }

  /**
   * Test method for setInstance()
   */
  @SuppressWarnings("static-access")
  @Test
  public void testSetInstance() {
    Highscores testH = new Highscores();
    highscores.setInstance(testH);
    assertEquals(testH, testH.getInstance());

  }

}

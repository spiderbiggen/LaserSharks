package lasersharks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for HighScores class.
 *
 * @author SEMGroup27
 */
public class HighScoresTest {

  private static final String INPUT_FILE = "src/test/resources/highScoresTestFile";
  private final ArrayList<String> list = new ArrayList<>();
  private static final int TEST_SIZE = 5;
  private HighScores highScores;

  /**
   * Set the input file to the test file.
   *
   * @throws Exception when the file is not found.
   */
  @Before public void setUp() throws Exception {
    highScores = new HighScores();
    highScores.setInputFile(INPUT_FILE);
  }

  /**
   * After executing all tests the high score file should be written back to how it was.
   *
   * @throws Exception file error
   */
  @After public void tearDown() throws Exception {
    HighScores.destroyInstance();
    try (FileWriter fw = new FileWriter(new File(INPUT_FILE))) {
      for (int i = 0; i < TEST_SIZE; i++) {
        if (i < TEST_SIZE - 1) {
          fw.write((i + 1) + ". " + (TEST_SIZE - i) + System.lineSeparator());
        } else {
          fw.write((i + 1) + ". " + (TEST_SIZE - i));
        }

      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Test case for readHighScores, when the resulting list equals the expected list.
   *
   * @throws IOException when there is an erroneous input.
   */
  @Test public void testReadHighScoresTrue() throws IOException {
    highScores.readHighScore();
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highScores.getList().toString());
  }

  /**
   * Test case for readHighScores, when the resulting list is not the same as expected.
   *
   * @throws IOException when there is an erroneous input.
   */
  @Test public void testReadHighScoresFalse() throws IOException {
    highScores.readHighScore();
    assertNotEquals("[1. 1, 2. 2, 3. 3, 4. 4, 5. 5]", highScores.getList().toString());
  }

  /**
   * Test method for FixHighScoreCount(). We enter a 'bad' high score list and a new good numbered
   * list should come out.
   */
  @Test public void testFixHighScoreCountTrue() {
    list.add("1. 600");
    list.add("1. 500");
    list.add("2. 400");
    list.add("3. 300");
    list.add("4. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]",
        highScores.fixHighScoreCount(list).toString());

  }

  /**
   * Test method for FixHighScoreCount(). We enter a good high score list and this should stay the
   * same.
   */
  @Test public void testFixHighScoreCountStaysTheSame() {
    list.add("1. 600");
    list.add("2. 500");
    list.add("3. 400");
    list.add("4. 300");
    list.add("5. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]",
        highScores.fixHighScoreCount(list).toString());

  }

  /**
   * Test method for FixHighScoreCount(). We enter completely erroneous list and a good one should
   * still come out.
   */
  @Test public void testFixHighScoreCountCompletelyBadList() {
    list.add("3. 600");
    list.add("4. 500");
    list.add("1. 400");
    list.add("5. 300");
    list.add("2. 200");
    assertEquals("[1. 600, 2. 500, 3. 400, 4. 300, 5. 200]",
        highScores.fixHighScoreCount(list).toString());

  }

  /**
   * Test method for the getHighScore() method.
   *
   * @throws FileNotFoundException when the file is not found (highly unlikely).
   */
  @Test public void testGetHighScoreTrue() throws FileNotFoundException {
    final int highestScore = highScores.getHighScore();
    assertSame(TEST_SIZE, highestScore);
  }

  /**
   * Test method for the getHighScore() method.
   *
   * @throws FileNotFoundException when the file is not found (highly unlikely).
   */
  @Test public void testGetHighScoreFalse() throws FileNotFoundException {
    final int highestScore = highScores.getHighScore();
    assertNotSame(TEST_SIZE - 1, highestScore);
  }

  /**
   * Test method for the makeHighScoreString() method in HighScores.
   *
   * @throws FileNotFoundException when the file is not found (highly unlikely).
   */
  @Test public void testMakeHighScoreString() throws FileNotFoundException {
    final ArrayList<String> list = new ArrayList<>();
    list.add("1. 10");
    list.add("2. 8");
    list.add("3. 6");
    list.add("4. 4");
    list.add("5. 2");
    highScores.setList(list);
    final String li = System.lineSeparator();
    highScores.setScore(0);
    assertEquals(
        "High Scores:" + li + "     " + "1. 10" + li + "     " + "2. 8" + li + "     " + "3. 6" + li
            + "     " + "4. 4" + li + "     " + "5. 2" + li + li + "Your score: " + highScores
            .getScore(), highScores.makeHighScoreString());

  }

  /**
   * Test method for the getList() method.
   *
   * @throws FileNotFoundException when the file is not found (highly unlikely).
   */

  @Test public void testGetListInitialEmptyList() throws FileNotFoundException {
    highScores.setList(new ArrayList<>());
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highScores.getList().toString());
  }

  /**
   * Test method for the getList() method.
   *
   * @throws FileNotFoundException when the file is not found (highly unlikely).
   */

  @Test public void testGetListInitialNullList() throws FileNotFoundException {
    highScores.setList(null);
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highScores.getList().toString());
  }

  /**
   * Test method for the getList() method.
   *
   * @throws FileNotFoundException when the file is not found (highly unlikely).
   */

  @Test public void testGetListNormalList() throws FileNotFoundException {
    list.add("1. 5");
    list.add("2. 4");
    list.add("3. 3");
    list.add("4. 2");
    list.add("5. 1");

    highScores.setList(list);
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 2, 5. 1]", highScores.getList().toString());
  }

  /**
   * Test method for the writeHighScore() method.
   *
   * @throws IOException when there is an erroneous input.
   */
  @Test public void testWriteHighScoreNewHighsScoreEntry() throws IOException {
    final int score = 50;
    highScores.setScore(score); // the highest score is now 50
    highScores.writeHighScore();
    assertEquals("[1. 50, 2. 5, 3. 4, 4. 3, 5. 2]", highScores.getList().toString());
  }

  /**
   * Test method for the writeHighScore() method.
   *
   * @throws IOException when there is an erroneous input.
   */
  @Test public void testWriteHighScoreMiddleEntry() throws IOException {
    final int score = 3;
    highScores.setScore(score); // the score should be entered in the middle of the list
    highScores.writeHighScore();
    assertEquals("[1. 5, 2. 4, 3. 3, 4. 3, 5. 2]", highScores.getList().toString());
  }

  /**
   * Test method for the getFishBonus() method.
   */
  @SuppressWarnings("static-access") @Test public void testGetFishBonus() {
    final int expectedFishBonus = 20;
    assertEquals(expectedFishBonus, highScores.getFishBonus());
  }

  /**
   * Make sure get instance doesn't return null.
   */
  @Test public void testGetInstanceNotNull() {
    assertNotSame(null, HighScores.getInstance());
  }

  /**
   * Make sure getInstance always returns same object if no setters used.
   */
  @Test public void testAlwaysSameInstance() {
    final HighScores l = HighScores.getInstance();
    highScores.destroyInstance();
    assertEquals(l, HighScores.getInstance());
  }

}

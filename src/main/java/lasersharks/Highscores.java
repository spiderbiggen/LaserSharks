package lasersharks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that handles anything to do with high scores.
 * 
 * @author SEMGroup27
 *
 */
public class HighScores {

  private static final HighScores instance = new HighScores();
  private static final String HIGH_SCORE_FILE_URI = "src/main/resources/highScoreList";
  private static final int DATA_OFFSET = 3;
  private static final int FISH_BONUS = 20;

  private ArrayList<String> highScoreList;
  private String inputFile;
  private int amountOfFishesEaten;
  private int score;

  /**
   * create a new instance of high scores.
   */
  protected HighScores() {
    this.inputFile = HIGH_SCORE_FILE_URI;
  }

  /**
   * return the instance.
   *
   * @return high scores instance
   */
  public static HighScores getInstance() {
    return instance;
  }

  /**
   * Reads the current high score list so it can be edited.
   * 
   */
  public void readHighScore() {
    final ArrayList<String> list = new ArrayList<>();
    try (Scanner sc = new Scanner(new File(inputFile))) {

      while (sc.hasNextLine()) {
        list.add(sc.nextLine());
      }
    } catch (FileNotFoundException e) {
      final int five = 5;
      for (int i = 0; i < five; i++) {
        list.add(i + 1 + ". 0");
      }
    }
    highScoreList = list;
  }

  /**
   * Sets the list of high scores to inputList.
   * 
   * @param inputList
   *          the list of new high scores.
   */
  public void setList(final ArrayList<String> inputList) {
    highScoreList = inputList;
  }

  /**
   * Will return the current list. If the list doesn't exist yet read the one from the file.
   *
   * @return the list of high scores.
   */
  public ArrayList<String> getList() {
    boolean noFile = false;
    try (Scanner sc1 = new Scanner(new File(inputFile))) {
      sc1.hasNext();
    } catch (FileNotFoundException e) {
      noFile = true;
    }

    if (highScoreList == null || highScoreList.isEmpty() || noFile) {

      readHighScore();
    }
    return highScoreList;
  }

  /**
   * Set the input file to the one specified with inputFile.
   * 
   * @param inputFile
   *          the relative path to the new input file.
   */
  public void setInputFile(final String inputFile) {
    this.inputFile = inputFile;
  }

  /**
   * Method for writing the high scores. The high scores are saved in highScoreList.txt .
   */
  public void writeHighScore() {
    final ArrayList<String> list = getList();
    for (int i = 0; i < list.size(); i++) {
      if (score >= Integer.parseInt(list.get(i).substring(DATA_OFFSET))) {
        list.remove(list.size() - 1);
        list.add(i, i + ". " + score);
        break;
      }

    }
    fixHighScoreCount(list);

    try (FileWriter fw = new FileWriter(inputFile)) {
      for (int i = 0; i < list.size(); i++) {
        if (i < list.size() - 1) {
          fw.write(list.get(i) + System.lineSeparator());
        } else {
          fw.write(list.get(i));
        }
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Fixes the still erroneous list after the new score has been inserted in the writeHighScore()
   * method. An example would be 1. 500 2. 250 2. 250 3. 100 4. 50.
   * 
   * @param list
   *          the list containing the high score elements.
   * @return the correct high score list.
   */
  public ArrayList<String> fixHighScoreCount(final ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      String newEntry = list.get(i);
      list.remove(i);

      newEntry = (i + 1) + ". " + newEntry.substring(DATA_OFFSET);
      list.add(i, newEntry);
    }

    return list;
  }

  /**
   * Gets the highest score from the high score list.
   * 
   * @return the highest score in the list.
   * @throws FileNotFoundException
   *           when the file is not found.
   */
  public int getHighScore() throws FileNotFoundException {
    try (Scanner sc = new Scanner(new File(inputFile))) {
      final String firstLine = sc.nextLine();
      return Integer.parseInt(firstLine.substring(DATA_OFFSET));
    }

  }

  /**
   * Makes a nicely displayed string to output on the end screen.
   *
   * @return a String containing the high scores.
   */
  public String makeHighScoreString() {
    getList();
    final StringBuilder stringBuilder = new StringBuilder();
    final String li = System.lineSeparator();
    stringBuilder.append("High Scores:").append(li);
    for (final String highScore : highScoreList) {
      stringBuilder.append("     ").append(highScore).append(li);
    }
    return stringBuilder.append(li).append("Your score: ").append(score).toString();
  }

  /**
   * Increase the current score the player has according to the size of the fish eaten.
   *
   * @param increment
   *          the amount by which to increment the score.
   */
  public void increaseScore(final int increment) {
    score += Math.max(0, increment);
  }

  /**
   * Get most current time penalty.
   *
   * @return current time penalty.
   */
  public int getTimePenalty() {
    return (amountOfFishesEaten++) * 2;
  }

  /**
   * @return Bonus per eaten fish.
   */
  public static int getFishBonus() {
    return FISH_BONUS;
  }

  /**
   * Method so we can mock highScoreList in tests of other classes.
   *
   * @param highScores
   *          highScoreList to be used.
   */
 /* public static void setInstance(final HighScores highScores) {
    HighScores.instance = highScores;
  }*/

  /**
   * @return the score
   */
  public int getScore() {
    return this.score;
  }

  /**
   * @param score
   *          the score to set
   */
  public void setScore(final int score) {
    this.score = score;
  }

  /**
   * Restores the initial state of the instance, while obeying the singleton pattern.
   */
  public static void destroyInstance() {
    instance.highScoreList = null;
    instance.inputFile = HIGH_SCORE_FILE_URI;
    instance.amountOfFishesEaten = 0;
    instance.score = 0;
  }
}

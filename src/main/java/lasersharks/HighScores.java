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

  /**
   * The singleton instance of this class.
   */
  private static HighScores instance;
  /**
   * The list of high scores stored as strings.
   */
  private ArrayList<String> highScores;
  /**
   * The URI to the resource file.
   */
  private String inputFile;
  /**
   * The amount of fishes that has been eaten.
   */
  private int amountOfFishesEaten = 0;
  /**
   * The amount of characters to offset the string values by to get a score.
   */
  private static final int DATA_OFFSET = 3;
  /**
   * Bonus score per fish.
   */
  private static final int FISH_BONUS = 20;
  /**
   * The current score.
   */
  private int score;

  /**
   * create a new instance of highScores.
   */
  protected HighScores() {
    this.inputFile = "src/main/resources/highScores";
  }

  /**
   * return the instance.
   * 
   * @return highscores instance
   */
  public static HighScores getInstance() {
    if (instance == null) {
      instance = new HighScores();
    }
    return instance;
  }

  /**
   * Reads the current highscore list so it can be edited.
   * 
   */
  public void readHighScore() {
    ArrayList<String> list = new ArrayList<String>();
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
    highScores = list;
  }

  /**
   * Sets the list of highscores to inputList.
   * 
   * @param inputList
   *          the list of new highscores.
   */
  public void setList(ArrayList<String> inputList) {
    highScores = inputList;
  }

  /**
   * Will return the current list. If the list doesn't exist yet read the one from the file.
   * 
   * @return the list of highscores.
   * @throws FileNotFoundException
   *           if the file doesn't exist or is in the wrong location.
   */
  public ArrayList<String> getList() {
    boolean noFile = false;
    try (Scanner sc1 = new Scanner(new File(inputFile))) {
      sc1.hasNext();
    } catch (FileNotFoundException e) {
      noFile = true;
    }

    if (highScores == null || highScores.size() == 0 || noFile) {

      readHighScore();
    }
    return highScores;
  }

  /**
   * Set the inputfile to the one specified with inputFile.
   * 
   * @param inputFile
   *          the relative path to the new inputfile.
   */
  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }

  /**
   * Method for writing the highscores. The highscores are saved in highscores.txt .
   * 
   * @throws IOException
   *           when there is an erroneous input.
   */
  public void writeHighScore() throws IOException {
    ArrayList<String> list = getList();
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
   * Fixes the still erroneous list after the new score has been inserted in the writeHighscore()
   * method. An example would be 1. 500 2. 250 2. 250 3. 100 4. 50.
   * 
   * @param list
   *          the list containing the highscore elements.
   * @return the correct highscore list.
   */
  public ArrayList<String> fixHighScoreCount(ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      String newEntry = list.get(i);
      list.remove(i);

      newEntry = (i + 1) + ". " + newEntry.substring(DATA_OFFSET);
      list.add(i, newEntry);
    }

    return list;
  }

  /**
   * Gets the highest score from the highscore list.
   * 
   * @return the highest score in the list.
   * @throws FileNotFoundException
   *           when the file is not found.
   */
  public int getHighScore() throws FileNotFoundException {
    try (Scanner sc = new Scanner(new File(inputFile))) {
      String firstLine = sc.nextLine();
      int highestScore = Integer.parseInt(firstLine.substring(DATA_OFFSET));
      return highestScore;
    }

  }

  /**
   * Makes a nicely displayed string to output on the end screen.
   * 
   * @return a String containing the highscores.
   */
  public String makeHighScoreString() {
    getList();
    String res = "";
    String li = System.lineSeparator();
    for (int i = 0; i < highScores.size(); i++) {
      res = res + "     " + highScores.get(i) + li;
    }

    return "Highscores:" + li + res + li + "Your score: " + score;
  }

  /**
   * Increase the current score the player has according to the size of the fish eaten.
   * 
   * @param increment
   *          the fish that is used to calculate the additional score
   */

  public void increaseScore(int increment) {
    score += Math.max(0, increment);
  }

  /**
   * Get most current timepenalty.
   * 
   * @return current timepenalty.
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
   * Method so we can mock highscores in tests of other classes.
   * 
   * @param highscores
   *          highscores to be used.
   */
  public static void setInstance(HighScores highscores) {
    HighScores.instance = highscores;
  }

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
  public void setScore(int score) {
    this.score = score;
  }

}

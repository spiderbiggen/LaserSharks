package lasersharks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class that handles anything to do with highscores.
 * 
 * @author SEMGroup27
 *
 */
public class Highscores {

  private static Highscores instance;
  private ArrayList<String> highscores;
  private String inputFile;
  private int amountOfFishesEaten = 0;
  private static final int DATA_OFFSET = 3;
  private static final int FISH_BONUS = 20;
  private int score;

  /**
   * create a new instance of highscores.
   */
  protected Highscores() {
    this.inputFile = "src/main/resources/highscores";
  }

  /**
   * return the instance.
   * 
   * @return highscores instance
   */
  public static Highscores getInstance() {
    if (instance == null) {
      instance = new Highscores();
    }
    return instance;
  }

  /**
   * Reads the current highscore list so it can be edited.
   * 
   */
  public void readHighscore() {
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
    highscores = list;
  }

  /**
   * Sets the list of highscores to inputList.
   * 
   * @param inputList
   *          the list of new highscores.
   */
  public void setList(ArrayList<String> inputList) {
    highscores = inputList;
  }

  /**
   * Will return the current list. If the list doesn't exist yet read the one from the file.
   * 
   * @return the list of highscores.
   * @throws FileNotFoundException
   *           if the file doesn't exist or is in the wrong location.
   */
  public ArrayList<String> getList() {
    if (highscores == null || highscores.size() == 0) {
      readHighscore();
    }
    return highscores;
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
  public void writeHighscore() throws IOException {
    ArrayList<String> list = getList();
    for (int i = 0; i < list.size(); i++) {
      if (score >= Integer.parseInt(list.get(i).substring(DATA_OFFSET))) {
        list.remove(list.size() - 1);
        list.add(i, i + ". " + score);
        break;
      }

    }
    fixHighscoreCount(list);

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
  public ArrayList<String> fixHighscoreCount(ArrayList<String> list) {
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
  public String makeHighscoreString() {
    getList();
    String res = "";
    String li = System.lineSeparator();
    for (int i = 0; i < highscores.size(); i++) {
      res = res + "     " + highscores.get(i) + li;
    }

    return "Highscores:" + li + res + li + "Your score: " + score;
  }

  /**
   * Increase the current score the player has according to the size of the fish eaten.
   * 
   * @param fish
   *          the fish that is used to calculate the additional score
   */
  public void increaseScore(int increment) {
    score += Math.max(0, increment);
  }

  /**
   * Get most current timepenalty.
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
  public static void setInstance(Highscores highscores) {
    Highscores.instance = highscores;
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

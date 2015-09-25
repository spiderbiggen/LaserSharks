package lasersharks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lasersharksgui.LevelGUI;

/**
 * A class that handles anything to do with highscores.
 * 
 * @author Daan
 *
 */
public class Highscores {

  private static Highscores highscores;
  private ArrayList<String> list;
  private String inputFile;
  private static final int DATA_OFFSET = 3;
  private static final int FISH_BONUS = 20;

  protected Highscores() {
    this.inputFile = "src/main/resources/highscores";
  }
/**
 * return the instance
 * @return highscores instance
 */
  public static Highscores getInstance() {
    if (highscores == null) {
      highscores = new Highscores();
    }
    return highscores;
  }

  /**
   * Reads the current highscore list so it can be edited.
   * 
   * @return the current highscore list.
   * @throws FileNotFoundException
   *           when the file is not found.
   */
  public ArrayList<String> readHighscore() throws FileNotFoundException {
    try (Scanner sc = new Scanner(new File(inputFile))) {
      ArrayList<String> list = new ArrayList<String>();
      while (sc.hasNextLine()) {
        list.add(sc.nextLine());
      }

      return list;
    }

  }

  /**
   * Sets the list of highscores to inputList.
   * 
   * @param inputList
   *          the list of new highscores.
   */
  public void setList(ArrayList<String> inputList) {
    list = inputList;
  }

  /**
   * Will return the current list. If the list doesn't exist yet read the one from the file.
   * 
   * @return the list of highscores.
   * @throws FileNotFoundException
   *           if the file doesn't exist or is in the wrong location.
   */
  public ArrayList<String> getList() throws FileNotFoundException {
    if (list == null || list.size() == 0) {
      list = readHighscore();
    }
    return list;
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
    list = readHighscore();
    for (int i = 0; i < list.size(); i++) {
      if (LevelGUI.getScore() >= Integer.parseInt(list.get(i).substring(DATA_OFFSET))) {
        list.remove(list.size() - 1);
        list.add(i, i + ". " + LevelGUI.getScore());
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
   * @return the fishBonus
   */
  public static int getFishBonus() {
    return FISH_BONUS;
  }

  /**
   * Makes a nicely displayed string to output on the end screen.
   * 
   * @return a String containing the highscores.
   */
  public String makeHighscoreString() {
    String res = "";
    String li = System.lineSeparator();
    for (int i = 0; i < list.size(); i++) {
      res = res + "     " + list.get(i) + li;
    }

    return "Highscores:" + li + res + li + "Your score: " + LevelGUI.getScore();
  }

  /**
   * Method so we can mock highscores in tests of other classes.
   * 
   * @param highscores
   *          highscores to be used.
   */
  public static void setInstance(Highscores highscores) {
    Highscores.highscores = highscores;
  }

}

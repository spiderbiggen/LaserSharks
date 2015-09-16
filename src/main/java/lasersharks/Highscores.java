package lasersharks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import lasersharks.gui.LevelGUI;

public class Highscores {

  private static ArrayList<String> list = new ArrayList<String>();
  private static int score = LevelGUI.getScore();

  /**
   * Reads the current highscore list so it can be edited.
   * 
   * @return the current highscore list.
   * @throws FileNotFoundException
   */
  public static ArrayList<String> readHighscore() throws FileNotFoundException {
    @SuppressWarnings("resource")
    Scanner sc = new Scanner(new File("highscores"));
    ArrayList<String> list = new ArrayList<String>();
    while (sc.hasNextLine()) {
      list.add(sc.nextLine());
    }

    return list;

  }

  /**
   * Method for writing the highscores. The highscores are saved in highscores.txt .
   * 
   * @throws IOException
   */
  public static void writeHighscore() throws IOException {
    for (int i = 0; i < list.size(); i++) {
      if (score >= Integer.parseInt(list.get(i).substring(3))) {
        list.remove(i);
        list.add(i, i + ". " + score);
        break;
      }

    }
    fixHighscoreCount(list);

    try (FileWriter fw = new FileWriter("highscores")) {
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
   * @return the correct highscore list
   */
  public static ArrayList<String> fixHighscoreCount(ArrayList<String> list) {
    for (int i = 0; i < list.size(); i++) {
      String newEntry = list.get(i);
      list.remove(i);

      newEntry = (i + 1) + ". " + newEntry.substring(3);
      list.add(i, newEntry);
    }

    return list;
  }

  /**
   * Makes a nicely displayed string of the highscore
   */

  public String makeString() {
    String res = "";
    String li = System.lineSeparator();
    for (int i = 0; i < list.size(); i++) {
      res = res + list.get(i) + li;
    }

    return res;
  }
}

package lasersharks;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Highscores {
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
}

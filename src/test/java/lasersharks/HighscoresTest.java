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

  @After
  public void tearDown() throws Exception {

  }

  public void rewrite() throws IOException {

  }

  @Test
  public void testReadHighscores() throws IOException {
  System.out.println(Highscores.readHighscore()); 
  }

}

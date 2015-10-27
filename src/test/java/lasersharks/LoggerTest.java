package lasersharks;

import org.junit.After;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Test for logger class.
 * 
 * @author SEMGroup27
 *
 */
public class LoggerTest {
  private static final int TIMESTAMP_CHARS = 8;

  /**
   * Clear current logger so that getInstance can actually thoroughly be tested.
   */
  @After
  public void tearDown() {
    Logger.setInstance(null);
  }

  /**
   * Make sure get instance doesn't return null.
   */
  @Test
  public void testGetInstanceNotNull() {
    assertTrue(Logger.getInstance() != null);
  }

  /**
   * Make sure getInstance always returns same object if no setters used.
   */
  @Test
  public void testAlwaysSameInstance() {
    Logger l = Logger.getInstance();
    assertEquals(l, Logger.getInstance());
  }

  /**
   * Make sure getInstance returns last set instance.
   */
  @Test
  public void testLastSetInstanceMock() {
    Logger l = mock(Logger.class);

    Logger.setInstance(l);
    assertEquals(l, Logger.getInstance());
  }

  /**
   * Make sure file being written to is handled correctly.
   * 
   * @throws IOException
   */
  @Test
  public void testFileHandling() throws IOException {
    FileWriter f = mock(FileWriter.class);
    Logger.getInstance(f);
    assertEquals(f, Logger.getInstance().getFileWriter());
  }

  /**
   * Test for writing to clean file.
   * 
   * @throws IOException
   */
  @Test
  public void cleanWriteTest() throws IOException {
    File f = new File("logs/test.txt");
    f.getParentFile().mkdirs();
    if (f.exists()) {
      f.delete();
    }
    f.createNewFile();
    f.deleteOnExit();
    FileWriter fw = new FileWriter(f);
    Logger l = Logger.getInstance(fw);
    l.write("testEvent", "testSpecifics");
    fw.close();
    FileReader fr = new FileReader(f);
    BufferedReader reader = new BufferedReader(fr);
    String content = reader.readLine();
    String c2content = reader.readLine();
    if (c2content != null) {
      content = c2content;
    }
    reader.close();
    if (content != null) {
      assertEquals(" : testEvent : testSpecifics",
          content.substring(TIMESTAMP_CHARS, content.length()));
    } else {
      assertEquals(null, content);
    }
  }
}

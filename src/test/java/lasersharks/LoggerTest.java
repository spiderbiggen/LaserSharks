package lasersharks;

import org.junit.After;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;

/**
 * Test for logger classe.
 * 
 * @author SEMGroup27
 *
 */
public class LoggerTest {
  private static final int TIMESTAMP_CHARS = 8;

  /**
   * Clear current logger so that getInstance can actualy thourowly be tested.
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
    assertNotSame(null, Logger.getInstance());
  }

  /**
   * Make sure getInstance always returns same object if no setters used.
   */
  @Test
  public void testAlwaysSameInstance() {
    final Logger l = Logger.getInstance();
    assertEquals(l, Logger.getInstance());
  }

  /**
   * Make sure getInstance returns last set instance.
   */
  @Test
  public void testLastSetInstanceMock() {
    final Logger l = mock(Logger.class);

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
    final FileWriter f = mock(FileWriter.class);
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
    final File f = new File("logs/test.txt");
    f.getParentFile().mkdirs();
    if (f.exists()) {
      f.delete();
    }
    f.createNewFile();
    f.deleteOnExit();
    final FileWriter fw = new FileWriter(f);
    final Logger l = Logger.getInstance(fw);
    l.write("testevent", "testspecifics");
    fw.close();
    final FileReader fr = new FileReader(f);
    final BufferedReader reader = new BufferedReader(fr);
    String content = reader.readLine();
    final String c2content = reader.readLine();
    if (c2content != null) {
      content = c2content;
    }
    reader.close();
    if (content == null) {
      assertEquals(null, content);
    } else {
      assertEquals(" : testevent : testspecifics",
          content.substring(TIMESTAMP_CHARS, content.length()));
    }
  }
}

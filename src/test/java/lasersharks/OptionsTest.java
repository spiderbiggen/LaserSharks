package lasersharks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Toolkit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

/**
 * A unit test object for the Options class.
 * 
 * @author SEMGroup27
 *
 */
public class OptionsTest {

  private static final int X_RESOLUTION = 1100;
  private static final int Y_RESOLUTION = 1000;
  private static final int X_RESOLUTION_OTHER = 2200;
  private static final int Y_RESOLUTION_OTHER = 2000;
  private static final Dimension DEFAULT_DIMENSION = new Dimension(1920, 1080);
  private Options options;

  private Dimension dim;
  private Dimension dimOther;

  /**
   * Sets up a few objects for testing.
   */
  @Before
  public void setUp() {
    dim = new Dimension(X_RESOLUTION, Y_RESOLUTION);
    dimOther = new Dimension(X_RESOLUTION_OTHER, Y_RESOLUTION_OTHER);
    options = new Options(dim);
  }

  /**
   * Make sure everything is cleaned up properly.
   */
  @After
  public void tearDown() {
    Options.destroyInstance();
  }

  /**
   * We test if we can set a new interface.
   */
  @Test
  public void testSetInstance() {
    Options.setInstance(new Options(dim));
    assertEquals(Options.getInstance(), new Options(dim));
  }

  /**
   * We test if a new options object is created when the current instance is zero.
   */
  @Test
  public void testNewInstance() {
    Options.setInstance(null);

    try {
      // in case of a non-maven test, it should equal the screen SIZE of the monitor.
      assertEquals(Options.getInstance().getDimension(),
          Toolkit.getDefaultToolkit().getScreenSize());
      // in case of a maven test, the dimension has 1920x1080 values.
    } catch (HeadlessException e) {
      assertEquals(Options.getInstance().getDimension(), DEFAULT_DIMENSION);
    }
  }

  /**
   * Tests for the equals method.
   */
  @Test
  public void testEqualsFalseNull() {
    assertNotEquals(options, null);
  }

  /**
   * Tests for the equals method.
   */
  @Test
  public void testEqualsFalseWrongValues() {
    assertFalse(options.equals(new Options(dimOther)));
  }

  /**
   * Tests for the equals method.
   */
  @Test
  public void testEqualsTrueCorrectValues() {
    assertEquals(options, new Options(dim));
  }

  /**
   * Tests for the equals method.
   */
  @Test
  public void testEqualsTrueSame() {
    assertEquals(options, options);
  }

}

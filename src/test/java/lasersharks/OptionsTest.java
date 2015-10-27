package lasersharks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * A unit test object for the Options class.
 * 
 * @author SEMGroup27
 *
 */
public class OptionsTest {

  private Options options;
  private static final int X_RES = 1100;
  private static final int Y_RES = 1000;
  private static final int X_RES_OTHER = 2200;
  private static final int Y_RES_OTHER = 2000;
  private Dimension dim;
  private Dimension dimOther;
  private static final Dimension DEFAULT_DIM = new Dimension(1920, 1080);

  /**
   * Sets up a few objects for testing.
   */
  @Before
  public void setUp() {
    dim = new Dimension(X_RES, Y_RES);
    dimOther = new Dimension(X_RES_OTHER, Y_RES_OTHER);
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
      // in case of a non-maven test, it should equal the screen size of the monitor.
      assertEquals(Options.getInstance().getDimension(),
          Toolkit.getDefaultToolkit().getScreenSize());
      // in case of a maven test, the dimension has 1920x1080 values.
    } catch (Exception e) {
      assertEquals(Options.getInstance().getDimension(), DEFAULT_DIM);
    }
  }

  /**
   * Tests for the equals method.
   */
  @Test
  public void testEqualsFalseNull() {
    assertFalse(options.equals(null));
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
    assertTrue(options.equals(new Options(dim)));
  }

  /**
   * Tests for the equals method.
   */
  @Test
  public void testEqualsTrueSame() {
    assertTrue(options.equals(options));
  }

}

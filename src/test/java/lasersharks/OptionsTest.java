package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Before;
import org.junit.Test;

/**
 * A unit test object for the Options class.
 * @author sytze
 *
 */
public class OptionsTest {
  
  private Options options;
  private static final int XRES = 1100;
  private static final int YRES = 1000;
  private static final int XRES_OTHER = 2200;
  private static final int YRES_OTHER = 2000;
  private Dimension dim;
  private Dimension dimOther;
  private static final Dimension DEFAULTDIM = new Dimension(1920, 1080);
  
  /**
   * Sets up a few objects for testing.
   */
  @Before
  public void setUp() {
    dim = new Dimension(XRES, YRES);
    dimOther = new Dimension(XRES_OTHER, YRES_OTHER);
    options = new Options(dim);
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
    //in case of a non-maven test, it should equal the screen size of the monitor.
    assertEquals(
        Options.getInstance().getDimension(),
        Toolkit.getDefaultToolkit().getScreenSize()
        );
    //in case of a maven test, the dimensionhas 1920x1080 values.
    } catch (Exception e) {
      assertEquals(
          Options.getInstance().getDimension(),
          DEFAULTDIM
          );
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

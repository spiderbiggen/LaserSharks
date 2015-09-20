package lasersharks;

import static org.junit.Assert.*;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class OptionsTest {
  
  private Options options;
  private static int XRES = 1100;
  private static int YRES = 1000;
  private static int XRES_OTHER = 2200;
  private static int YRES_OTHER = 2000;
  private Dimension dim;
  private Dimension dimOther;
  
  @Before
  public void setUp() throws Exception {
    dim = new Dimension(XRES,YRES);
    dimOther = new Dimension(XRES_OTHER,YRES_OTHER);
    options = new Options(dim);
  }
  
  @Test
  public void testSetInstance() {
    Options.setInstance(new Options(dim));
    assertEquals(Options.getInstance(), new Options(dim));
  }
  
  @Test
  public void testNewInstance() {
    Options.setInstance(null);
    assertEquals(
        Options.getInstance().getDimension(),
        Toolkit.getDefaultToolkit().getScreenSize()
        );
  }
  
  @Test
  public void testEqualsFalseNull() {
    assertFalse(options.equals(null));
  }
  
  @Test
  public void testEqualsFalseWrongValues() {
    assertFalse(options.equals(new Options(dimOther)));
  }
  
  @Test
  public void testEqualsTrueCorrectValues() {
    assertTrue(options.equals(new Options(dim)));
  }
  
  @Test
  public void testEqualsTrueSame() {
    assertTrue(options.equals(options));
  }

}

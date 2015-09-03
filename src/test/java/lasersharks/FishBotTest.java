package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.*;

public class FishBotTest {
  
  FishBot fishbot1;
  FishBot fishbot2;
  FishBot generatedFish;
  Position posOnScreen;
  Position posOffScreen;

  @Before
  public void setUp() throws Exception {
    posOnScreen = new Position(50,50);
    posOffScreen = new Position(-1,-1);
    fishbot1 = new FishBot(posOnScreen,30,40,Direction.East);
    fishbot2 = new FishBot(posOffScreen,30,40,Direction.East);
  }

  @After
  public void tearDown() throws Exception {
    
  }

  @Test
  public void testFishBot() {
  }

  @Test
  public void testGetPosition() {
  }

  @Test
  public void testGetSize() {
  }

  @Test
  public void testGetSpeed() {
  }

  @Test
  public void testGetDirection() {
  }

  @Test
  public void testCollision() {
  }

  @Test
  public void testMove() {
    int oldX = fishbot1.getPosition().getPosX();
    fishbot1.move();
    int newX = fishbot1.getPosition().getPosX();
    assertEquals(oldX+fishbot1.getSpeed(), newX, 0);
    
  }

  @Test
  public void testRandomFish() {
    generatedFish = FishBot.generateFish();
    System.out.println(generatedFish.toString());
  }

  @Test
  public void testIsOnScreen() {
    assert(fishbot1.isOnScreen());
  }
  
  @Test
  public void testIsOffScreen() {
    assertFalse(fishbot2.isOnScreen());
  }


}

package lasersharks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.Random;

import org.junit.*;

public class FishBotTest {
  
  FishBot fishbot1;
  FishBot fishbot2;
  FishBot generatedFish;
  Position posOnScreen;
  Position posOffScreen;
  Random seed;

  @Before
  public void setUp() throws Exception {
    posOnScreen = new Position(50, 50);
    posOffScreen = new Position(-1, -1);
    fishbot1 = new FishBot(posOnScreen, 30, 40, Direction.East);
    fishbot2 = new FishBot(posOffScreen, 30, 40, Direction.West);
  }

  @Test
  public void testGetPosition() {
    assertEquals(posOnScreen,fishbot1.getPosition());
  }

  @Test
  public void testGetSize() {
    assertEquals(fishbot1.getSize(),30,0);
  }

  @Test
  public void testGetSpeed() {
    assertEquals(fishbot1.getSpeed(),40,0);
  }

  @Test
  public void testGetDirection() {
    assertEquals(fishbot1.getDirection(), Direction.East);
  }

  @Test
  public void testCollisionFalse() {
    assertFalse(fishbot1.collision(fishbot2));
  }

  @Test
  public void testCollisionTrue() {
    FishBot fishbot3 = fishbot1;
    assertFalse(fishbot3.collision(fishbot2));
    fishbot3.move();
    assert(fishbot3.collision(fishbot2));
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

    seed = new Random(10);
    FishBot.setRng(seed);
    generatedFish = FishBot.generateFish();
    System.out.println(generatedFish.toString());
    assertEquals(generatedFish.getDirection(), Direction.East);
    assertEquals(generatedFish.getPosition(), new Position(1920,481));
    assertEquals(generatedFish.getSize(), 10, 0);
    assertEquals(generatedFish.getSpeed(), 12, 0);
    generatedFish = FishBot.generateFish();
    System.out.println(generatedFish.toString());
    assertEquals(generatedFish.getDirection(), Direction.West);
    assertEquals(generatedFish.getPosition(), new Position(0, 725));
    assertEquals(generatedFish.getSize(), 10, 0);
    assertEquals(generatedFish.getSpeed(), 11, 0);
  }

  @Test
  public void testIsOnScreen() {
    assert(fishbot1.onScreen());
  }
  
  @Test
  public void testIsOffScreen() {
    assertFalse(fishbot2.onScreen());
  }
}

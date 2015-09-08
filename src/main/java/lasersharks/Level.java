package lasersharks;

import java.sql.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Level {
  FishController fishCon;
  LaserShark shark;
  ScreenController screenCon;
  Boolean running;
  List<Fish> currentFish;
  Timer timer;
  TimerTask task;
  Date date;
  int score;
  
  private static int startSize = 20;
  private static long tickTime = 20;
  
  /**
   * this is the constructor of the level class.
   */
  public Level() {
    fishCon = new FishController();
    shark = new LaserShark(Position.middlePosition(), 1.0f, startSize, Direction.East);
    running = false;
    fishCon.addFish(shark);
    timer = new Timer();
  }
  
  /**
   * calling this method causes the timertask to start.
   */
  public void start() {
    running = true;
    
    timer.schedule(new TimerTask() {      
      @Override
      public void run() {
        gameLoop();       
      }
    }, new Date(0), tickTime);
  }
  
  /**
   * calling this method causes the timerTask to stop.
   */
  public void stop() {
    running = false;
    //TODO: stop the ticker
  }
  
  /**
   * this function is run at each tick.
   * It includes checking for collisions, 
   */
  private void gameLoop() {
    currentFish = fishCon.getNextCycleInformation();
   // screenCon.projectFish(currentFish);    
    for(int i=0;i<currentFish.size();i++){
      Fish fish = currentFish.get(i);
      if(shark.collision(fish) && !shark.equals(fish)){
        if(shark.getSize() < fish.getSize()) {
          shark.eat(fish);
        } else {
          //TODO: implement that the game ends because the fish is larger than the shark
        }
      }
    }
  }

  /**
   * @return the fishCon
   */
  public FishController getFishCon() {
    return fishCon;
  }

  /**
   * @param fishCon the fishCon to set
   */
  public void setFishCon(FishController fishCon) {
    this.fishCon = fishCon;
  }

  /**
   * @return the shark
   */
  public LaserShark getShark() {
    return shark;
  }

  /**
   * @param shark the shark to set
   */
  public void setShark(LaserShark shark) {
    this.shark = shark;
  }

  /**
   * @return the screenCon
   */
  public ScreenController getScreenCon() {
    return screenCon;
  }

  /**
   * @param screenCon the screenCon to set
   */
  public void setScreenCon(ScreenController screenCon) {
    this.screenCon = screenCon;
  }

  /**
   * @return the running
   */
  public Boolean getRunning() {
    return running;
  }

  /**
   * @param running the running to set
   */
  public void setRunning(Boolean running) {
    this.running = running;
  }

  /**
   * @return the currentFish
   */
  public List<Fish> getCurrentFish() {
    return currentFish;
  }

  /**
   * @param currentFish the currentFish to set
   */
  public void setCurrentFish(List<Fish> currentFish) {
    this.currentFish = currentFish;
  }

  /**
   * @return the timer
   */
  public Timer getTimer() {
    return timer;
  }

  /**
   * @param timer the timer to set
   */
  public void setTimer(Timer timer) {
    this.timer = timer;
  }

  /**
   * @return the task
   */
  public TimerTask getTask() {
    return task;
  }

  /**
   * @param task the task to set
   */
  public void setTask(TimerTask task) {
    this.task = task;
  }

  /**
   * @return the date
   */
  public Date getDate() {
    return date;
  }

  /**
   * @param date the date to set
   */
  public void setDate(Date date) {
    this.date = date;
  }

  /**
   * @return the score
   */
  public int getScore() {
    return score;
  }

  /**
   * @param score the score to set
   */
  public void setScore(int score) {
    this.score = score;
  }

  /**
   * @return the startSize
   */
  public static int getStartSize() {
    return startSize;
  }

  /**
   * @param startSize the startSize to set
   */
  public static void setStartSize(int startSize) {
    Level.startSize = startSize;
  }

  /**
   * @return the tickTime
   */
  public static long getTickTime() {
    return tickTime;
  }

  /**
   * @param tickTime the tickTime to set
   */
  public static void setTickTime(long tickTime) {
    Level.tickTime = tickTime;
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Level [fishCon=" + fishCon + ", shark=" + shark + ", screenCon=" + screenCon
        + ", running=" + running + ", currentFish=" + currentFish + ", timer=" + timer + ", task="
        + task + ", date=" + date + ", score=" + score + "]";
  }
  
  
  
}

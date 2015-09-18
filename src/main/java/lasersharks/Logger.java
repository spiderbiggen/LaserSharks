package lasersharks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for logging game events.
 */
public class Logger {
  private static Logger instance;
  private static final String LOG_DIRECTORY = "logs/";
  private FileWriter fileWriter;
  private PrintWriter printWriter;
  private DateFormat dateFormat;

  /**
   * Create an instance of the logger class.
   * 
   * @param fileWriter
   *          file to use.
   */
  protected Logger(FileWriter fileWriter) {
    this.fileWriter = fileWriter;
    this.printWriter = new PrintWriter(new BufferedWriter(this.fileWriter));
    this.dateFormat = new SimpleDateFormat("HH:mm:ss");
    Logger.instance = this;
    this.printWriter.println("__________________ LAUNCHED ________________");
  }
  
  /**
   * getCurrentFileWriter.
   * @return current file writer.
   */
  public FileWriter getFileWriter() {
    return fileWriter;
  }

  /**
   * Write event to log.
   * @param event the event that happend.
   * @param specifics the specifics about the event.
   */
  public void write(String event, String specifics) {
    this.printWriter.println(
        dateFormat.format(new Date())
        + " : "
        + event
        + " : "
        + specifics
        + "\n"
    );
    this.printWriter.flush();
  }
  
  /**
   * Get correct instance of Logger class. If it currently doesn't exists one is created.
   * 
   * @return Logger class.
   */
  public static Logger getInstance() {
    if (Logger.instance != null) {
      return Logger.instance;
    }
    
    DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    FileWriter fw;
    try {
      File f = new File(
          LOG_DIRECTORY + dateFormat.format(new Date()) + ".txt"
      );
      
      f.getParentFile().mkdirs();
      if (!f.exists()) {
        f.createNewFile();
      }
      fw = new FileWriter(f, true);
    } catch (IOException e) {
      System.err.println(e);
      return null;
    }
    
    return Logger.getInstance(fw);
  }
  
  /**
   * Get instance of Logger class, if none exists one is created using the given filename.
   * @param filename filename to use.
   * @return instance of Logger Class.
   */
  public static Logger getInstance(FileWriter filename) {
    if (Logger.instance == null) {
      new Logger(filename);
    }
    return Logger.instance;
  }
  
  /**
   * Method so we can mock logger in tests of other classes.
   * @param logger logger to be used.
   */
  public static void setInstance(Logger logger) {
    Logger.instance = logger;
  }
}

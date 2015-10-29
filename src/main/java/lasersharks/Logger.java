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
 * 
 * @author SEMGroup27
 */
public class Logger {

  /**
   * The relative URI to the log directory.
   */
  private static final String LOG_DIRECTORY = "logs/";
  /**
   * The singleton instance of this class.
   */
  private static Logger instance;
  /**
   * The main fileWriter used to write to the log file.
   */
  private final FileWriter fileWriter;
  /**
   * The printWriter used by this logger.
   */
  private final PrintWriter printWriter;
  /**
   * DateFormat to get the time into the log.
   */
  private final DateFormat dateFormat;

  /**
   * Create an instance of the logger class.
   * 
   * @param fileWriter
   *          file to use.
   */
  protected Logger(final FileWriter fileWriter) {
    this.fileWriter = fileWriter;
    this.printWriter = new PrintWriter(new BufferedWriter(this.fileWriter));
    this.dateFormat = new SimpleDateFormat("HH:mm:ss");
    Logger.setInstance(this);
    this.printWriter.println("__________________ LAUNCHED ________________");
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

    final DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    FileWriter fw;
    try {
      final File f = new File(LOG_DIRECTORY + dateFormat.format(new Date()) + ".txt");

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
   * Method so we can mock logger in tests of other classes.
   *
   * @param logger
   *          logger to be used.
   */
  public static void setInstance(final Logger logger) {
    Logger.instance = logger;
  }

  /**
   * Get instance of Logger class, if none exists one is created using the given filename.
   *
   * @param filename
   *          filename to use.
   * @return instance of Logger Class.
   */
  public static Logger getInstance(final FileWriter filename) {
    if (Logger.instance == null) {
      new Logger(filename);
    }
    return Logger.instance;
  }

  /**
   * getCurrentFileWriter.
   *
   * @return current file writer.
   */
  public FileWriter getFileWriter() {
    return fileWriter;
  }

  /**
   * Write event to log.
   *
   * @param event
   *          the event that happened.
   * @param specifics
   *          the specifics about the event.
   */
  public void write(String event, String specifics) {
    this.printWriter
        .println(String.format("%s : %s : %s\n", dateFormat.format(new Date()), event, specifics));
    this.printWriter.flush();
  }
}

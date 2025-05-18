package se.kth.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for the log.
 */
public class LogHandler {
    private static final String LOG_FILE_NAME = "pos-log.txt";
    private PrintWriter logFile;

    /**
     * Creates a new instance, used for logging to a file.
     */
    public LogHandler() throws IOException {
        logFile = new PrintWriter(
                  new FileWriter(LOG_FILE_NAME), true);
    }

    /**
     * Writes a log entry that describes a thrown exception.
     * @param exception The exception that is going to be logged.
     */
    public void logException(Exception exception) {
        String timestamp = createTime();
        String message = exception.getMessage();
        
        logFile.printf("%s, Exception was thrown: %s%n", timestamp, message);
        exception.printStackTrace(logFile);
        logFile.println("\n");
    }

    private String createTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        return now.format(formatter);
    }
}

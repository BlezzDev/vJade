package com.blezzdev.vjade.core.debug;

import com.blezzdev.vjade.util.types.log.LogLevel;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/** The {@code Logger} class is a debugging tool
 * responsible for managing <b>custom developer logs</b>.
 *
 * <p>The only parameter for this tool is the <b>directory</b>
 * where the path to the logs will be saved.</p>
 *
 * <p>This tool has 5 levels representing different types of logs
 * (extracted from the {@link LogLevel} enum):</p>
 *
 * <ul>
 *     <li>{@link #success(String)}, recommended for use to show that a process
 *      was carried out correctly and without errors or problems, highlighted in green.</li>
 *     <li>{@link #info(String)}, recommended for use when reporting neutral information.</li>
 *     <li>{@link #warn(String)}, recommended for use if certain errors occur that are
 *     not serious enough to stop the entire program, highlighted in yellow.</li>
 *     <li>{@link #error(String)}, recommended for use in fatal errors, highlighted in a bolded red.</li>
 * </ul>
 * */

public class Logger {
    private String directory;

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final Map<LogLevel, String> color = new HashMap<>();

    public Logger() {
        color.put(LogLevel.NOTIFICATION, "\u001B[1m");
        color.put(LogLevel.SUCCESS, "\u001B[32m");
        color.put(LogLevel.INFO, "\u001B[0m");
        color.put(LogLevel.WARN, "\u001B[33m");
        color.put(LogLevel.ERROR, "\u001B[1;31m");
    }

    public void log(LogLevel level, String message) {
        String time = LocalTime.now().format(timeFormatter);
        String text = String.format("[%s] [%s]: %s%n", time, level, message);

        System.out.printf(color.get(level) + text + "\u001B[0m");

        verifyDirectory(text);
    }

    private void verifyDirectory(String text) {
        if (directory != null) {
            String fileName = LocalDateTime.now().format(fileFormatter) + ".log";
            try (FileWriter writer = new FileWriter(directory + "/" + fileName, true)) {
                writer.write(text);
            } catch (IOException e) {
                throw new RuntimeException("Error writing log to file: ", e);
            }
        }
    }

    public void success(String message) { log(LogLevel.SUCCESS, message); }
    public void info(String message) { log(LogLevel.INFO, message); }
    public void warn(String message) { log(LogLevel.WARN, message); }
    public void error(String message) { log(LogLevel.ERROR, message); }

    public void setLogDirectory(String directory) {
        this.directory = directory;
    }
}

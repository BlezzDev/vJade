package com.blezzdev.vjade.core.engine;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private String directory;

    public enum Level {
        SUCCESS, INFO, WARN, ERROR
    }

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final Map<Level, String> color = new HashMap<>();

    public Logger() {
        color.put(Level.SUCCESS, "\u001B[32m");
        color.put(Level.INFO, "\u001B[0m");
        color.put(Level.WARN, "\u001B[33m");
        color.put(Level.ERROR, "\u001B[1;31m");
    }

    public void log(Level level, String message) {
        String time = LocalTime.now().format(timeFormatter);
        String text = String.format("[%s] [%s]: %s%n", time, level, message);

        System.out.printf(color.get(level) + text + "\u001B[0m");

        if (directory != null) {
            String fileName = LocalDateTime.now().format(fileFormatter) + ".log";
            try (FileWriter writer = new FileWriter(directory + "/" + fileName, true)) {
                writer.write(text);
            } catch (IOException e) {
                throw new RuntimeException("Error escribiendo log en archivo", e);
            }
        }
    }

    public void success(String message) { log(Level.SUCCESS, message); }
    public void info(String message) { log(Level.INFO, message); }
    public void warn(String message) { log(Level.WARN, message); }
    public void error(String message) { log(Level.ERROR, message); }

    public void setLogDirectory(String directory) {
        this.directory = directory;
    }
}

package com.blezzdev.vjade.core.engine.logger;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    public enum Level {
        INFO, WARN, ERROR
    }

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final Map<Level, String> color = new HashMap<>(); {
        color.put(Level.INFO, "\u001B[0m");
        color.put(Level.WARN, "\u001B[33m");
        color.put(Level.ERROR, "\u001B[1;31m");
    }

    public void log(Level level, String message) {
        String time = LocalTime.now().format(formatter);
        System.out.printf(color.get(level) + "[%s] [%s]: %s%n\u001B[0m", time, level, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warn(String message) {
        log(Level.WARN, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }
}

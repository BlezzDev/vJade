package com.blezzdev.vjade.core.manager.timer;

import java.util.ArrayList;
import java.util.List;

public class TimerManager {
    private final List<Timer> timerList = new ArrayList<>();

    public void timerProcesses(double deltaTime) {
        for (int i = 0; i < timerList.size(); i++) {
            Timer time = timerList.get(i);

            time.step((float) deltaTime);

            if (time.getCurrentTime() >= time.getTimeWait()) {
                time.exec();
                timerList.remove(time);
            }
        }
    }

    public void clear() {
        timerList.clear();
    }

    public void delay(float seconds, Runnable function) {
        timerList.add(new Timer(seconds, function));
    }
}
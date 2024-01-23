package com.lapchenko.pomodoro.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TimerManager {

    private final static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    public static Timer createTimer(TimerObserver observer) {
        return new Timer(scheduler, observer);
    }
}

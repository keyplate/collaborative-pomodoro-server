package com.lapchenko.pomodoro.timer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Timer {

    private final ScheduledExecutorService clock = Executors.newScheduledThreadPool(2);
    private long remainingTime;

    public void start(long remainingTimeSeconds) {

    }
}

package com.lapchenko.pomodoro.timer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Timer {

    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> scheduledFuture;
    private long remainingTime;
    private final TimerObserver observer;

    public Timer(ScheduledExecutorService scheduler, TimerObserver observer) {
        this.scheduler = scheduler;
        this.observer = observer;
    }


    public void start(long durationSeconds) {
        this.remainingTime = durationSeconds;
        final Runnable timerTask = () -> {
            remainingTime--;
            if (remainingTime <= 0) {
                scheduledFuture.cancel(true);
                observer.timerStopped();
            }
        };
        scheduledFuture = scheduler.scheduleWithFixedDelay(timerTask, 0, 1, TimeUnit.SECONDS);
        observer.timerStarted();
    }


    public void stop() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
            observer.timerStopped();
        }
    }
}


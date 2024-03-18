package com.lapchenko.pomodoro.timer;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Timer {

    private final ScheduledExecutorService scheduler;
    private final TimerObserver observer;
    private ScheduledFuture<?> scheduledFuture;
    private int remainingTime;

    public Timer(ScheduledExecutorService scheduler, TimerObserver observer) {
        this.scheduler = scheduler;
        this.observer = observer;
    }


    public void start(int durationSeconds) {
        this.remainingTime = durationSeconds;
        final Runnable timerTask = () -> {
            if (remainingTime <= 0) {
                scheduledFuture.cancel(true);
                observer.timedOut();
            }
            remainingTime--;
            observer.timeUpdated(remainingTime);
        };
        scheduledFuture = scheduler.scheduleWithFixedDelay(timerTask, 0, 1, TimeUnit.SECONDS);
    }

    public void resume() {
        if (remainingTime > 0) {
            start(remainingTime);
        }
    }


    public void stop() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    public int getRemainingTime() {
        return remainingTime;
    }
}


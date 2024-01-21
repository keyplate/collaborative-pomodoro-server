package com.lapchenko.pomodoro.timer;

import org.springframework.stereotype.Service;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
public class TimerManager {

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(10);

    public Timer createTimer(TimerObserver observer) {
        return new Timer(scheduler, observer);
    }
}

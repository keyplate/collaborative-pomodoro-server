package com.lapchenko.pomodoro.pomodorotimer;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroConfig;
import com.lapchenko.pomodoro.timer.Timer;
import com.lapchenko.pomodoro.timer.TimerManager;
import com.lapchenko.pomodoro.timer.TimerObserver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PomodoroRoom implements TimerObserver {

    private final TimerManager timerManager;
    private PomodoroState currentPomodoro;
    private PomodoroConfig pomodoroConfig;
    private int breaksCounter;
    private Timer timer;

    public PomodoroRoom(TimerManager timerManager, PomodoroConfig pomodoroConfig) {
        this.timer = timerManager.createTimer(this);
        this.currentPomodoro = PomodoroState.FOCUS;
        this.pomodoroConfig = pomodoroConfig;
        this.timerManager = timerManager;
        this.breaksCounter = 0;
    }

    public void startTimer() {
        //todo if (not running)
        timer.start(pomodoroConfig.sessionDurations().get(currentPomodoro));
    }

    public void nextSession() {
        this.currentPomodoro = PomodoroState.getNext(currentPomodoro,
                pomodoroConfig.breaksBeforeLongBreak(), breaksCounter);
        startTimer();
    }

    public void pauseTimer() {
        timer.stop();
    }

    @Override
    public void timedOut() {
    }
}

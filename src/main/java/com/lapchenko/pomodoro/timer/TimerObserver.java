package com.lapchenko.pomodoro.timer;

public interface TimerObserver {

    void timeUpdated(int newTime);

    void timedOut();

}

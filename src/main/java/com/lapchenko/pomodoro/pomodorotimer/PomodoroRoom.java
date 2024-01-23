package com.lapchenko.pomodoro.pomodorotimer;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroUpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.model.TimerUpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.notifier.PomodoroNotifier;
import com.lapchenko.pomodoro.timer.Timer;
import com.lapchenko.pomodoro.timer.TimerManager;
import com.lapchenko.pomodoro.timer.TimerObserver;

public class PomodoroRoom implements TimerObserver {
    private final Timer timer;
    private final String roomId;
    private final PomodoroNotifier notifier;

    public PomodoroRoom(String roomId, PomodoroNotifier notifier) {
        this.timer = TimerManager.createTimer(this);
        this.roomId = roomId;
        this.notifier = notifier;
    }

    public void startTimer(int duration) {
        timer.start(duration);
        notifier.publishPomodoroUpdate(roomId,
                new PomodoroUpdateMessage(PomodoroState.START, String.valueOf(duration)));
    }

    public void pauseTimer() {
        timer.stop();
        notifier.publishPomodoroUpdate(roomId,
                new PomodoroUpdateMessage(PomodoroState.PAUSE, null));
    }

    @Override
    public void timeUpdated(int remainingTime) {
        notifier.publishTimerUpdate(roomId,
                new TimerUpdateMessage(roomId, remainingTime));
    }

    @Override
    public void timedOut() {
        notifier.publishPomodoroUpdate(roomId,
                new PomodoroUpdateMessage(PomodoroState.STOP, null));
    }
}

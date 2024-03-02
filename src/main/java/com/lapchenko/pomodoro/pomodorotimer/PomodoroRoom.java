package com.lapchenko.pomodoro.pomodorotimer;

import com.lapchenko.pomodoro.pomodorotimer.model.message.CurrentTimeUpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.model.message.StartUpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.model.message.StopUpdateMessage;
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
        notifier.publishPomodoroUpdate(roomId, new StartUpdateMessage(duration));
    }

    public void pauseTimer() {
        timer.stop();
        notifier.publishPomodoroUpdate(roomId,
                new StopUpdateMessage());
    }

    @Override
    public void timeUpdated(int remainingTime) {
        notifier.publishPomodoroUpdate(roomId,
                new CurrentTimeUpdateMessage(remainingTime));
    }

    @Override
    public void timedOut() {
        //todo
    }
}

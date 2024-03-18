package com.lapchenko.pomodoro.pomodorotimer;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroUpdate;
import com.lapchenko.pomodoro.pomodorotimer.model.message.UpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.notifier.PomodoroNotifier;
import com.lapchenko.pomodoro.timer.Timer;
import com.lapchenko.pomodoro.timer.TimerManager;
import com.lapchenko.pomodoro.timer.TimerObserver;

public class PomodoroRoom implements TimerObserver {
    private final Timer timer;
    private final String roomId;
    private final PomodoroNotifier notifier;
    private boolean isTimerRunning;

    public PomodoroRoom(String roomId, PomodoroNotifier notifier) {
        this.timer = TimerManager.createTimer(this);
        this.roomId = roomId;
        this.notifier = notifier;
        this.isTimerRunning = false;
    }

    public void startTimer(int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Room ID: " + roomId + " arg: " + duration);
        }
        if (!isTimerRunning) {
            timer.start(duration);
            notifier.publishPomodoroUpdate(roomId, new UpdateMessage(PomodoroUpdate.STARTED, duration));
            isTimerRunning = true;
        }
    }

    public void stopTimer() {
        if (isTimerRunning) {
            timer.stop();
            notifier.publishPomodoroUpdate(roomId, new UpdateMessage(PomodoroUpdate.PAUSED, null));
            isTimerRunning = false;
        }
    }

    public void adjustTimer(int adjustmentDuration) {
        if (isTimerRunning) {
            final int updatedDuration = timer.getRemainingTime() + adjustmentDuration;
            timer.stop();
            timer.start(updatedDuration);
        }
        /*  If anyone among users of the room adjusts time on the timer,
         *  we want to see that adjustment both when timer is stopped and when it's running.
         */
        notifier.publishPomodoroUpdate(roomId, new UpdateMessage(PomodoroUpdate.DURATION_ADJUSTMENT, adjustmentDuration));
    }

    @Override
    public void timeUpdated(int remainingTime) {
        notifier.publishPomodoroUpdate(roomId, new UpdateMessage(PomodoroUpdate.TIME_UPDATE,
                remainingTime));
    }

    @Override
    public void timedOut() {
        //notifier.publishPomodoroUpdate(roomId, new UpdateMessage());
        isTimerRunning = false;
    }
}

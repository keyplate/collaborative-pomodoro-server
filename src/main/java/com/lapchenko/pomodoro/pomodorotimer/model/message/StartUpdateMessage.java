package com.lapchenko.pomodoro.pomodorotimer.model.message;

public class StartUpdateMessage extends UpdateMessage {
    private int duration;

    public StartUpdateMessage(int duration) {
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

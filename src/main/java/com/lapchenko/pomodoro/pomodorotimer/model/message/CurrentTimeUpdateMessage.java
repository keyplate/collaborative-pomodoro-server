package com.lapchenko.pomodoro.pomodorotimer.model.message;

public class CurrentTimeUpdateMessage extends UpdateMessage {
    private int currentTime;

    public int getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public CurrentTimeUpdateMessage(int currentTime) {
        this.currentTime = currentTime;
    }
}
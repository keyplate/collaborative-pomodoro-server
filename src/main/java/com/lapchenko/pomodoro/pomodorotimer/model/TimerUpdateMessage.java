package com.lapchenko.pomodoro.pomodorotimer.model;

public record TimerUpdateMessage(String roomId, int timeLeft) {
}

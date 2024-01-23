package com.lapchenko.pomodoro.pomodorotimer.model;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroState;

public record PomodoroUpdateMessage(PomodoroState command, String argument) {}

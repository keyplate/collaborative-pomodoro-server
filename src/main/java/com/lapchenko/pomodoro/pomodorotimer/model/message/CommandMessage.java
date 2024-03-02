package com.lapchenko.pomodoro.pomodorotimer.model.message;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroCommand;

public record CommandMessage (PomodoroCommand command, Object args) {}

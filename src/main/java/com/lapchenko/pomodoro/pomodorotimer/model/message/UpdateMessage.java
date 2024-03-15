package com.lapchenko.pomodoro.pomodorotimer.model.message;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroUpdate;

public record UpdateMessage(PomodoroUpdate update, Object args) {}

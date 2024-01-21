package com.lapchenko.pomodoro.pomodorotimer.model;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroState;

import java.util.Map;

public record PomodoroConfig(Map<PomodoroState, Integer> sessionDurations, int breaksBeforeLongBreak) { }

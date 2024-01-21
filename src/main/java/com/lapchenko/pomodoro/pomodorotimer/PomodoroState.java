package com.lapchenko.pomodoro.pomodorotimer;

public enum PomodoroState {
    FOCUS,
    BREAK,
    LONG_BREAK;

    public static PomodoroState getNext(PomodoroState current, int breaksBeforeLongBreak, int currentSessionCount) {
        switch (current) {
            case FOCUS:
                if (currentSessionCount >= breaksBeforeLongBreak) {
                    return LONG_BREAK;
                } else {
                    return BREAK;
                }
            case BREAK, LONG_BREAK:
                return FOCUS;
            default:
                throw new IllegalArgumentException("Unknown PomodoroState: " + current);
        }
    }
}

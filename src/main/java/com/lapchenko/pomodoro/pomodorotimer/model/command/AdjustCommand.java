package com.lapchenko.pomodoro.pomodorotimer.model.command;

public class AdjustCommand {
    private int adjustmentDuration;

    public int getAdjustmentDuration() {
        return this.adjustmentDuration;
    }

    public void setAdjustmentDuration(int adjustmentDuration) {
        this.adjustmentDuration = adjustmentDuration;
    }
}

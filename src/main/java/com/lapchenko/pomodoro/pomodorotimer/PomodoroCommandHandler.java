package com.lapchenko.pomodoro.pomodorotimer;

import com.lapchenko.pomodoro.pomodorotimer.model.command.AdjustCommand;
import com.lapchenko.pomodoro.pomodorotimer.model.message.CommandMessage;
import com.lapchenko.pomodoro.pomodorotimer.model.command.StartCommand;
import com.lapchenko.pomodoro.pomodorotimer.service.PomodoroRoomService;
import org.springframework.stereotype.Service;

@Service
public class PomodoroCommandHandler {

    private final PomodoroRoomService roomService;

    public PomodoroCommandHandler(PomodoroRoomService roomService) {
        this.roomService = roomService;
    }

    public void handle(String roomId, CommandMessage message) {
        switch (message.command()) {
            case START -> handleStart(roomId, (StartCommand) message.args());
            case STOP -> handleStop(roomId);
            case ADJUST -> handleAdjust(roomId, (AdjustCommand) message.args());
        }
    }

    private void handleStart(String roomId, StartCommand command) {
        int duration = command.getDuration();
        roomService.startTimer(roomId, duration);
    }

    private void handleStop(String roomId) {
        roomService.stopTimer(roomId);
    }

    private void handleAdjust(String roomId, AdjustCommand command) {
        int adjustmentDuration = command.getAdjustmentDuration();
        roomService.adjustTimer(roomId, adjustmentDuration);
    }
}

package com.lapchenko.pomodoro.pomodorotimer;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroUpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.service.PomodoroRoomService;
import org.springframework.stereotype.Service;

@Service
public class PomodoroCommandHandler {

    private final PomodoroRoomService roomService;

    public PomodoroCommandHandler(PomodoroRoomService roomService) {
        this.roomService = roomService;
    }

    public void handle(String roomId, PomodoroUpdateMessage message) {
        switch (message.command()) {
            case START -> handleStart(roomId, message);
            case PAUSE -> handlePause(roomId);
        }
    }

    private void handleStart(String roomId, PomodoroUpdateMessage message) {
        int duration = Integer.parseInt(message.argument());
        roomService.startTimer(roomId, duration);
    }

    private void handlePause(String roomId) {
        roomService.pauseTimer(roomId);
    }
}

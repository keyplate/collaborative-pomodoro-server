package com.lapchenko.pomodoro.pomodorotimer.controller;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroCommandHandler;
import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroUpdateMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PomodoroCommandsController {

    private final PomodoroCommandHandler commandHandler;

    public PomodoroCommandsController(PomodoroCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @MessageMapping("/room/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public PomodoroUpdateMessage send(@DestinationVariable("roomId") String roomId,
                                      PomodoroUpdateMessage message) {
        commandHandler.handle(roomId, message);
        return message;
    }
}

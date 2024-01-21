package com.lapchenko.pomodoro.pomodorotimer.controller;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroCommandMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class PomodoroCommandsController {

    @MessageMapping("/room/{roomId}")
    @SendTo("/topic/room/{roomId}")
    public PomodoroCommandMessage send(@DestinationVariable("roomId") String roomId,
                                       PomodoroCommandMessage message) {
        return message;
    }
}

package com.lapchenko.pomodoro.pomodorotimer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lapchenko.pomodoro.pomodorotimer.PomodoroCommandHandler;
import com.lapchenko.pomodoro.pomodorotimer.mapper.CommandMessageDeserializer;
import com.lapchenko.pomodoro.pomodorotimer.model.message.CommandMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Controller
public class PomodoroCommandsController {

    private final PomodoroCommandHandler commandHandler;
    private final ObjectMapper mapper;


    public PomodoroCommandsController(PomodoroCommandHandler commandHandler, ObjectMapper mapper) {
        this.mapper = mapper;
        this.commandHandler = commandHandler;

        SimpleModule module = new SimpleModule();
        module.addDeserializer(CommandMessage.class, new CommandMessageDeserializer());
        mapper.registerModule(module);
    }

    @MessageMapping("/room/{roomId}")
    public void send(@DestinationVariable("roomId") String roomId,
                               String jsonMessage) throws JsonProcessingException {
        CommandMessage message = mapper.readValue(jsonMessage, CommandMessage.class);
        commandHandler.handle(roomId, message);
    }
}

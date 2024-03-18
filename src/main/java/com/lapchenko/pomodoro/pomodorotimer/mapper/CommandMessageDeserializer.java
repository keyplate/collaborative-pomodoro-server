package com.lapchenko.pomodoro.pomodorotimer.mapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroCommand;
import com.lapchenko.pomodoro.pomodorotimer.model.command.AdjustCommand;
import com.lapchenko.pomodoro.pomodorotimer.model.message.CommandMessage;
import com.lapchenko.pomodoro.pomodorotimer.model.command.StartCommand;
import com.lapchenko.pomodoro.pomodorotimer.model.command.StopCommand;

import java.io.IOException;

public class CommandMessageDeserializer extends StdDeserializer<CommandMessage> {

    public CommandMessageDeserializer() {
        this(null);
    }

    protected CommandMessageDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CommandMessage deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        PomodoroCommand command = PomodoroCommand.valueOf(node.get("command").asText());
        JsonNode argsNode = node.get("args");

        Object args = null;
        if (command == PomodoroCommand.START) {
            args = p.getCodec().treeToValue(argsNode, StartCommand.class);
        }
        if (command == PomodoroCommand.STOP) {
            args = p.getCodec().treeToValue(argsNode, StopCommand.class);
        }
        if (command == PomodoroCommand.ADJUST) {
            args = p.getCodec().treeToValue(argsNode, AdjustCommand.class);
        }

        return new CommandMessage(command, args);
    }
}
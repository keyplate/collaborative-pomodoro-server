package com.lapchenko.pomodoro.pomodorotimer.notifier;

import com.lapchenko.pomodoro.pomodorotimer.model.message.UpdateMessage;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PomodoroNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public PomodoroNotifier(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @SendTo("/topic/room/{roomId}")
    public void publishPomodoroUpdate(@DestinationVariable("roomId") String roomId,
                                      UpdateMessage message) {
        messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
    }
}

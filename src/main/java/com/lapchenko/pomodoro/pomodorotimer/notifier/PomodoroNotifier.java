package com.lapchenko.pomodoro.pomodorotimer.notifier;

import com.lapchenko.pomodoro.pomodorotimer.model.PomodoroUpdateMessage;
import com.lapchenko.pomodoro.pomodorotimer.model.TimerUpdateMessage;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class PomodoroNotifier {

    private final SimpMessagingTemplate messagingTemplate;

    public PomodoroNotifier(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void publishPomodoroUpdate(String roomId, PomodoroUpdateMessage message) {
        messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
    }

    public void publishTimerUpdate(String roomId, TimerUpdateMessage message) {
        messagingTemplate.convertAndSend("/topic/room/" + roomId, message);
    }
}

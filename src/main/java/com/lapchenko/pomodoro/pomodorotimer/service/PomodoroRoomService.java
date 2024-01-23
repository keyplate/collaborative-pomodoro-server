package com.lapchenko.pomodoro.pomodorotimer.service;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroRoom;
import com.lapchenko.pomodoro.pomodorotimer.notifier.PomodoroNotifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class PomodoroRoomService {

    private final Map<String, PomodoroRoom> roomMap;
    private final PomodoroNotifier notifier;

    public PomodoroRoomService(PomodoroNotifier notifier) {
        this.roomMap = new HashMap<>();
        this.notifier = notifier;
    }

    public String createRoom() {
        String roomId = String.valueOf(UUID.randomUUID());
        PomodoroRoom room = new PomodoroRoom(roomId, notifier);
        roomMap.put(roomId, room);
        return roomId;
    }

    public void startTimer(String roomId, int duration) {
        getRoomIfPresent(roomId).startTimer(duration);
    }

    public void pauseTimer(String roomId) {
        getRoomIfPresent(roomId).pauseTimer();
    }

    private PomodoroRoom getRoomIfPresent(String roomId) {
        if (!roomMap.containsKey(roomId)) {
            throw new NoSuchElementException("Room ID: " + roomId);
        }
        return roomMap.get(roomId);
    }

}

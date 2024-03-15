package com.lapchenko.pomodoro.pomodorotimer.service;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroRoom;
import com.lapchenko.pomodoro.pomodorotimer.notifier.PomodoroNotifier;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public void deleteRoom(String roomId) {
        PomodoroRoom room = getRoomIfPresent(roomId);
        //todo stop timer
        roomMap.remove(roomId);
    }

    public void startTimer(String roomId, int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Room ID: " + roomId + " arg: " + duration);
        }
        getRoomIfPresent(roomId).startTimer(duration);
    }

    public void stopTimer(String roomId) {
        getRoomIfPresent(roomId).stopTimer();
    }

    public Optional<PomodoroRoom> getRoomOptional(String roomId) {
        try {
            return Optional.of(getRoomIfPresent(roomId));
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    private PomodoroRoom getRoomIfPresent(String roomId) {
        if (!roomMap.containsKey(roomId)) {
            throw new NoSuchElementException("Room ID: " + roomId);
        }
        return roomMap.get(roomId);
    }

}

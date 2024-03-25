package com.lapchenko.pomodoro.pomodorotimer.service;

import com.lapchenko.pomodoro.pomodorotimer.PomodoroRoom;
import com.lapchenko.pomodoro.pomodorotimer.notifier.PomodoroNotifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        getRoomIfPresent(roomId).deleteTimer();
        roomMap.remove(roomId);
    }

    @Scheduled(cron = "@hourly")
    private void cleanUpUnusedRooms() {
        List<String> roomsToDelete = new ArrayList<>();
        LocalDateTime considerIdleTime = LocalDateTime.now().minusHours(1);
        roomMap.forEach((key, value) -> {
            if (value.getLastActivity().isBefore(considerIdleTime)) {
                roomsToDelete.add(key);
            }
        });
        roomsToDelete.forEach(this::deleteRoom);
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
        var room = roomMap.get(roomId);
        updateRoomLastAccessTime(room);
        return room;
    }

    private void updateRoomLastAccessTime(PomodoroRoom room) {
        room.setLastActivity();
    }
}

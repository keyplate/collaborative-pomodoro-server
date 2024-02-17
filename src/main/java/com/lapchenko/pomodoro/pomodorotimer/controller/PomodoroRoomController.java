package com.lapchenko.pomodoro.pomodorotimer.controller;

import com.lapchenko.pomodoro.pomodorotimer.service.PomodoroRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PomodoroRoomController {

    private final PomodoroRoomService roomService;

    public PomodoroRoomController(PomodoroRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/pomodoro-room")
    @ResponseBody
    public ResponseEntity<Map> createRoom() {
        return ResponseEntity.ok(Map.of("roomId", roomService.createRoom()));
    }

    @DeleteMapping("/pomodoro-room/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable String id) {
        return ResponseEntity.accepted().build();
    }
}

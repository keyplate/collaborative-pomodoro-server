package com.lapchenko.pomodoro.pomodorotimer.controller;

import com.lapchenko.pomodoro.pomodorotimer.service.PomodoroRoomService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PomodoroRoomController {

    private final PomodoroRoomService roomService;

    public PomodoroRoomController(PomodoroRoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/pomodoro-room")
    @ResponseBody
    public String createRoom() {
        return roomService.createRoom();
    }
}

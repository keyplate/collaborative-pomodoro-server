package com.lapchenko.pomodoro;

import com.lapchenko.pomodoro.timer.Timer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Executors;

@SpringBootApplication
public class PomodoroApplication {

	public static void main(String[] args) {
		SpringApplication.run(PomodoroApplication.class, args);
	}

}
package main;

import house.DateTime;
import house.Weekday;
import tempcontrol.TempController;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private DateTime dateTime;
    private TempController controller;

    public Simulation(int hours, int minutes, Weekday day, TempController ctrl) {
        dateTime = new DateTime(hours, minutes, day);
        controller = ctrl;
    }

    private void tick() {
        String time = dateTime.getTime();
        Weekday day = dateTime.getDay();
        controller.tick(time, day);
        dateTime.advanceTime();
    }

    public void run() {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Runnable task = this::tick;
        executor.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);
    }
}

package main;

import house.Room;
import house.Weekday;
import tempcontrol.Schedule;
import tempcontrol.TempController;
import tempcontrol.VALVE_STEPS;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private static ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
        new Room(false, 25, 15, VALVE_STEPS.CLOSED, false, new Schedule()),
        new Room(true, 25, 40, VALVE_STEPS.OPEN, false, new Schedule()),
        new Room(false, 25, 25, VALVE_STEPS.CLOSED, false, new Schedule())
    ));

    public static void main(final String args[]) {
        TempController controller = new TempController(rooms);
        Simulation experiment = new Simulation(23, 55, Weekday.MONDAY, controller);
        experiment.run();
    }
}

package main;

import house.Room;
import house.Weekday;
import tempcontrol.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    private static ArrayList<Room> rooms = new ArrayList<>(Arrays.asList(
        new Room("Room One", false, 25, 15, VALVE_STEPS.CLOSED, new Schedule(
                new DaySchedule(new HourSchedule[]{
                    new HourSchedule(new HashMap<String, HourSchedule.Event>(){{
                        put("0:5", HourSchedule.Event.ARRIVAL);
                        put("0:25", HourSchedule.Event.DEPARTURE);
                    }}),
                    new HourSchedule("0:5", HourSchedule.Event.ARRIVAL),
                    new HourSchedule("0:5", HourSchedule.Event.ARRIVAL),
                    new HourSchedule("0:5", HourSchedule.Event.ARRIVAL),
                    new HourSchedule("0:5", HourSchedule.Event.ARRIVAL),
                    new HourSchedule("0:5", HourSchedule.Event.ARRIVAL),
                    new HourSchedule("0:5", HourSchedule.Event.ARRIVAL)
                })
        )),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 3", true, 25, 40, VALVE_STEPS.OPEN, new Schedule()),
        new Room("Room 4", false, 25, 25, VALVE_STEPS.CLOSED, new Schedule())
    ));

    public static void main(final String args[]) {
        TempController controller = new TempController(rooms);
        Simulation experiment = new Simulation(0, 0, Weekday.MONDAY, controller);
        experiment.run();
    }
}

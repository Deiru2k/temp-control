package tempcontrol;

import house.DateTime;
import house.Room;
import house.Weekday;

import java.time.Clock;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static tempcontrol.VALVE_STEPS.CLOSED;
import static tempcontrol.VALVE_STEPS.OPEN;
import static tempcontrol.VALVE_STEPS.HALF_OPEN;

public class TempController {
    private ArrayList<Room> rooms;

    public TempController(ArrayList<Room> initialRooms) {
        rooms = initialRooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    private String[] formatStats() {
        Object[] stats = rooms.stream().map((Room room) -> {
            int currentTemp = room.getCurrentTemp();
            int desiredTemp = room.getTemp();
            VALVE_STEPS valveState = room.getValve().getState();
            String present = room.getSensor().getPresence() ? "People Present" : "People Absent";
            String valveStateStr;
            if (valveState == VALVE_STEPS.OPEN) valveStateStr = "Valve Open";
            else if (valveState == VALVE_STEPS.CLOSED) valveStateStr = "Valve Closed";
            else if (valveState == VALVE_STEPS.HALF_OPEN) valveStateStr = "Valve Half-open";
            else valveStateStr = "Unknown Valve State";

            return String.format("Current: %dC | Desired: %dC | %s | %s", currentTemp, desiredTemp, present, valveStateStr);
        }).toArray();
        return Arrays.copyOf(stats, stats.length, String[].class);
    }

    public void tick(String time, Weekday day) {
        String timeSplit[] = time.split(":", 2);
        int hours = new Integer(timeSplit[0]);
        rooms.forEach((Room room) -> {
            room.tick();
            IRSensor sensor = room.getSensor();
            Valve valve = room.getValve();
            int currentTemp = room.getCurrentTemp();
            int desiredTemp = room.getTemp();

            if (currentTemp >= desiredTemp + 5) {
                valve.setState(CLOSED);
            } else if (currentTemp <= desiredTemp - 5) {
                valve.setState(OPEN);
            } else {
                valve.setState(HALF_OPEN);
            }
        });

        System.out.print(String.format("%s, %s \n", DateTime.formatDay(day), time));
        String[] stats = formatStats();
        for (int i = 0; i < stats.length; i++) {
            System.out.print(String.format("Room #%d | %s \n", i, stats[i]));
        }
    }
}

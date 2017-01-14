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
            int gasSpent = room.getGasSpent();
            VALVE_STEPS valveState = room.getValve().getState();
            String present = room.getSensor().getPresence() ? "People Present" : "People Absent";
            String valveStateStr;
            if (valveState == VALVE_STEPS.OPEN) valveStateStr = "Valve Open";
            else if (valveState == VALVE_STEPS.CLOSED) valveStateStr = "Valve Closed";
            else if (valveState == VALVE_STEPS.HALF_OPEN) valveStateStr = "Valve Half-open";
            else valveStateStr = "Unknown Valve State";

            return String.format("Current: %dC | Desired: %dC | %s | %s | Gas %d", currentTemp, desiredTemp, present, valveStateStr, gasSpent);
        }).toArray();
        return Arrays.copyOf(stats, stats.length, String[].class);
    }

    public void tick(String time, Weekday day) {
        rooms.forEach((Room room) -> {
            room.tick(time, day);
            IRSensor sensor = room.getSensor();
            Valve valve = room.getValve();
            int currentTemp = room.getCurrentTemp();
            int desiredTemp = sensor.getPresence() ? room.getTemp() : room.getTemp() - 5;

            if (currentTemp > desiredTemp) {
                valve.setState(CLOSED);
            } else if (currentTemp < desiredTemp) {
                valve.setState(OPEN);
            } else {
                valve.setState(HALF_OPEN);
            }
        });

        int totalGasSpent = rooms.stream().map(Room::getGasSpent).reduce(0, (a, b) -> a + b);
        System.out.print(String.format("%s, %s, Total Gas Spent: %d \n", DateTime.formatDay(day), time, totalGasSpent));
        String[] stats = formatStats();
        for (int i = 0; i < stats.length; i++) {
            System.out.print(String.format("Room #%d | %s\n", i, stats[i]));
        }
    }
}

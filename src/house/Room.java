package house;

import tempcontrol.*;

public class Room {
    private String name;
    private int temp;
    private int currentTemp;
    private int gasSpent = 0;
    private Schedule schedule;
    private IRSensor sensor;
    private Valve valve;


    public Room(String defaultName, boolean defaultPresence, int defaultTemp, int defaultCurrentTemp, VALVE_STEPS defaultValveState, Schedule initSchedule) {
        name = defaultName;
        temp = defaultTemp;
        currentTemp = defaultCurrentTemp;
        sensor = new IRSensor(defaultPresence);
        valve = new Valve(defaultValveState);
        schedule = initSchedule;
    }

    public Schedule getSchedule() { return schedule; }

    public void setName(String value) { name = value; }

    public String getName() { return name; }

    public void setPresence(boolean value) {
        sensor.setPresence(value);
    }

    public int getTemp() {
        return temp;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public int getGasSpent() { return gasSpent; }

    public IRSensor getSensor() {
        return sensor;
    }

    public Valve getValve() {
        return valve;
    }

    public void tick(String time, Weekday day) {
        HourSchedule.Event evt = schedule.getEvent(day, time);
        sensor.tick(evt);
        VALVE_STEPS valveState = valve.getState();
        if (valveState == VALVE_STEPS.CLOSED) {
            currentTemp -= 1;
        } else if (valveState == VALVE_STEPS.OPEN) {
            currentTemp += 1;
        }
        gasSpent += valveState.getValue();
    }
}

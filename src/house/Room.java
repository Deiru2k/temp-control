package house;

import tempcontrol.IRSensor;
import tempcontrol.Schedule;
import tempcontrol.VALVE_STEPS;
import tempcontrol.Valve;

public class Room {
    private int temp;
    private int currentTemp;
    private boolean people;
    private Schedule schedule;
    private IRSensor sensor;
    private Valve valve;


    public Room(boolean defaultPresence, int defaultTemp, int defaultCurrentTemp, VALVE_STEPS defaultValveState, boolean initPeople, Schedule initSchedule) {
        temp = defaultTemp;
        currentTemp = defaultCurrentTemp;
        sensor = new IRSensor(defaultPresence);
        valve = new Valve(defaultValveState);
        people = initPeople;
        schedule = initSchedule;
    }

    public void setPeople(boolean value) {
        people = value;
    }

    public int getTemp() {
        return temp;
    }

    public int getCurrentTemp() {
        return currentTemp;
    }

    public IRSensor getSensor() {
        return sensor;
    }

    public Valve getValve() {
        return valve;
    }

    public void tick() {
        sensor.tick();
        VALVE_STEPS valveState = valve.getState();
        if (valveState == VALVE_STEPS.CLOSED) {
            currentTemp -= 1;
        } else if (valveState == VALVE_STEPS.OPEN) {
            currentTemp += 1;
        }
    }
}

package tempcontrol;

import java.util.HashMap;
import java.util.Map;

public class HourSchedule {
    public static enum Event {
        ARRIVAL, DEPARTURE, NONE;
    }
    private Map<String, Event> schedule = new HashMap<>();

    public HourSchedule() {}
    public HourSchedule(String time, Event event) {
        HashMap<String, Event> newSch = new HashMap<>();
        newSch.put(time, event);
        schedule = newSch;
    }
    public HourSchedule(HashMap<String, Event> initSchedule) {
        schedule = initSchedule;
    }

    public Event getEvent(String time) {
        return schedule.getOrDefault(time, Event.NONE);
    }
}
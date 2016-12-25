package tempcontrol;

import house.Weekday;

import java.util.Arrays;

public class DaySchedule {
    private HourSchedule[] schedule;

    private static void checkSize(HourSchedule[] array) throws Exception {
        if (array.length != 7) {
            throw new Exception("DaySchedule must be 7 days long");
        }
    }

    private static HourSchedule[] getEmptySchedule() {
        return new HourSchedule[] {
            new HourSchedule(), new HourSchedule(), new HourSchedule(),
            new HourSchedule(), new HourSchedule(), new HourSchedule(),
            new HourSchedule(),
        };
    }

    public DaySchedule() {
        schedule = getEmptySchedule();
    }

    public DaySchedule(HourSchedule[] initialPresence) {
        try {
            checkSize(initialPresence);
            schedule = initialPresence;
        } catch (Exception e) {
            e.printStackTrace();
            schedule = getEmptySchedule();
        }
    }

    public HourSchedule getDaySchedule(Weekday day) {
        switch(day) {
            case MONDAY: return schedule[0];
            case TUESDAY: return schedule[1];
            case THURSDAY: return schedule[2];
            case WEDNESDAY: return schedule[3];
            case FRIDAY: return schedule[4];
            case SATURDAY: return schedule[5];
            case SUNDAY: return schedule[6];
            default: return new HourSchedule();
        }
    }
}

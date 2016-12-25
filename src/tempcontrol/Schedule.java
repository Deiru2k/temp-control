package tempcontrol;

import house.Weekday;

public class Schedule {
    private DaySchedule schedule;

    public Schedule() { schedule = new DaySchedule(); }
    public Schedule(DaySchedule initSchedule) {
        schedule = initSchedule;
    }

    public HourSchedule.Event getEvent(Weekday day, String time) {
        return schedule.getDaySchedule(day).getEvent(time);
    }
}

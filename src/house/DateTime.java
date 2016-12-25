package house;

import java.util.concurrent.atomic.AtomicInteger;

public class DateTime {
    private AtomicInteger hours;
    private AtomicInteger minutes;
    private Weekday day;

    public DateTime(int initHours, int initMinutes, Weekday initDay) {
        hours = new AtomicInteger(initHours);
        minutes = new AtomicInteger(initMinutes);
        day = initDay;
    }

    public static String formatDay(Weekday day) {
        switch(day) {
            case MONDAY: return "MON";
            case TUESDAY: return "TUE";
            case THURSDAY: return "THU";
            case FRIDAY: return "FRI";
            case SATURDAY: return "SAT";
            case SUNDAY: return "SUN";
            default: return "NONE";
        }
    }

    private static String formatTime(int hours, int minutes) {
        return String.format("%d:%d", hours, minutes);
    }

    public Weekday getDay() {
        return day;
    }

    public String getTime() {
        return formatTime(hours.get(), minutes.get());
    }

    public void setTime(int newHours, int newMinutes) {
        hours.set(newHours);
        minutes.set(newMinutes);
    }

    private void advanceDay() {
        synchronized (this) {
            switch(day) {
                case MONDAY: day = Weekday.TUESDAY;
                case TUESDAY: day = Weekday.THURSDAY;
                case THURSDAY: day = Weekday.FRIDAY;
                case FRIDAY: day = Weekday.SATURDAY;
                case SATURDAY: day = Weekday.SUNDAY;
                case SUNDAY: day = Weekday.MONDAY;
            }
        }
    }

    private void advanceMinutes() {
        if (minutes.get() == 59) minutes.set(0);
        else minutes.incrementAndGet();
    }

    private void advanceHours() {
        if (hours.get() == 23) {
            hours.set(0);
            advanceDay();
        }
        else hours.incrementAndGet();
    }

    public void advanceTime() {
        int currentMinutes = minutes.get();

        if (currentMinutes == 59) {
            advanceMinutes();
            advanceHours();
        } else {
            advanceMinutes();
        }
    }
}

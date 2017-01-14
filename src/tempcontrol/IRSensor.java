package tempcontrol;

import javax.lang.model.type.NullType;

public class IRSensor {
    private boolean presence;

    public IRSensor() {
        setPresence(false);
    }

    public IRSensor(boolean defaultPresence) {
        setPresence(defaultPresence);
    }

    public void setPresence(boolean value) {
        presence = value;
    }

    public boolean getPresence() {
        return presence;
    }

    public void tick(HourSchedule.Event evt) {
        switch(evt) {
            case ARRIVAL:
                setPresence(true);
                break;
            case DEPARTURE:
                setPresence(false);
                break;
        }
    }
}

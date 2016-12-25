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

    public NullType tick() {
        return null;
    }
}

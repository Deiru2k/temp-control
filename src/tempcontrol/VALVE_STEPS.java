package tempcontrol;

public enum VALVE_STEPS {
    OPEN(5), CLOSED(0), HALF_OPEN(2);

    private int value;

    VALVE_STEPS(int val) {
        value = val;
    }

    public int getValue() {
        return value;
    }
}

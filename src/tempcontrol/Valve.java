package tempcontrol;

public class Valve {
    private VALVE_STEPS state;

    public Valve(VALVE_STEPS initialState) {
        setState(initialState);
    }

    public void setState(VALVE_STEPS value) {
        state = value;
    }

    public VALVE_STEPS getState() {
        return state;
    }
}

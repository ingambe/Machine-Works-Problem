public class State {

    private State previousState;
    public int dollar;
    public int dayToday;
    public Machine machine;

    public State(State previousState, int dollar, int dayToday, Machine machine) {
        this.previousState = previousState;
        this.dollar = dollar;
        this.dayToday = dayToday;
        this.machine = machine;
    }

    public State(int dollar, int dayToday) {
        this.dollar = dollar;
        this.dayToday = dayToday;
        this.previousState = null;
        this.machine = null;
    }

    public State getPreviousState() {
        return previousState;
    }

}

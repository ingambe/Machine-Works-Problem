import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solver {

    /**
     * A BFS to
     * @param problem
     * @return
     */
    public static int solve(Problem problem){
        Queue<State> stateQueue = new LinkedList<>();
        List<State> endState = new ArrayList<>();
        int maxProfit = 0;
        State initial = new State(problem.getMoney(), 0);
        stateQueue.add(initial);
        while (!stateQueue.isEmpty()){
            State current = stateQueue.remove();
            if(current.dayToday != problem.getNumberDays()) {
                // if still not bought a machine and not at the last day
                if (current.machine == null) {
                    State empty = new State(current.dollar, current.dayToday + 1);
                    stateQueue.add(empty);
                    for (Machine machine : problem.machinesOfDay(current.dayToday, current.dollar)) {
                        State buy = new State(current, current.dollar - machine.getPriceBought(), current.dayToday + 1, machine);
                        stateQueue.add(buy);
                    }
                } else {
                    State keepMachine = new State(current, current.dollar + current.machine.getDailyProfit(), current.dayToday + 1, new Machine(current.machine, 1));
                    stateQueue.add(keepMachine);
                    for (Machine machine : problem.machinesOfDay(current.dayToday, current.dollar)) {
                        // TODO : dailyProfit on the day of the sale ???
                        State buy = new State(current, current.dollar + current.machine.getPriceResold() - machine.getPriceBought(), current.dayToday + 1, machine);
                        stateQueue.add(buy);
                    }
                }
            } else {
                if (current.machine != null) {
                    current.dollar += current.machine.getDailyProfit();
                    current.dollar += current.machine.getPriceResold();
                }
                current.machine = null;
                // we keep the end's states to have the possibility to get the full solution
                endState.add(current);
                if(current.dollar > maxProfit){
                    maxProfit = current.dollar;
                }
            }
        }
        return maxProfit;
    }
}

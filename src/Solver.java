import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solver {

    /**
     * A BFS to solve the problem
     * @param problem the problem's model
     * @return max profit at day D+1
     */
    public static int solve(Problem problem){
        // the queue keep the node of the search tree
        Queue<State> stateQueue = new LinkedList<>();
        int maxProfit = 0;
        State initial = new State(problem.getMoney(), 0);
        stateQueue.add(initial);
        while (!stateQueue.isEmpty()){
            State current = stateQueue.remove();
            // if we aren't the last day
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
                        // we don't change our machine for a less profitable one
                        if(current.machine.getDailyProfit() < machine.getDailyProfit()) {
                            State buy = new State(current, current.dollar + current.machine.getPriceResold() - machine.getPriceBought(), current.dayToday + 1, machine);
                            stateQueue.add(buy);
                        }
                    }
                }
            } else {
                // if at the end we have a machine
                if (current.machine != null) {
                    current.dollar += current.machine.getDailyProfit();
                    current.dollar += current.machine.getPriceResold();
                }
                // we have sold our machine
                current.machine = null;
                if(current.dollar > maxProfit){
                    maxProfit = current.dollar;
                }
            }
        }
        return maxProfit;
    }

    /**
     * A more advance BFS to solve the problem
     * @param problem the problem's model
     * @return max profit at day D+1
     */
    public static int complexSolver(Problem problem){
        // the queue keep the node of the search tree
        Queue<State> stateQueue = new LinkedList<>();
        int maxProfit = 0;
        State initial = new State(problem.getMoney(), 0);
        stateQueue.add(initial);
        while (!stateQueue.isEmpty()){
            State current = stateQueue.remove();
            // if we aren't the last day
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
                    // we don't change our machine if she's not profitable
                    if(current.machine.machineIsProfitable()) {
                        for (Machine machine : problem.machinesOfDay(current.dayToday, current.dollar)) {
                            // we don't change our machine for a less profitable one
                            if (current.machine.getDailyProfit() < machine.getDailyProfit()) {
                                State buy = new State(current, current.dollar + current.machine.getPriceResold() - machine.getPriceBought(), current.dayToday + 1, machine);
                                stateQueue.add(buy);
                            }
                        }
                    }
                }
            } else {
                // if at the end we have a machine
                if (current.machine != null) {
                    current.dollar += current.machine.getDailyProfit();
                    current.dollar += current.machine.getPriceResold();
                }
                // we have sold our machine
                current.machine = null;
                if(current.dollar > maxProfit){
                    maxProfit = current.dollar;
                }
            }
        }
        return maxProfit;
    }
}

import java.util.LinkedList;
import java.util.Queue;

public class Solver {

    /**
     * A BFS to solve the problem with some filtering to remove useless exploration
     * @param problem the problem's model
     * @return max profit at day D+1
     */
    public static int solve(final Problem problem){
        // the queue keep the leaf of the search tree
        Queue<State> stateQueue = new LinkedList<>();
        int maxProfit = 0;
        State initial = new State(problem.getMoney(), 0);
        stateQueue.add(initial);
        while (!stateQueue.isEmpty()){
            State current = stateQueue.remove();
            // if it's not the last day
            if(current.dayToday != problem.getNumberDays()) {
                // if still not bought a machine and not at the last day
                if (current.machine == null) {
                    // we explore the state where we still don't buy anything and wait for a possible good opportunity in the future
                    State empty = new State(current.dollar, current.dayToday + 1);
                    stateQueue.add(empty);
                    for (Machine machine : problem.machinesOfDay(current.dayToday, current.dollar)) {
                        // we currently don't have a machine so we only buy one
                        State buy = new State(current, current.dollar - machine.getPriceBought(), current.dayToday + 1, machine);
                        stateQueue.add(buy);
                    }
                } else {
                    // the state where we keep the machine and get money from her
                    State keepMachine = new State(current, current.dollar + current.machine.getDailyProfit(), current.dayToday + 1, new Machine(current.machine, 1));
                    stateQueue.add(keepMachine);

                    // we don't change our machine if she hasn't done a profit yet because otherwise it would have been better not buying her
                    if(current.machine.machineIsProfitable()) {
                        // we look at the catalogue of available machines and check only the one we could buy
                        int moneyAfterMachineSold = current.dollar + current.machine.getPriceResold();
                        for (Machine machine : problem.machinesOfDay(current.dayToday, moneyAfterMachineSold)) {
                            // we don't change our machine for a less profitable one
                            if (current.machine.getDailyProfit() < machine.getDailyProfit()) {
                                // we sell our current machine and buy another one
                                State buy = new State(current, current.dollar + current.machine.getPriceResold() - machine.getPriceBought(), current.dayToday + 1, machine);
                                stateQueue.add(buy);
                            }
                        }
                    }
                }
            } else {
                // if at the end we have a machine we sold her
                if (current.machine != null) {
                    current.dollar += current.machine.getDailyProfit();
                    current.dollar += current.machine.getPriceResold();
                }
                // we have sold our machine
                current.machine = null;
                // we look if it's the best scenario (state) to maximise our profits
                // possibility if needed to keep the best scenario if needed by saving the state with the best profit
                if(current.dollar > maxProfit){
                    maxProfit = current.dollar;
                }
            }
        }
        return maxProfit;
    }

}

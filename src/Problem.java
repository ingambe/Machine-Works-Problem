import java.util.List;

public class Problem {

    private int totalNumberMachines;
    private int money;
    private int daysLeft;
    private List<Machine> machines;

    public Problem(int totalNumberMachines, int money, int daysLeft, List<Machine> machines) {
        this.totalNumberMachines = totalNumberMachines;
        this.money = money;
        this.daysLeft = daysLeft;
        this.machines = machines;
    }

    public int getTotalNumberMachines() {
        return totalNumberMachines;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getDaysLeft() {
        return daysLeft;
    }

    public void setDaysLeft(int daysLeft) {
        this.daysLeft = daysLeft;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

}

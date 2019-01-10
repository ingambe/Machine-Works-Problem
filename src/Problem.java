import java.util.ArrayList;
import java.util.List;

public class Problem {

    private int totalNumberMachines;
    private int money;
    private int numberDays;
    private List<Machine> machines;

    public Problem(int totalNumberMachines, int money, int numberDays, List<Machine> machines) {
        this.totalNumberMachines = totalNumberMachines;
        this.money = money;
        this.numberDays = numberDays;
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

    public int getNumberDays() {
        return numberDays;
    }

    public void setNumberDays(int numberDays) {
        this.numberDays = numberDays;
    }

    public List<Machine> getMachines() {
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    /**
     * Return the catalog of all machine we can buy with our money today
     * @param day today's date
     * @param amountOfMoney our amount of money
     * @return the catalog
     */
    public List<Machine> machinesOfDay(int day, int amountOfMoney){
        List<Machine> machinesDay = new ArrayList<>();
        for(Machine machine : this.machines){
            if(machine.couldBuyThisMachine(amountOfMoney) && machine.getDaySale() == day){
                machinesDay.add(machine);
            }
        }
        return machinesDay;
    }

}

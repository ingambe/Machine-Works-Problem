public class Machine {

    // GIVEN
    private int daySale;
    private int priceBought;
    private int priceResold;
    private int dailyProfit;
    // CALCULATED
    private int machineCost;
    private int daysLefts;
    private double minDaysProfitable;
    // how much we could expect if we keep the machine until the ends (then sold her)
    private int maximumMachineProfit;

    public Machine(int daySale, int priceBought, int priceResold, int dailyProfit, int totalDaysProblem) {
        this.daySale = daySale;
        this.priceBought = priceBought;
        this.priceResold = priceResold;
        this.dailyProfit = dailyProfit;
        this.machineCost = priceBought - priceResold;
        this.daysLefts = totalDaysProblem - daySale;
        this.minDaysProfitable = Math.ceil((this.machineCost * 1.0) / this.dailyProfit);
        this.maximumMachineProfit = (this.dailyProfit * this.daysLefts) + this.priceResold;
    }

    /**
     * Create a new machine for a future state with updated metrics
     * @param machine the machine
     * @param days number of days spent since last state
     */
    public Machine(Machine machine, int days){
        this.daySale = machine.daySale;
        this.priceBought = machine.priceBought;
        this.priceResold = machine.priceResold;
        this.dailyProfit = machine.dailyProfit;
        this.machineCost = priceBought - priceResold;
        this.minDaysProfitable = machine.minDaysProfitable - 1;
        this.daysLefts = machine.daysLefts - days;
        this.maximumMachineProfit = (this.dailyProfit * this.daysLefts) + this.priceResold;
    }

    public int getDaySale() {
        return daySale;
    }

    public int getPriceBought() {
        return priceBought;
    }

    public int getPriceResold() {
        return priceResold;
    }

    public int getDailyProfit() {
        return dailyProfit;
    }

    public int getMachineCost() {
        return machineCost;
    }

    public int getDaysLefts() {
        return daysLefts;
    }

    public double getMinDaysProfitable() {
        return minDaysProfitable;
    }

    public int getMaximumMachineProfit() {
        return maximumMachineProfit;
    }

    /**
     * We should consider a machine only if she is rentable
     * @return if we need to consider a machine or not
     */
    public boolean couldBeProfitable(){
        return this.priceBought < this.maximumMachineProfit;
    }

    public boolean couldBuyThisMachine(int amountOfMoney){
        return amountOfMoney >= this.priceBought;
    }

    public boolean machineIsProfitable(){
        return minDaysProfitable <= 0;
    }

}

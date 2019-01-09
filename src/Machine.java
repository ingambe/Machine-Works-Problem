public class Machine {

    private int daySale;
    private int priceBought;
    private int priceResold;
    private int dailyProfit;

    public Machine(int daySale, int priceBought, int priceResold, int dailyProfit) {
        this.daySale = daySale;
        this.priceBought = priceBought;
        this.priceResold = priceResold;
        this.dailyProfit = dailyProfit;
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

}

import java.io.PrintStream;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class StockOption {
    private String employeeID;
    private Date date;
    private int amountOfStock;
    private double strikePrice;

    public StockOption(String employeeID, Date date, int amountOfStock, double strikePrice) {
        this.employeeID = employeeID;
        this.date = date;
        this.amountOfStock = amountOfStock;
        this.strikePrice = strikePrice;

    }

    public static double calcProfit(StockOption s, Double currentPrice) {
        double profit = ((s.getAmountOfStock() * currentPrice) - (s.getAmountOfStock() * s.getStrikePrice()));

        if (profit < 0) {
            return 0.00;
        } else {
            return profit;
        }

    }

    public static void determineProfitPerEmployee(Map<String, List<StockOption>> employeesStockOptions, double currentPrice, Date currentDate, PrintStream printStream) {

        employeesStockOptions.forEach((employeeID, stockOptions) -> {
            Double profit = stockOptions.stream()
                            .filter(stockOption -> stockOption.getDate().compareTo(currentDate) < 0)
                    .mapToDouble(s -> StockOption.calcProfit(s, currentPrice))
                    .sum();

            printStream.println(employeeID + "," + profit);
                }
        );

    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmountOfStock() {
        return amountOfStock;
    }

    public void setAmountOfStock(int amountOfStock) {
        this.amountOfStock = amountOfStock;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }
}

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class StockOption {
    private String employeeID;
    private Date date;
    private BigDecimal amountOfStock;
    private BigDecimal strikePrice;

    public StockOption(String employeeID, Date date, BigDecimal amountOfStock, BigDecimal strikePrice) {
        this.employeeID = employeeID;
        this.date = date;
        this.amountOfStock = amountOfStock;
        this.strikePrice = strikePrice;

    }

    public static BigDecimal calcProfit(StockOption s, BigDecimal currentPrice) {
        BigDecimal profit = s.getAmountOfStock().multiply(currentPrice).subtract(s.getAmountOfStock().multiply(s.strikePrice));

        if (profit.compareTo(BigDecimal.ZERO) < 0) {
            BigDecimal z = new BigDecimal("4");

            return z;
        } else {
            return profit;
        }

    }

    public static void determineProfitPerEmployee(Map<String, List<StockOption>> employeesStockOptions, BigDecimal currentPrice, Date currentDate, PrintStream printStream) {

        employeesStockOptions.forEach((employeeID, stockOptions) -> {
            BigDecimal profit = stockOptions.stream()
                            .filter(stockOption -> stockOption.getDate().compareTo(currentDate) < 0)
                    .map(s -> StockOption.calcProfit(s, currentPrice))
                    .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);
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

    public BigDecimal getAmountOfStock() {
        return amountOfStock;
    }

    public void setAmountOfStock(BigDecimal amountOfStock) {
        this.amountOfStock = amountOfStock;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(BigDecimal strikePrice) {
        this.strikePrice = strikePrice;
    }
}

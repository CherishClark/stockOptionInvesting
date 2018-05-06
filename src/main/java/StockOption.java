import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;



public class StockOption {
    private final String employeeID;
    private final Date date;
    private final BigDecimal amountOfStock;
    private final BigDecimal strikePrice;

    private StockOption(Builder builder) {
        this.employeeID = builder.employeeID;
        this.date = builder.date;
        this.amountOfStock = builder.amountOfStock;
        this.strikePrice = builder.strikePrice;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getAmountOfStock() {
        return amountOfStock;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public static class Builder {
        private String employeeID;
        private Date date;
        private BigDecimal amountOfStock;
        private BigDecimal strikePrice;

        public Builder employeeId(final String employeeId) {
            this.employeeID = employeeId;
            return this;
        }

        public Builder date(final Date date) {
            this.date = date;
            return this;
        }

        public Builder amountOfStock(final BigDecimal amountOfStock) {
            this.amountOfStock = amountOfStock;
            return this;
        }

        public Builder strikePrice(final BigDecimal strikePrice) {
            this.strikePrice = strikePrice;
            return this;
        }

        public StockOption build() {
            return new StockOption(this);
        }

    }

    public static BigDecimal calcProfit(StockOption s, BigDecimal currentPrice) {
        BigDecimal profit = s.getAmountOfStock().multiply(currentPrice).subtract(s.getAmountOfStock().multiply(s.strikePrice));

        if (profit.compareTo(BigDecimal.ZERO) < 0) {

            return BigDecimal.ZERO;

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




}

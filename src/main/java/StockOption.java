

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.DoubleStream;

public class StockOption {
    private String employeeID;
    private Date date;
    private int amountOfStock;
    private double strikePrice;
    private double currentPrice;

    public StockOption(String employeeID, Date date, int amountOfStock, double strikePrice) {
        this.employeeID = employeeID;
        this.date = date;
        this.amountOfStock = amountOfStock;
        this.strikePrice = strikePrice;

    }

    public static double calcProfit(StockOption s) {
        double profit = ((s.getAmountOfStock() * 5) - (s.getAmountOfStock() * s.getStrikePrice()));

        if (profit < 0) {
            return 0;
        } else {
            return profit;
        }

    }

    public static void determineProfitPerEmployee(Map<String, List<StockOption>> employeesStockOptions, double currentPrice, Date currentDate) {

        employeesStockOptions.forEach((employeeID, stockOptions) -> {
                    DoubleStream amountOfStock = stockOptions.stream()
                            .filter(stockOption -> stockOption.getDate().compareTo(currentDate) < 0)
                            .mapToDouble(StockOption::calcProfit);


                    System.out.println("printing employee's" + employeeID + "profit " + amountOfStock);
                }

        );

        System.out.println("printing current date " + currentDate + "and current price " + currentPrice);



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

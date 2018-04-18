import java.util.Date;

public class StockOption {
    private String employeeID;
    private Date date = new Date();
    private int amountOfStock;
    private double strikePrice;

    public StockOption(String employeeID, Date date, int amountOfStock, double strikePrice) {
        this.employeeID = employeeID;
        this.date = date;
        this.amountOfStock = amountOfStock;
        this.strikePrice = strikePrice;
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

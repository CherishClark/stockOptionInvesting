import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Employee implements Comparable<Employee> {
    private final String employeeId;
    private final List<Event> employeeRecord;
    BigDecimal employeeProfit;
    BigDecimal employeeSalesProfit;


    public Employee(String employeeId, List<Event> employeeRecord) {
        this.employeeId = employeeId;
        this.employeeRecord = employeeRecord;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public List<Event> getEmployeeRecord() {
        return employeeRecord;
    }

    public void setEmployeeSalesProfit(BigDecimal employeeSalesProfit) {
        this.employeeSalesProfit = employeeSalesProfit;
    }

    @Override
    public int compareTo(Employee o) {
        return employeeId.compareTo(o.employeeId);
    }

    public void processSaleOfStock(SaleEvent saleEvent) {
        List<Event> vestedOptions = returnVestedOptions(saleEvent.getEventDate());

        for (Event option : vestedOptions) {
            option.reduceEventAmount(saleEvent.getAmtSold());
//            TODO: Casting
            BigDecimal originalPrice = ((VestEvent) option).getStrikePrice();
            BigDecimal profit = saleEvent.calcProfit(originalPrice);
            saleEvent.setProfitOfSale(profit);
        }
    }

    public List<Event> returnVestedOptions (Date currentDate) {
      return employeeRecord.stream().filter(e -> e.getEventType().compareTo("VEST")==0)
                .filter(event -> event.getEventDate().compareTo(currentDate)<0)
              .collect(Collectors.toList());
    }
}

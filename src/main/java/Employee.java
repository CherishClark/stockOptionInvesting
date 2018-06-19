import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Employee implements Comparable<Employee> {
    private final String employeeId;
    private final List<Event> employeeRecord;
    BigDecimal employeeProfit;
    BigDecimal employeeSalesProfit;


    Employee(String employeeId, List<Event> employeeRecord) {
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
            BigDecimal originalPrice = ((VestEvent) option).getStrikePrice();
            BigDecimal profit = saleEvent.calcProfit(originalPrice);
            saleEvent.setProfitOfSale(profit);
        }
    }

    public void processPerformanceEvent(PerformanceEvent performanceEvent) {
        List<Event> vestedOptions = returnNonVestedOptions(performanceEvent.getEventDate());

        for (Event option : vestedOptions) {
            option.increaseEventAmount(performanceEvent.getPerfMultiplier());
        }

    }

    private List<Event> returnVestedOptions(LocalDate currentDate) {
      return employeeRecord.stream().filter(e -> e.getEventType().compareTo("VEST")==0)
                .filter(event -> event.getEventDate().compareTo(currentDate)<0)
              .collect(Collectors.toList());
    }


    private List<Event> returnNonVestedOptions(LocalDate currentDate) {
        return employeeRecord.stream().filter(e -> e.getEventType().compareTo("VEST") == 0)
                .filter(event -> event.getEventDate().compareTo(currentDate) < 0)
                .collect(Collectors.toList());
    }

    public void setEmployeeProfit(BigDecimal employeeProfit) {
        this.employeeProfit = employeeProfit;
    }
}

import java.math.BigDecimal;
import java.util.List;

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

    public void setEmployeeProfit(BigDecimal employeeProfit) {
        this.employeeProfit = employeeProfit;
    }
}

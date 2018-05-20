import java.math.BigDecimal;
import java.util.List;

public class Employee {
    String employeeId;
    List<Event> employeeRecord;
    BigDecimal employeeProfit;


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

    public BigDecimal getEmployeeProfit() {
        return employeeProfit;
    }
}

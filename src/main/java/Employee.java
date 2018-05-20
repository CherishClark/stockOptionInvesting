import java.math.BigDecimal;
import java.util.List;

public class Employee implements Comparable<Employee> {
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


    @Override
    public int compareTo(Employee o) {
        return employeeId.compareTo(o.employeeId);
    }
}

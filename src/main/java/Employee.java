import java.math.BigDecimal;
import java.util.List;

public class Employee {
    String employeeId;
    List<Event> employeeRecord;


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

    public BigDecimal profit(CurrentMarketInformation cmi) {

        return BigDecimal.ZERO;
    }

}

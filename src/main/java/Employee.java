public class Employee {
    String employeeId;
    EmployeeRecord employeeRecord;

    public Employee(String employeeId, EmployeeRecord employeeRecord) {
        this.employeeId = employeeId;
        this.employeeRecord = employeeRecord;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public EmployeeRecord getEmployeeRecord() {
        return employeeRecord;
    }
}

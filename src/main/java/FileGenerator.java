import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

class FileGenerator {

    private final List<Employee> employeeList;

    FileGenerator(List<Employee> employeeList, OutputStream output) {

        this.employeeList = employeeList;
        printEmployeeOutput(employeeList, output);
    }

    private void printEmployeeOutput(List<Employee> employeeList, OutputStream output) {
        PrintStream printStream = new PrintStream(output);

        Collections.sort(employeeList);

        boolean containsSaleEvent = false;

        for (Employee e : employeeList) {
            if (e.employeeProfit == null) {
                e.setEmployeeProfit(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
            }

            if (e.employeeSalesProfit != null) {
                containsSaleEvent = true;
            }

            if (!containsSaleEvent)
                printStream.println(e.getEmployeeId() + "," + e.employeeProfit);
            else {

                if (e.employeeSalesProfit == null) {
                    e.setEmployeeSalesProfit(BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                printStream.println(e.getEmployeeId() + "," + e.employeeProfit + "," + e.employeeSalesProfit);
            }
        }
    }
}

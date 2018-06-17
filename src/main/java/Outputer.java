import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class Outputer {

    private final EventProcessor eventProcessor;

    public Outputer(EventProcessor eventProcessor, OutputStream output) {

        this.eventProcessor = eventProcessor;
        printEmployeeOutput(eventProcessor, output);

    }


    private void printEmployeeOutput(EventProcessor eventProcessor, OutputStream output) {

        PrintStream printStream = new PrintStream(output);

        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Collections.sort(employeeList);

        boolean containsSaleEvent = false;

        for (Employee e : employeeList) {
            if (e.employeeProfit == null) {
                e.setEmployeeProfit(BigDecimal.ZERO.setScale(2));
            }

            if (e.employeeSalesProfit != null) {
                containsSaleEvent = true;
            }

            if (containsSaleEvent == false)
                printStream.println(e.getEmployeeId() + "," + e.employeeProfit);
            else {
//                TODO: refactor this
                if (e.employeeSalesProfit == null) {
                    e.setEmployeeSalesProfit(BigDecimal.ZERO.setScale(2));
                }
                printStream.println(e.getEmployeeId() + "," + e.employeeProfit + "," + e.employeeSalesProfit);
            }
        }
    }
}

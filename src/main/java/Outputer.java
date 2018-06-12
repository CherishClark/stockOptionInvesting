import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class Outputer {

    private final EventProcessor eventProcessor;

    public Outputer(EventProcessor eventProcessor, OutputStream output) {

        this.eventProcessor = eventProcessor;
        printEmployeeProfit(eventProcessor, output);

    }


    private void printEmployeeProfit(EventProcessor eventProcessor, OutputStream output) {

        PrintStream printStream = new PrintStream(output);

        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Collections.sort(employeeList);

        for (Employee e : employeeList) {
            if (e.employeeSalesProfit == null)
            printStream.println(e.getEmployeeId() + "," + e.employeeProfit);
            else {
                printStream.println(e.getEmployeeId() + "," + e.employeeProfit + "," + e.employeeSalesProfit);
            }
        }
    }
}

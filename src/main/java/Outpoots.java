import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class Outpoots {


    EventProcessor eventProcessor;


    public Outpoots(EventProcessor eventProcessor, OutputStream output) {

        this.eventProcessor = eventProcessor;
        printEmployeeProfit(eventProcessor, output);

    }


    public void printEmployeeProfit(EventProcessor eventProcessor, OutputStream output) {

        PrintStream printStream = new PrintStream(output);

        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Collections.sort(employeeList);

        for (Employee e : employeeList) {
            printStream.println(e.employeeId + "," + e.employeeProfit);

        }

    }
}

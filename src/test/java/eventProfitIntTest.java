import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class eventProfitIntTest {
    @Test
    public void givenCurrentMarketInfo_whenMultipleOptionsForSameAndMultipleEmployees_andMarketDateisAfterAllVestDates_returnProfitPerEmployee() {

        String string = "5\n" +
                "VEST,001B,20120101,1000,0.45\n" +
                "VEST,002B,20120101,1500,0.45\n" +
                "VEST,002B,20130101,1000,0.50\n" +
                "VEST,001B,20130101,1500,0.50\n" +
                "VEST,003B,20130101,1000,0.50\n" +
                "20140101,1.00";

        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Outputer outputer = new Outputer(employeeList, output);


        System.out.println(output.toString());
        assertEquals("001B,1300.00\n" +
                "002B,1325.00\n" +
                "003B,500.00\n", output.toString());
    }

    @Test
    public void givenCurrentMarketInfo_whenMultipleOptionsForSameAndMultipleEmployees_andMarketDateIsPriorToSomeVestDateofSomeOPtions_returnProfitPerEmployee() {
        String string = "5\n" +
                "VEST,001B,20120101,1000,0.45\n" +
                "VEST,002B,20120101,1500,0.45\n" +
                "VEST,002B,20130101,1000,0.50\n" +
                "VEST,001B,20130101,1500,0.50\n" +
                "VEST,003B,20130101,1000,0.50\n" +
                "20120615,1.00\n";
        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Outputer outputer = new Outputer(employeeList, output);

        System.out.println(output.toString());
        assertEquals("001B,550.00\n" +
                "002B,825.00\n" +
                "003B,0.00\n", output.toString());
    }

    @Test
    public void givenCurrentMarketInfo_whenPerformanceIsConsidered_andMarketDateIsAfterAllEventDates_returnProfitPerEmployee() {
        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "VEST,003B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20140101,1.00\n";
        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Outputer outputer = new Outputer(employeeList, output);

        System.out.println(output.toString());
        assertEquals("001B,825.00\n" +
                "002B,825.00\n" +
                "003B,550.00\n", output.toString());

    }

    @Test
    public void givenCurrentMarketInfo_whenPerformanceIsConsidered_andMarketDateisPriorToPerformanceEventDates_returnProfitPerEmployee() {

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "VEST,003B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20130101,1.00\n";

        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Outputer outputer = new Outputer(employeeList, output);

        System.out.println(output.toString());
        assertEquals("001B,550.00\n" +
                "002B,550.00\n" +
                "003B,550.00\n", output.toString());

    }

    @Test
    public void givenCurrentMarketInfo_whenStockOptionsAreSold_andMarketDateIsAfterAllEventDates_returnTotalProfitPerEmployeeAndProfitThroughSale() {

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "SALE,001B,20120402,500,1.00\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20140101,1.00\n";
        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Outputer outputer = new Outputer(employeeList, output);

        System.out.println(output.toString());
        assertEquals("001B,412.50,275.00\n" +
                "002B,825.00,0.00\n", output.toString());

    }

    @Test
    public void givenCurrentMarketInfo_whenStockOptionsAreSold_andMarketDateIsPriorToPerformanceEventDates_returnTotalProfitPerEmployeeAndProfitThroughSale() {

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "SALE,001B,20120402,500,1.00\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20130101,1.00\n";
        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        Outputer outputer = new Outputer(employeeList, output);

        System.out.println(output.toString());
        assertEquals("001B,275.00,275.00\n" +
                "002B,550.00,0.00\n", output.toString());

    }

}

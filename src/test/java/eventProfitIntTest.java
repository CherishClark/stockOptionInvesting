import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class eventProfitIntTest {
    @Test
    public void givenCurrentMarketInfo_whenMultipleOptionsForSameAndMultipleEmployees_andMarketDateisAfterAllVestDates_returnProfitPerEmployee() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();
        String string = "5\n" +
                "VEST,001B,20120101,1000,0.45\n" +
                "VEST,002B,20120101,1500,0.45\n" +
                "VEST,002B,20130101,1000,0.50\n" +
                "VEST,001B,20130101,1500,0.50\n" +
                "VEST,003B,20130101,1000,0.50\n" +
                "20140101,1.00";

        OutputStream output = getOutputStream(eventConfiguration, string);


        System.out.println(output.toString());
        assertEquals("001B,1300.00\n" +
                "002B,1325.00\n" +
                "003B,500.00\n", output.toString());
    }

    @Test
    public void givenCurrentMarketInfo_whenMultipleOptionsForSameAndMultipleEmployees_andMarketDateIsPriorToSomeVestDateofSomeOPtions_returnProfitPerEmployee() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();

        String string = "5\n" +
                "VEST,001B,20120101,1000,0.45\n" +
                "VEST,002B,20120101,1500,0.45\n" +
                "VEST,002B,20130101,1000,0.50\n" +
                "VEST,001B,20130101,1500,0.50\n" +
                "VEST,003B,20130101,1000,0.50\n" +
                "20120615,1.00\n";
        OutputStream output = getOutputStream(eventConfiguration, string);

        System.out.println(output.toString());
        assertEquals("001B,550.00\n" +
                "002B,825.00\n" +
                "003B,0.00\n", output.toString());
    }

    @Test
    public void givenCurrentMarketInfo_whenPerformanceIsConsidered_andMarketDateIsAfterAllEventDates_returnProfitPerEmployee() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "VEST,003B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20140101,1.00\n";
        OutputStream output = getOutputStream(eventConfiguration, string);

        System.out.println(output.toString());
        assertEquals("001B,825.00\n" +
                "002B,825.00\n" +
                "003B,550.00\n", output.toString());

    }

    @Test
    public void givenCurrentMarketInfo_whenPerformanceIsConsidered_andMarketDateisPriorToPerformanceEventDates_returnProfitPerEmployee() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "VEST,003B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20130101,1.00\n";

        OutputStream output = getOutputStream(eventConfiguration, string);

        System.out.println(output.toString());
        assertEquals("001B,550.00\n" +
                "002B,550.00\n" +
                "003B,550.00\n", output.toString());

    }

    @Test
    public void givenCurrentMarketInfo_whenStockOptionsAreSold_andMarketDateIsAfterAllEventDates_returnTotalProfitPerEmployeeAndProfitThroughSale() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "SALE,001B,20120402,500,1.00\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20140101,1.00\n";
        OutputStream output = getOutputStream(eventConfiguration, string);

        System.out.println(output.toString());
        assertEquals("001B,412.50,275.00\n" +
                "002B,825.00,0.00\n", output.toString());

    }

    @Test
    public void givenCurrentMarketInfo_whenStockOptionsAreSold_andMarketDateIsPriorToPerformanceEventDates_returnTotalProfitPerEmployeeAndProfitThroughSale() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();

        String string = "5\n" +
                "VEST,001B,20120102,1000,0.45\n" +
                "SALE,001B,20120402,500,1.00\n" +
                "VEST,002B,20120102,1000,0.45\n" +
                "PERF,001B,20130102,1.5\n" +
                "PERF,002B,20130102,1.5\n" +
                "20130101,1.00\n";
        OutputStream output = getOutputStream(eventConfiguration, string);

        System.out.println(output.toString());
        assertEquals("001B,275.00,275.00\n" +
                "002B,550.00,0.00\n", output.toString());

    }


    @Test
    public void pipeDelimitedFile() throws Exception {
        Map<String, Event> eventConfiguration = createConfiguration();

        String string = "5\n" +
                "VEST|001B|20120102|1000|0.45\n" +
                "SALE|001B|20120402|500|1.00\n" +
                "VEST|002B|20120102|1000|0.45\n" +
                "PERF|001B|20130102|1.5\n" +
                "PERF|002B|20130102|1.5\n" +
                "20130101|1.00\n";
        OutputStream output = getOutputStream(eventConfiguration, string);

        System.out.println(output.toString());
        assertEquals("001B,275.00,275.00\n" +
                "002B,550.00,0.00\n", output.toString());

    }

    private Map<String, Event> createConfiguration() {
        Map<String, Event> eventConfiguration = new HashMap<>();
        eventConfiguration.put("VEST", new VestEvent.Builder().build());
        eventConfiguration.put("PERF", new PerformanceEvent.Builder().build());
        eventConfiguration.put("SALE", new SaleEvent.Builder().build());
        return eventConfiguration;
    }

    private OutputStream getOutputStream(Map<String, Event> eventConfiguration, String string) throws Exception {
        OutputStream output = new java.io.ByteArrayOutputStream();
        InputStream input = new java.io.ByteArrayInputStream(string.getBytes());
        EventInfo eventInfo = EventParser.parseEvents(input, eventConfiguration);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        List<Employee> employeeList = eventProcessor.getEmployeesList();
        new FileGenerator(employeeList, output);
        return output;
    }

}

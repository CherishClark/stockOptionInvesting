import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        Map<String, Event> eventConfiguration = new HashMap<>();
        eventConfiguration.put("VEST", new VestEvent.Builder().build());
        eventConfiguration.put("PERF", new PerformanceEvent.Builder().build());
        eventConfiguration.put("SALE", new SaleEvent.Builder().build());
        eventConfiguration.put("junk", new SaleEvent.Builder().build());


        EventInfo eventInfo = EventParser.parseEvents(System.in, eventConfiguration);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        new FileGenerator(eventProcessor.getEmployeesList(), System.out);
    }

}
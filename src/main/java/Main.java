import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {

        Map<String, createEventFactory> eventConfiguration = new HashMap<>();
        eventConfiguration.put("VEST", new createVestEvent());
        eventConfiguration.put("PERF", new createPerformanceEvent());
        eventConfiguration.put("SALE", new createSaleEvent());

        EventInfo eventInfo = EventParser.parseEvents(System.in, eventConfiguration);
        EventProcessor eventProcessor = new EventProcessor(eventInfo);
        new FileGenerator(eventProcessor.getEmployeesList(), System.out);
    }

}
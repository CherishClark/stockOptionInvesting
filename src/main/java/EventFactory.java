

public class EventFactory {

    public static Event getEvent(String eventType) throws Exception {
        switch (eventType) {
            case "VEST":
                return new VestEvent.Builder().build();
            case "SALE":
                return new SaleEvent.Builder().build();
            case "PERF":
                return new PerformanceEvent.Builder().build();
            default:
                throw new Exception("Invalid event type");
        }
    }
}

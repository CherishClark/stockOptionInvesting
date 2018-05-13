import java.util.List;

public class EventInfo {

    List<Event> eventsList;
    CurrentMarketInformation currentMarketInfo;

    public EventInfo(List<Event> eventsList, CurrentMarketInformation currentMarketInfo) {
        this.eventsList = eventsList;
        this.currentMarketInfo = currentMarketInfo;
    }

    public List<Event> getEventsList() {
        return eventsList;
    }

    public CurrentMarketInformation getCurrentMarketInfo() {
        return currentMarketInfo;
    }
}

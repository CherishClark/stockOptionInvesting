import java.util.ArrayList;
import java.util.List;

public class EmployeeRecord {
    List<Event> eventList = new ArrayList<>();

    public EmployeeRecord(EventInfo eventInfo) {
        this.eventList = eventInfo.getEventsList();
    }

    public List<Event> getEventList() {
        return eventList;
    }


}

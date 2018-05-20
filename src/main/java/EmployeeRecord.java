import java.util.List;

public class EmployeeRecord {
    List<Event> eventList;
    List<VestEvent> vestEventList;

    public EmployeeRecord(EventInfo eventInfo) {
        this.vestEventList = eventInfo.getEventsList();
    }

    public List<Event> getEventList() {
        return eventList;
    }


}

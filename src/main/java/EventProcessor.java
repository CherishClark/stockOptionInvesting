import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventProcessor {
    EventInfo eventInfo;
    List<Employee> employeesList = new ArrayList();

    public EventProcessor(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
        processEvents();
    }

    public EventInfo getEventInfo() {
        return eventInfo;
    }

    private void processEvents() {

        List<Event> eventsList = eventInfo.eventsList;

        Set<String> allEmployeeIds = eventsList.stream()
                .map(e -> e.getEmployeeId())
                .collect(Collectors.toSet());
        for (String employeeId : allEmployeeIds) {
            Employee employee = new Employee(employeeId, eventsList.stream()
                    .filter(e -> e.getEmployeeId().compareTo(employeeId) == 0)
                    .collect(Collectors.toList()));
//
            employeesList.add(employee);
            determineProfitOfEmployeeVestedOptions(employee);
        }
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    public BigDecimal determineProfitOfEmployeeVestedOptions(Employee employee) {
        List<Event> employeeEvents = employee.getEmployeeRecord();

        BigDecimal profit =
                employeeEvents.stream()
                        .filter(e -> {
                            Date marketDate = eventInfo.getCurrentMarketInfo().getMarketDate();
                            return e.getEventDate().compareTo(marketDate) < 0;
                        }).map(vestEvent -> vestEvent.calcProfit(eventInfo.currentMarketInfo.getMarketPrice()))
                        .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);
        employee.employeeProfit = profit;
        return profit;
    }

}

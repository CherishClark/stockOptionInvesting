import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class EventProcessor {
    private final EventInfo eventInfo;
    private final List<Employee> employeesList = new ArrayList<>();

    EventProcessor(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
        processEvents();
    }

    private void processEvents() {
        List<Event> eventsList = eventInfo.eventsList.stream().filter(e -> {
            LocalDate marketDate = eventInfo.getCurrentMarketInfo().getMarketDate();
            return e.getEventDate().compareTo(marketDate) < 0;
        }).collect(Collectors.toList());

        Set<String> allEmployeeIds = eventInfo.eventsList.stream()
                .map(Event::getEmployeeId)
                .collect(Collectors.toSet());

        for (String employeeId : allEmployeeIds) {
            Employee employee = new Employee(employeeId, eventsList.stream()
                    .filter(e -> e.getEmployeeId().compareTo(employeeId) == 0)
                    .collect(Collectors.toList()));
            employeesList.add(employee);

            List<Event> employeesEvents = employee.getEmployeeRecord();

            processesSaleEvents(employeesEvents, employee);
            processPerformanceEvents(employeesEvents, employee);
            processVestEvents(employeesEvents, employee);
        }
    }

    public List<Employee> getEmployeesList() {
        return employeesList;
    }

    private void determineProfitOfEmployeeVestedOptions(Employee employee) {
        List<Event> employeeEvents = employee.getEmployeeRecord().stream().filter(event -> event.getEventType().compareTo("VEST") == 0).collect(Collectors.toList());

        employee.employeeProfit = employeeEvents.stream()
                .filter(e -> {
                    LocalDate marketDate = eventInfo.getCurrentMarketInfo().getMarketDate();
                    return e.getEventDate().compareTo(marketDate) < 0;
                }).map(vestEvent -> vestEvent.calcProfit(eventInfo.currentMarketInfo.getMarketPrice()))
                .reduce(BigDecimal.ZERO.setScale(2, RoundingMode.UP), BigDecimal::add);
    }

    private void processesSaleEvents(List<Event> events, Employee employee) {

        for (Event event : events) {
            if (event.getEventTypeEnum().equals(EventTypes.SALE)) {
                employee.processSaleOfStock((SaleEvent) event);
                employee.setEmployeeSalesProfit(((SaleEvent) event).getProfitOfSale());
            }
        }
    }

    private void processPerformanceEvents(List<Event> events, Employee employee) {

        for (Event event : events) {
            if (event.getEventTypeEnum().equals(EventTypes.PERFORMANCE)) {
                employee.processPerformanceEvent((PerformanceEvent) event);
            }
        }
    }

    private void processVestEvents(List<Event> events, Employee employee) {
        for (Event event : events) {
            if (event.getEventTypeEnum().equals(EventTypes.VEST)) {
                determineProfitOfEmployeeVestedOptions(employee);
            }
        }
    }
}

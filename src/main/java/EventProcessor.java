import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class EventProcessor {
    EventInfo eventInfo;
    List employeesList = new ArrayList();

    public EventProcessor(EventInfo eventInfo) {
        this.eventInfo = eventInfo;
    }

    public EventInfo getEventInfo() {
        return eventInfo;
    }

    public void processEvents(EventInfo eventInfo) {
        createEmployee(eventInfo, employeesList);

    }

    public void createEmployee(EventInfo eventInfo, List employeesList) {

        List<Event> eventsList = eventInfo.eventsList;

        for (Event event : eventsList) {

            if (!employeesList.contains(event.getEmployeeId())) {
                List<Event> employeeEventList = eventsList.stream()
                        .filter(eventList -> !eventList.getEmployeeId()
                                .equals(eventList.getEmployeeId()))
                        .collect(Collectors
                                .toList());

                EmployeeRecord employeeRecord = new EmployeeRecord(eventInfo);
                Employee employee = new Employee(event.getEmployeeId(), employeeRecord);

                employeesList.add(employee);
            }

        }

    }

    public BigDecimal determineProfitOfEmployeeVestedOptions(Employee employee) {
        List<Event> employeeEvents = employee.employeeRecord.getEventList();
        List<Event> vestedEvents = employeeEvents.stream().filter(e -> {
            Date marketDate = employee.employeeRecord.eventInfo.currentMarketInfo.getMarketDate();
            return e.getEventDate().compareTo(marketDate) < 0;
        }).collect(Collectors.toList());

        List<VestEvent> vestedEventsList = new ArrayList<>();

        for (VestEvent event : vestedEventsList) {
            if (event.getEventType().toUpperCase() == "VEST") {
                vestedEventsList.add(event);

            }
            BigDecimal profit = vestedEventsList.stream().map(e -> calcProfit(e, e.getStrikePrice())).reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);

            return profit;

        }


    }

    private static BigDecimal calcProfit(VestEvent e, BigDecimal strikePrice) {
        BigDecimal profit = e.getAmountOfStock().multiply(strikePrice).subtract(e.getAmountOfStock().multiply(e.getStrikePrice()));

        if (profit.compareTo(BigDecimal.ZERO) < 0) {

            return BigDecimal.ZERO;

        } else {

            return profit;
        }

    }
}

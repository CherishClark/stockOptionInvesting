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
//        List<Event> vestedEvents = (VestEvent) new ArrayList<VestEvent>();
//
//
//        for (Event event : employeeEvents) {
//            if(event.getEventDate().compareTo(eventInfo.currentMarketInfo.getMarketDate()) < 0) {
//                vestedEvents.add(event);
//            }
//        }
//
//        for (v : vestedEvents) {
//
//            calcProfit(vestedEvents,eventInfo.currentMarketInfo.getStrikePrice());
//
//        }

        List<Event> vestedEvents = employeeEvents.stream()
                .filter(e -> e.getEventType().compareTo("VEST") == 0)
                .collect(Collectors.toList());


        BigDecimal profit =
                employeeEvents.stream()
                        .filter(e -> {
                            Date marketDate = eventInfo.getCurrentMarketInfo().getMarketDate();
                            return e.getEventDate().compareTo(marketDate) < 0;
                        }).map(vestEvent -> vestEvent.calcProfit(eventInfo.currentMarketInfo.getMarketPrice()))
                        .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);
//                        .filter(e -> e instanceof VestEvent)//e.getEventType().toUpperCase().compareTo("VEST") == 0)
        //.map(VestEvent.class::cast)
//                        .map(e -> (VestEvent)e)

        employee.employeeProfit = profit;
        return profit;
    }

//    private static BigDecimal calcProfit(VestEvent e, BigDecimal strikePrice) {
//        BigDecimal profit = e.getAmountOfStock().multiply(strikePrice).subtract(e.getAmountOfStock().multiply(e.getStrikePrice()));
//
//        if (profit.compareTo(BigDecimal.ZERO) < 0) {
//
//            return BigDecimal.ZERO;
//
//        } else {
//
//            return profit;
//        }
//
//    }
}

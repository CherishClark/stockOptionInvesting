import com.sun.tools.classfile.Opcode;

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

        Set<String> allEmployeeIds = eventInfo.eventsList.stream()
                .map(e -> e.getEmployeeId())
                .collect(Collectors.toSet());
        for (String employeeId : allEmployeeIds) {
            Employee employee = new Employee(employeeId, eventInfo.eventsList.stream()
                    .filter(e -> e.getEmployeeId().compareTo(employeeId) == 0)
                    .collect(Collectors.toList()));
            employeesList.add(employee);
            determineProfitOfEmployeeVestedOptions(employee);
        }

    }


    public BigDecimal determineProfitOfEmployeeVestedOptions(Employee employee) {
        List<Event> employeeEvents = employee.getEmployeeRecord();

        BigDecimal profit =
                employeeEvents.stream()
                        .filter(e -> {
                            Date marketDate = eventInfo.currentMarketInfo.getMarketDate();
                            return e.getEventDate().compareTo(marketDate) < 0;
                        }).filter(e -> e.getEventType().toUpperCase().compareTo("VEST") == 0)
                        .map(VestEvent.class::cast)
                        .map(e -> calcProfit(e, e.getStrikePrice()))
                        .reduce(BigDecimal.ZERO.setScale(2), BigDecimal::add);

        return profit;
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

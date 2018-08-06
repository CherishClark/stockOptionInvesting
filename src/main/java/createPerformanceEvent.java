import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class createPerformanceEvent extends createEventFactory {
    @Override
    public Event createEvent(String eventString, String fileDelimiter) {
        List<String> performanceEventElements = Arrays.asList((eventString.split(fileDelimiter)));
        String eventType = performanceEventElements.get(0);
        String employeeId = performanceEventElements.get(1);
        BigDecimal perfMultiplier = new BigDecimal(performanceEventElements.get(3));
        String dateString = performanceEventElements.get(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate performanceDate = LocalDate.parse(dateString, formatter);

        return new PerformanceEvent.Builder()
                .perfMultiplier(perfMultiplier)
                .eventType(eventType)
                .eventDate(performanceDate)
                .employeeId(employeeId)
                .eventTypeEnum(EventTypes.PERFORMANCE)
                .build();
    }
}

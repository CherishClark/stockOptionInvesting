import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class PerformanceEvent extends Event {
    private BigDecimal perfMultiplier;

    public PerformanceEvent(Builder builder) {
        super(builder);
        this.perfMultiplier = builder.perfMultiplier;
    }

    public static class Builder extends Event.Builder {
        private BigDecimal perfMultiplier;

        public Builder perfMultiplier(final BigDecimal perfMultiplier) {
            this.perfMultiplier = perfMultiplier;
            return this;
        }

        public Event build() {
            return new PerformanceEvent(this);
        }
    }

    public BigDecimal getPerfMultiplier() {
        return perfMultiplier;
    }

    @Override
    public Event createEvent(String eventString) {
        List<String> performanceEventElements = Arrays.asList((eventString.split(",")));
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

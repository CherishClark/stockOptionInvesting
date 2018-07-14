import java.math.BigDecimal;
import java.time.LocalDate;


public class Event {
    private final String eventType;
    private final EventTypes eventTypeEnum;
    private final String employeeId;
    private final LocalDate eventDate;

    Event(Builder builder) {
        this.eventTypeEnum = builder.eventTypeEnum;
        this.eventType = builder.eventType;
        this.employeeId = builder.employeeId;
        this.eventDate = builder.eventDate;
    }

    public EventTypes getEventTypeEnum() {
        return eventTypeEnum;
    }

    public String getEventType() {
        return eventType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public static class Builder {
        private EventTypes eventTypeEnum;
        private String eventType;
        private String employeeId;
        private LocalDate eventDate;

        public Builder eventTypeEnum(final EventTypes eventTypeEnum) {
            this.eventTypeEnum = eventTypeEnum;
            return this;
        }

        public Builder eventType(final String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder employeeId(final String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder eventDate(final LocalDate eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Event build() {
            return new Event(this);
        }

    }

    public BigDecimal calcProfit(BigDecimal marketPrice) {
        return BigDecimal.ZERO;
    }

    public void reduceEventAmount(BigDecimal amount) {

    }

    public void increaseEventAmount(BigDecimal amount) {

    }

    public Event createEvent(String eventString, String fileDelimiter) throws Exception {
        try {
            return new Event.Builder().build();
        } catch (Exception e) {
            throw new Exception(eventString + "Event could not be created");
        }
    }


}

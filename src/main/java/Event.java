import java.util.Date;

public class Event {
    private final String eventType;
    private final String employeeId;
    private final Date eventDate;


    public Event(Builder builder) {
        this.eventType = builder.eventType;
        this.employeeId = builder.employeeId;
        this.eventDate = builder.eventDate;

    }

    public String getEventType() {
        return eventType;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public static class Builder {
        private String eventType;
        private String employeeId;
        private Date eventDate;

        public Builder eventType(final String eventType) {
            this.eventType = eventType;
            return this;
        }

        public Builder employeeId(final String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder eventDate(final Date eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Event build() {
            return new Event(this);
        }

    }


}

import java.math.BigDecimal;

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

}

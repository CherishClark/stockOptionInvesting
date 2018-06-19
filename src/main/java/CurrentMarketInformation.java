import java.math.BigDecimal;
import java.time.LocalDate;

public class CurrentMarketInformation {
    private LocalDate marketDate;
    private BigDecimal marketPrice;

    private CurrentMarketInformation(Builder builder) {
        this.marketDate = builder.marketDate;
        this.marketPrice = builder.marketPrice;
    }

    public LocalDate getMarketDate() {
        return marketDate;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public static class Builder {
        private LocalDate marketDate;
        private BigDecimal marketPrice;

        public Builder marketDate(final LocalDate marketDate) {
            this.marketDate = marketDate;
            return this;
        }

        public Builder marketPrice(final BigDecimal marketPrice) {
            this.marketPrice = marketPrice;
            return this;
        }

        public CurrentMarketInformation build() {
            return new CurrentMarketInformation(this);
        }
    }

}

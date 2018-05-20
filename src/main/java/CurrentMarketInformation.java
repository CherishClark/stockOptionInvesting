import java.math.BigDecimal;
import java.util.Date;

public class CurrentMarketInformation {
    private Date marketDate;
    private BigDecimal marketPrice;

    private CurrentMarketInformation(Builder builder) {
        this.marketDate = builder.marketDate;
        this.marketPrice = builder.marketPrice;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public BigDecimal getMarketPrice() {
        return marketPrice;
    }

    public static class Builder {
        private Date marketDate;
        private BigDecimal marketPrice;

        public Builder marketDate(final Date marketDate) {
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

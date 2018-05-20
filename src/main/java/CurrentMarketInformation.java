import java.math.BigDecimal;
import java.util.Date;

public class CurrentMarketInformation {
    private Date marketDate;
    private BigDecimal strikePrice;

    private CurrentMarketInformation(Builder builder) {
        this.marketDate = builder.marketDate;
        this.strikePrice = builder.strikePrice;
    }

    public Date getMarketDate() {
        return marketDate;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    public static class Builder {
        private Date marketDate;
        private BigDecimal strikePrice;

        public Builder marketDate(final Date marketDate) {
            this.marketDate = marketDate;
            return this;
        }

        public Builder strikePrice(final BigDecimal marketPrice) {
            this.strikePrice = marketPrice;
            return this;
        }

        public CurrentMarketInformation build() {
            return new CurrentMarketInformation(this);
        }
    }

}

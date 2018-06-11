import java.math.BigDecimal;

public class VestEvent extends Event {
    private BigDecimal amountOfStock;
    private BigDecimal strikePrice;

    public VestEvent(Builder builder) {
        super(builder);
        this.amountOfStock = builder.amountOfStock;
        this.strikePrice = builder.strikePrice;

    }


    public static class Builder extends Event.Builder {
        private BigDecimal amountOfStock;
        private BigDecimal strikePrice;

        public Builder amountOfStock(final BigDecimal amountOfStock) {
            this.amountOfStock = amountOfStock;
            return this;
        }

        public Builder strikePrice(final BigDecimal strikePrice) {
            this.strikePrice = strikePrice;
            return this;
        }

        public Event build() {
            return new VestEvent(this);
        }
    }

    @Override
    public BigDecimal calcProfit(BigDecimal marketPrice) {
        BigDecimal profit = getAmountOfStock().multiply(marketPrice).subtract(getAmountOfStock().multiply(strikePrice));

        if (profit.compareTo(BigDecimal.ZERO) < 0) {

            return BigDecimal.ZERO;

        } else {

            return profit;
        }
    }


    public BigDecimal getAmountOfStock() {
        return amountOfStock;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

}




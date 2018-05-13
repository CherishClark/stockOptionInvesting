import java.math.BigDecimal;

public class VestEvent extends Event {
    private BigDecimal amountOfStock;
    private BigDecimal strikePrice;
    private String employeeId;

    public VestEvent(Builder builder) {
        super(builder);
        this.amountOfStock = builder.amountOfStock;
        this.strikePrice = builder.strikePrice;

    }

    public BigDecimal getAmountOfStock() {
        return amountOfStock;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
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

    }


}


}

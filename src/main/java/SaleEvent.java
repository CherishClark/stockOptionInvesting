import java.math.BigDecimal;

public class SaleEvent extends Event {
    private BigDecimal amtSold;
    private BigDecimal salePrice;
    private BigDecimal profitOfSale;

    public SaleEvent(Builder builder) {
        super(builder);
        this.amtSold = builder.amtSold;
        this.salePrice = builder.salePrice;
    }




    public static class Builder extends Event.Builder {
        private BigDecimal amtSold;
        private BigDecimal salePrice;

        public Builder amtSold(final BigDecimal amtSold) {
            this.amtSold = amtSold;
            return this;
        }

        public Builder salePrice(final BigDecimal salePrice) {
            this.salePrice = salePrice;
            return this;
        }

        public Event build() {
            return new SaleEvent(this);
        }
    }

    @Override
    public BigDecimal calcProfit(BigDecimal originalPrice) {
         salePrice.multiply(amtSold).subtract(originalPrice.multiply(amtSold));
        return profitOfSale;
    }

    public BigDecimal getProfitOfSale() {
        return profitOfSale;
    }

    public BigDecimal getAmtSold() {
        return amtSold;
    }

    public BigDecimal getSalePrice() {
        return salePrice;
    }
}

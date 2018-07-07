import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SaleEvent extends Event {
    private BigDecimal amtSold;
    private BigDecimal salePrice;
    private BigDecimal profitOfSale;

    SaleEvent(Builder builder) {
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
        return salePrice.multiply(amtSold).subtract(originalPrice.multiply(amtSold));
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

    public void setProfitOfSale(BigDecimal profitOfSale) {
        this.profitOfSale = profitOfSale;
    }

    @Override
    public Event createEvent(String eventString) {
        List<String> saleEventElements = Arrays.asList((eventString.split(",")));
        String eventType = saleEventElements.get(0);
        String employeeId = saleEventElements.get(1);
        BigDecimal amtSold = new BigDecimal(saleEventElements.get(3));
        BigDecimal salePrice = new BigDecimal(saleEventElements.get(4));
        String dateString = saleEventElements.get(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate saleDate = LocalDate.parse(dateString, formatter);

        return new SaleEvent.Builder()
                .amtSold(amtSold)
                .salePrice(salePrice)
                .employeeId(employeeId)
                .eventType(eventType)
                .eventDate(saleDate)
                .eventTypeEnum(EventTypes.SALE)
                .build();
    }

}

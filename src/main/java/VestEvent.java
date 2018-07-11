import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class VestEvent extends Event {
    private BigDecimal amountOfStock;
    private BigDecimal strikePrice;


    VestEvent(Builder builder) {
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

            return profit.setScale(2, BigDecimal.ROUND_HALF_UP);
        }
    }

    @Override
    public void reduceEventAmount(BigDecimal amountOfStock) {
        BigDecimal currentAmt = getAmountOfStock();

        if (currentAmt.compareTo(amountOfStock) > 0) {
          setAmountOfStock(currentAmt.subtract(amountOfStock));
        } else {
            setAmountOfStock(BigDecimal.ZERO);
        }
    }

    @Override
    public void increaseEventAmount(BigDecimal multiplier) {
        setAmountOfStock(multiplier.multiply(amountOfStock));

    }

    private BigDecimal getAmountOfStock() {
        return amountOfStock;
    }

    private void setAmountOfStock(BigDecimal amountOfStock) {
        this.amountOfStock = amountOfStock;
    }

    public BigDecimal getStrikePrice() {
        return strikePrice;
    }

    @Override
    public Event createEvent(String eventString, String fileDelimiter) {
        List<String> vestEventElements = Arrays.asList(eventString.split(fileDelimiter));
        String eventType = vestEventElements.get(0);
        String employeeId = vestEventElements.get(1);
        BigDecimal amountOfStock = new BigDecimal(vestEventElements.get(3));
        BigDecimal strikePrice = new BigDecimal(vestEventElements.get(4));
        String dateString = vestEventElements.get(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate vestDate = LocalDate.parse(dateString, formatter);

        return new VestEvent.Builder()
                .amountOfStock(amountOfStock)
                .strikePrice(strikePrice)
                .eventType(eventType)
                .employeeId(employeeId)
                .eventDate(vestDate)
                .eventTypeEnum(EventTypes.VEST)
                .build();
    }

}




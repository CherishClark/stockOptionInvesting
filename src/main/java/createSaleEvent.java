import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class createSaleEvent extends createEventFactory {
    @Override
    public Event createEvent(String eventString, String fileDelimiter) {
        List<String> saleEventElements = Arrays.asList((eventString.split(fileDelimiter)));
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

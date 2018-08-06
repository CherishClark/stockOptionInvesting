import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class createVestEvent extends createEventFactory {
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


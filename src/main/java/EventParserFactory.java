import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class EventParserFactory {

    public Event makeNewEvent(String eventString) {
        if (identifyEventType(eventString).compareTo("VEST") == 0) {
            return createVestEvent(eventString);
        } else if (identifyEventType(eventString).compareTo("SALE") == 0) {
            return createSaleEvent(eventString);
        } else if (identifyEventType(eventString).compareTo("PERF") == 0) {
            return createPerformanceEvent(eventString);
        } else {
            return null;
        }
    }

    private static String identifyEventType(String eventString) {
        List<String> eventElements = Arrays.asList(eventString.split(","));
        return eventElements.get(0);
    }

    private static Event createVestEvent(String eventString) {
        List<String> vestEventElements = Arrays.asList(eventString.split(","));
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

    private static Event createSaleEvent(String eventString) {
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

    private static Event createPerformanceEvent(String eventString) {
        List<String> performanceEventElements = Arrays.asList((eventString.split(",")));
        String eventType = performanceEventElements.get(0);
        String employeeId = performanceEventElements.get(1);
        BigDecimal perfMultiplier = new BigDecimal(performanceEventElements.get(3));
        String dateString = performanceEventElements.get(2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate performanceDate = LocalDate.parse(dateString, formatter);

        return new PerformanceEvent.Builder()
                .perfMultiplier(perfMultiplier)
                .eventType(eventType)
                .eventDate(performanceDate)
                .employeeId(employeeId)
                .eventTypeEnum(EventTypes.PERFORMANCE)
                .build();
    }
}

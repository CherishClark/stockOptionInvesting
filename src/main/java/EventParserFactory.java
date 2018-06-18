import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
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
        java.util.Date vestDate = new Date();
        try {
            vestDate = new SimpleDateFormat("yyyymmdd").parse(vestEventElements.get(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new VestEvent.Builder()
                .amountOfStock(amountOfStock)
                .strikePrice(strikePrice)
                .eventType(eventType)
                .employeeId(employeeId)
                .eventDate(vestDate)
                .build();

    }

    private static Event createSaleEvent(String eventString) {
        List<String> saleEventElements = Arrays.asList((eventString.split(",")));
        String eventType = saleEventElements.get(0);
        String employeeId = saleEventElements.get(1);
        BigDecimal amtSold = new BigDecimal(saleEventElements.get(3));
        BigDecimal salePrice = new BigDecimal(saleEventElements.get(4));
        java.util.Date saleDate = new Date();
        try {
            saleDate = new SimpleDateFormat("yyyymmdd").parse(saleEventElements.get(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new SaleEvent.Builder()
                .amtSold(amtSold)
                .salePrice(salePrice)
                .employeeId(employeeId)
                .eventType(eventType)
                .eventDate(saleDate)
                .build();
    }

    private static Event createPerformanceEvent(String eventString) {
        List<String> performanceEventElements = Arrays.asList((eventString.split(",")));
        String eventType = performanceEventElements.get(0);
        String employeeId = performanceEventElements.get(1);
        BigDecimal perfMultiplier = new BigDecimal(performanceEventElements.get(3));
        java.util.Date performanceDate = new Date();
        try {
            performanceDate = new SimpleDateFormat("yyyymmdd").parse(performanceEventElements.get(2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new PerformanceEvent.Builder()
                .perfMultiplier(perfMultiplier)
                .eventType(eventType)
                .eventDate(performanceDate)
                .employeeId(employeeId)
                .build();

    }
}

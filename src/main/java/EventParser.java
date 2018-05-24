import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EventParser {

    public static EventInfo parseEvents(InputStream input) {
        Scanner sc = new Scanner(input);
        Integer a = sc.nextInt();

        List<Event> eventsList = new ArrayList<>();

        for (int i = 0; i < a; i++) {
            String eventString = sc.next();

            if (identifyEventType(eventString).compareTo("VEST") == 0) {
                eventsList.add(createVestEvent(eventString));
            }
        }

        String currentMarketInfoString = sc.next();

        return new EventInfo(eventsList, createCurrentMarketInfo(currentMarketInfoString));

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

    private static CurrentMarketInformation createCurrentMarketInfo(String currentMarketInfoString) {
        String[] currentMarketInfo = currentMarketInfoString.split(",");
        BigDecimal marketPrice = new BigDecimal(currentMarketInfo[1]);
        Date marketDate = new Date();
        try {

            marketDate = new SimpleDateFormat("yyyymmdd").parse(currentMarketInfo[0]);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        return new CurrentMarketInformation.Builder()
                .marketDate(marketDate)
                .marketPrice(marketPrice)
                .build();

    }
}

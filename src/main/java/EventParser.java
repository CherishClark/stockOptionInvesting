
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventParser {

    public static EventInfo parseEvents(InputStream input, Map<String, Event> eventConfiguration) {
        Scanner sc = new Scanner(input);
        Integer a = sc.nextInt();
        List<Event> eventsList = new ArrayList<>();
        String fileDelimiter = "\\s*\\|\\s*|,";

        try {
            for (int i = 0; i < a; i++) {
                String eventString = sc.next();
                String eventType = Arrays.asList((eventString.split(fileDelimiter))).get(0);
                for (Map.Entry<String, Event> event : eventConfiguration.entrySet()) {
                    if (event.getKey().equals(eventType)) {
                        Event newEvent = event.getValue();
                        eventsList.add(newEvent.createEvent(eventString, fileDelimiter));

                    }


                }
//                Event newEvent = EventFactory.getEvent(eventType);


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String currentMarketInfoString = sc.next();
        return new EventInfo(eventsList, createCurrentMarketInfo(currentMarketInfoString, fileDelimiter));

    }

    private static CurrentMarketInformation createCurrentMarketInfo(String currentMarketInfoString, String fileDelimiter) {
        String[] currentMarketInfo = currentMarketInfoString.split(fileDelimiter);
        BigDecimal marketPrice = new BigDecimal(currentMarketInfo[1]);
        String stringDate = currentMarketInfo[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate marketDate = LocalDate.parse(stringDate, formatter);

        return new CurrentMarketInformation.Builder()
                .marketDate(marketDate)
                .marketPrice(marketPrice)
                .build();
    }
}

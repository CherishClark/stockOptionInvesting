
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EventParser {

    public static EventInfo parseEvents(InputStream input, Map<String, createEventFactory> eventConfiguration) throws Exception {
        Scanner sc = new Scanner(input);
        Integer a = sc.nextInt();
        List<Event> eventsList = new ArrayList<>();
        String fileDelimiter = "\\s*\\|\\s*|,";

        try {
            for (int i = 0; i < a; i++) {
                String eventString = sc.next();
                String eventType = Arrays.asList((eventString.split(fileDelimiter))).get(0);
                for (Map.Entry<String, createEventFactory> event : eventConfiguration.entrySet()) {
                    if (event.getKey().equals(eventType)) {
                        Event newEvent = event.getValue().createEvent(eventString, fileDelimiter);
                        eventsList.add(newEvent);
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        }
        String currentMarketInfoString = sc.next();
        return new EventInfo(eventsList, createCurrentMarketInfo(currentMarketInfoString, fileDelimiter));

    }

    private static CurrentMarketInformation createCurrentMarketInfo(String currentMarketInfoString, String fileDelimiter) throws Exception {
        String[] currentMarketInfo = currentMarketInfoString.split(fileDelimiter);
        BigDecimal marketPrice = new BigDecimal(currentMarketInfo[1]);
        String stringDate = currentMarketInfo[0];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate marketDate = LocalDate.parse(stringDate, formatter);
        CurrentMarketInformation cMI;
        try {
            cMI = new CurrentMarketInformation.Builder()
                    .marketDate(marketDate)
                    .marketPrice(marketPrice)
                    .build();
            return cMI;
        } catch (Exception e) {
            throw new Exception("Current Market Info could not be created");
        }
    }
}

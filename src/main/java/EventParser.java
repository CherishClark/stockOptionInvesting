import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventParser {

    public static EventInfo parseEvents(InputStream input) {
        Scanner sc = new Scanner(input);
        Integer a = sc.nextInt();
        List<Event> eventsList = new ArrayList<>();

        try {
            for (int i = 0; i < a; i++) {
                String eventString = sc.next();
                Event newEvent = new EventParserFactory().makeNewEvent(eventString);
                eventsList.add(newEvent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String currentMarketInfoString = sc.next();
        createCurrentMarketInfo(currentMarketInfoString);
        return new EventInfo(eventsList, createCurrentMarketInfo(currentMarketInfoString));

    }
    private static CurrentMarketInformation createCurrentMarketInfo(String currentMarketInfoString) {
        String[] currentMarketInfo = currentMarketInfoString.split(",");
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

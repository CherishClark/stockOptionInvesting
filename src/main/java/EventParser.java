import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class EventParser {

    public static EventInfo parseEvents(InputStream input) {
        Scanner sc = new Scanner(input);
        Integer a = sc.nextInt();

        List<Event> eventsList = new ArrayList<>();

        for (int i = 0; i < a; i++) {
            String eventString = sc.next();
            Event newEvent = new EventParserFactory().makeNewEvent(eventString);
            eventsList.add(newEvent);
        }
        String currentMarketInfoString = sc.next();
        createCurrentMarketInfo(currentMarketInfoString);
        return new EventInfo(eventsList, createCurrentMarketInfo(currentMarketInfoString));

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

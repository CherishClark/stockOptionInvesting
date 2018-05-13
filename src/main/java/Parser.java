import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Parser {

    public static void parseInput(InputStream input, OutputStream output) {

        Scanner sc = new Scanner(input);

        Integer a = sc.nextInt();

        Map<String, List<StockOption>> inputString = new TreeMap<>();

        for (int i = 0; i < a; i++) {

            String stockOptionString = sc.next();

            String action = chooseAction(stockOptionString);
            if (action.contentEquals("VEST")) {

                StockOption parsedOption = stockOptionParser(stockOptionString);

                List<StockOption> employeeStockOptionsList;

                String employeeId = parsedOption.getEmployeeId();

                if (inputString.containsKey(employeeId)) {

                    employeeStockOptionsList = inputString.get(employeeId);

                } else {
                    employeeStockOptionsList = new ArrayList<>();
                    inputString.put(employeeId, employeeStockOptionsList);
                }

                employeeStockOptionsList.add(parsedOption);

            } else if (action.contentEquals("SALE")) {

            } else if (action.contentEquals("PERF")) {

            }

        }

        String currentMarketInfo = sc.next();

//        parsing current market info
        List<String> currentMarketInfoElementsList = Arrays.asList(currentMarketInfo.split(","));

        String Date = currentMarketInfoElementsList.get(0);
        BigDecimal strikePrice = new BigDecimal(currentMarketInfoElementsList.get(1));
        java.util.Date currentDate = new Date();

        try {

            currentDate = new SimpleDateFormat("yyyymmdd").parse(Date);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        PrintStream printStream = new PrintStream(output);

        StockOption.determineProfitPerEmployee(inputString, strikePrice, currentDate, printStream);

    }

    public static String chooseAction(String stockOption) {
        List<String> stockOptionElementsList = Arrays.asList(stockOption.split(","));

        return stockOptionElementsList.get(0);
    }

    public static StockOption stockOptionParser(String stockOption) {

        List<String> stockOptionElementsList = Arrays.asList(stockOption.split(","));

        String action = stockOptionElementsList.get(0);
        String employeeID = stockOptionElementsList.get(1);
        String Date = stockOptionElementsList.get(2);
        Date date = new Date();

        try {

            date = new SimpleDateFormat("yyyymmdd").parse(Date);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        BigDecimal amountOfStock = new BigDecimal(stockOptionElementsList.get(3));

        BigDecimal strikePrice = new BigDecimal(stockOptionElementsList.get(4));

//        return new StockOption(employeeID, date, amountOfStock, strikePrice);

        StockOption theRealStockOption = new StockOption.Builder()
                .employeeId(employeeID)
                .strikePrice(strikePrice)
                .date(date)
                .amountOfStock(amountOfStock).build();

        return theRealStockOption;
    }

}

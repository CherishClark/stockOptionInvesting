
import javax.print.DocFlavor;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        parseMyOptionsYouFool(System.in, System.out);
    }

    public static void parseMyOptionsYouFool(InputStream input, OutputStream output) {

        //    Takes in stdin list of records with
//    current mkt date and price

//    parses which are stock option records and which is the current mkt date and price
//    each record is assigned to an employee
//    calculate profit for each employee
//    return comma del string with employeeId and Profit
        Scanner sc = new Scanner(input);

        Integer a = sc.nextInt();

        Map<String, List<StockOption>> employeesStockOptions = new HashMap<>();

        for (int i = 0; i<a; i++) {

            String stockOptionString = sc.next();

            System.out.println(stockOptionString);

            StockOption parsedOption = stockOptionParser(stockOptionString);

            List<StockOption> listOfStockOptions;

            String employeeId = parsedOption.getEmployeeID();

            if (employeesStockOptions.containsKey(employeeId)) {

                listOfStockOptions = employeesStockOptions.get(employeeId);

            } else {
                listOfStockOptions = new ArrayList<>();
                employeesStockOptions.put(employeeId, listOfStockOptions);
            }

            listOfStockOptions.add(parsedOption);
            System.out.println(employeesStockOptions.get(employeeId));

        }

        String currentMarketInfo = sc.next();

//        parsing current market info
        List<String> currentMarketInfoElementsList = Arrays.asList(currentMarketInfo.split(","));

        String Date = currentMarketInfoElementsList.get(0);
        Double strikePrice = Double.parseDouble(currentMarketInfoElementsList.get(1));
        Date currentDate = new Date();

        try {

            currentDate = new SimpleDateFormat("yyyymmdd").parse(Date);

        } catch (ParseException e) {

            e.printStackTrace();
        }
// end of parsing current market info

        printEmployeeStockOptionsMap(employeesStockOptions);

//  to print out stdout info

        PrintStream printStream = new PrintStream(output);
        printStream.println("help");

        calculateProfitPerEmployee(employeesStockOptions, strikePrice, currentDate);


    }


    public static StockOption stockOptionParser(String stockOption) {

        List<String> stockOptionElementsList = Arrays.asList(stockOption.split(","));

        String employeeID = stockOptionElementsList.get(1);
        String Date = stockOptionElementsList.get(2).toString();
        Date date = new Date();

        try {

            date = new SimpleDateFormat("yyyymmdd").parse(Date);

        } catch (ParseException e) {

            e.printStackTrace();
        }

        int amountOfStock = Integer.parseInt(stockOptionElementsList.get(3));

        double strikePrice = Double.parseDouble(stockOptionElementsList.get(4));

        return new StockOption(employeeID, date, amountOfStock, strikePrice);

    }

    public static void printEmployeeStockOptionsMap(Map employeeStockOptions) {
        System.out.println("Printing out stockoptions....." + employeeStockOptions);


    }

    public static void calculateProfitPerEmployee(Map<String, List<StockOption>> employeesStockOptions, double strikePrice, Date currentDate) {

        employeesStockOptions.forEach((employeeID, stockOptions) -> {
                    double employeeProfit = stockOptions.stream()
                            .filter(stockOption -> stockOption.getDate().compareTo(currentDate) < 0)
                            .mapToDouble(StockOption::getAmountOfStock)
                            .sum();
                    System.out.println("printing employee's" + employeeID + "profit " + employeeProfit);
                }

        );

        System.out.println("printing current date " + currentDate + "and strike price " + strikePrice);



    }

}
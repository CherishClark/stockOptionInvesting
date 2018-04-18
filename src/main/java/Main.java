
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        //    Takes in stdin list of records with
//    current mkt date and price

//    parses which are stock option records and which is the current mkt date and price
//    each record is assigned to an employee
//    calculate profit for each employee
//    return comma del string with employeeId and Profit
        Scanner sc = new Scanner(System.in);

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

        printEmployeeStockOptionsMap(employeesStockOptions);

    }

    public static StockOption stockOptionParser(String stockOption) {

        List<String> stockOptionElementsList = Arrays.asList(stockOption.split(","));

        String employeeID = stockOptionElementsList.get(1);
        String preDate = stockOptionElementsList.get(2).toString();
        Date date = new Date();

        try {

            date = new SimpleDateFormat("yyyymmdd").parse(preDate);

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

}
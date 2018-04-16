
import java.io.File;
import java.text.DateFormat;
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

        for (int i = 0; i<a; i++) {

            String stockOption = sc.next();

            System.out.println(stockOption);

            stockOptionParser(stockOption);

        }

        String currentMarketInfo = sc.next();


    }

    public static void stockOptionParser(String stockOption) {

       List<String> stockOptionElementsList = Arrays.asList(stockOption.split(","));

       String employeeID = stockOptionElementsList.get(1);
       String preDate = stockOptionElementsList.get(2).toString();

       try {
           Date date = new SimpleDateFormat("yyyymmdd").parse(preDate);
           System.out.println("date is " + date);

       } catch (ParseException e) {

            e.printStackTrace();
        }
       int amountOfStock = Integer.parseInt(stockOptionElementsList.get(3));
        System.out.println("amount of stock is "+ amountOfStock);
       double strikePrice = Double.parseDouble(stockOptionElementsList.get(4));
        System.out.println("strike price is " + strikePrice);

//       System.out.println(employeeID + " " + date + " " + amountOfStock + " " + strikePrice);



    }
}
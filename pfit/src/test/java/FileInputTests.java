import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class FileInputTests {

    @Test
    void testCSV(){
        final String file = "C:\\Users\\bquan\\Documents\\Finance\\testcsv.csv";
        boolean test = false;
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            test = true;
        }
        catch(IOException i){
            System.out.println("error");
        }
        Assertions.assertTrue(test);
    }

    @Test
    void testCsvSplit(){
        final String file = "C:\\Users\\bquan\\Documents\\Finance\\testcsv.csv";
        boolean test = false;
        List<List<String>> records = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] csvRow = line.split(",");
                records.add(Arrays.asList(csvRow));
            }
            test = true;
        }
        catch(IOException i){
            System.out.println("error");
        }
        for(var lists : records){
            System.out.println(lists);
        }
        Assertions.assertTrue(test);
    }

    @Test
    void testDateFormatting() {
        String inputDate = "2/20/2023";
        String format = "M/d/yyyy";

        LocalDate enteredDate = LocalDate.parse(inputDate,
                DateTimeFormatter.ofPattern(format));

        LocalDate testDate = LocalDate.of(2023,2,20);

        Assertions.assertEquals(enteredDate, testDate);
    }

    @Test
    void testFinanceFile1(){
        final String file = "C:\\Users\\bquan\\Documents\\Finance\\finance 2022.txt";
        boolean test = false;
        List<List<String>> records = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] csvRow = line.split("\\|\\|");
                records.add(Arrays.asList(csvRow));
            }
            test = true;
        }
        catch(IOException i){
            System.out.println("error");
        }
        System.out.println("# of records: " + records.size());
        for(int i = 0; i < 10; i++){
            System.out.println(records.get(i));
        }
        for(List<String> record : records){
            if(record.size() < 5){
                System.out.println("larger trx: " + record);
            }
        }
        Assertions.assertTrue(test);
    }

    @Test
    void testFinanceFile2(){
        final String file = "C:\\Users\\bquan\\Documents\\Finance\\finance 2022.txt";
        boolean test = false;
        List<List<String>> records = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            while((line = br.readLine()) != null){
                String[] csvRow = line.split("\\|\\|");
                records.add(Arrays.asList(csvRow));
            }
        }
        catch(IOException i){
            System.out.println("error");
        }
        int vendorLength = 0;
        for(var record : records){
            if(vendorLength < record.get(2).length()){
                vendorLength = record.get(2).length();
            }
        }
        System.out.println("Vendor length: "+vendorLength);
        Assertions.assertTrue(vendorLength <= 45);
    }


}

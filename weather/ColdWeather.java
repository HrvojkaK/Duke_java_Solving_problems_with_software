
/**
 * when running test method:
 * Opens multiple csv files (choose from folder) and finds the file with the lowest temperature. 
 * Prints out the lowest temperature and the hour when it was recorded, 
 * and the average temerature during that day.
 * 
 * Opens multiple files (choose from folder again) 
 * and finds the file with the highest humidity, prints its name.
 * 
 * download the compressed folder with csv files from:
 * https://www.dukelearntoprogram.com/course2/data/nc_weather.zip
 */

import org.apache.commons.csv.*;
import java.io.*;
import edu.duke.*;

public class ColdWeather {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        // a method that finds the coldest hour in a single file (passed to it as csv data)
        //and returns the row containing the coldest hour 
        CSVRecord coldest = null;
        //For each row (currentRow) in the CSV File:
        for (CSVRecord currentRow : parser) {
            coldest = coldestOfTwo(coldest, currentRow);
        }
        return coldest;
    }
    
    public void printColdestAverage (String fnm) {
        //a method that takes in the filename, then passes the content of that file
        //to coldestHourInFile method and collects the row with the coldest hour
        //Then calls avgTempInFile method and collects the value of avg.temp in file
        FileResource fr = new FileResource(fnm);
        CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
        System.out.println("The temperature was " + coldest.get("TemperatureF") +
                   "F, measured at " + coldest.get("DateUTC").substring(11,16));
        System.out.println("The average temperature during the day was " + avgTempInFile(fr.getCSVParser()));           
    }
    
    public double avgTempInFile(CSVParser parser) {
        // a method that finds the average temp. in a single file 
        // (passed to it as csv data)  
        double average = 0.0;
        int count = 0;
        
        for (CSVRecord currentRow : parser) {
            average = average + Double.parseDouble(currentRow.get("TemperatureF"));
            count = count +1;
        }
        return average/(double)count;
    }
    
    public String fileWithColdestTemp(){
        //prompts for many files to be chosen, then finds the file with the coldest temp
        // and returns the name + path of that file 
        CSVRecord coldest = null;
        DirectoryResource dr = new DirectoryResource();
        //String fname = "";
            //iterate over files:
            for (File f : dr.selectedFiles()) {
               FileResource fr = new FileResource(f);
               //fname = f.getName();
               CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
               coldest = coldestOfTwo(coldest, currentRow);
        }
        String fname = "weather-" + coldest.get("DateUTC").substring(0,10) + ".csv";
        return "nc_weather/" + coldest.get("DateUTC").substring(0,4) + "/" + fname;    
    }
    
    public CSVRecord coldestOfTwo(CSVRecord coldest, CSVRecord currentRow){
        //takes two rows of csv data and returns the row with the lower temp.
        //
        // check that currentRow has a valid temerature:
        // -9999 is a default number in files for days without a valid temperature reading, so it should be ignored
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
              if (currentTemp == -9999){return coldest;}
              if (coldest == null){coldest = currentRow;}
              else{
                   double coldestTemp = Double.parseDouble(coldest.get("TemperatureF"));
                   if (currentTemp < coldestTemp) {
                    //if so update coldest to currentRow
                    coldest = currentRow; 
                   }
              }
        return coldest;
    }
    
    public String humidityInManyDays(){
        CSVRecord largest = null;
        String fres = "";
        DirectoryResource dr = new DirectoryResource();
            for (File f : dr.selectedFiles()) {
               FileResource fr = new FileResource(f);
               CSVRecord current = humidityInFile(fr.getCSVParser()); //largest row in file
               if (largest == null){
                   largest = current;
                   fres = f.getName();
                }
               else{
                   double currentH = Double.parseDouble(current.get("Humidity"));
                   double largestH = Double.parseDouble(largest.get("Humidity"));
                   //Check if current’s temperature > largest’s
                   if (currentH > largestH) {
                    //If so update largest to current
                    largest = current; 
                    fres = f.getName();
                   }
               }
        }
        System.out.println("Largest humidity, of "+largest.get("Humidity")
                +", was found in file: " + fres);
        return fres;    
    }
    
    public CSVRecord humidityInFile(CSVParser parser) {
        CSVRecord largest = null;
        for (CSVRecord currentRow : parser) {
            if (currentRow.get("Humidity")=="N/A"){continue;} //invalid entry
            if (largest == null) {largest = currentRow;}
            else {
                double currentH = Double.parseDouble(currentRow.get("Humidity"));
                double largestH = Double.parseDouble(largest.get("Humidity"));
                if (currentH > largestH) {
                    largest = currentRow; 
                }
            }
        }
        return largest;
    }
    
    public void test (){
        String fileH = humidityInManyDays();
        System.out.println("Now look at the temperatures:");
        String fnm = fileWithColdestTemp();
        System.out.println("The coldest hour record is in "+fnm);
        printColdestAverage(fnm);
    
    }    
    

}

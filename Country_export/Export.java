
/**
 * Reads a csv file containing data about different countries, their exports and exp.value
 * Prints out the countries exporting 2 selected goods, and countries with
 * total export value of $1,000,000,000 and above
 * 
 */

import org.apache.commons.csv.*;
import edu.duke.*;

public class Export{

    public void whoExports(CSVParser parser, String export1, String export2){
        System.out.println("Countries exporting "+export1+ " and "+export2+ " :");
        for (CSVRecord record : parser){
            //look at the Export column:
            String export = record.get("Exports");
            if (export.contains(export1) && export.contains(export2)){
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    
    public void bigExporters(CSVParser parser){
        System.out.println("The biggest exporters in total, regardless of what they export, are: ");
        for (CSVRecord record : parser){
            //look at the Export column:
            String value = record.get("Value (dollars)");
            if (value.length() >= 14){
                String country = record.get("Country");
                System.out.println(country+ ", makes " +value+ " in exports.");
            }
        } 
    }
    
    //a method that calls and tests whoExport and bigExporters methods:
    public void test(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        whoExports(parser, "diamonds", "gold"); //could check for any other goods
        //now check the big exporters:
        parser = fr.getCSVParser();
        bigExporters(parser);
        }
        
//    public static void main (String[] args) {
//       Export pr = new Export();
//       pr.test(); 
//    }   
}

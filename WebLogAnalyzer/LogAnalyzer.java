
/**
 * Uses WebLogParser to create LogEntry objects from a log file (can read it via readFile method, given a filename),
 * and stores them in a ArrayList records.
 * 
 * countUniqueIPs method goes through IP addresses in records, returns the number of unique ones
 * uniqueIPVisitsOnDay method goes through IP addresses in records on a given date (someday parameter),
 *           (someday must be of the Mmm DD format, ie "Jan 05", "Sep 30" etc),returns the number of unique ones
 * findUniqueIPsInRange method takes in integer values for lower and upper bound for the range of status codes, looks
 *           at the LogEntrie in records which fall under that range, among those collects the ones with unique IPs and
 *           returns the LogEntries in a new ArrayList, selectStatusLogs
 * countVisitsPerIP returns a HashMap containing different IP addresses and the number of
 *           times they've visited the webpage (IP, stored as String, is hash key)
 * mostNumberVisitsByIP returns the max number of visits made by a sngle IP, collected in the HashMap created
 *            by countsVisitsPerIP method
 * iPsForDays goes through records, collects all dates in format Mmm DD and the IPs, and makes a HashMap with dates as keys, 
 *            and, as values, ArrayLists containing IPs of those dates (all IPs on the date, including the same ones)
 *           
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     public LogAnalyzer() {
         //initialize records to an empty ArrayList
         records = new ArrayList<LogEntry>();
     }
        
     public void readFile(String filename) {
         //create FileReseource for filename
         FileResource resource = new FileResource(filename);
         //iterate over its lines
         for(String s : resource.lines()){
             //use WebLogParser.parseEntry to convert string line to LogEntry obj and append to records
             records.add(WebLogParser.parseEntry(s));
         }
     }
     
     public int countUniqueIPs(){
         //make empty list uniqueIPs:
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         //loop through LogEntrys in records and collect unique IPs vis LogEntry.get
         for (LogEntry le : records){
             String address = le.getIpAddress();
             if (!uniqueIPs.contains(address)){
                 uniqueIPs.add(address);
             }
         }
         return uniqueIPs.size(); //return size of list of unique IPs
     }
     
     public int uniqueIPVisitsOnDay(String someday){
         //go through records and check if the date is the same as someday,
         //if so, collect the IP address (just unique ones)
         ArrayList<String> uniqueIPsomeday = new ArrayList<String>();
         for (LogEntry le : records){
             //get only the date from Date object in le, in format of type "Jan 05"
             String str = le.getAccessTime().toString().substring(4,10);
             //if it is equal to someday, check for unique IPs
             if(str.equals(someday)){
                 String address = le.getIpAddress();
                 if (!uniqueIPsomeday.contains(address)){uniqueIPsomeday.add(address);}
             }
         }
         return uniqueIPsomeday.size(); //return number of unique IPs in the "someday" date
     }
     
     public ArrayList findUniqueIPsInRange(int low, int high) {
         //given the lower and upper bound for status codes, go through records 
         //and find the logs with unique IP addresses, that are within in that range
         ArrayList<LogEntry> selectStatusLogs = new ArrayList<LogEntry>();
         //auxilarry list for keeping track of unique IPs:
         ArrayList<String> uniqueIPs = new ArrayList<String>();
         for (LogEntry le : records){
             //if the status codes are in range:
             if(high >= le.getStatusCode() && le.getStatusCode() >= low){
                //only append to selectStatusLogs if IP is unique
                String address = le.getIpAddress();
                if (!uniqueIPs.contains(address)){ //doesn't contain it ->append
                 uniqueIPs.add(address);
                 selectStatusLogs.add(le);
                }
             }
         }
         return selectStatusLogs;   
     }
     
     public HashMap<String,Integer> countVisitsPerIP(){
         //counting how many visits different IP addresses made
         //will use HashMap, IP stored as String as key
         HashMap<String,Integer> countsIPvisits = new HashMap<String,Integer>();
         //go through LogEntries in records and add IPs, as well as their count
         for (LogEntry le : records){
             String ip = le.getIpAddress();
             if(!countsIPvisits.containsKey(ip)){
                 //if HashMap doesn't contain this IP, put it in
                 countsIPvisits.put(ip,1);
             }
             else{
                 //is is already in HashMap ->increase counter
                 countsIPvisits.put(ip,countsIPvisits.get(ip)+1);
             }
         }
         return countsIPvisits;
              //nb. with HashKey, getting the number of unique IPs is just:
              // int uniqueIPs = countsIPvisits.size();
     }
     
     public int mostNumberVisitsByIP(HashMap<String,Integer> countsIPvisits){
         //returns the max number of visits by a single IP, found in HashMap countsIPvisits
         int count = 0; //initialize counter of visits
         for (String key : countsIPvisits.keySet()){
             if(countsIPvisits.get(key)>count){count=countsIPvisits.get(key);}
            }
         return count;
     }
     
     public HashMap<String,ArrayList<String>> iPsForDays(){
         //goes through records and maps dates (Mmm DD format) as keys in HashMap, and the IPs from that days (all) as vals
         HashMap<String,ArrayList<String>> daysIPs = new HashMap<String,ArrayList<String>>();
         
         for (LogEntry le : records){
             //make aux ArrayList to build on and put into HashMap:
             ArrayList<String> ips = new ArrayList<String>();
             //get the date from Date object in le, in format of type "Jan 05"
             String str = le.getAccessTime().toString().substring(4,10);
             //get IP from that line
             String ip = le.getIpAddress();
             if(!daysIPs.containsKey(str)){
                 //if HashMap doesn't contain this date, put it in and just append to empty list ips
                 ips.add(ip);
                 daysIPs.put(str,ips);
             }
             else{
                 //date is already in HashMap ->get non-empty list ips, append and out it into HashMap
                 ips = daysIPs.get(str);
                 ips.add(ip);
                 daysIPs.put(str,ips);
             }
         }
         return daysIPs;
     }
     
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     
}

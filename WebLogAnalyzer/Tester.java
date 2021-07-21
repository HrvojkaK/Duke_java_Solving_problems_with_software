
/**
 * to test LogEntry class (testLogEntry method) and
 * LogAnalyzer class (passing in a filename of an example log file within this folder - "short-test_log")
 * 
 */

import java.util.*;

public class Tester
{
    public void testLogEntry() {
        LogEntry le = new LogEntry("1.2.3.4", new Date(), "example request", 200, 500);
        System.out.println(le);
        LogEntry le2 = new LogEntry("1.2.100.4", new Date(), "example request 2", 300, 400);
        System.out.println(le2);
    }
    
    public void testLogAnalyzer() {
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("short-test_log");
        la.printAll();
        System.out.println("There are "+ la.countUniqueIPs() + " unique IPs in log file.");
        String someday = "Sep 30"; //can try different dates, but they have to have this format (Mmm DD)
        System.out.println("On "+someday+
        " there were "+la.uniqueIPVisitsOnDay(someday) + " visits from unique IP addresses.");
        int lower = 300; //define input parameters for findUniqueIPsInRange method 
        int higher = 302; //upper bound
        System.out.println("Log entries with status codes within range of "+lower+","+higher+" and unique IPs are:");
        ArrayList<LogEntry> selectLogs = la.findUniqueIPsInRange(lower,higher);
        for (LogEntry le : selectLogs) {System.out.println(le);}
        
        System.out.println("\n IP address=number of visits:");
        HashMap<String,Integer> visits = la.countVisitsPerIP();
        System.out.println(visits);
        System.out.println("The max number of visits by a single IP recorded in log file is: "
        +la.mostNumberVisitsByIP(visits));
        HashMap<String,ArrayList<String>> datesIPs = la.iPsForDays();
        System.out.println("\n Date website vas visited = [IP addresses that made the visit]:");
        System.out.println(datesIPs);
    }
}

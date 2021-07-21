For analyzing log entry files.
LogAnalyzer class can, when given a filename of a log file (example file: "short-test_log" within this folder),
read the content of the file as string, line by line, and parse it via WebLogParser class
(which was provided by the Duke Uni). WebLogParser returns the parsed string as a LogEntry object
(instance of LogEntry class, which holds and can return via its get methods data like IP adress (as string), date (as Date obj), etc).
LogAnalyzer saves each LogEntry obj obtained from a log file, as a single entry in ArrayList records.
LogAnalyzer has methods:
    countUniqueIPs: returns the number of unique IP addresses in the records list
    printAll: prints all the data in records list, line by line (IPaddress,time of visit,request that was made,response,bytes sent)
    uniqueIPVisitsOnDay: returns the number of unique IP addresses in the records list on a given date, passed in as String someday
                                (someday must be in Mmm DD format, ie "Jan 05", "Sep 30" etc)
    findUniqueIPsInRange: returns an ArrayList which has selected LogEntrie as elements: these LogEntries have status codes within some 
                              int range that was passed in (as 2 int parameters: low,high), and have unique IPs  
    countVisitsPerIP: returns a HashMap containing different IP addresses and the number of times they've visited the webpage 
                                                                                  (IP, stored as String, is hash key)
    mostNumberVisitsByIP: returns the max number of visits made by a sngle IP, collected from the HashMap passed in (this HashMap is
                                   created by the countsVisitsPerIP method)  
    iPsForDays: returns HashMap that has dates as keys (in format Mmm DD, collected from records), and as values, IPs from those dates
                 (stored in ArrayLists)
                        

    
Tester class tests LogEntry, and methods of LogAnalyzer (passing in the name of the log file to be analyzed)    
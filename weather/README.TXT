WeatherCSV:
when running test method, 3 calls are made:

fileWithColdestTemp();
  Opens multiple csv files (choose from folder) and finds the file with the lowest temperature. 
  Prints out the lowest temperature and the hour when it was recorded.
printColdestAverage(fnm); 
  Prints the average temerature during that day.
  
hunidityInManyDays():
  Opens multiple files (choose from folder again) 
  and finds the file with the highest humidity, prints its name.

A folder with csv files can be downloaded from https://www.dukelearntoprogram.com/course2/data/nc_weather.zip
(just unpack).


WeatherCSV uses edu.duke and org.apache.commons.csv. library - they comes with Duke Uni's edition of BlueJ environment, downloadable from
https://www.dukelearntoprogram.com//course2/index.php
The libraris can also be downloaded separately as JAR, from:
https://www.dukelearntoprogram.com//downloads/bluej.php?course=2
(edu.duke and org.apache... link, bottom of the page).

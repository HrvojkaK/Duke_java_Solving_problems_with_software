
/**
 * reads the lines from the file at this URL location, 
 * http://www.dukelearntoprogram.com/course2/data/manylinks.html, 
 * and prints each URL on the page that is a link to youtube.com
 * 
 */
import edu.duke.*;
//import java.io.*;

public class Weblink {
    public void find(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com//course2/data/manylinks.html");
        for (String s : ur.words()) {
            String sLow = s.toLowerCase();
            if (sLow.contains("youtube.com")){
                int start = sLow.indexOf("youtube.com");
                int left = leftIndex(sLow, start);
                int right = sLow.indexOf("\"", start);
                String lnk = s.substring(left,right+1);
                System.out.println(lnk);
            }
        }
    }
    
    public int leftIndex(String sLow, int start){
        int i = start;
        while (i>=0){
            if (sLow.substring(i,i+1).matches("\"")){return i;}
            i = i-1;
        }
        return 0;
    }
    
//    public static void main (String[] args) {
//        weblink pr = new weblink();
//        pr.find();
//    }
}
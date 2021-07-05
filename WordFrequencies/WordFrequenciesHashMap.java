/**
 * counts how many times each word occurs, and what is the most frequent word
 * this version with HashMap instead of ArrayLists 
 */
import edu.duke.*;
import java.util.HashMap;

public class WordFrequenciesHashMap{
    private HashMap<String,Integer> myFreqs;
        
    public WordFrequenciesHashMap() {
        myFreqs = new HashMap<String,Integer>();
    }
    
    public void findUniqueWords(){
        FileResource resource = new FileResource();
        //use the FileResource'd words method to go through all the words in a file:
        for(String s : resource.words()){
            s = s.toLowerCase();
            //if myFreqs contains the word as key (keySet method makes an iterable of keys):
            if (myFreqs.keySet().contains(s)){
                //increment its counter (to the integer value associated with key=s, put value of old value+1)
                myFreqs.put(s,myFreqs.get(s)+1);
            }
            //elif myFreqss doesn't contain the word, append it with int value 1:
            else {
                myFreqs.put(s,1);
            }
        }
    }
    
    private String findIndexOfMax(){
        int maxIndex = 0;
        String keyMax = "";
        //loop through all of the elements in myFreqs and when a larger frequency than the previous one is found, update:
        for(String s : myFreqs.keySet()){
            if (myFreqs.get(s) > maxIndex){
                maxIndex = myFreqs.get(s);
                keyMax = s;
            }
        }
        return keyMax;
    }
    
        public void test(){
        findUniqueWords();
        System.out.println("Number of unique words is "+ myFreqs.size());
        for(String s : myFreqs.keySet()){
        System.out.println(s + " occured "+myFreqs.get(s) + " times");
        }
        String keyMax = findIndexOfMax();
        System.out.println("The word with the maximum frequency: "+keyMax
        +" occured "+myFreqs.get(keyMax)+" times.");
    }
}



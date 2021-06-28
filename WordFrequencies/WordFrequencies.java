/**
 * counts how many times each word occurs, and what is the most frequent word
 */
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies{
    private ArrayList<Integer> myFreqs;
    private ArrayList<String> myWords;
        
    public WordFrequencies() {
        myFreqs = new ArrayList<Integer>();
        myWords = new ArrayList<String>();
    }
    
    public void findUniqueWords(){
        FileResource resource = new FileResource();
        //use the FileResource'd words method to go through all the words in a file:
        for(String s : resource.words()){
            s = s.toLowerCase();
            int index = myWords.indexOf(s);
            //if myWords doesn't contain the word, append it:
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            //else, if it does, just increment the counter of the word in myFreqs:
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public int findIndexOfMax(){
        //start with the first element as initial values of max (max word occurence) and maxIndex (its index in myFreqs):
        int max = myFreqs.get(0);
        int maxIndex = 0;
        //loop through all of the elements in myFreqs and when a larger frequency than the initial one is found, update:
        for(int i=0; i < myFreqs.size(); i++){
            if (myFreqs.get(i) > max){
                max = myFreqs.get(i);
                maxIndex = i;
            }
        }
        return maxIndex;
    }
    
        public void test(){
        findUniqueWords();
        System.out.println("Number of unique words is "+ myWords.size());
        for(int i=0; i<myWords.size(); i++){
        System.out.println(myWords.get(i) + " occured "+myFreqs.get(i) + " times");
        }
        int indexMax = findIndexOfMax();
        System.out.println("The word with the maximum frequency: "+myWords.get(indexMax)
        +" occured "+myFreqs.get(indexMax)+" times.");
    }
}


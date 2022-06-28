import java.util.*;
import edu.duke.*;
import javafx.util.Pair;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        //slices the (encrypted)string into single-key encrypted parts
        StringBuilder sb = new StringBuilder();
        for (int i=whichSlice; i<message.length(); i+=totalSlices){
            sb.append(message.charAt(i));
        }
        return sb.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //create CeaserCracker obj, pass sliced encrypted message and get the key vith getKey()
        CaesarCracker cc = new CaesarCracker(mostCommon);
        for (int i = 0; i < klength; i++){
            String sliced = sliceString(encrypted, i, klength);
            key[i] = cc.getKey(sliced);
        }
        return key;
    }
   
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> words = new HashSet<String>();
        for (String s : fr.words()) {
            words.add(s.toLowerCase());
        }
        return words;
    }
    
    public int countWords(HashSet dictionary, String message){
        String[] messageWords = message.split("\\W+");
        int counter = 0;
        for (String mw : messageWords){
            if (dictionary.contains(mw.toLowerCase())){ counter+=1; }
        }
        return counter;
    }
    
    public Pair<Integer,String> breakForLanguage(HashSet dictionary, String encrypted){
        // get the most common letter in dictionary:
        char mostCommon = mostCommonCharIn(dictionary);
        // set the maximum key length to be tested (could do klenMax = message.length() but this would take a long time)
        //int klenMax = (int) Math.ceil(encrypted.length()/20);
        int klenMax = 100;
        //initialize and go!
        int mostWordsFound = 0;
        int bestKlen = -1;
        String bestDecrypted = "";
      
        for (int klen = 1; klen<klenMax; klen++){
            //pass klen & encrpted mess,
            int [] key = tryKeyLength(encrypted,klen,mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            if(countWords(dictionary,decrypted) > mostWordsFound){
                mostWordsFound = countWords(dictionary,decrypted);
                bestDecrypted = decrypted;
                bestKlen = klen;
            }
        }
        //print out the results of code breaking:
        System.out.println("max words found ("+mostWordsFound+"), for key length: "+bestKlen);
        System.out.println("");

        return new Pair<Integer,String>(mostWordsFound,bestDecrypted);
    }
    
    public String breakForAllLangs(String encrypted, HashMap<String,HashSet<String>> languages){
        int maxWords=0;
        String bestLang="";
        String bestDecrypted="";
        //iterate over all of the dictionaries in HashMap languages:
        for (String lang : languages.keySet()){
            //print some general info on what I'm testing:
            System.out.println("Trying "+lang+" :");
            Pair<Integer, String> p = breakForLanguage(languages.get(lang),encrypted);
            //int m = breakForLanguage(languages.get(lang),encrypted).getKey();
            int m = p.getKey();
            if (m > maxWords){ 
                bestLang = lang;
                bestDecrypted = p.getValue();//breakForLanguage(languages.get(lang),encrypted).getValue();
                maxWords = m;
            }
        }
        //print out what is the best result:
        System.out.println("Message decrypts best when using "+bestLang+" language.");
        //now to actually print out the best decrypted message:
        return bestDecrypted;
    
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        // create a HashMap with all of English letters as keys (chars), 
        // populate it with the letters and initialize their count as 0
        HashMap<Character,Integer> myChars = new HashMap<Character,Integer>();
        for (char ch = 'a'; ch <= 'z'; ++ch) {
            myChars.put(Character.valueOf(ch), 0);
        }
  
        for(String word : dictionary){
            //go through all the words in dictionary, for each go through all its chars
            for(char ch : word.toLowerCase().toCharArray()) {
                //if myChars contains the chat ch as key
                if (myChars.keySet().contains(ch)){
                //increment its counter (to the integer value associated with key=ch, put value of old value+1)
                myChars.put(ch,myChars.get(ch)+1);
                }
            }
        }
        char maxChar = findMaxChar(myChars);
        return maxChar;
    }
    
    private char findMaxChar(HashMap<Character,Integer> myChars){
        //find which char in myChars HashMap occurs the most (has largest int)
        int maxIndex = 0;
        char maxChar = '\0';
        //loop through all of the elements in myChar and when a larger integer than the previous one is found, update:
        for(Character ch : myChars.keySet()){
            if (myChars.get(ch) > maxIndex){
                maxIndex = myChars.get(ch);
                maxChar = ch;
            }
        }
        return maxChar;
    }
    
    
    public void breakVigenere () {
        //run from here/main
        System.out.println("Asking for the encrypted message file");
        FileResource fr = new FileResource();
        String message = fr.asString();
        
        System.out.println("***********************************");
        System.out.println("Asking for dictionaris: in order:");
        System.out.println("Danish,  Dutch,  English,  French");
        System.out.println("German,  Italian,  Portuguese,  Spanish");
        
        HashMap<String,HashSet<String>> languages = new HashMap<String,HashSet<String>>();
        
        List<String> strings = Arrays.asList("Danish","Dutch","English","French","German","Italian","Portuguese","Spanish");
        for (String s : strings){
            FileResource frr = new FileResource();
            HashSet<String> t = readDictionary(frr);
            languages.put(s,t);
            System.out.println("read in "+s);
        }

        //System.out.println(breakForAllLangs(message,languages)); //this might be too long
        String upTo = breakForAllLangs(message, languages).substring(0,100);
        System.out.println(upTo);
    }    
       
    
}

package Caesar_cipher_and_breaker;


/**
 * breakTwoKeys method breaks the message into two: counting letters in the original, it makes a string with just the odd letters, 
 * and a string just with even. Then it decrypts these two separately (finds two keys):
 * 
 * getKey method takes in a message decrypted with a single key as a parameter, finds the most frequent letter in the message, 
 * assuming this letter is an encrypted 'e' calculates the corresponding key, and returns it.
 * This is repeated with the other half of the message, then 
 * the result is put together in breakTwoKeys method, which then instantiates TwoKeysCaesarCipher with the 'inverse keys' (opposite of
 * encryption keys, that is, 26-key), collects and returns the decoded message.
 *
 *
 * has 2 simple test methods, to test if TwoKeysCaesarBreaker works (testBreakTwoKeys) and if TwoKeysCaesarCipher works (testTwoKeys)
 */

public class TwoKeysCaesarBreaker {
    public int[] countLetterOccurance(String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        //Make an array to hols the numbers of occurance of each letter
        int[] counts = new int[26];
        for(int i=0; i < message.length(); i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int idx = alphabet.indexOf(ch);
            //If ch exists in the alphabet, count the occurance
            if(idx != -1){counts[idx] += 1;}
        }
        return counts;
    }
    
    public int getMaxIndex(int[] vals){
        int maxIndex = 0;
        for (int i=0; i<vals.length; i++){
            if (vals[i] > vals[maxIndex]){maxIndex = i;}
        }
        return maxIndex;
    }
    
    public int getKey(String encryptedS){
        int[] occurences = countLetterOccurance(encryptedS);
        int maxIndex = getMaxIndex(occurences);
        //Assuming the most frequent letter (say, "a", found in occurences[0]) 
        //was really the encrypted "e", the key used to encrypt it has
        //shifted the alphabet by 4: "e",4 --->"a",0 
        int key = maxIndex - 4;
        if (maxIndex < 4){ key = 26 - (4-maxIndex); } 
        return key;
    }
    
    public String halfOfString(String message, int start){
        StringBuilder halfOfMessage = new StringBuilder("");
        int i = start;
        for (i=start; i<message.length(); i+=2){
            halfOfMessage.append(message.charAt(i));
        }
        return halfOfMessage.toString();
    }
    
    public String breakTwoKeys(String encrypted){
        String evenHalf = halfOfString(encrypted, 0);
        String oddHalf = halfOfString(encrypted, 1);
        int key1 = getKey(oddHalf);
        int key2 = getKey(evenHalf);
        System.out.println("Even half uses "+key2+". Odd half uses "+key1+".");
        TwoKeysCaesarCipher cypher = new TwoKeysCaesarCipher(26-key1,26-key2); //instantiate TwoKeysCaesarCipher
        String decrypted = cypher.encryptTwoKeys(encrypted); //decrypt using TwoKeysCaesarCipher instance with 'inverse keys' (26-key)
        return decrypted;
    }
    
    
    public void testTwoKeys(){
        String testMessage = "Weeeee! Evy is here! She was away for long, and we had missed her dearly.";
        TwoKeysCaesarCipher two = new TwoKeysCaesarCipher(17,23);
        String encrypted2 = two.encryptTwoKeys(testMessage);//encrypt testMessage
        System.out.println(encrypted2);
        String decrypted2 = two.decryptTwoKeys(encrypted2);//decrypt using same instance
        System.out.println(decrypted2);
    }
    public void testBreakTwoKeys(){
        String encrypted2Key ="Tvbvbv! Bmv fj ybib! Jev nxj rtrv cfo ifkx, xea tv yxu dfjpva evo avxiip.";
        String decrypted2Key = breakTwoKeys(encrypted2Key);
        System.out.println(decrypted2Key);
        //nb. If "Weeeee!" is taken out of message, there are not enouh letters 'e'
        //for the even part to be decrypted. 
    }
}

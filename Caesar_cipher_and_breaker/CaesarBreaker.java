
/**
 * breaks the coded message (coded by CaesarCipher class), by guessing the key.
 * Uses the assumption that the letter 'e' is the one with the most occurences in the message 
 * (because 'e' is the most used letter in English). So needs a long message to break CaesarCipher.
 * 
 * breakCaesar method takes in a message decrypted with a single key as a parameter, finds the most frequent letter in the message, 
 * and, assuming this letter is an encrypted 'e', calculates the corresponding key.
 * Once the key is calculated, CaesarCipher is called with this key value and used to decrypt via it's decrypt method.
 * 
 * has 2 methods just to quick-check the code: testCaesarBreaker tests the breakCaesar method,
 * and testCaesarCipher instantiates CaesarCipher and tests its encrypt and decrypt method 
 * 
 */

public class CaesarBreaker {
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
    
    public String breakCaesar(String encryptedMsg){
        int[] occurences = countLetterOccurance(encryptedMsg);
        int maxIndex = getMaxIndex(occurences);
        //Assuming the most frequent letter (say, "a", found in occurences[0]) 
        //was really the encrypted "e", the key used to encrypt it has
        //shifted the alphabet by 4: "e",4 --->"a",0 
        int key = maxIndex - 4;
        if (maxIndex < 4){ key = 26 - (4-maxIndex); } 
        //print out the key:
        System.out.println("The key used to encrypt the message was: "+key);
        //to decrypt, create a new CaesarCipher obj with key, and call its decrypt method:
        CaesarCipher cc = new CaesarCipher(key);
        String decrypted = cc.decrypt(encryptedMsg);
        return decrypted;
    }
    
    //test CaesarCipher class will encrypt and decrypt properly:
    public void testCaesarCipher() {
        String testMessage = "Evy is here! She was away for long, and we had missed her dearly.";
        CaesarCipher cc = new CaesarCipher(17);
        String encrypted = cc.encrypt(testMessage);
        System.out.println(encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);        
    }
    //now use the CaesarCipher generated encrypted message, to test the CaesarBreaker:
    public void testCaesarBreaker(){
        //the encrypted message I wish to decrypt using CaesarBreaker:    
        String encryptedMsg = "Vmp zj yviv! Jyv nrj rnrp wfi cfex, reu nv yru dzjjvu yvi uvricp.";
        //call the breakCaesar method and print out the result:
        String decryptedMsg = breakCaesar(encryptedMsg);
        System.out.println(decryptedMsg);
    }
}

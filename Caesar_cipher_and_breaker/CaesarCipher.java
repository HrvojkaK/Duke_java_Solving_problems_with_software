
/**
 * given an integer key, can either encrypt (via encrypt method) or decrypt (decrypt method) a message passed in
 * 
 * The key is an integer by which the alphabet is shifted and, using this shifted alphabet, a message is encrypted.
 * For example, key = 1 will replace all of the letters A in the original message with B in the encrypted message, 
 * then B --> C, ..., Z --> A.
 * 
 */

public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int key){
        mainKey = key;
        //Write down the alphabet
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        shiftedAlphabet = alphabet.substring(key,alphabet.length())+
        alphabet.substring(0,key);
    }
    
    public String encrypt(String input) {
        //Make a StringBuilder with message, call it encrypted
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Set up the flag which will mark lower case letters
            int flag =0;
            //If the letter is lowercase, convert to upper and change flag
            if(Character.toLowerCase(currChar)==currChar){
                flag=1; 
                currChar = Character.toUpperCase(currChar);            
            }
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                if(flag ==1){
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }                
                else { encrypted.setCharAt(i, newChar); }
            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    
    public String decrypt(String input){
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        String decrypted = cc.encrypt(input);
        return decrypted;
    }
  
}


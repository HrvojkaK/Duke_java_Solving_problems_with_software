
/**
 * 2 - key variant of CaesarCipher
 * uses key2 on first, third...etc character, and key1 on second, fourth... character.
 * 
 * 2 methods, encryptTwoKeys (for a given instance, ie given key1 and key2, when given an input message will return an encrypted verion),
 * and decryptTwoKeys (takes in encrypted and return a de-crypted message)
 */

public class TwoKeysCaesarCipher {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;
    public TwoKeysCaesarCipher(int key1, int key2){
        mainKey1 = key1;
        mainKey2 = key2;
        //Write down the alphabet:
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabets
        shiftedAlphabet1 = alphabet.substring(key1,alphabet.length())+
        alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2,alphabet.length())+
        alphabet.substring(0,key2);
    }
    
    public String encryptTwoKeys(String input) {
        //Make a StringBuilder with message, call it encrypted
        StringBuilder encrypted = new StringBuilder(input);
        //Count from 0 to < length of encrypted
        for(int i = 0; i <encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Set up the flag which will mark lower case letters
            int flag =0;
            //If the letter is lowercase, convert to upper and change flag
            if(Character.toLowerCase(currChar)==currChar){
                flag=1; 
                currChar = Character.toUpperCase(currChar);            
            }
            //Find the index of currChar in the alphabet (idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of the corresponding shiftedAlphabet (newChar)
                //Odd characters use key1, even characters use key2
                char newChar = '*';
                if(i%2 == 0 || i==0){newChar = shiftedAlphabet2.charAt(idx);}
                else {newChar = shiftedAlphabet1.charAt(idx);}
                //Replace the ith character of encrypted with newChar
                if(flag ==1){
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));
                }                
                else { encrypted.setCharAt(i, newChar); }
            }
            //Otherwise: do nothing
        }
        return encrypted.toString();
    }

    public String decryptTwoKeys(String input){
        TwoKeysCaesarCipher two = new TwoKeysCaesarCipher(26-mainKey1, 26-mainKey2);
        String decrypted = two.encryptTwoKeys(input);
        return decrypted;
    }
}

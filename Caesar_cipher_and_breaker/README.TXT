CaesarCipher: given an integer key, can either encrypt (via encrypt() method) or decrypt (decrypt() method) a message passed in
The key is an integer by which the alphabet is shifted and, using this shifted alphabet, a message is encrypted.
For example, key = 1 will replace all of the letters A in the original message with B in the encrypted message, then B --> C, ..., Z --> A.


TwoKeysCaesarCipher is using the same algorithm, but with 2 keys: it separates the input message on odd and even chunk, 
and uses key2 on first, third...etc character of the message, and key1 on second, fourth... character.


CaesarBreaker decrypts an encrypted message passed in, by guessing the key.
It uses the assumption that the letter 'e' is the one with the most occurences in the message
(because 'e' is the most used letter in English). 
breakCaesar() method takes in a message decrypted with a single key as a parameter, finds the most frequent letter in the message, 
and, assuming this letter is an encrypted 'e', calculates the corresponding key. Once the key is calculated, 
CaesarCipher is instantiated with this key value, and used to decrypt the message via it's decrypt() method.
 * has 2 methods just to quick-check the code: testCaesarBreaker() tests the breakCaesar method,
    testCaesarCipher() instantiates CaesarCipher and tests its encrypt and decrypt method


TwoKeysCaesarBreaker decrypts an encrypted message, assuming it was encrypted using two keys, one for odd, second for even letters.
breakTwoKeys() method breaks the message into two: counting letters in the original, it makes a string with just the odd letters, 
and a string just with even. Then decrypts these two separately (finds two keys, with getKey() method).
 
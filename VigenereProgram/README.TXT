Assignment for Duke Uni's course. Decrypts an encoded message, written in an unknown
language, encrypted with a Vigenere cypher of an unknown key & key length.

Compile and run the breakVigenere() method, in VigenereBreaker class.

When run, program asks for an encrypted message to be decrypted (choose 
from 'messages' directory), and then for the dictionaries: choose all from 
'dictionaries' directory, in alphabetical order. Program will try all of them,
and for each it will test decryption keys of various lenghts (up to 100 chars - 
can modify this in breakForLanguage method in VigenereBreaker class). For each 
language it then finds the best key (keylength) - depending on how many actual 
words from the imported dictionary this key has yielded. Then (based on which 
language gave the biggest wordcount) it decides which language was the original 
message written in, and decrypts it. 
WordFrequencies determines the word that occurs the most often in a file. Uses FileResource from edu.duke to read a .txt file.
If more than one word occurs as the most often, returns the first such word found. All words are made lowercase before counting them;
so, “This” and “this” will both be counted as the lowercase version of “this”. Does not consider punctuation, so “end” and “end,” 
are considered different words.

method findUniqueWords reads in the file using FileResource, goes through all the words in it,
and populates the myWords Arraylit with unique ones, while keeping their counts in the
corresponding index locations of the myFreqs ArrayList

method findIndexOfMax finds the index of the word with max occurence in myWords

test method is just to test WordFrequencies


WordFrequenciesHashMap is the same as WordFrequencies, except it uses HashMap instead of ArrayLists to count and store unique words.

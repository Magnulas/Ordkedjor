package util;

import java.util.BitSet;
import java.util.HashMap;

/**
 * A data structure that works like a HashMap<char[], boolean> but solves the issue with
 * the lack of a good equals-method of char[]. Here is a demonstration of the issue:
 * 
 * 		HashMap<char[], Boolean> map = new HashMap<char[], Boolean>();
 * 		map.put("test".toCharArray(), true);
 * 		assert map.get("test".toCharArray()); // assertion fails
 * 
 * The add, remove, empty and contains operations run in O(1) and should be faster that
 * in a regular HashMap. In order to reach that speed it requires quite a lot of memory.
 * Memory usage is proportional against the number of letters in the words and the alphabet
 * size. It uses approximately 100 Kb memory if the alphabet contains 30 letters and each
 * word is 4 letters long. 30^4/8/1024 = 98.88 Kb.
 * 
 * WARNING!
 * Do not use WordMap for long words, it uses too much memory.
 * The recommended word length is 4 letters or less.
 * 
 * @author Anton Holmberg
 *
 */
public class WordMap {
	private BitSet containsWord;
	private HashMap<Character, Integer> letterIndexes;
	private int nWordCombinations;
	
	public WordMap(char[] alphabet, int wordLength) {
		
		nWordCombinations = (int) Math.pow(alphabet.length, wordLength);
		containsWord = new BitSet(nWordCombinations);
		
		letterIndexes = new HashMap<Character, Integer>();
		for(int i = 0; i < alphabet.length; i++) {
			letterIndexes.put(new Character(alphabet[i]), i);
		}
	}

	public void add(char[] word) {
		containsWord.set(indexForWord(word), true);
	}
	
	public void remove(char[] word) {
		containsWord.set(indexForWord(word), false);
	}
	
	public boolean contains(char[] word) {
		return containsWord.get(indexForWord(word));
	}
	
	public void empty() {
		containsWord = new BitSet(nWordCombinations);
	}
	
	private int indexForWord(char[] word) {
        int index = 0;
        for(int i = 0; i < word.length; i++) {
        	index += (int)Math.pow(letterIndexes.size(), i) * letterIndexes.get(new Character(word[i]));
        }
        return index;
	}
}

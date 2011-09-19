package util;

import java.util.ArrayList;

public class WordMapTest {

	public static void main(String[] args) {
		
	    char [] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
				       'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
				       's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 
				       'ä', 'ö', 'é' };
		WordMap wordMap = new WordMap(alphabet, 4);
		
		assert !wordMap.contains("test".toCharArray());
		wordMap.add("test".toCharArray());
		assert wordMap.contains("test".toCharArray());
		wordMap.remove("test".toCharArray());
		assert !wordMap.contains("test".toCharArray());
		
		char[] firstWord = {alphabet[0], alphabet[0], alphabet[0], alphabet[0]};
		char[] lastWord = {alphabet[alphabet.length-1],alphabet[alphabet.length-1], alphabet[alphabet.length-1], alphabet[alphabet.length-1]};
		assert !wordMap.contains(firstWord);
		assert !wordMap.contains(lastWord);
		wordMap.add(firstWord);
		wordMap.add(lastWord);
		assert wordMap.contains(firstWord);
		assert wordMap.contains(lastWord);
		wordMap.remove(firstWord);
		wordMap.remove(lastWord);
		assert !wordMap.contains(firstWord);
		assert !wordMap.contains(lastWord);
		
		ArrayList<String> addedWords = new ArrayList<String>();
		StringBuilder wordBuilder = new StringBuilder(); 
		for(int i = 0; i < 3000; i++) {
			wordBuilder.delete(0, wordBuilder.length());
			for(int j = 0; j < 4; j++) {
				wordBuilder.append(alphabet[(int)(Math.random() * alphabet.length)]);
			}
			String word = wordBuilder.toString();
			if(!addedWords.contains(word)) {
				addedWords.add(word);
				wordMap.add(word.toCharArray());
			}
		}
		
		int nAddedWords = addedWords.size();
		for(int i = 0; i < nAddedWords; i++) {
			char[] currentWord = addedWords.remove(addedWords.size() - 1).toCharArray();
			assert wordMap.contains(currentWord);
			wordMap.remove(currentWord);
			assert !wordMap.contains(currentWord);
			for(String word : addedWords) {
				assert wordMap.contains(word.toCharArray());
			}
		}
		
	}
}

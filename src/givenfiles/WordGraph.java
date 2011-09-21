package givenfiles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
import java.util.Vector;
import java.io.*;

import util.WordMap;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordGraph
{
    static HashMap<String, ArrayList<String>> neighborMap; // ordlista
    //static private WordMap used; // databas med använda ord
    static private HashMap<String, Boolean> used; // databas med använda ord
    static int wordLength;
    static int size; // antal ord i ordlistan

    // Read läser in en ordlista från strömmen input. Alla ord ska ha
    // wordLength bokstäver.
    static public void Read(int wordLength_, BufferedReader input)throws IOException {
		wordLength = wordLength_;
		size = 0;
		neighborMap = new HashMap<String, ArrayList<String>>();
		neighborMap.keySet();
		
		while (true) {
		    String s = input.readLine();
		    if (s.equals("#")) break;
		    if (s.length() != wordLength)
			System.out.println("Rad " + size + 
					   " i filen innehåller inte " + 
					   wordLength + " tecken.");
		    neighborMap.put(s, new ArrayList<String>());
		    size++;
		}
		for(String currentWord : neighborMap.keySet()) {
			for(String otherWord : neighborMap.keySet()) {
				if(areNeighbors(currentWord, otherWord)) {
					neighborMap.get(currentWord).add(otherWord);
//					System.out.println("currentWord: " + currentWord + " neighbor: " + otherWord);
				}
			}
		}
		
		used = new HashMap<String, Boolean>();
		resetUsed();
    }
    
    private static boolean areNeighbors(String s1, String s2) {
    	int nDifferentChars = 0;
    	for(int i = 0; i < s1.length(); i++) {
    		if(s1.charAt(i) != s2.charAt(i)) {
    			nDifferentChars++;
    			if(nDifferentChars >1) {
    				return false;
    			}
    		}
    	}
    	return nDifferentChars == 1;
    }

    // Contains slår upp w i ordlistan och returnerar ordet om det finns med.
    // Annars returneras null.
    static public ArrayList<String>getNeighbors(String word) {
    	return neighborMap.get(word);
    }

    static public void markAsUsed(String word) {
    	used.put(word, true);
    }
    
    static public boolean isUsed(String word) {
    	return used.get(word);
    }

    // EraseUsed gör så att inga ord anses använda längre.
    static public void resetUsed(){
		for(String currentWord : neighborMap.keySet()) {
			used.put(currentWord, false);
		}
    }
}

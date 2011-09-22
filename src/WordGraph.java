import java.util.ArrayList;
import java.util.HashMap;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordGraph
{
    private HashMap<String, ArrayList<String>> neighbourMap; // ordlista
    
    public WordGraph(ArrayList<String> words) {
		neighbourMap = new HashMap<String, ArrayList<String>>();
		for(String currentWord : words) {
			ArrayList<String> neighbours = new ArrayList<String>();
			for(String otherWord : words) {
				if(areNeighbors(currentWord, otherWord)) {
					neighbours.add(otherWord);
				}
			}
			neighbourMap.put(currentWord, neighbours);
		}
    }
    
    private boolean areNeighbors(String s1, String s2) {
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
    
    private WordRec breadthFirst(String startWord,String endWord){
    	
		WordRec start = new WordRec(startWord, null, true);
	    HashMap<String, Boolean> used = new HashMap<String, Boolean>(); // databas med använda ord
		Queue<WordRec> q = new Queue<WordRec>();
		
		q.put(start);
		WordRec wr = null;
		
	    while (!q.isEmpty()) {
	    	WordRec currentRec = q.get();
	    	
	    	ArrayList<String> neighbours = neighbourMap.get(currentRec.getWord());
	    	
	    	for(String neighbour : neighbours){
	    		if(used.get(neighbour) == null){ // null is false
	    			used.put(neighbour, true);
	    			
	    			wr = new WordRec(neighbour, currentRec, true);
	    			if (endWord != null && neighbour.equals(endWord)) {
	    				return wr;
	    			}
	    			q.put(wr);
	    		}
	    	}
	    }
	    
	    if(endWord != null) {
	    	return null;
	    }
	    
	    return wr;
    }
    
    public WordRec shortestPathBetween(String startWord, String endWord) {
    	return breadthFirst(endWord,startWord);
    }

    // CheckAllStartWords hittar den längsta kortaste vägen från något ord
    // till endWord.
    public WordRec longestOfTheShortestPathsTo(String startWord){
		return breadthFirst(startWord, null);
    }
}

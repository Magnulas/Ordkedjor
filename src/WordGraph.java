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
    	
//    	Denna är för att kolla att man inte använder ett ord som inte finns.
//    	Men i uppgiftslydelsen är det givet att alla ord man söker på finns
//    	i ordlistan så detta är inte problemet.
//    	Dock så var det problem om man sökte på ett isolerat ord, dvs. inga grannar.
//    	Detta är ändrat nu och man får tillbaka en "kedja" med bara det ordet.
//    	if(!neighbourMap.containsKey(startWord)){
//    		return null;
//    	}
		WordRec wr = new WordRec(startWord, null);
	    HashMap<String, Boolean> used = new HashMap<String, Boolean>(); // databas med använda ord
		Queue<WordRec> q = new Queue<WordRec>();
		
		q.put(wr);
//    	Fanns problem för:
//    	aaaa
//    	aaan
//    	#
//    	aaaa
//    	Ger:
//    	aaaa 3 ord
//    	aaaa -> aaan -> aaaa
//		Fixat genom used.put(startWord,true);
		used.put(startWord,true);
		
	    while (!q.isEmpty()) {
	    	WordRec currentRec = q.get();
	    	
	    	ArrayList<String> neighbours = neighbourMap.get(currentRec.getWord());
	    	
	    	for(String neighbour : neighbours){
	    		if(used.get(neighbour) == null){ // null is false
	    			used.put(neighbour, true);
	    			
	    			wr = new WordRec(neighbour, currentRec);
	    			if (endWord != null && neighbour.equals(endWord)) {
	    				wr.reverse();
	    				return wr;
	    			}
	    			q.put(wr);
	    		}
	    	}
	    }
	    
	    if(endWord != null) {
	    	return null;
	    }
	    
	    wr.reverse();
	    	
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

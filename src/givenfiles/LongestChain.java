package givenfiles;

import java.util.ArrayList;
import java.util.Iterator;


class LongestChain
{
    private Queue<WordRec> q; // kö som används i breddenförstsökningen
    int wordLength;
    char[] wordBuilder;

    final static char [] alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
			       'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
			       's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 
			       'ä', 'ö', 'é' };
    int alphabetLength = alphabet.length;

    public LongestChain(int wordLength){
		this.wordLength = wordLength;
		wordBuilder = new char[wordLength];
		q = new Queue<WordRec>();
    }

	// BreadthFirst utför en breddenförstsökning från startWord för att
    // hitta kortaste vägen till endWord. Den kortaste vägen returneras
    // som en kedja av ordposter (WordRec).
    // Om det inte finns något sätt att komma till endWord returneras null.
    private WordRec BreadthFirst(String startWord, String endWord){
		WordGraph.resetUsed();
		WordRec start = new WordRec(endWord, null);
		WordGraph.markAsUsed(endWord);
		String goalWord = startWord;
		q.empty();
		q.put(start);
		WordRec wr = null;
		
	    while (!q.isEmpty()) {
	    	WordRec x = q.get();
	    	
	    	ArrayList<String> neighbours = WordGraph.getNeighbors(x.word);
	    	
	    	for(String neighbour : neighbours){
	    		if(!WordGraph.isUsed(neighbour)){
	    			WordGraph.markAsUsed(neighbour);
	    			
	    			wr = new WordRec(neighbour, x);
	    			if (goalWord != null && neighbour.equals(goalWord)) {
	    				return wr;
	    			}
	    			q.put(wr);
	    		}
	    	}
	    }
	    
	    if(startWord != null) {
	    	return null;
	    }
	    
	    return wr;
    }
    
    public WordRec shortestPathBetween(String startWord, String endWord) {
    	return BreadthFirst(startWord, endWord);
    }

    // CheckAllStartWords hittar den längsta kortaste vägen från något ord
    // till endWord. Den längsta vägen skrivs ut.
    public WordRec checkAllStartWords(String endWord){
		return BreadthFirst(null, endWord);
    }
}

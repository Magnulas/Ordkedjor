import java.util.ArrayList;
import java.util.LinkedList;

class WordGraph
{
    private LinkedList<Integer>[] neighbourArray;
    
    @SuppressWarnings("unchecked")
	public WordGraph(ArrayList<String> words) {
		neighbourArray = new LinkedList[WordHasher.WORD_COMBINATIONS];
		int[] hashValues = new int[words.size()];
		
		/**
		 * Denna algoritm minskade med 1/4. Måste minska ytterligare om vi 
		 * wanna be the very best,
		 * like no one ever was! 
		 * To catch them is my real test
		 * to train them is my cause!
		 */
		for(int i = 0;i<words.size();i++){
//			String currentWord = ;
			hashValues[i] = WordHasher.indexForWord(words.get(i));			
//			makeNeighbours(currentWord, hashValues[i], i);
			neighbourArray[hashValues[i]] = new LinkedList<Integer>();
		}
		
		for(int i = 0;i<words.size();i++) {
			String currentWord = words.get(i);
			makeNeighbours(currentWord, hashValues[i]);
		}
    }    
    
    private void makeNeighbours(String word, int index){
    	    	
    	LinkedList<Integer> neighbours = neighbourArray[index];
    	char[] wordBuilder = new char[WordHasher.WordLength];
    	wordBuilder = word.toCharArray();
//    	for(int i = 0; i< WordHasher.WordLength;i++){
//    		
//    		wordBuilder[i] = word.charAt(i);
//    	}
    	
    	for (int i = 0; i < WordHasher.WordLength; i++) {
    		for (int c = 0; c < WordHasher.Alphabet.length; c++) {
    			if (WordHasher.Alphabet[c] != word.charAt(i)) {
    				
    				wordBuilder[i] = WordHasher.Alphabet[c];
    				int neighbour = WordHasher.indexForWord(wordBuilder);
    				
    				if(neighbourArray[neighbour]!=null){
	    			    neighbours.add(neighbour);
    				}
    			}
    		}
    		wordBuilder[i] = word.charAt(i);
    	}
    }
    
    private WordRec breadthFirst(String startWord,String endWord){
    	
    	boolean[] used = new boolean[WordHasher.WORD_COMBINATIONS];
    	Queue<HashRec> q = new Queue<HashRec>();
    	
    	int startWordHash = WordHasher.indexForWord(startWord);
    	
		HashRec start = new HashRec(startWordHash, null);

		int endWordHash = -1;
		
		if(endWord!=null){
			endWordHash = WordHasher.indexForWord(endWord);
		}

		q.put(start);
		used[startWordHash] = true;
		
		HashRec wr = null;
		HashRec currentRec = null;
		
	    while (!q.isEmpty()) {
	    	
	    	currentRec = q.get();
	    	
	    	LinkedList<Integer> neighbours = neighbourArray[currentRec.topValue()];
	    	
	    	for(int neighbour : neighbours){
	    		if(used[neighbour] == false){
	    			used[neighbour] = true;
	    		
	    			wr = new HashRec(neighbour, currentRec);
	    			if (endWord != null && neighbour == endWordHash) {
	    				return wr.toWordRec();
	    			}
	    			q.put(wr);
	    		}
	    	}
	    }
	    
	    if(endWord != null) {
	    	return null;
	    }
	    	    	
	    return wr.toWordRec();
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

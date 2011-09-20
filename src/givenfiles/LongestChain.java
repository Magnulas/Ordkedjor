package givenfiles;

import java.util.ArrayList;
import java.util.Iterator;

import util.ChangableString;

class LongestChain
{
    private Queue q; // kö som används i breddenförstsökningen
    private String goalWord; // slutord i breddenförstsökningen
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
		q = new Queue();
    }

    // IsGoal kollar om w är slutordet.
    private boolean IsGoal(String w){
    	return w.equals(goalWord);
    }

    // MakeSons skapar alla ord som skiljer på en bokstav från x.
    // Om det är första gången i sökningen som ordet skapas så läggs det
    // in i kön q.
    // MAGNUS COMMENT: Retunerar null om man inte hittar order man söker en WordRec som är kedjan av ord man gått igenom
    private WordRec MakeSons(WordRec x) {
    	
    	ChangableString changeStr;
    	String res;
    	
		for (int i = 0; i < wordLength; i++) {
		    for (int c = 0; c < alphabetLength; c++) {
				if (alphabet[c] != x.word.charAt(i)) {
					/*
					MAGNUS COMMENT: 
					Alternativ till att skapa flera strings varje gång, skapas bara en string varje gång.
					Körtid förbättrades med ca 500-600 millisekunder på min dator hemma
					Körningen tog totalt 14700 millisekunder efter denna ändring
					Ändingen är i stortsett att vi har en char[] som vi ändrar istället för 
					att skapa massa String object. För att göra detta behövde vi göra ett
					ChangableString object eftersom vi behöver implementera en equals metod som retunerar
					true ifall två char arrayer har samma värden i sig. Equals för en vanlig char[] retunerar ej true
					ifall det är samma värden i arrayerna, bara om det är exakt samma array pekarna pekar på.
					*/
					for(int k = 0;k<wordBuilder.length;k++){
						if(k==i){
							wordBuilder[k] = alphabet[c];
						} else{
							wordBuilder[k] = x.word.charAt(k);
						}
					}

					changeStr = new ChangableString(wordBuilder);
					ChangableString changeStr2 = WordList.Contains(changeStr);
					
					if(changeStr2 != null){
						 res = changeStr2.toString();
						 if (WordList.MarkAsUsedIfUnused(res)) {
								WordRec wr = new WordRec(res, x); //TODO Gör från string till char[]
								if (IsGoal(res)) return wr; //TODO Gör från string till char[]
								q.Put(wr);
						    }
					}  
				}
		    }
		}
		return null;
    }
    
    private WordRec MakeParents(WordRec x) {
    	
    	ArrayList<String> neighbours;
    	
    	neighbours = getExistingNeighbours(x.word);
    	WordRec wr = null;
    	
    	for(String n : neighbours){
    		if(WordList.MarkAsUsedIfUnused(n)){
    			wr = new WordRec(n, x);
    			q.Put(wr);
    		}
    	}

		return x;
    }

    private ArrayList<String> getExistingNeighbours(String word) {
    	
    	ChangableString changeStr;
    	ArrayList<String> ret = new  ArrayList<String>();
    	
    	for (int i = 0; i < wordLength; i++) {
		    for (int c = 0; c < alphabetLength; c++) {
				if (alphabet[c] != word.charAt(i)) {
				
					for(int k = 0;k<wordBuilder.length;k++){
						if(k==i){
							wordBuilder[k] = alphabet[c];
						} else{
							wordBuilder[k] = word.charAt(k);
						}
					}
					
					changeStr = new ChangableString(wordBuilder);
					ChangableString changeStr2 = WordList.Contains(changeStr);
					
					if(changeStr2 != null){
						 ret.add(changeStr2.toString());
					} 
				}
		    }
		}
    	
		return ret;
	}

	// BreadthFirst utför en breddenförstsökning från startWord för att
    // hitta kortaste vägen till endWord. Den kortaste vägen returneras
    // som en kedja av ordposter (WordRec).
    // Om det inte finns något sätt att komma till endWord returneras null.
    public WordRec BreadthFirst(String startWord, String endWord){
		WordList.EraseUsed();
		WordRec start = new WordRec(startWord, null);
		WordList.MarkAsUsedIfUnused(startWord); //TODO Gör från string till char[]
		goalWord = endWord;
		q.Empty();
		q.Put(start);
		try {
		    while (true) {
				WordRec wr = MakeSons((WordRec) q.Get());
				if (wr != null) return wr;
		    }
		} catch (Exception e) {
		    return null;
		}
    }

    // CheckAllStartWords hittar den längsta kortaste vägen från något ord
    // till endWord. Den längsta vägen skrivs ut.
    public void CheckAllStartWords(String endWord){
		int maxChainLength = 0;
		WordRec maxChainRec = null;
//		Iterator<ChangableString> iter = WordList.list.iterator();
//		while(iter.hasNext()){
//		for (int i = 0; i < WordList.size; i++) {
//		    WordRec x = BreadthFirst(WordList.WordAt(i).toString(), endWord); //TODO Gör från string till char[]
//			WordRec x = BreadthFirst(iter.next().toString(), endWord);
		
			WordRec x = MyBreadthFirst(endWord);
			if (x != null) {
			int len = x.ChainLength();
				if (len > maxChainLength) {
				    maxChainLength = len;
				    maxChainRec = x;
				    // x.PrintChain(); // skriv ut den hittills längsta kedjan
				}
		    }
//		}
		System.out.println(endWord + ": " + maxChainLength + " ord");
		if (maxChainRec != null) maxChainRec.PrintChain();
    }

	private WordRec MyBreadthFirst(String endWord) {
		WordList.EraseUsed();
		WordRec start = new WordRec(endWord, null);
		WordList.MarkAsUsedIfUnused(endWord); //TODO Gör från string till char[]
		WordRec wr = null;
		goalWord = endWord;
		q.Empty();
		q.Put(start);
		try {
			while(!q.IsEmpty()){
				wr = MakeParents((WordRec) q.Get());
			}
		} catch (Exception e) {
		    return null;
		}
		
		return wr;
	}
}

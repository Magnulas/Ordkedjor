package givenfiles;
class WordRec
{
    String word;
    WordRec father; // pekare till ordposten som skapat detta ord
    
    private static final String NEXT_ARROW = " -> ";

    public WordRec(String word, WordRec father) {
		this.word = word;
		this.father = father;
    }
        
    // ChainLength returnerar antalet ord i en kedja av ordposter. 
    public int ChainLength(){
		int i = 0;
		for (WordRec x = this; x != null; x = x.father) i++;
		return i;
    }
    
    public String getReversedChainString() {
    	StringBuilder builderBob = new StringBuilder();
    	builderBob.append(word);
    	if(father != null){
    		father.getReversedChainStringHelper(builderBob);
    	}
    	return builderBob.toString();
    }
    
    public void getReversedChainStringHelper(StringBuilder builderBob) {
    	builderBob.append(NEXT_ARROW);
    	builderBob.append(word);

    	if(father != null){
    		father.getReversedChainStringHelper(builderBob);
    	}
    }
}


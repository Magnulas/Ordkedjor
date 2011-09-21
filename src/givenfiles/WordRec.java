package givenfiles;
class WordRec
{
    String word;
    WordRec father; // pekare till ordposten som skapat detta ord
    
    private boolean reversed;
    
    private static final String NEXT_ARROW = " -> ";

    public WordRec(String word, WordRec father) {
		this(word, father, false);
    }
    
    public WordRec(String word, WordRec father, boolean reversed) {
		this.word = word;
		this.father = father;
		this.reversed = reversed;
		if(father!=null){
			this.reversed = father.reversed;
		}
    }
        
    // ChainLength returnerar antalet ord i en kedja av ordposter. 
    public int ChainLength(){
		int i = 0;
		for (WordRec x = this; x != null; x = x.father) i++;
		return i;
    }
    
    private String getReversedChainString() {
    	StringBuilder builderBob = new StringBuilder();
    	builderBob.append(word);
    	if(father != null){
    		father.getReversedChainStringHelper(builderBob);
    	}
    	return builderBob.toString();
    }
    
    private void getReversedChainStringHelper(StringBuilder builderBob) {
    	builderBob.append(NEXT_ARROW);
    	builderBob.append(word);

    	if(father != null){
    		father.getReversedChainStringHelper(builderBob);
    	}
    }
    
    private String getChainString() {
    	StringBuilder builderBob = new StringBuilder();
    	if(father != null){
    		father.getChainStringHelper(builderBob);
    	}
    	builderBob.append(word);
    	return builderBob.toString();
    }
    
    private void getChainStringHelper(StringBuilder builderBob) {
    	if(father != null){
    		father.getChainStringHelper(builderBob);
    	}
    	builderBob.append(word);
    	builderBob.append(NEXT_ARROW);
    }
    
    @Override
    public String toString(){
    	
    	if(reversed){
    		return getReversedChainString();
    	} else{
    		return getChainString();
    	}
    }
}


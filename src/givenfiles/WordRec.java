package givenfiles;
class WordRec
{
    private Word word;    
    private boolean reversed;
    
    private static final String NEXT_ARROW = " -> ";

    public WordRec(String word, WordRec father) {
		this(word, father, false);
    }
    
    public WordRec(String word, WordRec father, boolean reversed) {
    	
    	if(father!=null){
    		this.word = new Word(word,father.word);
    	} else{
    		this.word = new Word(word,null);
    	}
		
		this.reversed = reversed;
    }

    private class Word{
    	
    	String word;
    	Word father;
    	
    	public Word(String word, Word father) {
    		this.word = word;
    		this.father = father;
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
    }
    
    // ChainLength returnerar antalet ord i en kedja av ordposter. 
    public int ChainLength(){
		int i = 0;
		for (Word x = word; x != null; x = x.father) i++;
		return i;
    }
    
    public String getWord(){
    	
    	return word.word;
    }
    
    @Override
    public String toString(){
    	
    	if(reversed){
    		return word.getReversedChainString();
    	} else{
    		return word.getChainString();
    	}
    }
}


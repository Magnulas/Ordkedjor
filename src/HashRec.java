
public class HashRec {

	private IntegerNode node;
	
	public HashRec(int hashValue, HashRec father) {
    	
    	if(father!=null){
    		this.node = new IntegerNode(hashValue,father.node);
    	} else{
    		this.node = new IntegerNode(hashValue,null);
    	}

    }
	
	private class IntegerNode{
		
		private int hashValue;
		private IntegerNode father;
		
		public IntegerNode(int hashValue, IntegerNode father){
			this.hashValue = hashValue;
			this.father = father;
		}
		
		private WordRec toWordRec(){
			
			WordRec wr = new WordRec(WordHasher.wordForIndexString(hashValue), null);
			
			if(father != null){
				return father.toWordRecHelper(wr);
			} else{
				return wr;
			}
			
		}
		
		private WordRec toWordRecHelper(WordRec oldWr){
			
			WordRec wr = new WordRec(WordHasher.wordForIndexString(hashValue), oldWr);
			
			if(father!=null){
				return father.toWordRecHelper(wr);
			} else{
				return wr;
			}
		}
	}

	public int topValue() {
		return node.hashValue;
	}
	
	public WordRec toWordRec(){
		
		return node.toWordRec();
	}
}

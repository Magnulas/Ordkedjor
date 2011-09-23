
public class WordHasher {
	
	
	public final static char [] Alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
		       'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
		       's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 
		       'ä', 'ö', 'é' };

    public final static int WordLength = 4;
    
    public static int indexForWord(String word) {
    	int index = 0;
        for(int i = 0; i < WordLength; i++) {
        	index += (int)Math.pow(Alphabet.length, i) * letterIndexForChar(word.charAt(i));
        }
        return index;
	}
    
    public static int indexForWord(char[] word) {
        int index = 0;
        for(int i = 0; i < WordLength; i++) {
        	index += (int)Math.pow(Alphabet.length, i) * letterIndexForChar(word[i]);
        }
        return index;
	}
    
    public static char[] wordForIndex(int index) {
    	char[] word = new char[WordLength];
    	for(int i = WordLength - 1; i >= 0; i--){
    		int rest = index % (int)Math.pow(Alphabet.length, i);
    		int tmp = index - rest;
    		tmp = tmp/(int)Math.pow(Alphabet.length, i);
    		word[i] = charForLetterIndex(tmp);
    		
    		index = rest;
    	}
    	return word;
    }
    
    private static int letterIndexForChar(char c) {
    	if(c>=97&&c<=122) return c - 97;
    	if(c == 'å') return 26;
    	if(c == 'ä') return 27;
    	if(c == 'ö') return 28;
    	if(c == 'é') return 29;
    	return -1;
    }
    
    private static char charForLetterIndex(int i) {
    	if(i<26) return (char) (i + 97);
    	if(i == 26) return 'å';
    	if(i == 27) return 'ä';
    	if(i == 28) return 'ö';
    	if(i == 29) return 'é';
    	return ' ';
    }
}

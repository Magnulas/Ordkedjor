
public class WordHasher {
	
	public final static char [] Alphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
		       'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 
		       's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'å', 
		       'ä', 'ö', 'é' };

    public final static int WordLength = 4;
    public final static int WORD_COMBINATIONS = 810000; //alphabet.length^WordLength
    private final static int pow3 = 27000;
    private final static int pow2 = 900;
    private final static int pow1 = 30;
    
    private final static int[] power = {1,pow1,pow2,pow3};
            
    public static int indexForWord(String word) {
//    	int index = 0;
//        for(int i = 0; i < WordLength; i++) {
//        	index += power[i] * letterIndexForChar(word.charAt(i));
//        }
    	int index = letterIndexForChar(word.charAt(0));
    	index += pow1*letterIndexForChar(word.charAt(1));
    	index += pow2*letterIndexForChar(word.charAt(2));
    	index += pow3*letterIndexForChar(word.charAt(3));
    	
        return index;
	}
    
    public static int indexForWord(char[] word) {
//        int index = 0;
//        for(int i = 0; i < WordLength; i++) {
//        	index += power[i] * letterIndexForChar(word[i]);
//        }
    	int index = letterIndexForChar(word[0]);
    	index += pow1*letterIndexForChar(word[1]);
    	index += pow2*letterIndexForChar(word[2]);;
    	index += pow3*letterIndexForChar(word[3]);
        return index;
	}
    
    public static char[] wordForIndex(int index) {
    	char[] word = new char[WordLength];
    	for(int i = WordLength - 1; i >= 0; i--){
    		int rest = index % power[i];
    		int tmp = index - rest;
    		tmp = tmp/power[i];
    		word[i] = charForLetterIndex(tmp);
    		
    		index = rest;
    	}
    	return word;
    }
    
    public static String wordForIndexString(int index) {
    	return new String(wordForIndex(index));
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
    	return '-';
    }
}

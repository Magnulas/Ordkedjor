package util;

/**
 * Essentially a character array but with a different
 * equals method of a normal char[]. 
 * 
 * @author Magnus
 *
 */
public class ChangableString implements Comparable<Object>{

	char[] characters;
	
	public ChangableString(int size){
		characters = new char[size];
	}
	
	public ChangableString(char[] characters){
		this.characters = characters;
	}
	
	public ChangableString(String str){
		this.characters = new char[str.length()];
		
		for(int i = 0;i<characters.length;i++){
			characters[i] = str.charAt(i);
		}
	}
	
	@Override
	public boolean equals(Object o){
		
		if(o instanceof ChangableString){
			return equals((ChangableString)o);
		} else{
			return false;
		}
	}
	
	private boolean equals(ChangableString str){
		
		char[] ch = str.toCharArray();
		
		if(characters.length!=ch.length){
			return false;
		}
		
		int length = Math.min(characters.length, ch.length);
		
		for(int i = 0;i<length;i++){
			if(characters[i]!=ch[i]){
				return false;
			}
		}
		
		return true;
	}

	public char charAt(int i) {
		return characters[i];
	}
	
	public void setChar(int i, char c) {
		characters[i] = c;
	}

	public char[] toCharArray() {
		return characters;
	}
	
	@Override
	public String toString(){
		
		return new String(characters);
	}
	
	public int compareTo(ChangableString str) {

		char[] ch = str.toCharArray();
				
		int length = Math.min(characters.length, ch.length);
		
		
		//TODO make it not depending on char value but on alphabetic
		for(int i = 0;i<length;i++){
			if(characters[i]>ch[i]){
				return -1;
			} else{
				if(characters[i]<ch[i]){
					return 1;
				}
			}
		}
		
		return 0;
	}
	
	@Override
	public int compareTo(Object o) {

		if(o instanceof ChangableString){
			return compareTo((ChangableString)o);
		} else{
			throw new ClassCastException();
		}
	}
	
//	public void setChars(char[] chars, int off, int length) {
//		while(off<characters.length||length==0){
//			
//			off++;
//			length--;
//		}
//	}
}

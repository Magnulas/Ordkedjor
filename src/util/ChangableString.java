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
		}
		if(o instanceof String){
			return equals((String)o);
		}
		if(o instanceof char[]){
			return equals((char[])o);
		}
		
		return false;
	}
	
	private boolean equals(ChangableString str){
		
		return equals(str.toCharArray());
	}
	
	private boolean equals(String str){
		
		return equals(str.toCharArray());
	}
	
	private boolean equals(char[] str){
				
		if(characters.length!=str.length){
			return false;
		}
		
		int length = Math.min(characters.length, str.length);
		
		for(int i = 0;i<length;i++){
			if(characters[i]!=str[i]){
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
	
	private int compareTo(char[] str) {
				
		int length = Math.min(characters.length, str.length);
		
		//TODO make it not depending on char value but on alphabetic
		for(int i = 0;i<length;i++){
			if(characters[i]>str[i]){
				return -1;
			} else{
				if(characters[i]<str[i]){
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
		}
		if(o instanceof String){
			return compareTo((String)o);
		}
		if(o instanceof char[]){
			return compareTo((char[])o);
		}
		
		throw new ClassCastException();
		
	}
	
	private int compareTo(ChangableString str) {
		
		return compareTo(str.toCharArray());
	}
	
	private int compareTo(String str) {
		
		return compareTo(str.toCharArray());
	}
	
}

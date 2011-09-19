package givenfiles;
import java.util.TreeSet;
import java.util.Vector;
import java.io.*;

import util.ChangableString;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordList
{
    static TreeSet<ChangableString> list; // ordlista
    static private TreeSet<String> used; // databas med använda ord
    static int wordLength;
    static int size; // antal ord i ordlistan

    // Read läser in en ordlista från strömmen input. Alla ord ska ha
    // wordLength bokstäver.
    static public void Read(int wordLength_, BufferedReader input)throws IOException {
		wordLength = wordLength_;
		size = 0;
		list = new TreeSet<ChangableString>();
		while (true) {
		    String s = input.readLine();
		    if (s.equals("#")) break;
		    if (s.length() != wordLength)
			System.out.println("Rad " + size + 
					   " i filen innehåller inte " + 
					   wordLength + " tecken.");
		    list.add(new ChangableString(s));
		    size++;
		}
		used = new TreeSet<String>();
    }

    // WordAt returnerar ordet med angivet index i ordlistan.
//    static public ChangableString WordAt(int index){
//		if (index >= 0 && index < size) return list.elementAt(index);
//		else return null;
//    }

    // Contains slår upp w i ordlistan och returnerar ordet om det finns med.
    // Annars returneras null.
    static public ChangableString Contains(ChangableString w){
    	if (list.contains(w)) return w; else return null;
    }

    // MarkAsUsedIfUnused kollar om w är använt tidigare och returneras i så
    // fall false. Annars markeras w som använt och true returneras.
    static public boolean MarkAsUsedIfUnused(String w){
		if (used.contains(w)) return false;
		used.add(w);
		return true;
    }

    // EraseUsed gör så att inga ord anses använda längre.
    static public void EraseUsed(){
    	used.clear();
    }
}

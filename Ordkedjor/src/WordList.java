import java.util.Vector;
import java.io.*;

// Klassen WordList innehåller en ordlista och en datastruktur som håller
// reda på använda ord.

class WordList
{
    static private Vector<String> list; // ordlista
    static private Vector<String> used; // databas med använda ord
    static int wordLength;
    static int size; // antal ord i ordlistan

    // Read läser in en ordlista från strömmen input. Alla ord ska ha
    // wordLength bokstäver.
    static public void Read(int wordLength_, BufferedReader input)
	throws IOException
    {
	wordLength = wordLength_;
	size = 0;
	list = new Vector<String>();
	while (true) {
	    String s = input.readLine();
	    if (s.equals("#")) break;
	    if (s.length() != wordLength)
		System.out.println("Rad " + size + 
				   " i filen innehåller inte " + 
				   wordLength + " tecken.");
	    list.add(s);
	    size++;
	}
	used = new Vector<String>(size);
    }

    // WordAt returnerar ordet med angivet index i ordlistan.
    static public String WordAt(int index)
    {
	if (index >= 0 && index < size) return (String) list.elementAt(index);
	else return null;
    }

    // Contains slår upp w i ordlistan och returnerar ordet om det finns med.
    // Annars returneras null.
    static public String Contains(String w)
    {
	if (list.contains(w)) return w; else return null;
    }

    // MarkAsUsedIfUnused kollar om w är använt tidigare och returneras i så
    // fall false. Annars markeras w som använt och true returneras.
    static public boolean MarkAsUsedIfUnused(String w)
    {
	if (used.contains(w)) return false;
	used.add(w);
	return true;
    }

    // EraseUsed gör så att inga ord anses använda längre.
    static public void EraseUsed()
    {
	used.clear();
    }

}

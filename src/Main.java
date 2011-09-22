import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main
{
    final static private int WordLength = 4;
    
    public static void main (String args[]) throws IOException {
    	
    	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
    	WordGraph wordGraph = new WordGraph(readDictionaryWords(stdin));
		
		while (true) {
		    String line = stdin.readLine();
		    if (line == null) break;
		    String tokens[] = line.split(" ");
		    
		    if (tokens.length == 1) {
		    	WordRec wr = wordGraph.longestOfTheShortestPathsTo(tokens[0]);
		    	
		    	if(wr!=null){
				    System.out.println(tokens[0] + ": " + wr.ChainLength() + " ord");
				    System.out.println(wr);
		    	} else{
		    		System.out.println(tokens[0] + ": 0 ord");
		    	}
		    }
		    else if (tokens.length == 2) {
				WordRec wr = wordGraph.shortestPathBetween(tokens[0], tokens[1]);
				if (wr == null) {
				    System.out.println(tokens[0] + " " + tokens[1] + ": ingen lösning");
				} else {
				    System.out.println(tokens[0] + " " + tokens[1] + ": "+ wr.ChainLength() + " ord");
				    System.out.println(wr);
				}
		    }
		    else{
				System.out.println("felaktig fråga: '" + line + "'");
				System.out.println("syntax för frågor: slutord");
				System.out.println("eller:             startord slutord");
		    }
	
		}
    }
    
    private static ArrayList<String> readDictionaryWords(BufferedReader reader) throws IOException{
		ArrayList<String> words = new ArrayList<String>();
		int size = 0;
		
		while (true) {
		    String word = reader.readLine();
		    size++;
		    if (word.equals("#")) break;
		    if (word.length() != WordLength) {
		    	System.out.println("Rad " + size + " i filen innehåller inte " + WordLength + " tecken.");
		    }
		    words.add(word);
		}
		return words;
    }
}

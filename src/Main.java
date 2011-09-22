import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main
{
    final static private int WordLength = 4;
    final static private boolean UseTimer = false;
    
    public static void main (String args[]) throws IOException {
    	Timer timer = null;
    	if(UseTimer) {
    		timer = new Timer();
    		timer.start();
    	}
    	
    	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		
    	if(UseTimer) {
			System.out.println("Time to read graph: " + timer.getElapsedTime() + " ms");
			timer.restart();
		}
    	
    	WordGraph wordGraph = new WordGraph(readDictionaryWords(stdin));
		
		if(UseTimer) {
			System.out.println("Time to build graph: " + timer.getElapsedTime() + " ms");
			timer.restart();
		}
		
		while (true) {
		    String line = stdin.readLine();
		    if (line == null) break;
		    String tokens[] = line.split(" ");
		    
		    if (tokens.length == 1) {
		    	WordRec wr = wordGraph.longestOfTheShortestPathsTo(tokens[0]);
		    	
			    System.out.println(tokens[0] + " " + wr.ChainLength() + " ord");
			    System.out.println(wr);
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
		    if(UseTimer) {
		    	System.out.println("Time to search: " + timer.getElapsedTime() + " ms");
				timer.restart();
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

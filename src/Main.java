import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Main
{    	
    private final static String SYS_WHITESPACE = " ";
    private final static String SYS_COLON = ": ";
    private final static String SYS_WORD =" ord";
    private final static String SYS_COLON_WORD = ": 0 ord";
    private final static String SYS_NOSOLUTION = ": ingen lösning";
    private final static String SYS_BRACKET = "#";
    
    public static void main (String args[]) throws IOException {
    	
    	Timer timer = new Timer();
    	timer.start();
    	BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
		
    	ArrayList<String> data = readDictionaryWords(stdin);
    	    	
    	WordGraph wordGraph = new WordGraph(data);
		String line = null;    	
    	
		while (true) {
		    line = stdin.readLine();
		    if (line == null) break;
		    String tokens[] = line.split(SYS_WHITESPACE);
		    
		    if (tokens.length == 1) {
		    	WordRec wr = wordGraph.longestOfTheShortestPathsTo(tokens[0]);
		    
		    	if(wr!=null){
		    		StringBuilder sb = new StringBuilder(tokens[0]);
		    		sb.append(SYS_COLON);
		    		sb.append(wr.ChainLength());
		    		sb.append(SYS_WORD);
		    		sb.append('\n');
		    		sb.append(wr);;
				    System.out.println(sb);
		    	} else{
		    		System.out.println(tokens[0] + SYS_COLON_WORD);
		    	}
		    }
		    else if (tokens.length == 2) {
				WordRec wr = wordGraph.shortestPathBetween(tokens[0], tokens[1]);
				if (wr == null){
					StringBuilder sb = new StringBuilder(tokens[0]);
		    		sb.append(SYS_WHITESPACE);
		    		sb.append(tokens[1]);
		    		sb.append(SYS_NOSOLUTION);
		    		System.out.println(sb);
				} else{
					StringBuilder sb = new StringBuilder(tokens[0]);
		    		sb.append(SYS_WHITESPACE);
		    		sb.append(tokens[1]);
		    		sb.append(SYS_COLON);
		    		sb.append(wr.ChainLength());
		    		sb.append(SYS_WORD);
		    		sb.append('\n');
		    		sb.append(wr);;
				    System.out.println(sb);
				}
		    }
		    else{
				System.out.println("felaktig fråga: '" + line + "'");
				System.out.println("syntax för frågor: slutord");
				System.out.println("eller:             startord slutord");
		    }
		}
		timer.stop();
		System.out.println("Runtime: " + timer.getElapsedTime() + " ms");
    }
    
    private static ArrayList<String> readDictionaryWords(BufferedReader reader) throws IOException{
		ArrayList<String> words = new ArrayList<String>();
		int size = 0;
		String word = null;
		
		while (true) {
		    word = reader.readLine();
		    size++;
		    if (word.equals(SYS_BRACKET)) break;
		    if (word.length() != WordHasher.WordLength) {
		    	System.out.println("Rad " + size + " i filen innehåller inte " + WordHasher.WordLength + " tecken.");
		    }
		    words.add(word);
		}
		return words;
    }
}

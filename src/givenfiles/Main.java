package givenfiles;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import util.Timer;

public class Main
{
    final static private int WordLength = 4;
    
    public static void main (String args[]) throws IOException {
    	
//    	Timer timer = new Timer();
//    	timer.start();
    	
		BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
		WordGraph.Read(WordLength, stdin);
		LongestChain lc = new LongestChain(WordLength);
		
//		System.out.println("Time to build graph: " + timer.getElapsedTime());
		
//		timer.start();
		while (true) {
		    String line = stdin.readLine();
		    if (line == null) break;
		    String tokens[] = line.split(" ");
		    
		    if (tokens.length == 1) {
		    	WordRec wr = lc.checkAllStartWords(tokens[0]);

			    System.out.println(tokens[0] + " " + wr.ChainLength() + " ord");
			    System.out.println(wr.getReversedChainString());
		    }
		    else if (tokens.length == 2) {
				WordRec wr = lc.shortestPathBetween(tokens[0], tokens[1]);
				if (wr == null) {
				    System.out.println(tokens[0] + " " + tokens[1] + ": ingen lösning");
				} else {
				    System.out.println(tokens[0] + " " + tokens[1] + ": "+ wr.ChainLength() + " ord");
				    System.out.println(wr.getReversedChainString());
				}
				
		    }
		    else{
				System.out.println("felaktig fråga: '" + line + "'");
				System.out.println("syntax för frågor: slutord");
				System.out.println("eller:             startord slutord");
		    }
		    
//		    System.out.println("Time to search in milliseconds: " + timer.getElapsedTime());
//			timer.start();
		}
    }
}

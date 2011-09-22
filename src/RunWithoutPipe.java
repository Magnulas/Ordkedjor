

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/** 
 * This class is meant to be used if you don't have a Unix
 * system with pipes. You pass the file you wish to test
 * as an argument or if there's no argument we run a default
 * test file.
 * 
 * @author Magnus
 *
 */
public class RunWithoutPipe {

	public static void main(String args[]) throws IOException{
		
		String fileToRun;
		
		if(args.length == 0){
			fileToRun = "src/testfall/sample.in";
		} else{
			fileToRun = args[0];
		}
				
		FileInputStream is = new FileInputStream(new File(fileToRun));
		
		System.setIn(is);
		
		Main.main(null);
	}
}


public class WordHasherTest {

	public static void main(String[] args) {
		String word = "åäöé";
		String maybeWord = new String(WordHasher.wordForIndex(WordHasher.indexForWord(word)));
		System.out.println(maybeWord);
	}
}

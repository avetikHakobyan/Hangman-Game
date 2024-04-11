package hangmanProgram;

public class Hangtest {

	public static void main(String[] args) {
		HangmanGame testHangman = new HangmanGame();
		testHangman.addPlayer(new Player("Zachary"));
		testHangman.addPlayer(new Player("Avet"));
		testHangman.addPlayer(new Player("Brandon"));
		testHangman.sort();
	}

}

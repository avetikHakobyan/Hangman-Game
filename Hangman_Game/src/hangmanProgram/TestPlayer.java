package hangmanProgram;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestPlayer {

	@Test
	void testCase1() {
		Player p1 = new Player();
		assertEquals("Unknown", p1.getName(), "Test Case 1: getName()");
	}
	
	@Test
	void testCase2() {
		Player p1 = new Player("Avet");
		assertEquals("Avet", p1.getName(), "Test Case 2: getName()");
	}

	@Test
	void testCase3() {
		Player p1 = new Player();
		assertEquals(0, p1.getNumberGamesPlayed(), "Test Case 3: getNumberGamesPlayed()");
	}

	@Test
	void testCase4() {
		Player p1 = new Player();
		p1.setNumberGamesPlayed(1);
		assertEquals(1, p1.getNumberGamesPlayed(), "Test Case 4: getNumberGamesPlayed()");
	}
	
	@Test
	void testCase5() {
		Player p1 = new Player();
		p1.setNumberGamesWon(3);
		assertEquals(3, p1.getNumberGamesWon(), "Test Case 5: getNumberGamesWon()");
	}

} // TestPlayer class

package hangmanProgram;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestScoreBoard {

	@Test
	void testCase1() {
		ScoreBoard s1 = new ScoreBoard();
		assertEquals(0, s1.getNumPlayers(), "Test Case 1: getNumPlayers()");
	}
	
	@Test
	void testCase2() {
		ScoreBoard s1 = new ScoreBoard();
		s1.addPlayer(new Player());
		assertEquals(1, s1.getNumPlayers(), "Test Case 2: getNumPlayers() 1 player");
	}
	
	@Test
	void testCase3() {
		ScoreBoard s1 = new ScoreBoard();
		s1.addPlayer(new Player("John"));
		s1.addPlayer(new Player("Henry"));
		s1.addPlayer(new Player("Tracy"));
		assertEquals(3, s1.getNumPlayers(), "Test Case 3: getNumPlayers() 1 and more players");
	}
	
	@Test
	void testCase4() {
		ScoreBoard s1 = new ScoreBoard();
		s1.addPlayer(new Player("Avet"));
		assertTrue(s1.isAlreadUsed("Avet"), "Test Case 4: isAlreadUsed() true");
	}
	
	@Test
	void testCase5() {
		ScoreBoard s1 = new ScoreBoard();
		s1.addPlayer(new Player("Avet"));
		assertFalse(s1.isAlreadUsed("avet"), "Test Case 5: isAlreadUsed() false");
	}
	
	@Test
	void testCase6() {
		ScoreBoard s1 = new ScoreBoard();
		Player p1 = new Player("Avet");
		Player p2 = new Player("John");
		s1.addPlayer(p1);
		assertThrows(NullPointerException.class, () -> s1.setPlayer(p2), "Test Case 6: NullPointerException was not thrown");
	}
	
	@Test
	void testCase7() {
		ScoreBoard s1 = new ScoreBoard();
		Player p1 = new Player("Avet");
		s1.addPlayer(p1);
		assertEquals(p1, s1.setPlayer(p1), "Test Case 7: Player(\"Avet\") was not returned");
	}

} // TestScoreBoard class

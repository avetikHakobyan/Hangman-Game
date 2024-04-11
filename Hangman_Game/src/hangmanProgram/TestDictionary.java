package hangmanProgram;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

class TestDictionary {

	@Test
	void testCase1() throws FileNotFoundException {
		Dictionary dr1 = new Dictionary();
		dr1.readFile();
		assertEquals(44, dr1.getWordListLength(), "Test Case 1: 44 words in the list");
	}
	
	@Test
	void testCase2() {
		Dictionary dr1 = new Dictionary();
		dr1.setWordPos(12);
		assertEquals(12, dr1.getWordPos(), "Test Case 2: getWordPos()");
	}
	
	@Test
	void testCase3() {
		Dictionary dr1 = new Dictionary();
		assertTrue(dr1.validateWord("word"), "Test Case 3: valid word");
	}
	
	@Test
	void testCase4() {
		Dictionary dr1 = new Dictionary();
		assertFalse(dr1.validateWord("word1"), "Test Case 4: invalid word 'word1'");
	}
	
	@Test
	void testCase5() {
		Dictionary dr1 = new Dictionary();
		assertFalse(dr1.validateWord("???"), "Test Case 5: invalid word '???'");
	}
	
	@Test
	void testCase6() {
		Dictionary dr1 = new Dictionary();
		assertFalse(dr1.validateWord(":)"), "Test Case 6: invalid word ':)'");
	}
	
	@Test
	void testCase7() {
		Dictionary dr1 = new Dictionary();
		dr1.setGuessedLetters();
		dr1.addGuessedLetters("a");
		dr1.addGuessedLetters("e");
		dr1.addGuessedLetters("i");
		assertEquals(3, dr1.getGuessedLettersLength(), "Test Case 7: addGuessedLetters()");
	}
	
	@Test
	void testCase8() {
		Dictionary dr1 = new Dictionary();
		assertThrows(NullPointerException.class, () -> dr1.getGuessedLettersLength(), "Test Case 8: NullPointerException was not thrown");
	}
	
	@Test
	void testCase9() {
		Dictionary dr1 = new Dictionary();
		dr1.setWrongGuessedLetters();
		dr1.addWrongGuessedLetter("a");
		dr1.addWrongGuessedLetter("e");
		dr1.addWrongGuessedLetter("i");
		assertEquals(3, dr1.getWrongGuessedLettersLength(), "Test Case 9: addWrongGuessedLetter()");
	}
	
	@Test
	void testCase10() {
		Dictionary dr1 = new Dictionary();
		int random = dr1.generateRandom(3);
		assertTrue(random <= 3 && random >= 0, "Test Case 10: generateRandom(3)");
	}
	
	@Test
	void testCase11() throws FileNotFoundException {
		Dictionary dr1 = new Dictionary();
		dr1.readFile();
		dr1.setWordToGuess();
		assertTrue(dr1.getWordToGuessLength() == dr1.getWordToGuess().getLength(), "Test Case 11: setWordToGuess()");
	}

} // TestDictionary class

/**
 * Description: This class handles all the logical operations of the hangman game
 * @author Avetik Hakobyan
 * Course number: 420-G30
 * Assignment number: A02
 * Last Modification: November 9, 2022, 11:45:43 AM
 */
package hangmanProgram;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class HangmanGame implements Serializable {
	private Dictionary dictionaryObj = new Dictionary();
	private ScoreBoard scoreBoardObj = new ScoreBoard();
	private Player player;
	private final String INPUT_REGEX = "^[a-zA-Z]$";
	private int hintLimit;
	private String fileName = "players.txt";
	private int wrongCount;
	private int correctCount;

	public void resetBoard() {
		setWrongCount(0);
		setCorrectCount(0);
		setHintLimit(0);
		getDictionary().setGuessedLetters();
		getDictionary().setWrongGuessedLetters();
	} // createGame()

	public Dictionary getDictionary() {
		return dictionaryObj;
	} // getDictionary()

	public ScoreBoard getScoreBoard() {
		return scoreBoardObj;
	} // getScoreBoard()

	public void setWrongCount(int x) {
		wrongCount = x;
	} // setCount(int)

	public int getWrongCount() {
		return wrongCount;
	} // getWrongCount()

	public void setCorrectCount(int x) {
		correctCount = x;
	} // setCorrectCount(int)

	public int getCorrectCount() {
		return correctCount;
	} // getCorrectCount()

	public int getHintLimit() {
		return hintLimit;
	} // getHintLimit()

	public void setHintLimit(int x) {
		hintLimit = x;
	} // setHintLimit(int)
	
	public void setPlayer(Player name) {
		player = scoreBoardObj.setPlayer(name);
	} // setPlayerName(Player)
	
	public Player setExistingPlayer(String name) {
		player = scoreBoardObj.setExistingPlayer(name);
		return player;
	} // setExistingPlayer(String)
	
	public Player getPlayer() {
		return player;
	} // getPlayerName()
	
	public void readFile() throws FileNotFoundException {
		dictionaryObj.readFile();
	} // readFile()
	
	public boolean setWordToGuess() {
		if (dictionaryObj.getWordListLength() == 0) {
			return false;
		} else {			
			dictionaryObj.setWordToGuess();
			return true;
		}
	} // setWordToGuess()
	
	public void addPlayer(Player name) {
		scoreBoardObj.addPlayer(name);
	} // addPlayer(String)
	
	public boolean isAlreadyGuessed(String guess) {
		boolean isAlreadyGuessed = false;
		Character inChar = Character.valueOf(guess.charAt(0));
		if (getDictionary().getGuessedLetters().find(inChar) != null) {
			if (getDictionary().getGuessedLetters().find(inChar)
					.getElement().equals(inChar)) {
				isAlreadyGuessed = true;
			}
		}
		return isAlreadyGuessed;
	} // isAlreadyGuessed(String)
	
	public boolean isAlreadyUsed(String name) {
		return scoreBoardObj.isAlreadUsed(name);
	}

	public boolean validateInput(String in) {
		boolean isValid = false;
		if (in.matches(INPUT_REGEX)) {
			isValid = true;
		}
		return isValid;
	} // validateInput(String)

	public String updateGuess(String inStr) {
		getDictionary().addGuessedLetters(inStr);
		checkGuess(inStr);
		return refreshHiddenWord();
	} // checkAnswer(String)

	public String refreshHiddenWord() {
		String newHiddenWord = "";
		for (int i = 0; i < getDictionary().getWordToGuessLength(); i++) {
			if (getDictionary().getGuessedLetters()
					.find(getDictionary().getWordToGuess().getElementAt(i)) != null) {
				newHiddenWord += (getDictionary().getWordToGuess().getElementAt(i));
			}
			else {
				newHiddenWord += " _ ";
			}
		}
		return newHiddenWord;
	}

	public String getWrongGuessedLettersString() {
		String guessedLetters = "";
		if (getDictionary().getWrongGuessedLettersLength() > 0) {
			guessedLetters = getDictionary().getWrongGuessedLetters()
					.getElementAt(getDictionary().getWrongGuessedLettersLength() - 1)
					.toString();

			for (int i = getDictionary().getWrongGuessedLettersLength()
					- 2; i >= 0; i--) {
				guessedLetters += ", "
						+ getDictionary().getWrongGuessedLetters().getElementAt(i);
			}
		}
		return guessedLetters;
	} // getWrongGuessedLettersString()

	public void checkGuess(String inStr) {
		if (getDictionary().getWordToGuess()
				.find(Character.valueOf(inStr.charAt(0))) == null) {
			dictionaryObj.addWrongGuessedLetter(inStr);
			wrongCount++;
		}
		else {
			correctCount++;
		}
	} // checkGuess(String)

	public String getHint() throws Exception {
		String randomLetter = "";
		if (getHintLimit() < 3) {
			randomLetter = getDictionary().getRandomLetter().toString();
			hintLimit++;
		}
		else {
			throw new Exception();
		}
		
		if (getDictionary().getGuessedLettersLength() > 0) {
			for (int i = 0; i < getDictionary().getGuessedLettersLength(); i++) {
				if (getDictionary().getGuessedLetters().getElementAt(i) == Character
						.valueOf(randomLetter.charAt(0))) {
					while (getDictionary().getGuessedLetters()
							.getElementAt(i) == Character.valueOf(randomLetter.charAt(0))) {
						randomLetter = getDictionary().getRandomLetter().toString();
					}
				}
			}
		}
		return updateGuess(randomLetter);
	} // getHint()

	public String getHiddenWord() {
		String str = "";
		for (int i = 0; i < getDictionary().getWordToGuessLength(); i++) {
			if (dictionaryObj.getWordToGuess().getElementAt(i) != null) {
				str += " _ ";
			}
		}
		return str;
	} // getHiddenWord()
	
	public void incrementWins() {
		scoreBoardObj.getPlayerList().find(player).getElement().incrementWins();
	} // incrementWins
	
	public void incrementGames() {
		scoreBoardObj.getPlayerList().find(player).getElement().incrementGames();
	} // incrementGames()
	
	public void sort() {
		scoreBoardObj.sort();
	}
} // HangmanGame class

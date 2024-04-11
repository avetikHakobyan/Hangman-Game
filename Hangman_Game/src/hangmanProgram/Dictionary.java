/**
 * Description: This class handles all operations with a dictionary, Singly linked lists of the word list, word to guess, guessed letters and wrong guessed letters
 * @author Avetik Hakobyan
 * Course number: 420-G30
 * Assignment number: A02
 * Last Modification: November 7, 2022, 8:30:44 PM
 */
package hangmanProgram;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
import linked_data_structures.SLNode;
import linked_data_structures.SinglyLinkedList;

public class Dictionary implements Serializable {

	private SinglyLinkedList<String> wordList = new SinglyLinkedList<String>();
	private SinglyLinkedList<Character> wordToGuess;
	private SinglyLinkedList<Character> guessedLetters;
	private SinglyLinkedList<Character> wrongGuessedLetters;
	private String wordToGuessString = "";
	private final String WORD_REGEX = "^[a-zA-Z\s]+$";
	private int wordPos;

	public int getWordListLength() {
		return wordList.getLength();
	} // getWordListLength()

	public int getWordToGuessLength() {
		return wordToGuess.getLength();
	} // getWordToGuessLength()

	public int getGuessedLettersLength() {
		return guessedLetters.getLength();
	} // getGuessedLettersLength()

	public int getWrongGuessedLettersLength() {
		return wrongGuessedLetters.getLength();
	} // getWrongGuessedLength()

	public SinglyLinkedList<Character> getWrongGuessedLetters() {
		return wrongGuessedLetters;
	} // getWrongGuessedLetters()

	public SinglyLinkedList<Character> getWordToGuess() {
		return wordToGuess;
	} // getWordToGuess()

	public String getWordToGuessString() {
		return wordToGuessString;
	} // getWordToGuessString()

	public SinglyLinkedList<Character> getGuessedLetters() {
		return guessedLetters;
	} // getGuessedLetters()
	
	public void setGuessedLetters() {
		guessedLetters = new SinglyLinkedList<Character>();
	}
	
	public void setWrongGuessedLetters() {
		wrongGuessedLetters = new SinglyLinkedList<Character>();
	}
	
	public void setWordPos(int p) {
		wordPos = p;
	} // setWordPos(int)

	public int getWordPos() {
		return wordPos;
	} // getWordPos()

	public int generateRandom(int x) {
		return (int) Math.floor((Math.random() * x));
	} // generateRandom()

	public boolean validateWord(String in) {
		boolean isValid = false;

		if (in.matches(WORD_REGEX)) {
			isValid = true;
		}

		return isValid;
	} // validateWord(String)

	public SLNode<String> getRandomWord() {
		int randomNum = generateRandom(wordList.getLength());
		setWordPos(randomNum);
		return wordList.find(randomNum);
	} // getRandoomWord()

	public Character getRandomLetter() {
		Character randomChar = wordToGuess
				.find(generateRandom(getWordToGuessLength())).getElement();
		return randomChar;
	} // getRandomLetter()

	public void readFile() throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("word_db.txt"));
		String nextWord = "";

		while (scanner.hasNext()) {
			nextWord = scanner.nextLine();
			if (!validateWord(nextWord)) {
				System.out.println("Skipped " + nextWord);
			}
			else {
				wordList.add(nextWord);
			}
		}
	} // readFile()

	public void setWordToGuess() {
		wordToGuess = new SinglyLinkedList<Character>();
		wordToGuessString = getRandomWord().getElement();
		char[] arrWord = wordToGuessString.toCharArray();

		removeWord();

		for (int i = arrWord.length - 1; i >= 0; i--) {
			wordToGuess.add(arrWord[i]);
		}
	} // setWordToGuess()

	public void addGuessedLetters(String in) {
		guessedLetters.add(in.charAt(0));
	} // addGuessedLetters(String)

	public void addWrongGuessedLetter(String in) {
		wrongGuessedLetters.add(in.charAt(0));
	} // addWrongGuessedLetter(String)

	public void removeWord() {
		wordList.remove(getWordPos());
	} // removeWord()

	//	public void displayWordList() {
	//		for (int i = getWordListLength() - 1; i > 0 ; i--) {
	//			System.out.println(wordList.getElementAt(i));
	//		}
	//	}
	//	
	//	public void displayCharacterList() {
	//		for (int i = wordToGuess.getLength() - 1; i > 0; i--) {
	//			System.out.print(wordToGuess.getElementAt(i));
	//		}
	//		System.out.println();
	//	}

} // Dictionary class
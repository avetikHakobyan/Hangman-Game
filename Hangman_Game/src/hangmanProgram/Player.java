/**
 * Description: This class contains all information for a player
 * @author Avetik Hakobyan
 * Course number: 420-G30
 * Assignment number: A02
 * Last Modification: November 3, 2022, 8:23:38 PM
 */
package hangmanProgram;

import java.io.Serializable;

public class Player implements Serializable {

	private String name;
	private int numberGamesPlayed;
	private int numberGamesWon;

	public Player() {
		setName("Unknown");
		setNumberGamesPlayed(0);
		setNumberGamesWon(0);
	} // Default

	public Player(String n) {
		setName(n);
		setNumberGamesPlayed(0);
		setNumberGamesWon(0);
	} // Name

	public void setName(String n) {
		name = n;
	} // setName(String)

	public String getName() {
		return name;
	} // getName()

	public void setNumberGamesPlayed(int i) {
		numberGamesPlayed = i;
	} // setNumberGamesPlayed(int)

	public int getNumberGamesPlayed() {
		return numberGamesPlayed;
	} // getNumberGamesPlayed()

	public void setNumberGamesWon(int i) {
		numberGamesWon = i;
	} // setNumberGamesWon(int)

	public int getNumberGamesWon() {
		return numberGamesWon;
	} // getNumberGamesWon()

	public void incrementWins() {
		numberGamesWon++;
	} // incrementWin()
	
	public void incrementGames() {
		numberGamesPlayed++;
	} // incrementWin()

} // Player class
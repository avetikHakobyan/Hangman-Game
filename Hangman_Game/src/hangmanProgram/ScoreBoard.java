/**
 * Description: This class contains all information for the scoreboard of the hangman game
 * @author Avetik Hakobyan
 * Course number: 420-G30
 * Assignment number: A02
 * Last Modification: November 9, 2022, 11:06:03 PM
 */
package hangmanProgram;

import java.io.Serializable;
import linked_data_structures.DoublyLinkedList;

public class ScoreBoard implements Serializable {

	private DoublyLinkedList<Player> playerList;
	private int numPlayers;

	public ScoreBoard() {
		playerList = new DoublyLinkedList<Player>();
		numPlayers = 0;
	} // Default

	public void setNumPlayers() {
		numPlayers = playerList.getLength();
	} // setNumPlayers(int)

	public int getNumPlayers() {
		return numPlayers;
	} // getNumPlayers()

	public DoublyLinkedList<Player> getPlayerList() {
		return playerList;
	} // getPlayerList()

	public void addPlayer(Player player) {
		playerList.add(player);
		numPlayers++;
	} // addPlayer(String)

	public Player getNextPlayer(int i) {
		return playerList.getElementAt(i);
	} // getNextPlayer(int)

	public void displayScoreBoard() {
		for (int i = playerList.getLength() - 1; i >= 0; i--) {
			System.out.println(playerList.getElementAt(i).getName() + " Played: "
					+ playerList.getElementAt(i).getNumberGamesPlayed() + " Won: "
					+ playerList.getElementAt(i).getNumberGamesWon());
		}
	} // displayScoreBoard()

	public boolean isAlreadUsed(String name) {
		boolean isAlreadUsed = false;
		for (int i = playerList.getLength() - 1; i >= 0; i--) {
			if (playerList.getElementAt(i).getName().equals(name)) {
				isAlreadUsed = true;
			}
		}
		return isAlreadUsed;
	} // isAlreadUsed(String)

	public Player setPlayer(Player name) {
		return playerList.find(name).getElement();
	} // setPlayer(Player)

	public Player setExistingPlayer(String name) {
		Player player = null;
		for (int i = playerList.getLength() - 1; i >= 0; i--) {
			if (playerList.getElementAt(i).getName().equals(name)) {
				player = playerList.getElementAt(i);
			}
		}
		return player;
	} // setPlayer(Player)

	private void swap(int first, int second) {
		Player temp = playerList.getElementAt(first);
		playerList.remove(first);
		playerList.add(temp, second);
	} // swapPlayers(int, int)
	
	
	public void sort() {
		for (int i = 0; i < playerList.getLength() - 1; i++) {
			if (playerList.getElementAt(i).getName()
					.compareTo(playerList.getElementAt(i + 1).getName()) > 0) {
				swap(i, i + 1);
			}
		}
	} // sort()

} // ScoreBoard class
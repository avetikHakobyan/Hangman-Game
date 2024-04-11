/**
 * Description: This class handles the user interface of the hangman game
 * @author Avetik Hakobyan
 * Course number: 420-G30
 * Assignment number: A02
 * Last Modification: November 9, 2022, 10:31:28 PM
 */
package hangmanProgram;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class HangmanFrame extends JFrame
		implements ActionListener, WindowListener, MouseListener, Serializable {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnFileMenu;
	private JMenu mnhelpMenu;
	private JPanel infoPane;
	private JPanel hangmanPane;
	private JLabel lblWrongGuesses;
	private JTextField fldGuess;
	private JButton btnGuess;
	private JLabel wrongGuesses;
	private JButton btnHint;
	private JLabel gallows;
	private JPanel guessedLettersPanel;
	private JPanel inOutPanel;
	private JPanel outputPane;
	private JPanel inPane;
	private JPanel hangmanImages;
	private JLabel head;
	private JLabel body;
	private JLabel leftArm;
	private JLabel leftLeg;
	private JLabel rightLeg;
	private JLabel rightArm;
	private HangmanGame hangmanGame;
	private JLabel lblGuessLetter;
	private JPanel wordDisplay;
	private JLabel lblWordToGuess;
	private JLabel word;
	private JPanel playerInfoPane;
	private JLabel lblPlayerName;
	private JPanel namePanel;
	private JLabel lblFldName;
	private JLabel lblCmbName;
	private JTextField fldName;
	private JComboBox<String> cmbName;
	private JLabel playerName;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmHint;
	private JMenuItem mntmRules;
	private JMenuItem mntmScoreBoard;
	private JMenuItem mntmQuit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangmanFrame frame = new HangmanFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // main

	/**
	 * Create the frame.
	 */
	public HangmanFrame() {
		setForeground(new Color(255, 255, 255));
		setTitle("Hangman Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 515);
		menuBar = new JMenuBar();
		menuBar.setForeground(new Color(0, 0, 0));
		mnFileMenu = new JMenu("File");
		mnFileMenu.setForeground(new Color(0, 0, 0));
		mnFileMenu.setBackground(new Color(240, 240, 240));
		mnhelpMenu = new JMenu("Help");
		mnhelpMenu.setForeground(new Color(0, 0, 0));
		mnhelpMenu.setBackground(new Color(240, 240, 240));
		menuBar.add(mnFileMenu);

		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.setHorizontalAlignment(SwingConstants.LEFT);
		mnFileMenu.add(mntmNewGame);

		mntmScoreBoard = new JMenuItem("Scoreboard");
		mnFileMenu.add(mntmScoreBoard);

		mntmQuit = new JMenuItem("Quit");
		mnFileMenu.add(mntmQuit);
		menuBar.add(mnhelpMenu);

		mntmHint = new JMenuItem("Hint");
		mnhelpMenu.add(mntmHint);

		mntmRules = new JMenuItem("Learn about rules");
		mnhelpMenu.add(mntmRules);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(24, 26, 27));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setJMenuBar(menuBar);
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2, 0, 0));

		hangmanPane = new JPanel();
		hangmanPane.setBackground(new Color(255, 255, 255));
		contentPane.add(hangmanPane);
		hangmanPane.setLayout(new GridLayout(2, 1, 0, 0));

		hangmanImages = new JPanel();
		hangmanImages.setBorder(new LineBorder(new Color(255, 255, 255)));
		hangmanImages.setBackground(new Color(24, 26, 27));
		hangmanPane.add(hangmanImages);
		hangmanImages.setLayout(null);

		gallows = new JLabel("");
		gallows.setForeground(new Color(0, 0, 0));
		gallows.setBounds(97, 5, 200, 200);
		hangmanImages.add(gallows);
		gallows.setIcon(new ImageIcon("gallows.png"));
		gallows.setHorizontalAlignment(SwingConstants.CENTER);

		head = new JLabel("");
		head.setVisible(false);
		head.setBounds(97, 5, 200, 200);
		head.setIcon(new ImageIcon("head.png"));
		hangmanImages.add(head);

		body = new JLabel("");
		body.setVisible(false);
		body.setHorizontalAlignment(SwingConstants.CENTER);
		body.setIcon(new ImageIcon("body.png"));
		body.setBounds(97, 5, 200, 200);
		hangmanImages.add(body);

		leftArm = new JLabel("");
		leftArm.setVisible(false);
		leftArm.setIcon(new ImageIcon("leftArm.png"));
		leftArm.setHorizontalAlignment(SwingConstants.CENTER);
		leftArm.setBounds(97, 5, 200, 200);
		hangmanImages.add(leftArm);

		rightArm = new JLabel("");
		rightArm.setVisible(false);
		rightArm.setHorizontalAlignment(SwingConstants.CENTER);
		rightArm.setIcon(new ImageIcon("rightArm.png"));
		rightArm.setBounds(97, 5, 200, 200);
		hangmanImages.add(rightArm);

		leftLeg = new JLabel("");
		leftLeg.setVisible(false);
		leftLeg.setIcon(new ImageIcon("leftLeg.png"));
		leftLeg.setHorizontalAlignment(SwingConstants.CENTER);
		leftLeg.setBounds(97, 5, 200, 200);
		hangmanImages.add(leftLeg);

		rightLeg = new JLabel("");
		rightLeg.setVisible(false);
		rightLeg.setIcon(new ImageIcon("rightLeg.png"));
		rightLeg.setHorizontalAlignment(SwingConstants.CENTER);
		rightLeg.setBounds(97, 5, 200, 200);
		hangmanImages.add(rightLeg);

		guessedLettersPanel = new JPanel();
		guessedLettersPanel.setBackground(new Color(24, 26, 27));
		guessedLettersPanel
				.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		hangmanPane.add(guessedLettersPanel);

		lblWrongGuesses = new JLabel("Wrong guesses : ");
		guessedLettersPanel.add(lblWrongGuesses);
		lblWrongGuesses.setForeground(new Color(255, 255, 255));
		lblWrongGuesses.setHorizontalAlignment(SwingConstants.CENTER);
		lblWrongGuesses.setVerticalAlignment(SwingConstants.TOP);
		lblWrongGuesses.setFont(new Font("Tahoma", Font.PLAIN, 14));

		wrongGuesses = new JLabel("");
		wrongGuesses.setForeground(Color.red);
		wrongGuesses.setFont(new Font("Tahoma", Font.BOLD, 20));
		guessedLettersPanel.add(wrongGuesses);

		infoPane = new JPanel();
		contentPane.add(infoPane);
		infoPane.setLayout(new GridLayout(2, 1, 0, 0));

		outputPane = new JPanel();
		outputPane.setBackground(new Color(24, 26, 27));
		infoPane.add(outputPane);
		outputPane.setLayout(new GridLayout(2, 1, 0, 0));

		playerInfoPane = new JPanel();
		playerInfoPane.setBackground(new Color(24, 26, 27));
		outputPane.add(playerInfoPane);

		lblPlayerName = new JLabel("Player name: ");
		lblPlayerName.setForeground(new Color(255, 255, 255));
		lblPlayerName.setVerticalAlignment(SwingConstants.TOP);
		lblPlayerName.setHorizontalTextPosition(SwingConstants.CENTER);
		lblPlayerName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPlayerName.setFont(new Font("Tahoma", Font.BOLD, 14));
		playerInfoPane.add(lblPlayerName);

		playerName = new JLabel("");
		playerName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPlayerName.setLabelFor(playerName);
		playerName.setHorizontalAlignment(SwingConstants.LEFT);
		playerName.setForeground(new Color(255, 255, 255));
		playerInfoPane.add(playerName);

		wordDisplay = new JPanel();
		wordDisplay.setBackground(new Color(24, 26, 27));
		wordDisplay.setForeground(new Color(255, 255, 255));
		outputPane.add(wordDisplay);
		wordDisplay.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblWordToGuess = new JLabel("Word to guess: ");
		lblWordToGuess.setForeground(new Color(255, 255, 255));
		lblWordToGuess.setVerticalAlignment(SwingConstants.TOP);
		lblWordToGuess.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWordToGuess.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWordToGuess.setBackground(SystemColor.menu);
		lblWordToGuess.setAlignmentX(0.5f);
		wordDisplay.add(lblWordToGuess);

		word = new JLabel("");
		word.setOpaque(true);
		word.setHorizontalAlignment(SwingConstants.LEFT);
		word.setFont(new Font("Tahoma", Font.BOLD, 18));
		word.setBackground(SystemColor.controlHighlight);
		wordDisplay.add(word);

		inOutPanel = new JPanel();
		infoPane.add(inOutPanel);
		inOutPanel.setLayout(new GridLayout(1, 1, 0, 0));

		inPane = new JPanel();
		FlowLayout flowLayout = (FlowLayout) inPane.getLayout();
		flowLayout.setHgap(10);
		inPane.setBackground(new Color(24, 26, 27));
		inOutPanel.add(inPane);

		lblGuessLetter = new JLabel("Guess Letter: ");
		lblGuessLetter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblGuessLetter.setForeground(new Color(255, 255, 255));
		inPane.add(lblGuessLetter);

		fldGuess = new JTextField();
		inPane.add(fldGuess);
		fldGuess.setColumns(2);

		btnGuess = new JButton("Guess");
		inPane.add(btnGuess);

		btnHint = new JButton("Hint");
		inPane.add(btnHint);

		namePanel = new JPanel(new GridLayout(2, 3, 6, 10));

		fldName = new JTextField();
		cmbName = new JComboBox<String>();
		cmbName.addItem("-- Select --");

		lblFldName = new JLabel("Name:");
		lblFldName.setLabelFor(fldName);

		lblCmbName = new JLabel("Select from existing");
		lblCmbName.setLabelFor(cmbName);

		namePanel.add(lblFldName);
		namePanel.add(fldName);
		namePanel.add(lblCmbName, BorderLayout.SOUTH);
		namePanel.add(cmbName);

		this.addWindowListener(this);

		fldGuess.addActionListener(this);
		btnGuess.addActionListener(this);
		btnHint.addActionListener(this);
		mntmNewGame.addActionListener(this);
		mntmHint.addActionListener(this);
		mntmQuit.addActionListener(this);
		mntmScoreBoard.addActionListener(this);
		mntmRules.addActionListener(this);

		fldName.addMouseListener(this);
		cmbName.addMouseListener(this);

	} // HangmanFrame constructor

	private void setWordToGuess() {
		if (hangmanGame.setWordToGuess()) {
			hangmanGame.resetBoard();
			word.setText(hangmanGame.getHiddenWord());
		} else {
			JOptionPane.showMessageDialog(this,
					"Game over! No more words left. Thank you for playing!", "End",
					JOptionPane.INFORMATION_MESSAGE);
			serializeGame();
			System.exit(-1);
		}
	} // setWordToGuess()

	private void checkAnswer(String in) {
		String displayedWord = hangmanGame.updateGuess(in);
		word.setText(displayedWord);
		wrongGuesses.setText(hangmanGame.getWrongGuessedLettersString());
		fldGuess.setText("");
		if (displayedWord
				.equals(hangmanGame.getDictionary().getWordToGuessString())) {
			JOptionPane.showMessageDialog(this,
					"Congratulations! You guessed the word!", "Win",
					JOptionPane.INFORMATION_MESSAGE);
			hangmanGame.incrementWins();
			hangmanGame.incrementGames();
			resetBoard();
			setWordToGuess();
		}
	} // checkAnswer(String)

	private void resetBoard() {
		head.setVisible(false);
		body.setVisible(false);
		leftArm.setVisible(false);
		rightArm.setVisible(false);
		leftLeg.setVisible(false);
		rightLeg.setVisible(false);
		wrongGuesses.setText("");
		word.setText("");
	} // resetBoard()

	private void analyzeHangman() {
		switch (hangmanGame.getWrongCount()) {
		case 1:
			head.setVisible(true);
			break;
		case 2:
			head.setVisible(true);
			body.setVisible(true);
			break;
		case 3:
			head.setVisible(true);
			body.setVisible(true);
			leftArm.setVisible(true);
			break;
		case 4:
			head.setVisible(true);
			body.setVisible(true);
			leftArm.setVisible(true);
			rightArm.setVisible(true);
			break;
		case 5:
			head.setVisible(true);
			body.setVisible(true);
			leftArm.setVisible(true);
			rightArm.setVisible(true);
			leftLeg.setVisible(true);
			break;
		case 6:
			head.setVisible(true);
			body.setVisible(true);
			leftArm.setVisible(true);
			rightArm.setVisible(true);
			leftLeg.setVisible(true);
			rightLeg.setVisible(true);
			JOptionPane.showMessageDialog(this,
					"You lost! The word was \""
							+ hangmanGame.getDictionary().getWordToGuessString()
							+ "\". Guess another word",
					"Game over", JOptionPane.ERROR_MESSAGE);
			hangmanGame.incrementGames();
			resetBoard();
			setWordToGuess();
			break;
		}
	} // analyzeHangman()

	private void processGuess(String input) {
		if (hangmanGame.validateInput(input)) {
			if (hangmanGame.isAlreadyGuessed(input)) {
				JOptionPane.showMessageDialog(this,
						"You already guessed \"" + input + "\", guess another letter",
						"Already guessed", JOptionPane.ERROR_MESSAGE);
				fldGuess.setText("");
			}
			else {
				checkAnswer(input);
				analyzeHangman();
			}
		}
		else {
			JOptionPane.showMessageDialog(this, "Please enter a single letter",
					"Invalid character", JOptionPane.ERROR_MESSAGE);
			fldGuess.setText("");
			fldGuess.requestFocus();
		}
	} // processGuess

	private void getHint() {
		try {
			word.setText(hangmanGame.getHint());
			if (!word.getText().contains("_")) {
				JOptionPane.showMessageDialog(this,
						"Congratulations! You guessed the word!", "Win",
						JOptionPane.INFORMATION_MESSAGE);
				hangmanGame.incrementGames();
				hangmanGame.incrementWins();
				resetBoard();
				setWordToGuess();
			}
		}
		catch (Exception e1) {
			JOptionPane.showMessageDialog(this,
					"Sorry you have reached the limit of possible hints!",
					"Limit reached", JOptionPane.ERROR_MESSAGE);
			fldGuess.requestFocus();
		}
	} // getHint()

	private void refreshPlayerNameItems() {
		if (hangmanGame.getScoreBoard().getNumPlayers() > 0) {
			for (int i = hangmanGame.getScoreBoard().getNumPlayers()
					- 1; i >= 0; i--) {
				cmbName.addItem(hangmanGame.getScoreBoard().getPlayerList()
						.getElementAt(i).getName());
			}
		}
	} // refreshPlayerNameItems()

	private void addPlayer() {
		Player player = null;
		String name = null;
		int answer;
		boolean quit = false;
		String validName = "^[a-zA-Z]+$";
		while (player == null && !quit) {

			// Source: https://stackoverflow.com/questions/6555040/multiple-input-in-joptionpane-showinputdialog
			answer = JOptionPane.showConfirmDialog(this, namePanel, "Enter name",
					JOptionPane.OK_CANCEL_OPTION);

			if (answer == 0) {

				if (fldName.isEnabled()) {
					name = fldName.getText();
					if (name.matches(validName)) {
						if (hangmanGame.isAlreadyUsed(name)) {
							JOptionPane.showMessageDialog(this,
									"Entered name already exists, please enter a different name",
									"Duplicate name", JOptionPane.ERROR_MESSAGE);
							fldName.setText("");
							fldName.requestFocus();
						}
						else {
							player = new Player(fldName.getText());
							hangmanGame.addPlayer(player);
							refreshPlayerNameItems();
							hangmanGame.setPlayer(player);
							fldName.setText("");
							playerName.setText(name);
							contentPane.setVisible(true);
						}
					}
					else
						if (name.trim().isEmpty()) {
							JOptionPane.showMessageDialog(this, "Please enter your name",
									"Empty name", JOptionPane.ERROR_MESSAGE);
							fldName.setText("");
						}
						else {
							name = null;
							JOptionPane.showMessageDialog(this,
									"Please enter your name correctly", "Invalid name",
									JOptionPane.ERROR_MESSAGE);
							fldName.setText("");
						}
				}
				else {
					name = cmbName.getSelectedItem().toString();
					if (name == "-- Select --") {
						player = null;
						JOptionPane.showMessageDialog(this, "Please select a player",
								"Invalid choice", JOptionPane.ERROR_MESSAGE);
					} else {
						player = hangmanGame.setExistingPlayer(name);
						playerName.setText(name);
						contentPane.setVisible(true);
					}
				}
			}
			else
				if (answer == 2 || answer == -1) {
					quit = true;
					System.exit(-1);
				}
		}
	} // btnSearchByYearRanking()

	private void serializeGame() {
		try {

			// Saving of object in a file
			FileOutputStream file = new FileOutputStream("game.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);

			// Method for serialization of object
			out.writeObject(hangmanGame);
			out.close();
			file.close();

			System.out.println("Game has been serialized");
		}
		catch (IOException ex) {
			System.out.println("IOException is caught");
		}
	} // serializeScoreBoard()

	public void deserializeGame() throws IOException, ClassNotFoundException {
		// Reading the object from a file
		FileInputStream file = new FileInputStream("game.txt");
		ObjectInputStream in = new ObjectInputStream(file);

		// Method for deserialization of object
		hangmanGame = (HangmanGame) in.readObject();

		in.close();
		file.close();

		System.out.println("Game has been deserialized ");
	}

	private void newGame() {
		contentPane.setVisible(false);
		addPlayer();
		resetBoard();
		setWordToGuess();
	} // newGame()

	private void refreshGame() {
		resetBoard();
		analyzeHangman();
		playerName.setText(hangmanGame.getPlayer().getName());
		word.setText(hangmanGame.refreshHiddenWord());
		wrongGuesses.setText(hangmanGame.getWrongGuessedLettersString());
	} // refreshGame()

	private void readWordList() {
		try {
			hangmanGame.readFile();
		}
		catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(this,
					"Couldn't read the file " + e1.getMessage()
							+ "\nPlease make sure it exists and re-run the program again.",
					"Word list file not found", JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
	} // readWordList()

	private String separateLines(int x) {
		String str = "";
		for (int i = 0; i < x; i++) {
			str += "-";
		}
		return str;
	} // separateLines(int)

	private void displayScoreBoard() {
		hangmanGame.sort();
		JPanel scoreBoardPanel = new JPanel();
		JTextArea text = new JTextArea(30, 65);
		text.setEditable(false);
		text.setBackground(new Color(24, 26, 27));
		text.setFont(new Font("Consolas", Font.BOLD, 14));
		text.setForeground(new Color(255, 255, 255));

		text.append(String.format("%43s %n %n%n", "Hangman scoreboard"));
		text.append(String.format("%-20s%-20s%15s%n%n", "Name", "Number of games",
				"Number of wins"));
		text.append(String.format("%s%n", separateLines(64)));

		for (int i = 0; i <= hangmanGame.getScoreBoard().getPlayerList().getLength()
				- 1; i++) {
			text.append(String.format("%-27s%s%20s%n%s%n",
					hangmanGame.getScoreBoard().getPlayerList().getElementAt(i).getName(),
					Integer.toString(hangmanGame.getScoreBoard().getPlayerList()
							.getElementAt(i).getNumberGamesPlayed()),
					Integer.toString(hangmanGame.getScoreBoard().getPlayerList()
							.getElementAt(i).getNumberGamesWon()),
					separateLines(64)));
		}

		scoreBoardPanel.add(new JScrollPane(text), BorderLayout.CENTER);
		JOptionPane.showMessageDialog(this, scoreBoardPanel, "Scoreboard",
				JOptionPane.PLAIN_MESSAGE);
	} // displayScoreBoard()

	private void displayRules() {
		JPanel rulesPanel = new JPanel();
		JTextArea text = new JTextArea(30, 40);
		text.setEditable(false);
		text.setBackground(new Color(24, 26, 27));
		text.setFont(new Font("Verdana", 0, 14));
		text.setForeground(new Color(255, 255, 255));
		text.setLineWrap(true);
		text.append(String.format("%55s%n%n%s", "Hangman Game rules",
				"\rThe player enters their name at the beginning of the game\n\n\rThe player guesses a word hidden with underscores (_) one letter at a time. If the guessed letter is in the word then all occurences of that letter is displayed in the correct position. If not, a mistake is recorded, placed in the list of wrong guessed letters and a stick figure is starting to be hang.\n\n\rThe player wins when they guess the word before six mistakes are made. When they lose, the correct answer is displayed and they get a chance to play another game.\n\nThe player has the option of asking for 3 hints"));

		rulesPanel.add(new JScrollPane(text), BorderLayout.CENTER);

		JOptionPane.showMessageDialog(this, rulesPanel, "Rules",
				JOptionPane.PLAIN_MESSAGE);
	} // displayRules()

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = fldGuess.getText();
		if (e.getSource() == btnGuess || e.getSource() == fldGuess) {
			processGuess(input);
		}
		if (e.getSource() == btnHint || e.getSource() == mntmHint) {
			getHint();
		}
		if (e.getSource() == mntmNewGame) {
			cmbName.setModel(
					new DefaultComboBoxModel<String>(new String[] { "-- Select --" }));
			refreshPlayerNameItems();
			newGame();
		}
		if (e.getSource() == mntmQuit) {
			serializeGame();
			System.exit(-1);
		}
		if (e.getSource() == mntmScoreBoard) {
			displayScoreBoard();
		}
		if (e.getSource() == mntmRules) {
			displayRules();
		}
	} // actionPerformed(ActionEvent)

	@Override
	public void windowOpened(WindowEvent e) {
		try {
			deserializeGame();
			refreshGame();
			refreshPlayerNameItems();
		}
		catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		catch (IOException e1) {
			hangmanGame = new HangmanGame();
			readWordList();
			newGame();
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		serializeGame();
	}

	@Override
	public void windowClosed(WindowEvent e) {

	}

	@Override
	public void windowIconified(WindowEvent e) {

	}

	@Override
	public void windowDeiconified(WindowEvent e) {

	}

	@Override
	public void windowActivated(WindowEvent e) {

	}

	@Override
	public void windowDeactivated(WindowEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == fldName) {
			fldName.setEnabled(true);
			lblFldName.setEnabled(true);
			fldName.setEditable(true);
			cmbName.setEnabled(false);
			lblCmbName.setEnabled(false);
		}
		if (e.getSource() == cmbName) {
			cmbName.setEnabled(true);
			lblCmbName.setEnabled(true);
			fldName.setEnabled(false);
			fldName.setEditable(false);
			lblFldName.setEnabled(false);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}

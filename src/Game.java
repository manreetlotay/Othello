import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {
	
	//fields
	private Player p1, p2;
	private Player first, second, current;
	private Board board;
	String fileName;
	
	//define Scanner
	Scanner scn = new Scanner(System.in);

	//constructor
	public Game(Player p1, Player p2) {
		this.p1 = p1;
		this.p2 = p2;
		
		//setting current/first player to player1 and second player to p2
		current = p1;
		first = p1;
		second = p2;

	}
	
	//method that changes player
	public void changePlayer() {
		if (current == p1)
			current = p2;
		else
			current = p1;
	}
			
		
	public void start() {
		
		//ask user about preferred starting position
		System.out.print("Enter 1 for a four-by-four non-standard starting position, Enter 2 for a standard starting position: ");
		int userChoice = scn.nextInt();
		
		//instantiate board
		Board board = new Board();
		
		if (userChoice == 1) {
			board.nonStandardStart();
			board.drawBoard();
		}
		
		else if (userChoice == 2) {
			board.standardStart();
			board.drawBoard();
		}
		
		
		//loop game play until game is not over/valid moves left
		while(!board.noValidMoveLeft(current.getColor())) {
			
			//give the player the option to save, concede or make a move
			int option;
			
			System.out.println();
			System.out.print(current + " - Enter 1 to save the game, Enter 2 to concede, Enter 3 to make a move: ");
			option = scn.nextInt();
			System.out.println();
			
			//check all cases
			if (option == 1) {
				board.save();
				playerSave();
				System.exit(0);
				
			}
			
			else if (option == 2) {
				
				//give turn to other player
				changePlayer();
				
				//method that prompts current player to make move
				System.out.println(current + " - Enter the coordinates of the cell separated by a space x[0, 7] and y[0, 7]");
				int x = scn.nextInt();
				int y = scn.nextInt();
				
				play(x, y, board);
				
			}
			
			else if (option == 3) {
				
				//method that prompts current player to make move
				System.out.print(current + " - Enter the coordinates of the cell separated by a space x[0, 7] and y[0, 7]");
				int x = scn.nextInt();
				int y = scn.nextInt();
				
				play(x, y, board);
				
			}
			
			//change player
			changePlayer();		
		}
		
		//if game is over display appropriate message
		board.boardState();
		
		}
		

	public void play(int x, int y, Board board) {
		
		//prompt user to keep entering a coordinate until a valid one is entered
		while(!(board.isValidMove(x, y, current.getColor()))) {
			System.out.println();
			System.out.println("That was as invalid move. Please try again");
			System.out.println();
			System.out.print(current + " - Enter the coordinates of the cell separated by a space x[0, 7] and y[0, 7]");
			
			Scanner scn = new Scanner(System.in);
			x = scn.nextInt();
			y = scn.nextInt();
		}
		
		//display # of pieces flipped after making move
		System.out.println();
		System.out.println("Flipped pieces: " + board.makeMove(x, y, current.getColor()));
		
		
		//display both players' score after move
		System.out.println();
		board.count(p1);
		System.out.println();
		board.count(p2);    
		
		//draw board
		board.drawBoard();
		
	}
	
	//method that saves player color and name to textfile
	public void playerSave() {
		
		// Scanner
		Scanner scn = new Scanner(System.in);

		System.out.println("Please enter a file name for your game");
		fileName = scn.next();
		
		// create File
		File fileObject = new File(fileName);
		
		// appending to file
		PrintWriter outputStream = null;
		try {

			outputStream = new PrintWriter(new FileOutputStream(fileObject, true));
			
			//for printing player name
			outputStream.println("Player 1: " + p1.getName());
			outputStream.println("Player 1: " + p1.getColor());
			outputStream.println("Player 1: " + p2.getName());
			outputStream.println("Player 1: " + p2.getColor());
			
			outputStream.close();

		} catch (FileNotFoundException e) {

			System.out.println("The file could not be opened");
			System.exit(0);

		}
	}
	

	

}



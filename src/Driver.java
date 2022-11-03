
import java.io.IOException;
import java.util.Scanner;

class Driver {

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		
		//define Scanner
				Scanner scn = new Scanner(System.in);

		//fields
		int userChoice;
		String player1, player2;
		
		//welcome user
		System.out.println("--------------------PLAY OTHELLO--------------------");
		System.out.println();
		System.out.println("Here Are Your Options:");
		System.out.println();
		
		//Provide user options
		System.out.println("1. Start a New Game");
		System.out.println("2. Quit");
		//System.out.println("3. Load a Game");
		System.out.println();

		userChoice = scn.nextInt();
		System.out.println();

		while ((userChoice != 3) || (userChoice != 2) || (userChoice != 1)) {
			
			switch (userChoice) {

			case 1: {

				// request two player names
				System.out.print("Player 1, please enter you name: ");
				player1 = scn.next();
				System.out.println();
				System.out.print("Player 2, please enter your name: ");
				player2 = scn.next();
				System.out.println();

				//initialize player 1 to B and player 2 to W
				Player p1 = new Player(player1, 'B');
				Player p2 = new Player(player2, 'W');

				// instantiate game with player 1 and 2 where player 1 is current player
				Game game = new Game(p1, p2);

				// instantiate board
				Board board = new Board();

				game.start();
		
				}

		case 2: System.exit(0);

		/*
		case 3: {
					//instantiate board
					Board board = new Board();
					//prompt user to enter file
					System.out.println("Please enter file name");
					String fileName = scn.next();
					
					//load 
					board.load(fileName);
				}
				*/
	
					}
		}



	}
}

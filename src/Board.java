import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Board {

	// attributes
	private Position[][] board = new Position[8][8];
	String fileName;

	// constructor
	public Board() {

	}

	// initialize board to a four-by-four non-standard starting position
	public void nonStandardStart() {

		// initializing all positions to be empty
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new playablePosition('-');
			}
			System.out.println();
		}

		board[0][2] = new unplayablePosition('*');
		board[0][3] = new unplayablePosition('*');
		board[0][4] = new unplayablePosition('*');
		board[0][5] = new unplayablePosition('*');

		board[2][2] = new unplayablePosition('W');
		board[3][3] = new unplayablePosition('W');
		board[4][4] = new unplayablePosition('W');
		board[5][5] = new unplayablePosition('W');
		board[2][3] = new unplayablePosition('W');
		board[3][2] = new unplayablePosition('W');
		board[4][5] = new unplayablePosition('W');
		board[5][4] = new unplayablePosition('W');

		board[2][4] = new unplayablePosition('B');
		board[3][5] = new unplayablePosition('B');
		board[4][2] = new unplayablePosition('B');
		board[5][3] = new unplayablePosition('B');
		board[2][5] = new unplayablePosition('B');
		board[3][4] = new unplayablePosition('B');
		board[4][3] = new unplayablePosition('B');
		board[5][2] = new unplayablePosition('B');

	}

	// intialize board to a standard starting position
	public void standardStart() {

		// initializing all positions to be empty
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new playablePosition('-');
			}
			System.out.println();
		}

		// initializing unplayable position of a standard othello board
		board[0][2] = new unplayablePosition('*');
		board[0][3] = new unplayablePosition('*');
		board[0][4] = new unplayablePosition('*');
		board[0][5] = new unplayablePosition('*');

		board[3][3] = new unplayablePosition('W');
		board[4][4] = new unplayablePosition('W');
		board[4][3] = new unplayablePosition('B');
		board[3][4] = new unplayablePosition('B');

	}

	public void drawBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
		}
	}

	// method that checks if a move is valid
	public boolean isValidMove(int x, int y, char piece) {

		// unvalid coordinate for 8x8 board
		if ((x > 7) || (y > 7) || (x < 0) || (y < 0))
			return false;

		// if coordinate entered is not playableposition, not a valid move
		if (!board[x][y].canPlay())
			return false;

		// set opposite char as piece to be flipped
		char opposite;
		if (piece == 'B')
			opposite = 'W';
		else
			opposite = 'B';

		// check if opposite piece can be found in one of the 8 directions surrounding
		// the (x, y) position where player wants the piece to be placed

		// first direction: upper left (only coordinates with x > 0 and y > 0 have other
		// coordinate to their upperleft
		if ((x > 0) && (y > 0)) {
			if ((board[x - 1][y - 1].getPiece() == opposite)) {
				int r = x - 1;
				int c = y - 1;

				// loop ensures we don't go out out of the board
				while ((r > 0) && (c > 0)) {
					if ((board[r - 1][c - 1].isEmpty())) // if the piece after is empty, go out of loop: not a valid
															// move
						break;
					--r;
					--c;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}

		}

		// second direction: upper right
		if ((x > 0) && (y < 7)) {
			if ((board[x - 1][y + 1].getPiece() == opposite)) {
				int r = x - 1;
				int c = y + 1;

				while (r > 0 && c < 7) {
					if (board[r - 1][c + 1].isEmpty())
						break;
					--r;
					++c;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}

		// third direction: down left
		if ((x < 7) && (y > 0)) {
			if ((board[x + 1][y - 1].getPiece() == opposite)) {
				int r = x + 1;
				int c = y - 1;

				while (r < 7 && c > 0) {
					if (board[r + 1][c - 1].isEmpty())
						break;
					++r;
					--c;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}

		// fourth direction: down right
		if ((x < 7) && (y < 7)) {
			if ((board[x + 1][y + 1].getPiece() == opposite)) {
				int r = x + 1;
				int c = y + 1;

				while (r < 7 && c < 7) {
					if (board[r + 1][c + 1].isEmpty())
						break;
					++r;
					++c;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}

		// fifth direction: left
		if (y > 0) {
			if ((board[x][y - 1].getPiece() == opposite)) {
				int r = x;
				int c = y - 1;

				while (c > 0) {
					if (board[r][c - 1].isEmpty())
						break;
					--c;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}

		// sixth direction: right
		if (y < 7) {
			if ((board[x][y + 1].getPiece() == opposite)) {
				int r = x;
				int c = y + 1;

				while (c < 7) {
					if (board[r][c + 1].isEmpty())
						break;
					++c;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}

		// seventh direction: up
		if (x > 0) {
			if ((board[x - 1][y].getPiece() == opposite)) {
				int r = x - 1;
				int c = y;

				while (r > 0) {
					if (board[r - 1][c].isEmpty())
						break;
					--r;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}

		// eigth direction: down
		if (x < 7) {
			if ((board[x + 1][y].getPiece() == opposite)) {
				int r = x + 1;
				int c = y;

				while (r < 7) {
					if (board[r + 1][c].isEmpty())
						break;
					++r;
					if (board[r][c].getPiece() == piece)
						return true;
				}
			}
		}
		return false;
	}

	// makeMove method that flips piece if chosen coordinate is a valid move
	public int makeMove(int x, int y, char piece) {

		// count opposite pieces that were flipped
		int oppositeCount = 0;

		if (isValidMove(x, y, piece)) {

			// set opposite char
			char opposite;
			if (piece == 'B')
				opposite = 'W';
			else
				opposite = 'B';

			// if opposite piece is to the left
			if (y > 1) {
				if (board[x][y - 1].getPiece() == opposite) {

					int r = x;
					int c = y - 1;

					// ensure next one is not empty
					if (!(board[r][c - 1].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							--c;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece is to the right
			if (y < 6) {
				if (board[x][y + 1].getPiece() == opposite) {

					int r = x;
					int c = y + 1;

					// ensure next one is not empty
					if (!(board[r][c + 1].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							++c;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece is up
			if (x > 1) {
				if (board[x - 1][y].getPiece() == opposite) {

					int r = x - 1;
					int c = y;

					if (!(board[r - 1][c].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							--r;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece is down
			if (x < 6) {
				if (board[x + 1][y].getPiece() == opposite) {

					int r = x + 1;
					int c = y;

					if (!(board[r + 1][c].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							++r;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece upper left
			if ((x > 1) && (y > 1)) {
				if (board[x - 1][y - 1].getPiece() == opposite) {

					int r = x - 1;
					int c = y - 1;

					if (!(board[r - 1][c - 1].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							--r;
							--c;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece is upper right
			if ((x > 1) && (y < 6)) {
				if (board[x - 1][y + 1].getPiece() == opposite) {

					int r = x - 1;
					int c = y + 1;

					if (!(board[r - 1][c + 1].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							--r;
							++c;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece is down left
			if ((x < 6) && (y > 1)) {
				if (board[x + 1][y - 1].getPiece() == opposite) {

					int r = x + 1;
					int c = y - 1;

					if (!(board[r + 1][c - 1].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							++r;
							--c;
						}
						return oppositeCount;
					}
				}
			}

			// if opposite piece is down right
			if ((x < 6) && (y < 6))
				if (board[x + 1][y + 1].getPiece() == opposite) {

					int r = x + 1;
					int c = y + 1;

					if (!(board[r + 1][c + 1].isEmpty())) {

						// if it's a valid move place the piece on the board
						board[x][y].setPiece(piece);

						while ((board[r][c].getPiece() != piece) && (!(board[r][c].isEmpty()))) {

							flip(r, c);
							++oppositeCount;
							++r;
							++c;
						}
						return oppositeCount;
					}
				}

		}

		return oppositeCount;
	}

	// method that flips piece
	public void flip(int x, int y) {
		if (board[x][y].getPiece() == 'W')
			board[x][y].setPiece('B');
		else
			board[x][y].setPiece('W');

	}

	// method that checks if there are no more valid moves left/game over
	public boolean noValidMoveLeft(char piece) {

		// check if there's any valid moves on the board, if so, return false
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (isValidMove(i, j, piece))
					return false;
			}
		}
		return true;
	}

	// method returns count of black pieces
	public int blackCount() {

		int blackCount = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].isBlack())
					++blackCount;
			}
		}
		return blackCount;
	}

	// method that return count of white pieces
	public int whiteCount() {

		int whiteCount = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j].isWhite())
					++whiteCount;
			}
		}
		return whiteCount;
	}

	// a player's piece count
	public void count(Player current) {

		int count = 0;

		if (current.getColor() == 'B') {

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j].isBlack())
						++count;
				}
			}

			System.out.println(current + ": " + count);
		}

		else if (current.getColor() == 'W') {

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j].isWhite())
						++count;
				}
			}
			System.out.println(current + ": " + count);
		}
	}

	// method that prints the current board state
	public void boardState() {

		System.out.print("Black pieces: ");
		System.out.print(blackCount());
		System.out.println();
		System.out.print("White pieces: ");
		System.out.println(whiteCount());

		// check who won
		if (blackCount() > whiteCount()) {
			System.out.println("**Player 1 won**");
		} else if (blackCount() < whiteCount())
			System.out.println("**player 2 won**");
		else
			System.out.println("**The game is over**");
	}

	//method that saves board state into a text file
	public void save() {

		// Scanner
		Scanner scn = new Scanner(System.in);

		System.out.println("Please enter a file name for your game");
		fileName = scn.next();

		// create File
		File fileObject = new File(fileName);

		while (fileObject.exists()) {
			System.out.println("This file already exists. Please enter a different file name");
			fileName = scn.next();
			fileObject = new File(fileName);
		}

		// writing to file
		PrintWriter outputStream = null;
		try {

			outputStream = new PrintWriter(new FileOutputStream(fileObject));

			// printing array
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					outputStream.print(board[i][j]);
				}
				outputStream.println();
			}
			
			outputStream.close();

		} catch (FileNotFoundException e) {

			System.out.println("The file could not be opened");
			System.exit(0);

		}

	}

	//method to load game previously saved in a text file
	/*public void load(String fileName)  {
		
		Scanner inputStream = null;

		try {

			inputStream = new Scanner(new FileInputStream(fileName));
			
			char [][] arr = new char[8][8];
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					arr = inputStream.next();
				}
			}
			
			//save player names on line 9 and 10
			inputStream.next(); inputStream.next(); inputStream.next(); inputStream.next(); inputStream.next(); inputStream.next(); inputStream.next(); inputStream.next();
			String p1 = inputStream.next();
			String p2 = inputStream.next();
			
		} catch (FileNotFoundException e) {

			System.out.println(fileName + " could not be found");
		}

	}*/


}

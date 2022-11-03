public abstract class Position {

	//fields
	private char piece;
	public static final char EMPTY = '-';
	public static final char BLACK = 'B';
	public static final char WHITE = 'W';

	// constructor
	public Position(char piece) {
		this.piece = piece;
	}
	
	// getter
	public char getPiece() {
		return piece;
	}

	
	//setters
	public void setPiece(char piece) {
		this.piece = piece;
	}
	
	// abstract method
	public abstract boolean canPlay();

	// toString method to print type of piece in position
	public String toString() {
		return String.valueOf(this.getPiece());
	}

	// method that checks if a position is empty
	public boolean isEmpty() {
		if (this.piece == EMPTY)
			return true;
		else
			return false;
	}

	// method that checks if a position has a black
	public boolean isBlack() {
		if (this.piece == BLACK)
			return true;
		else
			return false;
	}

	// method that checks if a position has a white
	public boolean isWhite() {
		if (this.piece == WHITE)
			return true;
		else
			return false;
	}

}

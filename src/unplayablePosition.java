public class unplayablePosition extends Position{

	//attributes
	public static final char UNPLAYABLE = '*';
	
	//constructor
	public unplayablePosition(char piece) {
		super(piece);
	}

	@Override
	public boolean canPlay() {
		return false;
	}

}

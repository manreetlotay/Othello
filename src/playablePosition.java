
public class playablePosition extends Position{

	//constructor
	public playablePosition(char piece) {
		super(piece);
	}

	@Override
	public boolean canPlay() {
		if (this.getPiece() == EMPTY)
			return true;
		else
			return false;
	}

}

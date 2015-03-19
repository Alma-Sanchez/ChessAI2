public class BlankPiece extends Piece {

	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		return false;
	}

	@Override
	public boolean wasPressed(int x, int y) {
		return false;
	}
	//set the peices to a blank space instead of null
	public BlankPiece(){
		pieceletter=" ";
		img=null;
	}

}

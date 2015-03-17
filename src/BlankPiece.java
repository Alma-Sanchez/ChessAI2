
public class BlankPiece extends Piece {

	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean wasPressed(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public BlankPiece(){
		pieceletter=" ";
		img=null;
	}

}

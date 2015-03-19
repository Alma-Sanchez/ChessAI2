import javax.swing.ImageIcon;

public class Knight extends Piece{
	//constructor
	public Knight(boolean isWhite, Chess chess){
		//set knight color
		this.isWhite=isWhite;
		if (isWhite){
			img=new ImageIcon ("wknight.gif");
			pieceletter="K";
		}
		else 
		{
			img=new ImageIcon ("bknight.gif");
			pieceletter="k";
		}
	}

	@Override
	void drawPiece() {
	}
	
	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		//movement conditional statement
		if (Math.abs(y-finy)==1 && Math.abs(x-finx)==2){
			if((" ".equals(Chess.pieces[finy][finx].pieceletter))||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}else{
				return false;
			}
		}
		else if (Math.abs(y-finy)==2 && Math.abs (x-finx)==1){
			if((" ".equals(Chess.pieces[finy][finx].pieceletter))||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}
			else{
				return false;
			}
		}else{
			return false;
		}
	}
		/*if (((Math.abs(y-finy)<3 && Math.abs(x-finx)<3))&&Math.abs(Math.abs(y-finy)-Math.abs(x-finx))==1){ 
			//checks to see if final destination is null or has a piece of opposite color to take
			if((" ".equals(Chess.pieces[finy][finx].pieceletter))||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}
		}
		return false;*/
	@Override
	public boolean wasPressed(int x, int y) {
		return false;
	}
}

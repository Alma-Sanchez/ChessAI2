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
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		//movement conditional statement
		if ((Math.abs(y-finy)<3 && Math.abs(x-finx)<3)&&Math.abs(Math.abs(y-finy)-Math.abs(x-finx))==1){ 
			//checks to see if final destination is null or has a piece of opposite color to take
			if((" ".equals(Chess.pieces[finy][finx].pieceletter))||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean wasPressed(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}

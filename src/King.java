import javax.swing.ImageIcon;


public class King extends Piece{
	Chess chess;

	


	public boolean wasPressed(int clickx, int clicky) {
		return false;
	
		}

	


	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		//move king forward and backward
		if ((Math.abs(y-finy)==1 && Math.abs(x-finx)==0) && ( chess.pieces[finy][finx]==null||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite)){
		
				return true;
			
		}
		//move king side to side
		else if ((Math.abs(x-finx)==1 && Math.abs(y-finy)==0)&&(chess.pieces[finy][finx]==null||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite)){ 
			
				return true;
			
		}
		//move king diagionally
		else if ((Math.abs(x-finx)==1 && Math.abs(y-finy)==1) &&(chess.pieces[finy][finx]==null || chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite)){ 
			
				return true;
				//System.out.println(chess.pieces[finy][finx]+"it went through");
		}
			
		return false;
	}


	void drawPiece() {
		// TODO Auto-generated method stub
		
	}
	public King(boolean isWhite, Chess chess) {
		// TODO Auto-generated constructor stub
		//set king color
		this.isWhite=isWhite;
		this.chess=chess;
		if (isWhite){
			img=new ImageIcon ("wking.gif");
			pieceletter="A";
			
		}
		else 
		{
			img=new ImageIcon ("bking.gif");
			pieceletter="a";
		}
	}
}

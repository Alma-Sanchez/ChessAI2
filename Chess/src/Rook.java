import javax.swing.ImageIcon;


public class Rook extends Piece {
	
	Chess chess;
	public Rook(boolean isWhite, Chess chess){
		this.isWhite=isWhite;
		if (isWhite){
			img=new ImageIcon ("wrook.gif");
			pieceletter="R";
			
		}
		else 
		{
			img=new ImageIcon ("brook.gif");
			pieceletter="r";
		}
	}



	@Override
	void drawPiece() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		//negative movement y direction
		if ((y-finy)>0 && (x-finx)==0){ 
			
			for (int i=1; i<Math.abs(y-finy);i++){
					if (Chess.pieces[y-i][x]!=null){
						return false;
					
					}
					
		
			}
			if(Chess.pieces[finy][finx]==null||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}
		}
		//positive movement y direction
		else if ((y-finy)<0 && (x-finx)==0){
			for (int i=1; i<Math.abs(y-finy);i++){
					if (Chess.pieces[y+i][x]!=null){
						return false;
					
					}
					
		
			}
			if(Chess.pieces[finy][finx]==null||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}
		}
		//negative x movement
		else if ((x-finx)>0 && (y-finy)==0){
			for (int i=1; i<Math.abs(x-finx);i++){
					if (Chess.pieces[y][x-i]!=null){
						return false;
					
					}
					
		
			}
			if(Chess.pieces[finy][finx]==null||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
				return true;
			}
		}
		//positive x movement
		else if ((x-finx)<0 && (y-finy)==0){
			for (int i=1; i<Math.abs(x-finx);i++){
					if (Chess.pieces[y][x+i]!=null){
						return false;
					
					}
					
		
			}
			if(Chess.pieces[finy][finx]==null||Chess.pieces[finy][finx].isWhite!=Chess.pieces[y][x].isWhite){
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

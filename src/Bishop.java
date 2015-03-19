import javax.swing.ImageIcon;


public class Bishop extends Piece {
	Chess chess;
	public Bishop(boolean isWhite, Chess chess) {
		// TODO Auto-generated constructor stub
		//boolean determines color
		this.isWhite = isWhite;
		this.chess = chess;
		//chooses correct image based on color
		if (isWhite){
			img=new ImageIcon ("wbishop.gif");
			pieceletter="B";
			
		}
		else 
		{
			img=new ImageIcon ("bbishop.gif");
			pieceletter="b";
		}
	}

	



	@Override
	void drawPiece() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		
	//if statement determining if piece is moving in negative x and negative y direction
		if (((x-finx)>0 && (y-finy)>0) && Math.abs(x-finx)==Math.abs(y-finy)){ 
				// for loop that goes through tiles in movement path of piece looking for obstacles that may block movement
				for (int i=1; i<Math.abs(x-finx);i++){
						if (!(" ".equals(chess.pieces[y-i][x-i].pieceletter))){
							return false;
						
						}
						
			
				}
				//if path is clear checks to see if final point is null or has a piece of opposite color to take
				if((" ".equals(chess.pieces[finy][finx].pieceletter))||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite){
					return true;
				}
		}
		//positive x, positive y case 
		else if (((x-finx)<0 && (y-finy)<0) && Math.abs(x-finx)==Math.abs(y-finy)){
			//another for loop checking the movement path for obstacles
			for (int i=1; i<Math.abs(x-finx);i++){
				if (!(" ".equals(chess.pieces[y+i][x+i].pieceletter))){
					return false;
				
				}
				
	
		}
			//if path is clear checks to see if final point is null or has a piece of opposite color to take
		if((" ".equals(chess.pieces[finy][finx].pieceletter))||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite){
			return true;
		}
			
		}
		//negative y and positive x case
		else if (((x-finx)<0 && (y-finy)>0) && Math.abs(x-finx)==Math.abs(y-finy)){
			// for loop checking path
			for (int i=1; i<Math.abs(x-finx);i++){
				if (!(" ".equals(chess.pieces[y-i][x+i].pieceletter))){
					return false;
				
				}
				
	
		}
			//checking final destination to make sure it's empty or contains pieces to take
			//System.out.println(chess.pieces[y][x].isWhite);
		if((" ".equals(chess.pieces[finy][finx].pieceletter))||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite){
			return true;
		}
			
		}
		//postivie y and negative x case
		else if (((x-finx)>0 && (y-finy)<0) && Math.abs(x-finx)==Math.abs(y-finy)){
			//path checker
			for (int i=1; i<Math.abs(x-finx);i++){
				if (!(" ".equals(chess.pieces[y+i][x-i].pieceletter))){
					return false;
				
				}
				
	
		}
			//final spot checker
		if((" ".equals(chess.pieces[finy][finx].pieceletter))||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite){
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

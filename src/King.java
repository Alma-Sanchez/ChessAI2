import javax.swing.ImageIcon;


public class King extends Piece{
	Chess chess;

	


	public boolean wasPressed(int clickx, int clicky) {
		return false;
	
		}
	public static String posibleA(int i) {
        String list="";
        int r=i/8, c=i%8;
        for (int j=0;j<9;j++) {
            if (j!=4) {
                try {
                    if (Chess.pieces[r][c].canMove(r,c, r-1+j/3, c-1+j%3)) {
                    	
                     
                        if (BoardMouseListener.kingSafe()) {
                            list=list+r+c+(r-1+j/3)+(c-1+j%3)+Chess.pieces[r-1+j/3][c-1+j%3].pieceletter;
                        }
                        
                        
                    }
                } catch (Exception e) {}
            }
           
            
        }
        //need to add casting later
        return list;
    }

	


	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		//move king forward and backward
		if ((Math.abs(y-finy)==1 && Math.abs(x-finx)==0) && ( (" ".equals(chess.pieces[finy][finx].pieceletter))||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite)){
		
				return true;
			
		}
		//move king side to side
		else if ((Math.abs(x-finx)==1 && Math.abs(y-finy)==0)&&((" ".equals(chess.pieces[finy][finx].pieceletter))||chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite)){ 
			
				return true;
			
		}
		//move king diagionally
		else if ((Math.abs(x-finx)==1 && Math.abs(y-finy)==1) &&((" ".equals(chess.pieces[finy][finx].pieceletter)) || chess.pieces[finy][finx].isWhite!=chess.pieces[y][x].isWhite)){ 
			
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
		//this.isWhite=isWhite;
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

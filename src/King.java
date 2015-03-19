import javax.swing.ImageIcon;

public class King extends Piece{
	Chess chess;

	public boolean wasPressed(int clickx, int clicky) {
		return false;
	}
	
	//Function that the rating class uses to determine score in relation to King. Creates a list of safe moves the king can make in order to avoid check.
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
    static void updatekingposition(){
		int kingPositionC=0;
		int kingPositionL=0;
				
		while (!"A".equals(Chess.pieces[kingPositionC/8][kingPositionC%8].pieceletter))
		{
			//System.out.println("A".equals(pieces[kingPositionC/8][kingPositionC%8].pieceletter));
					kingPositionC++;
				
			}
		while ( !"a".equals(Chess.pieces[kingPositionL/8][kingPositionL%8].pieceletter)) {

			kingPositionL++;
    		}
		Chess.kingPositionC=kingPositionC;
		Chess.kingPositionL=kingPositionL;
		System.out.println("kingPositionC = "+Chess.kingPositionC+" kingPositionL = "+Chess.kingPositionL);
	}

    //determines if the king is being moved to a legal spot
	public boolean canMove(int x, int y, int finx, int finy) {
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
	void drawPiece(){
	}
	
	public King(boolean isWhite, Chess chess) {
		// TODO Auto-generated constructor stub
		//set king color
		//this.isWhite=isWhite;
		this.chess=chess;
		if (isWhite){
			img=new ImageIcon ("wking.gif");
			pieceletter="A";
		}else{
			img=new ImageIcon ("bking.gif");
			pieceletter="a";
		}
	}
}

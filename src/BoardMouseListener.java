import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardMouseListener implements MouseListener {
	static Chess chess;
	//position of mouse
	public static int posx;
	public static int posy;
	public static int posxfinal;
	public static int posyfinal;
	public static int globaldepth=4;
	//boolean so white always moves first
	//static boolean whiteturn = true;
	public static String alphaBeta(int depth, int beta, int alpha, String move, int player){
        String list=possiblemoves();
        //System.out.println(list);
        //base case. Lets the program know what to do if it reaches the end of possible moves
        //or if there are no possible moves
        if (depth==0 || list.length()==0) {
            return move+(rating()*(player*2-1));
        }
        //sort(the best that we can) later to help speed up alpha beta choosing the best move
        player=1-player;//either 1 or 0, switches who the current player is
        //Go through every possible move
        for (int i=0;i<list.length();i+=5){
            //make a move
        	//System.out.println(list.substring(i,i+5));
            makemove(list.substring(i,i+5));
            //flip the chess board to predict the human player's moves
            flipboard();
            //the recursion part, gives the function different depth and move
            String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);

            int value=Integer.valueOf(returnString.substring(5));
            //flip board back to normal
            flipboard();
            undomove(list.substring(i,i+5));
            //else refers to other player
            if (player==0) {
                //if it's a good move return the move value
                if (value<=beta) {
                    beta=value;
                    if (depth==globaldepth){
                        move=returnString.substring(0,5);
                    }
                }
            } else {
                if (value>alpha){
                    alpha=value;
                    if (depth==globaldepth)
                    {
                        move=returnString.substring(0,5);
                    }
                }
            }
            if (alpha>=beta) {
                if (player==0) {
                    return move+beta;
                }
                else {
                    return move+alpha;
                }
            }
        }
        //incase the algorithm found no good move, just return what it has
        if (player==0) {
            return move+beta;
        }
        else {
            return move+alpha;
        }
    }
    public static String possiblemoves(){          //finds possible moves for each peice
        String themoves="";                       // tells the alpha beta alogritm what moves to evaluate
        int count=0;
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                if (Chess.pieces[i][j]!=null){
                    for (int k=0; k<8; k++){
                        for (int l=0; l<8; l++){
                            //System.out.println("Initial y is "+ i+" Initial x is "+j+ " Final y is "+k+ " Final x is "+ l);
                            if (Chess.pieces[i][j].canMove(j, i, l ,k)){
                                count++;
                                String istring=Integer.toString(i);
                                String jstring=Integer.toString(j);
                                String kstring=Integer.toString(k);
                                String lstring=Integer.toString(l);
                                if (Chess.pieces[k][l]!=null){
                                	System.out.println("There is a piece to be taken, "+chess.pieces[j][i].pieceletter+" at "+j+","+i+" takes "+chess.pieces[k][l].pieceletter+" at "+l+","+k);
                                    String newmove=jstring+istring+lstring+kstring+Chess.pieces[l][k].pieceletter;
                                    if (count<100){
                                        themoves+=newmove;
                                    }
                                }
                                else{
                                    String newmove=jstring+istring+lstring+kstring+" ";
                                    if (count<100){
                                        themoves+=newmove;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return themoves;
    }
    public static int rating(){
        //System.out.print("What is the score: ");
        //Scanner sc=new Scanner(System.in);
        return 0;
    }
    //flips the board
    public static void postflip(int x, int y){
    	Piece temp = null;
    	temp=chess.pieces[y][x];
    	chess.pieces[y][x]=chess.pieces[x][y];
    	chess.pieces[x][y]=temp;
    	
    }
    public static void flipboard(){
    	Piece temp = null;
        for (int i=0; i<32; i++){
            int r= i/8;
            int c= i%8;
            if(chess.pieces[c][r]!=null){
            	if (Character.isUpperCase(chess.pieces[c][r].pieceletter.charAt(0))){
            		chess.pieces[c][r].pieceletter.toLowerCase();
            		chess.pieces[c][r].isWhite=false;
            		temp=chess.pieces[c][r];
            	}
            	else{
            		chess.pieces[c][r].pieceletter.toUpperCase();
            		chess.pieces[c][r].isWhite=true;
            		temp=chess.pieces[c][r];
            	}
            }
            else{
            	temp=chess.pieces[c][r];
            }
            if (chess.pieces[7-c][7-r]!=null){
            	if (Character.isUpperCase(chess.pieces[7-c][7-r].pieceletter.charAt(0))){
            		chess.pieces[7-c][7-r].pieceletter.toLowerCase();
            		chess.pieces[7-c][7-r].isWhite=false;
            		chess.pieces[c][r]=chess.pieces[7-c][7-r];
            	}
            	else{
            		chess.pieces[7-c][7-r].pieceletter.toUpperCase();
            		chess.pieces[7-c][7-r].isWhite=true;
            		chess.pieces[c][r]=chess.pieces[7-c][7-r];
            	
            	}
            	chess.pieces[7-c][7-r]=temp;
            }
            else{
            	chess.pieces[c][r]=chess.pieces[7-c][7-r];
            	chess.pieces[7-c][7-r]=temp;
            	/*if (chess.pieces[c][r]!=null){
            	System.out.println(chess.pieces[c][r]+" at "+c+","+r+" turned to null");
            	}*/
            	
            }
          
            
        }
    }
    public static void makemove(String moveinfo){
        int posx=Character.getNumericValue(moveinfo.charAt(0));
        int posy=Character.getNumericValue(moveinfo.charAt(1));
        int posxfinal=Character.getNumericValue(moveinfo.charAt(2));
        int posyfinal=Character.getNumericValue(moveinfo.charAt(3));

        //conditional checking to see if it's the first turn so white moves first
        //if (whiteturn == true) {
            //makes it so you can click and release on empty square without null pointer exception
            if (/*chess.pieces[posy][posx]!=null &&*/ (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0)) {
                //checks to see if piece can move based on initial and final coordinates, this version also only allows white to move since it is the first turn
            	//System.out.println(chess.pieces[posy][posx].pieceletter+" "+posy+" "+posx+" "+posyfinal+" "+posxfinal);
                if (chess.pieces[posy][posx]!=null && chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)/*&& chess.pieces[posy][posx].isWhite == true*/) {
                    //moves piece
                	if (chess.pieces[posyfinal][posxfinal]!=null){
                		System.out.println(chess.pieces[posyfinal][posxfinal].pieceletter+" at "+posyfinal+","+posxfinal+ " is killed by "+chess.pieces[posy][posx].pieceletter+" at "+posy+","+posx);
                	}
                    chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
     
                    //conditional prevents user from deleteing piece of mouse is clicked and release on the same position
                    if (posy != posyfinal || posx != posxfinal) {
                        //deletes old piece position
                        chess.pieces[posy][posx] = null;
                        //ends first move/whites beginning turn
                        //whiteturn = false;
                    }
                    //repaints board with new piece positions
                    //chess.theboard.repaint();
                    String test= possiblemoves();
                    //System.out.println(test);
                }
            }
        //}
        //conditionals for all turns after first turn
            /*
        else{
            //makes it so user can click and release on empty square without null pointer exception
            if (/*chess.pieces[posy][posx]!=null && (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0)) {
                //checks if piece can move
            	System.out.println(posy+" "+posx+ " "+ posyfinal+ " "+ posxfinal+ " ");
            	System.out.println(chess.pieces[posy][posx]);
                if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal) && chess.pieces[posy][posx].isWhite==false) {
                    //changes position
                    chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
                    //prevents user from accidentally deleting piece by clicking and releasing on same piece
                    if (posy != posyfinal || posx != posxfinal) {
                        //deletes old piece position
                        chess.pieces[posy][posx] = null;
                        whiteturn=true;
                    }
                    //repaints boardå
                    //chess.theboard.repaint();

                    String test= possiblemoves();
                    //System.out.println(test);
                }
            }
        } 
            */
    }

    public static void undomove(String moveinfo){
        posx=Character.getNumericValue(moveinfo.charAt(0));
        posy=Character.getNumericValue(moveinfo.charAt(1));
        posxfinal=Character.getNumericValue(moveinfo.charAt(2));
        posyfinal=Character.getNumericValue(moveinfo.charAt(3));
        chess.pieces[posy][posx]=chess.pieces[posyfinal][posxfinal];
        //chess.pieces[posyfinal][posxfinal]=null;
        if (moveinfo.charAt(4)==' '){
            Chess.pieces[posyfinal][posxfinal]=null;
        }
        else if (moveinfo.charAt(4)=='r'){
            Chess.pieces[posyfinal][posxfinal]=new Rook(false, chess);
        }
        else if (moveinfo.charAt(4)=='R'){
            Chess.pieces[posyfinal][posxfinal]=new Rook(true, chess);
        }
        else if (moveinfo.charAt(4)=='k'){
            Chess.pieces[posyfinal][posxfinal]=new Knight(false, chess);
        }
        else if (moveinfo.charAt(4)=='K'){
            Chess.pieces[posyfinal][posxfinal]=new Knight(true, chess);
        }
        else if (moveinfo.charAt(4)=='b'){
            Chess.pieces[posyfinal][posxfinal]=new Bishop(false, chess);
        }
        else if (moveinfo.charAt(4)=='B'){
            Chess.pieces[posyfinal][posxfinal]=new Bishop(true, chess);
        }
        else if (moveinfo.charAt(4)=='q'){
            Chess.pieces[posyfinal][posxfinal]=new Queen(false, chess);
        }
        else if (moveinfo.charAt(4)=='Q'){
            Chess.pieces[posyfinal][posxfinal]=new Queen(true, chess);
        }
        else if (moveinfo.charAt(4)=='a'){
            Chess.pieces[posyfinal][posxfinal]=new King(false, chess);
        }
        else if (moveinfo.charAt(4)=='A'){
            Chess.pieces[posyfinal][posxfinal]=new King(true, chess);
        }
        else if (moveinfo.charAt(4)=='p'){
            Chess.pieces[posyfinal][posxfinal]=new Pawn(false, chess);
        }
        else if(moveinfo.charAt(4)=='P'){
            Chess.pieces[posyfinal][posxfinal]=new Pawn(true, chess);
        }
        /*if (whiteturn){
        	whiteturn=false;
        }
        else{
        	whiteturn=true;
        }*/
        
    }

	public BoardMouseListener(Chess chess) {
		this.chess = chess;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		//equates initial mouse/piece position with tile position on board
		posx = e.getX() / 62;
		posy = e.getY() / 62;
		

	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//equates final mouse/piece position with tile position on board
		posxfinal = e.getX() / 62;
		posyfinal = e.getY() / 62;
		System.out.println("The mouse released on the board");
		//conditional checking to see if it's the first turn so white moves first
		//if (whiteturn == true) {
			//makes it so you can click and release on empty square without null pointer exception
			if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
				//checks to see if piece can move based on initial and final coordinates, this version also only allows white to move since it is the first turn
				if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)&& chess.pieces[posy][posx].isWhite == true) {
					//moves piece
					chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
					//conditional prevents user from deleteing piece of mouse is clicked and release on the same position
					if (posy != posyfinal || posx != posxfinal) {
						//deletes old piece position
						chess.pieces[posy][posx] = null;
						System.out.println("the move was done");
						//flipboard();
						//ends first move/whites beginning turn
						//whiteturn = false;
						//flipboard();
						//postflip(posxfinal, posyfinal);
					}
					//repaints board with new piece positions
				
				
					
					
					
				}
			}
			chess.theboard.repaint();
		//}
		//conditionals for all turns after first turn
		/*else{
			//makes it so user can click and release on empty square without null pointer exception
			if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
				//checks if piece can move
				
				if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal) && chess.pieces[posy][posx].isWhite == false) {
					//changes position
					chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
					//prevents user from accidentally deleting piece by clicking and releasing on same piece
					if (posy != posyfinal || posx != posxfinal) {
						//deletes old piece position
						chess.pieces[posy][posx] = null;
						whiteturn=true;
						//flipboard();
						//postflip(posx,posy);
						
						
					}
					//repaints boardå
					
				
			
				}
			
		
			}
			
			chess.theboard.repaint();
				
		}*/
		
		/*String Stringx=Integer.toString(posx);
		String Stringy=Integer.toString(posy);
		String Stringfinx=Integer.toString(posxfinal);
		String Stringfiny=Integer.toString(posyfinal);
		String testmove=Stringx+Stringy+Stringfinx+Stringfiny+"A";
		undomove(testmove);*/
		makemove(alphaBeta(globaldepth, 1000000, -1000000, "", 0));
		
		chess.theboard.repaint();
		
			
		}
	

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

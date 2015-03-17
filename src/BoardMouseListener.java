import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;
//I am here
public class BoardMouseListener implements MouseListener {
	static int globaldepth=1;
	public static Chess chess;
	//position of mouse
	public static int posx, posy, posxfinal, posyfinal;
	//boolean so white always moves first
	public static boolean whiteturn = true;

	
		//get King's location
    //while (!'a'.equals(chessBoard[kingPositionL/8][kingPositionL%8])) {kingPositionL++;}//get king's location
	
	public static String alphaBeta(int depth, int beta, int alpha, String move, int player) {
		String list=possiblemoves();
        if (depth==0 || list.length()==0) {return move+(Rating.rating(list.length(), depth)*(player*2-1));}
        list=sortMoves(list);
        player=1-player;//either 1 or 0
        for (int i=0;i<list.length();i+=5) {
            makemove(list.substring(i,i+5));
            flipboard();
            String returnString=alphaBeta(depth-1, beta, alpha, list.substring(i,i+5), player);
            int value=Integer.valueOf(returnString.substring(5));
            flipboard();
            undomove(list.substring(i,i+5));
            if (player==0) {
                if (value<=beta) {beta=value; if (depth==globaldepth) {move=returnString.substring(0,5);}}
            } else {
                if (value>alpha) {alpha=value; if (depth==globaldepth) {move=returnString.substring(0,5);}}
            }
            if (alpha>=beta) {
                if (player==0) {return move+beta;} else {return move+alpha;}
            }
        }
        if (player==0) {return move+beta;} else {return move+alpha;}
	}
	
	public static String sortMoves(String list) {
        int[] score=new int [list.length()/5];
        for (int i=0;i<list.length();i+=5) {
            makemove(list.substring(i, i+5));
            score[i/5]=-Rating.rating(-1, 0);
            undomove(list.substring(i, i+5));
        }
        String newListA="", newListB=list;
        for (int i=0;i<Math.min(6, list.length()/5);i++) {//first few moves only
            int max=-1000000, maxLocation=0;
            for (int j=0;j<list.length()/5;j++) {
                if (score[j]>max) {max=score[j]; maxLocation=j;}
            }
            score[maxLocation]=-1000000;
            newListA+=list.substring(maxLocation*5,maxLocation*5+5);
            newListB=newListB.replace(list.substring(maxLocation*5,maxLocation*5+5), "");
        }
        return newListA+newListB;
    }
	
	
	 public static String posibleA(int i) {
	        String list="", oldPiece;
	        int r=i/8, c=i%8;
	        for (int j=0;j<9;j++) {
	            if (j!=4) {
	                try {
	                    if (Character.isLowerCase(Chess.pieces[r-1+j/3][c-1+j%3].pieceletter.charAt(0)) || " ".equals(Chess.pieces[r-1+j/3][c-1+j%3].pieceletter)) {
	                        oldPiece=Chess.pieces[r-1+j/3][c-1+j%3].pieceletter;
	                        Chess.pieces[r][c].pieceletter=" ";
	                        Chess.pieces[r-1+j/3][c-1+j%3].pieceletter="A";
	                        int kingTemp=Chess.kingPositionC;
	                        Chess.kingPositionC=i+(j/3)*8+j%3-9;
	                        if (kingSafe()) {
	                            list=list+r+c+(r-1+j/3)+(c-1+j%3)+oldPiece;
	                        }
	                        Chess.pieces[r][c].pieceletter="A";
	                        Chess.pieces[r-1+j/3][c-1+j%3].pieceletter=oldPiece;
	                        Chess.kingPositionC=kingTemp;
	                    }
	                } catch (Exception e) {}
	            }
	        }
	        //need to add casting later
	        return list;
	    }
	public static String possiblemoves(){
		String themoves="";
		
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if(!(" ".equals(Chess.pieces[i][j].pieceletter)))
					for (int k=0; k<8; k++){
						for (int l=0; l<8; l++){
							if (Chess.pieces[i][j].canMove(j, i, l ,k)){
								String istring=Integer.toString(i);
								String jstring=Integer.toString(j);
								String kstring=Integer.toString(k);
								String lstring=Integer.toString(l);
								if (!" ".equals(Chess.pieces[k][l].pieceletter)){
									String newmove=istring+jstring+kstring+lstring+Chess.pieces[k][l].pieceletter;
									themoves+=newmove;	
								}
								else{
									String newmove=istring+jstring+kstring+lstring+" ";	
									themoves+=newmove;
								
								}
								
								
						}
					}
				}
			
				
				
			}
		}
		return themoves;
	}
	
	public static void flipboard(){
		
		 String temp="";
	        for (int i=0;i<32;i++) {
	            int r=i/8, c=i%8;
	            if (Character.isUpperCase(Chess.pieces[r][c].pieceletter.charAt(0))) {
	                temp=Chess.pieces[r][c].pieceletter.toLowerCase();
	                
	            } else {
	                temp=Chess.pieces[r][c].pieceletter.toUpperCase();
	            }
	            if (Character.isUpperCase(Chess.pieces[7-r][7-c].pieceletter.charAt(0))) {
	            		Chess.pieces[r][c].pieceletter=Chess.pieces[7-r][7-c].pieceletter.toLowerCase();
	            } else {
	            		Chess.pieces[r][c].pieceletter=Chess.pieces[7-r][7-c].pieceletter.toUpperCase();
	            }
	            Chess.pieces[7-r][7-c].pieceletter=temp;
	        }
	        int kingTemp=Chess.kingPositionC;
	        Chess.kingPositionC=63-Chess.kingPositionL;
	        Chess.kingPositionL=63-kingTemp;
	        //System.out.println(temp);
		
	}
	 public static void makemove(String move) {
	        if (move.charAt(4)!='P') {
	        		//System.out.println("move is"+move+Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3))+Character.getNumericValue(move.charAt(0))+Character.getNumericValue(move.charAt(1)));
	            Chess.pieces[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))].pieceletter=Chess.pieces[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))].pieceletter;
	            Chess.pieces[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))].pieceletter=" ";
	            if ("A".equals(Chess.pieces[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))].pieceletter)) {
	                Chess.kingPositionC=8*Character.getNumericValue(move.charAt(2))+Character.getNumericValue(move.charAt(3));
	            }
	        } else {
	            //if pawn promotion
	        		Chess.pieces[1][Character.getNumericValue(move.charAt(0))].pieceletter=" ";
	        		Chess.pieces[0][Character.getNumericValue(move.charAt(1))].pieceletter=String.valueOf(move.charAt(3));
	        }
	    }

	 public static void undomove(String move) {
	        if (move.charAt(4)!='P') {
	            Chess.pieces[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))].pieceletter=Chess.pieces[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))].pieceletter;
	            Chess.pieces[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))].pieceletter=String.valueOf(move.charAt(4));
	            if ("A".equals(Chess.pieces[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))].pieceletter)) {
	                Chess.kingPositionC=8*Character.getNumericValue(move.charAt(0))+Character.getNumericValue(move.charAt(1));
	            }
	        } else {
	            //if pawn promotion
	        		Chess.pieces[1][Character.getNumericValue(move.charAt(0))].pieceletter="P";
	        		Chess.pieces[0][Character.getNumericValue(move.charAt(1))].pieceletter=String.valueOf(move.charAt(2));
	        }
	    }
	
	public static void MakeMove2(String move){
		Chess.pieces[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]
		=Chess.pieces[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
		Chess.pieces[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=new BlankPiece();
	}
	
	public static boolean kingSafe(){
		int temp=1;
		for(int i=-1;i<=1;i+=2){
			for(int j=-1;j<=1;j+=2){
				try{
					while(" ".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8+temp*j].pieceletter)){
						temp++;
					}
					if("b".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8+temp*i].pieceletter)
							||
                            "q".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8+temp*j].pieceletter)){
						return false;
					}
				} catch(Exception e){}
				temp=1;
			}
		}
		
		for(int i=-1;i<=1;i+=2){
			try{
				while(" ".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8+temp*i].pieceletter)){
					temp++;
				}
				if("r".equals(Chess.pieces[Chess.kingPositionC/8][Chess.kingPositionC%8+temp*i].pieceletter) ||
                        "q".equals(Chess.pieces[Chess.kingPositionC/8][Chess.kingPositionC%8+temp*i].pieceletter)){
					return false;
					
				}
			} catch (Exception e){}
			temp=1;
			try{
				while(" ".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8+temp*i].pieceletter)){
					temp++;
				}
				if("r".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8].pieceletter) ||
                        "q".equals(Chess.pieces[Chess.kingPositionC/8+temp*i][Chess.kingPositionC%8].pieceletter)){
					return false;
				}	
			} catch(Exception e){}
			temp=1;
		}
		 for (int i=-1; i<=1; i+=2) {
	            for (int j=-1; j<=1; j+=2) {
	                try {
	                    if ("k".equals(Chess.pieces[Chess.kingPositionC/8+i][Chess.kingPositionC%8+j*2].pieceletter)) {
	                        return false;
	                    }
	                } catch (Exception e) {}
	                try {
	                    if ("k".equals(Chess.pieces[Chess.kingPositionC/8+i*2][Chess.kingPositionC%8+j].pieceletter)) {
	                        return false;
	                    }
	                } catch (Exception e) {}
	            }
	        }
		 if (Chess.kingPositionC>=16) {
	            try {
	                if ("p".equals(Chess.pieces[Chess.kingPositionC/80-1][Chess.kingPositionC%8-1].pieceletter)) {
	                    return false;
	                }
	            } catch (Exception e) {}
	            try {
	                if ("p".equals(Chess.pieces[Chess.kingPositionC/80-1][Chess.kingPositionC%8+1].pieceletter)) {
	                    return false;
	                }
	            } catch (Exception e) {}
	            //king
	            for (int i=-1; i<=1; i++) {
	                for (int j=-1; j<=1; j++) {
	                    if (i!=0 || j!=0) {
	                        try {
	                            if ("a".equals(Chess.pieces[Chess.kingPositionC/8+i][Chess.kingPositionC%8+j].pieceletter)) {
	                                return false;
	                            }
	                        } catch (Exception e) {}
	                    }
	                }
	            }
	        }
			
			
		return true;
	}
	
	
	
	public BoardMouseListener(Chess chess) {
		this.chess = chess;
	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		//equates initial mouse/piece position with tile position on board
		if (e.getX()<8*62 &&e.getY()<8*62) {
			posx = e.getX() / 62;
			posy = e.getY() / 62;
			System.out.println("valid press");
		}

	}
	public void MakeHumanMove(){
		
			if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
				//checks to see if piece can move based on initial and final coordinates, this version also only allows white to move since it is the first turn
				if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)){
					System.out.println("Piece can move");
					if(chess.pieces[posy][posx].isWhite == true) {
					//moves piece
					System.out.println("Piece is white");
					chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
					//conditional prevents user from deleteing piece of mouse is clicked and release on the same position
					if (posy != posyfinal || posx != posxfinal) {
						//deletes old piece position
						chess.pieces[posy][posx] = null;
						//ends first move/whites beginning turn
						whiteturn = false;
					}
					//repaints board with new piece positions
					chess.theboard.repaint();
				}
			}
			}
		
	//conditionals for all turns after first turn
	else{
		//makes it so user can click and release on empty square without null pointer exception
		if(whiteturn)	
			if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
				//checks if piece can move
				if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)) {
					//changes position
					chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
					//prevents user from accidentally deleting piece by clicking and releasing on same piece
					if (posy != posyfinal || posx != posxfinal) {
						//deletes old piece position
						chess.pieces[posy][posx] = null;
						whiteturn=false;
					}
					//repaints board��
					chess.theboard.repaint();
				}
			}
	}
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		//equates final mouse/piece position with tile position on board
		if (e.getX()<8*62 &&e.getY()<8*62) {
			posxfinal = e.getX() / 62;
			posyfinal = e.getY() / 62;
			System.out.println("valid release");
		}
		
	
			
		
            String dragMove;
            if (posyfinal==0 && posy==1 && "P".equals(Chess.pieces[posy][posx].pieceletter)) {
                //pawn promotion
                dragMove=""+posx+posxfinal+Chess.pieces[posyfinal][posxfinal].pieceletter+"QP";
            } else {
                //regular move
                dragMove=""+posy+posx+posyfinal+posxfinal+Chess.pieces[posyfinal][posxfinal].pieceletter;
                //System.out.println("regular drag move = "+ dragMove);
            }
            for(int i=0;i<8;i++)
    			for(int j=0;j<8;j++){				
    					System.out.print(Chess.pieces[i][j].pieceletter);
    					if(j==7){
    						System.out.println();
    			}
    			}
            System.out.println("dragMove = "+dragMove);
            String userPosibilities=possiblemoves();
            System.out.println("User Possibilities = "+userPosibilities);
            System.out.println("Piece '"+Chess.pieces[posy][posx].pieceletter+ "' is White="+Chess.pieces[posy][posx].isWhite+" at " + posy +" "+posx+" can move "+Chess.pieces[posy][posx].canMove(posx, posy, posxfinal, posyfinal));
            String compmove;
            if (userPosibilities.replaceAll(dragMove, "").length()<userPosibilities.length()) {
            		System.out.println("valid move");
                MakeMove2(dragMove);
                chess.theboard.repaint();
                flipboard();
                compmove=alphaBeta(globaldepth, 1000000, -1000000, "", 0);
                System.out.println("Computer move = "+compmove+" can move = "+Chess.pieces[Character.getNumericValue(compmove.charAt(0))][Character.getNumericValue(compmove.charAt(1))].canMove(Character.getNumericValue(compmove.charAt(0)), Character.getNumericValue(compmove.charAt(1)), Character.getNumericValue(compmove.charAt(2)), Character.getNumericValue(compmove.charAt(3))));
                MakeMove2(compmove);  
                flipboard();
                chess.theboard.repaint();
            }
            for(int i=0;i<8;i++)
    			for(int j=0;j<8;j++){				
    					System.out.print(Chess.pieces[i][j].pieceletter);
    					if(j==7){
    						System.out.println();
    			}
    			}
            
            
        
		
		
			
			
			
		
				
	}

		
			
		
	

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

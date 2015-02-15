import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class BoardMouseListener implements MouseListener {
	static int globaldepth=4;
	public static String alphaBeta(int depth, int beta, int alpha, String move, int player ) {
		String list= possiblemoves();
		//String list="1";
		if (depth==0 || list.length()==0){
			return move+rating() *((player*2)-1);
			
		}
		//sort with be here
		list="";
		System.out.print("How many moves are there: ");
		Scanner sc=new Scanner(System.in);
		int temp=sc.nextInt();
		for (int i=0; i<temp; i++){
			list="1111b";
		}
		
		
		player=1-player;
		for (int i=0; i<list.length(); i+=5){
		
			
			makemove(list.substring(i, i+5));
			flipboard();
			String returnstring= alphaBeta(depth-1, beta, alpha, list.substring(i, +i+5), player);
			int value= Integer.valueOf(returnstring.substring(5));
			flipboard();
			undomove(list.substring(i,i+5));
			if (player==0){
				if (value<=beta){
					beta=value;
					if (depth==globaldepth){
						move=returnstring.substring(0,5);
						
					}
				}
			}
			else{
				if (value>=alpha){
					alpha=value;
					if (depth==globaldepth){
						move=returnstring.substring(0,5);
						
					}
				}
				
			}
			if (alpha>=beta){
				if (player==0){
					return move+beta;
				}
				else{
					return move+alpha;
				}
			}
		}
	
		if (player==0){
			return move+beta;
			}
		else{
			return move+alpha;
			}
			
		
		
		
	
	}
	public static String possiblemoves(){
		String themoves="";
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
								if (Chess.pieces[l][k]!=null){
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
		System.out.print("What is the score: ");
		Scanner sc=new Scanner(System.in);
		return sc.nextInt();
		
	}
	public static void flipboard(){
		
	}
	public static void makemove(String moveinfo){
		
	}
	public static void undomove(String undomovinfo){
		
	}
	Chess chess;
	//position of mouse
	int posx, posy, posxfinal, posyfinal;
	//boolean so white always moves first
	boolean whiteturn = true;

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
		//conditional checking to see if it's the first turn so white moves first
		if (whiteturn == true) {
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
						//ends first move/whites beginning turn
						whiteturn = false;
					}
					//repaints board with new piece positions
					chess.theboard.repaint();
					
					String test= possiblemoves();
					System.out.println(test);
					
					/*for (int i=0; i<100; i++){
						if (test[i]!=null){
							System.out.println(test[i]);
						}
						
					}*/
					//System.out.println(possiblemoves());
				
					/*if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
						//checks if piece can move
						System.out.println(chess.pieces[posy][posx]);
						if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)) {
							//changes position
							chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
							//prevents user from accidentally deleting piece by clicking and releasing on same piece
							if (posy != posyfinal || posx != posxfinal) {
								//deletes old piece position
								chess.pieces[posy][posx] = null;
							}
							//repaints board
							chess.theboard.repaint();
							//insert ai here
							//System.out.println(possiblemoves());
							
					}
					}*/
				}
			}
		}
		//conditionals for all turns after first turn
		else{
			//makes it so user can click and release on empty square without null pointer exception
			if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
				//checks if piece can move
				if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)) {
					//changes position
					chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
					//prevents user from accidentally deleting piece by clicking and releasing on same piece
					if (posy != posyfinal || posx != posxfinal) {
						//deletes old piece position
						chess.pieces[posy][posx] = null;
					}
					//repaints boardå
					chess.theboard.repaint();
					
					String test= possiblemoves();
					System.out.println(test);
					
					/*for (int i=0; i<100; i++){
						if (test[i]!=null){
							System.out.println(test[i]);
						}
						
					}*/
					//insert ai here
					
					/*if (Math.abs(posxfinal - posx) != 0|| Math.abs(posyfinal - posy) != 0) {
						//checks if piece can move
						if (chess.pieces[posy][posx].canMove(posx, posy, posxfinal,posyfinal)) {
							//changes position
							chess.pieces[posyfinal][posxfinal] = chess.pieces[posy][posx];
							//prevents user from accidentally deleting piece by clicking and releasing on same piece
							if (posy != posyfinal || posx != posxfinal) {
								//deletes old piece position
								chess.pieces[posy][posx] = null;
							}
							//repaints board
							chess.theboard.repaint();
					}
					}*/
				}
			
		
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

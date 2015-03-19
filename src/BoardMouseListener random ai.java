import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BoardMouseListener implements MouseListener {
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
					int count=0;
					posx=(int) (Math.random()*8);
					posy=(int) (Math.random()*8);
					posxfinal=(int) (Math.random()*8);
					posyfinal=(int) (Math.random()*8);
					//System.out.println(posx+","+posy+" to"+posxfinal+","+posyfinal+ "outside while loop");
					while (chess.pieces[posy][posx]==null || chess.pieces[posy][posx].canMove(posx, posy, posxfinal, posyfinal)==false || chess.pieces[posy][posx].isWhite || (Math.abs(posxfinal - posx) == 0 && Math.abs(posyfinal - posy) == 0)){
						count++;
						if (count>10000){
							System.exit(0);
						}
						posx=(int) (Math.random()*8);
						posy=(int) (Math.random()*8);
						posxfinal=(int) (Math.random()*8);
						posyfinal=(int) (Math.random()*8);
						//System.out.println(posx+","+posy+" to"+posxfinal+","+posyfinal+" inside while loop");
					}
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
							//repaints board
							chess.theboard.repaint();
					}
					}
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
					int count=0;
					posx=(int) (Math.random()*8);
					posy=(int) (Math.random()*8);
					posxfinal=(int) (Math.random()*8);
					posyfinal=(int) (Math.random()*8);
					//System.out.println(posx+","+posy+" to"+posxfinal+","+posyfinal+ "outside while loop");
					while (chess.pieces[posy][posx]==null || chess.pieces[posy][posx].isWhite || chess.pieces[posy][posx].canMove(posx, posy, posxfinal, posyfinal)==false || (Math.abs(posxfinal - posx) == 0 && Math.abs(posyfinal - posy) == 0)){
						count++;
						if (count>10000){
							System.exit(0);
						}
						posx=(int) (Math.random()*8);
						posy=(int) (Math.random()*8);
						posxfinal=(int) (Math.random()*8);
						posyfinal=(int) (Math.random()*8);
						//System.out.println(posx+","+posy+" to"+posxfinal+","+posyfinal+" inside while loop");
					}
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
							//repaints board
							chess.theboard.repaint();
					}
					}
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

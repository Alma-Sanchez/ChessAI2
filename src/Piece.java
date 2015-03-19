import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;

public abstract class Piece extends JComponent {
	ImageIcon img;
	//abstract boolean determining of piece can move
	public abstract boolean canMove(int x, int y, int finx, int finy);
	//not used boolean
	public abstract boolean wasPressed(int x,int y);
	//boolean to determing color
	public boolean isWhite;
	//draw pieces
	public void drawPiece(Graphics g, int x, int y){
		if(img!=null)
			g.drawImage(img.getImage(), x*62, y*62, null);
	}
	void drawPiece() {
		// TODO Auto-generated method stub
	}
	public String pieceletter;
}
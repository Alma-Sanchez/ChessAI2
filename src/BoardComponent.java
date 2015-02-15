import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JComponent;
public class BoardComponent extends JComponent {
	Chess chess;

	public BoardComponent(Chess chess){
		
		this.chess=chess;

		
	}
	public void paintComponent(Graphics g){
		//draws array of board tiles 8X8
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
			chess.boardTiles[i][j].draw(g);
			}
		}
		// goes through array of board tiles and looks for the correct places to draw pieces
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				if (chess.pieces[i][j]!=null)
				{
					chess.pieces[i][j].drawPiece(g,j,i);
				}
			}
		}
	}


}

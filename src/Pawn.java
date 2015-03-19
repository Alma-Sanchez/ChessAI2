import javax.swing.ImageIcon;

public class Pawn extends Piece {
	int x, y;
	Chess chess;
	// move boolean to determine if pawn can move forward two spots
	boolean moved = false;

	public boolean wasPressed(int clickx, int clicky) {
		return false;
	}

	public boolean canMove(int x, int y, int finx, int finy) {
		// movement conditionals for white pawns
		if (chess.pieces[y][x].isWhite) {	
			if (y==6) {
				// vertical movement
				if (((x - finx) == 0) && ((y - finy) <= 2 && (y - finy) > 0)
						&& (" ".equals(chess.pieces[y - 1][x].pieceletter))
						&& (" ".equals(chess.pieces[finy][finx].pieceletter))) {
					//this.moved = true;
					//System.out.println("Pawn should move two spaces");
					return true;
					// allows pawn to take piece of opposite color even if
					// hasn't been moved yet
				} else if (((!(" ".equals(chess.pieces[finy][finx].pieceletter))) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(x - finx) == 1) && ((y - finy) == 1)) {
					//System.out.print("allows pawn to take piece of opposite color even if");
					//chess.pieces[finy][finx] = new BlankPiece();
					return true;

				}
				return false;
			}
			// conditionals for white pawns that have been moved
			else {
				// vertical movement
				if (((x - finx) == 0) && ((y - finy) == 1)
						&& (" ".equals(chess.pieces[finy][finx].pieceletter))) {
					//System.out.print("vertical movement");
					return true;
					// allows for taking of pieces diagonally
				} else if (((!(" ".equals(chess.pieces[finy][finx].pieceletter))) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(x - finx) == 1) && ((y - finy) == 1)) {

					//chess.pieces[finy][finx] = new BlankPiece();
					return true;

				} else {
					return false;
				}

			}
		}
		// conditional statements for black pawn movement
		else {
			// if black pawn hasn't moved yet
			if (y==1) {
				// vertical movement
				if (((finx - x) == 0) && ((finy - y) <= 2 && (finy - y) > 0)
						&& (" ".equals(chess.pieces[y + 1][x].pieceletter))
						&& (" ".equals(chess.pieces[y + 2][x].pieceletter))) {
					this.moved = true;
					return true;
					// diagional movement when taking another piece
				} else if (((!(" ".equals(chess.pieces[finy][finx].pieceletter))) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(finx - x) == 1) && ((finy - y) == 1)) {
					//chess.pieces[finy][finx] = new BlankPiece();
					this.moved=true;
					return true;
				}
				//this.moved=false;
				return false;
			}
			// if pawn has moved
			else {
				// vertical movement
				if (((finx - x) == 0) && ((finy - y) == 1)
						&& (" ".equals(chess.pieces[finy][finx].pieceletter))) {
					return true;
					// diagonal movement if pawn is taking another piece
				} else if (((!(" ".equals(chess.pieces[finy][finx].pieceletter))) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(finx - x) == 1)
						&& (Math.abs(finy - y) == 1)) {
					//chess.pieces[finy][finx] = new BlankPiece();
					return true;
				} else {
					return false;
				}
			}
		}
	}

	public Pawn(boolean isWhite, Chess chess) {
		// determines pawn color
		this.isWhite = isWhite;
		this.chess = chess;
		if (isWhite) {
			img = new ImageIcon("wpawn.gif");
			pieceletter="P";

		} else {
			img = new ImageIcon("bpawn.gif");
			pieceletter="p";
		}
	}

	@Override
	void drawPiece() {
	}
}

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
			// movement conditional for white pawn upon first movement
			//System.out.println("moved "+moved+","+x+","+y+","+finx+","+finy);
			if (y==6) {
				// vertical movement
				if (((x - finx) == 0) && ((y - finy) <= 2 && (y - finy) > 0)
						&& chess.pieces[y - 1][x] == null
						&& chess.pieces[finy][finx] == null) {
					this.moved = true;
					//System.out.print("Pawn should move two spaces");
					return true;
					// allows pawn to take piece of opposite color even if
					// hasn't been moved yet
				} else if (((chess.pieces[finy][finx] != null) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(x - finx) == 1) && ((y - finy) == 1)) {
					chess.pieces[finy][finx] = null;
					return true;

				}
				return false;
			}
			// conditionals for white pawns that have been moved
			else {
				// vertical movement
				if (((x - finx) == 0) && ((y - finy) == 1)
						&& chess.pieces[finy][finx] == null) {

					return true;
					// allows for taking of pieces diagonally
				} else if (((chess.pieces[finy][finx] != null) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(x - finx) == 1) && ((y - finy) == 1)) {

					chess.pieces[finy][finx] = null;
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
						&& chess.pieces[y + 1][x] == null
						&& chess.pieces[y + 2][x] == null) {
					this.moved = true;
					return true;
					// diagional movement when taking another piece
				} else if (((chess.pieces[finy][finx] != null) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(finx - x) == 1) && ((finy - y) == 1)) {
					chess.pieces[finy][finx] = null;
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
						&& chess.pieces[finy][finx] == null) {
					return true;
					// diagonal movement if pawn is taking another piece
				} else if (((chess.pieces[finy][finx] != null) && (chess.pieces[y][x].isWhite != chess.pieces[finy][finx].isWhite))
						&& (Math.abs(finx - x) == 1)
						&& (Math.abs(finy - y) == 1)) {
					chess.pieces[finy][finx] = null;
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
		// TODO Auto-generated method stub

	}

}

import javax.swing.ImageIcon;

public class Queen extends Piece {

	public Queen(boolean isWhite, Chess chess) {
		// TODO Auto-generated constructor stub
		this.isWhite = isWhite;
		if (isWhite) {
			img = new ImageIcon("wqueen.gif");
			pieceletter="Q";

		} else {
			img = new ImageIcon("bqueen.gif");
			pieceletter="q";
		}
	}

	@Override
	void drawPiece() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canMove(int x, int y, int finx, int finy) {
		// TODO Auto-generated method stub
		//negative y movement
		if ((y - finy) > 0 && (x - finx) == 0) {
			//scans spaces between start and finish for obstructing pieces
			for (int i = 1; i < Math.abs(y - finy); i++) {
				if (!(" ".equals(Chess.pieces[y - i][x].pieceletter))) {
					return false;

				}

			}
			//checks to see if final piece is either null or has a takeable pieces
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
		}
		// positive movement y direction
		else if ((y - finy) < 0 && (x - finx) == 0) {
			//space scanner
			for (int i = 1; i < Math.abs(y - finy); i++) {
				if (!(" ".equals(Chess.pieces[y + i][x].pieceletter))) {
					return false;

				}

			}
			//checks to see if final piece is either null or has a takeable pieces
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
		}
		// negative x movement
		else if ((x - finx) > 0 && (y - finy) == 0) {
			//space scanner
			for (int i = 1; i < Math.abs(x - finx); i++) {
				if (!(" ".equals(Chess.pieces[y][x - i].pieceletter))) {
					return false;

				}

			}
			//checks to see if final space is either null or has a takeable piece
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
		}
		// positive x movement
		else if ((x - finx) < 0 && (y - finy) == 0) {
			//position scanner
			for (int i = 1; i < Math.abs(x - finx); i++) {
				if (!(" ".equals(Chess.pieces[y][x + i].pieceletter))) {
					return false;

				}

			}
			//checks final space is either null or has a takeable piece
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
			//negative x movement
		} else if (((x - finx) > 0 && (y - finy) > 0)
				&& Math.abs(x - finx) == Math.abs(y - finy)) {
				//position scanner
			for (int i = 1; i < Math.abs(x - finx); i++) {
				if (!(" ".equals(Chess.pieces[y - i][x - i].pieceletter))) {
					return false;

				}

			}
			//checks if final space is either null or has a takeable piece
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
			//positive y,x diagonal movement
		} else if (((x - finx) < 0 && (y - finy) < 0)
				&& Math.abs(x - finx) == Math.abs(y - finy)) {
			//space scanner
			for (int i = 1; i < Math.abs(x - finx); i++) {
				if (!(" ".equals(Chess.pieces[y + i][x + i].pieceletter))) {
					return false;

				}

			}
			//final space checker
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
			//positive x, negative y diagonal
		} else if (((x - finx) < 0 && (y - finy) > 0)
				&& Math.abs(x - finx) == Math.abs(y - finy)) {
			//space scanner
			for (int i = 1; i < Math.abs(x - finx); i++) {
				if (!(" ".equals(Chess.pieces[y - i][x + i].pieceletter))) {
					return false;

				}

			}
			//final space checker
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}
			//negative x, positive y diagonal movement
		} else if (((x - finx) > 0 && (y - finy) < 0)
				&& Math.abs(x - finx) == Math.abs(y - finy)) {
			//space checker
			for (int i = 1; i < Math.abs(x - finx); i++) {
				if (!(" ".equals(Chess.pieces[y + i][x - i].pieceletter))) {
					return false;

				}

			}
			//final space checker
			if ((" ".equals(Chess.pieces[finy][finx].pieceletter))
					|| Chess.pieces[finy][finx].isWhite != Chess.pieces[y][x].isWhite) {
				return true;
			}

		}
		return false;
	}

	@Override
	public boolean wasPressed(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

}

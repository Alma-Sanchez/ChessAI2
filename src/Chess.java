import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Chess{
	//window size
	static final int WINDOWHEIGHT=516, WINDOWLENGTH=496;
	//tile array
	BoardTile[][] boardTiles;
	//piece array
	static Piece [][] pieces;
	//defining of board component
	BoardComponent theboard;
	//defining of mouse listener
	BoardMouseListener bml;
	public static int kingPositionC, kingPositionL;
	public Chess(){
		//Jframe creation
		JFrame chess;
		chess= new JFrame("board");
		chess.setSize(WINDOWLENGTH,WINDOWHEIGHT);
		//creation of new component and mouse listener to chess
		theboard=new BoardComponent(this);
		bml=new BoardMouseListener(this);
		
		//adding component to chess
		chess.add(theboard);
		//adding the listener to the component
		theboard.addMouseListener(bml);
		//creating a new tile array
		boardTiles=new BoardTile[8][8]; 
		//creating tiles in the 2d array
		for (int i=0; i<8; i++){
			for (int j=0; j<8; j++){
				boardTiles[i][j]=new BoardTile();
			}
		}
		//creating 2d piece array
		pieces=new Piece[8][8];
		for (int i=0; i<8; i++){
			if (i==0){
				//placing pieces with proper colors in correct starting locations, this creates the black back row
				for (int j=0; j<8; j++){
					if (j==0 || j==7){
						pieces [i][j]=new Rook(false,this);
					}
					if (j==1 || j==6){
						pieces [i][j]=new Knight(false,this); 
					}
					if (j==2 || j==5){
						pieces [i][j]=new Bishop(false,this);
					}
					if (j==3){
						pieces [i][j]=new Queen (false,this);
					}
					if (j==4){
						pieces [i][j]=new King (false,this);
					}
				}
			}
			else if (i==1)
			{
				//creates black pawns
				for (int j=0; j<8; j++){
					pieces[i][j]=new Pawn(false,this);
				}
			}
			else if (i==2||i==3||i==4||i==5){
				for (int j=0;j<8;j++)
					pieces[i][j]= new BlankPiece();
			}
			else if (i==6){
				//creates white pawns
				for (int j=0; j<8; j++){
					pieces[i][j]=new Pawn(true,this);
				}
				}
			else if (i==7){
				//creates white back row
					for (int j=0; j<8; j++){
						if (j==0 || j==7){
							pieces [i][j]=new Rook(true,this);
						}
						if (j==1 || j==6){
							pieces [i][j]=new Knight(true,this); 
						}
						if (j==2 || j==5){
							pieces [i][j]=new Bishop(true,this);
						}
						if (j==3){
							pieces [i][j]=new Queen (true,this);
						}
						if (j==4){
							pieces [i][j]=new King (true,this);
						}
					}
				
			}
		}
		//sets the windows visible and ends program if the window is closed
		chess.setVisible(true);
		chess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}


	public static void main(String[] args) {
		//creates chess
		new Chess();
		String userPosibilities=BoardMouseListener.possiblemoves();
        System.out.println("User Possibilities = "+userPosibilities);
		for(int i=0;i<8;i++)
			for(int j=0;j<8;j++){
				if(Chess.pieces[i][j]!=null)
					System.out.println(Chess.pieces[i][j].pieceletter);
			}
		//BoardMouseListener.flipboard();
		//searches for the kings in the array and stores there positions in two separate integers.
		while (/*(kingPositionC/8<8 && kingPositionC%8<8)&&*/ !"A".equals(pieces[kingPositionC/8][kingPositionC%8].pieceletter))
		{
			//System.out.println("A".equals(pieces[kingPositionC/8][kingPositionC%8].pieceletter));
			if("A".equals(pieces[kingPositionC/8][kingPositionC%8].pieceletter)){
				System.out.println("FOUND KING!!!!");
			}
			kingPositionC++;	
		}	
        while (/*(kingPositionL/8<8 && kingPositionL%8<8)&&*/ !"a".equals(pieces[kingPositionL/8][kingPositionL%8].pieceletter)) {
				kingPositionL++;
        }	
        System.out.println("kingPositionC = "+kingPositionC+" kingPositionL = "+kingPositionL);	
	}
}

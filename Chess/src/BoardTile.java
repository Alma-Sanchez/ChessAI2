import java.awt.Color;
import java.awt.Graphics;


public class BoardTile implements Runnable {
	//defines colors of dark and light tiles
	Color darktile = new Color(233,174,95);
	Color lighttile = new Color(177,113,24);
	
	public void draw(Graphics g)
	{
		//System.out.println("paint component was called");
		

		//for loops that goes through tile array and colors the tiles in a pattern that resembles a chess board.
		for(int i=0; i<8; i++)
		{
			if (i%2==0){
				for (int j=0; j<8; j++){
				if(j%2==0){
					g.setColor(darktile);
					g.fillRect(0+(j*62), 0+(i*62), 62, 62);
				}
				else {
					g.setColor(lighttile);
					g.fillRect(0+(j*62), 0+(i*62), 62, 62);
				}
			}
			}

			
			else if(i%2!=0){
				for (int j=0; j<8; j++){
					if (j%2!=0){
						g.setColor(darktile);
						g.fillRect(0+(j*62), 0+(i*62), 62, 62);
					}
				else
				{
					g.setColor(lighttile);
					g.fillRect(0+(j*62), 0+(i*62), 62, 62);
				}
			}
			}
			}
			
			
		}
	

	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}

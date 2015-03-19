import java.util.Scanner;



public class AItest {
	static int globaldepth=4;
	public static void Main(String[] args){
		System.out.println(alphaBeta(globaldepth,1000000,-1000000, "", 0));
	}

	public static String alphaBeta(int depth, int beta, int alpha, String move, int player ) {
		//String list= possiblemoves();
		String list="1";
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
		return null;
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
}

	

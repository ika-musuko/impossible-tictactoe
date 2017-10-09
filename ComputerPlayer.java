public class ComputerPlayer {
	private char piece;
	private char other;
	private int[] plays = {5, 1, 3, 7, 9, 2, 4, 6, 8};
	private boolean[] usethis;
	private final static int [][] winningCombos = 
	{	{1,2,3}, {4,5,6}, 
		{7,8,9}, {1,4,7}, 
		{2,5,8}, {3,6,9},
		{1,5,9}, {3,5,7}
	};
	
	private final static int[][] diag = {
		{1,9}, {3,7}
	};
	
	public ComputerPlayer(char c, Board board){
		piece = c;
		other = (c == board.NOUGHT) ? board.CROSS : board.NOUGHT;
	}
	
	public int take_turn(Board board){
		System.out.println("computer's turn!");
		int sp;
		int p = 0;
		
		// try to win
		System.out.println("ttw");
		for(int i = 0; i < winningCombos.length; ++i){
			for(int j = 0; j < 3; ++j){
				if(board.getSquare(winningCombos[i][j%3]) == piece && board.getSquare(winningCombos[i][(j+1)%3]) == piece){
					sp = winningCombos[i][(j+2)%3];
					if(board.isFreeSquare(sp)) return sp;
				}
			}
		}
	
		// block obvious wins
		System.out.println("blok");
		for(int i = 0; i < winningCombos.length; ++i){
			for(int j = 0; j < 3; ++j){
				if(board.getSquare(winningCombos[i][j%3]) == other && board.getSquare(winningCombos[i][(j+1)%3]) == other){
					sp = winningCombos[i][(j+2)%3];
					if(board.isFreeSquare(sp)) return sp;
				}
			}
		}
		
		// block the corner trick
		for(int i = 0; i < diag.length; ++i){
			if(board.getSquare(diag[i][0]) == other && board.getSquare(diag[i][1]) == other)
			{
				System.out.println("no tricks allowed!");
				p = 5;
				break;
			}	
			
		}
		
		// can't do either
		System.out.println(": (");
		for(; !board.isFreeSquare(plays[p]) && p < 19; ++p);
		return plays[p%9];
	}
}

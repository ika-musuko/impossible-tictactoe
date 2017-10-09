
public class Board {

	private char [] squares = new char[9];
	private final static int [][] winningCombos = 
		{	{0,1,2}, {3,4,5}, 
			{6,7,8}, {0,3,6}, 
			{1,4,7}, {2,5,8},
			{0,4,8}, {2,4,6}
		};
	
	private int moveCount = 0;
	public final static char BLANK = '_', CROSS = 'X', NOUGHT = 'O';
	
	public Board() {
		for (int i = 1; i <= 9; i++) {
			setSquare(i,BLANK);
		}
	}
	
	public void reset(){
		for (int i = 1; i <= 9; i++) {
			setSquare(i,BLANK);
		}
		moveCount = 0;
	}
	
	public void setSquare(int squareNumber, char c) {
		squares[squareNumber-1] = c;
	}
	
	public void setSquare(int row, int column, char c) {
		setSquare((row-1)*3 + column, c);
	}
	
	public char getSquare(int squareNumber) {
		return squares[squareNumber-1];
	}
	public char getSquare(int row, int column) {
		return getSquare((row-1)*3 + column);
	}
	
	public boolean isXTurn() {
		return moveCount % 2 == 0;
	}
	public boolean isOTurn() {
		return moveCount % 2 == 1;
	}
	public boolean isWin(char which) {
		for (int i = 0; i< winningCombos.length; i++) {
			boolean possibleWin = true;
			for (int j = 0; j < winningCombos[i].length && possibleWin; j++)
				if (squares[winningCombos[i][j]] != which)
					possibleWin = false;
			if (possibleWin)
				return true;
		}
		return false;		
	}
	
	public boolean isBoardFull() {
		return moveCount == 9;
	}
	
	public boolean isCatsGame() {
		return isBoardFull() && !((isXWin()) ||  isOWin());
	}
	
	public boolean isXWin() {
		return isWin(CROSS);
	}
	public boolean isOWin() {
		return isWin(NOUGHT);
	}
	

	
	public boolean isFreeSquare(int row, int column) {
	
		return getSquare(row,column) == BLANK;
	}
	
	public boolean isFreeSquare (int squareNumber) {
		return getSquare(squareNumber) == BLANK;
	}
	// square number is a number from 1 to 9
	public boolean moveToSquare(int squareNumber) {
		if (!isFreeSquare(squareNumber))
			return false;
		setSquare(squareNumber,isXTurn() ? CROSS : NOUGHT);
		moveCount++;
		return true;
	}
	
	public boolean moveToSquare(int row, int column) {
		return moveToSquare((row-1)*3 + column);
	}

	
	public String toString() {
		String s = "";
		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++)
				s += (getSquare(i,j) + " ");
			s += '\n';
		}
		return s;
	}
	
	
	public static void main(String [] args) {
		Board b = new Board();

		b.moveToSquare(1, 1);
		b.moveToSquare(1, 2);
		b.moveToSquare(2, 2);
		b.moveToSquare(3, 3);	
		b.moveToSquare(1, 3);
		b.moveToSquare(2, 3);
		b.moveToSquare(3, 1);
		System.out.println(b);
		System.out.println(b.isXWin());
		System.out.println("DONE");
		
	}
	
	
	
	
	
	
	

}

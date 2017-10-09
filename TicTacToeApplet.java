
import java.applet.*;
import java.awt.font.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.JOptionPane;
public class TicTacToeApplet extends Applet  implements ActionListener {

	public Board board = new Board();
	public Panel 	boardPanel = new Panel(),
					controlPanel = new Panel();
	public int xscore = 0, oscore = 0, catsgame = 0;
	public Label xlab = new Label("X: 0"), olab = new Label("O: 0"), clab = new Label("CAT: 0");
	public Label lab = new Label("Game ON!");
	public SquareButton [] squareButtons =  new SquareButton[9];
	public ComputerPlayer cp = new ComputerPlayer(board.NOUGHT, board);
	public boolean playable = true;
	
	public void init() {
		BorderLayout layout1 = new BorderLayout();
		this.setLayout(layout1);
		this.add(boardPanel, BorderLayout.CENTER);
		this.add(controlPanel, BorderLayout.SOUTH);
		boardPanel.setLayout(new GridLayout(3,3,5,5));
		for (int i = 0; i < 9; i++) {
			squareButtons[i] = new SquareButton(i+1);
			boardPanel.add(squareButtons[i]);	
		}
		controlPanel.add(lab);
		controlPanel.add(xlab); 
		controlPanel.add(olab); 
		controlPanel.add(clab); 

	}
	
	public void update_button(SquareButton theButton, int squareNum){
		theButton.setLabel(""+board.getSquare(squareNum));
	}
	
	public boolean check_board_state(Board board){
		if (board.isXWin()){
			lab.setText("X WINS!!");
			++xscore;
			return true;
		}
		if (board.isOWin()){
			lab.setText("O WINS!!");
			++oscore;
			return true;
		}
		if (board.isCatsGame()){
			lab.setText("CAT'S GAME");
			++catsgame;
			return true;
		}
		else return false;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (!playable){
			board.reset();
			for(int i = 0; i < 9; ++i){
				update_button(squareButtons[i], i+1);
			}
			playable = true;
			lab.setText("Game ON!");
			xlab.setText("X: "+xscore);
			olab.setText("O: "+oscore);
			clab.setText("CAT: "+catsgame);
			return;
		}
		
		SquareButton theButton = (SquareButton) e.getSource();
		int squareNum = theButton.ID;
		if (!board.moveToSquare(squareNum)) {
			JOptionPane.showMessageDialog(getParent(), "Square taken!");
			return;
		}
		
		System.out.println("your turn: "+squareNum);
		update_button(theButton, squareNum);
		
		if(check_board_state(board)){
			playable = false;
			return;
		}
		squareNum = cp.take_turn(board);
		System.out.println("cp turn: "+squareNum);
		board.moveToSquare(squareNum);
		update_button(squareButtons[squareNum-1], squareNum);
		System.out.println("-------------------");
		
		if(check_board_state(board)){
			playable = false;
			return;
		}
	}
	public class SquareButton extends Button {
		public final int ID;
		public SquareButton(int ID) {
			Font f = new Font("Courier", Font.BOLD,36);
			this.setFont(f);
			this.setBackground(new Color(120,200,255));
			this.ID = ID;
			this.setLabel("" + Board.BLANK);
			this.addActionListener(TicTacToeApplet.this);
		}
	}
	
	
	

}

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;


public class ChessBoard extends JPanel{ //extending JPanel

	private static BoardPieces bp = new BoardPieces();

	public ChessBoard(){ //empty constructer
	
	}

	public void paint(Graphics g){ //'painting' the board

		for (int i = 0; i < 400; i+=100) {
			for (int j = 0; j < 400; j+=100) {
					g.setColor(new Color(193,161,120)); //building dark brown squares
					g.fillRect(i, j, 50, 50); //squares are 50x50 pixels
			}
		}
		for (int i = 0; i < 400; i+=100) {
			for (int j = 50; j < 400; j+=100) {
					g.setColor(new Color(79,36,18)); //building light brown squares
					g.fillRect(i, j, 50, 50);//squares are 50x50 pixels
			}
		}
		for (int i = 50; i < 400; i+=100) {
			for (int j = 50; j < 400; j+=100) {
					g.setColor(new Color(193,161,120)); //building dark brown squares
					g.fillRect(i, j, 50, 50); //squares are 50x50 pixels
			}
		}
		for (int i = 50; i < 400; i+=100) {
			for (int j = 0; j < 400; j+=100) {
					g.setColor(new Color(79,36,18)); //building light brown squares
					g.fillRect(i, j, 50, 50); //squares are 50x50 pixels
			}
		}

		bp.setupPieces(g); //setup the inital set of pieces
	}

	public void clickSomething(Coordinate initialClick, Coordinate finalClick){

		bp.click(initialClick, finalClick);
		repaint();
	}
	public String[][] getBoardState(){ //since bp can't be accessed from chess screen this is a method that returns the board for chessScreen

		return bp.getBoard();
	}
	public void setBoardState(String[][] board){ //since bp can't be mutated from chess screen this is a method that updates the board from chessScreen

		bp.setBoard(board);
		repaint();
	}
	public void resetBoardState(){ //just resets board to starting game board

		String board[][] = new String[][] {
			{"Rook1", "Knight1" , "Bishop1" , "Queen" , "King" , "Bishop2" , "Knight2" , "Rook2"}, //case sensitive
			{"Pawn1" , "Pawn2" , "Pawn3" , "Pawn4" , "Pawn5" , "Pawn6" , "Pawn7" , "Pawn8"},
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{"pawn1" , "pawn2" , "pawn3" , "pawn4" , "pawn5" , "pawn6" , "pawn7" , "pawn8"},
			{"rook1", "knight1" , "bishop1" , "queen" , "king" , "bishop2" , "knight2" , "rook2"}
		};

		setBoardState(board);
		
	}
}
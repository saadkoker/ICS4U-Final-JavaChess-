import java.awt.BorderLayout;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import java.util.ArrayList;

public class ChessScreen{ //this is our main interface where the game operations are conducted

	private static ChessBoard cb = new ChessBoard(); //our ChessBoard object
	private static ClickListener click = new ClickListener(); //our ClickListener object
	private static Convert conv = new Convert(); //our Convert object

	public void startScreen(int h, int l) throws InterruptedException{ //this method is called by another class and builds the chess board -> this will be the method that calls the Game class
		
		JFrame board = new JFrame("Chess");
		JLabel message = new JLabel("This fat bruh moment");
		JToolBar tools = new JToolBar();
		JButton newGame = new JButton("New");
		JButton saveGame = new JButton("Save");
		JButton undoButton = new JButton("Undo");
		JButton resignButton = new JButton("Resign");
		tools.setFloatable(false);
		board.add(cb);
		board.add(tools, BorderLayout.PAGE_START);
		tools.add(newGame);
		tools.add(saveGame); 
		tools.add(undoButton); 
		tools.addSeparator();
		tools.add(resignButton); 
		tools.addSeparator();
		tools.add(message);

		board.addMouseListener(click);
		board.setSize(h,l);

		board.setVisible(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setResizable(true);


		boolean clickyTime = true; 
		int count = 0;

		System.out.println("You have 250 clicks, dont waste them!");

		BoardPieces moves = new BoardPieces();

		while(clickyTime){ //just a test loop

		/*
			int[] initialClick = click.getClick();
			initialClick = conv.convertArr(initialClick, 62); //Saad this wont work for your resolution--> change the value to 60 for your machine
			int[] finalClick = click.getClick();
			finalClick = conv.convertArr(finalClick, 62); //for mac u need the value 42
			cb.clickSomething(initialClick, finalClick);
			count++;
		

			Coordinate initialClick = click.getClick();

			System.out.println("res values: " + initialClick.getY() + " , " + initialClick.getX());
			initialClick = conv.convCoor(initialClick, 42);//mac: 42
			BoardPieces moves = new BoardPieces();

			System.out.println("The legal moves for the click at location: " + initialClick.getY() + " , " + initialClick.getX() + " are: "
			+ Arrays.deepToString(moves.movement(initialClick)));
		*/

			Coordinate click1 = click.getClick();
			message.setText("Click recieved, please click a destination");
			Coordinate click2 = click.getClick();
			click1 = conv.convCoor(click1, 42);
			click2 = conv.convCoor(click2, 42);

			
			ArrayList<Coordinate> legalMoves = moves.movement(click1);

			boolean legal = false;

			for (Coordinate c: legalMoves){
				if(c.equals(click2)){
					legal = true;
					System.out.println("black beans");
					System.out.println("moving piece at " + c.getY() + " , " + c.getX() + " to " + click2.getY() + " , " + click2.getX());
				}
			}

			if(legal){
				cb.clickSomething(click1, click2);
				message.setText("Piece Moved");
			}

			else if (!legal){
				message.setText("Invalid move, please try again");
			}
			

			if (count == 250)
				clickyTime = false;
		}

		System.out.println("clicky time has ended");

	}		

}
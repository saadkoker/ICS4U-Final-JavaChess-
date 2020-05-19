import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JToolBar;

public class ChessScreen{ //this is our main interface where the game operations are conducted

	private static ChessBoard cb = new ChessBoard(); // our ChessBoard object
	private static ClickListener click = new ClickListener(); //our ClickListener object
	private static Convert conv = new Convert(); //our Convert object
	private static JLabel message = new JLabel("This fat bruh moment");
	private static Save gameSave = new Save();

	private static String boardPieces[][] = new String[][]{
		{"Rook1", "Knight1" , "Bishop1" , "Queen" , "King" , "Bishop2" , "Knight2" , "Rook2"}, //case sensitive
		{"Pawn1" , "Pawn2" , "Pawn3" , "Pawn4" , "Pawn5" , "Pawn6" , "Pawn7" , "Pawn8"},
		{" ", " " , " " , " " , " " , " " , " " , " " },
		{" ", " " , " " , " " , " " , " " , " " , " " },
		{" ", " " , " " , " " , " " , " " , " " , " " },
		{" ", " " , " " , " " , " " , " " , " " , " " },
		{"pawn1" , "pawn2" , "pawn3" , "pawn4" , "pawn5" , "pawn6" , "pawn7" , "pawn8"},
		{"rook1", "knight1" , "bishop1" , "queen" , "king" , "bishop2" , "knight2" , "rook2"}
	};

	public void startScreen(int h, int l) throws InterruptedException{ //this method is called by another class and builds the chess board -> this will be the method that calls the Game class
		
		JFrame board = new JFrame("Chess");
		JToolBar tools = new JToolBar();
		JButton newGame = new JButton("New");
		JButton saveGame = new JButton("Save");
		saveGame.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				
				JFileChooser f = new JFileChooser();
				JButton open = new JButton();
				f.setCurrentDirectory(new File("."));
				f.setDialogTitle("Save as");
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){
					String path = f.getSelectedFile().getAbsolutePath();
					System.out.println(path);
					gameSave.export(boardPieces, path);
				}
			}  
		});  
		JButton openButton = new JButton("Open");
		
		openButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				JFileChooser f = new JFileChooser();
				JButton open = new JButton();
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
				f.setDialogTitle("Open file");
				f.setCurrentDirectory(new File("."));

				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					String path = f.getSelectedFile().getAbsolutePath();
					System.out.println(path);
					String[][] newBoard = gameSave.fromSave(path);
					System.out.println(Arrays.deepToString(newBoard));
				}
			}  
		});  
		JButton resignButton = new JButton("Resign");
		resignButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  
				
				board.dispose();
				System.exit(-1);
			}  
		});
		tools.setFloatable(false);
		board.add(cb);
		board.add(tools, BorderLayout.PAGE_START);
		tools.add(newGame);
		tools.add(saveGame); 
		tools.add(openButton); 
		tools.addSeparator();
		tools.add(resignButton); 
		tools.addSeparator();
		tools.add(message);

		board.addMouseListener(click);
		board.setSize(h,l);

		board.setVisible(true);
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board.setResizable(true);


		//boolean clickyTime = true; 

		int count = 0;

		//System.out.println("You have 250 clicks, dont waste them!");

			boolean checkmate = false;

			//for(int i = 1; checkmate == false; i++){ //occelating between white and black team

			int i = 1;

			while(!checkmate){
			
				if (i % 2 != 0){ //odd so it's white turn
					message.setText("White's move");
					myGame(0);
					i++;
				}

				else { //even so black's turn
					message.setText("Black's move");
					myGame(1);
					i++;
				}
			}
	}
	
	public static void myGame(int team)throws InterruptedException{//team: 0 for white, 1 for black

		boolean legal = false;

		while(!legal){

		Coordinate click1 = click.getClick();
		message.setText("Click recieved, please click a destination");
		Coordinate click2 = click.getClick();
		click1 = conv.convCoor(click1, 42);
		click2 = conv.convCoor(click2, 42);
		
		BoardPieces moves = new BoardPieces();

		ArrayList<Coordinate> legalMoves = moves.movement(click1);

			for (Coordinate c: legalMoves){
				if(c.equals(click2) && moves.getCase(click1.getY(), click1.getX()) == team){ //making sure the move is legal and is on the right team
					legal = true;
					System.out.println("black beans");
					System.out.println("moving piece at " + c.getY() + " , " + c.getX() + " to " + click2.getY() + " , " + click2.getX());
				}

				if(moves.getCase(click1.getY(), click1.getX()) != team)
					message.setText("Invalid team");
			}

			if(legal){
				cb.clickSomething(click1, click2);
				message.setText("Piece Moved");
				break;
			}

			else if (!legal){
				message.setText("Invalid move, please try again");
			}
		}

	}

}
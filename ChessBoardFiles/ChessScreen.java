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
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ChessScreen{ //this is our main interface where the game operations are conducted

	public static ChessBoard cb = new ChessBoard(); // our ChessBoard object
	public static ClickListener click = new ClickListener(); //our ClickListener object
	private static Convert conv = new Convert(); //our Convert object
	public static JLabel message = new JLabel("This fat bruh moment");
	private static Save gameSave = new Save();
	public static JFrame board = new JFrame("Chess"); //creating the jframe for all the components
	private static int i = 1;

	public void startScreen(int h, int l) throws InterruptedException{ //this method is called by another class and builds the chess board -> this will be the method that calls the Game class
		
		JToolBar tools = new JToolBar(); //creating a toolbox to create a task bar
		JButton newGame = new JButton("New"); //creating a button for a new game
		newGame.addActionListener(new ActionListener(){  //creating an action listener to listen for clicks
			public void actionPerformed(ActionEvent e){
				
				int input = 0;

				input = JOptionPane.showConfirmDialog(null, "Are you sure? (board will not be automatically saved)"); // 0=yes, 1=no, 2=cancel
	
				if (input == JOptionPane.YES_OPTION) {
					cb.resetBoardState();

				}
			}  
		}); 

		JButton saveGame = new JButton("Save"); //used for saving the current board state
		saveGame.addActionListener(new ActionListener(){  //creating an action listener to listen for clicks
			public void actionPerformed(ActionEvent e){
				
				JFileChooser f = new JFileChooser(); //creating a fileChooser object
				JButton open = new JButton(); //creating an open button for the file chooser
				f.setCurrentDirectory(new File(".")); //setting the current directory to the directory of the java file for the sake of ease
				f.setDialogTitle("Save As"); //setting the frame title to Save as
				f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); //making sure they can only select directories and not files

				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION){ //if they click the save button then get the path and pass on board state and path to method which exports
					String path = f.getSelectedFile().getAbsolutePath();
					System.out.println(path);
					gameSave.export(cb.getBoardState(), path, i);
				}
			}  
		});  
		JButton openButton = new JButton("Open");
		
		openButton.addActionListener(new ActionListener(){  //creating an action listener for clicks
			public void actionPerformed(ActionEvent e){
				
				JFileChooser f = new JFileChooser(); //creating a filechooser object
				JButton open = new JButton(); //creating a button for the filechooser
				f.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); //making it so they can select files and directories
				FileNameExtensionFilter filter = new FileNameExtensionFilter("ChessBoard Files", "cb");
				f.setFileFilter(filter);
				f.setDialogTitle("Open File");
				f.setCurrentDirectory(new File(".")); //setting the directory of the filechooser to start in the java files directory

				if (f.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) { //if the user clikcs open the board will import from the selected save file
					String path = f.getSelectedFile().getAbsolutePath();
					String[][] newBoard = gameSave.fromSave(path);
					i = gameSave.getTeamMove();
					System.out.println(i);
					cb.setBoardState(newBoard);
				}
			}  
		});  
		JButton resignButton = new JButton("Resign"); //TODO: add a little menu to congratulate player on winning
		resignButton.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  //if resign is clicked close program
				
				board.dispose();
				System.exit(-1);
			}  
		});

		tools.setFloatable(false);
		board.add(cb); //add all of the created componenets to the JFrame to be displated
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

		board.setVisible(true); //setting the frame to be visable 
		board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //making sure that the exit button actually closes the program
		board.setResizable(true); //making the board resizeable

			boolean checkmate = false;

			while(!checkmate){

				Game play = new Game();
				System.out.println("In loop: " + i);
				if (i % 2 != 0){ //odd so it's white turn
					message.setText("White's move");
					checkmate = !play.myGame(0, message, cb, click, board);//white team
					//myGame(0);
					i++;
				}

				else { //even so black's turn
					message.setText("Black's move");
					checkmate = !play.myGame(1, message, cb, click, board);
					//myGame(1);
					i++;
				}
			}
	}

}
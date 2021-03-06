import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.imageio.ImageIO;
import java.util.Collections;

public class BoardPieces{

	private static BufferedImage Rook1, Knight1 , Bishop1 , Queen , King , Bishop2 , Knight2 , Rook2, Pawn1 , Pawn2 , Pawn3 , Pawn4 , Pawn5 , Pawn6 , Pawn7 , Pawn8; //black pieces 
	private static BufferedImage rook1, knight1 , bishop1 , queen , king , bishop2 , knight2 , rook2, pawn1 , pawn2 , pawn3 , pawn4 , pawn5 , pawn6 , pawn7 , pawn8; //white pieces
	private static Convert conv = new Convert();
	private static PieceTest piece = new PieceTest();
	
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
	
	public String[][] getBoard(){
		
		return boardPieces;
	}
	
	public int getCase(int y, int x){

		if((boardPieces[y][x].charAt(0) == Character.toLowerCase(boardPieces[y][x].charAt(0))))
			return 0; //lowercase
		
		
		else if((boardPieces[y][x].charAt(0) == Character.toUpperCase(boardPieces[y][x].charAt(0))))
			return 1; //uppercase
		

		else
			return -1;
	}

	public boolean gameOver(String[][] board, boolean whiteTeam){
		
		Check checkTest = new Check();

		if (checkTest.getCheck(board, whiteTeam)){
			
			ArrayList<Move> legal = getLegalMoves(whiteTeam);

			for (Move m: legal){
				if (boardTester(m.getStart(), m.getEnd(), board) == false){ //if one of the possible moves results in no check
					return false;
				}
			}

			return true;
		}

		return false;
	}


	public ArrayList<Coordinate> movement(Coordinate coord){

		return piece.getLegalMoves(coord, boardPieces[coord.getY()][coord.getX()], boardPieces);
	}
	
	public ArrayList<Move> getLegalMoves(boolean whiteTeam){

		ArrayList<Move> myMobileMoves = piece.myMoves(whiteTeam, boardPieces);
		ArrayList<Move> myPossibleMoves =  new ArrayList<Move>();

		for(int i = 0; i < myMobileMoves.size(); i++){//looping through all mobile moves to find ones that will get the player out of checkmate
			if(boardTester((myMobileMoves.get(i).getStart()), (myMobileMoves.get(i).getEnd())) == false){ //this movement will not result in check
				myPossibleMoves.add(myMobileMoves.get(i));
			}
		}

		return myPossibleMoves;

	}

	public ArrayList<Move> getLegalMoves(boolean whiteTeam, String[][] board){

		ArrayList<Move> myMobileMoves = piece.myMoves(whiteTeam, board);
		ArrayList<Move> myPossibleMoves =  new ArrayList<Move>();

		for(int i = 0; i < myMobileMoves.size(); i++){//looping through all mobile moves to find ones that will get the player out of checkmate
			if(boardTester((myMobileMoves.get(i).getStart()), (myMobileMoves.get(i).getEnd())) == false){ //this movement will not result in check
				myPossibleMoves.add(myMobileMoves.get(i));
			}
		}

		return myPossibleMoves;

	}

	public void click(Coordinate coordStart, Coordinate coordDestination){

		boardPieces[coordDestination.getY()][coordDestination.getX()] = boardPieces[coordStart.getY()][coordStart.getX()];
		//System.out.println("You just moved the " + boardPieces[coordStart.getY()][coordStart.getX()] + " to (" + coordDestination.getY() + " , " + coordDestination.getX() + ")");
		boardPieces[coordStart.getY()][coordStart.getX()] = " ";

	}


	public void clickTest(Coordinate coordStart, Coordinate coordDestination, String[][] copy){

		copy[coordDestination.getY()][coordDestination.getX()] = copy[coordStart.getY()][coordStart.getX()];
		copy[coordStart.getY()][coordStart.getX()] = " ";
	}

	public boolean boardTester(Coordinate c1, Coordinate c2){

		boolean whiteTeam = false;
		Check check = new Check();

		String[][] boardState = deepCopyOf(boardPieces); //copying 

		if(boardState[c1.getY()][c1.getX()].charAt(0) == Character.toLowerCase(boardState[c1.getY()][c1.getX()].charAt(0))){ //determining team
			whiteTeam = true;
		}

		boardState[c2.getY()][c2.getX()] = boardState[c1.getY()][c1.getX()];
		boardState[c1.getY()][c1.getX()] = " ";


		return check.getCheck(boardState, whiteTeam); //checking if this board state would put the king in check
	}

	public boolean boardTester(Coordinate c1, Coordinate c2, String [][] board){ //the programmar efficeny would be o(n^x) if I tried to change the method parameters on this

		boolean whiteTeam = false;
		Check check = new Check();

		String[][] boardState = deepCopyOf(board); //copying 

		if(boardState[c1.getY()][c1.getX()].charAt(0) == Character.toLowerCase(boardState[c1.getY()][c1.getX()].charAt(0))){ //determining team
			whiteTeam = true;
		}

		boardState[c2.getY()][c2.getX()] = boardState[c1.getY()][c1.getX()];
		boardState[c1.getY()][c1.getX()] = " ";


		return check.getCheck(boardState, whiteTeam); //checking if this board state would put the king in check
	}

	public boolean boardTester(int team){ //yay polymorphism

		Check check = new Check();
		boolean whiteTeam = false;

		if(team == 0){
			whiteTeam = true;
		}
		if(team == 1){
			whiteTeam = false;
		}

		return check.getCheck(boardPieces, whiteTeam);
	}

	public void setupPieces(Graphics g){

		try{

			Rook1 = ImageIO.read(new File("pieces/rook1.png"));
			Knight1 = ImageIO.read(new File("pieces/knight1.png"));
			Bishop1 = ImageIO.read(new File("pieces/bishop1.png"));
			Queen = ImageIO.read(new File("pieces/queen1.png"));
			King = ImageIO.read(new File("pieces/king1.png"));
			Bishop2 = ImageIO.read(new File("pieces/bishop1.png"));
			Knight2 = ImageIO.read(new File("pieces/knight1.png"));
			Rook2 = ImageIO.read(new File("pieces/rook1.png"));
			Pawn1 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn2 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn3 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn4 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn5 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn6 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn7 = ImageIO.read(new File("pieces/pawn1.png")); 
			Pawn8 = ImageIO.read(new File("pieces/pawn1.png")); 
/*************************************************************************/
			rook1 = ImageIO.read(new File("pieces/rook2.png"));
			knight1 = ImageIO.read(new File("pieces/knight2.png"));
			bishop1 = ImageIO.read(new File("pieces/bishop2.png"));
			queen = ImageIO.read(new File("pieces/queen2.png"));
			king = ImageIO.read(new File("pieces/king2.png"));
			bishop2 = ImageIO.read(new File("pieces/bishop2.png"));
			knight2 = ImageIO.read(new File("pieces/knight2.png"));
			rook2 = ImageIO.read(new File("pieces/rook2.png"));
			pawn1 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn2 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn3 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn4 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn5 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn6 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn7 = ImageIO.read(new File("pieces/pawn2.png")); 
			pawn8 = ImageIO.read(new File("pieces/pawn2.png")); 

			drawPic(Rook1, g, "Rook1");
			drawPic(Knight1, g, "Knight1");
			drawPic(Bishop1, g, "Bishop1");
			drawPic(Queen, g, "Queen");
			drawPic(King, g, "King");
			drawPic(Bishop2, g, "Bishop2");
			drawPic(Knight2, g, "Knight2");
			drawPic(Rook2, g, "Rook2");
			drawPic(Pawn1, g, "Pawn1");
			drawPic(Pawn2, g, "Pawn2");
			drawPic(Pawn3, g, "Pawn3");
			drawPic(Pawn4, g, "Pawn4");
			drawPic(Pawn5, g, "Pawn5");
			drawPic(Pawn6, g, "Pawn6");
			drawPic(Pawn7, g, "Pawn7");
			drawPic(Pawn8, g, "Pawn8");
//**************************************************/
			drawPic(rook1, g, "rook1");
			drawPic(knight1, g, "knight1");
			drawPic(bishop1, g, "bishop1");
			drawPic(queen, g, "queen");
			drawPic(king, g, "king");
			drawPic(bishop2, g, "bishop2");
			drawPic(knight2, g, "knight2");
			drawPic(rook2, g, "rook2");
			drawPic(pawn1, g, "pawn1");
			drawPic(pawn2, g, "pawn2");
			drawPic(pawn3, g, "pawn3");
			drawPic(pawn4, g, "pawn4");
			drawPic(pawn5, g, "pawn5");
			drawPic(pawn6, g, "pawn6");
			drawPic(pawn7, g, "pawn7");
			drawPic(pawn8, g, "pawn8");

		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public static void drawPic(BufferedImage img, Graphics g, String name){
		
		int[] result = searchArr(boardPieces, name);

		if(result[1] > -1 && result[0] > -1){
			int y = conv.convertCoord(result[0]);
			int x = conv.convertCoord(result[1]);
			//System.out.println("drawing " + name + " @ " + y + " , " + x);
			g.drawImage(img, x, y, 50, 50, null);
		}
	}
	
	public static int[] searchArr(String arr[][], String target){
		int[] location = new int[2];
		location[0] = -1;
		location[1] = -1;

		for(int i = 0; i < arr.length; i++){ //row 
			
			for(int j = 0; j < arr[i].length; j++){ //collumns
			   
				if (arr[i][j].equals(target)){

					location[0] = i;
					location[1] = j;
					return location;
				}
				
			}
		}

		return location;
	
	}

	public void setBoard(String[][] board){ //takes in an array and updates the board state to the given board state

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				boardPieces[i][j] = board[i][j];
			}
		}
	}

	public static String[][] deepCopyOf(String board[][]) { //creates a copy of the current board so we dont mutate the original one

		String[][] arr = new String[8][8]; // revise this later to make for more inclusive OOC

		for (int i = 0; i < 8; i++) {
			arr[i] = Arrays.copyOf(board[i], board[i].length);
		}

		return arr;
	}
}
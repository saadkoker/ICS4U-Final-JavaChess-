import java.lang.reflect.Array;
import java.util.Arrays;

public class Piece{

/*
	private Coordinate coordinate;
	private boolean isWhite;
	private File file;

	public void Piece(Coordinate coordinate, boolean isWhite, File file){ //constructor

	    this.coordinate = coordinate;
	    this.isWhite = isWhite;
	    this.file = file;
	}

*/
	public static int[][] getLegalMoves(int[] coordinate, String piece, String board[][]){ //takes in string since it can just take in the name of the file found in the board array

		int[][] legalMoves = new int[8][8];

		if(piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")){
			legalMoves = rookLegalMoves(coordinate, piece, board);
		}
		/*
		else if (piece.equalsIgnoreCase("Knight1") || piece.equalsIgnoreCase("Knight2")) {
			legalMoves = knightLegalMoves(coordinate);
		}
		else if (piece.equalsIgnoreCase("Bishop1") || piece.equalsIgnoreCase("Bishop2")) {
			legalMoves = bishopLegalMoves(coordinate);
		}
		else if (piece.equalsIgnoreCase("Queen1") || piece.equalsIgnoreCase("Queen2")) {
			legalMoves = queenLegalMoves(coordinate);
		}
		else if (piece.equalsIgnoreCase("King1") || piece.equalsIgnoreCase("King2")) {
			legalMoves = kingLegalMoves(coordinate);
		}
		else{ //sets legal moves for pawns
			legalMoves = pawnLegalMoves(coordinate);
		}
		*/
		return legalMoves;
	}
	private static int[][] rookLegalMoves(int[] coordinate, String piece, String[][] board){

		int y = coordinate[0];
		int x = coordinate[1];
		int diffrence = 7 - x; 
		int counter = 0;

		int[][] legalMoves = new int[16][2];

		for (int i = 0; i < 7; i++) {
			if((y - i) == -1){ //bad method name but uk what i mean
				legalMoves[i][0] = -1;
				legalMoves[i][1] = 	-1;
				i = 90;
			}
			else if((y - i) > -1 && teamInPos(y - i, x - i, board, piece) == false){
				counter++;
				legalMoves[i][0] = 	y - i;
				legalMoves[i][1] = 	x;
			} 
		}
		for (int i = counter; i < 7; i++) {
			if((y + i) == 8){ //bad method name but uk what i mean
				legalMoves[i][0] = -1;
				legalMoves[i][1] = 	-1;
				i = 90;
			}
			else if((y + i) < 8  && teamInPos(y - i, x, board, piece) == false){
				counter++;
				legalMoves[i][0] = 	y + i;
				legalMoves[i][1] = 	x;
			} 
		}

		for (int i = 0; i < 7; i++) {
			if((x - i) == -1){ //bad method name but uk what i mean
				legalMoves[i][0] = -1;
				legalMoves[i][1] = 	-1;
				i = 90;
			}
			else if((x - i) > -1 && teamInPos(y , x - i, board, piece)){
				counter++;
				legalMoves[i][0] = 	y - i;
				legalMoves[i][1] = 	x;
			} 
		}
		for (int i = counter; i < 7; i++) {
			if((x + i) == 8){ //bad method name but uk what i mean
				legalMoves[i][0] = -1;
				legalMoves[i][1] = 	-1;
				i = 90;
			}
			else if((y + i) < 8  && teamInPos(y, x + i, board, piece)){
				counter++;
				legalMoves[i][0] = 	y + i;
				legalMoves[i][1] = 	x;
			} 
		}

		return legalMoves;
	}
/*
	private static int[][] knightLegalMoves(int[] coordinate){

		return coordinate;
		
	}
	private static int[][] bishopLegalMoves(int[] coordinate){
		
		return coordinate;
	}
	private static int[][] queenLegalMoves(int[] coordinate){
		
		return coordinate;
	}
	private static int[][] kingLegalMoves(int[] coordinate){
		
		return coordinate;
	}
	private static int[][] pawnLegalMoves(int[] coordinate){
		
		return coordinate;
	}

*/
	public static boolean teamInPos(int y, int x, String[][] board, String piece){
		
		boolean team_mate = false;

		String[][] myTeam = getTeam(board, piece);

		if(myTeam[y][x] != " ") //arrays are row major for some reason
			team_mate = true;

		return team_mate;
	}


	public static String[][] getTeam(String[][] board, String piece){

		String[][] teamBoard = deepCopyOf(board); //we dont want to override our current board

		if (piece.charAt(0) == Character.toLowerCase(piece.charAt(0))){ //we are checking if the first character in the piece is lowercase

			for(int i = 0; i < teamBoard.length; i++){ //row 

				for(int j = 0; j < teamBoard[i].length; j++){ //collumns
				
					if (teamBoard[i][j].charAt(0) == (Character.toUpperCase(teamBoard[i][j].charAt(0)))){
						teamBoard[i][j] = " "; //get rid off it because its upper case and thus not on the same team
					}
					
				}
			}
		}

		else{ //if the piece is an uppercase type type

			for(int i = 0; i < teamBoard.length; i++){ //row 

				for(int j = 0; j < teamBoard[i].length; j++){ //collumns
			
					if (teamBoard[i][j].charAt(0) == (Character.toLowerCase(teamBoard[i][j].charAt(0)))){
						teamBoard[i][j] = " "; //get rid off it because its lower case and thus not on the same team
					}
				
				}
			}
		}

		return teamBoard;
	}

	private static String[][] deepCopyOf(String board[][]){
		
		String[][] arr = new String[8][8]; //revise this later to make for more inclusive OOC

		for (int i = 0; i < 8; i++) {
			arr[i] = Arrays.copyOf(board[i], board[i].length);
		}

		return arr;
	}

}
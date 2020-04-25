import java.lang.reflect.Array;
import java.util.Arrays;

public class Piece{


	private Coordinate coordinate;


	public int[][] getLegalMoves(int[] coordinate, String piece, String board[][]){ //takes in string since it can just take in the name of the file found in the board array

		int[][] legalMoves = new int[63][2];

		if(piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")){
			legalMoves = getRookMoves(coordinate, piece, board);
		}
		
		//else if (piece.equalsIgnoreCase("Knight1") || piece.equalsIgnoreCase("Knight2")) {
		//	legalMoves = knightLegalMoves(coordinate);
	//	}
		//else if (piece.equalsIgnoreCase("Bishop1") || piece.equalsIgnoreCase("Bishop2")) {
		//	legalMoves = bishopLegalMoves(coordinate);
		//}
		//else if (piece.equalsIgnoreCase("Queen1") || piece.equalsIgnoreCase("Queen2")) {
		//	legalMoves = queenLegalMoves(coordinate);
		//}
		//else if (piece.equalsIgnoreCase("King1") || piece.equalsIgnoreCase("King2")) {
		//	legalMoves = kingLegalMoves(coordinate);
		//}
		else{ //sets legal moves for pawns
			legalMoves = pawnLegalMoves(coordinate, board, piece);
		}
		

		//System.out.println(Arrays.deepToString(legalMoves));
		return legalMoves;
	}

		public static void assignArbitraryValues(int[][] a){

			for(int i = 0; i < a.length; i++){
				for(int j = 0; j < a[i].length; j++){
					a[i][j] = -1;
				}
			}
		}
		public static int[][] getRookMoves(int[] coord, String piece, String[][] board){

			int[][] moves = new int[256][2];
			assignArbitraryValues(moves);
			int x = coord[1];
			int y = coord[0];
			int pos = 0;

			//Rules: it must be straight lines
			// => we have four directions to check
			//starts at 1 because we dont want it to return itself

			//System.out.println(y + " , " + x);

			for (int i = 1; i < 9; i++){ 

				if (((x+i) <= 8) && (teamInPos(y, x, y, x+i, board, piece, "right") == false)){ //remember boolean order of operations here!
					System.out.println("we movin right");
					moves[pos][1] = (x+i);
					moves[pos][0] = y;
					pos++;
				}
			
				if (((x-i) >= 0) && (teamInPos(y, x, y, x-i, board, piece, "left") == false)){
					System.out.println("we movin left");
					moves[pos][1] = (x-i);
					moves[pos][0] = y;
					pos++;
				}

				if (((y+i) <= 8) && (teamInPos(y, x, y+i, x, board, piece, "up") == false)){
					System.out.println("we movin up");
					moves[pos][1] = x;
					moves[pos][0] = (y+i);
					pos++;
				}
			
				if (((y-i) >= 0) && (teamInPos(y, x, y-i, x, board, piece, "down") == false)){					
					System.out.println("we movin down");
					moves[pos][1] = x;
					moves[pos][0] = (y-i);
					pos++;
				}
			}

			return moves;
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
	*/
	private static int[][] kingLegalMoves(int[] coordinate, String piece, String[][] board){
		
		int[][] squareMaps = {{1, 0}, {0, 1},{-1, 0},{0, -1},{1, 1},{-1, 1},{-1, -1},{1, -1}};
		int clickCoordY = coordinate[0]; //idk whether y or x is supposed to be first but id rather kms then figure out
		int clickCoordX = coordinate[1];
		int[][] coords = new int[8][2];

		for (int i = 0; i < squareMaps.length; i++) {
			coords[i][0] = clickCoordY - squareMaps[i][0];
			coords[i][1] = clickCoordY - squareMaps[i][1];
		}

		//int[][] finalCoords = removeOutOfBounds(coords); //this method changes any out of bounds coordinates in the array to [-1,-1]
		System.out.println(Arrays.deepToString(coords) + "\n");
		return coords; //return the array
		
	}

<<<<<<< HEAD
	private static int[][] pawnLegalMoves(int[] coordinate, String[][] board, String piece, String direction){
=======
*/

	private static int[][] lines(int[] coordinate, String[][] board, String piece){
>>>>>>> a4f94e6311854d94896ce21326fae58a2fb7f546
		
		int y = coordinate[0];
		int x = coordinate[1];
		int count = 0;

		int[][] possible = new int[64][2];

		for (int i = 1; i < 8; i++){

			if (((y+i) < 7) && (teamInPos(y, x, y+i, x, board, piece, "up") == false)) { 
				possible[count][0] = y+i;
				possible[count][1] = x;
				System.out.println("we movin up");
				count++;
			}
			
			if (((y-i) > 0) && (teamInPos(y, x, y+i, x, board, piece, "down") == false)) {
				possible[count][0] = y-i;
				possible[count][1] = x;
				System.out.println("we movin down");
				count++;
			}
			
			else
				i=8;
		}
		
		return possible;
	}
	
	private static int[][] pawnLegalMoves(int[] coordinate, String[][] board, String piece){
		
		int y = coordinate[0];
		int x = coordinate[1];
		int count = 0;
		int[][] possible = new int[4][2];
		assignArbitraryValues(possible);
		
		//String[][] team = getTeam(board, piece);
		
		if ((y == 1) && (board[3][x] == " ")){
			possible[count][0] = 3;
			possible[count][1] = x;
			count++;
		}
		
		else if ((y == 6) && (board[4][x] == " ")){ //pawns get to move 2 blocks on first move (P1 varient)
			possible[count][0] = 4;
			possible[count][1] = x;
			count++;
		}
		
		if ((piece.charAt(0) == Character.toLowerCase(piece.charAt(0)))){ //we are checking if the first character in the piece is lowercase		
			
			if(y > 0) {
				
				if(board[y-1][x] == " ") {
					possible[count][0] = y-1;
					possible[count][1]= x;
					count++;
				}
			
				if((x > 0) && (board[y-1][x-1] != " ")) { //checking if there's a piece diagnolly available -> up and to the left
					possible[count][0] = y-1;
					possible[count][1] = x-1;
					count++;
				}
	
				if((x < 7) && (board[y-1][x+1] != " ")) { //checking if there's a piece diagnolly available --> up and to the right
					possible[count][0] = y-1;
					possible[count][1] = x+1;
					count++;
				}
			}
		}
		
		else if ((piece.charAt(0) == Character.toUpperCase(piece.charAt(0)))){
			
			if(y < 7) {
				
				if((board[y+1][x] == " ")){
					possible[count][0] = y+1;
					possible[count][1]= x;
					count++;
				}
				
				if((x > 1) && (board[y+1][x-1] != " ")) { //checking if there's a piece diagnolly available -> down and to the left
					possible[count][0] = y+1;
					possible[count][1] = x-1;
					count++;
				}
	
				if((x < 7) && (board[y+1][x+1] != " ")) { //checking if there's a piece diagnolly available --> down and to the right
					possible[count][0] = y+1;
					possible[count][1] = x+1;
					count++;
				}
			}
		}
		
		return possible;
	}

	
	public static boolean teamInPos(int y1, int x1, int y2, int x2, String[][] board, String piece, String direction){
		
		boolean team_mate = false;
		String[][] myTeam = getTeam(board, piece);

		if (direction.equals("up")){

			for(int i = y1+1; i <= y2; i++){

				if (myTeam[i][x1] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + x2 + " it's a " + myTeam[i][x2]);
					return true;
				}
			}
		}

		if (direction.equals("down")){

			for(int i = y2+1; i <= y1; i++){

				if (myTeam[i][x1] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + x2 + " it's a " + myTeam[i][x2]);
					return true;
				}
			}
		}

		if (direction.equals("left")){

			for(int i = x2; i <= x1; i++){

				if (myTeam[y2][i] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + y2 + " and x: " + i + " it's a " + myTeam[y2][i]);
					return true;
				}
			}
		}

		if (direction.equals("right")){

			for(int i = x1; i <= x2; i++){

				if (myTeam[y2][i] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + y2 + " and x: " + i + " it's a " + myTeam[y2][i]);
					return true;
				}
			}
		}

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

		//System.out.println("our team: " + Arrays.deepToString(teamBoard));

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


	/*
	public static void main(String[] args){

		int[] click = new int [2];
		click[0] = 3;
		click[1] = 2;

		String boardPieces[][] = new String[][]{
			{"Rook1", "Knight1" , "Bishop1" , "Queen" , "King" , "Bishop2" , "Knight2" , "Rook2"}, //case sensitive
			{"Pawn1" , "Pawn2" , "Pawn3" , "Pawn4" , "Pawn5" , "Pawn6" , "Pawn7" , "Pawn8"},
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{" ", " " , " " , " " , " " , " " , " " , " " },
			{"pawn1" , "pawn2" , "pawn3" , "pawn4" , "pawn5" , "pawn6" , "pawn7" , "pawn8"},
			{"rook1", "knight1" , "bishop1" , "queen" , "king" , "bishop2" , "knight2" , "rook2"}
		};

		System.out.println(boardPieces[6][2]);
		System.out.println("legal moves : " + Arrays.deepToString(getRookMoves(click, boardPieces, "rook1")));
	}
	private boolean isOutOfBounds(int x, int y) {
		
		if ((x == 10 || x == -1) || (y == 10 || y == -1)) {
			return true;
		}
		else{
			return false;
		}
	}
	private int[][] removeOutOfBounds(int[][] coords){

		for (int i = 0; i < coords.length; i++) {
			for (int j = 0; j < coords[i].length; i++) {
				if ((coords[i][j] == -1) || (coords[i][j] == 10))  {
					coords[i][0] = -1;
					coords[i][1] = -1;
				}
			}
		}
		return coords;
	}
	*/

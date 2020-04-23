import java.lang.reflect.Array;
import java.util.Arrays;

public class Piece{


	private Coordinate coordinate;


	public int[][] getLegalMoves(int[] coordinate, String piece, String board[][]){ //takes in string since it can just take in the name of the file found in the board array

		int[][] legalMoves = new int[63][2];

		if(piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")){
			legalMoves = getRookMoves(coordinate, board, piece);
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
		//else{ //sets legal moves for pawns
		//	legalMoves = pawnLegalMoves(coordinate);
		//}
		

		//System.out.println(Arrays.deepToString(legalMoves));
		return legalMoves;
	}
/*
	private static int[][] rookLegalMoves(int[] coordinate, String piece, String[][] board){

		int y = coordinate[0];
		int x = coordinate[1];
		int diffrence = 7 - x; 
		int counter = 0;

		int[][] legalMoves = new int[64][2];

		for (int i = 0; i < 7; i++) {

			if((y - i) == -1){ //bad method name but uk what i mean
				legalMoves[i][0] = -1;
				legalMoves[i][1] = 	-1;
				break;
			}
			else if((y - i) > -1 && teamInPos(y - i, x - i, board, piece) == false){
				counter++;
				legalMoves[i][0] = 	y - i;
				legalMoves[i][1] = 	x;
			} 
		}

		for (int i = 0; i < 7; i++) {

			if((y + i) == 8){ //bad method name but uk what i mean
				legalMoves[counter][0] = -1;
				legalMoves[counter][1] = -1;
				break;
			}
			else if((y + i) < 8  && teamInPos(y - i, x, board, piece) == false){
				counter++;
				legalMoves[counter][0] = 	y + i;
				legalMoves[counter][1] = 	x;
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
*/

		public static int[][] getRookMoves(int[] coord, String[][] board, String piece){

			int[][] moves = new int[63][2];
			int x = coord[1];
			int y = coord[0];
			int pos = 0;


			//Rules: it must be straight lines
			// => we have four directions to check
			//starts at 1 because we dont want it to return itself


			for (int i = 1; i < 9; i++){ 

				if (((x+i) < 8) && (teamInPos(y, x, y, x+i, board, piece, "right") == false)){ //remember boolean order of operations here!
					moves[pos][1] = (x+i);
					moves[pos][0] = y;
					pos++;
				}
			
				if (((x-i) > -1) && (teamInPos(y, x, y, x-i, board, piece, "left") == false)){
					moves[pos][1] = (x-i);
					moves[pos][0] = y;
					pos++;
				}

				if (((y+i) < 8) && (teamInPos(y, x, y+i, x, board, piece, "down") == false)){ 
					moves[pos][1] = x;
					moves[pos][0] = (y+i);
					pos++;
				}
			
				if (((y-i) > -1) && (teamInPos(y, x, y-i, x, board, piece, "up") == false)){
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
	private static int[][] kingLegalMoves(int[] coordinate){
		
		return coordinate;
	}

*/

	private static int[][] pawnLegalMoves(int[] coordinate, String[][] board, String piece, String direction){
		
		int y = coordinate[0];
		int x = coordinate[1];
		int count = 0;

		int[][] possible = new int[64][2];

		for (int i = 1; i < 8; i++){

			if ((y+i < 9) && (teamInPos(y, x, y++, x2, board, piece, direction)) == false){
				possible[count][0] = y++;
				possible[count][1] = x;
			}
		}

		return possible;
	}

	
	public static boolean teamInPos(int y1, int x1, int y2, int x2, String[][] board, String piece, String direction){
		
		boolean team_mate = false;
		String[][] myTeam = getTeam(board, piece);

		if (direction == "up"){ //y is decreasing

			for(int i = y1; i >= y2; i--){
				if(!(myTeam[i][x1].equals(" "))){
					//System.out.println("returning true for: " + x1 + " , " + i);
					team_mate = true;
					//return true;
				}
			}
		}
		if (direction == "down"){ //y is increasing

			for(int j = y1; j <= y2; j++){
				if(!(myTeam[j][x1].equals(" "))){
					//System.out.println("returning true for: " + x1 + " , " + j);
					team_mate = true;
					//return true;
				}
			}
		}
		if (direction == "left"){ //x is decreasing

			for(int k = x2; k <= x1; k++){
				if(!(myTeam[y1][k].equals(" "))){
					//System.out.println("returning true for: " + k + " , " + y1);
					team_mate = true;
					//return true;
				}
			}
		}

		if (direction == "right"){ //x is increasing

			for(int l = x1; l <= x2; l++){
				if(!(myTeam[y1][l].equals(" "))){
					//System.out.println("returning true for: " + l + " , " + y1);
					team_mate = true;
					//return true;
				}
			}
		}
		else{ //this means it's diagnoal -> x and y is changing at the same rate

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



public static boolean mobility(int direction, int length, int row, int col){

	boolean mobile = true;

	if (direction == 1){ //incrementing row

		for (int i = 1; i < length; i++){
			
			if ((row+i > 9) || (boatLocation[row+i][col] == true)){
				mobile = false;
				return false;
			}
		}
	}
	else if (direction == 2){ //incrementing row, incrementing column
		
		for (int i = 1; i < length; i++){
			
			if ((row+i > 9) || (col+i > 9) || (boatLocation[row+i][col+i] == true)){
				mobile = false;
				return false;
			}
		}
	}
			
	else if (direction == 3){ //incrementing col
		
		for (int i = 1; i < length; i++){
			
			if ((col+i > 9) || (boatLocation[row][col+i] == true)){
				mobile = false;
				return false;
			}
		}
	}
	else if (direction == 4){ //decrementing row, incrementing column
		
		for (int i = 1; i < length; i++){
			
			if (boatLocation[row-i][col+i] == true){
				mobile = false;
				return false;
			}
		}
	}
	else if (direction == 5){ //decrementing row
		
		for (int i = 1; i < length; i++){
			
			if (boatLocation[row-i][col] == true){
				mobile = false;
				return false;
			}
		}
	}
	else if (direction == 6){ //decrementing row decrementing col
		
		for (int i = 1; i < length; i++){
			
			if ((row-i < 0) || (col-i < 0) || (boatLocation[row-i][col-i] == true)){
				mobile = false;
				return false;
			}
		}
	}
	else if (direction == 7){//decrementing col
		
		for (int i = 1; i < length; i++){
			
			if (boatLocation[row][col-i] == true){
				mobile = false;
				return false;
			}
		}
	}
	else if (direction == 8){//incrementing row, decrementing col
		
		for (int i = 1; i < length; i++){
			
			if (boatLocation[row+i][col-i] == true){
				mobile = false;
				return false;
			}
		}
	}
	return mobile;
}
*/

}
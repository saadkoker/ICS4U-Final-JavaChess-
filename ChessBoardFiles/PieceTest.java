import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class PieceTest {

	public ArrayList<Coordinate> getLegalMoves(Coordinate location, String piece, String[][] board) {

		ArrayList<Coordinate> legalMoves = new ArrayList<Coordinate>(); //creating an ArrayList called legalMoves to store the returned moves

		if (piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")) { //if piece is rook call getRookMoves and save it to legalMoves
			legalMoves.addAll(getRookMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("Knight1") || piece.equalsIgnoreCase("Knight2")) { //if piece is knight call knigtLegalMoves and save it to legalMoves
			legalMoves.addAll(knightLegalMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("Bishop1") || piece.equalsIgnoreCase("Bishop2")) { //if piece is bishop call bishopLegalMoves and save it to legalMoves
			legalMoves.addAll(bishopLegalMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("Queen")) { //if piece is Queen call queenLegalMoves
			legalMoves.addAll(queenLegalMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("King")) { //if piece is king call kingLegalMoves
			legalMoves = kingLegalMoves(location, board, piece);

		} else if (piece.contains("pawn") || piece.contains("Pawn")) { //if piece is pawn call pawnLegalMoves and save it to legalMoves
			legalMoves.addAll(pawnLegalMoves(location, board, piece));
		}

		return legalMoves; //return legalMoves
	}

	public ArrayList<Coordinate> getEnemyMoves(boolean whiteTeam, String[][] board){
		
		String[][] enemyBoard;
		ArrayList<Coordinate> enemyMoves = new ArrayList<>();

		if(whiteTeam){
			enemyBoard = getEnemyTeam(board, "a"); //lowercase 'A' is just a place holder for the team value 
		}

		else { //black team
			enemyBoard = getEnemyTeam(board, "A"); //uppercase 'a' is just a place holder for the team value
		}

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){

				if(enemyBoard[i][j].contains("pawn") || enemyBoard[i][j].contains("Pawn")){
					enemyMoves.addAll(getPawnAttack(new Coordinate(i,j), board, whiteTeam));
				}
				
				else if (!(enemyBoard[i][j].contains("pawn") || enemyBoard[i][j].contains("Pawn"))) {
					enemyMoves.addAll(getLegalMoves(new Coordinate(i,j), enemyBoard[i][j], board));
				}

			}
		}

		return enemyMoves;
	}

	public ArrayList<Move> myMoves(boolean whiteTeam, String[][] board){
		
		//even elements in arraylist will be start point, odd will be end points 
		
		ArrayList<Move> friendlyMoves = new ArrayList<Move>();

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				if(((whiteTeam == true) && (board[i][j].charAt(0)) == Character.toUpperCase(board[i][j].charAt(0))) || ((whiteTeam == false) && (board[i][j].charAt(0) == Character.toUpperCase(board[i][j].charAt(0))))){
				
					ArrayList<Coordinate> endPoints = getLegalMoves(new Coordinate(i,j), board[i][j], board);

					if(endPoints.size() > 0){//this means that the piece is mobile

						for(int k = 0; k < endPoints.size(); k++){
							friendlyMoves.add(new Move(new Coordinate(i,j), endPoints.get(k))); //adding all the possible movements from a valid root, at (i,j)
						}
					}
				}
			}
		}

		return friendlyMoves;
	}



	public static ArrayList<Coordinate> getPawnAttack(Coordinate location, String[][] board, boolean whiteTeam){

		ArrayList<Coordinate> pawnMoves = new ArrayList<>();

		int x = location.getX();
		int y = location.getY();

		if (!whiteTeam) { // the piece is uppercase
																			
			if (y > 0) { //when its 0 it should become a queen

				if ((x > 0) && (board[y - 1][x - 1] != " ")) { // checking if there's a piece diagnolly available -> up and to the left					
					pawnMoves.add(new Coordinate(y - 1, x - 1));
				}

				if ((x < 7) && (board[y - 1][x + 1] != " ")) { // checking if there's a piece diagnolly available --> up and to the right
					pawnMoves.add(new Coordinate(y - 1, x + 1));
				}
			}
		}

		else if (whiteTeam) {

			if (y < 7) { //when its 7 it should become a queen

				if ((x > 0) && (board[y + 1][x - 1] != " ")) { // checking if there's a piece diagnolly available -> down and to the left						
					pawnMoves.add(new Coordinate(y + 1, x - 1));
				}

				if ((x < 7) && (board[y + 1][x + 1] != " ")) { // checking if there's a piece diagnolly available --> down and to the right					
					pawnMoves.add(new Coordinate(y + 1, x + 1));
				}
			}
		}

		return pawnMoves;
	}

	public static ArrayList<Coordinate> kingLegalMoves(Coordinate location, String[][] board, String piece) { //method that returns the legalMoves of a king

		ArrayList<Coordinate> kingMoves = new ArrayList<Coordinate>(); //creates an ArrayList to store the legal moves in

		int[][] squareMaps = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } }; //creating a 2D array of "offsets" to iterate through and create coordinates with
		String[][] myTeam = getTeam(board, piece); //get teamMates to check for legal moves with

		int clickCoordY = location.getY(); //get the x and y stored in the coordinate of the source location
		int clickCoordX = location.getX();

		for (int i = 0; i < squareMaps.length; i++) { //iterate through the array of offsets and creates 
			if (!isOutOfBounds((clickCoordY - squareMaps[i][0]), clickCoordX - squareMaps[i][1])
					&& (myTeam[clickCoordY - squareMaps[i][0]][clickCoordX - squareMaps[i][1]] == " ")) { //adds the new point if the point is in bounds and if there are no allies there
				kingMoves.add(new Coordinate(clickCoordY - squareMaps[i][0], clickCoordX - squareMaps[i][1]));
			}
		}

		return kingMoves; //returns arrayList of legal moves

	}

	public static ArrayList<Coordinate> pawnLegalMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> pawnMoves = new ArrayList<Coordinate>();
		String[][] teamMate = getTeam(board, piece);
		String[][] enemyTeam = getEnemyTeam(board, piece);

		int y = location.getY();
		int x = location.getX();


		if ((piece.charAt(0) == Character.toLowerCase(piece.charAt(0)))) { // we are checking if the first character in the piece is lowercase
																			
			if (y > 0) { //when its 0 it should become a queen

				if ((y == 6) && (board[4][x] == " ") && (board[3][x] == " ")) { // pawns get to move 2 blocks on first move (P1 varient)
					pawnMoves.add(new Coordinate(4, x));
				}

				if (board[y - 1][x] == " ") {
					pawnMoves.add(new Coordinate(y - 1, x));
				}

				if ((x > 0) && (enemyTeam[y - 1][x - 1] != " ")) { // checking if there's a piece diagnolly available -> up
																// and to the left
					pawnMoves.add(new Coordinate(y - 1, x - 1));
				}

				if ((x < 7) && (enemyTeam[y - 1][x + 1] != " ")) { // checking if there's a piece diagnolly available -->
																// up and to the right
					pawnMoves.add(new Coordinate(y - 1, x + 1));
				}
			}

		}

		else if ((piece.charAt(0) == Character.toUpperCase(piece.charAt(0)))) {

			if (y < 7) {

				if ((y == 1) && (board[3][x] == " ") & board[2][x] == " ") {
					pawnMoves.add(new Coordinate(3, x)); // legalising 2 square movement on the first move
				}

				if ((board[y + 1][x] == " ")) {
					pawnMoves.add(new Coordinate(y + 1, x));
				}

				if ((x > 0) && (enemyTeam[y + 1][x - 1] != " ")) { // checking if there's a piece diagnolly available ->
																// down and to the left
					pawnMoves.add(new Coordinate(y + 1, x - 1));
				}

				if ((x < 7) && (enemyTeam[y + 1][x + 1] != " ")) { // checking if there's a piece diagnolly available -->
																// down and to the right
					pawnMoves.add(new Coordinate(y + 1, x + 1));
				}
			}
		}

		return pawnMoves;

	}

	public static ArrayList<Coordinate> getRookMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		String[][] myTeam = getTeam(board, piece);
		String[][] enemyTeam = getEnemyTeam(board, piece);
		int x = location.getX();
		int y = location.getY();


		// Rules: it must be straight lines
		// => we have four directions to check
		// starts at 1 because we dont want it to return itself

		// System.out.println(y + " , " + x);

		// ROOKS, BISHOP, QUEEN

		for (int i = x + 1, j = y; i < 8; i++) { // Generates all possible moves right
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) { //checks if the current branch is out of bounds or contains a teamate or is out of bounds
				moves.add(new Coordinate(j, i)); //adds a coordinate to the current list
			}
			
			if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
		}

		for (int i = x - 1, j = y; i > -1; i--) { // Generates all possible moves going left
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) { //checks if the current branch is out of bounds or contains a teamate or is out of bounds
				moves.add(new Coordinate(j, i)); //adds a coordinate to the current list
			}
			
			if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
		}

		for (int j = y + 1, i = x; j < 8; j++) { // Generates all possible moves going down
			
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) { //checks if the current branch is out of bounds or contains a teamate or is out of bounds
				moves.add(new Coordinate(j, i)); //adds a coordinate to the current list
			}
			
			if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
			
		}
		for (int j = y - 1, i = x; j > -1; j--) { // Generates all possible moves going up
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) { //checks if the current branch is out of bounds or contains a teamate or is out of bounds
				moves.add(new Coordinate(j, i)); //adds a coordinate to the current list
			}

			if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}

		}
		
		return moves; //return list
	}

	public static ArrayList<Coordinate> queenLegalMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> moves = getRookMoves(location, board, piece); //initalizing the array to store the legal moves and get rook legal moves for the source coordinate
		moves.addAll(bishopLegalMoves(location, board, piece)); //add the bishop legal moves the to the moves array

		return moves; //return the list
	}

	private static ArrayList<Coordinate> bishopLegalMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> coords = new ArrayList<Coordinate>(); //inizalizing variables
		int x = location.getX();
		int y = location.getY();

		String[][] myTeam = getTeam(board, piece); //getting a board with just allies to use for move generation
		String[][] enemyTeam = getEnemyTeam(board, piece); //getting a board of the enemies for finding the termination conditions of our branches

		for (int j = x + 1, i = y + 1; j < board.length && i < board.length; j++, i++) { //creates moves for bottom right diagonal
			if ((!isOutOfBounds(i, j)) && (myTeam[i][j].equals(" "))) { //making sure there is no teammate the move isnt out of bounds
				coords.add(new Coordinate(i, j)); //adding coordinate to the list
			}

			if ((enemyTeam[i][j] != " ") || (isOutOfBounds(i, j)) || (myTeam[i][j] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
		}
		for (int j = x - 1, i = y + 1; j > -1 && i < board.length; j--, i++) { //creates moves for bottom left diagonal
			if ((!isOutOfBounds(i, j)) && (myTeam[i][j].equals(" "))) { //making sure there is no teammate/ the move isnt out of bounds
				coords.add(new Coordinate(i, j));
			} 
			if ((enemyTeam[i][j] != " ") || (isOutOfBounds(i, j)) || (myTeam[i][j] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
		}
		for (int j = x - 1, i = y - 1; j > -1 && i > -1; j--, i--) { //creates moves for top left diagonal
			if ((!isOutOfBounds(i, j)) && (myTeam[i][j].equals(" "))) { //making sure there is no teammate/ the move isnt out of bounds
				coords.add(new Coordinate(i, j));
			}

			if ((enemyTeam[i][j] != " ") || (isOutOfBounds(i, j)) || (myTeam[i][j] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
		}
		for (int j = x + 1, i = y - 1; j < board.length && i > -1; j++, i--) { //creates moves for top right diagonal
			if ((!isOutOfBounds(i, j)) && (myTeam[i][j].equals(" "))) { //making sure there is no teammate/ the move isnt out of bounds
				coords.add(new Coordinate(i, j));
			} 

			if ((enemyTeam[i][j] != " ") || (isOutOfBounds(i, j)) || (myTeam[i][j] != " ")){ //terminates branch if the above conditions are true OR an enemy player is present on the branch
				break;
			}
		}
		return coords; //returning the arrayList of legalMoves

	}

	public static ArrayList<Coordinate> knightLegalMoves(Coordinate location, String[][] board, String piece) {

		int[][] squareMaps = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { -2, -1 }, { -1, -2 }, { 1, -2 } }; //creating a 2D array of "offsets" that the knight can move in
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();//inizalizing variables
		int clickCoordY = location.getY();
		int clickCoordX = location.getX();
		String[][] myTeam = getTeam(board, piece); //getting a board with just allies to use for move generation

		for (int i = 0; i < squareMaps.length; i++) { //iterating through the offsets and adding them to the source coord to create legalMoves

			if ((!isOutOfBounds(clickCoordY + squareMaps[i][0], clickCoordX + squareMaps[i][1]))
					&& myTeam[clickCoordY + squareMaps[i][0]][clickCoordX + squareMaps[i][1]].equals(" ")) { //making sure that the move is legal

				moves.add(new Coordinate(clickCoordY + squareMaps[i][0], clickCoordX + squareMaps[i][1])); //adding the moves
			}
		}

		return moves; //returning the legal moves
	}

	private static boolean isOutOfBounds(int y, int x) { //returns true or false based on if the coordinate is between 0-7

		if ((x >= 8 || x < 0) || (y >= 8 || y < 0))
			return true;

		else
			return false;
	}

	public static String[][] getTeam(String[][] board, String piece) { //returns a version of the board state with just allies

		String[][] teamBoard = deepCopyOf(board); // we dont want to override our current board

		if (piece.charAt(0) == Character.toLowerCase(piece.charAt(0))) { // we are checking if the first character in
																			// the piece is lowercase

			for (int i = 0; i < teamBoard.length; i++) { // row

				for (int j = 0; j < teamBoard[i].length; j++) { // collumns

					if (teamBoard[i][j].charAt(0) == (Character.toUpperCase(teamBoard[i][j].charAt(0)))) {
						teamBoard[i][j] = " "; // get rid off it because its upper case and thus not on the same team
					}

				}
			}
		}

		else { // if the piece is an uppercase type type

			for (int i = 0; i < teamBoard.length; i++) { // row

				for (int j = 0; j < teamBoard[i].length; j++) { // collumns

					if (teamBoard[i][j].charAt(0) == (Character.toLowerCase(teamBoard[i][j].charAt(0)))) {
						teamBoard[i][j] = " "; // get rid off it because its lower case and thus not on the same team
					}
				}
			}
		}


		return teamBoard;
	}

	public static String[][] getEnemyTeam(String[][] board, String piece) { //returns a version of the board state with just the opponents

		String[][] teamBoard = deepCopyOf(board); // we dont want to override our current board

		if (piece.charAt(0) == Character.toLowerCase(piece.charAt(0))) { // we are checking if the first character in
																			// the piece is lowercase

			for (int i = 0; i < teamBoard.length; i++) { // row

				for (int j = 0; j < teamBoard[i].length; j++) { // collumns

					if (teamBoard[i][j].charAt(0) == (Character.toLowerCase(teamBoard[i][j].charAt(0)))) {
						teamBoard[i][j] = " "; // get rid off it because its upper case and thus not on the same team
					}

				}
			}
		}

		else { // if the piece is an uppercase type type

			for (int i = 0; i < teamBoard.length; i++) { // row

				for (int j = 0; j < teamBoard[i].length; j++) { // collumns

					if (teamBoard[i][j].charAt(0) == (Character.toUpperCase(teamBoard[i][j].charAt(0)))) {
						teamBoard[i][j] = " "; // get rid off it because its lower case and thus not on the same team
					}
				}
			}
		}

		return teamBoard;
	}

	private static String[][] deepCopyOf(String board[][]) { //creates a copy of the current board so we dont mutate the original one

		String[][] arr = new String[8][8]; // revise this later to make for more inclusive OOC

		for (int i = 0; i < 8; i++) {
			arr[i] = Arrays.copyOf(board[i], board[i].length);
		}

		return arr;
	}
}
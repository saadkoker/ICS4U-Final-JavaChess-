import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Piece{


	//private Coordinate coordinate;


	public int[][] getLegalMoves(int[] coordinate, String piece, String board[][]){ //takes in string since it can just take in the name of the file found in the board array

		int[][] legalMoves = new int[92][2];

		if(piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")){
			legalMoves = getRookMoves(coordinate, board, piece);
		}
		else if (piece.equalsIgnoreCase("Knight1") || piece.equalsIgnoreCase("Knight2")) {
			legalMoves = knightLegalMoves(coordinate);
		}
		else if (piece.equalsIgnoreCase("Bishop1") || piece.equalsIgnoreCase("Bishop2")) {
			legalMoves = bishopLegalMoves(coordinate, board, piece);
		}
		else if (piece.equalsIgnoreCase("Queen")) {
			legalMoves = queenLegalMoves(coordinate, board, piece);
		}
		else if (piece.equalsIgnoreCase("King")) {
			System.out.println("king moved");
			legalMoves = kingLegalMoves(coordinate, piece, board);
		}
		else if (piece.contains("pawn") || piece.contains("Pawn")) { //sets legal moves for pawns
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
	public static int[][] getRookMoves(int[] coord, String[][] board, String piece){

			ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
			int x = coord[1];
			int y = coord[0];
			int pos = 0;

			//Rules: it must be straight lines
			// => we have four directions to check
			//starts at 1 because we dont want it to return itself

			//System.out.println(y + " , " + x);

			for (int i = 1; i < 8; i++){

				if (((x+i) <= 7) && (teamInPos(y, x, y, x+i, board, piece, "right") == false)){ //remember boolean order of operations here!
					System.out.println("we movin right");
					moves.add(new Coordinate((x+i), y));
					pos++;
				}

				if (((x-i) >= 0) && (teamInPos(y, x, y, x-i, board, piece, "left") == false)){
					System.out.println("we movin left");
					moves.add(new Coordinate((x-i), y));
					pos++;
				}

				if (((y+i) <= 7) && (teamInPos(y, x, y+i, x, board, piece, "down") == false)){
					System.out.println("we movin down");
					moves.add(new Coordinate(x, (y+i)));
					pos++;
				}

				if (((y-i) >= 0) && (teamInPos(y, x, y-i, x, board, piece, "up") == false)){
					System.out.println("we movin up");
					moves.add(new Coordinate(x, (y-i)));
					pos++;
				}
			}

			int[][] finalCoords = new int[moves.size()][2];

			for (int i = 0; i < finalCoords.length; i++) {
				finalCoords[i][0] = moves.get(i).getX();
				finalCoords[i][1] = moves.get(i).getY();
			}
			return finalCoords;
			
	}

	private static int[][] knightLegalMoves(int[] coordinate){
		
		int[][] squareMaps = {{-2, 1},{-1, 2},{1, 2},{2, 1},{2, -1},{-2, -1},{-1, -2},{1,-2}};
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int clickCoordY = coordinate[0];
		int clickCoordX = coordinate[1];
		int[][] coords = new int[8][2];

		for (int i = 0; i < squareMaps.length; i++) {
			moves.add(new Coordinate(clickCoordY + squareMaps[i][0], clickCoordX + squareMaps[i][1]));
		}

		for (int i = 0; i < moves.size(); i++) {
			if(moves.get(i).getX() == -1){
				moves.remove(i);
			}
		}
		int[][] finalCoords = new int[moves.size()][2];

		for (int i = 0; i < finalCoords.length; i++) {
			finalCoords[i][0] = moves.get(i).getX();
			finalCoords[i][1] = moves.get(i).getY();
		}
		return finalCoords;
	}
	

	
	private static int[][] bishopLegalMoves(int[] coordinate, String[][] board, String piece){

		int[][] moves = new int[28][2];
		
		int y_pos = coordinate[0];
		int x_pos = coordinate[1];
		
		String[][] team = getTeam(board, piece);
		
		for(int i = 0; i < 28;) { //we're adding all possible diagnol movements here
			for(int j = 1; j < 8; j++) {
				
				if((!isOutOfBounds((y_pos+j), (x_pos+j))) && (team[y_pos+j][x_pos+j].equals(" "))) {
					moves[i][0] = y_pos+j;
					moves[i][1] = x_pos+j;
					i++;
				}
				
				if((!isOutOfBounds((y_pos+j), (x_pos-j))) && (team[y_pos+j][x_pos-j].equals(" "))) {
					moves[i][0] = y_pos+j;
					moves[i][1] = x_pos-j;
					i++;
				}
				
				if((!isOutOfBounds((y_pos-j), (x_pos-j))) && (team[y_pos-j][x_pos-j].equals(" "))) {
					moves[i][0] = y_pos-j;
					moves[i][1] = x_pos-j;
					i++;
				}
				
				if((!isOutOfBounds((y_pos-j), (x_pos+j))) && (team[y_pos-j][x_pos+j].equals(" "))) {
					moves[i][0] = y_pos-j;
					moves[i][1] = x_pos+j;
					i++;
				}
			}
		}
		
		return moves;
		
	}
	
	private static int[][] queenLegalMoves(int[] coordinate, String[][] board, String piece){

		int[][] moves = new int[92][2];
		int[][] arrOne = bishopLegalMoves(coordinate, board, piece);
		int[][] arrTwo = getRookMoves(coordinate, board, piece);
		
		for(int i = 0; i < arrOne.length; i++) { //making those diagonal moves
			moves[i][0] = arrOne[i][0];
			moves[i][1] = arrOne[i][1];
		}	
		
		for(int i = arrOne.length; i < 92; i++) { //making those diagonal moves
			moves[i][0] = arrTwo[i][0];
			moves[i][1] = arrTwo[i][1];
		}	
		
		return moves;
	}
	
	private static int[][] kingLegalMoves(int[] coordinate, String piece, String[][] board){

		int[][] squareMaps = {{1, 0}, {0, 1},{-1, 0},{0, -1},{1, 1},{-1, 1},{-1, -1},{1, -1}};
		int clickCoordY = coordinate[0]; //idk whether y or x is supposed to be first but id rather kms then figure out
		int clickCoordX = coordinate[1];
		int[][] coords = new int[8][2];

		//TODO: run team mate checks
		for (int i = 0; i < squareMaps.length; i++) {
			coords[i][0] = clickCoordY - squareMaps[i][0];
			coords[i][1] = clickCoordX - squareMaps[i][1];
		}

		removeOutOfBounds(coords); //this method changes any out of bounds coordinates in the array to [-1,-1]
		//System.out.println("king coords" + Arrays.deepToString(coords) + "\n" + "king coords" + Arrays.deepToString(finalCoords));
		
		return coords; //return the array

	}

	private static int[][] lines(int[] coordinate, String[][] board, String piece){

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

		possible = removeArbValue(possible);
		return possible;
	}


	public static boolean teamInPos(int y1, int x1, int y2, int x2, String[][] board, String piece, String direction){

		boolean team_mate = false;
		String[][] myTeam = getTeam(board, piece);

		if (direction.equals("down")){

			for(int i = y1+1; i < y2; i++){

				if (myTeam[i][x1] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + x2 + " it's a " + myTeam[i][x2]);
					return true;
				}
			}
		}

		if (direction.equals("up")){

			for(int i = y2; i < y1; i++){

				if (myTeam[i][x1] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + x2 + " it's a " + myTeam[i][x2]);
					return true;
				}
			}
		}

		if (direction.equals("left")){

			for(int i = x2; i <= x1-1; i++){

				if (myTeam[y2][i] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + y2 + " and x: " + i + " it's a " + myTeam[y2][i]);
					return true;
				}
			}
		}

		if (direction.equals("right")){

			for(int i = x1+1; i < x2; i++){

				if (myTeam[y2][i] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + y2 + " and x: " + i + " it's a " + myTeam[y2][i]);
					return true;
				}
			}
		}
		
		if (direction.equals("diagOne")) {
			
			for(int i = x1+1; i < x2; i++) { //this is the easier variant of the diagonal relationship
				
				if (myTeam[i][i] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + i + " it's a " + myTeam[i][i]);
					return true;
				}
			}
		}
		
		if (direction.equals("diagTwo")) {
			
			
			for(int i = y2+1, j = x1; i < y1 && j < x2; i++, j++) { //this is the harder variant of the diagonal relationship
				
				if (myTeam[i][j] != " "){
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + j + " it's a " + myTeam[i][j]);
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

	private static boolean isOutOfBounds(int y, int x) {

		if ((x >= 8 || x <= 0) || (y >= 8 || y <= 0)) 
			return true;
		
		else
			return false;
	}
	
	private static void removeOutOfBounds(int[][] coords){

		for (int i = 0; i < coords.length; i++) {
				if (isOutOfBounds(coords[i][0], coords[i][1])) {
					coords[i][0] = -1;
					coords[i][1] = -1;
				}
			}
		
		//return coords;
	}
	
	private static void removeInvalidPieces(int[][] coords, int y1, int x1, String[][] board, String piece, String direction){
		
		removeOutOfBounds(coords);
		
		for (int i = 0; i < coords.length; i++) {
			if(teamInPos(y1, x1, coords[i][0], coords[i][1], board, piece, direction)) {
				coords[i][0] = -1;
				coords[i][1] = -1;
			}
		}
		
	}
	
	private static int[][] removeArbValue(int[][] coords){
		
		int n = 0;
		
		for(int j = 0; j < coords[0].length; j++) {
			
			if (coords[0][j] == -1) {
				n++;
			}
		}
		
		int[][] freshArr = new int[2][coords.length-n];
		
		for(int i = 0; i < 2; i++) {
			Arrays.sort(coords[i]);
			freshArr[i] = Arrays.copyOfRange(coords[i], n+1, coords.length);
			System.out.println("new arr row: " + i + " is: " + Arrays.toString(freshArr[i]));
		}
		
		return freshArr;
	}
}

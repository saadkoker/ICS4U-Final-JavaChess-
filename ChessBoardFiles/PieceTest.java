import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class PieceTest {

	public int[][] getLegalMoves(Coordinate location, String piece, String[][] board) {

		ArrayList<Coordinate> legalMoves = new ArrayList<Coordinate>();

		if (piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")) {
			Collections.copy(legalMoves, getRookMoves(location, board, piece));
		} else if (piece.equalsIgnoreCase("Knight1") || piece.equalsIgnoreCase("Knight2")) {
			Collections.copy(legalMoves, knightLegalMoves(location, board, piece));
		} else if (piece.equalsIgnoreCase("Bishop1") || piece.equalsIgnoreCase("Bishop2")) {
			Collections.copy(legalMoves, bishopLegalMoves(location, board, piece));
		} else if (piece.equalsIgnoreCase("Queen")) {
			Collections.copy(legalMoves, queenLegalMoves(location, board, piece));
		} else if (piece.equalsIgnoreCase("King")) {
			System.out.println("king moved");
			legalMoves = kingLegalMoves(location, piece, board);
		} else if (piece.contains("pawn") || piece.contains("Pawn")) { // sets legal moves for pawns
			Collections.copy(legalMoves, pawnLegalMoves(location, board, piece));
		}

		int[][] finalSend = new int[legalMoves.size()][2];

		for (int i = 0; i < legalMoves.size(); i++) {
			finalSend[i][0] = legalMoves.get(i).getX();
			finalSend[i][1] = legalMoves.get(i).getY();
		}
		return finalSend;
	}

	public static ArrayList<Coordinate> getRookMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		String[][] myTeam = getTeam(board, piece);
		int x = location.getX();
		int y = location.getY();

		// Rules: it must be straight lines
		// => we have four directions to check
		// starts at 1 because we dont want it to return itself

		// System.out.println(y + " , " + x);

		// ROOKS, BISHOP, QUEEN

		for (int i = x + 1, j = y; i < board.length; i++) { // Generates all possible moves going up
			if (!isOutOfBounds(i, j) && myTeam[i][j] == " ") {
				moves.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		for (int i = x - 1, j = y; i > -1; i--) { // Generates all possible moves going down
			if (!isOutOfBounds(i, j) && myTeam[i][j] == " ") {
				moves.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		for (int i = y + 1, j = x; i < board.length; i++) { // Generates all possible moves going right
			if (!isOutOfBounds(j, i) && myTeam[j][i] == " ") {
				moves.add(new Coordinate(j, i));
			} else {
				break;
			}
		}
		for (int i = y - 1, j = x; i < board.length; i++) { // Generates all possible moves going left
			if (!isOutOfBounds(j, i) && myTeam[j][i] == " ") {
				moves.add(new Coordinate(j, i));
			} else {
				break;
			}
		}
		return moves;
	}
	public static ArrayList<Coordinate> queenLegalMoves(Coordinate location, String[][] board, String piece) {
		
		ArrayList<Coordinate> moves = getRookMoves(location, board, piece);
		moves.addAll(bishopLegalMoves(location, board, piece));

		return moves;
	}

	private static ArrayList<Coordinate> bishopLegalMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> coords = new ArrayList<Coordinate>();
		int x = location.getX();
		int y = location.getY();

		String[][] teamBoard = getTeam(board, piece);

		for (int j = x + 1, i = y + 1; j < board.length && i < board.length; j++, i++) {
			if ((!isOutOfBounds(j, i)) && (teamBoard[j][i].equals(" "))) {
				coords.add(new Coordinate(j, i));
			} else {
				break;
			}
		}
		for (int j = x - 1, i = y + 1; j > -1 && i < board.length; j--, i++) {
			if ((!isOutOfBounds(j, i)) && (teamBoard[j][i].equals(" "))) {
				coords.add(new Coordinate(j, i));
			} else {
				break;
			}
		}
		for (int j = x - 1, i = y - 1; j > -1 && i > -1; j--, i--) {
			if ((!isOutOfBounds(j, i)) && (teamBoard[j][i].equals(" "))) {
				coords.add(new Coordinate(j, i));
			} else {
				break;
			}
		}
		for (int j = x + 1, i = y - 1; j < board.length && i > -1; j++, i--) {
			if ((!isOutOfBounds(j, i)) && (teamBoard[j][i].equals(" "))) {
				coords.add(new Coordinate(j, i));
			} else {
				break;
			}
		}
		return coords;
	}
	public static ArrayList<Coordinate> knightLegalMoves(Coordinate location, String[][] board, String piece) {

		int[][] squareMaps = {{-2, 1},{-1, 2},{1, 2},{2, 1},{2, -1},{-2, -1},{-1, -2},{1,-2}};
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int clickCoordY = location.getX();
		int clickCoordX = location.getY();
		int[][] coords = new int[8][2];

		for (int i = 0; i < squareMaps.length; i++) {
			moves.add(new Coordinate(clickCoordY + squareMaps[i][0], clickCoordX + squareMaps[i][1]));
		}

		for (int i = 0; i < moves.size(); i++) {
			if(moves.get(i).getX() == -1){
				moves.remove(i);
			}
		}
		return moves;
	}

	public static boolean teamInPos(int y1, int x1, int y2, int x2, String[][] board, String piece, String direction) {

		boolean team_mate = false;
		String[][] myTeam = getTeam(board, piece);

		if (direction.equals("down")) {

			for (int i = y1 + 1; i < y2; i++) {

				if (myTeam[i][x1] != " ") {
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + x2 + " it's a " + myTeam[i][x2]);
					return true;
				}
			}
		}

		if (direction.equals("up")) {

			for (int i = y2; i < y1; i++) {

				if (myTeam[i][x1] != " ") {
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + x2 + " it's a " + myTeam[i][x2]);
					return true;
				}
			}
		}

		if (direction.equals("left")) {

			for (int i = x2; i <= x1 - 1; i++) {

				if (myTeam[y2][i] != " ") {
					team_mate = true;
					System.out.println("You have a team mate @ y: " + y2 + " and x: " + i + " it's a " + myTeam[y2][i]);
					return true;
				}
			}
		}

		if (direction.equals("right")) {

			for (int i = x1 + 1; i < x2; i++) {

				if (myTeam[y2][i] != " ") {
					team_mate = true;
					System.out.println("You have a team mate @ y: " + y2 + " and x: " + i + " it's a " + myTeam[y2][i]);
					return true;
				}
			}
		}

		if (direction.equals("diagOne")) {

			for (int i = x1 + 1; i < x2; i++) { // this is the easier variant of the diagonal relationship

				if (myTeam[i][i] != " ") {
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + i + " it's a " + myTeam[i][i]);
					return true;
				}
			}
		}

		if (direction.equals("diagTwo")) {

			for (int i = y2 + 1, j = x1; i < y1 && j < x2; i++, j++) { // this is the harder variant of the diagonal
																		// relationship

				if (myTeam[i][j] != " ") {
					team_mate = true;
					System.out.println("You have a team mate @ y: " + i + " and x: " + j + " it's a " + myTeam[i][j]);
					return true;
				}
			}

		}

		return team_mate;
	}

	private static boolean isOutOfBounds(int y, int x) {

		if ((x >= 8 || x <= 0) || (y >= 8 || y <= 0))
			return true;

		else
			return false;
	}

	public static String[][] getTeam(String[][] board, String piece) {

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

		// System.out.println("our team: " + Arrays.deepToString(teamBoard));

		return teamBoard;
	}

	private static String[][] deepCopyOf(String board[][]) {

		String[][] arr = new String[8][8]; // revise this later to make for more inclusive OOC

		for (int i = 0; i < 8; i++) {
			arr[i] = Arrays.copyOf(board[i], board[i].length);
		}

		return arr;
	}
}
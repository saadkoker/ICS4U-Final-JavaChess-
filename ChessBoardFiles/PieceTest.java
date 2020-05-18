import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class PieceTest {

	public ArrayList<Coordinate> getLegalMoves(Coordinate location, String piece, String[][] board) {

		ArrayList<Coordinate> legalMoves = new ArrayList<Coordinate>();

		if (piece.equalsIgnoreCase("Rook1") || piece.equalsIgnoreCase("Rook2")) {
			// Collections.copy(legalMoves, getRookMoves(location, board, piece));
			legalMoves.addAll(getRookMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("Knight1") || piece.equalsIgnoreCase("Knight2")) {
			// Collections.copy(legalMoves, knightLegalMoves(location, board, piece));
			legalMoves.addAll(knightLegalMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("Bishop1") || piece.equalsIgnoreCase("Bishop2")) {
			// Collections.copy(legalMoves, bishopLegalMoves(location, board, piece));
			legalMoves.addAll(bishopLegalMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("Queen")) {
			// Collections.copy(legalMoves, queenLegalMoves(location, board, piece));
			legalMoves.addAll(queenLegalMoves(location, board, piece));

		} else if (piece.equalsIgnoreCase("King")) {
			System.out.println("king moved");
			legalMoves = kingLegalMoves(location, board, piece);

		} else if (piece.contains("pawn") || piece.contains("Pawn")) { // sets legal moves for pawns
			// Collections.copy(legalMoves, pawnLegalMoves(location, board, piece));
			legalMoves.addAll(pawnLegalMoves(location, board, piece));
		}

		int[][] finalSend = new int[legalMoves.size()][2];

		for (int i = 0; i < legalMoves.size(); i++) {
			finalSend[i][1] = legalMoves.get(i).getX();
			finalSend[i][0] = legalMoves.get(i).getY();
		}
		return legalMoves;
	}

	public static ArrayList<Coordinate> kingLegalMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> kingMoves = new ArrayList<Coordinate>();

		int[][] squareMaps = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 }, { 1, 1 }, { -1, 1 }, { -1, -1 }, { 1, -1 } };
		String[][] myTeam = getTeam(board, piece);

		int clickCoordY = location.getY();
		int clickCoordX = location.getX();

		for (int i = 0; i < squareMaps.length; i++) {
			if (!isOutOfBounds((clickCoordY - squareMaps[i][0]), clickCoordX - squareMaps[i][1])
					&& (myTeam[clickCoordY - squareMaps[i][0]][clickCoordX - squareMaps[i][1]] == " ")) {
				kingMoves.add(new Coordinate(clickCoordY - squareMaps[i][0], clickCoordX - squareMaps[i][1]));
			}
		}

		return kingMoves;

	}

	public static ArrayList<Coordinate> pawnLegalMoves(Coordinate location, String[][] board, String piece) {

		ArrayList<Coordinate> pawnMoves = new ArrayList<Coordinate>();

		int y = location.getY();
		int x = location.getX();
		int count = 0;

		if ((y == 1) && (board[3][x] == " ")) {
			pawnMoves.add(new Coordinate(3, x)); // legalising 2 square movement on the first move
			count++;
		}

		else if ((y == 6) && (board[4][x] == " ")) { // pawns get to move 2 blocks on first move (P1 varient)
			pawnMoves.add(new Coordinate(4, x));
			count++;
		}

		if ((piece.charAt(0) == Character.toLowerCase(piece.charAt(0)))) { // we are checking if the first character in
																			// the piece is lowercase

			if (y > 0) {

				if (board[y - 1][x] == " ") {
					pawnMoves.add(new Coordinate(y - 1, x));
					count++;
				}

				if ((x > 0) && (board[y - 1][x - 1] != " ")) { // checking if there's a piece diagnolly available -> up
																// and to the left
					pawnMoves.add(new Coordinate(y - 1, x - 1));
					count++;
				}

				if ((x <= 7) && (board[y - 1][x + 1] != " ")) { // checking if there's a piece diagnolly available -->
																// up and to the right
					pawnMoves.add(new Coordinate(y - 1, x + 1));
					count++;
				}
			}
		}

		else if ((piece.charAt(0) == Character.toUpperCase(piece.charAt(0)))) {

			if (y <= 7) {

				if ((board[y + 1][x] == " ")) {
					pawnMoves.add(new Coordinate(y + 1, x));
					count++;
				}

				if ((x > 0) && (board[y + 1][x - 1] != " ")) { // checking if there's a piece diagnolly available ->
																// down and to the left
					pawnMoves.add(new Coordinate(y + 1, x - 1));
					count++;
				}

				if ((x <= 7) && (board[y + 1][x + 1] != " ")) { // checking if there's a piece diagnolly available -->
																// down and to the right
					pawnMoves.add(new Coordinate(y + 1, x + 1));
					count++;
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
		int counter = 0;

		// Rules: it must be straight lines
		// => we have four directions to check
		// starts at 1 because we dont want it to return itself

		// System.out.println(y + " , " + x);

		// ROOKS, BISHOP, QUEEN

		for (int i = x + 1, j = y; i < 8; i++) { // Generates all possible moves right
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) {
				moves.add(new Coordinate(j, i));
			
				if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){
					break;
				}
			}
		}
		for (int i = x - 1, j = y; i > -1; i--) { // Generates all possible moves going left
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) {
				moves.add(new Coordinate(j, i));
			
				if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){
					break;
				}
			}
		}
		for (int j = y + 1, i = x; j < 8; j++) { // Generates all possible moves going down
			
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) {
				moves.add(new Coordinate(j, i));
			
				if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){
					break;
				}
			}
		}
		for (int j = y - 1, i = x; j < 8; j--) { // Generates all possible moves going up
			if (!isOutOfBounds(j, i) && ((myTeam[j][i] == " "))) {
				moves.add(new Coordinate(j, i));
			
				if ((enemyTeam[j][i] != " ") || (isOutOfBounds(j, i)) || (myTeam[j][i] != " ")){
					break;
				}
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
			if ((!isOutOfBounds(i, j)) && (teamBoard[i][j].equals(" "))) {
				coords.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		for (int j = x - 1, i = y + 1; j > -1 && i < board.length; j--, i++) {
			if ((!isOutOfBounds(i, j)) && (teamBoard[i][j].equals(" "))) {
				coords.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		for (int j = x - 1, i = y - 1; j > -1 && i > -1; j--, i--) {
			if ((!isOutOfBounds(i, j)) && (teamBoard[i][j].equals(" "))) {
				coords.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		for (int j = x + 1, i = y - 1; j < board.length && i > -1; j++, i--) {
			if ((!isOutOfBounds(i, j)) && (teamBoard[i][j].equals(" "))) {
				coords.add(new Coordinate(i, j));
			} else {
				break;
			}
		}
		return coords;

	}

	public static ArrayList<Coordinate> knightLegalMoves(Coordinate location, String[][] board, String piece) {

		int[][] squareMaps = { { -2, 1 }, { -1, 2 }, { 1, 2 }, { 2, 1 }, { 2, -1 }, { -2, -1 }, { -1, -2 }, { 1, -2 } };
		ArrayList<Coordinate> moves = new ArrayList<Coordinate>();
		int clickCoordY = location.getY();
		int clickCoordX = location.getX();
		int[][] coords = new int[8][2];
		String[][] myTeam = getTeam(board, piece);

		for (int i = 0; i < squareMaps.length; i++) {

			if ((!isOutOfBounds(clickCoordY + squareMaps[i][0], clickCoordX + squareMaps[i][1]))
					&& myTeam[clickCoordY + squareMaps[i][0]][clickCoordX + squareMaps[i][1]].equals(" ")) {

				moves.add(new Coordinate(clickCoordY + squareMaps[i][0], clickCoordX + squareMaps[i][1]));
			}
		}

		return moves;
	}

	private static boolean isOutOfBounds(int y, int x) {

		if ((x >= 8 || x < 0) || (y >= 8 || y < 0))
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

		System.out.println("our team: " + Arrays.deepToString(teamBoard));

		return teamBoard;
	}

	public static String[][] getEnemyTeam(String[][] board, String piece) {

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

		System.out.println("our enemies: " + Arrays.deepToString(teamBoard));

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
public class Piece{

	private Coordiante coordinate;
	private boolean isWhite;
	private File file;

	public void Piece(Coordinate coordinate, boolean isWhite, File file){ //constructor

	    this.coordinate = coordinate;
	    this.isWhite = isWhite;
	    this.file = file;
	}
	public static int[][] getLegalMoves(int[] coordinate, String piece){ //takes in string since it can just take in the name of the file found in the board array

		int[][] legalMoves;

		if(piece.equals("Rook1") || piece.equals("Rook2")){
			legalMoves = rookLegalMoves(coordinate);
		}
		else if (piece.equals("Knight1") || piece.equals("Knight2")) {
			legalMoves = knightLegalMoves(coordinate);
		}
		else if (piece.equals("Bishop1") || piece.equals("Bishop2")) {
			legalMoves = bishopLegalMoves(coordinate);
		}
		else if (piece.equals("Queen1") || piece.equals("Queen2")) {
			legalMoves = queenLegalMoves(coordinate);
		}
		else if (piece.equals("King1") || piece.equals("King2")) {
			legalMoves = kingLegalMoves(coordinate);
		}
		else{ //sets legal moves for pawns
			legalMoves = pawnLegalMoves(coordinate);
		}
		return legalMoves;
	}
	private static int[][] rookLegalMoves(int[] coordinate){

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
			else if((y - i) > -1 && teamInPos(y - i, x - i)){
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
			else if((y + i) < 8  && teamInPos(y - i, x)){
				counter++;
				legalMoves[i][0] = 	y + i;
				legalMoves[i][1] = 	x;
			} 
		}
	}
	private static int[][] knightLegalMoves(int[] coordinate){
		
	}
	private static int[][] bishopLegalMoves(int[] coordinate){
		
	}
	private static int[][] queenLegalMoves(int[] coordinate){
		
	}
	private static int[][] kingLegalMoves(int[] coordinate){
		
	}
	private static int[][] pawnLegalMoves(int[] coordinate){
		
	}
}
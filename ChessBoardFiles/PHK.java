public class PHK {

    public static Coordinate coord;
    public static String piece;
    public static String[][] board;

    public void PHK(Coordinate coord, String piece, String[][] board){
    
        this.coord = coord;
        this.piece = piece;
        this.board = board;
    }
    
    private static Coordinate[] pawnLegalMoves(){

		int y = coord.getY();
        int x = coord.getX();
        int count = 0;
        
        ArrayList<Coordinate> possibile = new ArrayList<Coordinate>();

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
}
import java.util.ArrayList;

public class Check {
	
	private String[][] board;
	public static PieceTest piece = new PieceTest();

	public boolean getCheck(String[][] boardState, boolean whiteTeam){ //returns wheter or not someone is in check.
		
		Coordinate king = new Coordinate(-1, -1); //new coordinate for king position
		
		boolean check = false;

		for(int i = 0; i < 8; i++){ //iterates through the board to look for king
			for(int j = 0; j < 8; j++){

				if(boardState[i][j].equals("king") && whiteTeam == true){
					king = new Coordinate(i,j); //sets the kings row and column to i and j if we are looking for the white team king
				}

				else if(boardState[i][j].equals("King") && whiteTeam == false){
					king = new Coordinate(i,j); //sets the kings row and column to i and j if we are looking for the black team king
				}
			}
		}

		ArrayList<Coordinate> enemyMoves = piece.getEnemyMoves(whiteTeam, boardState); //creating an arraylist of legal ENEMY moves
		//System.out.println("king located at @ " + king.getY() + "," + king.getX());

		for(Coordinate c: enemyMoves){ //iterate through array list to check if any of the moves can take the king 
			if((c.getY() == (king.getY())) && (c.getX() == (king.getX()))) //if king can be taken retrun true
				check = true;
		}

		return check; //return whether king is in check
	}
	
}
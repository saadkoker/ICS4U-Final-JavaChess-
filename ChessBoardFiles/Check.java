import java.util.ArrayList;
public class Check {
    
    private String[][] board;
    public static PieceTest piece = new PieceTest();

    public boolean getCheck(String[][] boardState, boolean whiteTeam){
        
        Coordinate king = new Coordinate(-1,-1);
        
        boolean check = false;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(boardState[i][j].equals("king") && whiteTeam == true){
                    king = new Coordinate(i,j);
                }

                else if(boardState[i][j].equals("King") && whiteTeam == false){
                    king = new Coordinate(i,j);
                }
            }
        }

        ArrayList<Coordinate> enemyMoves = piece.getEnemyMoves(whiteTeam, boardState);
        System.out.println("king located at @ " + king.getY() + "," + king.getX());

        for(Coordinate c: enemyMoves){
            if((c.getY() == (king.getY())) && (c.getX() == (king.getX())))
                check = true;
        }

        return check;
    }
    
}
public class Check {
    
    private String[][] board;
    public static PieceTest piece = new PieceTest();

    public Check(String[][] cur_board){

        board = cur_board;
    }

    public boolean getCheck(String[][] boardState, boolean whiteTeam){
        
        Coordinate king = new Coordinate(-1,-1);
        
        boolean check = false;

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){

                if(whiteTeam){
                    if(boardState[i][j].contains("king")){
                        king = new Coordinate(i,j);
                    }
                 }

                else if(whiteTeam == false){
                    if(boardState[i][j].contains("King")){
                        king = new Coordinate(i,j);
                    }
                }
            }
        }

       ArrayList<Coordinate> enemyMoves = piece.getEnemyMoves(whiteTeam, boardState);

       for(Coordinate c: enemyMoves){
            if(c.getY().equals(king.getY()) && c.getX().equals(king.getX()))
                check = true;
       }

       return check;
    }
    
}
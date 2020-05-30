import java.util.ArrayList;
public class ComputerBrain {

	private static BoardPieces bp;
	private static ChessBoard cb;

	public ComputerBrain(BoardPieces bp, ChessBoard cb){
		this.bp = bp;
		this.cb = cb;
	}

	private static PieceTest piece = new PieceTest();

	public static int miniMax(String[][] boardState, int depth, int alpha, int beta, boolean maxPlayer){
	
		//System.out.println("minimax called: ");
		boolean whiteTeam = false;

		if(maxPlayer)
			whiteTeam = true;

		
	//	if(depth == 0 || bp.gameOver(boardState, whiteTeam)){ //our base case
	if(depth == 0){
			//System.out.println("returning @ depth 0: " + getValue(boardState));
			return getValue(boardState); //houristic static evaluation at the end of our tree
		}

		if(maxPlayer){ //is white

			//System.out.println("call");
		   
			//int maxEval = -99999; //a very small number
			Integer maxEval = Integer.MIN_VALUE;

			//ArrayList<Move> children = piece.myMoves(whiteTeam, boardState); //remember white is maximising, while black is minimizing
			ArrayList<Move> children = bp.getLegalMoves(whiteTeam, boardState);

			for(Move child: children){

				String tempEnd = boardState[child.getEnd().getY()][child.getEnd().getX()];
				boardState[child.getEnd().getY()][child.getEnd().getX()] = boardState[child.getStart().getY()][child.getStart().getX()];
				boardState[child.getStart().getY()][child.getStart().getX()] = " ";

				int eval = miniMax(boardState, depth-1, alpha, beta, false);

				boardState[child.getStart().getY()][child.getStart().getX()] = boardState[child.getEnd().getY()][child.getEnd().getX()];
				boardState[child.getEnd().getY()][child.getEnd().getX()] = tempEnd;
	
				//eval = Math.max(alpha, eval)
			
				if(eval > maxEval){
					maxEval = eval;
					//System.out.println("maxEval" + bestMove.getStart().getY() + "," + bestMove.getStart().getX() + " to " + bestMove.getEnd().getY() + "," + bestMove.getEnd().getX());
				}

				if(alpha < maxEval){
					alpha = maxEval;
				}

				if(beta <= alpha){
					break;
				}
			}

			//System.out.println("returning the max score of " + maxEval);
			return maxEval;
		}

		else { //is black
			
			//System.out.println("black call");
			//int minEval = 99999; //a very large number
			Integer minEval = Integer.MAX_VALUE;

			ArrayList<Move> children = piece.myMoves(whiteTeam, boardState); //remember max player is white, min player is black

			for(Move child: children){
				
				String tempEnd = boardState[child.getEnd().getY()][child.getEnd().getX()];

				boardState[child.getEnd().getY()][child.getEnd().getX()] = boardState[child.getStart().getY()][child.getStart().getX()];
				boardState[child.getStart().getY()][child.getStart().getX()] = " ";

				int eval = miniMax(boardState, depth-1, alpha, beta, true);

				boardState[child.getStart().getY()][child.getStart().getX()] = boardState[child.getEnd().getY()][child.getEnd().getX()];
				boardState[child.getEnd().getY()][child.getEnd().getX()] = tempEnd;
				
				//beta = Math.min(eval, beta);

				if(eval < minEval){
					minEval = eval;
				}

				if(beta < eval){
					beta = eval;
				}
				
			//	eval = Math.min(eval, minEval);

				if(beta <= alpha){
					break;
				}
			}

			return minEval;
		}
	}

	public static int getValue(String[][] board){
	   
		int value = 0;

		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				if(board[i][j].equals("rook1") || board[i][j].equals("rook2"))
					value = value + 5;

				else if(board[i][j].equals("Rook1") || board[i][j].equals("Rook2"))
					value = value - 5;
				
				else if(board[i][j].equals("knight1") || board[i][j].equals("knight2"))
					value = value + 3;

				else if(board[i][j].equals("Knight1") || board[i][j].equals("Knight2"))
					value = value - 3;

				else if(board[i][j].equals("bishop1") || board[i][j].equals("bishop2"))
					value = value + 3;
				
				else if(board[i][j].equals("Bishop1") || board[i][j].equals("Bishop2"))
					value = value - 3;

				else if(board[i][j].equals("queen"))
					value = value + 9;

				else if(board[i][j].equals("Queen"))
					value = value - 9;

				else if(board[i][j].equals("king"))
					value = value + 9999; //a massive value because the king must be saved at all costs
				
				else if(board[i][j].equals("King"))
					value = value - 9999; //a massive value because the king must be saved at all costs
				
				else if(board[i][j].contains("pawn"))
					value++;
				
				else if(board[i][j].contains("Pawn"))
					value--;
			}
		}

		return value;
	}

	public static Move getBestMove(String[][] dummyBoard){

		//int bestScore = 9999; //a very large number because we are trying to find the minimum outcome
		Integer bestScore = Integer.MAX_VALUE;
		Move bestMove = new Move(new Coordinate(-1,-1), new Coordinate(-1,-1));

		ArrayList<Move> myMoves = piece.myMoves(false, dummyBoard); //getting all of the possible moves that black can make 

		int tempScore;

		for(Move possible : myMoves){

			//System.out.println(possible.getStart().getY() + "," + possible.getStart().getX() + " to " + possible.getEnd().getY() + "," + possible.getEnd().getX());
			//String[][] futureBoard = bp.deepCopyOf(dummyBoard);
			String tempEnd = dummyBoard[possible.getEnd().getY()][possible.getEnd().getX()]; //to prevent data loss
			dummyBoard[possible.getEnd().getY()][possible.getEnd().getX()] = dummyBoard[possible.getStart().getY()][possible.getStart().getX()];
			dummyBoard[possible.getStart().getY()][possible.getStart().getX()] = " ";

			tempScore = miniMax(dummyBoard, 4, Integer.MIN_VALUE, Integer.MAX_VALUE, true); //calculating the best outcome for this possible move
			//System.out.println("Score: "  + tempScore);

			dummyBoard[possible.getStart().getY()][possible.getStart().getX()] = dummyBoard[possible.getEnd().getY()][possible.getEnd().getX()];
			dummyBoard[possible.getEnd().getY()][possible.getEnd().getX()] = tempEnd;
			

			if (tempScore < bestScore){
				bestScore = tempScore;
				bestMove = possible;
			}

			//System.out.println(Arrays.deepToString(futureBoard) + "\n score: " + tempScore + " current best score: " + bestScore);
		}

		//System.out.println("Our best move: " + bestMove.getStart().getY() + "," + bestMove.getStart().getX() + " to " + bestMove.getEnd().getY() + "," + bestMove.getEnd().getX());
		return bestMove;
	}

	public void makeMove(String[][] myBoard){

		//String[][] dummyBoard = bp.deepCopyOf(cb.getBoardState());
		Move myMove = getBestMove(myBoard);

		if (myMove != (new Move(new Coordinate(-1,-1), new Coordinate(-1,-1)))){
			cb.clickSomething(myMove.getStart(), myMove.getEnd());
		}

		else{
			System.out.println("shite");
		}
	}
}
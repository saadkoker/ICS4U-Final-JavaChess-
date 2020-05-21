import javax.swing.JLabel;
import javax.swing.JFrame;
import java.util.ArrayList;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Game {

    private static Convert conv = new Convert();
    private static File errorAudio = new File("Error.wav");
    /*
    * This method is called for each turn and acts as the motherboard of the turn from which clicks are recieved, validity is checked and pieces are moved
    */
    public boolean myGame(int team, JLabel message, ChessBoard cb, ClickListener click, JFrame frame) throws InterruptedException{//team: 0 for white, 1 for black

		boolean legal = false;

		while(!legal){

        BoardPieces moves = new BoardPieces();
        boolean inCheck = moves.boardTester(team);

        String teamName = "black";
        Boolean whiteTeam = false;

        if(team == 0){
            teamName = "white";
            whiteTeam = true;
        }

        if(inCheck){
            
            ArrayList<Move> myMoves = moves.getLegalMoves(whiteTeam);
            //System.out.println("check moves size: " + myMoves.size());

            if(myMoves.size() < 1){
                message.setText("Game over! " + teamName + " looses");
                return false;
            }
            
            else{
                inCheck = false;
                message.setText(teamName + " is in Check!");
                vibrate(frame);
                errorSound();
            }
        }

        //checking for stale mate
        ArrayList<Move> legalMovements = moves.getLegalMoves(whiteTeam);
        boolean staleMate = true;
        for(Move m: legalMovements){
            if(moves.boardTester(m.getStart(), m.getEnd()) == false){
                System.out.println("found an option: " + m.getStart().getY() + "," + m.getStart().getX() + " and " + m.getEnd().getY() + "," + m.getEnd().getX());
                staleMate = false;
                break;
            }
        }

        if(staleMate){
            message.setText("Stale mate! It's a tie");
            return false;
        }

        Coordinate click1 = click.getClick();
		Coordinate click2 = click.getClick();
		click1 = conv.convCoor(click1, 42);
		click2 = conv.convCoor(click2, 42);

        if(!inCheck){

            boolean futureCheck = false;
            ArrayList<Coordinate> legalMoves = moves.movement(click1);

                for (Coordinate c: legalMoves){
                    
                    if (c.equals(click2) && moves.getCase(click1.getY(), click1.getX()) == team){ //making sure the move is legal and is on the right team
                        legal = true;
                        //System.out.println("moving piece at " + c.getY() + " , " + c.getX() + " to " + click2.getY() + " , " + click2.getX());
                    }
                }
                
                if(moves.getCase(click1.getY(), click1.getX()) != team){
                    message.setText("Invalid team");
                    vibrate(frame);
                    errorSound();
                }

                else if (moves.boardTester(click1, click2)){ //if the move would put the king in check
                    message.setText("That would put your king in check");
                    futureCheck = true;
                    legal = false;
                    vibrate(frame);
                    errorSound();
                }
                
                else if(legal == false && futureCheck == false){
                    message.setText("Illegal move");
                    vibrate(frame);
                    errorSound();
                }

                if(legal){
                    cb.clickSomething(click1, click2);
                    message.setText("Piece Moved");
                    break;
                }
            }
        }

        return true;
    }

    public static void vibrate(JFrame f) { 
        try { 
          int x = f.getLocationOnScreen().x; 
          int y = f.getLocationOnScreen().y; 
    
          for (int i = 0; i < 2; i++) { 
            Thread.sleep(5); 
            f.setLocation(x, y + 10); 
            Thread.sleep(5); 
            f.setLocation(x, y - 10);
            Thread.sleep(5); 
            f.setLocation(x + 10, y);
            Thread.sleep(5);
            f.setLocation(x - 10, y); 
            f.setLocation(x, y);
          } 
        } 
        catch (Exception e) { 
          System.out.println("something went wrong");
        } 
      }

    public static void errorSound(){
       
        try{

            Clip audio = AudioSystem.getClip();
            audio.open(AudioSystem.getAudioInputStream(errorAudio));
            audio.start();     
            Thread.sleep(audio.getMicrosecondLength()/1000); 
        
        }catch(Exception e){
            System.out.println("Oops");
        }

    }
}
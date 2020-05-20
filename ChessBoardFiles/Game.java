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
    public void myGame(int team, JLabel message, ChessBoard cb, ClickListener click, JFrame frame) throws InterruptedException{//team: 0 for white, 1 for black

		boolean legal = false;

		while(!legal){

		Coordinate click1 = click.getClick();
		Coordinate click2 = click.getClick();
		click1 = conv.convCoor(click1, 42);
		click2 = conv.convCoor(click2, 42);
		
        BoardPieces moves = new BoardPieces();
        Check kingSafety = new Check();

		ArrayList<Coordinate> legalMoves = moves.movement(click1);

			for (Coordinate c: legalMoves){
				
				if (c.equals(click2) && moves.getCase(click1.getY(), click1.getX()) == team){ //making sure the move is legal and is on the right team
					legal = true;
					System.out.println("moving piece at " + c.getY() + " , " + c.getX() + " to " + click2.getY() + " , " + click2.getX());
                }
            }
            
            if(moves.getCase(click1.getY(), click1.getX()) != team){
                message.setText("Invalid team");
                vibrate(frame);
                errorSound();
            }

            else if (moves.boardTester(click1, click2)){ //if the move would put the king in check
                message.setText("That would put your king in check");
                vibrate(frame);
                errorSound();
				legal = false;
            }
            
            else if(legal == false){
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
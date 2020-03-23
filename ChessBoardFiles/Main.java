import java.util.Scanner;
import javax.swing.*;

public class Main{

    private static ChessBoard cb = new ChessBoard();
    private static Scanner userInput = new Scanner (System.in);

    public static void main(String args[]){

        int length;
        int height;
        int os;
        boolean mac = true;

        System.out.println("If your operating system is NOT mac please enter 1 ");

        os = userInput.nextInt();

        if (os == 1)
            mac = false;

        JFrame frame = new JFrame("Chess");

        if (mac){
            length = 472;
            height = 450;
        }
        else{
            length = 489; //this is for windows so figure out what the optimal size is
            height = 465;
        }

        frame.getContentPane().add(new ChessBoard());
        frame.setSize(height, length);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }
}
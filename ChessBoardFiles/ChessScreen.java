import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.image.BufferedImage;

public class ChessScreen{

private static ChessBoard cb = new ChessBoard(); //our ChessBoard object
private static ClickListener click = new ClickListener(); //our ClickListener object
private static Convert conv = new Convert(); //our Convert object


    public void startScreen(int h, int l) throws InterruptedException{ //this method is called by another class and builds the chess board -> this will be the method that calls the Game class

        JFrame board = new JFrame("Chess");
        board.add(cb);
        board.addMouseListener(click);
        board.setSize(h,l);
    
        board.setVisible(true);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(false);

        try{
            BufferedImage img =  ImageIO.read(new File("queen1.png"));
            System.out.println("shit");
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            board.add(label);

        }catch(Exception E){
            System.out.println("shit");
        }

        boolean clickyTime = true; 
        int count = 0;
        System.out.println("You have 25 clicks, dont waste them!");

        while(clickyTime){ //just a test loop

            int[] clickCoords = click.getClick(); //getting a click -> note: we must get our clicks from this method
            clickCoords = conv.convertArr(clickCoords, 22);
            System.out.println(Arrays.toString(clickCoords));
            count++;

                if (count == 25)
                     clickyTime = false;
        }
        System.out.println("clicky time has ended");

    }

}
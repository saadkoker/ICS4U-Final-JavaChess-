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
        JLabel message = new JLabel("This fat bruh moment");
        JToolBar tools = new JToolBar();
        JButton newGame = new JButton("New");
        JButton saveGame = new JButton("Save");
        JButton undoButton = new JButton("Undo");
        JButton resignButton = new JButton("Resign");
        tools.setFloatable(false);
        board.add(cb);
        board.add(tools, BorderLayout.PAGE_START);
        tools.add(newGame);
        tools.add(saveGame); 
        tools.add(undoButton); 
        tools.addSeparator();
        tools.add(resignButton); 
        tools.addSeparator();
        tools.add(message);

        board.addMouseListener(click);
        board.setSize(h,l);

        board.setVisible(true);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(true);
        
        boolean clickyTime = true; 
        int count = 0;
        System.out.println("You have 25 clicks, dont waste them! hehe");

        while(clickyTime){ //just a test loop

            int[] clickCoords = click.getClick(); //getting a click -> note: we must get our clicks from this method
            clickCoords = conv.convertArr(clickCoords, 60);
            System.out.println(Arrays.toString(clickCoords));
            message.setText("Yikes, bad move.");
            count++;

                if (count == 25)
                     clickyTime = false;
        }
        System.out.println("clicky time has ended");

    }		

}
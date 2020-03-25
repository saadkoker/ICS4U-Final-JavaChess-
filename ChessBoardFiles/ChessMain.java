import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.event.MouseInputAdapter;

import java.awt.event.*;
import java.awt.Container;
import java.awt.Font;
import java.util.Scanner;
import java.util.logging.Handler;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;

public class ChessMain{

private static Font buttonFont = new Font("Vollkorn", Font.PLAIN, 20);
private static Convert convert = new Convert();
private static ChessBoard cb = new ChessBoard();
private static ClickListener click = new ClickListener();
private static Coordinate lastClick;

    //public static void main(String args [])throws InterruptedException{    
      //  introScreen();
    //}

    public static void startScreen(int l, int h) throws InterruptedException{

        JFrame board = new JFrame("Chess");
        board.getContentPane().add(cb);
        board.setSize(h,l);

        //cb.addMouseListener(click);
       // cb.addMouseListener(new MouseAdapter() 
           // {
              /** A method that is called when the mouse is clicked
               */
            //public void mouseClicked(MouseEvent e) 
           // { 
               // int x = e.getX();
               // int y = e.getY();

              //  x = convert.convertPos(x);
             //   y = convert.convertPos(y);

           //     System.out.println(y + "," + x);
         //   }

       // });

        cb.addMouseListener(click); 

        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(false);
        board.setVisible(true);

        //int[] clickCoords = click.getClick();
        //Coordinate coord = getClick();
        //System.out.println(coord.getRow() + " , " + coord.getCol());

    }

    public void introScreen() throws InterruptedException{

        try{

            JFrame frame = new JFrame();

            JButton startMacButton = new JButton("MAC");
            startMacButton.setBounds(15, 140, 85, 50);
            startMacButton.setFont(buttonFont);
            frame.add(startMacButton);
        
            JButton startOtherButton = new JButton("OTHER");
            startOtherButton.setBounds(115, 140, 85, 50);
            startOtherButton.setFont(buttonFont);
            frame.add(startOtherButton);
        
            JButton startHelpButton = new JButton("HELP");
            startHelpButton.setBounds(63, 220, 85, 50);
            startHelpButton.setFont(buttonFont);
            frame.add(startHelpButton);
        
            BufferedImage img =  ImageIO.read(new File("startScreen.png"));
            ImageIcon icon = new ImageIcon(img);
            JLabel label = new JLabel(icon);
            frame.add(label);

            startMacButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e){
                    frame.setVisible(false);
                    frame.dispose();

                    try{
                        startScreen(472,450);
                    } catch(Exception E){
                        System.out.println("something went wrong");
                    }
                }
            });

            startOtherButton.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent e){
                    frame.dispose();
                    frame.setVisible(false);

                    try{
                        startScreen(489,465);
                    } catch(Exception E){
                        System.out.println("something went wrong");
                    }
                }
            });
        
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        
        }catch (Exception E){
            System.out.println("Something went wrong");
        }
    }

  
  private static int convertPos(int coordinate){

    if(coordinate <= 50){
        coordinate = 0;
    }
    else if((coordinate > 50) && (coordinate <= 100)){
        coordinate = 1;
    }
    else if((coordinate > 100) && (coordinate <= 150)){
        coordinate = 2;
    }
    else if((coordinate > 150) && (coordinate <= 200)){
        coordinate = 3;
    }
    else if((coordinate > 200) && (coordinate <= 250)){
        coordinate = 4;
    }
    else if((coordinate > 250) && (coordinate <= 300)){
        coordinate = 5;
    }
    else if((coordinate > 300) && (coordinate <= 350)){
        coordinate = 6;
    }
    else if((coordinate > 350) && (coordinate <= 400)){
        coordinate = 7;
    }
    return coordinate;
}

}
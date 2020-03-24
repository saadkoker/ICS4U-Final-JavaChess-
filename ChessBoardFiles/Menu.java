import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.event.*;
import javafx.event.ActionEvent;
import java.awt.Container;
import java.awt.Font;
import java.util.Scanner;
import java.util.logging.Handler;
import javax.swing.*;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.*;
import javafx.scene.text.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.*;



public class Menu extends JPanel{

private static Font buttonFont = new Font("Vollkorn", Font.PLAIN, 20);
private static ClickListener click = new ClickListener();
private static ChessBoard cb = new ChessBoard();


    public void startScreen(int l, int h) throws InterruptedException{

        JFrame board = new JFrame("Chess");
        board.add(cb);
        board.getContentPane().addMouseListener(click);
        board.setSize(h,l);
        board.setVisible(true);
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setResizable(false);

        
        int[] num = click.getClick();
        System.out.println("Recieved Coords");
        System.out.println(Arrays.toString(num));

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
                    frame.dispose();
                    frame.setVisible(false);

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

}




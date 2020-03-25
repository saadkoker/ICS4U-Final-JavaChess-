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

public class Main{

    public static ChessScreen main = new ChessScreen();
    private static Font buttonFont = new Font("Vollkorn", Font.PLAIN, 20);
    public static void main(String args[]) throws InterruptedException{
        
        main.startScreen(450, 450); //calling the startScreen method with a preset resolution
    }

        public static void introScreen() throws InterruptedException{ //this will be a method used in the future -> it is yet to be effectively implemented 
    
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
                        main.startScreen(472, 450);
                        }catch(Exception E){
                        System.out.println("oh dear!");
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
                        main.startScreen(489, 465);
                        }catch(Exception E){
                            System.out.println("oh dear!");
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

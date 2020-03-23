import java.awt.Color;
import java.awt.Container;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Container;
import java.awt.Font;
import java.util.Scanner;
import java.util.logging.Handler;
import javax.swing.*;


public class Menu extends JPanel{

private static Font titleFont = new Font("Times New Roman", Font.PLAIN, 40);
private static Font buttonFont = new Font("Times New Roman", Font.PLAIN, 25);


    public void startScreen(){

        Scanner userInput = new Scanner (System.in);

        int length;
        int height;
        int os;
        boolean mac = true;

        System.out.println("If your operating system is NOT mac please enter 1 ");

        os = userInput.nextInt();

        if (os == 1)
            mac = false;


        if (mac){
            length = 472;
            height = 450;
        }
        else{
            length = 489; //this is for windows so figure out what the optimal size is
            height = 465;
        }

        JFrame frame = new JFrame("Chess");
        ChessBoard cb = new ChessBoard();

        frame.getContentPane().add(cb);
        cb.setVisible(false); 
        frame.setSize(length, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.black);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setResizable(false);

        Container container = frame.getContentPane();

        JPanel titleNamePanel = new JPanel();
        titleNamePanel.setBounds(100, 100, 250, 50);;
        titleNamePanel.setBackground(Color.black);

        JLabel titleNameLabel = new JLabel("JAVA CHESS");
        titleNameLabel.setForeground(Color.white);
        titleNameLabel.setFont(titleFont);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setBounds(150, 300, 150, 50);
        startButtonPanel.setBackground(Color.black);

        JButton startButton = new JButton("PLAY");
        startButton.setBackground(Color.black);
        startButton.setForeground(Color.black);
        startButton.setFont(buttonFont);
        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e){

                if (e.getSource() == startButton){
                    System.out.println("click");
                    titleNameLabel.setVisible(false);
                    titleNamePanel.setVisible(false);
                    startButtonPanel.setVisible(false);

                    frame.getContentPane().setBackground(Color.white);
                    cb.setVisible(true);
                }
            }
        });

        titleNamePanel.add(titleNameLabel);
        startButtonPanel.add(startButton);

        container.add(titleNamePanel);
        container.add(startButtonPanel);
    }

}

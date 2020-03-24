import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class StartScreen{

private static Font buttonFont = new Font("Vollkorn", Font.PLAIN, 20);

public static void main(String args[]){

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

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setVisible(true);

}catch (Exception E){
    System.out.println("Something went wrong");
}

    

}

}


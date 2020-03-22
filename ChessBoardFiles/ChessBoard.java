import javax.swing.*;
import java.awt.*;
import java.applet.*;
import javax.swing.border.*;

public class ChessBoard extends Applet{

    public static JFrame frame = new JFrame("Chess");

    public void paint(Graphics g){

        for (int i = 0; i < 450; i+=100) {
            for (int j = 0; j < 450; j+=100) {
                    g.setColor(new Color(79,36,18));
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 0; i < 450; i+=100) {
            for (int j = 50; j < 450; j+=100) {
                    g.setColor(new Color(193,161,120));
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 50; i < 450; i+=100) {
            for (int j = 50; j < 450; j+=100) {
                    g.setColor(new Color(79,36,18));
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 50; i < 450; i+=100) {
            for (int j = 0; j < 450; j+=100) {
                    g.setColor(new Color(193,161,120));
                    g.fillRect(i, j, 50, 50);
            }
        }
    }
    public void buildBoard(){

        frame.getContentPane().add(new ChessBoard());
        frame.setSize(450, 472);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        
    }

}
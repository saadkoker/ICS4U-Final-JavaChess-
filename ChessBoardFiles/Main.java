import java.util.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main{

    public static void main(String args[]) throws InterruptedException{

        int length;
        int height;
        int os;
        boolean mac = true;
        boolean hello = true;

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
        
        frame.add(cb);
        frame.setSize(height, length);
        frame.getContentPane().addMouseListener(click);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
<<<<<<< HEAD
        while (hello){
            int[] num = click.getClick();
            System.out.println("Recieved Coords");
            System.out.println(Arrays.toString(num));
            if (num[0] == 6) {
                hello = false;
            }
        }

        Menu menu = new Menu();
        menu.introScreen();
=======
        int[] num = click.getClick();
        Arrays.toString(num);
>>>>>>> parent of f5e25ac... Partially fixed multi-threading of the clicks
    }
}
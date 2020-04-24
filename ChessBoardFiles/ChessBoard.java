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


public class ChessBoard extends JPanel{ //extending JPanel

    private static BoardPieces bp = new BoardPieces();
    private static int x,y;

    public ChessBoard()
    {
    
    }

    public void paint(Graphics g){ //'painting' the board

        for (int i = 0; i < 400; i+=100) {
            for (int j = 0; j < 400; j+=100) {
                    g.setColor(new Color(193,161,120)); //colours can be changed or customized later in development
                    g.fillRect(i, j, 50, 50); //squares are 50x50 pixels -> consider this when building piece icons
            }
        }
        for (int i = 0; i < 400; i+=100) {
            for (int j = 50; j < 400; j+=100) {
                    g.setColor(new Color(79,36,18)); 
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 50; i < 400; i+=100) {
            for (int j = 50; j < 400; j+=100) {
                    g.setColor(new Color(193,161,120));
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 50; i < 400; i+=100) {
            for (int j = 0; j < 400; j+=100) {
                    g.setColor(new Color(79,36,18));
                    g.fillRect(i, j, 50, 50);
            }
        }

        bp.setupPieces(g);
    }

    public void clickSomething(int[] initialClick, int[] finalClick){

      //System.out.println("first click:" + Arrays.toString(initialClick));
      //System.out.println("final click:" + Arrays.toString(finalClick));
      bp.click(initialClick, finalClick);
      repaint();
    }
}
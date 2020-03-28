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


public class ChessBoard extends JPanel implements MouseListener, MouseMotionListener{ //extending JPanel

    //private BufferedImage[] pieces;
    private static BoardPieces bp = new BoardPieces();
    private static int x,y;
    public static BufferedImage pawn1,pawn2,rook1,rook2,bishop1,bishop2,knight1,knight2,queen1,queen2,king1,king2;

    public ChessBoard()
    {
    
    }

    public void paint(Graphics g){ //'painting' the board
        
        try{

            pawn1 = ImageIO.read(new File("pieces/pawn1.png"));
            pawn2 = ImageIO.read(new File("pieces/pawn2.png"));
            rook1 = ImageIO.read(new File("pieces/rook1.png"));
            rook2 = ImageIO.read(new File("pieces/rook2.png"));
            bishop1 = ImageIO.read(new File("pieces/bishop1.png"));
            bishop2 = ImageIO.read(new File("pieces/bishop2.png"));
            knight1 = ImageIO.read(new File("pieces/knight1.png"));
            knight2 = ImageIO.read(new File("pieces/knight2.png"));
            queen1 = ImageIO.read(new File("pieces/queen1.png"));
            queen2 = ImageIO.read(new File("pieces/queen2.png"));
            king1 = ImageIO.read(new File("pieces/king1.png"));
            king2 = ImageIO.read(new File("pieces/king2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }

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
        bp.setPiece(250, 150, pawn1, g);
    }
    @Override
    public void mouseClicked(MouseEvent e){
        x = (Math.round(e.getX()/10) * 10);
        y = (Math.round(e.getY()/10) * 10);
        System.out.println("Raw Coords: " + e.getX() + ", " + e.getY());
        System.out.println("Rounded Coords: " + x + ", " + y);
        repaint();
    }
    @Override
    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseMoved(MouseEvent e){}

    @Override
    public void mouseDragged(MouseEvent e){}
}
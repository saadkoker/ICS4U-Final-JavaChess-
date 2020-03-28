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

    private BufferedImage image;
    static int x = 150;
    static int y = 100;
    
    @Override
    public void paintComponent(Graphics g){ //'painting' the board
        
        super.paintComponent(g);
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        
        for (int i = 0; i < 400; i+=100) {
            for (int j = 0; j < 400; j+=100) {
                    g.setColor(new Color(79,36,18)); //colours can be changed or customized later in development
                    g.fillRect(i, j, 50, 50); //squares are 50x50 pixels -> consider this when building piece icons
            }
        }
        for (int i = 0; i < 400; i+=100) {
            for (int j = 50; j < 400; j+=100) {
                    g.setColor(new Color(193,161,120));
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 50; i < 400; i+=100) {
            for (int j = 50; j < 400; j+=100) {
                    g.setColor(new Color(79,36,18));
                    g.fillRect(i, j, 50, 50);
            }
        }
        for (int i = 50; i < 400; i+=100) {
            for (int j = 0; j < 400; j+=100) {
                    g.setColor(new Color(193,161,120));
                    g.fillRect(i, j, 50, 50);
            }
        }
        try{
            image = ImageIO.read(getClass().getResourceAsStream("pieces/rook2.png"));
            g.drawImage(image, x, y, 50, 50, null);
        }catch(IOException e){
            e.printStackTrace();
        }
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
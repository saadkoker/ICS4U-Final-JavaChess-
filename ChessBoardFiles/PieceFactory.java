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

public class PieceFactory extends JPanel{

	private BufferedImage image;

	public void paint(Graphics graph){

		try{
			Thread.sleep(10);
		}catch(Exception e){
			e.printStackTrace();
		}

		try{

			image = ImageIO.read(getClass().getResourceAsStream("pieces/rook2.png"));
			graph.drawImage(image, 200, 150, 50, 50, null);

		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
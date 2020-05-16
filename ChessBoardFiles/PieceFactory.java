import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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
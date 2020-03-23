import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Coordinate extends MouseAdapter{

	private static int x = 0;
	private static int x = 0; 

	@Override
	public void mouseClicked(MouseEvent e){
		System.out.println(e.getX() + ", " + e.getY());
		
	}
}
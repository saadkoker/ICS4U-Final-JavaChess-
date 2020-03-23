import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Coordinate extends MouseAdapter{

	private static int x = 0;
	private static int y = 0; 

	@Override
	public void mouseClicked(MouseEvent e){
		x = convertPos(e.getX());
		y = convertPos(e.getY());

	}
	private static int convertPos(int coordinate){

		if(coordinate <= 50){
			coordinate = 0;
		}
		else if((coordinate > 50) && (coordinate <= 100)){
			coordinate = 1;
		}
		else if((coordinate > 100) && (coordinate <= 150)){
			coordinate = 2;
		}
		else if((coordinate > 150) && (coordinate <= 200)){
			coordinate = 3;
		}
		else if((coordinate > 200) && (coordinate <= 250)){
			coordinate = 4;
		}
		else if((coordinate > 250) && (coordinate <= 300)){
			coordinate = 5;
		}
		else if((coordinate > 300) && (coordinate <= 350)){
			coordinate = 6;
		}
		else if((coordinate > 350) && (coordinate <= 400)){
			coordinate = 7;
		}
		System.out.println("X: " +  coordinate);
		return coordinate;
	}
	public static int getX(){
		return x;
	}
	public static int getY(){
		return y;
	}
}
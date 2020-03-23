import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter{

	private static int x = -1;
	private static int y = -1; 

		@Override
		public void mouseClicked(MouseEvent e){
			synchronized (ClickListener.this){
				x = convertPos(e.getX());
				y = convertPos(e.getY());
				notifyAll();
				System.out.println("Notified");
		}
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
		return coordinate;
	}
	public static int getX(){
		return x;
	}
	public static int getY(){
		return y;
	}
	public int[] getClick() throws InterruptedException{
        
		int[] UserClick = new int[2];

        synchronized(this){
        	
        	while((x == -1) && (y == -1)){
            	wait();
            	System.out.println("waiting");
        	}
            UserClick[0] = x;
            UserClick[1] = y;
            x = -1;
            x = -1;
            System.out.println("not waiting");
        }
        return UserClick;
    }
}
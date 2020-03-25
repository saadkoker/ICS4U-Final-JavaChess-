import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter{

	private static int x = -1;
	private static int y = -1; 
	
	@Override //this is called when the mouse is clicked
	public void mouseClicked(MouseEvent e){
		//System.out.println("click");

			synchronized(ClickListener.this) //synchronized to THIS clicklistner class
        	{
				x = convertPos(e.getX()); //converting the coords
				y = convertPos(e.getY());
				
				notify();//letting all the other threads know a click has happened, should they be waiting for one
				//System.out.println("notified");
             
          } /* synchronized */
		}/* mouseClicked */
		
	private static int convertPos(int coordinate){ //converting resolution values into implementable coordinates

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

	public int[] getClick() throws InterruptedException{ //this is called by other methods that wish to get a click
		
		//System.out.println("getClick");
		int[] userClick = new int[2];

        synchronized(ClickListener.this){ //also synced to this specific class
	
			//System.out.println("waiting");
			wait(); //waiting for a click to happen -> we are now concurrantly ruining with the click method
			
            userClick[0] = x;
			userClick[1] = y;
            //System.out.println("not waiting");
        }
        return userClick; //returning the click
	}
	
}
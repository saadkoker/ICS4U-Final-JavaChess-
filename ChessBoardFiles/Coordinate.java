public class Coordinate
{
	private int x;
	private int y;
	
	public Coordinate(int y_val, int x_val)
	{
		x = x_val;
		y = y_val;
	}
	
	public int getX()
	{
		return x;
	}
		
	public int getY()
	{
		return y; //returns y
	}

	public boolean equals(Coordinate coord){

		boolean equality = false;

		if (coord.getX() == x && coord.getY() == y){
			equality = true;
		}

		return equality;
	}
} 
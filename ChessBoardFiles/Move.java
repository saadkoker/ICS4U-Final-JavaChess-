public class Move {
    
	private Coordinate start;
	private Coordinate destination;
	
	public Move(Coordinate startLocation, Coordinate endLocation)
	{
		start = startLocation;
		destination = endLocation;
	}
	
	public Coordinate getStart()
	{
		return start;
	}
		
	public Coordinate getEnd()
	{
		return destination; 
	}

	public boolean equals(Move move){

		boolean equality = false;

		if (move.getStart() == start && move.getEnd() == destination){
			equality = true;
		}

		return equality;
	}
} 

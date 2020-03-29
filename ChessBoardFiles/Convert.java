public class Convert{

    public static int convertPos(int coordinate, int resolutionFactor){

		if(coordinate <= 50 + resolutionFactor){
			coordinate = 0;
		}
		else if((coordinate > 50 + resolutionFactor) && (coordinate <= 100 + resolutionFactor)){
			coordinate = 1;
		}
		else if((coordinate > 100 + resolutionFactor) && (coordinate <= 150 + resolutionFactor)){
			coordinate = 2;
		}
		else if((coordinate > 150 + resolutionFactor) && (coordinate <= 200 + resolutionFactor)){
			coordinate = 3;
		}
		else if((coordinate > 200 + resolutionFactor) && (coordinate <= 250 + resolutionFactor)){
			coordinate = 4;
		}
		else if((coordinate > 250 + resolutionFactor) && (coordinate <= 300 + resolutionFactor)){
			coordinate = 5;
		}
		else if((coordinate > 300 + resolutionFactor) && (coordinate <= 350 + resolutionFactor)){
			coordinate = 6;
		}
		else if((coordinate > 350 + resolutionFactor) && (coordinate <= 400 + resolutionFactor)){
			coordinate = 7;
		}
		
		else if (coordinate > 400 + resolutionFactor){
			coordinate = -1; //if the user clicks outside of the board
		}

		return coordinate;
	}

	public int convertCoord(int coor){ //takes in a coor value between 0-7 and transfers it to the center resolution of the coorisponding square

		if (coor == 0){
			coor = 0;
		}
		else if (coor == 1){
			coor = 50;
		}
		else if (coor == 2){
			coor = 100;
		}
		else if (coor == 3){
			coor = 150;
		}
		else if (coor == 4){
			coor = 200;
		}
		else if (coor == 5){
			coor = 250;
		}
		else if (coor == 6){
			coor = 300;
		}
		else if (coor == 7){
			coor = 350;
		}

		return coor;
	}
	
	public int [] convertArr(int arr[], int resolutionFactor){

		arr[0] = convertPos(arr[0], resolutionFactor);
		arr[1] = convertPos(arr[1], 0);

		return arr;
		
	}

}
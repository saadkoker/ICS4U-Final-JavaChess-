import java.util.*;
import java.io.*;

public class Save {

	private static int theColor = 0;

	public String[][] fromSave(String directory) { //method decloration...this method imports a board state from a file

		String[][] board = new String[8][8]; //Initializing a board

		try { // this try and catch is for because Files and Scanners have unhandeled exceptions
			File input = new File(directory); //declaring a new File object in the file the user selects
			
			Scanner scan = new Scanner(input); //inizalizing scanner for the file

			for (int i = 0; i < board.length; i++) { //iterating through through the array and importing from the file and putting it in the array
				for (int j = 0; j < board[i].length; j++) {
					String tempString = scan.nextLine();
					if (tempString.equals("space")) { //if its space fill in a blank in that space
						board[i][j] = " ";
					}
					else{ //if its not space just put the scan in
						board[i][j] = tempString;
					}
				}
			}
			theColor = scan.nextInt();
		} catch (FileNotFoundException e) { //literally just there for exception purposes
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}
	public static int getTeamMove(){

		return theColor;
	}
	public void export(String[][] board, String directory, int colorMove) { //method decleration for the export method, takes in a board and a directory to save the board in

		try { //try and catch for unhandeled exceptions

			directory = directory + "\\" + java.time.LocalDate.now() + "save.cb"; //add the file name to the directory and make the file name the current date + "save.cb" so we only override saves once a day
			File file = new File(directory); //create a new file in that directory
			PrintWriter print = new PrintWriter(file); //declaring a PrintWriter to write in the new file

			for (int i = 0; i < board.length; i++) { //iterating through the board and printing the elements inside the file
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == " ") { //if the space is empty then print the word space since blanks cant be imported
						print.println("space");
					}
					else{ //if its a piece print its name
						print.println(board[i][j]);
					}
				}
			}
			print.println(colorMove);
			print.close(); //close the PrintWriter so the file saves
		} catch (Exception e) { //exeption handaling
			System.out.println("Poopy poop file no exist");
		}
	}
}
import java.util.*;
import java.io.*;

public class Save {

	public String[][] fromSave(String directory) {

		String[][] board = new String[8][8];

		try {
			File input = new File(directory);
			
			Scanner scan = new Scanner(input);

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					
					String tempString = scan.nextLine();
					if (tempString.equals("space")) {
						board[i][j] = " ";
					}
					else{
						board[i][j] = tempString;
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}

	public void export(String[][] board, String directory) {

		try {

			directory = directory + "\\" + java.time.LocalDate.now() + "save.cb";
			File file = new File(directory);
			PrintWriter print = new PrintWriter(file);

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board.length; j++) {
					if (board[i][j] == " ") {
						print.println("space");
					}
					else{
						print.println(board[i][j]);
					}
				}
			}
			print.close();
		} catch (Exception e) {
			System.out.println("Poopy poop file no exist dumbass");
		}
	}
}
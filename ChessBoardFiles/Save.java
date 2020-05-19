import java.util.*;
import java.io.*;

public class Save {

	public String[][] fromSave(String fileName) {

		String[][] board = new String[8][8];

		try {
			File input = new File(fileName);
			Scanner scan;

			scan = new Scanner(input);

			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					String temp = scan.next();
					if (temp.equals("space")) {
						board[i][j] = " ";
					}
					else{
						board[i][j] = scan.next();
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
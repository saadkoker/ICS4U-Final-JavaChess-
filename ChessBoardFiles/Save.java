import java.util.*;
import java.io.*;

public class Save {

	public Save() {

	}

	/*
	 * public String[][] import(String fileName) throws IOException {
	 * 
	 * File input = new File(fileName); Scanner scan = new Scanner(input); int
	 * counter = 0;
	 * 
	 * return convertArray; }
	 */
	public void export(String[][] board, String directory) {

		try {
			
			PrintWriter print = new PrintWriter(new File("saveTest.cb"));

			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if(board[i][j] == " "){
						print.println("\n");
						System.out.println(board[i][j]);
					}
					else if(board[i][j] != " "){
						print.println(board[i][j]);
						System.out.println(board[i][j]);
					}
				}
			}
			print.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
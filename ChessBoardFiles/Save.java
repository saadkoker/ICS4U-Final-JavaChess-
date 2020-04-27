import java.util.*;
import java.io.*;

public class Save{

	private String[][] boardState;
	private int time;

	public Save(String[][] board, int saveTime){

		this.time = saveTime;
		this.boardState = Array.copyOf(board, board.length);

	}

	public static String[] stringFileScan(String fileName) throws IOException {

		File input = new File(fileName);
		Scanner scan = new Scanner(input);
		int counter = 0;

		return convertArray;
	}
}
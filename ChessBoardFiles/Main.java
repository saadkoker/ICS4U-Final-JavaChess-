import java.util.Scanner;

public class Main{

    private static ChessBoard cb = new ChessBoard();
    private static Scanner userInput = new Scanner (System.in);

    public static void main(String args[]){


        int os;
        boolean mac = true;

        System.out.println("If your operating system is NOT mac please enter 1 ");

        os = userInput.nextInt();

        if (os == 1)
            mac = false;




        cb.buildBoard(mac);
    }
}
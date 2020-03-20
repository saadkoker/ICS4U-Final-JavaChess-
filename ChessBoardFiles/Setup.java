import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
public class Setup{

    static BoardV2 board = new BoardV2();

    public static void main(String args[]){
        buildBoard();
    }

    public static void buildBoard(){   

        JFrame f = new JFrame("ChessChamp");
        f.add(board.getGui());
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
    }
}
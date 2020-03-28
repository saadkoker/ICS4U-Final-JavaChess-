import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.awt.Graphics;

public class BoardPieces{

    public static BufferedImage pawn1,pawn2,rook1,rook2,bishop1,bishop2,knight1,knight2,queen1,queen2,king1,king2;

    public void setupPieces(Graphics g){

        try{

            pawn1 = ImageIO.read(new File("pieces/pawn1.png"));
            pawn2 = ImageIO.read(new File("pieces/pawn2.png"));
            rook1 = ImageIO.read(new File("pieces/rook1.png"));
            rook2 = ImageIO.read(new File("pieces/rook2.png"));
            bishop1 = ImageIO.read(new File("pieces/bishop1.png"));
            bishop2 = ImageIO.read(new File("pieces/bishop2.png"));
            knight1 = ImageIO.read(new File("pieces/knight1.png"));
            knight2 = ImageIO.read(new File("pieces/knight2.png"));
            queen1 = ImageIO.read(new File("pieces/queen1.png"));
            queen2 = ImageIO.read(new File("pieces/queen2.png"));
            king1 = ImageIO.read(new File("pieces/king1.png"));
            king2 = ImageIO.read(new File("pieces/king2.png"));
           
            for(int i = 0; i < 8; i++){
                g.drawImage(pawn1, 50*i , 50, 50, 50, null); //black pawns
            }
            
            for(int i = 0; i < 8; i++){
                g.drawImage(pawn2, 50*i , 300, 50, 50, null); //white pawns
            }

            g.drawImage(rook1, 0, 0, 50, 50, null); //black
            g.drawImage(rook1, 350, 0, 50, 50, null);
            //------------------------------------------
            g.drawImage(rook2, 0, 350, 50, 50, null); //white
            g.drawImage(rook2, 350, 350, 50, 50, null);
            /******************************************/
            g.drawImage(bishop1, 50, 0, 50, 50, null); //black
            g.drawImage(bishop1, 300, 0, 50, 50, null);
            //------------------------------------------
            g.drawImage(bishop2, 50, 350, 50, 50, null); //white
            g.drawImage(bishop2, 300, 350, 50, 50, null);
            /******************************************/
            g.drawImage(knight1, 100, 0, 50, 50, null); //black
            g.drawImage(knight1, 250, 0, 50, 50, null);
            //------------------------------------------
            g.drawImage(knight2, 100, 350, 50, 50, null); //white
            g.drawImage(knight2, 250, 350, 50, 50, null);
            /******************************************/
            g.drawImage(queen1, 150, 0, 50, 50, null); //black
            //------------------------------------------
            g.drawImage(queen2, 150, 350, 50, 50, null); //white
            /******************************************/
            g.drawImage(king1, 200, 0, 50, 50, null);
            //------------------------------------------
            g.drawImage(king2, 200, 350, 50, 50, null);



        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void setPiece(int newX, int newY, BufferedImage piece, Graphics g){

        g.drawImage(piece, newX, newY, 50, 50, null);
    }
    
}
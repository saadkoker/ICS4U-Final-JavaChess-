public class MainV3{

    public static void main(String args[]) throws InterruptedException{
        
        ClickListener click = new ClickListener();
        ChessMain main = new ChessMain();
        main.introScreen();

        //Coordinate coord = click.getClick();
        //System.out.println(coord.getRow() + " , " + coord.getCol());
        //int [] coord = click.getClickBad();
        //System.out.println(coord[0] + " , " + coord[1]);

        try {
            //Thread.sleep(6000);
            
                System.out.println("please click");
                int [] coord = click.getClick();
                System.out.println(coord[0] + " , " + coord[1]);
            
            //Coordinate coord = click.getClick();
            //System.out.println(coord.getRow() + " , " + coord.getCol());
            
        } catch (Exception e) {
            
        }

    }
}
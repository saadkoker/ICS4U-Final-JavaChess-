import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Font;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;

public class Main{

	public static ChessScreen screen = new ChessScreen(); //initalzing the 
	private static Font buttonFont = new Font("Vollkorn", Font.PLAIN, 20);
	public static void main(String args[]) throws InterruptedException{
		
		screen.startScreen(450, 470); //calling the startScreen method with a preset resolution
	}

		public static void introScreen() throws InterruptedException{ //this will be a method used in the future -> it is yet to be effectively implemented 
	
			try{ 
				JFrame frame = new JFrame();
	
				JButton startMacButton = new JButton("MAC");
				startMacButton.setBounds(15, 140, 85, 50);
				startMacButton.setFont(buttonFont);
				frame.add(startMacButton);
			
				JButton startOtherButton = new JButton("OTHER");
				startOtherButton.setBounds(115, 140, 85, 50);
				startOtherButton.setFont(buttonFont);
				frame.add(startOtherButton);
			
				JButton startHelpButton = new JButton("HELP");
				startHelpButton.setBounds(63, 220, 85, 50);
				startHelpButton.setFont(buttonFont);
				frame.add(startHelpButton);
			
				BufferedImage img =  ImageIO.read(new File("startScreen.png"));
				ImageIcon icon = new ImageIcon(img);
				JLabel label = new JLabel(icon);
				frame.add(label);
	
				startMacButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(java.awt.event.ActionEvent e){
						frame.setVisible(false);
						frame.dispose();

						try{
						screen.startScreen(472, 450);
						}catch(Exception E){
						System.out.println("oh dear!");
						}
					}
				});
	
				startOtherButton.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(java.awt.event.ActionEvent e){
						frame.dispose();
						frame.setVisible(false);

						try{
						screen.startScreen(489, 465);
						}catch(Exception E){
							System.out.println("oh dear!");
						}
					}
				});
			
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);
			
			}catch (Exception E){
				System.out.println("Something went wrong");
			}
		}

	}

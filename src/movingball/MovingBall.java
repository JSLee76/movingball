package movingball;

import java.awt.*;
import javax.swing.*;

public class MovingBall extends JPanel{

	
	int width;
	int height;
	
	float radius = 40;
	float diameter = radius*2;
	
	float X = radius + 50;
	float Y = radius + 20;
	
	float dx = 3;
	float dy = 2;
	
	public MovingBall() {
		Thread thread = new Thread() {
			public void run() {
				while(true) {
					width = getWidth();
					height = getHeight();
					
					X = X + dx;
					Y = Y + dy;
					
					if (X-radius<0) {
						dx = -dx;
						X = radius;
					} else if (X+radius > width) {
						dx = -dx;
						X = width-radius;
					} 
					if (Y-radius<0) {
						dy = -dy;
						Y = radius;
					} else if (Y+radius>height) {
						dy = -dy;
						Y = height - radius;
					}
					repaint();
					
					try {
						Thread.sleep(10);
					} catch(InterruptedException ex) {
						
					}
				}
			}
		};
		thread.start();

	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillOval((int)(X-radius),(int)(Y-radius), (int)diameter, (int)diameter);
		g.setColor(Color.RED);
		g.fillOval((int)(X-radius+10),(int)(Y-radius+10), (int)diameter+10, (int)diameter+10);
	}
	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("Bouncing Ball");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 300);
		frame.setContentPane(new MovingBall());
		frame.setVisible(true);
		
	}

}

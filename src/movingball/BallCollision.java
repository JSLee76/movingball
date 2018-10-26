package movingball;

import java.awt.*;
import javax.swing.*;
import java.math.*;

public class BallCollision extends JFrame {
	Painting contentPane = new Painting();
	int width = 1500;
	int height = 1000;
	
	static final double friction = 0.01;
	
	double radius1 = 40;
	double diameter1 = radius1*2;
	
	double radius2 = 40;
	double diameter2 = radius2*2;
	
	double X1 = 450;
	double Y1 = 700;
	
	double X2 = 500;
	double Y2 = 200;
	
	double dx1 = 0;
	double dy1 = -10;
	
	double dx2 = 0;
	double dy2 = 0;
	
	double xrot = 0.01;
	
	
	BallCollision(){
		setTitle("KeyPr2");
		setContentPane(contentPane);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setSize(width, height);
		setDefaultLookAndFeelDecorated(true);
		width = contentPane.getWidth();
		height = contentPane.getHeight();
		Thread thread = new Thread() {
			public void run() {
				
				while(true) {
					
					
					width = contentPane.getWidth();
					height = contentPane.getHeight();
					
					if(width==0) { 
						System.out.println(width);
						continue;
					}
					
					
					
					X1 = X1 + dx1;
					Y1 = Y1 + dy1;
					X2 = X2 + dx2;
					Y2 = Y2 + dy2;
						
					
					if (X1-radius1<0) {
						System.out.println("X1-radius1<0");
						dx1 = -dx1;
						X1 = radius1;
					} else if (X1+radius1 > width) {
						System.out.println("X1+radius1 > width"+X1+" "+radius1+" "+width);
						dx1 = -dx1;
						X1 = width-radius1;
					} 
					if (Y1-radius1<0) {
						System.out.println("Y1-radius1<0");
						dy1 = -dy1;
						Y1 = radius1;
					} else if (Y1+radius1>height) {
						System.out.println("Y1+radius1>height");
						dy1 = -dy1;
						Y1 = height - radius1;
					}
					
					if (X2-radius2<0) {
						System.out.println("X2-radius2<0");
						dx2 = -dx2;
						X2 = radius2;
					} else if (X2+radius2 > width) {
						System.out.println("X2+radius2 > width");
						dx2 = -dx2;
						X2 = width-radius2;
					} 
					if (Y2-radius2<0) {
						System.out.println("Y2-radius2<0");
						dy2 = -dy2;
						Y2 = radius2;
					} else if (Y2+radius2>height) {
						System.out.println("Y2+radius2>height");
						dy2 = -dy2;
						Y2 = height - radius2;
					}
					
					/*if ((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1) <= (radius1+radius2)*(radius1+radius2)) {
						double pi = Math.atan((Y2-Y1)/(X2-X1));
						double initdx = dx1;
						dx1 = initdx*Math.sin(pi)*Math.sin(pi);
						dy1 = -initdx*Math.sin(pi)*Math.cos(pi);
						dx2 = initdx*Math.cos(pi)*Math.cos(pi);
						dy2 = initdx*Math.cos(pi)*Math.sin(pi);
					} */
					if ((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1) <= (radius1+radius2)*(radius1+radius2)) {
						double pi1 = Math.atan2(dy1,dx1);
						double pi2 = Math.atan2((Y2-Y1),(X2-X1));
						System.out.println("pi1: "+pi1+" pi2: "+pi2);
						double vscalar = Math.sqrt(dx1*dx1+dy1*dy1);
						dx1 = -vscalar*Math.sin(pi1-pi2)*Math.sin(pi2);
						dy1 = vscalar*Math.sin(pi1-pi2)*Math.cos(pi2);
						dx2 = vscalar*Math.cos(pi1-pi2)*Math.cos(pi2);
						dy2 = vscalar*Math.cos(pi1-pi2)*Math.sin(pi2);
						System.out.println("dx1: "+dy1+" dy1: "+dy1+" dx2: "+dx2+" dy2: "+dy2);
					} 
					double theta1 = Math.atan2(dy1, dx1);
					double theta2 = Math.atan2(dy2, dx2);
 
					if (Math.abs(dx1)<friction) dx1=0; else dx1 = dx1 - friction*Math.cos(theta1);
					if (Math.abs(dy1)<friction) dy1=0; else dy1 = dy1 - friction*Math.sin(theta1);
					if (Math.abs(dx2)<friction) dx2=0; else dx2 = dx2 - friction*Math.cos(theta2);
					if (Math.abs(dy2)<friction) dy2=0; else dy2 = dy2 - friction*Math.sin(theta2);
					
					double dxtemp = dx1;
					dx1 = dxtemp*Math.cos(xrot)-dy1*Math.sin(xrot);
					dy1 = dxtemp*Math.sin(xrot)+dy1*Math.cos(xrot);
					
					repaint();
					
					try {
						Thread.sleep(50);
					} catch(InterruptedException ex) {
						
					}
				}
			}
		};
		thread.start();
		setVisible(true);
		
	}
	
	/*public void colDetector(double X1,double Y1,double X2,double Y2) {
		if ((X2-X1)*(X2-X1)+(Y2-Y1)*(Y2-Y1) > (radius1+radius2)*(radius1+radius2)) {
			return;
		} else {
			double theta1 = Math.atan2(dy1,dx1);
			double theta2 = Math.atan2(dy2,dx2);
			double pi = theta1-theta2;
			double vscalar1 = Math.sqrt(dx1*dx1+dy1*dy1);
			double vscalar2 = Math.sqrt(dx2*dx2+dy2*dy2);
			dx1 = (vscalar1*Math.cos(theta1-pi)*0+2*1*vscalar2)*Math.cos(pi)/(2)-vscalar1*Math.sin(theta1-pi)*Math.sin(pi);
			dy1 = (vscalar1*Math.cos(theta1-pi)*0+2*1*vscalar2)*Math.sin(pi)/(2)+vscalar1*Math.sin(theta1-pi)*Math.cos(pi);
			dx2 = (vscalar2*Math.cos(theta2-pi)*0+2*1*vscalar1)*Math.cos(pi)/(2)-vscalar2*Math.sin(theta2-pi)*Math.sin(pi);
			dy2 = (vscalar2*Math.cos(theta2-pi)*0+2*1*vscalar1)*Math.sin(pi)/(2)+vscalar2*Math.sin(theta2-pi)*Math.cos(pi);
			
		}
		
	}*/
	
	
	class Painting extends JPanel {


		public void paintComponent(Graphics g) {
			paintComponents(g);
			g.setColor(Color.BLUE);
			g.fillOval((int)(X1-radius1),(int)(Y1-radius1), (int)diameter1, (int)diameter1);
			g.setColor(Color.RED);
			g.fillOval((int)(X2-radius2),(int)(Y2-radius2), (int)diameter2, (int)diameter2);
		}
	}
	
	
	
	public static void main(String[] args) {
		new BallCollision();

	}
}
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javafx.scene.shape.Shape;

import javax.swing.JPanel;

public class DrawPanel extends JPanel
{
	private double width;
    private double height;
    private double widthH;
    private double heightH;
    private double xFactor;
    private double yFactor;
    private double y[];


	public DrawPanel(double A[]) 
	{
		y = A;
	}

	public void paintComponent (Graphics g)
	{
		width = getWidth();
	    height = getHeight();
	    widthH = getWidth()/2;
	    heightH = getHeight()/2;
	    xFactor = width / (y.length-1);
	    yFactor = height / (y.length-1)*2;
		Graphics2D g2d = (Graphics2D) g;
	    super.paintComponent(g2d);
	
//	    System.out.println(xFactor);
//	    System.out.println(yFactor);
//	    System.out.println(width);
//	    System.out.println(height);
	    
	    //Draw X and Y Axis
	    g.drawLine(0, (int)(heightH), (int) width, (int) (heightH));
	    g.drawLine((int)widthH, 0, (int) widthH, (int) height);
	    
	    g.setColor(Color.lightGray);
	    //Draw Grid
	    g.drawLine(0, (int)(heightH)/2, (int) width, (int) (heightH)/2);
	    g.drawLine((int)widthH/2, 0, (int) widthH/2, (int) height);
	    g.drawLine(0, (int)(heightH)*3/2, (int) width, (int) (heightH)*3/2);
	    g.drawLine((int)widthH*3/2, 0, (int) widthH*3/2, (int) height);
	    
	    //THIS IS MISSION CRITICAL
	    //DON'T CHANGE!
	    //DON'T YOU DARE!
	    //RED IS LOVE. RED IS LIFE
	    g2d.setColor(Color.RED);
	   
	    //Evaluate Graph
	    for(int i = 0; i < y.length - 1; i++)
		{
	    	Line2D line = new Line2D.Double((xFactor * i), (heightH - y[i] * yFactor), (xFactor * (i+1)), (heightH - y[i+1] * yFactor));
	    	//g.drawLine((int) (xFactor * i), (int) (heightH - y[i] * yFactor), (int) (xFactor * (i+1)), (int) (heightH - y[i+1] * yFactor));
	    	g2d.draw(line);
		}
	    
	}
}
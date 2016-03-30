import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

//import javafx.scene.shape.Shape;


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
	private double yScale;

	/**
	 * Instantiate a new DrawPanel with given y-coordinates
	 * @param A
	 */
	public DrawPanel(double A[]) 
	{
		y = A;
	}
	/**
	 * Set the y-coordinates and the scale(in y direction)
	 * for the panel
	 * @param input - the y-coordinates
	 * @param scale - the y scale
	 */
	public void setY(double[] input, double scale)
	{
		y = input;
		yScale = scale;
		
	}
	/**
	 * Set the y scaling for a currently drawn graph
	 * @param scale - the y scaling
	 */
	public void setYScale(double scale)
	{
		yScale = scale;
	}
	
	/**
	 * Draw the Graph and axes
	 */
	public void paintComponent (Graphics g)
	{
		width = getWidth();
	    height = getHeight();
	    widthH = getWidth()/2;
	    heightH = getHeight()/2;
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		xFactor = width / (GraphModel.X.length);
	    yFactor = height / (GraphModel.X.length)* 10 * 5 * 5/yScale;
		

	    super.paintComponent(g2d);
	    
	    //Draw X and Y Axis
	    g2d.drawLine(0, (int)(heightH), (int) width, (int) (heightH));
	    g2d.drawLine((int)widthH, 0, (int) widthH, (int) height);
	    
	    g2d.setColor(Color.lightGray);
	    //Draw Grid
	    g2d.drawLine(0, (int)(heightH)/2, (int) width, (int) (heightH)/2);
	    g2d.drawLine((int)widthH/2, 0, (int) widthH/2, (int) height);
	    g2d.drawLine(0, (int)(heightH)*3/2, (int) width, (int) (heightH)*3/2);
	    g2d.drawLine((int)widthH*3/2, 0, (int) widthH*3/2, (int) height);
	    
	    g2d.setColor(Color.RED);

	    if(y == null)
	    	return;
	    
	    //Evaluate Graph
	    for(int i = 0; i < y.length - 1; i++)
		{
	    	if(!(Math.abs(y[i] - y[i + 1]) > 10000))
	    			g2d.drawLine((int) (xFactor * i), (int) (heightH - y[i] * yFactor), 
	    					(int) (xFactor * (i+1)), (int) (heightH - y[i+1] * yFactor));	
		}
	}
}

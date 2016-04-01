import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;

//import javafx.scene.shape.Shape;



import javax.swing.JLabel;
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
	private double numberOfGrids;
	private Graphics2D g2d;
	
	public DrawPanel()
	{
		repaint();
	}
	/**
	 * Set the y-coordinates and the scale(in y direction)
	 * for the panel
	 * @param input - the y-coordinates
	 * @param scale - the y scale
	 */
	public void setY(double[] input, double Scale)
	{
		if(input == null)
			return;
		y = input;
		yScale = (y.length - 1)/200/Scale;
	}
	/**
	 * Set the y scaling for a currently drawn graph
	 * @param scale - the y scaling
	 */
	public void setYScale(double Scale)
	{
		yScale = (y.length - 1)/200/Scale;
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
	    yFactor = height / (GraphModel.X.length)* 50 * yScale;
		

	    super.paintComponent(g2d);
	        
	    g2d.setColor(Color.lightGray);
	    //Draw Grid
	    numberOfGrids = 10;
	    for(int i = 1; i <= numberOfGrids*2; i++)
	    {
	    	//Draw Horizontal grid
	    	g2d.drawLine(0, (int)(heightH/numberOfGrids*i), (int) width, (int)(heightH/numberOfGrids*i));
	    	JLabel yLabel = new JLabel();
	    	yLabel.setBounds((int) width / 2 + 2, (int)(heightH/numberOfGrids*i), 50, 15);
	    	yLabel.setText("" + (10 - i));
	    	add(yLabel);
	    	//Draw vertical grid
	    	g2d.drawLine((int)(widthH/numberOfGrids*i), 0, (int)(widthH/numberOfGrids*i), (int) height);
	    	JLabel xLabel = new JLabel();
	    	xLabel.setBounds((int)(widthH/numberOfGrids*i), (int) height / 2 + 2, 50, 15);
	    	if((i - 10) != 0)
	    	{	xLabel.setText("" + (i - 10));
	    		add(xLabel);
	    	}
	    }
	       
	  //Draw X and Y Axis
	    g2d.setColor(Color.BLACK);
	    g2d.drawLine(0, (int)(heightH), (int) width, (int) (heightH));
	    g2d.drawLine((int)widthH, 0, (int) widthH, (int) height);
	    
	   g2d.setColor(Color.RED);

	    if(y == null)
	    	return;
	    
	    //Evaluate Graph   
	    for(int i = 0; i < (y.length - 1); i++)
		{
	    	if(!(Math.abs(y[i] - y[i + 1]) > 10000))
	    			g2d.drawLine((int) (xFactor * i), 	  (int) (heightH - y[i] * yFactor), 
	    						 (int) (xFactor * (i+1)), (int) (heightH - y[i+1] * yFactor));
		}
	}
	
	
}

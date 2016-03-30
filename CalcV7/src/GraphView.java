import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;


public class GraphView extends JFrame 
{
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int GRAPH_HEIGHT = 550;
	private static final int GRAPH_WIDTH = 550;
	private static final int DISPLAY_WIDTH = 50;
	private static final int DISPLAY_HEIGHT = 50;
	private double y[];
	private GridLayout buttons_manager = new GridLayout(0, 3);
	
	private JPanel top = new JPanel();
	private DrawPanel  bottom;
	private JPanel buttons = new JPanel();
	
	private JLabel expression = new JLabel();
	private static double yScale = 5;
	private static JLabel N_Y;
	private static JLabel P_Y;
	private static JLabel N_X;
	private static JLabel P_X;
	private static JLabel yScaleLabel;

	
	/**
	 * Instantiates a new GraphView with the associated controller
	 * @param controller - the GraphView's controller
	 */
	public GraphView(final GraphController controller)
	{
		super("Graph");
		setResizable(false);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		top.setBackground(Color.DARK_GRAY);
		
		top.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		buttons.setLocation(300, 0);
		buttons.setBackground(Color.DARK_GRAY);
		
		buttons.setSize(300, 73);
		buttons_manager.setVgap(5);
		buttons_manager.setHgap(5);
		buttons.setLayout(buttons_manager);
		bottom = new DrawPanel(y);
		
	    ButtonAdapter calc;
		buttons.add(calc = new ButtonAdapter("Calc")
		{
			
			public void pressed()
			{
				controller.changeToCalc();
			}
		});
		calc.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		calc.setFont(new Font("Dialog", Font.BOLD, 16));
		calc.setForeground(Color.WHITE);
		calc.setBackground(Color.BLACK);
		

		ButtonAdapter fav;
		buttons.add(fav = new ButtonAdapter("Fav")
		{
			
			public void pressed()
			{
				controller.changToFav();
			}
		});
		fav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fav.setFont(new Font("Dialog", Font.BOLD, 16));
		fav.setForeground(Color.WHITE);
		fav.setBackground(Color.BLACK);
		
		
		ButtonAdapter addFav;
		buttons.add(addFav = new ButtonAdapter("<html>Add to Favorites<html>")
		{
			
			public void pressed()
			{
				controller.addToFav();
			}
		});
		addFav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addFav.setForeground(Color.WHITE);
		addFav.setBackground(Color.BLACK);
		fav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fav.setFont(new Font("Dialog", Font.BOLD, 16));
		fav.setForeground(Color.WHITE);
		fav.setBackground(Color.BLACK);
		
		
		buttons.setVisible(true);
		top.setLayout(null);
		expression.setBounds(0, 0, 262, 73);
		expression.setForeground(Color.WHITE);
		expression.setFont(new Font("Dialog", Font.BOLD, 24));
		expression.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		top.add(expression);
		top.add(buttons);
		top.setVisible(true);
	
		bottom.setSize(GRAPH_WIDTH, GRAPH_HEIGHT);
		bottom.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(top, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(bottom, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(top, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(bottom, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		ButtonAdapter IncreaseScale;
		buttons.add(IncreaseScale = new ButtonAdapter("↑")
		{
			
			public void pressed()
			{
				if (yScale <= 1)
					yScale *= 2;
				else
				yScale += 1; 
				rescale();
			}
		});
		IncreaseScale.setForeground(Color.WHITE);
		IncreaseScale.setBackground(Color.BLACK);
		IncreaseScale.setBounds(256, 0, 44, 25);
		top.add(IncreaseScale);
		
		ButtonAdapter DecreaseScale;
		buttons.add(DecreaseScale = new ButtonAdapter("↓")
		{
			
			public void pressed()
			{
				if (yScale <= 1)
					yScale /= 2;
				else
					yScale -= 1;
				rescale();
			}
		});
		DecreaseScale.setForeground(Color.WHITE);
		DecreaseScale.setBackground(Color.BLACK);
		DecreaseScale.setBounds(256, 48, 44, 25);
		top.add(DecreaseScale);
		
		yScaleLabel = new JLabel("" + yScale);
		yScaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yScaleLabel.setHorizontalTextPosition(SwingConstants.LEFT);
		yScaleLabel.setForeground(Color.WHITE);
		yScaleLabel.setBounds(256, 24, 44, 25);
		top.add(yScaleLabel);
		
		bottom.setLayout(null);
		
		N_X = new JLabel();
		P_X = new JLabel();
		N_Y = new JLabel();
		P_Y = new JLabel();
		N_X.setBounds(152, 247, 50, 15);
		P_X.setBounds(453, 247, 13, 15);
		P_Y.setBounds(302, 124, 50, 15);
		N_Y.setBounds(302, 367, 50, 15);
		
		bottom.add(N_X);
		bottom.add(P_X);
		bottom.add(P_Y);
		bottom.add(N_Y);
		
		getContentPane().setLayout(groupLayout);
	}
	/**
	 * Update the label of the shown graph
	 * @param expr - the expression of the graph
	 */
	public void updateExpr(String expr)
	{
		expression.setText(expr);
	}
	/**
	 * Draws a new graph with the given y coordinates
	 * @param y - the y coordinates
	 * @param SCGraph - set the scaling if trig graph
	 */
	public void drawGraph(double[] y, boolean SCGraph)
	{
		yScale = 5;	
		if (SCGraph == true)
			{
				N_X.setText("-\u03C0");
				P_X.setText("\u03C0");
			}
			else
			{
				N_X.setText("-5");
				P_X.setText("5");
			}
			N_Y.setText("-" + (int) yScale);
			P_Y.setText("" + (int) yScale);
			yScaleLabel.setText("" + (int) yScale);
			bottom.setY(y, yScale);
			bottom.repaint();
	}
	/**
	 * Re-scales the currently drawn graph
	 * in the y-direction
	 */
	public void rescale()
	{
			N_Y.setText("-" + (int) yScale);
			P_Y.setText("" + (int) yScale);
			yScaleLabel.setText("" +  (int) yScale);			
			bottom.repaint();
			bottom.setYScale(yScale);
	}		
}

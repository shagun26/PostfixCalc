import java.awt.GridLayout;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;

import javax.swing.JButton;


public class GraphView extends JFrame 
{
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int GRAPH_HEIGHT = 550;
	private static final int GRAPH_WIDTH = 550;
	private static final int DISPLAY_WIDTH = 50;
	private static final int DISPLAY_HEIGHT = 50;
	//private double y[];
	private GridLayout buttons_manager = new GridLayout(0, 3);
	
	private JPanel top = new JPanel();
	private DrawPanel  graph;
	private JPanel buttons = new JPanel();
	
	private JLabel expression = new JLabel();
	private static double yScale = 5;
	private static double xScale;
	//private JTextField xScaleLabel;

	
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
		
		buttons.setSize(300, 37);
		buttons_manager.setVgap(5);
		buttons_manager.setHgap(5);
		buttons.setLayout(buttons_manager);
		graph = new DrawPanel();
		
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
		expression.setBounds(0, 0, 300, 73);
		expression.setForeground(Color.WHITE);
		expression.setFont(new Font("Dialog", Font.BOLD, 24));
		expression.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		top.add(expression);
		top.add(buttons);
		top.setVisible(true);
	
		graph.setSize(GRAPH_WIDTH, GRAPH_HEIGHT);
		graph.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(top, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(graph, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(top, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(graph, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		
		
		graph.setLayout(null);
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
	public void drawGraph(double[] y, double xScale2)
	{
		xScale = xScale2;
		graph.setY(y, yScale);
		graph.repaint();
	}
	
	
}

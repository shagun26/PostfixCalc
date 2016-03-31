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
	private double y[];
	private GridLayout buttons_manager = new GridLayout(0, 3);
	
	private JPanel top = new JPanel();
	private DrawPanel  bottom;
	private JPanel buttons = new JPanel();
	
	private JLabel expression = new JLabel();
	private static double yScale = 5;
	private static double xScale;
	private static JLabel N_Y;
	private static JLabel P_Y;
	private static JLabel N_X;
	private static JLabel P_X;
	private static JTextField yScaleLabel;
	private JTextField xScaleLabel;

	
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
		bottom = new DrawPanel();
		
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
		
		ButtonAdapter IncreaseScaleY;
		buttons.add(IncreaseScaleY = new ButtonAdapter("↑")
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
		IncreaseScaleY.setForeground(Color.WHITE);
		IncreaseScaleY.setBackground(Color.BLACK);
		IncreaseScaleY.setBounds(400, 40, 44, 25);
		top.add(IncreaseScaleY);
		
		ButtonAdapter DecreaseScaleY;
		buttons.add(DecreaseScaleY = new ButtonAdapter("↓")
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
		DecreaseScaleY.setForeground(Color.WHITE);
		DecreaseScaleY.setBackground(Color.BLACK);
		DecreaseScaleY.setBounds(300, 40, 44, 25);
		top.add(DecreaseScaleY);
		
		AbstractAction EnterKeyY = new AbstractAction()
		{
		  	@Override
			public void actionPerformed(ActionEvent arg0) {
		  		yScale = Double.parseDouble(yScaleLabel.getText());
		  		rescale();
			}
		};
		yScaleLabel = new JTextField("" + yScale);
		yScaleLabel.setBackground(Color.BLACK);
		yScaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yScaleLabel.setForeground(Color.WHITE);
		yScaleLabel.setBounds(350, 40, 44, 25);
		yScaleLabel.addActionListener(EnterKeyY);
		top.add(yScaleLabel);
		
		ButtonAdapter IncreaseScaleX = new ButtonAdapter("↑") {
			public void pressed() 
			{
				if (xScale <= 1 || xScale == Math.PI/2)
					xScale *= 2;
				else if (xScale % Math.PI==0)
					xScale += Math.PI;
				else
				xScale += 1; 
				//controller.newGraphSet(xScale);
			}
		};
		IncreaseScaleX.setForeground(Color.WHITE);
		IncreaseScaleX.setBackground(Color.BLACK);
		IncreaseScaleX.setBounds(550, 40, 44, 25);
		top.add(IncreaseScaleX);
		
		ButtonAdapter DecreaseScaleX = new ButtonAdapter("↓") {
			public void pressed() 
			{
				if (xScale <= 1 || xScale == Math.PI || xScale == Math.PI/2)
					xScale /= 2;
				else if(xScale % Math.PI==0)
					xScale -= Math.PI;
				else
				xScale -= 1; 
				//controller.newGraphSet(xScale);
			}
		};
		DecreaseScaleX.setForeground(Color.WHITE);
		DecreaseScaleX.setBackground(Color.BLACK);
		DecreaseScaleX.setBounds(450, 40, 44, 25);
		top.add(DecreaseScaleX);
		
		AbstractAction EnterKeyX = new AbstractAction()
		{
		  	@Override
			public void actionPerformed(ActionEvent arg0) {
		  		xScale = Double.parseDouble(xScaleLabel.getText());
		  		//controller.newGraphSet(xScale);
			}
		};
		xScaleLabel = new JTextField("" + xScale);
		xScaleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xScaleLabel.setForeground(Color.WHITE);
		xScaleLabel.setBackground(Color.BLACK);
		xScaleLabel.setBounds(500, 40, 44, 25);
		xScaleLabel.addActionListener(EnterKeyX);
		top.add(xScaleLabel);
		
		bottom.setLayout(null);
		
		N_X = new JLabel();
		P_X = new JLabel();
		N_Y = new JLabel();
		P_Y = new JLabel();
		
		N_X.setBounds(152, 247, 50, 15);
		P_X.setBounds(453, 247, 50, 15);
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
	public void drawGraph(double[] y, double xScale2)
	{
		xScale = xScale2;
		System.out.println(xScale%Math.PI);
		if(xScale%Math.PI==0)
		{
			N_X.setText("-" +(int)(xScale/Math.PI)+"\u03C0" );
			P_X.setText((int)(xScale/Math.PI)+"\u03C0");
			xScaleLabel.setText((int)(xScale/Math.PI)+"\u03C0");
		}
		else
		{
			N_X.setText("" + (-1*xScale));
			P_X.setText("" + xScale);
			xScaleLabel.setText("" + xScale);
		}
		
		N_Y.setText("" + (-1*yScale));
		P_Y.setText("" + yScale);
		yScaleLabel.setText("" + yScale);
		bottom.setY(y, yScale);
		bottom.repaint();
	}
	
	public void rescale()
	{
		if((xScale%Math.PI)==0)
		{
			N_X.setText("-" +(int)(xScale/Math.PI)+"\u03C0" );
			P_X.setText((int)(xScale/Math.PI)+"\u03C0");
			xScaleLabel.setText((int)(xScale/Math.PI)+"\u03C0");
		}
		else
		{
			N_X.setText("" + (-1*xScale));
			P_X.setText("" + xScale);
			xScaleLabel.setText("" + xScale);
		}
		
		N_Y.setText("" + (-1*yScale));
		P_Y.setText("" + yScale);
		yScaleLabel.setText("" + yScale);
		bottom.setYScale(yScale);
		bottom.repaint();
	}	
	
	public String getExpr()
	{
		return expression.getText();
	}
}

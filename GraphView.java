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
	private GridLayout top_manager = new GridLayout(1, 2);
	
	private JPanel top = new JPanel();
	private static DrawPanel  bottom = null;
	private JPanel buttons = new JPanel();
	
	private JLabel expression = new JLabel("y = x");
	

	
	
	public GraphView(final GraphController controller)
	{
		super("Graph");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		top.setBackground(Color.DARK_GRAY);
		
		top.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		top.setLayout(top_manager);
		buttons.setBackground(Color.DARK_GRAY);
		
		buttons.setSize(20, 20);
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
				controller.changToFav(controller);
			}
		});
		fav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fav.setFont(new Font("Dialog", Font.BOLD, 16));
		fav.setForeground(Color.WHITE);
		fav.setBackground(Color.BLACK);
		
		
		ButtonAdapter addFav;
		buttons.add(addFav = new ButtonAdapter("Add to Favorites")
		{
			
			public void pressed()
			{
				//controller.addtoFav();
			}
		});
		fav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fav.setFont(new Font("Dialog", Font.BOLD, 16));
		fav.setForeground(Color.WHITE);
		fav.setBackground(Color.BLACK);
		
		
		buttons.setVisible(true);
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
		getContentPane().setLayout(groupLayout);
		
		
	}
	
	public void updateExpr(String expr)
	{
		expression.setText(expr);
	}
	
	public void drawGraph(double[] y)
	{
		if(bottom == null)
			bottom = new DrawPanel(y);
		else
		{
			bottom.setY(y);
			bottom.repaint();
		}
			
	}
}

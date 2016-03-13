import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GraphView extends JFrame 
{
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int GRAPH_HEIGHT = 550;
	private static final int GRAPH_WIDTH = 550;
	private static final int DISPLAY_WIDTH = 50;
	private static final int DISPLAY_HEIGHT = 50;
	
	private GridLayout window_layout_manager = new GridLayout(2, 1);
	private GridLayout buttons_manager = new GridLayout(2, 1);
	private GridLayout top_manager = new GridLayout(1, 2);
	
	private JPanel top = new JPanel();
	private JPanel bottom = new JPanel();
	private JPanel buttons = new JPanel();
	
	private JLabel expression = new JLabel("y = x");
	
	
	public GraphView(GraphController controller)
	{
		super("Graph");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(window_layout_manager);
		
		
		
		top.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		top.setLayout(top_manager);
		
		
		
		buttons.setSize(20, 20);
		buttons.setLayout(buttons_manager);
		buttons.add(new ButtonAdapter("Calc")
		{
			
			public void pressed()
			{
				
				controller.changeToCalc();
			}
		});
		
		buttons.add(new ButtonAdapter("Fav")
		{
			
			public void pressed()
			{
				controller.changToFav();
			}
		});
		
		buttons.setVisible(true);
		
		
		top.add(expression);
		top.add(buttons);
		top.setVisible(true);

		bottom.setSize(GRAPH_WIDTH, GRAPH_HEIGHT);
		bottom.setVisible(true);
		
		add(top);
		add(bottom);
		
		
	}
	
	public void updateExpr(String expr)
	{
		expression.setText(expr);
	}
	
	//public static void main(String[] args)
	//{
		//GraphView v = new GraphView();
		//v.setVisible(true);
	//}
	
}

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


	public class FavView extends JFrame
{
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int FUNC_HEIGHT = 550;
//	private static final int FUNC_WIDTH = 550;
//	private static final int TOP_WIDTH = 50;
	private static final int TOP_HEIGHT = 50;

	//Back button, Title
//	private GridLayout top_manager = new GridLayout(1, 3);
	//Actual fucntions for all function put delete button and graph button
//	private GridLayout bottom_manager = new GridLayout(10, 3);
	

//	private JPanel top = new JPanel();
//	private JPanel bottom = new JPanel();
//	//private JPanel functionHolder = new JPanel();
//	private JLabel title = new JLabel("Favorties");
//	private JPanel buttons = new JPanel();
//	private JPanel remove_one = new JPanel();
//	private JPanel remove_two = new JPanel();
//	
//	public  FavView(final FavController favController)
//	{
//		super("Favorites");
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//		title.setSize(100,100);
//		title.setForeground(Color.WHITE);
//		top.setBackground(Color.DARK_GRAY);
//		top.setLayout(top_manager);
//		//top.setSize(FUNC_WIDTH, FUNC_HEIGHT);
//		
//		top.setSize(WINDOW_WIDTH, TOP_HEIGHT);
//
//		
//		ButtonAdapter fav;
//		buttons.add(fav = new ButtonAdapter("Fav")
//		{
//			
//			public void pressed()
//			{
//				favController.changeToGraph();
//			}
//		});
//		fav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		fav.setSize(75, TOP_HEIGHT);
//		fav.setFont(new Font("Dialog", Font.BOLD, 16));
//		fav.setForeground(Color.WHITE);
//		fav.setBackground(Color.BLACK);
//		
//		
//		bottom.setLayout(bottom_manager);
//		buttons.setSize(20,20);
//		bottom.setBackground(Color.WHITE);
//		
//
//		ButtonAdapter remove1;
//		remove_one.add(remove1 = new ButtonAdapter("Remove")
//		{
//			
//			public void pressed()
//			{
////				favController.deleteExpr(remove_one);
//			}
//		});
//		fav.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//		fav.setFont(new Font("Dialog", Font.BOLD, 16));
//		fav.setForeground(Color.WHITE);
//		fav.setBackground(Color.BLACK);
//		
//		
//		bottom.add(remove_one);
//		top.add(buttons);
//		top.add(title);
//		add(top);
//		add(bottom);
//		setVisible(true);
//		
//		
//		
//	}
	private GridLayout buttons_manager = new GridLayout(0, 3);
	private GridLayout top_manager = new GridLayout(1, 2);
	
	private JPanel top = new JPanel();
	private JPanel buttons = new JPanel();
	
	private JLabel Title = new JLabel("Favorites");
	

	
	
	public FavView(final FavController favController)
	{
		super("Favorite");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		top.setBackground(Color.DARK_GRAY);
		
		top.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		top.setLayout(top_manager);
		buttons.setBackground(Color.DARK_GRAY);
		
		buttons.setSize(20, 20);
		buttons_manager.setVgap(5);
		buttons_manager.setHgap(5);
		buttons.setLayout(buttons_manager);

		
	    ButtonAdapter toGraph;
		top.add(toGraph = new ButtonAdapter("Back To Graph")
		{
			
			public void pressed()
			{
				favController.changeToGraph();
			}
		});
		toGraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		toGraph.setFont(new Font("Dialog", Font.BOLD, 16));
		toGraph.setForeground(Color.WHITE);
		toGraph.setBackground(Color.BLACK);
		
		ButtonAdapter remove1;
		buttons.add(remove1 = new ButtonAdapter("Remove")
		{
			
			public void pressed()
			{
//				favController.deleteExpr(remove_one);
			}
		});
		remove1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		remove1.setFont(new Font("Dialog", Font.BOLD, 16));
		remove1.setForeground(Color.WHITE);
		remove1.setBackground(Color.BLACK);
		
		
		
		buttons.setVisible(true);
		Title.setForeground(Color.WHITE);
		Title.setFont(new Font("Dialog", Font.BOLD, 24));
		Title.setHorizontalAlignment(SwingConstants.CENTER);
		
		
		top.add(Title);
		top.add(buttons);
		top.setVisible(true);
	
		buttons.setSize(WINDOW_WIDTH, FUNC_HEIGHT);
		buttons.setVisible(true);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(top, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addComponent(buttons, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(top, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(buttons, GroupLayout.PREFERRED_SIZE, 489, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		getContentPane().setLayout(groupLayout);
		
		
	}
	public void updateFav(String expr)
	{
		//bottom.setText(expr);
	}
}

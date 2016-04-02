	import java.awt.GridLayout;

	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

	import java.awt.Font;
import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.ScrollPaneConstants;


	public class FavView extends JFrame 
	{
		private FavController favcntrl;
		private static final int WINDOW_WIDTH = 600;
		private static final int WINDOW_HEIGHT = 600;
		private static final int FAV_HEIGHT = 600;
		private static final int FAV_WIDTH = 600;
		private static final int DISPLAY_WIDTH = 50;
		private static final int DISPLAY_HEIGHT = 50;
		private JScrollPane Stuff = new JScrollPane();
		private JLabel Title = new JLabel();
		private final JPanel Top = new JPanel();
		private final JPanel Entries = new JPanel();
		
		/**
		 * Instantiates a new favorites view
		 * @param favController - the assigned controller
		 */
		public FavView(final FavController favController)
		{
			super("Favourite");
			setResizable(false);
			favcntrl = favController;
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
			ButtonAdapter toGraph;
			Stuff.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			Stuff.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			
			Stuff.setSize(FAV_WIDTH, FAV_HEIGHT);
			GroupLayout groupLayout = new GroupLayout(getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(Stuff, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(Stuff, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
			Top.setBackground(Color.DARK_GRAY);
			
			Stuff.setColumnHeaderView(Top);
			Top.setLayout(new GridLayout(0, 2, 0, 0));
			Top.setSize(50,50);
			Top.add(Title);
			
			Title.setText("Favorites");
			
			Title.setForeground(Color.WHITE);
			Title.setFont(new Font("Dialog", Font.BOLD, 24));
			Title.setHorizontalAlignment(SwingConstants.CENTER);
			Stuff.add(toGraph = new ButtonAdapter("Back To Graph")
			{
				
				public void pressed()
				{
					favcntrl.changeToGraph();
				}
			});
			Top.add(toGraph);
			toGraph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			toGraph.setFont(new Font("Dialog", Font.BOLD, 16));
			toGraph.setForeground(Color.WHITE);
			toGraph.setBackground(Color.BLACK);
			Entries.setPreferredSize(new Dimension(600, 500));
			
			Entries.setBackground(Color.DARK_GRAY);
			
			Stuff.setViewportView(Entries);
			FlowLayout fl_Bottom = new FlowLayout(FlowLayout.LEFT, 0, 5);
			Entries.setLayout(fl_Bottom);
			
			getContentPane().setLayout(groupLayout);
				
		}	
		/**
		 * Updates the favorites view with a new entry
		 * (Section 14.3 and 11.2 in Design Document)
		 * @param expr - the new expression entry
		 */
		public void updateFav(final String expr)
		{
			
			final ButtonAdapter btnNewButton;
			//ButtonAdapter btnNewButton_1 = new JButton("New button");
			
			ButtonAdapter exitButton;
			
			Entries.add(btnNewButton = new ButtonAdapter("y = " + expr)
			{
				public void pressed()
				{
					favcntrl.drawGraph(expr);
				}
			});
			
			Entries.add(exitButton = new ButtonAdapter("X")
			{
				public void pressed()
				{
					favcntrl.remove(expr);
					Entries.remove(btnNewButton);
					Entries.remove(this);
					Entries.repaint();
				}
			});
			
			btnNewButton.setPreferredSize(new Dimension(535, 30));
			btnNewButton.setForeground(Color.WHITE);
			btnNewButton.setBackground(Color.BLACK);
			btnNewButton.setFont(new Font("Dialog", Font.BOLD, 18));
			Entries.add(btnNewButton);
			exitButton.setPreferredSize(new Dimension(50, 30));
			exitButton.setBackground(Color.RED);
			exitButton.setForeground(Color.WHITE);
			exitButton.setFont(new Font("Dialog", Font.BOLD, 18));
			Entries.add(exitButton);
		}
	}


import java.awt.GridLayout;



import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import java.awt.Point;
import java.awt.Cursor;


@SuppressWarnings("serial")
public class View extends JFrame
{
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int DISPLAY_HEIGHT = 300;
	private static final int DISPLAY_WIDTH = 300;
	
	
	private static final String ZERO = "0";
	private static final String ONE = "1";
	private static final String TWO = "2";
	private static final String THREE = "3";
	private static final String FOUR = "4";
	private static final String FIVE = "5";
	private static final String SIX = "6";
	private static final String SEVEN = "7";
	private static final String EIGHT = "8";
	private static final String NINE = "9";
	private static final String DECIMAL_POINT = ".";
	
	
	private GridLayout window_layout_manager = new GridLayout(2, 1);
	private GridLayout keypad_layout_manager = new GridLayout(4, 4);
	private GridLayout operator_layout_manager = new GridLayout(0, 3);
	
	private JPanel display = new JPanel();
	private JPanel keypad = new JPanel();
	private JPanel operators = new JPanel();
	
	private JLabel value_display = new JLabel();
	private JLabel history_display = new JLabel();
	
	
	private Controller controller;
	private final JPanel panel = new JPanel();
	private final ButtonAdapter graph = new ButtonAdapter("Graph") {
	public void pressed() {
		controller.changeToGraph(controller);
	}
};
	
	
	public View()
	{
		super("Numeric Keypad");
		setLocation(new Point(800, 200));
		setResizable(false);
		setTitle("PostFix Calculator");
		getContentPane().setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getContentPane().setLayout(window_layout_manager);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controller = new Controller(this);
		display.setBackground(Color.DARK_GRAY);
		
		display.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		display.setVisible(true);
		
		
		getContentPane().add(display);
		display.setLayout(null);
		history_display.setOpaque(true);
		history_display.setHorizontalAlignment(SwingConstants.CENTER);
		history_display.setForeground(Color.WHITE);
		history_display.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		history_display.setBackground(new Color(143, 188, 143));
		history_display.setFont(new Font("Dialog", Font.BOLD, 30));
		history_display.setBounds(26, 26, 535, 100);
		display.add(history_display);
		value_display.setBackground(new Color(143, 188, 143));
		value_display.setOpaque(true);
		value_display.setForeground(Color.WHITE);
		value_display.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		value_display.setBounds(26, 134, 535, 78);
		value_display.setHorizontalAlignment(SwingConstants.RIGHT);
		value_display.setFont(new Font("Dialog", Font.BOLD, 30));
		display.add(value_display);
		
		
		
		value_display.setText("0");
		history_display.setText("Start new Calculation");
		graph.setFont(new Font("Dialog", Font.BOLD, 14));
		graph.setForeground(Color.WHITE);
		graph.setBackground(Color.BLACK);
		graph.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		graph.setBounds(504, 230, 84, 63);
		
		display.add(graph);
		panel.setBackground(Color.DARK_GRAY);
		
		getContentPane().add(panel);
		keypad.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		keypad.setBackground(Color.DARK_GRAY);
		keypad.setLocation(12, 12);
		
		keypad.setSize(302, 269);
		keypad_layout_manager.setVgap(5);
		keypad_layout_manager.setHgap(5);
		keypad.setLayout(keypad_layout_manager);
		
		ButtonAdapter seven;
		keypad.add(seven = new ButtonAdapter(SEVEN)
		{
			public void pressed()
			{
				controller.addToEntry(SEVEN);
			}
		});
		seven.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		seven.setFont(new Font("Dialog", Font.BOLD, 18));
		seven.setForeground(Color.WHITE);
		seven.setBackground(Color.BLACK);
		
		ButtonAdapter eight;
		keypad.add(eight = new ButtonAdapter(EIGHT)
		{
			public void pressed()
			{
				controller.addToEntry(EIGHT);
			}
		});
		eight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		eight.setFont(new Font("Dialog", Font.BOLD, 18));
		eight.setForeground(Color.WHITE);
		eight.setBackground(Color.BLACK);
		
		ButtonAdapter nine;
		keypad.add(nine = new ButtonAdapter(NINE)
		{
			public void pressed()
			{
				controller.addToEntry(NINE);
			}
		});
		nine.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nine.setFont(new Font("Dialog", Font.BOLD, 18));
		nine.setForeground(Color.WHITE);
		nine.setBackground(Color.BLACK);
		
		ButtonAdapter four;
		keypad.add(four = new ButtonAdapter(FOUR)
		{
			public void pressed()
			{
				controller.addToEntry(FOUR);
			}
		});
		four.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		four.setFont(new Font("Dialog", Font.BOLD, 18));
		four.setForeground(Color.WHITE);
		four.setBackground(Color.BLACK);
		
		ButtonAdapter five;
		keypad.add(five = new ButtonAdapter(FIVE)
		{
			public void pressed()
			{
				controller.addToEntry(FIVE);
			}
		});
		five.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		five.setFont(new Font("Dialog", Font.BOLD, 18));
		five.setForeground(Color.WHITE);
		five.setBackground(Color.BLACK);
		
		ButtonAdapter six;
		keypad.add(six = new ButtonAdapter(SIX)
		{
			public void pressed()
			{
				controller.addToEntry(SIX);
			}
		});
		six.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		six.setFont(new Font("Dialog", Font.BOLD, 18));
		six.setForeground(Color.WHITE);
		six.setBackground(Color.BLACK);
		
		ButtonAdapter one;
		keypad.add(one = new ButtonAdapter(ONE)
		{
			public void pressed()
			{
				controller.addToEntry(ONE);
			}
		});
		one.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		one.setFont(new Font("Dialog", Font.BOLD, 18));
		one.setForeground(Color.WHITE);
		one.setBackground(Color.BLACK);
		
		ButtonAdapter two;
		keypad.add(two = new ButtonAdapter(TWO)
		{
			public void pressed()
			{
				controller.addToEntry(TWO);
			}
		});
		two.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		two.setFont(new Font("Dialog", Font.BOLD, 18));
		two.setForeground(Color.WHITE);
		two.setBackground(Color.BLACK);
		
		ButtonAdapter three;
		keypad.add(three = new ButtonAdapter(THREE)
		{
			public void pressed()
			{
				controller.addToEntry(THREE);
			}
		});
		three.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		three.setFont(new Font("Dialog", Font.BOLD, 18));
		three.setForeground(Color.WHITE);
		three.setBackground(Color.BLACK);
		
		ButtonAdapter zero;
		keypad.add(zero = new ButtonAdapter(ZERO)
		{
			public void pressed()
			{
				controller.addToEntry(ZERO);
			}
		});
		zero.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		zero.setFont(new Font("Dialog", Font.BOLD, 18));
		zero.setForeground(Color.WHITE);
		zero.setBackground(Color.BLACK);
		
		ButtonAdapter decimal_point;
		keypad.add(decimal_point = new ButtonAdapter(DECIMAL_POINT)
		{
			public void pressed()
			{
				controller.addToEntry(DECIMAL_POINT);
			}
		});
		decimal_point.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		decimal_point.setFont(new Font("Dialog", Font.BOLD, 18));
		decimal_point.setForeground(Color.WHITE);
		decimal_point.setBackground(Color.BLACK);
		
		ButtonAdapter enter;
		keypad.add(enter = new ButtonAdapter(Controller.ENTER)
		{
			public void pressed()
			{
				controller.enter();
			}
		});
		enter.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		enter.setFont(new Font("Dialog", Font.BOLD, 18));
		enter.setForeground(Color.WHITE);
		enter.setBackground(Color.ORANGE);
		
		operators.setBackground(Color.DARK_GRAY);
		operators.setLocation(326, 12);
		
		operators.setSize(262, 269);
		operator_layout_manager.setVgap(5);
		operator_layout_manager.setHgap(5);
		operators.setLayout(operator_layout_manager);
		
		ButtonAdapter plus;
		operators.add(plus = new ButtonAdapter(Controller.PLUS)
		{
			public void pressed()
			{
				controller.sum();
				controller.historyOperation(Controller.PLUS);
			}
			
		});
		plus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		plus.setFont(new Font("Dialog", Font.BOLD, 18));
		plus.setForeground(Color.WHITE);
		plus.setBackground(Color.RED);
		
		ButtonAdapter minus;
		operators.add(minus = new ButtonAdapter(Controller.MINUS)
		{
			public void pressed()
			{
				controller.subtract();
				controller.historyOperation(Controller.MINUS);
			}
			
		});
		minus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minus.setFont(new Font("Dialog", Font.BOLD, 18));
		minus.setForeground(Color.WHITE);
		minus.setBackground(Color.RED);
		
		
		ButtonAdapter fact;
		operators.add(fact = new ButtonAdapter(Controller.FACT)
		{
			public void pressed()
			{
				controller.factorial();
				controller.factHistoryOperation(Controller.FACT);
			}
			
		});
		fact.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		fact.setFont(new Font("Dialog", Font.BOLD, 18));
		fact.setForeground(Color.WHITE);
		fact.setBackground(Color.BLACK);
		
		ButtonAdapter div;
		operators.add(div = new ButtonAdapter(Controller.DIV)
		{
			public void pressed()
			{
				controller.divide();
				controller.historyOperation(Controller.DIV);
			}
			
		});
		div.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		div.setFont(new Font("Dialog", Font.BOLD, 18));
		div.setForeground(Color.WHITE);
		div.setBackground(Color.RED);
		
		ButtonAdapter mult;
		operators.add(mult = new ButtonAdapter(Controller.MULT)
		{
			public void pressed()
			{
				controller.multiply();
				controller.historyOperation(Controller.MULT);
			}
					
		});
		mult.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mult.setFont(new Font("Dialog", Font.BOLD, 18));
		mult.setForeground(Color.WHITE);
		mult.setBackground(Color.RED);
		
		ButtonAdapter plusminus;
		operators.add(plusminus = new ButtonAdapter(Controller.PLUSMINUS)
		{
			public void pressed()
			{
				controller.negate();
			}
			
		});
		plusminus.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		plusminus.setFont(new Font("Dialog", Font.BOLD, 18));
		plusminus.setForeground(Color.WHITE);
		plusminus.setBackground(Color.BLACK);
		
		ButtonAdapter cos;
		operators.add(cos = new ButtonAdapter("cos")
		{
			public void pressed()
			{
				controller.cos();
				controller.trigHistoryOperation(Controller.COS);
			}
			
		});
		cos.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cos.setFont(new Font("Dialog", Font.BOLD, 18));
		cos.setForeground(Color.WHITE);
		cos.setBackground(Color.BLACK);
		
		ButtonAdapter sin;
		operators.add(sin = new ButtonAdapter("sin")
		{
			public void pressed()
			{
				controller.sin();
				controller.trigHistoryOperation(Controller.SIN);
			}
			
		});
		sin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sin.setFont(new Font("Dialog", Font.BOLD, 18));
		sin.setForeground(Color.WHITE);
		sin.setBackground(Color.BLACK);
		
		ButtonAdapter pi;
		operators.add(pi = new ButtonAdapter(Controller.PI)
		{
			public void pressed()
			{
				
				controller.pi();
			}
		});
		pi.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		pi.setFont(new Font("Dialog", Font.BOLD, 18));
		pi.setForeground(Color.WHITE);
		pi.setBackground(Color.BLACK);
		
		ButtonAdapter undo;
		operators.add(undo = new ButtonAdapter(Controller.UNDO)
		{
			public void pressed()
			{
				controller.undo();
			}
		});
		undo.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		undo.setFont(new Font("Dialog", Font.BOLD, 16));
		undo.setForeground(Color.WHITE);
		undo.setBackground(Color.ORANGE);

		ButtonAdapter clear;
		operators.add(clear = new ButtonAdapter(Controller.CLEAR)
		{
			public void pressed()
			{
				controller.clear();
			}
		});
		clear.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clear.setFont(new Font("Dialog", Font.BOLD, 18));
		clear.setForeground(Color.WHITE);
		clear.setBackground(Color.ORANGE);
		
		ButtonAdapter expression;
		operators.add(expression = new ButtonAdapter(Controller.EXPRESSION)
		{
			public void pressed()
			{
				controller.expression();
			}
		});
		expression.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		expression.setFont(new Font("Dialog", Font.BOLD, 18));
		expression.setForeground(Color.WHITE);
		expression.setBackground(Color.BLACK);
		panel.setLayout(null);
		panel.add(keypad);
		panel.add(operators);
	}
	
	public void updateValue(String next_value)
	{
		
		value_display.setText(next_value);
	}
	
	public void updateHistory(String next_result)
	{
		history_display.setText("<html>" + next_result + "</html>");
	}
	
	public void clearDisplay()
	{
		value_display.setText("0");
		history_display.setText("Start new Calculation");
	}
}

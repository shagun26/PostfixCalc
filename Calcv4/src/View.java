import java.awt.GridLayout;


//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class View extends JFrame
{
	private static final int WINDOW_WIDTH = 600;
	private static final int WINDOW_HEIGHT = 600;
	private static final int KEYPAD_WIDTH = 200;
	private static final int KEYPAD_HEIGHT = 200;
	private static final int OPERATOR_HEIGHT = 100;
	private static final int OPERATOR_WIDTH = 100;
	private static final int DISPLAY_HEIGHT = 300;
	private static final int DISPLAY_WIDTH = 300;
	
	private static final String CLEAR = "Clear";
	private static final String ENTER = "Enter";
	private static final String PLUS = "+";
	private static final String MULT = "x";
	private static final String DIV = "÷";
	private static final String MINUS = "-";
	
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
	
	
	
	private GridLayout window_layout_manager = new GridLayout(3, 1);
	private GridLayout display_layout_manager = new GridLayout(2, 1);
	private GridLayout keypad_layout_manager = new GridLayout(4, 4);
	private GridLayout operator_layout_manager = new GridLayout(5, 1);
	
	/*private JButton zero = new JButton("0");
	private JButton one = new JButton("1");
	private JButton two = new JButton("2");
	private JButton three = new JButton("3");
	private JButton four = new JButton("4");
	private JButton five = new JButton("5");
	private JButton six = new JButton("6");
	private JButton seven = new JButton("7");
	private JButton eight = new JButton("8");
	private JButton nine = new JButton("9");*/
	
	//private JButton clear = new JButton("Clear");
	//private JButton enter = new JButton("Enter");
	
	//private JButton plus = new JButton("+");
	//private JButton minus = new JButton("-");
	//private JButton divide = new JButton("/");
	//private JButton multiply = new JButton("*");
	//private JButton decimal_point = new JButton(".");
	
	private JPanel display = new JPanel();
	private JPanel keypad = new JPanel();
	private JPanel operators = new JPanel();
	
	private JLabel value_display = new JLabel();
	private JLabel history_display = new JLabel();
	
	//private StringBuilder sb = new StringBuilder();
	
	private Controller controller = new Controller(this);
	
	
	public View()
	{
		super("Numeric Keypad");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(window_layout_manager);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		keypad.setSize(KEYPAD_WIDTH, KEYPAD_HEIGHT);
		keypad.setLayout(keypad_layout_manager);
		keypad.setVisible(true);
		
		operators.setSize(OPERATOR_HEIGHT, OPERATOR_WIDTH);
		operators.setLayout(operator_layout_manager);
		operators.setVisible(true);
		
		display.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		display.setLayout(display_layout_manager);
		display.setVisible(true);
		
		
		add(display);
		add(keypad);
		add(operators);
		display.add(history_display);
		display.add(value_display);
		
		
		keypad.add(new ButtonAdapter(ONE)
		{
			public void pressed()
			{
				controller.addToEntry(ONE);
			}
		});
		
		keypad.add(new ButtonAdapter(TWO)
		{
			public void pressed()
			{
				controller.addToEntry(TWO);
			}
		});
		
		keypad.add(new ButtonAdapter(THREE)
		{
			public void pressed()
			{
				controller.addToEntry(THREE);
			}
		});
		
		keypad.add(new ButtonAdapter(FOUR)
		{
			public void pressed()
			{
				controller.addToEntry(FOUR);
			}
		});
		
		keypad.add(new ButtonAdapter(FIVE)
		{
			public void pressed()
			{
				controller.addToEntry(FIVE);
			}
		});
		
		keypad.add(new ButtonAdapter(SIX)
		{
			public void pressed()
			{
				controller.addToEntry(SIX);
			}
		});
		
		keypad.add(new ButtonAdapter(SEVEN)
		{
			public void pressed()
			{
				controller.addToEntry(SEVEN);
			}
		});
		
		keypad.add(new ButtonAdapter(EIGHT)
		{
			public void pressed()
			{
				controller.addToEntry(EIGHT);
			}
		});
		
		keypad.add(new ButtonAdapter(NINE)
		{
			public void pressed()
			{
				controller.addToEntry(NINE);
			}
		});
		/*keypad.add(two);
		keypad.add(three);
		keypad.add(four);
		keypad.add(five);
		keypad.add(six);
		keypad.add(seven);
		keypad.add(eight);
		keypad.add(nine);*/
		
		keypad.add(new ButtonAdapter(CLEAR)
		{
			public void pressed()
			{
				controller.clear();
			}
		});
		//keypad.add(clear);
		
		//keypad.add(zero);
		
		keypad.add(new ButtonAdapter(ZERO)
		{
			public void pressed()
			{
				controller.addToEntry(ZERO);
			}
		});
		
		keypad.add(new ButtonAdapter(ENTER)
		{
			public void pressed()
			{
				controller.enter();
			}
		});
		
		//keypad.add(enter);
		
		
		//operators.add(plus);
		operators.add(new ButtonAdapter(PLUS)
		{
			public void pressed()
			{
				controller.sum();
				controller.historyOperation(PLUS);
			}
			
		});
		
		
		//operators.add(minus);
		operators.add(new ButtonAdapter(MINUS)
		{
			public void pressed()
			{
				controller.subtract();
				controller.historyOperation(MINUS);
			}
			
		});
		
		//operators.add(multiply);
		
		operators.add(new ButtonAdapter(MULT)
		{
			public void pressed()
			{
				controller.multiply();
				controller.historyOperation(MULT);
			}
			
		});
		
		//operators.add(divide);
		
		operators.add(new ButtonAdapter(DIV)
		{
			public void pressed()
			{
				controller.divide();
				controller.historyOperation(DIV);
			}
			
		});
		
		
		//operators.add(decimal_point);
		operators.add(new ButtonAdapter(DECIMAL_POINT)
		{
			public void pressed()
			{
				controller.addToEntry(DECIMAL_POINT);
			}
		});
		
		/*zero.addActionListener(controller);
		one.addActionListener(controller);
		two.addActionListener(controller);
		three.addActionListener(controller);
		four.addActionListener(controller);
		five.addActionListener(controller);
		six.addActionListener(controller);
		seven.addActionListener(controller);
		eight.addActionListener(controller);
		nine.addActionListener(controller);*/
		
		//clear.addActionListener(controller);
		//plus.addActionListener(controller);
		//enter.addActionListener(controller);
		//decimal_point.addActionListener(controller);
		
		value_display.setText("0");
		history_display.setText("Start new Calculation");
	}
	
	public void updateValue(String next_result)
	{
		//sb.append(next_result);
		value_display.setText(next_result);
	}
	
	public void updateHistory(String next_result)
	{
		history_display.setText(next_result);
	}
	
	public void clearDisplay()
	{
		//sb.delete(0, sb.length());
		value_display.setText("0");
		history_display.setText("Start new Calculation");
	}
	
	
}

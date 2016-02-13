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
	private static final String DIV = "/";
	private static final String MINUS = "-";
	private static final String FACT = "!";
	//private static final String UNDO = "Undo";
	private static final String PLUSMINUS = "±";
	private static final String COS = "COS(";
	private static final String SIN = "SIN(";
	
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
	
	private JPanel display = new JPanel();
	private JPanel keypad = new JPanel();
	private JPanel operators = new JPanel();
	
	private JLabel value_display = new JLabel();
	private JLabel history_display = new JLabel();
	
	
	private Controller controller;
	
	
	public View()
	{
		super("Numeric Keypad");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLayout(window_layout_manager);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controller = new Controller(this);
		
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
		
		
		keypad.add(new ButtonAdapter(CLEAR)
		{
			public void pressed()
			{
				controller.clear();
			}
		});
		
		
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
		
		
		operators.add(new ButtonAdapter(PLUS)
		{
			public void pressed()
			{
				controller.sum();
				controller.historyOperation(PLUS);
			}
			
		});
		
		
		
		operators.add(new ButtonAdapter(MINUS)
		{
			public void pressed()
			{
				controller.subtract();
				controller.historyOperation(MINUS);
			}
			
		});
		
		
		
		operators.add(new ButtonAdapter(MULT)
		{
			public void pressed()
			{
				controller.multiply();
				controller.historyOperation(MULT);
			}
			
		});
		
		
		
		operators.add(new ButtonAdapter(DIV)
		{
			public void pressed()
			{
				controller.divide();
				controller.historyOperation(DIV);
			}
			
		});
		
		operators.add(new ButtonAdapter(FACT)
		{
			public void pressed()
			{
				controller.factorial();
				controller.factHistoryOperation(FACT);
			}
					
		});
		
		/*operators.add(new ButtonAdapter(PLUSMINUS)
		{
			public void pressed()
			{
				controller.negate();
				controller.singleHistoryOperation(PLUSMINUS);
			}
			
		});*/
		
		operators.add(new ButtonAdapter("COS")
		{
			public void pressed()
			{
				controller.cos();
				controller.trigHistoryOperation(COS);
			}
			
		});
		
		operators.add(new ButtonAdapter("SIN")
		{
			public void pressed()
			{
				controller.sin();
				controller.trigHistoryOperation(SIN);
			}
			
		});
		
		
		operators.add(new ButtonAdapter(DECIMAL_POINT)
		{
			public void pressed()
			{
				controller.addToEntry(DECIMAL_POINT);
			}
		});
		
		/*operators.add(new ButtonAdapter(UNDO)
		{
			public void pressed()
			{
				controller.undo();
			}
		});*/
		
		value_display.setText("0");
		history_display.setText("Start new Calculation");
	}
	
	public void updateValue(String next_value)
	{
		
		value_display.setText(next_value);
	}
	
	public void updateHistory(String next_result)
	{
		history_display.setText(next_result);
	}
	
	public void clearDisplay()
	{
		value_display.setText("0");
		history_display.setText("Start new Calculation");
	}
	
	
}

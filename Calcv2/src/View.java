import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


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
	
	
	private GridLayout window_layout_manager = new GridLayout(3, 1);
	private GridLayout display_layout_manager = new GridLayout(2, 1);
	private GridLayout keypad_layout_manager = new GridLayout(4, 4);
	private GridLayout operator_layout_manager = new GridLayout(5, 1);
	
	private JButton zero = new JButton("0");
	private JButton one = new JButton("1");
	private JButton two = new JButton("2");
	private JButton three = new JButton("3");
	private JButton four = new JButton("4");
	private JButton five = new JButton("5");
	private JButton six = new JButton("6");
	private JButton seven = new JButton("7");
	private JButton eight = new JButton("8");
	private JButton nine = new JButton("9");
	
	private JButton clear = new JButton("Clear");
	private JButton enter = new JButton("Enter");
	
	private JButton plus = new JButton("+");
	private JButton minus = new JButton("-");
	private JButton divide = new JButton("/");
	private JButton multiply = new JButton("*");
	private JButton decimal_point = new JButton(".");
	
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
		
		
		keypad.add(one);
		keypad.add(two);
		keypad.add(three);
		keypad.add(four);
		keypad.add(five);
		keypad.add(six);
		keypad.add(seven);
		keypad.add(eight);
		keypad.add(nine);
		keypad.add(clear);
		keypad.add(zero);
		keypad.add(enter);
		
		operators.add(plus);
		operators.add(minus);
		operators.add(multiply);
		operators.add(divide);
		operators.add(decimal_point);
		
		zero.addActionListener(controller);
		one.addActionListener(controller);
		two.addActionListener(controller);
		three.addActionListener(controller);
		four.addActionListener(controller);
		five.addActionListener(controller);
		six.addActionListener(controller);
		seven.addActionListener(controller);
		eight.addActionListener(controller);
		nine.addActionListener(controller);
		
		clear.addActionListener(controller);
		plus.addActionListener(controller);
		enter.addActionListener(controller);
		decimal_point.addActionListener(controller);
		
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

import java.awt.GridLayout;



//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Font;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Button;


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
	private static final String MULT = "*";
	private static final String DIV = "/";
	private static final String MINUS = "-";
	private static final String FACT = "!";
	private static final String UNDO = "Undo";
	private static final String PI = "\u03C0";
	private static final String PLUSMINUS = "+/-";
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
	
	private static final String EXPRESSION = 'x' + "";
	
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
	private final ButtonAdapter buttonAdapter = new ButtonAdapter("Graph") {
	public void pressed() {
		
	}
};
	
	
	public View()
	{
		super("Numeric Keypad");
		getContentPane().setBackground(Color.WHITE);
		setForeground(Color.WHITE);
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		getContentPane().setLayout(window_layout_manager);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controller = new Controller(this);
		display.setBackground(UIManager.getColor("Button.background"));
		
		display.setSize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
		display.setVisible(true);
		
		
		getContentPane().add(display);
		display.setLayout(null);
		history_display.setFont(new Font("Dialog", Font.BOLD, 30));
		history_display.setBounds(12, 12, 576, 131);
		display.add(history_display);
		value_display.setBounds(12, 143, 576, 78);
		value_display.setHorizontalAlignment(SwingConstants.TRAILING);
		value_display.setFont(new Font("Dialog", Font.BOLD, 30));
		display.add(value_display);
		
		
		
		value_display.setText("0");
		history_display.setText("Start new Calculation");
		buttonAdapter.setBounds(504, 233, 84, 63);
		
		display.add(buttonAdapter);
		
		getContentPane().add(panel);
		keypad.setLocation(12, 12);
		
		keypad.setSize(302, 269);
		keypad_layout_manager.setVgap(5);
		keypad_layout_manager.setHgap(5);
		keypad.setLayout(keypad_layout_manager);
		
		
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
		operators.setLocation(326, 12);
		
		operators.setSize(262, 269);
		operator_layout_manager.setVgap(5);
		operator_layout_manager.setHgap(5);
		operators.setLayout(operator_layout_manager);
		
		
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
		
		operators.add(new ButtonAdapter(PLUSMINUS)
		{
			public void pressed()
			{
				controller.negate();
			}
			
		});
		
		operators.add(new ButtonAdapter("cos")
		{
			public void pressed()
			{
				controller.cos();
				controller.trigHistoryOperation(COS);
			}
			
		});
		
		operators.add(new ButtonAdapter("sin")
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
		
		operators.add(new ButtonAdapter(UNDO)
		{
			public void pressed()
			{
				controller.undo();
			}
		});
		
		
		operators.add(new ButtonAdapter(PI)
		{
			public void pressed()
			{
				
				controller.pi();
			}
		});
		
		operators.add(new ButtonAdapter(EXPRESSION)
		{
			public void pressed()
			{
				controller.expression();
			}
			
			
			
		});
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
		history_display.setText(next_result);
	}
	
	public void clearDisplay()
	{
		value_display.setText("0");
		history_display.setText("Start new Calculation");
	}
}

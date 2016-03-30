import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


@SuppressWarnings("serial")
public abstract class ButtonAdapter extends JButton implements ActionListener
{

	/**
	 * Creates a new button adapter with label
	 * @param Button_Symbol - the label
	 */
	public ButtonAdapter(String Button_Symbol)
	{
		super(Button_Symbol);
		addActionListener(this);
	}
	/**
	 * Performs a set of instructions when a ButtonAdapter
	 * pressed
	 */
	public void actionPerformed(ActionEvent e)
	{
		pressed();
	}
	/**
	 * The actions once a button is pressed
	 */
	public abstract void pressed();
	
	
	
}

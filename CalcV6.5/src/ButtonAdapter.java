import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


@SuppressWarnings("serial")
public abstract class ButtonAdapter extends JButton implements ActionListener
{

	public ButtonAdapter(String Button_Symbol)
	{
		super(Button_Symbol);
		addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		pressed();
	}
	
	public abstract void pressed();
	
	
	
}

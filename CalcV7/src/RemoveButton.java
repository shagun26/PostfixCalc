import java.awt.event.ActionListener;


public abstract class RemoveButton extends ButtonAdapter implements ActionListener
{
	private String button;
	public RemoveButton(String label, String expr)
	{
		super(label);
		addActionListener(this);
		button = expr;
	}
	
	public void actionPerformed()
	{
		delete(button);
	}
	
	public abstract void delete(String expr);
}

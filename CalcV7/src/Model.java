import java.util.ArrayList;
import java.util.Stack;


public class Model
{
	
	private  BinaryOperations bin_code;
	private  SingleOperations single_code;
	
	/**
	 * Stores a history of entered values and operations.
	 * (Section 3.3 in Design Document)
	 */
	private Stack<String> button_history = new Stack<String>();
	
	private Stack<Stack<String>> button_history_undo = new Stack<Stack<String>>();
	
	private static final String EQUALS = "=";
	
	/**
	 * Stores any entered values or results of operations.
	 * (Section 3.3 in Design Document)
	 */
	private Stack<Double> stored_values = new Stack<Double>();
	
	private Stack<Stack<Double>> stored_values_undo = new Stack<Stack<Double>>();
	
	/**
	 * A like-for-like copy of button_history.
	 * Used to print the history.
	 * (Section 3.3 in Design Document)
	 */
	private ArrayList<String> running_history = new ArrayList<String>();
	
	private Stack<ArrayList<String>> running_history_undo = new Stack<ArrayList<String>>();
	
	
	/**
	 * Stores the operators that are responsible for values
	 * in stored_values (excluding Entered numbers)
	 * (Section 3.3 in Design Document)
	 */
	
	private Stack<String> precedence= new Stack<String>();
	
	
	
	/**
	 * A reference to high precedence operators not including 
	 * factorial, sin and cos.
	 */
	private ArrayList<String> high_precedence = new ArrayList<String>();
	
	/**
	 * A reference to low precedence operators
	 */
	private ArrayList<String> lowest_precedence = new ArrayList<String>();
	
	/**
	 * 
	 */
	private ArrayList<String> highest_precedence = new ArrayList<String>();
	
	/**
	 * Stores a list of expressions
	 */
	private Stack<String> expressionsInFix = new Stack<String>();
	
	private Stack<String> expressionsPostFix = new Stack<String>();
	
	
	/**
	 * Used to trace character presses.
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb = new StringBuilder();
	
	/**
	 * Same functionality as sb except used for
	 * updates to history
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb_input_history = new StringBuilder();
	
	
	/**
	 * Used to construct next entry into button_history
	 * and running_history after an operation
	 * (Section 3.3 in Design Document)
	 */
	private StringBuilder sb_completed_operations = new StringBuilder();
	
	/**
	 * Indicates whether operands from only
	 * stored_values were used in an operation or otherwise
	 * (Section 3.3 in Design Document)
	 */
	private boolean from_memory = false;
	private boolean pi = false;
	private boolean error = false;
	
	/**
	 * Indicates whether an operation uses an expression
	 */
	private boolean opExpression = false;
	
	/**
	 * Indicates whether the expression list has already been
	 * updated
	 */
	private boolean exprUpdated = false;
	
	
	/**
	 * Instantiate a Model object in its default state
	 */
	public Model()
	{
		reset();
		
		high_precedence.add(Controller.MULT);
		high_precedence.add(Controller.DIV);
		
		lowest_precedence.add(Controller.PLUS);
		lowest_precedence.add(Controller.MINUS);
		
		
		highest_precedence.add(Controller.FACT);
		
	}
	
	public Stack<String> getInFixExpressionList()
	{
		return expressionsInFix;
	}
	
	public Stack<String> getPostExpressionList()
	{
		return expressionsPostFix;
	}
	
	
	/**
	 * Returns the String representation of running_history as 
	 * specified in the Requirements Document
	 * @return The string representation of running_history
	 */
	public String printHistory()
	{
		int size = running_history.size();
		/*if(size == 1)
		{
			return arraylist.get(0);
		}
		return printHistory(arraylist, size - 1) + ", " + arraylist.get(size - 1);*/
		if(size == 0)
			return "";
		
		StringBuilder result = new StringBuilder();
		for(int i = 0; i < size - 1; i++)
		{
			result.append(running_history.get(i) + ", ");
		}
		result.append(running_history.get(size - 1));
		return result.toString();
		
	}
	
	
	/**
	 * Enters the Java representation of PI into stored_values
	 * @return the Java value of Pi as a String
	 */
	public String valuePi()
	{
		pi = true;
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		stored_values.push(Math.PI);
		stored_values_undo.push((Stack<Double>) stored_values.clone());
		return "" + Math.PI;
		
	}
	
	/**
	 * Enters the symbol of PI into button_history and running_history
	 * @return The updated history String
	 */
	public String historyPi()
	{
		
		if(!button_history.empty())
		{
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
		}
		
		button_history.push(Controller.PI);
		running_history.add(Controller.PI);
		
		return printHistory();
	}
	
	/**
	 * Returns the expression symbol to be displayed
	 * in the value field
	 * @return - the expression symbol
	 */
	public String expressionVal()
	{
		opExpression = true;
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		return Controller.EXPRESSION;
	}
	
	/**
	 * Adds the expression symbol to the history and expression list.
	 * Returns the updated history
	 * @return - the updated history
	 */
	public String expressionHist()
	{
		
		if(!button_history.empty())
		{
			button_history_undo.push((Stack<String>) button_history.clone());
			running_history_undo.push((ArrayList<String>) running_history.clone());
		}
		
		button_history.push(Controller.EXPRESSION);
		running_history.add(Controller.EXPRESSION);
		expressionsInFix.push(Controller.EXPRESSION);
		expressionsPostFix.push(Controller.EXPRESSION);
		return printHistory();
	}
	
	/**
	 * Appends the next character to sb
	 * @param button - the character to be appended
	 */
	public void addToEntry(String button)
	{
		/*if(button.equals("" + PI))
		{
			sb.delete(0, sb.length());
			
			//Set pi true for other functions
			pi = true;
			
		}
		else
		{
			//Set pi false for other functions
			pi = false;
		}*/
		sb.append(button);
		sb_input_history.append(button);
		pi = false;
	}
	
	/**
	 * Checks whether button_history is empty or not
	 * @return true when button_history is empty. False otherwise
	 */
	public boolean isHistoryEmpty()
	{
		return(button_history.empty());
	}
	
	/**
	 * Returns the character sequence sb
	 * @return The String representation of sb
	 */
	public String updateValue()
	{
		return sb.toString();
	}
	
	/**
	 * Sets the model to its default state
	 * (Section 3.4 in Design Document)
	 */
	public void reset()
	{
		stored_values.clear();
		stored_values_undo.clear();
		button_history.clear();
		button_history_undo.clear();
		precedence.clear();
		expressionsInFix.clear();
		expressionsPostFix.clear();
		
		opExpression = false;
		exprUpdated = false;
		pi = false;
		error = false;
		
		sb_input_history.delete(0, sb_input_history.length());
		sb_completed_operations.delete(0, sb_completed_operations.length());
		running_history.clear();
		sb.delete(0, sb.length());
	}
	
	/**
	 * Carries out the addition of two operands
	 * and returns the result
	 * (Section 3.4 in Design Document)
	 * @return The value of the addition as a String
	 */
	public String sum()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		bin_code = new SumOperation();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				double value = bin_code.calculate(stored_values.pop(), stored_values.pop());
				stored_values.push(value);
				sb.delete(0, sb.length());
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				zeroCheckExpr();
				operandHistory(Controller.PLUS);
				return button_history.peek() ;
			}
			
		}
		else
		{
			from_memory = false;
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckSingle(stored_values, button_history);
				double history = Double.parseDouble(sb.toString());
				sb.delete(0, sb.length());
				
				double value = bin_code.calculate(history, stored_values.pop());
				stored_values.push(value);
				
				if(isInt(history, (int) history))
				{
					sb_input_history.delete(0, sb_input_history.length());
					sb_input_history.append((int) history);
				}
				
				return "" + value;
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				sb.delete(0, sb.length());
				operandHistory(Controller.PLUS);
				return button_history.peek() ;
			}
			
		}
	}
	
	
	/**
	 * Carries out the subtraction of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the subtraction as a String
	 */
	public String subtract()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		bin_code = new MinusOperation();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				double value = bin_code.calculate(stored_values.pop(), stored_values.pop());
				stored_values.push(value);
				//double value = subStoredValues();
				sb.delete(0, sb.length());
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				zeroCheckExpr();
				operandHistory(Controller.MINUS);
				return button_history.peek();
			}
			
		}
		else
		{
			from_memory = false;
			//If not expression, continue as normal
			if(!opExpression)
			{
				//double value = subStoredWithHistory();
				bin_code.zeroCheckSingle(stored_values, button_history);
				double history = Double.parseDouble(sb.toString());
				sb.delete(0, sb.length());
				
				double value = bin_code.calculate(history, stored_values.pop());
				stored_values.push(value);
				
				if(isInt(history, (int) history))
				{
					sb_input_history.delete(0, sb_input_history.length());
					sb_input_history.append((int) history);
				}
				
				
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				sb.delete(0, sb.length());
				operandHistory(Controller.MINUS);
				return button_history.peek();
			}
			
		}
	}
	
	/**
	 * Carries out the product of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the product as a String
	 */
	public String multiply()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		bin_code = new MultOperation();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				double value = bin_code.calculate(stored_values.pop(), stored_values.pop());
				stored_values.push(value);
				sb.delete(0, sb.length());
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				zeroCheckExpr();
				operandHistory(Controller.MULT);
				return button_history.peek();
			}
			
		}
		else
		{
			from_memory = false;
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckSingle(stored_values, button_history);
				double history = Double.parseDouble(sb.toString());
				sb.delete(0, sb.length());
				
				double value = bin_code.calculate(history, stored_values.pop());
				stored_values.push(value);
				
				if(isInt(history, (int) history))
				{
					sb_input_history.delete(0, sb_input_history.length());
					sb_input_history.append((int) history);
				}
				
				
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				sb.delete(0, sb.length());
				operandHistory(Controller.MULT);
				return button_history.peek();
			}
			
		}
	}
	
	/**
	 * Carries out the division of two operands
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the division as a String
	 */
	public String divide()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		bin_code = new DivideOperation();
		
		if(sb.toString().equals(""))
		{	
			from_memory = true;
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckBinary(stored_values, button_history);
				double value = bin_code.calculate(stored_values.pop(), stored_values.pop());
				stored_values.push(value);
				sb.delete(0, sb.length());
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				zeroCheckExpr();
				operandHistory(Controller.DIV);
				return button_history.peek();
			}
			
		}
		else
		{
			from_memory = false;
			//If not expression, continue as normal
			if(!opExpression)
			{
				bin_code.zeroCheckSingle(stored_values, button_history);
				double history = Double.parseDouble(sb.toString());
				sb.delete(0, sb.length());
				
				double value = bin_code.calculate(history, stored_values.pop());
				stored_values.push(value);
				
				if(isInt(history, (int) history))
				{
					sb_input_history.delete(0, sb_input_history.length());
					sb_input_history.append((int) history);
				}
				
				
				return "" + value;	
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				sb.delete(0, sb.length());
				operandHistory(Controller.DIV);
				return button_history.peek();
			}
			
		}
	}
	
	public String negate()
	{
		if(sb.toString().equals(""))
		{
			from_memory = true;
			double value = negateStoredValues();
			sb.delete(0, sb.length());
			sb_input_history.delete(0, sb_input_history.length());
			
			int alt = (int) value;
			
			if(isInt(Math.abs(value), Math.abs(alt)))
			{
				sb.append("" + (int) value);
				sb_input_history.append("" + (int) value);
			}
			else
			{
				sb.append("" + value);
				sb_input_history.append("" + value);
				
			}
				
			
			return sb.toString();
		}
		else
		{
			//If negation during number entry,
			//the value shown to the user must be updated
			from_memory = false;
			negateStoredWithHistory();
			return sb.toString();
		}

	}
	
	/**
	 * Carries out the factorial of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the factorial as a String
	 */
	public String factorial()
	{
		single_code = new FactorialOperation();
		opExpression = isExpression();
		
		if(opExpression)
		{
			error = true;
			return "NOT DEFINED";
		}
			
		
		if(sb.toString().equals(""))
		{
			from_memory = true;
			System.out.println("Fact from stored_values");
			double input = stored_values.pop();
			
			if(!isInt(input, (int)(input) )||  input < 0)
			{
				stored_values.push(input);
				error = true;
				return "NOT DEFINED";
			}
			
			double value = single_code.calculate(input);
			
			if(Double.isInfinite(value))
			{
				stored_values.push(input);
				error = true;
				return "NOT DEFINED";
			}
			
			stored_values.push(value);
			return "" + value;
		}
		else
		{
			from_memory = false;
			double input = Double.parseDouble(sb.toString());
			sb.delete(0, sb.length());
			if(!isInt(input, (int)(input) )|| input < 0)
			{
				//stored_values.push(input);
				error = true;
				return "NOT DEFINED";
			}
		
			double value = single_code.calculate(input);
			
			if(Double.isInfinite(value))
			{
				//stored_values.push(input);
				error = true;
				return "NOT DEFINED";
			}
			
			stored_values.push(value);
			return "" + value;
			
		}
		
	}
	
	/**
	 * Carries out the sin of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the sin as a String
	 */
	public String sin()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		single_code = new SinOperation();
		
		if(sb.toString().equals(""))
		{
			from_memory = true;
			//If not expression, continue as normal
			if(!opExpression)
			{
				single_code.zeroCheckSingle(stored_values, button_history);
				double value = single_code.calculate(stored_values.pop());
				stored_values.push(value);
				sb.delete(0, sb.length());
				return "" + value;
				
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				sb.delete(0, sb.length());
				trigHistory(Controller.SIN);
				return button_history.peek();
			}
			
			
		}
		else
		{
			from_memory = false;
			double history = Double.parseDouble(sb.toString());
			sb.delete(0, sb.length());
			
			double value = single_code.calculate(history);
			
			if(isInt(history, (int) history))
			{
				sb_input_history.delete(0, sb_input_history.length());
				sb_input_history.append((int) history);
			}
			
			stored_values.push(value);
			
			return "" + value;
		}

	}

	/**
	 * Carries out the cosine of one operand
	 * and returns the result
	 * (Section 3.4 in the Design Document)
	 * @return The value of the cosine as a String
	 */
	public String cos()
	{
		//Determine whether an expression is involved
		opExpression = isExpression();
		single_code = new CosOperation();
		
		if(sb.toString().equals(""))
		{
			from_memory = true;
			//If not expression, continue as normal
			if(!opExpression)
			{
				single_code.zeroCheckSingle(stored_values, button_history);
				double value = single_code.calculate(stored_values.pop());
				stored_values.push(value);
				sb.delete(0, sb.length());
				return "" + value;
			}
			//Otherwise update the history
			//and return top element (new expression)
			else
			{
				sb.delete(0, sb.length());
				trigHistory(Controller.COS);
				return button_history.peek();
			}
			
		}
		else
		{
			from_memory = false;
			double history = Double.parseDouble(sb.toString());
			sb.delete(0, sb.length());
			
			double value = single_code.calculate(history);
			
			if(isInt(history, (int) history))
			{
				sb_input_history.delete(0, sb_input_history.length());
				sb_input_history.append((int) history);
			}
			
			stored_values.push(value);
			
			return "" + value;
		}

	}
	
	/**
	 * Updates button_history and running_history
	 * after a Binary Operation. Returns the updated
	 * history string
	 * @param operator - The operator of the binary operation
	 * @return The updated history string
	 */
	public String operandHistory(String operator)
	{
		String first;
		String second;
		
		//If call made with an expression
		//that was already updated, set both boolean
		//variables to false and return without any changes made
		if(opExpression && exprUpdated)
		{
			opExpression = false;
			exprUpdated = false;
			return printHistory() + " " + EQUALS;
		}
		//Else if expression has not been updated,
		//set boolean variable true to indicate that it will be
		//after this call
		else if(opExpression && !exprUpdated)
		{
			exprUpdated = true;
		}
		
		//If not expression, continue as normal
		
		second = button_history.pop();
		
		if(!from_memory)
		{
			//If the stack is empty or operation is completed using a button press, 
			//use the button press to update to the stack
			if(!checkBrackets(second, operator))
			{
				sb_completed_operations.append(second + " " + operator + " " + sb_input_history.toString());
			}
			else
			{
				sb_completed_operations.append("(" + second + ")" + " " + operator + " " +  sb_input_history.toString());
			}
			
			//Update precedence list
			precedence.push(operator);
			
			System.out.println(precedence.toString());
			
			if(opExpression)
			{
				expressionsPostFix.push(sb_input_history.toString());
				expressionsPostFix.push(operator);
			}
			
			System.out.println(expressionsPostFix.toString());
			updateHistDirect();
			//prev_history.push(operator);
			
			return printHistory() + EQUALS;
		}
		
		//Else, use the last two entries in the stack and anything else as necessary
		
		first = button_history.pop();
		
		if(opExpression)
		{
			if(!stored_values.empty())
				expressionsPostFix.push("" + stored_values.pop());
			
			expressionsPostFix.push(operator);
			System.out.println(expressionsPostFix.toString());
		}
		
		
		formNewEntry(first, second, operator);
		//Update precedence list
		precedence.push(operator);
		
		System.out.println(precedence.toString());
		updateHistMem();
		
		//prev_history.push(operator);
		
		//print updated history
		return printHistory() + " " +  EQUALS;
	}
	
	/**
	 * Updates button_history and running_history
	 * after a factorial. Returns the updated
	 * history string
	 * @param operator - The operator of the factorial operation
	 * @return The updated history string
	 */
	public String factHistory(String operator)
	{
		if(opExpression || error)
		{
			error = false;
			return printHistory();
		}
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(sb_input_history.toString() + operator);
			//Reset button-press string for next use
			sb_input_history.delete(0, sb_input_history.length());
			//Add updated history to the stack (First and only element)
			button_history.push(sb_completed_operations.toString());
			// Reset completed operations string_builder for next entry
			sb_completed_operations.delete(0, sb_completed_operations.length());
			//replace them with updated computation
			running_history.add(button_history.peek());
			//prev_history.push(operator);
			//Update precedence list
			precedence.push(operator);
			System.out.println(precedence.toString());
			
			return printHistory() + EQUALS;
			
		}
		
		String first = button_history.pop();
			
		if(!checkBrackets(first, operator))
			sb_completed_operations.append(first + operator);
		else
			sb_completed_operations.append("(" + first + ")" + operator);
			
		//Update precedence list
		precedence.push(operator);
		System.out.println(precedence.toString());
		sb_input_history.delete(0, sb_input_history.length());
		// Add updated history to the stack
		button_history.push(sb_completed_operations.toString());
		// Reset completed operations string_builder for next use.
		// This prevents duplication of previous entries
		sb_completed_operations.delete(0, sb_completed_operations.length());
		//remove last two elements in running_history	
		if(!(running_history.isEmpty()))
		{
			running_history.remove(running_history.size() - 1);
		}
		//replace them with updated computation
		running_history.add(button_history.peek());
		//print updated history
		return printHistory() + " " + EQUALS;
	}
				
	/**
	 * Adds the 'Entered' number into running_history 
	 * and button_history. Returns the updated history string.
	 * @return The update history string
	 */
	public String enterHistory()
	{
		if(error)
			return printHistory();
			
		if(!button_history.empty())
		{
			running_history_undo.push((ArrayList<String>)running_history.clone());
			button_history_undo.push((Stack<String>) button_history.clone());
		}
			
		if(pi)
		{
			button_history.push(sb_input_history.toString());
			running_history.add(sb_input_history.toString());
		}
		else
		{
			button_history.push(sb.toString());
			running_history.add(sb.toString());
		}
			
		sb_input_history.delete(0, sb_input_history.length());
		sb.delete(0, sb.length());
		return printHistory();
		
	}
	
	/**
	 * Updates button_history and running_history
	 * after a Trigonometric Operation. Returns the updated
	 * history string
	 * @param funct - The trigonometric function carried out
	 * @return The updated history string
	 */
	public String trigHistory(String funct)
	{
		
		//If call made with an expression
		//that was already updated, set both boolean
		//variables to false and return without any changes made
		if(opExpression && exprUpdated)
		{
			opExpression = false;
			exprUpdated = false;
			return printHistory() + " " + EQUALS;
		}
		//Else if expression has not been updated,
		//set boolean variable true to indicate that it will be
		//after this call
		else if(opExpression && !exprUpdated)
		{
			exprUpdated = true;
			expressionsPostFix.push(funct);
			System.out.println(expressionsPostFix.toString());
		}
		
		if(!from_memory)
		{
			//set the string
			sb_completed_operations.append(funct + sb_input_history.toString() + ")");
			//Reset button-press string for next use
			sb_input_history.delete(0, sb_input_history.length());
			//Add updated history to the stack (First and only element)
			button_history.push(sb_completed_operations.toString());
			// Reset completed operations string_builder for next entry
			sb_completed_operations.delete(0, sb_completed_operations.length());
			precedence.push(funct);
			//replace them with updated computation
			running_history.add(button_history.peek());
			
			return printHistory() + EQUALS;
			
		}
		
		String first = button_history.pop();
			
			
		sb_completed_operations.append(funct + first + ")");
			
			
		if(isOp(first))
			precedence.pop();
			
		precedence.push(funct);	
		sb_input_history.delete(0, sb_input_history.length());
		// Add updated history to the stack
		button_history.push(sb_completed_operations.toString());
		// Reset completed operations string_builder for next use.
		// This prevents duplication of previous entries
		sb_completed_operations.delete(0, sb_completed_operations.length());
		//remove last element in running_history
		if(!(running_history.isEmpty()))
		{
			running_history.remove(running_history.size() - 1);
		}
			
		if(!(expressionsInFix.isEmpty()))
		{
			expressionsInFix.pop();
		}
		
		//replace them with updated computation
		running_history.add(button_history.peek());
		expressionsInFix.push(button_history.peek());
		//print updated history
		return printHistory() + " " + EQUALS;
	}
		
		
	
	
	/**
	 * Enters a value into stored_values. Returns the entered value.
	 * @return The entered value as a String
	 */
	public String enterValue()
	{
		if(sb.toString().equals(""))
		{
			if(stored_values.empty() && (!opExpression))
			{
				System.out.println("Empty");
				sb.append(0);
				sb_input_history.append(0);
			}
			else if(opExpression)
			{
				sb.append(button_history.peek());
				return button_history.peek();
			}
			
			prepareForEnter();
		}
		
		double pushed;
		try
		{
			pushed = Double.parseDouble(sb.toString());
		}
		catch (NumberFormatException e)
		{
			error = true;
			System.out.println(sb.toString() + "Hi");
			sb.delete(0, sb.length());
			sb_input_history.delete(0, sb_input_history.length());
			return "INVALID";
		}
		
		error = false;
		stored_values.push(pushed);
		stored_values_undo.push((Stack<Double>) stored_values.clone());
		
		if(isInt(Math.abs(pushed), Math.abs((int) pushed)))
		{
			sb.delete(0, sb.length());
			sb.append("" + (int) pushed);
		}	
		return sb.toString();
	}
	
	private void prepareForEnter()
	{
		double push = stored_values.peek();
		if(isInt(push, (int) push))
		{
			sb_input_history.append((int) push);
			pi = false;
		}
		
		if(push == Math.PI)
		{
			sb_input_history.append(Controller.PI);
			
			pi = true;
		}
		else
		{
			pi = false;
			sb_input_history.append(push);
		}	
		
		sb.append(push);	
	}
	
	
	private double negateStoredValues()
	{
		//System.out.println("wee");
		double first_number = stored_values.peek();
		//System.out.println(second_number);
		double result = first_number * (-1);
		
		if(Math.abs(result) == Math.PI)
		{
			pi = true;
			if(result < 0)
				sb_input_history.append("-" + Controller.PI);
			else
				sb_input_history.append(Controller.PI);
		}	
		else
			pi = false;
		

		return result;
	}

	private void negateStoredWithHistory()
	{
		double history = Double.parseDouble(sb.toString());
		double result = history * (-1);
		//Reset sb for update
		sb.delete(0, sb.length());
		sb_input_history.delete(0, sb_input_history.length());
		
		//If result is int, append the casted value 
		if(isInt(result, (int) result))
		{
			sb.append("" + (int) result);
			sb_input_history.append("" + (int) result);
		}
		//Otherwise append the double value
		else
		{
			sb.append("" + result);
			sb_input_history.append("" +  result);
		}
	}
	
	
	/**
	 * Replaces missing operand with 0 for expression operation
	 * 
	 */
	private void zeroCheckExpr()
	{
		//If no values in system
		if(stored_values.empty())
		{
			if(expressionsInFix.size() < 2)
			{
				button_history.push("" +  0);
				stored_values.push((double) 0);
				System.out.println("One expr");
			}
			
			
		}
		else if(opExpression)
		{
			expressionsInFix.push("" + stored_values.peek());
		}
		
	}
	
	/**
	 * Checks whether an inputed string is an operation
	 * or not
	 * @param input - the String to be checked
	 * @return true if input is an operation. False otherwise
	 */
	private boolean isOp(String input)
	{
		//If input was pi
		//not an operation
		if(pi || input.equals(Controller.EXPRESSION))
			return false;
		
		try
		{
			Double.parseDouble(input);
		}
		catch (NumberFormatException e)
		{
			return true;
		}
		
		return false;
	}
	
	/**
	 * Checks whether parenthesis are required after an operation
	 * (Refer to section 6.3 in Design Document for more details)
	 * @param last_entry - An operation string or number string
	 * @param operator - The operator of the recent operation
	 * @return true if paranthesis are required. False otherwise
	 */
	private boolean checkBrackets(String last_entry, String operator)
	{
		if(precedence.empty() || !isOp(last_entry))
		{
			return false;
		}
		//If last op was trig
		//No brackets needed
		if(precedence.peek().equals(Controller.COS) || precedence.peek().equals(Controller.SIN)
				|| precedence.peek().equals(operator))
		{
			String prev = precedence.pop();
			
			//If the two last operands are the same,
			//no need for brackets UNLESS it is DIV
			// via stored_values
			if(operator.equals(Controller.DIV) && prev.equals(Controller.DIV) &&  from_memory)
			{
				return true;
			}
			
		}
		
		//If Fact
		//Brackets needed
		else if(operator.equals(Controller.FACT))
		{
				precedence.pop();
				return true;
		}	
			
		//If both operands are high precedence
		//additional checks needed
		else if(high_precedence.contains(operator))
		{
			//If multiplication was first,
			//brackets needed
			String prev = precedence.pop();
			if((operator.equals(Controller.DIV) && prev.equals(Controller.MULT)) || 
					lowest_precedence.contains(prev) )
			{
				return true;
			}		
		}		
		
		return false;
	}
		
	private void formNewEntry(String first, String second, String operator)
	{
		//If the last element needs brackets
		if(checkBrackets(second, operator))	
		{
			System.out.println("second is true");
			//And so does the one before
			//Put them
			if(checkBrackets(first, operator))
				sb_completed_operations.append("(" + first +  ") " + operator + " " + "(" + second + ")");
				//Otherwise just on the last element
				else
				{
						sb_completed_operations.append(first +  " " + operator + " " + "(" + second + ")");
				}
						
		}
		//If only the one before needs brackets
		else if(checkBrackets(first, operator))
		{
			sb_completed_operations.append("(" + first +  ") " + operator + " " + second);
		}
		//No brackets at all
		else
		{
			sb_completed_operations.append(first + " " + operator + " " /*+ sb_input_history.toString() + " "*/ + second);
		}
	}
	
	/**
	 * Makes updates to running_history and button_history 
	 * after Case 2 of a Binary Operation (Section 5.1 in Design Document)
	 */
	private void updateHistDirect()
	{
		//Reset button-press string for next use
		sb_input_history.delete(0, sb_input_history.length());
		
		//Add updated history to the stack (First and only element)
		button_history.push(sb_completed_operations.toString());
		
		//If op involved an expression
		//replace last element with new expression op
		if(opExpression)
		{
			expressionsInFix.pop();
			expressionsInFix.push(button_history.peek());
		}
		
		// Reset completed operations string_builder for next entry
		sb_completed_operations.delete(0, sb_completed_operations.length());
		
		if(!running_history.isEmpty())
		{
			running_history.remove(running_history.size() - 1);
		}
			
		
		running_history.add(button_history.peek());
		
	}
	
	/**
	 * Makes updates to running_history and button_history 
	 * after Case 1 of a Binary Operation(Section 5.1 in Design Document)
	 */
	private void updateHistMem()
	{
		
		// Add updated history to the stack
		button_history.push(sb_completed_operations.toString());
		
		//If op inolved an expression
		//replace last two in list with latest one
		if(opExpression)
		{
			for(int i = 0; i < 2; i++)
			{
				if(!(expressionsInFix.isEmpty()))
				{
					expressionsInFix.pop();
				}
				
			}
			
			expressionsInFix.push(button_history.peek());
		}
		System.out.println(expressionsInFix.toString());
		// Reset completed operations string_builder for next use.
		// This prevents duplication of previous entries
		sb_completed_operations.delete(0, sb_completed_operations.length());
		
		//remove last two elements in running_history
		for(int i = 0; i < 2 && i > -1; i++)
		{
			if(!(running_history.isEmpty()))
			{
				running_history.remove(running_history.size() - 1);
			}
			
		}
		//replace them with updated computation
		running_history.add(button_history.peek());
	}
	
	/**
	 * Checks if there is a discrepancy between an integer value and 
	 * double value
	 * @param double_val - the double-precision floating point representaion of the value
	 * @param int_val - the integer representation  of the value
	 * @return - true if there is no discrepancy. False otherwise
	 */
	private boolean isInt(double double_val, int int_val)
	{
		if(double_val - int_val < Double.MIN_VALUE)
			return true;
		return false;
	}
	
	
	/**
	 * Checks if either of the last two elements in history are
	 * expressions are not
	 * @return - true if at least one of the elements is an expression. False otherwise.
	 */
	private boolean isExpression()
	{
		if(button_history.empty() || expressionsInFix.isEmpty())
			return false;
		else if(expressionsInFix.contains(button_history.peek()) || 
				expressionsInFix.contains(running_history.get(running_history.size() - 2)))
		{
			return true;
		}
		return false;
		
	}
	
	
	public String undoValue()
	{
		if(!sb.toString().equals(""))
		{
			sb.deleteCharAt(sb.length()-1);
			sb_input_history.deleteCharAt(sb_input_history.length() - 1);
			return updateValue();
		}
		
		stored_values_undo.pop();
		
		if(stored_values_undo.empty())
		{
			stored_values.pop();
			System.out.println(stored_values);
			return "" + 0;
		}
			
		
		stored_values =  stored_values_undo.peek();
		double result = stored_values.peek();
		
		if(isInt(result, (int) result))
		{
			return (int) result + "";
		}
		
		return  result + "";
	}
	 
	public String undoHistory()
	{
		if(sb.toString().equals(""))
		{
			if(running_history_undo.empty() && button_history_undo.empty())
			{
				running_history.remove(0);
				button_history.pop();
				return "Start New Calculation";
			}
				
			
			running_history = running_history_undo.pop();
			button_history = button_history_undo.pop();	
		}
		
		return printHistory();
	}
}

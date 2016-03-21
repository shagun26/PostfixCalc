
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class ModelTest {

	private Model m;

	// Initiating a tester Model object
	@Before
	public void setUp() throws Exception {
		m = new Model();
	}

	// Testing summation with 5 cases.
	@Test
	public void testSum() {
		// Case 1: Testing addition from stored values in the stack.
		m.addToEntry("1");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("2");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.sum(),"3");
		m.reset();
		// Case 2: Stored values and number on screen
		m.addToEntry("1");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("2");
		assertEquals(m.sum(),"3");
		m.reset();
		// Case 3: Addition with expression and stored numbers in stack.
		m.expressionHist();
		m.addToEntry("2");
		m.enterValue();
		m.enterHistory();
		
		assertEquals(m.sum(),"x + 2");
		m.reset();
		// Case 4: Addition with expression and number on screen.
		m.expressionHist();
		m.addToEntry("2");
		assertEquals(m.sum(),"x + 2");
		// Case 5: Testing addition without enterHistory() function
		// The purpose of this test is to make sure all If statements  are tested.
		m.reset();
		m.addToEntry("4");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		m.addToEntry("4");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		assertEquals(m.sum(),"8");
		
	}


	// Testing subtraction with 5 cases.
	@Test
	public void testSubtract() {
		// Case 1: Testing subtraction from stored values in the stack.
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				m.addToEntry("1");
				m.enterValue();
				m.enterHistory();
				assertEquals(m.subtract(),"1");
				m.reset();
				// Case 2: Stored values and number on screen
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				m.addToEntry("1");
				assertEquals(m.subtract(),"1");
				m.reset();
				// Case 3: Subtraction with expression and stored numbers in stack.
				m.expressionHist();
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				assertEquals(m.subtract(),"x - 2");
				m.reset();
				// Case 4: Subtraction with expression and number on screen.
				m.expressionHist();
				m.addToEntry("2");
				assertEquals(m.subtract(),"x - 2");
				// Case 5: Testing subtraction without enterHistory() function
				// The purpose of this test is to make sure all If statements  are tested.
				m.reset();
				m.addToEntry("4");
				m.enterValue();
				m.sb.delete(0, m.sb.length());
				m.addToEntry("4");
				m.enterValue();
				m.sb.delete(0, m.sb.length());
				assertEquals(m.subtract(),"0");
	}
	
	// Testing Division with 5 cases.
	@Test
	public void testDivision() {

		// Case 1: Testing division from stored values in the stack.
		m.addToEntry("2");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("2");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.divide(),"1");
		m.reset();
		// Case 2: stored values and number on screen
		m.addToEntry("2");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("2");
		assertEquals(m.divide(),"1");
		m.reset();
		// Case 3: Division with expression and stored numbers in stack.
		m.expressionHist();
		m.addToEntry("2");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.divide(),"x / 2");
		m.reset();
		// Case 4: Division with expression and number on screen.
		m.expressionHist();
		m.addToEntry("2");
		assertEquals(m.divide(),"x / 2");
		// Case 5: Testing Division without enterHistory() function
		// The purpose of this test is to make sure all If statements  are tested.
		m.reset();
		m.addToEntry("4");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		m.addToEntry("4");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		assertEquals(m.divide(),"1");

	}
	
	// Testing Multiplication with 5 cases.
	@Test
	public void testMultiplication() {

		// Case 1: Testing multiplication from stored values in the stack.
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				assertEquals(m.multiply(),"4");
				m.reset();
				// Case 2: stored values and number on screen
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				m.addToEntry("2");
				assertEquals(m.multiply(),"4");
				m.reset();
				// Case 3: Multiplication with expression and stored numbers in stack.
				m.expressionHist();
				m.addToEntry("2");
				m.enterValue();
				m.enterHistory();
				assertEquals(m.multiply(),"x * 2");
				m.reset();
				// Case 4: Multiplication with expression and number on screen.
				m.expressionHist();
				m.addToEntry("2");
				assertEquals(m.multiply(),"x * 2");
				// Case 5: Testing multiplication without enterHistory() function
				// The purpose of this test is to make sure all If statements  are tested.
				m.reset();
				m.addToEntry("4");
				m.enterValue();
				m.sb.delete(0, m.sb.length());
				m.addToEntry("4");
				m.enterValue();
				m.sb.delete(0, m.sb.length());
				assertEquals(m.multiply(),"16");
	}
	
	// Testing Negation with 3 cases.
		@Test
		public void testNegate() {
			
	        // Case 1: Testing negation from stored values in the stack.
			m.addToEntry("1");
			m.enterValue();
			m.enterHistory();
			assertEquals(m.negate(), "-1"); 
			
			// case 2: Testing real numbers.
			m.reset();
			m.addToEntry("1.5");
			m.enterValue();
			m.enterHistory();
			assertEquals(m.negate(), "-1.5"); 
		
			 // Case 3: Testing numbers on screen.
			m.reset();
			m.addToEntry("1");
			assertEquals(m.negate(), "-1"); 
			

		}

	// Testing factorial 
	@Test
	public void testFactorial() {
		
		
		// Case 1: Testing factorial from stored values in the stack.				
		m.addToEntry("3");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.factorial(), "6");
		
		// Case 2: Testing a number on screen. 
		m.reset();
		m.addToEntry("3");
		assertEquals(m.factorial(),"6" );
		
		// Case 3: Testing a number on screen, with non-empty stack of stored values.
		m.reset();
		m.addToEntry("3");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("3");
		assertEquals(m.factorial(),"6" );
		
		// Case 4: Real numbers factorial, entered but not in stored.
		m.reset();
		m.addToEntry("3.5");
		assertEquals(m.factorial(), "NOT DEFINED");
		
		// Case 5: Negative value, entered but not stored
		m.reset();
		m.addToEntry("-3");
		assertEquals(m.factorial(),"NOT DEFINED");
		
		// Case 6: Real numbers, entered and stored
		m.reset();
		m.addToEntry("3.5");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.factorial(),"NOT DEFINED");
				
		// Case 7: Negative value, stored in stack.
		m.reset();
		m.addToEntry("-3");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.factorial(),"NOT DEFINED");
		
		// case 8: Very large numbers, stored in stack.
		m.reset();
		m.addToEntry("720");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.factorial(),"NOT DEFINED");
		
		// Case 9: Very large numbers, on screen.
		m.reset();
		m.addToEntry("720");
		assertEquals(m.factorial(),"NOT DEFINED");
		// Case 10: Factorial of an expression
		m.reset();
		m.expressionHist();
		assertEquals(m.factorial(),"NOT DEFINED");
	
	}

	// Testing Sin function with 3 cases.
	@Test
	public void testSin() {

		// Case 1: Testing sin function from stored values on stack.
		m.addToEntry("0");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.sin(),"0");
		
		// Case 2: Number on screen.
		m.reset();
		m.addToEntry("0");
		assertEquals(m.sin(), "0");
		
		// Case 3: real numbers followed by sin.
		m.reset();
		m.addToEntry("1.5");
		assertEquals(m.sin(), "0.9974949866040544");
		
		
	}

	// Testing Cos function with 3 cases.
	@Test
	public void testCosFunction() {

		// Case 1: Testing cos function from stored values on stack.
				m.addToEntry("0");
				m.enterValue();
				m.enterHistory();
				assertEquals(m.cos(),"1");
				
				// Case 2: Number on screen.
				m.reset();
				m.addToEntry("0");
				assertEquals(m.cos(), "1");
				
				// Case 3: real numbers followed by cos.
				m.reset();
				m.addToEntry("1.5");
				assertEquals(m.cos(), "0.0707372016677029");
				
	}
	
	@Test
	public void testSinAndCosWithExpression() {
		
		// Case 1: Expression followed by sin.
		m.expressionHist();
		assertEquals(m.sin(),"SIN(x)");
		
		// Case 2: Expression followed by cos.
				m.reset();
				m.expressionHist();
				assertEquals(m.cos(),"COS(x)");
		
		
	}
	
	

	// Testing enterValue function
	@Test
	public void testEnterValue() {
		
		// Case 1: value on screen.

		String valueOnScreen = "1"; 

		m.sb.append("1");

		assertEquals(m.enterValue(), valueOnScreen); 
		
		// Case 2: no value on screen, and empty storedValues. 
		// we are testing if the calcualtor appends 0.
		m.reset();
		m.addToEntry("5");
		assertEquals(m.enterValue(), "5");
		
		// case 3: expression involved
		
	}

	// Testing enterValue function
	@Test
	public void testPrintHistoryWithElements() {
		// case 1: when there are elements in the history.
		m.sb.append("1");
		m.enterValue();
		m.enterHistory();

		m.sb.append("2");
		m.enterValue();
		m.enterHistory();

		m.sb.append("3");
		m.enterValue();
		m.enterHistory();

		String expectedResult = "1, 2, 3";

		assertEquals(m.printHistory(), expectedResult); // testing...

	}

	@Test
	public void testEmptyPrintHistory() {

		String expectedResult = "";

		assertEquals(m.printHistory(), expectedResult); // testing...

	}

	@Test
	public void testPi() {
		
    // case 1: Test Pi functionality.
	double pi =   Math.PI;
	assertEquals(m.valuePi(), pi + "");
	// case 2: testing history when it's empty.
	m.valuePi();
	String piSymbol = "π";
	assertEquals(m.historyPi(),  piSymbol);
	
	m.reset();
	// case 3: testing history when it's not empty by adding 1.
	String historyNotEmpty = "1, π";
	m.addToEntry("1");
	m.enterHistory();
	assertEquals(m.historyPi(),  historyNotEmpty);

	

	}
	@Test
	public void testExpressionVal() {
		// test if x appears on screen.

		String expectedResult = "x";
		assertEquals(m.expressionVal(), expectedResult); // testing...

	}
	
	@Test
	public void testUndoHistory() {
		
		// Case 1: Undo history from stored values.
		m.addToEntry("1");
		m.enterValue();
		m.enterHistory();
		assertEquals(m.undoHistory(),"Start new Calculation");
		
		// Case 2: Undo history with 2 numbers.
		m.reset();
		m.addToEntry("4");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("5");
		m.enterValue();
		m.enterHistory();
		
		assertEquals(m.undoHistory(),"4");
		
		// Case 3: Undo from screen.
		m.reset();
		m.addToEntry("4");		    
		assertEquals(m.undoHistory(),"");
		
		// Case 3: Undo from operations.
		m.reset();
		m.addToEntry("4");
		m.enterValue();
		m.enterHistory();
		m.addToEntry("5");
		m.enterValue();
		m.enterHistory();
		m.sum();
		assertEquals(m.undoHistory(),"4");
		
		
		
	}
	
	@Test
	public void testUndoHistoryWithExpressions() {
		// Case 4: Undo an expression.
		       
				m.expressionHist();
				m.enterHistory();
				m.expressionVal();
				assertEquals(m.undoHistory(), "x");
				
	}
	
	@Test
	public void testUndoValue() {
		
		m.addToEntry("5");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		m.addToEntry("2");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		
		assertEquals(m.undoValue(), "5"); 
		
		m.reset();
		
		m.addToEntry("5.1");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		m.addToEntry("2.1");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		
		assertEquals(m.undoValue(), "5.1"); 
		m.reset();
		

		m.addToEntry("5");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		
		assertEquals(m.undoValue(), "0"); 
		m.reset();
		
		m.addToEntry("5");
		m.enterValue();
		m.sb.delete(0, m.sb.length());
		m.sb.append("3");
		assertEquals(m.undoValue(), ""); 
	}
	
	@Test
	public void testy() {
		
		
		
		
	}
	
	
	@Test
	public void testFactHistory() {
		// Case 1: Expression involved, don't update history.
		m.expressionVal();
		assertEquals(m.factHistory("!"),"" ); 
		m.reset();
		// Case  : After adding a number < 750, check if it updates history.
		m.addToEntry("50");
		assertEquals(m.factHistory("!"),"50!=" ); 
		// Case  : check factorial history with numbers.
		m.reset();
		m.addToEntry("5");
		m.enterValue();
		m.enterHistory();
		
		m.factorial();
		
		
		assertEquals(m.factHistory("!"),"5! =" );
		
		
		// Case 2: 

	}
	@Test
	public void testTrigHistory() {
		
		// tests if the function trigHistory shows functions with correct brackets.
		
		
		assertEquals(m.trigHistory("(x"),"(x)=");

	}
	
	@Test
	public void testEnterHistory() {
		
		// case 1 : if error appears, don't update the history.
		m.expressionHist(); 
		m.factorial(); // factorial of an expression is not defined.
		assertEquals(m.enterHistory(), "x"); // testing...
		
		// case 2: if PI is pressed, update history with it.
		m.reset();
		m.valuePi();
		m.historyPi();
		assertEquals(m.enterHistory(),"π, ");
		
		// other cases tested through other related methods.


	}
	


}

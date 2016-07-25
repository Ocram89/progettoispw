package boundary;

import java.io.Serializable;
import java.util.Stack;

import javax.swing.JFrame;

public class StackFrame extends JFrame implements Serializable
{	
	private static final long serialVersionUID	= 1L;
	//Variabili di classe
	public static Stack <StackFrame> pilaCornici;
	
	public StackFrame()
	{
		super();
		pilaCornici =new Stack<StackFrame>();
	}
	
	public static void rimuoviDalTop(StackFrame c) 
	{
		while  (!StackFrame.pilaCornici.empty() && StackFrame.pilaCornici.peek().getClass().equals(c.getClass()))
		{
			//Cioè Finché non è vuota la pila, e Finché al top c'è la classe cercata, RIMUOVI (pop) al top quella classe! 
			StackFrame.pilaCornici.pop();
		}
	}
}

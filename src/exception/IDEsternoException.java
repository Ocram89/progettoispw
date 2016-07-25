package exception;

public class IDEsternoException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public IDEsternoException()
	{
	}
	
	public IDEsternoException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Prodotto non presente";
	}
}
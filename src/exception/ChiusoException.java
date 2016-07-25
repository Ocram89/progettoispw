package exception;

public class ChiusoException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ChiusoException()
	{
	}
	
	public ChiusoException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Bisogna aprire la gestione";
	}
}
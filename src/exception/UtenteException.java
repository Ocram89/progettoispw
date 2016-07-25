package exception;

public class UtenteException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public UtenteException()
	{
	}
	
	public UtenteException(String m)
	{
		super (m);
	}
	
	public String toString()
	{
		return "/nUtente non presente.";
	}
}

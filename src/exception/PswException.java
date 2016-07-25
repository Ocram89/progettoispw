package exception;

public class PswException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public PswException()
	{
	}
	
	public PswException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "Password errata";
	}
}

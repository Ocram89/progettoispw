package exception;

public class DimensioneMenuException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public DimensioneMenuException()
	{
	}
	
	public DimensioneMenuException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Il menu deve contenere almeno un elemento";
	}
}
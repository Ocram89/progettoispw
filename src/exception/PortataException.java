package exception;

public class PortataException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public PortataException()
	{
	}
	
	public PortataException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "Prodotto non presente";
	}
}

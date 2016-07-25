package exception;

public class GiacenzaInsufficienteException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public GiacenzaInsufficienteException()
	{
	}
	
	public GiacenzaInsufficienteException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Quantità  insufficente";
	}
}
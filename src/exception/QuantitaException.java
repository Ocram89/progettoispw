package exception;

public class QuantitaException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public QuantitaException()
	{
	}
	
	public QuantitaException(String m)
	{
		super (m);
	}
	
	public String toString()
	{
		return "Quantit� negativa non valida";
	}
}

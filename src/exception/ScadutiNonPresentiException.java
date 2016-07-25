package exception;


public class ScadutiNonPresentiException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ScadutiNonPresentiException()
	{
	}
	
	public ScadutiNonPresentiException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Scaduti Non presenti";
	}

}

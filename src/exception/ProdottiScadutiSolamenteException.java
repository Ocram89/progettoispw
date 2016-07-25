package exception;

public class ProdottiScadutiSolamenteException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ProdottiScadutiSolamenteException()
	{
	}
	
	public ProdottiScadutiSolamenteException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Presenza di Prodotti Scaduti";
	}

}

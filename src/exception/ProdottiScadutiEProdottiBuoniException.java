package exception;


public class ProdottiScadutiEProdottiBuoniException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ProdottiScadutiEProdottiBuoniException()
	{
	}
	
	public ProdottiScadutiEProdottiBuoniException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Presenza di Prodotti Scaduti e Buoni";
	}


}

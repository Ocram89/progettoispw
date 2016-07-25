package exception;

public class ProdottoScadutoException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ProdottoScadutoException()
	{
	}
	
	public ProdottoScadutoException(String m)
	{
		super(m);
	}

	public String toString()
	{
		return "Presenza di Prodotti Scaduti";
	}

}


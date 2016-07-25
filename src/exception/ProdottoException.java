package exception;

public class ProdottoException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ProdottoException()
	{
	}
	
	public ProdottoException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "Prodotto non presente";
	}
}

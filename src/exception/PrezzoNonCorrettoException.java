package exception;

public class PrezzoNonCorrettoException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public PrezzoNonCorrettoException()
	{
		
	}
	public PrezzoNonCorrettoException(String m)
	{
		super(m);
	}
	public String toString()
	{
		return "Prezzo non corretta!";
	}

}
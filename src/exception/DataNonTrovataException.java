package exception;


public class DataNonTrovataException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public DataNonTrovataException()
	{
		
	}
	public DataNonTrovataException(String m)
	{
		super(m);
	}
	public String toString()
	{
		return "Data non Trovata!";
	}

}
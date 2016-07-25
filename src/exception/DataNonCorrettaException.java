package exception;


public class DataNonCorrettaException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public DataNonCorrettaException()
	{
		
	}
	public DataNonCorrettaException(String m)
	{
		super(m);
	}
	public String toString()
	{
		return "Data non corretta!";
	}

}

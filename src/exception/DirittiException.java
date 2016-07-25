package exception;

public class DirittiException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public DirittiException()
	{
	}
	
	public DirittiException(String m)
	{
		super (m);
	}
	
	public String toString()
	{
		return "Operazione non ammessa per l/utente corrente";
	}
}

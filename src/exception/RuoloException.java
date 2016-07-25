package exception;

public class RuoloException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public RuoloException()
	{
	}
	
	public RuoloException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "/nRuolo non previsto";
	}
}

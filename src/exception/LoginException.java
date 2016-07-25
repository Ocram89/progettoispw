package exception;

public class LoginException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public LoginException()
	{
	}
	
	public LoginException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "/nLogin errata";
	}
}

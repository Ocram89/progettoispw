package exception;

public class MagazzinoException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public MagazzinoException()
	{
	}
	
	public MagazzinoException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "\nMagazzino vuoto";
	}
}

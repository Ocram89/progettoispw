package exception;

public class ClasseAlimentareException extends Exception
{
	private static final long	serialVersionUID	= 1L;

	public ClasseAlimentareException()
	{
	}
	
	public ClasseAlimentareException(String m)
	{
		super(m);
	}
	
	public String toString()
	{
		return "\nClasse alimentare non prevista dal menu";
	}
}

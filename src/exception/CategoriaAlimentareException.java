package exception;

public class CategoriaAlimentareException extends Exception
{
		private static final long	serialVersionUID	= 1L;

		public CategoriaAlimentareException()
		{
		}
		
		public CategoriaAlimentareException(String m)
		{
			super(m);
		}
		
		public String toString()
		{
			return "\nCategoria alimentare non prevista dal menu";
		}
}
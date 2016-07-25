package exception;

import java.io.Serializable;


public class GiacenzaNonNullaException extends Exception implements Serializable
{	
	protected static final long	serialVersionUID	= 1L;
	public GiacenzaNonNullaException() {}
	public GiacenzaNonNullaException(String m)
	{
		super(m);
	}
}

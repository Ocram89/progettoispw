package util;

import java.io.Serializable;

public class IDEsterno implements Serializable
{	
	protected static final long	serialVersionUID	= 1L;
	private String iDEsterno;
	
	public IDEsterno(String iDEsterno)
	{
		this.iDEsterno = iDEsterno;
	}
	
	public String toString()
	{
		return iDEsterno;
	}
}

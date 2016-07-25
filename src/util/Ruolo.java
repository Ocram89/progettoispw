package util;

import java.io.Serializable;

public class Ruolo implements Serializable
{	
	protected static final long	serialVersionUID	= 1L;
private String ruolo;
	
	public Ruolo(String ruolo)
	{
		this.ruolo = ruolo;
	}
	
	public String toString()
	{
		return ruolo;
	}
}

package entity;

import java.io.Serializable;

public class PrezzoAcquisto implements Serializable
{	
	protected static final long	serialVersionUID	= 1L;
	private double prezzoAcquisto;
	
	public PrezzoAcquisto(double pA)
	{
		this.prezzoAcquisto = pA;
	}
	
	public double getpA()
	{
		return prezzoAcquisto;
	}

}


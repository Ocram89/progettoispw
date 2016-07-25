package entity;

import java.io.Serializable;

public class PrezzoVendita implements Serializable
{	
	protected static final long	serialVersionUID	= 1L;
	private double prezzoVendita;
	
	public PrezzoVendita(double pV)
	{
		this.prezzoVendita = pV;
	}
	
	public double getpV()
	{
		return prezzoVendita;
	}
}
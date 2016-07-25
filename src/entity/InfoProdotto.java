package entity;

import java.io.Serializable;


//Classe usata nella Mappa Scadenze di ogni prodotto!
//Se "segnalati" è non nulla, allora si tratta di un lotto di scaduti.

public class InfoProdotto implements Serializable
{
	
	private static final long serialVersionUID = 1L;
	private int giacenza;
	private int segnalati;
	
	
	
	public InfoProdotto(int giacenza)
	{
		this.giacenza = giacenza;
	}
	public int getGiacenza()
	{
		return giacenza;
	}
	
	public int getSegnalati()
	{
		return segnalati;
	}
	
	public void addGiacenza(int g)
	{
		giacenza += g;
	}
	
	public void sottraiGiacenza(int g)
	{
		giacenza -= g;
	}
	public void nuovaGiacenza(int g)
	{
		giacenza = g;
	}
	
	public void addSegnalati(int s)
	{
		segnalati += s;
	}
	
	public void azzeraSegnalati()
	{
		segnalati = 0;
	}
	
	public void maxSegnalati()
	{
		segnalati = giacenza;
	}
	public void sottraiSegnalati(int s)
	{
		segnalati -= s;
	}
	
}
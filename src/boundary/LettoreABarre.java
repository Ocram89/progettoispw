package boundary;

//my -----------
import util.*;

public class LettoreABarre 
{
	public LettoreABarre()
	{
		
	}
	
	//Leggi Codice a barre per un Deposito o un Ritiro Scaduti
	/*public Decodifica readDepORit(String nomeCommerciale, String dimensione, Orologio dataScadenza)
	{
		Decodifica d = new Decodifica(nomeCommerciale, dimensione, dataScadenza);
		return d;
	}*/
	/*
	public String read(String nomeCommerciale, String dimensione String gg, String mm, String aaaa)
	{
		Decodifica d = new Decodifica(nomeCommerciale, dimensione);
		return d;
	}*/
	
	public String read(String codiceBarre)
	{
		return codiceBarre;
	}
	
	//Leggi Codice a Barre per una erogazione
	public Decodifica read(String nomeCommerciale, String dimensione)
	{
		Decodifica d = new Decodifica(nomeCommerciale, dimensione);
		return d;
	}
	
	
}

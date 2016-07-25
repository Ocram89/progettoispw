package util;

//my -----------
import exception.*;

public class Decodifica
{
	
	String nomeCommerciale;
	String dimensione;
	String dataScadenzaAm;
	String idEst;
	
	public Decodifica(String codifica)
	{
		decodifica(codifica);
	}
	
	public void decodifica(String cod)
	{
		String dataAm = cod.substring(0, 8);
		this.dataScadenzaAm = dataAm;
		
		String idEst = cod.substring(8, (cod.length()));	
		System.out.println("Lunghezza cod: "+ cod.length());
		this.idEst = idEst;
	}
	
	public String getId()
	{
		return idEst;
	}
	
	public String getData()
	{
		return dataScadenzaAm;
	}
	        
	
	
	/*public Decodifica(String nomeCommerciale, String dimensione, Orologio dataScadenza)
	{
		this.nomeCommerciale = nomeCommerciale;
		this.dimensione = dimensione;
		//this.dataScadenza = dataScadenza;		
	}*/
	
	public Decodifica(String nomeCommerciale, String dimensione)
	{
		this.nomeCommerciale = nomeCommerciale;
		this.dimensione = dimensione;	
	}
	
	public String getIDEsterno() throws IDEsternoException
	{
		String IDEsterno;
		if(nomeCommerciale.equals("")) throw new IDEsternoException("Inserire correttamente le stringhe atte a simulare il Codice A Barre");
		else if (dimensione.equals("")) IDEsterno = nomeCommerciale;
		else IDEsterno = nomeCommerciale.concat(dimensione);
		return IDEsterno;
	}
	
	/*Orologio getData()
	{
		return dataScadenza;
	}*/
}

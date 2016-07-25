package util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.Serializable;
//import java.lang.Thread;

public class Orologio implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private GregorianCalendar ora;
	
	public Orologio() 
	{
		ora = new GregorianCalendar();
	}
	
	/*public Orologio(boolean casuale)
	{
		if (casuale)
		{
			GeneraDataCasuale gdc = new GeneraDataCasuale();
			ora = gdc.genera();
		}
	}*/
	
	public Orologio(int gg, int mm, int aaaa) 
	{
		ora = new GregorianCalendar(aaaa,mm-1,gg);
	}
	
	public void aggiorna() 
	{ 
		ora = new GregorianCalendar(); 
	}
	
	public String calcolaDataEOra() //Formato es. 10/01/2012 - 21:10:35
	{
		this.aggiorna();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(ora.getTime());
		return dataEora;
	}
	public String dataCasualeStr()
	{
		GeneraDataCasuale gdc = new GeneraDataCasuale();
		ora = gdc.genera();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String dataEora = sdf.format(ora.getTime());
		return dataEora;
	}
	
	public Orologio dataCasuale()
	{
		GeneraDataCasuale gdc = new GeneraDataCasuale();
		ora = gdc.genera();
		return this;
	}
	
	public GregorianCalendar getData()
	{
		return ora;
	}
	
	public int calcolaDataFormatoAmericano() //Usato per inserire prodotti nella Mappa Scadenze
	{
		//GregorianCalendar dataAttual = new GregorianCalendar();
		this.aggiorna();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataAttualeStrg = sdf.format(ora.getTime());
		int dataAttuale = (Integer.parseInt(dataAttualeStrg));
		//System.out.println("Data Attuale: "+ dataAttuale);
		return dataAttuale;
	}
	
	public int FromOrologioToIntAmericano()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataAttualeStrg = sdf.format(ora.getTime());
		int dataAttuale = (Integer.parseInt(dataAttualeStrg));
		//System.out.println("Data Attuale: "+ dataAttuale);
		return dataAttuale;
	}
	
	public GregorianCalendar ricavaDataGregorian()
	{
		return ora;
	}
	
	
	
	/*public int get_sec()   
	{ 
		return ora.get(ora.SECOND); 
	}
	public int get_min()   
	{ 
		return ora.get(ora.MINUTE); 
	}
	public int get_hour()  
	{ 
		return ora.get(ora.HOUR_OF_DAY); 
	}
	public int get_day()   
	{ 
		return ora.get(ora.DATE); 
	}
	public int get_month() 
	{ 
		return ora.get(ora.MONTH)+1; 
	}
	public int get_year()  
	{ 
		return ora.get(ora.YEAR); 
	}
	
	public String data()   
	{
		aggiorna();
		String ore, min, sec, day, month;
		
		// Operatori ternari al fine di una miglior formattazione
		ore   = (get_hour()<10) ? "0"+get_hour() : ""+get_hour();
		min   = (get_min()<10)  ? "0"+get_min() : ""+get_min();
		sec   = (get_sec()<10)  ? "0"+get_sec() : ""+get_sec();
		day   = (get_day()<10)  ? "0"+get_day() : ""+get_day();
		month = (get_month()<10) ? "0"+get_month() : ""+get_month();
				
		return ore + ":" + min +":" + sec +", " + day + "/" + month + "/" + get_year();
	}*/
	
			
	/*public static void main(String[] args)
	{
		Orologio clock = new Orologio();
		
		for(int i=0; i<5; i++) 
		{
			System.out.println("Log di ADESSO! :  "+clock.calcolaDataEOra());
			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}*/
	
	public static void main(String[] args)
	{
		for(int i=0; i<100; i++)
		{
			Orologio o = new Orologio();
			o = o.dataCasuale();
			System.out.println("Ora casuale: "+o.FromOrologioToIntAmericano());
		}
	}
}

package util;

import java.util.GregorianCalendar;
//************************************************************************************************
// DateGenerator.java genera attraverso il metodo genera() un GregorianCalendar casuale, con date comprese
// fra il 1� Gennaio 2011 e il 31 Dicembre 2050. Ci� pu� tornar utile nel testing delle classi Portata (e figlie),
// implementando un controllo sulle date di scadenza.
//************************************************************************************************
public class GeneraDataCasuale 
{
	
	public GeneraDataCasuale() 
	{
		
	}
	
	public GregorianCalendar genera() 
	{
		int giorno, mese, anno;
		GregorianCalendar data;
		
		//Il giorno deve essere compreso tra 1 e 31
		giorno = (int)(Math.random()*31)+1;
		
		//Il mese deve essere compreso tra 1 e 12
		mese = (int)(Math.random()*12)+1;
		
		//L'anno � compreso tra il 2011 e il 2050
		int range = 2050-2011+1;
		anno = (int)(Math.random()*range)+2011;
		
		//Gli if seguenti correggono date assurde generate dal random
		if (mese == 4 || mese == 6 || mese == 9 || mese == 11 && giorno > 30) giorno = 30;
		if (mese == 2 && giorno > 28) giorno = 28;
		
		//Genera una data casuale
		data = new GregorianCalendar(anno, mese, giorno);
		
		return data;
	}
	
	public static void main(String[] args) 
	{
		GregorianCalendar data1, data2;
		GeneraDataCasuale dg = new GeneraDataCasuale();
		
		
		//Confronta 100 coppie di Date random
		for(int i = 0; i<100; i++) 
		{
			data1 = dg.genera();
			System.out.println("Tempo 1:\t" + data1.getTime());
			data2 = dg.genera();
			System.out.println("Tempo 2:\t" + data2.getTime());
			System.out.println();
		
		
			//Confronto che sarà importante per valutare se una Portata è già scaduta o no
			if(data1.getTimeInMillis() < data2.getTimeInMillis()) 
				System.out.println(data1.getTime()+" è più piccola di "+data2.getTime());
			else 
				System.out.println(data2.getTime()+" è più piccola di "+data1.getTime());
			System.out.println("-------------------------------------------");
		}
	}
}

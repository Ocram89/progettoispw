package util;

import java.util.Calendar;
// import java.util.GregorianCalendar;


public class Funzioni 
{
	
	public Funzioni()
	{
		
	}
	
	// MDM - Aggiunta funzione che restituisce la directory corrente
	public static String showDir() {
		String curDir = System.getProperty("user.dir");

		//System.out.println("\nIn questo momento ti trovi nella directory:");
		//System.out.println(" - " + curDir);
		return curDir;
	}
	  
	public static String parsingDataAmericana(Integer data)
	{
		String dataFormatoAmericano = data.toString();
		String anno = dataFormatoAmericano.substring(0, 4);
		String mese = dataFormatoAmericano.substring(4, 6);
		String giorno = dataFormatoAmericano.substring(6, 8);
		return giorno+"/"+mese+"/"+anno;
	}
	
	public static Orologio FromIntegerToOrologio(Integer data)
	{
		String dataFormatoAmericano = data.toString();
		String anno = dataFormatoAmericano.substring(0, 4);
		int aaaa = Integer.parseInt(anno);
		String mese = dataFormatoAmericano.substring(4, 6);
		int mm = Integer.parseInt(mese);
		String giorno = dataFormatoAmericano.substring(6, 8);
		int gg = Integer.parseInt(giorno);
		return new Orologio(gg, mm, aaaa);
	}
	
	//Restituisce intero maggiore o uguale a zero, se dataCasuale è più grande o uguale a dataAttuale
	public static int calcolaDiffGiorni(Orologio dataAttuale, Orologio dataCasuale)
	{
		int multiplo1 = dataAttuale.getData().get(Calendar.YEAR)-2000;
		int misuraDataAttuale = dataAttuale.getData().get(Calendar.DAY_OF_YEAR)+ 365*multiplo1;
		
		int multiplo2 = dataCasuale.getData().get(Calendar.YEAR)-2000;
		int misuraDataCasuale = dataCasuale.getData().get(Calendar.DAY_OF_YEAR)+365*multiplo2;
		
		int DifferenzaGiorni = misuraDataCasuale - misuraDataAttuale;
		return DifferenzaGiorni;
		/*GregorianCalendar g1 = new GregorianCalendar(2011, Calendar.DECEMBER, 20);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		System.out.println("Giorno dell'anno: "+g1.get(Calendar.DAY_OF_YEAR));
		int multiplo1 = g1.get(Calendar.YEAR)-2000;
		System.out.println(multiplo1);
		int misuraGiorno1 = g1.get(Calendar.DAY_OF_YEAR)+ 365*multiplo1;
		System.out.println(misuraGiorno1);
		
		String data1 = sdf.format(g1.getTime());
		System.out.println("Il giorno: "+data1+ " sono andato al bar");
		
		GregorianCalendar g2 = new GregorianCalendar();
		SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String data2 = sdf2.format(g2.getTime());
		int multiplo2 = g2.get(Calendar.YEAR)-2000;
		System.out.println(multiplo2);
		int misuraGiorno2 = g2.get(Calendar.DAY_OF_YEAR)+365*multiplo2;
		System.out.println(misuraGiorno2);
		
		int differenzaGiorni = misuraGiorno2 - misuraGiorno1;
		System.out.println("Oggi è: "+data2+ " e sono passati "+differenzaGiorni+" giorni");*/
		
		/*System.out.println(g.toString());
		String oggi = (new Integer(g.get(Calendar.DAY_OF_MONTH)).toString())+ "/"+(new Integer(g.get(Calendar.MONTH)+1).toString())+ "/"+(new Integer(g.get(Calendar.YEAR)).toString());
		int ora = g.get(Calendar.HOUR);
		int minuti = g.get(Calendar.MINUTE);
		System.out.println("Oggi è: "+ oggi+ " e sono le "+ ora+ ":"+minuti);*/
	}
}

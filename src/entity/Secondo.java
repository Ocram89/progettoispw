package entity;

import javax.swing.JOptionPane;

// my -----------
import util.*;
import exception.*;

public abstract class Secondo extends Portata
{
	private static final long serialVersionUID = 1L;
	protected static int tSecondiErogati=0;
	protected static int tSecondiRichiesti=0;
	protected static int tSecondiScaduti=0;
	protected static int tSecondiDepositati=0;
	
	public Secondo(IDEsterno t) 
	{
		super(t);
		
	}
	
	public Secondo() //Usato per creare un semplice oggetto che calcola indici
	{
		super();
	}

	public void eroga(int quantita)
	{
		tSecondiRichiesti += quantita;		
		tSecondiErogati += quantita;
		super.eroga(quantita); 
	}
	
	public void erogaCmqOAggiornaRich(int quantita, int daErogare, int risp) //Attenzione, se risp è no, daErogare == 0!
	{	
		if (risp==JOptionPane.YES_OPTION)
		{
			//In questo caso le bevande 
			tSecondiRichiesti += quantita;		
			tSecondiErogati += daErogare;    
		}
		else //se risp è no!
		{
			tSecondiRichiesti += quantita;
		}
		super.erogaCmqOAggiornaRich(quantita, daErogare, risp); 
	}
	
	public void deposita(int quantita) // throws QuantitaException
	{
	    //Sostituire parametri con istanza di Movimento
		tSecondiDepositati+=quantita;
		super.deposita(quantita);      //Chiama il metodo della classe Madre "Portata"
	}
	
	public void ritiraScaduti(int quantita) throws GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{		
		tSecondiScaduti+=quantita;  //Errore Corretto!! "+="
		super.ritiraScaduti(quantita, null);
	}	
	
	//DA CANC
	public double successo() throws ArithmeticException
	{
		System.out.println("Attenzione Successo in Secondo");
		return -1;
	}
	//DA CANC
	public double gradimento() throws ArithmeticException
	{
		System.out.println("Attenzione Gradimento in Secondo");
		return -1;
	}
	//DA CANC
	public double deperibilita() throws ArithmeticException
	{
		System.out.println("Attenzione Deperibilità in Secondo");
		return -1;
	}	
	
	
	//**** METODO B
		public double successo(IDEsterno c) throws ArithmeticException
		{
			if (c.toString().equals("Secondo")) 
			{
				System.out.println("E' un Secondo!");
				if (tSecondiRichiesti > 0) return (double) tSecondiErogati/(double) tSecondiRichiesti;
		    	throw new ArithmeticException("Al momento nessun Secondo risulta ancora richiesto. Impossibile calcolarne indice di successo!");
			}
			else
			{
				System.out.println("Chiamo successo in Portata!");
				return super.successo(c);	
			}	
		}
		
		public double gradimento(IDEsterno c) throws ArithmeticException
		{
			if (c.toString().equals("Secondo"))
			{
				if (tPortateErogate > 0) return (double) tSecondiErogati/(double) tPortateErogate;
		        throw new ArithmeticException ("Al momento nessunn Secondo risulta ancora erogato. Impossibile calcolare indice di gradimento di "+ this.getClass().getName());
			}
			else 
			{
				System.out.println("Chiamo gradimento in Portata!");
				return super.gradimento(c) ;
			}
		}
		
		
		public double deperibilita(IDEsterno c) throws ArithmeticException
		{
			if (c.toString().equals("Secondo")) 
			{
				if (tSecondiDepositati > 0) return (double) tSecondiScaduti/(double) tSecondiDepositati;
		        throw new ArithmeticException ("Al momento nessun Secondo risulta ancora depositato. Impossibile calcolarne indice di deperibilit�!");
			}
				
			else 
			{
				System.out.println("Chiamo deperibilita in Portata!");
				return super.deperibilita(c) ;
			}
		}
	
	public static void azzeraVarStatiche()
	{
		tSecondiErogati=0;
		tSecondiRichiesti=0;
		tSecondiScaduti=0;
		tSecondiDepositati=0;
	}
	
	
	public static String getVarStatiche()
	{
			String s = "\nClasse Secondo:\n"+
		"tSecondiDepositati: " +tSecondiDepositati+"\n"+
		"tSecondiErogati: " +tSecondiErogati+"\n"+
		"tSecondiRichiesti: " +tSecondiRichiesti+"\n"+
		"tSecondiScaduti: " +tSecondiScaduti+"\n";
			return s;
	}	
}
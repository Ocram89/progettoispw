package entity;

//import java.io.Serializable;
import javax.swing.JOptionPane;

// my -----------
import util.*;
import exception.*;

public abstract class Primo extends Portata // implements Serializable
{	
	protected static int tPrimiErogati=0;
	protected static int tPrimiRichiesti=0;
	protected static int tPrimiScaduti=0;
	protected static int tPrimiDepositati=0;
	protected static final long	serialVersionUID	= 1L;
	
	public Primo(IDEsterno t)
	{
		super(t);
	}
	
	public Primo() //Usato per creare un semplice oggetto che calcola indici
	{
		super();
	}
	
	public void eroga(int quantita)
	{
		tPrimiRichiesti += quantita;		
		tPrimiErogati += quantita;
		super.eroga(quantita); 
	}

	public void erogaCmqOAggiornaRich(int quantita, int daErogare, int risp) //Attenzione, se risp è no, daErogare == 0!
	{	
		if (risp==JOptionPane.YES_OPTION)
		{
			//In questo caso le bevande 
			tPrimiRichiesti += quantita;		
			tPrimiErogati += daErogare;    
		}
		else //se risp è no!
		{
			tPrimiRichiesti += quantita;
		}
		super.erogaCmqOAggiornaRich(quantita, daErogare, risp); 
	}
		
	public void deposita(int quantita) // throws QuantitaException
	{
	    //Sostituire parametri con istanza di Movimento
		tPrimiDepositati+=quantita;
		super.deposita(quantita);      //Chiama il metodo della classe Madre "Portata"
	}
	
	public void ritiraScaduti(int quantita) throws GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{		
		tPrimiScaduti+=quantita;  //Errore Corretto!! "+="
		super.ritiraScaduti(quantita, null);
	}		
	
	public double successo() throws ArithmeticException
	{
		System.out.println("Successo in Primo: errore software; la app. non sarebbe dovuto entrare in questo metodo");
		return -1;
	}
	
	public double gradimento() throws ArithmeticException
	{
		System.out.println("Gradimento in Primo: errore software; la app. non sarebbe dovuto entrare in questo metodo");
		return -1;
	}
	
	
	public double deperibilita() throws ArithmeticException
	{
		System.out.println("Deperibilità in Primo: errore software; la app. non sarebbe dovuto entrare in questo metodo");
		return -1;
	}	
		

	public static void azzeraVarStatiche()
	{
		tPrimiErogati=0;
		tPrimiRichiesti=0;
		tPrimiScaduti=0;
		tPrimiDepositati=0;
	}
	
	
	public static String getVarStatiche()
	{
			String s = "\nClasse Primo:\n"+
		"tPrimiDepositati: " +tPrimiDepositati+"\n"+
		"tPrimiErogati: " +tPrimiErogati+"\n"+
		"tPrimiRichiesti: " +tPrimiRichiesti+"\n"+
		"tPrimiScaduti: " +tPrimiScaduti+"\n";
			return s;
	}
	
	
	
	/*public double preferenza() throws ArithmeticException
	{
		if (tRichiesti > 0) return (double) nRichiesti/(double) tRichiesti;
        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora erogato/a. Impossibile calcolarne indice di gradimento!");
	}*/

	/*public double deperibilitaRelativa() throws ArithmeticException
	{
		if (tScaduti > 0) return (double) nScaduti/(double) tScaduti;
        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora rititato perch� scaduto. Impossibile calcolarne indice di deperibilit�!");
	}*/
		
	
	//**** METODO B
	public double successo(IDEsterno c) throws ArithmeticException
	{
		if (c.toString().equals("Primo")) 
		{
			System.out.println("E' un Primo!");
			if (tPrimiRichiesti > 0) return (double) tPrimiErogati/(double) tPrimiRichiesti;
	    	throw new ArithmeticException("Al momento nessun Primo risulta ancora richiesto. Impossibile calcolarne indice di successo!");
		}
		else
		{
			System.out.println("Chiamo successo in Portata!");
			return super.successo(c);	
		}
		
	}
	
	public double gradimento(IDEsterno c) throws ArithmeticException
	{
		if (c.toString().equals("Primo"))
		{
			if (tPortateErogate > 0) return (double) tPrimiErogati/(double) tPortateErogate;
	        throw new ArithmeticException ("Al momento nessun Primo risulta ancora erogata. Impossibile calcolare indice di gradimento di "+ this.getClass().getName());
		}
		else 
		{
			System.out.println("Chiamo gradimento in Portata!");
			return super.gradimento(c) ;
		}
	}
	
	
	public double deperibilita(IDEsterno c) throws ArithmeticException
	{
		if (c.toString().equals("Primo")) 
		{
			if (tPrimiDepositati > 0) return (double) tPrimiScaduti/(double) tPrimiDepositati;
	        throw new ArithmeticException ("Al momento nessun Primo risulta ancora depositata. Impossibile calcolarne indice di deperibilit�!");
		}
			
		else 
		{
			System.out.println("Chiamo deperibilita in Portata!");
			return super.deperibilita(c) ;
		}
	}
	/*public double deperibilitaRelativa(IDEsterno c) throws ArithmeticException
	{
		if (c.toString().equals(this.iDEsterno.toString())) 
			return deperibilita();
		else 
			return super.deperibilita(c) ;
	}*/
}
package entity;

//import java.io.Serializable;
import javax.swing.JOptionPane;

// my -----------
import util.*;
import exception.*;

public abstract class Bevanda extends Portata //implements Serializable
{	
	protected static int tBevandeErogate=0;
	protected static int tBevandeRichieste=0;
	protected static int tBevandeScadute=0;
	protected static int tBevandeDepositate=0;
	protected static final long	serialVersionUID	= 1L;
	
	public Bevanda(IDEsterno t)
	{
		super(t);
	}
	
	public Bevanda() //Usato per creare un semplice oggetto che calcola indici
	{
		super();
	}
	
	
	public void eroga(int quantita) 
	{
		tBevandeRichieste += quantita;		
		tBevandeErogate += quantita;
		super.eroga(quantita); 
	}
		
	public void erogaCmqOAggiornaRich(int quantita, int daErogare, int risp) //Attenzione, se risp � no, daErogare == 0!
	{	
		if (risp==JOptionPane.YES_OPTION)
		{
			//In questo caso le bevande 
			tBevandeRichieste += quantita;		
			tBevandeErogate += daErogare;    
		}
		else //se risp � no!
		{
			tBevandeRichieste += quantita;
		}
		super.erogaCmqOAggiornaRich(quantita, daErogare, risp); 
	}

	public void deposita(int quantita) // throws QuantitaException
	{
		System.out.println("Bevanda: depositate");
	    //Sostituire parametri con istanza di Movimento
		tBevandeDepositate+=quantita;
		super.deposita(quantita);      //Chiama il metodo della classe Madre "Portata"
	}
		
	public void ritiraScaduti(int quantita, String dataAmericana) throws GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{		
		//if (quantita <= giacenza)
		//{
			tBevandeScadute+=quantita;  //Errore Corretto!! "+="
		//}
		super.ritiraScaduti(quantita, null);
	}																		
		
		
	public double successo() throws ArithmeticException
	{
		System.out.println("Successo in Bevanda! Non entrerà mai");
		return -1; 
	}
	
	
	public double gradimento() throws ArithmeticException
	{
		System.out.println("Attenzione Gradimento in Bevanda");
		return -1;
	}
	
	/*public double gradimentoClasse() throws ArithmeticException
	{
		if (tPortateErogate > 0) return (double) tBevandeErogate/(double) tPortateErogate;
        throw new ArithmeticException ("Al momento nessuna " + super.getClass().getName() + " risulta ancora erogata. Impossibile calcolare indice di gradimento di "+ this.getClass().getName());
	}*/
	
	public double deperibilita() throws ArithmeticException
	{
		System.out.println("Attenzione Deperibilità in Bevanda");
		return -1;
	}
	/*public double deperibilitaClasse() throws ArithmeticException
	{
		if (tBevandeDepositate > 0) return (double) tBevandeScadute/(double) tBevandeDepositate;
        throw new ArithmeticException ("Al momento nessuna " + getClass().getName() + " risulta ancora depositata. Impossibile calcolarne indice di deperibilit�!");
	}*/
	
	
	public static void azzeraVarStatiche()
	{
		tBevandeErogate=0;
		tBevandeRichieste=0;
		tBevandeScadute=0;
		tBevandeDepositate=0;
	}
	
	public static String getVarStatiche()
	{
			String s = "\nClasse Bevande:\n"+
		"tBevandeDepositate: " +tBevandeDepositate+"\n"+
		"tBevandeErogate: " +tBevandeErogate+"\n"+
		"tBevandeRichieste: " +tBevandeRichieste+"\n"+
		"tBevandeScadute: " +tBevandeScadute+"\n";
			return s;
	}
	
	/*public double deperibilitaRelativa() throws ArithmeticException
	{
		if (tPortateScadute > 0) return (double) tBevandeScadute/(double) tPortateScadute;
        throw new ArithmeticException ("Al momento nessuna " + super.getClass().getName() + " risulta ancora rititata perchè scaduta. Impossibile calcolarne indice di deperibilit�!");
	}*/
	

	//**** METODO B
			public double successo(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals("Bevanda")) 
				{
					System.out.println("E' una Bevanda!");
					System.out.println("Successo in Bevanda!");
					if (tBevandeRichieste > 0) return (double) tBevandeErogate/(double) tBevandeRichieste;
			    	throw new ArithmeticException("Al momento nessuna Bevanda risulta ancora richiesta. Impossibile calcolarne indice di successo!");
				}
				else
				{
					System.out.println("Chiamo successo in Portata!");
					return super.successo(c);	
				}
				
			}
			
			public double gradimento(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals("Bevanda"))
				{
					if (tPortateErogate > 0) return (double) tBevandeErogate/(double) tPortateErogate;
			        throw new ArithmeticException ("Al momento nessuna " + super.getClass().getName() + " risulta ancora erogata. Impossibile calcolare indice di gradimento di "+ this.getClass().getName());
				}
				else 
				{
					System.out.println("Chiamo gradimento in Portata!");
					return super.gradimento(c) ;
				}
			}
			
			
			public double deperibilita(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals("Bevanda")) 
				{
					if (tBevandeDepositate > 0) return (double) tBevandeScadute/(double) tBevandeDepositate;
			        throw new ArithmeticException ("Al momento nessuna Bevanda risulta ancora depositata. Impossibile calcolarne indice di deperibilità!");
				}
					
				else 
				{
					System.out.println("Chiamo deperibilita in Portata!");
					return super.deperibilita(c) ;
				}
			}
	
	/*public double deperibilitaRealativa(IDEsterno c) throws ArithmeticException
	{
		if (c.toString().equals(this.getClass().getName()) ) 
			return deperibilita();
		else 
			return super.deperibilita(c) ;
	}*/	
}
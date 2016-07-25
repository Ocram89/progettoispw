package entity;

import java.io.Serializable;
import java.util.TreeMap;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;


// my -----------
import util.*;
import exception.*;


//COMMENTI ----------
//MDM - Inserito metodo get MappaDataScadenza() -----
//MDM - Creato medodo getGiacenza() -----------------------
//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.

public abstract class Portata implements Serializable
{
	//Variabili di classe
	protected static final long		serialVersionUID	= 1L;
	protected static int 			tPortateErogate=0;
	protected static int 			tPortateRichieste=0;
	protected static int 			tPortateScadute=0;
	protected static int 			tPortateDepositate=0;
	
	//Variabili d'istanza
	protected int 					giacenza;
	protected int 					nErogati;
	protected int 					nRichiesti;
	protected int 					nScaduti;
	protected int 					nDepositati;

	protected int 					dataMemorizzata = 0;
	protected int					nGiacenzaScaduti=0;
	protected double				prezzoAcquisto;
	protected double				prezzoVendita;
	
	// Serve per inserire il nome del prodotto nei messaggi di eccezione
	protected IDEsterno 			iDEsterno;
	
	protected TreeMap <Integer, InfoProdotto> MappaDataScadenza = new TreeMap <Integer, InfoProdotto>();

	//Relazioni
	//protected LogMonitor 			logMovimenti;
	
	//Variabili IndiciGestione
	protected  double spesa;
	protected  double ricavo;
	protected  double guadagno;
	protected  double mancatoGuadagno;
	protected  double spesaEvitabile;
	protected  double valoreMagazzino;
		
	public Portata(IDEsterno t)
	{
		iDEsterno 	= t;
		giacenza 	= 0;
		nErogati 	= 0;
        nRichiesti	= 0;
        nDepositati	= 0;
 		nScaduti	= 0;
	}
	
	public Portata()
	{
		//Usato per creare un semplice oggetto che calcola indici    <== Tolto!
	}
	
	//-----------------
	//Prezzo Acquisto
	//-----------------
	public double getPrezzoAcquisto()
	{
		return prezzoAcquisto;
	}
	
	//-----------------
	//Prezzo Vendita
	//-----------------
	public double getPrezzoVendita()
	{
		return prezzoVendita;
	}
	
	//-----------------
	//Aggiorna Spesa
	//-----------------
	public void AggiornaSpesa()
	{
		spesa = prezzoAcquisto*nDepositati;
	}
	
	public double getSpesa()
	{
		return spesa;
	}
	
	
	//-----------------
	//Aggiorna Ricavo
	//-----------------
	public void AggiornaRicavo()
	{
		ricavo = prezzoVendita*nErogati;
	}
	
	public double getRicavo()
	{
		return ricavo;
	}
	
	
	//-----------------
	//Aggiorna Guadagno
	//-----------------
	public void AggiornaGuadagno()
	{
		// ricavo - spesa
		guadagno = (prezzoVendita*nErogati) - (prezzoAcquisto*nDepositati);
	}
	
	public double getGuadagno()
	{
		return guadagno;
	}
	
	
	//------------------------
	//Aggiorna Valore Magazzino
	//-------------------------
	public void AggiornaValoreMagazzino()
	{
		valoreMagazzino = (nDepositati-nErogati)*prezzoVendita;
	}
	
	public double getValoreMagazzino()
	{
		return valoreMagazzino;
	}	
	
	//-------------------------
	//Aggiorna Mancato Guadagno
	//-------------------------
	public void AggiornaMancatoGuadagno()
	{
		if((nDepositati-nRichiesti) > 0){
			mancatoGuadagno =  0.0;
		}
		else{
			mancatoGuadagno = (-1)*(nDepositati-nRichiesti)*prezzoVendita;
		}
	}
	
	public double getMancatoGuadagno()
	{
		return mancatoGuadagno;
	}	
	
	//-----------------
	//Aggiorna Spesa Evitabile
	//-----------------
	public void AggiornaSpesaEvitabile()
	{
		spesaEvitabile = prezzoAcquisto*nScaduti;
	}
	
	public double getSpesaEvitabile()
	{
		return spesaEvitabile;
	}
	
	
	
	public void deposita(int data, int quantita) // throws QuantitaException
	{
		System.out.println("Attenzione deposita(int data, int quantita)  in Portata");
		
	}
	public void deposita(int quantita) // throws QuantitaException
	{
	    //Sostituire parametri con istanza di Movimento
		giacenza+=quantita;
		nDepositati+=quantita;
		tPortateDepositate += quantita;
		
		//System.out.println("Valori di questo prodotto:\n" +
				//"Giacenza: " +giacenza + ", Erogate: "+ nErogati + ", Richieste: "+ nRichiesti +", Depositate: "+nDepositati+", Scaduti: "+nScaduti);
	
//		logMovimenti.aggiungi(String.valueOf(giacenza));
	

		// Nel controllore
		// if (quantita > 0 )
			
	/*		DateFormat dataScadF = DateFormat.getDateInstance();
			Date dataScadenza = new Date ();
			dataScadenza = dataScadF.parse(dataScadenzaText.getText());
     */
		/* else
			throw new QuantitaException(); */
	}
	/*public void depositaMapScadenza(Integer data, Integer quantita) throws IDEsternoException
	{
		
	}*/
	public String stampaMapScadenza()
	{
		return "Portata";
	}
	
	// Escludere nel controllore quantita non positiva
	public void eroga(int quantita) //throws GiacenzaInsufficienteException
	{
		nRichiesti += quantita;													
		if (quantita <= giacenza)  //tanto sicuramente entra qui, perch� l'eccez � gestita dal confine
		{
			tPortateRichieste += quantita;		
			tPortateErogate += quantita;
			giacenza = giacenza - quantita;
			nErogati += quantita;
//			logMovimenti.aggiungi(String.valueOf(giacenza));

		}
//		else
//		{
//			tPortateRichieste = tPortateRichieste+quantita-giacenza;	
//			throw new GiacenzaInsufficienteException("Giacenza insufficiente: per " + iDEsterno.toString() + "disponibile = " + giacenza);
//		}
		
		//System.out.println("Valori di questo prodotto:\n" +
			//	"Giacenza: " +giacenza + ", Erogate: "+ nErogati + ", Richieste: "+ nRichiesti +", Depositate: "+nDepositati+", Scaduti: "+nScaduti);
	}
	
	public void erogaCmqOAggiornaRich(int quantita, int daErogare, int risp) 
	{
		if (risp==JOptionPane.YES_OPTION)
		{				
				nRichiesti += quantita;													
				
				tPortateRichieste += quantita;		
				tPortateErogate += daErogare;
				nErogati += daErogare;
				giacenza = giacenza - daErogare;
				//		logMovimenti.aggiungi(String.valueOf(giacenza));
	
		}
		else
		{
			nRichiesti += quantita;															
			tPortateRichieste += quantita;	
			
		}
//		else   //Questo else era di if(quantita > giacenza)
//		{
//			tPortateRichieste = tPortateRichieste+quantita-giacenza;	
//			throw new GiacenzaInsufficienteException("Giacenza insufficiente: per " + iDEsterno.toString() + "disponibile = " + giacenza);
//		}
		/*System.out.println("Valori di questo prodotto:\n" +
				"Giacenza: " +giacenza + ", Erogate: "+ nErogati + ", Richieste: "+ nRichiesti +", Depositate: "+nDepositati+", Scaduti: "+nScaduti);
		 */
	
	}
		
	
	public void rimuovi() throws GiacenzaNonNullaException
	{
		if (giacenza==0)
		{
//			logMovimenti.rimuovi(iDEsterno.toString() +"\t"+oggi());
		}
		else
		{
			throw new GiacenzaNonNullaException("Giacenza non nulla: per " + iDEsterno.toString() + "disponibilie = " + giacenza);
		}
	}
	
	//La identificazione degli scaduti non è gestita;
	public void ritiraScaduti(int quantita, String dataAmericana) throws GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{
		/* Questo test nel controllore
		if (quantita < 0)
			throw new QuantitaException();*/
		// quantita <= giacenza
		//System.out.println("Quantità da togliere: "+quantita);
		tPortateScadute+=quantita;  //               ** ERRORE Corretto! "+="
		giacenza = giacenza - quantita;
		//System.out.println("Aggiornata Giacenza: "+giacenza);
		nScaduti +=quantita;
//		logMovimenti.aggiungi(String.valueOf(giacenza));
		
		/*System.out.println("Valori di questo prodotto:\n" +
				"Giacenza: " +giacenza + ", Erogate: "+ nErogati + ", Richieste: "+ nRichiesti +", Depositate: "+nDepositati+", Scaduti: "+nScaduti);
		 */
	}
		
	public double successo() throws ArithmeticException
	{
		System.out.println("Successo: Portata, Non entrerà mai");
		return -1;
	}
	
	/*public double successoClasse()throws ArithmeticException
	{
		System.out.println("SuccessoClasse: Portata");
		if (tPortateRichieste > 0) return (double) tPortateErogate/(double)tPortateRichieste;
    	throw new ArithmeticException("Al momento nessuna " + getClass().getName() + " risulta ancora richiesta. Impossibile calcolarne indice di successo!");
	}*/
	
	public double erogazioneMediaGiornaliera()
	{
		return (double) 5.5555;
	}
	
	public double gradimentoMedioGiornaliero(Double risultato) throws ArithmeticException
	{
		return 1.0;
	}
	
	public double gradimento() throws ArithmeticException
	{return 1.0;}
	
	public double gradimentoClasse() throws ArithmeticException
	{return 1.0;}
	
	public double deperibilita() throws ArithmeticException
	{
		System.out.println("Deperibilità: Portata, Non entrerà mai");
		return -1;
	}
	
	/*public double deperibilitaClasse() throws ArithmeticException
	{
		if (tPortateDepositate > 0) return (double) tPortateScadute/(double) tPortateDepositate;
        throw new ArithmeticException ("Al momento nessuna " + getClass().getName() + " risulta ancora depositata. Impossibile calcolarne indice di deperibilità!");
	}*/
	
	public double deperibilitaRelativa() throws ArithmeticException
	{return 1.0;}

	
	//ULTERIORI CONTROLLI
			public double successo(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals("Portata")) 
				{
					if (tPortateRichieste > 0) return (double) tPortateErogate/(double)tPortateRichieste;
			    	throw new ArithmeticException("Al momento nessuna Portata risulta ancora richiesta. Impossibile calcolarne indice di successo!");
				}	
				else 
					throw new ArithmeticException("Errore nel nome della portata");
			}
			
			public double gradimento(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals("Portata")) 
					return 1.0;
				else 
					throw new ArithmeticException("Errore nel nome della portata");
			}
			
			
			public double deperibilita(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals("Portata")) 
				{
					if (tPortateDepositate > 0) return (double) tPortateScadute/(double) tPortateDepositate;
					throw new ArithmeticException ("Al momento nessuna Portata risulta ancora depositata. Impossibile calcolarne indice di deperibilità!");
				}
				else 
					throw new ArithmeticException("Errore nel nome della portata");
			}
	
	public void erogaLB(int quantita, Utente u, String dataAm) throws GiacenzaInsufficienteException, LineUnavailableException
	{
		System.out.println("Attenzione erogaLB in Portata");
	}
/*	public void stampaLog()
	{ 
		logMovimenti.stampa();
	}
	
	public void elimina()
	{ 
		logMovimenti.elimina();
		logMovimenti = null;
	}

	public LogMonitor getLogMovimenti()
	{
		return logMovimenti;
	}
*/	
	public IDEsterno getIDEsterno()
	{
		return iDEsterno;
	}
	
	public String getGiornoCreazione()
	{
		String s = "Metodo della classe Portata\n";
		return s;
	}
	
	public String getOrarioCreazione()
	{
		String s = "Metodo della classe Portata\n";
		return s;
	}
		
	public static void azzeraVarStatiche()
	{
		tPortateErogate=0;
		tPortateRichieste=0;
		tPortateScadute=0;
		tPortateDepositate=0;
	}
	public static String getVarStatiche()
	{
			String s = "\nClasse Portata:\n"+
		"tPortateDepositate: " +tPortateDepositate+"\n"+
		"tPortateErogate: " +tPortateErogate+"\n"+
		"tPortateRichieste: " +tPortateRichieste+"\n"+
		"tPortateScadute: " +tPortateScadute+"\n";
			return s;
	
		
	}
	
	public int getErogate()
	{
		return nErogati;
	}
	
	public int getRichieste()
	{
		return nRichiesti;
	}
	
	public int getScadute()
	{
		return nScaduti;
	}
	
	public int getDepositate()
	{
		return nDepositati;
	}
	
	public int getGiacenzaScaduti()
	{
		return nGiacenzaScaduti;
	}
	
	
	// MDM - Inserito metodo getMappaDataScadenza()  -----
	public TreeMap <Integer, InfoProdotto> getMappaDataScadenza()
	{
		return MappaDataScadenza;
	}
	
	//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
	//public int giacenza()
	//{
	//	return giacenza;
	//}
		
	//MDM - Creato medodo getGiacenza() -----------------------
	public int getGiacenza()
	{
		return giacenza;
	}
}

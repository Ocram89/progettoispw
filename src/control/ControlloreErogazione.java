package control;

import java.io.Serializable;
//import java.text.SimpleDateFormat;
//import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;



import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;



//my -----------
import util.*;
import exception.*;
import boundary.*;
import entity.*;

//COMMENTI ------------------
//MDM - Eliminato metodo giacenza() e sostituito da getGiacenza().
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()

public class ControlloreErogazione extends Controllore implements Serializable {
	private static final long	serialVersionUID	= 1L;
	
	Orologio orologio = new Orologio();
	public Utente utenteCorrente;
	
	public ControlloreErogazione(Controllore daGestione) //per threads NUOVA VERSIONE
	{
		super();
		//Inserisco in questo oggetto i valori dell'oggetto Controllore passato come parametro
		super.lock = daGestione.lock;
		super.prodottoDisponibile = daGestione.prodottoDisponibile;
		super.prodottoEsistenza = daGestione.prodottoEsistenza;
	}
	
	public ControlloreErogazione(Utente utente)   //Chiamato dal Utente CASSIERE
	{
		//super("Dummy");   *****************    <== Se questo Ã¨ attivo, all'inizio non crea oggetto MappaProdotti
		
		//Allora se specifico "super();" oppure NO, succede che il costruttore principale della classe madre viene avviato!
		//Se invece specifico "super("stringa");" succede che viene avviato il costruttore "secondario" della classe madre!!
				
		new ConfineCassiereErogazione(this);
		this.utenteCorrente = utente;
	}
	
	public ControlloreErogazione(Utente utente, String AdminGestore) //chimato dall'Amministratore o dal Gestore, da ControlloreGestione
	{
		super(utente, "AdminGestore");
		this.utenteCorrente = utente;
		//System.out.println("Qui Entra L'Admin e il Gestore!");
	}
	

	//Erogazione Unitaria con Lettore A Barre
	public void erogaLB(IDEsterno k, int quantita, String dataAm) throws IDEsternoException, MagazzinoException, GiacenzaInsufficienteException, DirittiException, ProdottoException, LineUnavailableException 
	{
		if (k==null)
		{
			throw new IDEsternoException("L'identificatore non deve essere nullo\n");
		}
		
		if (mappaProdotti.get(k.toString())==null) throw new ProdottoException("Prodotto non presente in magazzino, controllare il Codice A Barre");
		//MDM - Utilizzo metodo getGiacenza() -----
		//if (mappaProdotti.get(k.toString()).giacenza==0)
		if (mappaProdotti.get(k.toString()).getGiacenza()==0)
		{
			//Questa chiamata serve solamente per aggiornare le richieste! I paramentri vogliono dire questo:
			//1 param.= quantita: Ã¨ la quantitÃ  di richieste da aggiornare!
			//2 param.= 0: daErogare. In questo caso non bisogna erogare nulla. Cmq sia qst param Ã¨ inutilizzato, ma per sicurezza lo lasciamo a 0.
			//3 param.= 1: Significa NO. Vuol dire che nel metodo entra nell'else, dove solamente il contatore "richieste" Ã¨ aggiornato!
			mappaProdotti.get(k.toString()).erogaCmqOAggiornaRich(quantita, 0, 1);
			throw new MagazzinoException("Non ci sono prodotti di "+ k.toString()+ " in magazzino!");
		}
		
		//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
		//if (quantita > mappaProdotti.get(k.toString()).giacenza()) 
		if (quantita > mappaProdotti.get(k.toString()).getGiacenza()) 
		{
			throw new GiacenzaInsufficienteException ("Il disponibile di " + k.toString() + " (" + giacenza(k) + ")" + " è insufficiente ("  + quantita +")\n");					
		}
		mappaProdotti.get(k.toString()).erogaLB(quantita, utenteCorrente, dataAm);
		//log.logErogazione(this.utenteCorrente, k.toString(), quantita, giacenza(k));
		/*boolean erogato = 
		if(erogato)	
		{
			
		}
		else System.out.println("Niente log");*/
	}
	
	public void eroga(IDEsterno k, int quantita) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ProdottiScadutiSolamenteException, ProdottiScadutiEProdottiBuoniException, MagazzinoException
	{	
		if (k==null)
		{
			throw new IDEsternoException("L'identificatore non deve essere nullo\n");
		}
			
		/*if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("Prodotti " + k.toString() + "  non sono presenti\n");
		}*/
		//MDM - Utilizzo metodo getGiacenza() -----
		//if (mappaProdotti.get(k.toString()).giacenza==0)
		if (mappaProdotti.get(k.toString()).getGiacenza()==0) //Non ci sono prodotti Erogabili
		{
			//Questa chiamata serve solamente per aggiornare le richieste! I paramentri vogliono dire questo:
			//1 param.= quantita: è la quantità  di richieste da aggiornare!
			//2 param.= 0: daErogare. In questo caso non bisogna erogare nulla. Cmq sia qst param è inutilizzato, ma per sicurezza lo lasciamo a 0.
			//3 param.= 1: Significa NO. Vuol dire che nel metodo entra nell'else, dove solamente il contatore "richieste" è aggiornato!
			mappaProdotti.get(k.toString()).erogaCmqOAggiornaRich(quantita, 0, 1);
			
			//Aggiornamento indici
			System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
			AggiornaIndice("Mancato Guadagno",k);
			System.out.println("Erogazione:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
			
			throw new MagazzinoException("Non ci sono prodotti di "+ k.toString()+ " in magazzino!");
			
		}
		if (quantita <=0)
		{
			throw new QuantitaException ("La quantità da erogare deve essere positiva\n");
		}
		//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
		//if (quantita > mappaProdotti.get(k.toString()).giacenza()) 
		if (quantita > mappaProdotti.get(k.toString()).getGiacenza()) // Quantità richiesta maggiore della giacenza
		{		
			throw new GiacenzaInsufficienteException ("La quantità disponibile di " + k.toString() + " (" + giacenza(k) + ")" + " è insufficiente ("  + quantita +")\n");					
		}
		//Qui mi devo accorgere se ci sono prodotti scaduti:
		//	- Se sono presenti Prodotti Scaduti => Avvisare con un alert: "Sono presenti Prodotti Scaduti!
		//	  Il Programma cercherà  prodotti buoni, e se presenti li erogherà ! Continuare ? Si - No.
		Vector<Boolean> situazioneProdotti = situazioneMappaScadenza(k);
		
		Boolean prodottiScaduti = situazioneProdotti.get(0); //Mi deve dire se ci sono o no prodotti scaduti
		Boolean prodottiBuoni = situazioneProdotti.get(1);	//...e se ci sono o no prodotti buoni
		
		/*if (prodottiBuoni) System.out.println("Ci sono prodotti Buoni\n");
		else System.out.println("Non ci sono prodotti Buoni\n");
		if (prodottiScaduti) System.out.println("Ci sono prodotti Scaduti\n");
		else System.out.println("Non ci sono prodotti Scaduti\n");*/
		
		
		
		if (prodottiScaduti&&!prodottiBuoni) //Se ci sono ProdottiScaduti e Non ci sono Prodotti Buoni
		{
			//Questa chiamata serve solamente per aggiornare le richieste! I paramentri vogliono dire questo:
			//1 param.= quantita: è la quantità  di richieste da aggiornare!
			//2 param.= 0: daErogare. In questo caso non bisogna erogare nulla. Cmq sia qst param è inutilizzato, ma per chiarezza lo lasciamo a 0.
			//3 param.= 1: Significa NO. Vuol dire che nel metodo entra nell'else, dove solamente il contatore "richieste" è aggiornato!
			mappaProdotti.get(k.toString()).erogaCmqOAggiornaRich(quantita, 0, 1);
			
			//Aggiornamento indici
			System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
			AggiornaIndice("Mancato Guadagno",k);
			System.out.println("Erogazione:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
						
			throw  new ProdottiScadutiSolamenteException ("Attenzione! Impossibile Erogare! Del Prodotto " + k.toString()+" ci sono solamente Prodotti Scaduti!");
			
		}
		if (prodottiScaduti&&prodottiBuoni) //Se ci sono sia ProdottiScaduti, sia ProdottiBuoni
		{
			throw new ProdottiScadutiEProdottiBuoniException ("Attenzione! Trovati Prodotti Scaduti del Prodotto " + k.toString()+"!");
		
		}
		
		//EROGAZIONE
		//mappaProdotti.get(k.toString()) restituisce un oggetto di una sottoclasse di Portata! <= Polimorfismo!
		//Grazie al polimorfismo, verrÃ  chiamata la giusta funzione eroga(...), cioè specifica per quell'oggetto. 
		//Cioè non eroga(..) di Portata, ma eroga(..) di Altro, o Acqua, ...ecc.
		//In pratica l'HashMap restituisce un oggetto di una classe che è sottoclasse di Portata!!
		
		mappaProdotti.get(k.toString()).eroga(quantita);
		log.logErogazione(this.utenteCorrente, k.toString(), quantita, giacenza(k));
		
		//Aggiornamento indici
		System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
		//AGGIORNAMENTO Spesa non necessario in fase di erogazine
		AggiornaIndice("Ricavo",k);
		AggiornaIndice("Guadagno",k);
		AggiornaIndice("Valore Magazzino",k);
		//AGGIORNAMENTO Mancato Guadagno non Necessario (se siamo qui è perchè tutte le richieste sono state soddisfatte)
		//AGGIORNAMENTO Spesa Evitabile non Necessario (se siamo qui è perchè non sono stati trovati prodotti scaduti)		
		System.out.println("Erogazione:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
		//messaggio di avvenuta erogazione
		JOptionPane.showMessageDialog(null, "Erogati "+ quantita + " di " + k.toString());
	}
	   
	
	public void erogaThread(IDEsterno k, int quantita, Utente thread) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ProdottiScadutiSolamenteException, ProdottiScadutiEProdottiBuoniException, MagazzinoException, InterruptedException
	{
		lock.lock();
		utenteCorrente = thread;
		String nomeUtenteCorrente = utenteCorrente.getLogin();
		String nomeProdotto= k.toString();
		//System.out.println("Cassiere "+utenteCorrente.getLogin()+": lock ");
		
		while (mappaProdotti.get(k.toString())==null)
		{
			System.out.println("Cassiere "+nomeUtenteCorrente+": Aspetto che il prodotto "+nomeProdotto+ " venga inserito");
			prodottoDisponibile.await();
		}
		
		//MDM - Utilizzo metodo getGiacenza() -----
		//while (mappaProdotti.get(k.toString()).giacenza==0)
		while (mappaProdotti.get(k.toString()).getGiacenza()==0)
		{
			System.out.println("Cassiere "+nomeUtenteCorrente+": Aspetto che il fornitore depositi delle unità  del prodotto "+k.toString());
			prodottoDisponibile.await();
			//throw new MagazzinoException("Non ci sono prodotti di "+ k.toString()+ " in magazzino!");
		}
		
		//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
		//while (quantita > mappaProdotti.get(k.toString()).giacenza())  
		while (quantita > mappaProdotti.get(k.toString()).getGiacenza()) 
		{
			System.out.println("Cassiere "+nomeUtenteCorrente+": Aspetto che il prodotto "+k.toString()+ " abbia giacenza sufficiente");
			prodottoDisponibile.await();	
		}
		//Qui mi devo accorgere se ci sono prodotti scaduti:
		//	- Se sono presenti Prodotti Scaduti => Avvisare con un alert: "Sono presenti Prodotti Scaduti!
		//	  Il Programma cercherà  prodotti buoni, e se presenti li erogherà ! Continuare ? Si - No.
		Vector<Boolean> situazioneProdotti = situazioneMappaScadenza(k);
		
		Boolean prodottiScaduti = situazioneProdotti.get(0); //Mi deve dire se ci sono o no prodotti scaduti
		Boolean prodottiBuoni = situazioneProdotti.get(1);	//...e se ci sono o no prodotti buoni
				
		while (prodottiScaduti&&!prodottiBuoni) //Se ci sono ProdottiScaduti e Non ci sono Prodotti Buoni
		{
			prodottoDisponibile.wait();
		}
		if (prodottiScaduti&&prodottiBuoni) //Se ci sono sia ProdottiScaduti, sia ProdottiBuoni
		{
			try
			{
				erogaIndividuaProdottiBuoniThread(k, quantita);
			}
			finally
			{
				System.out.println("Cassiere "+nomeUtenteCorrente+": unlock ");
				lock.unlock();
			}
		}
		else
		{		
			try
			{
				
				//Operazione di Erogazione
				mappaProdotti.get(k.toString()).eroga(quantita);
				log.logErogazione(this.utenteCorrente, k.toString(), quantita, giacenza(k));
				
				//Aggiornamento indici
				System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
				//AGGIORNAMENTO Spesa non necessario in fase di erogazine
				AggiornaIndice("Ricavo",k);
				AggiornaIndice("Guadagno",k);
				AggiornaIndice("Valore Magazzino",k);
				//AGGIORNAMENTO Mancato Guadagno non Necessario (se siamo qui è perchè tutte le richieste sono state soddisfatte)
				//AGGIORNAMENTO Spesa Evitabile non Necessario (se siamo qui è perchè non sono stati trovati prodotti scaduti)		
				
			}
			finally
			{
				System.out.println("Cassiere "+nomeUtenteCorrente+": unlock");
				lock.unlock();
			}
		}		
	}		
	
	//Chiamato dal Confine se ci sono sia ProdottiBuoni che ProdottiScaduti! Il suo compito Ã¨ individuare Prodotti BUONI!
	
	
	private void erogaIndividuaProdottiBuoniThread(IDEsterno k, int quantita) throws DirittiException, IDEsternoException, GiacenzaInsufficienteException, ProdottoException, InterruptedException
	{
			lock.lock();
			orologio.aggiorna();
			//int dataAttuale = calcolaDataAttuale();
			int dataAttuale = orologio.calcolaDataFormatoAmericano();
			//System.out.println("Data Formato Americano: "+ dataAttuale);
			
			TreeMap <Integer, InfoProdotto> MappaDataScadenzaTemp = new TreeMap <Integer, InfoProdotto>();
			// MDM - Portata.MappaDataScadenza - Inserito metodo get -----
			//MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).MappaDataScadenza;
			MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).getMappaDataScadenza();
			
			int giacenzaProdottiBuoni = 0;
			Set <Integer> ChiaviMappaScadenze1 = MappaDataScadenzaTemp.keySet();
			for (Integer n : ChiaviMappaScadenze1)
			{
				if(dataAttuale<=n)
				{
					giacenzaProdottiBuoni = giacenzaProdottiBuoni + MappaDataScadenzaTemp.get(n).getGiacenza();
				}
				
			}			
			
			//System.out.println("Giacenza ("+k.toString()+") Prodotti Buoni: "+giacenzaProdottiBuoni);
			
			if (quantita>giacenzaProdottiBuoni)
			{	//Il controllo torna al Confine
				System.out.println("Cassiere "+utenteCorrente.getLogin()+": Aspetto prodotti erogabili "+k.toString()+ " siano disponibili");
				prodottoDisponibile.await();
			}
			else
			{
				mappaProdotti.get(k.toString()).eroga(quantita);
				System.out.println("Cassiere "+utenteCorrente.getLogin()+": Erogati "+quantita+ " di "+k.toString()+" ==> Giacenza: "+giacenza(k));
				//log = new LogProdotti();
				log.logErogazione(this.utenteCorrente, k.toString(), quantita, giacenza(k));
				
				//Aggiornamento indici
				System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
				//AggiornaIndice("Spesa",k); // non necessario in fase di erogazine
				AggiornaIndice("Ricavo",k);
				AggiornaIndice("Guadagno",k);
				AggiornaIndice("Valore Magazzino",k);
				AggiornaIndice("Mancato Guadagno",k);
				AggiornaIndice("Spesa Evitabile",k);
				System.out.println("Erogazione:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
				
			}
			lock.unlock();		
	}
	
	
	
	private Vector<Boolean> situazioneMappaScadenza(IDEsterno k) throws IDEsternoException, MagazzinoException
	{			
		orologio.aggiorna();
		int dataAttuale = orologio.calcolaDataFormatoAmericano();
		//System.out.println("Data Formato Americano: "+ dataAttuale);
		
		Vector <Boolean> Risposta = new Vector<Boolean>();
		Boolean prodottiBuoni = new Boolean(false);
		Boolean prodottiScaduti = new Boolean(false);
		
		TreeMap <Integer, InfoProdotto> MappaDataScadenzaTemp = new TreeMap <Integer, InfoProdotto>();
		// MDM - Portata.MappaDataScadenza - Inserito metodo get -----
		//MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).MappaDataScadenza;
		MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).getMappaDataScadenza();
		if(MappaDataScadenzaTemp.isEmpty())
		{	//Da Gestire con Eccezioni? Forse non serve!
			//throw new MagazzinoException("Non ci sono prodotti di "+ k.toString()+ "in magazzino!");
			System.out.println("MappaScadenza vuota! Impossibile Erogare!");
		}
		else
		{
			//Calcolo del Numero di Prodotti Scaduti (da inserire in variabile "ritiroScaduti")
			Set <Integer> ChiaviMappaScadenze1 = MappaDataScadenzaTemp.keySet();
			for (Integer n : ChiaviMappaScadenze1)
			{
				if(dataAttuale>n)
				{
					prodottiScaduti = true;
				}
				else
				{
					prodottiBuoni = true;
				}	
			}				
		}
		Risposta.add(prodottiScaduti);
		Risposta.add(prodottiBuoni);
		return Risposta;
		
	}
	
	/*public Vector<Boolean> situazioneMappaScadenzaNoStatic(IDEsterno k) throws IDEsternoException, MagazzinoException 
	{
		return situazioneMappaScadenza(k);
	}*/
	

	public void erogaCmqOAggiornaRich(IDEsterno k, int quantita, int risp) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException
	{
		
		if (k==null)
		{
			throw new IDEsternoException("L'identificatore non deve essere nullo\n");
		}
		if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("Prodotti " + k.toString() + "  non sono presenti\n");
		}
		if (quantita <=0)
		{
			throw new QuantitaException ("La quantita da erogare deve essere positiva\n");
		}
//		if (quantita > mappaProdotti.get(k.toString()).giacenza()) {
//			throw new GiacenzaInsufficienteException ("Il disponibile di " + k.toString() + " (" + giacenza(k) + ")" + " Ã¨ insufficiente ("  + quantita +")\n");					
//		}
		int maxProdottiErogabili = 0;
							
		orologio.aggiorna();
		//int dataAttuale = calcolaDataAttuale();
		int dataAttuale = orologio.calcolaDataFormatoAmericano();
		
		int quantiProdottiBuoni = 0;
		TreeMap <Integer, InfoProdotto> MappaDataScadenzaTemp = new TreeMap <Integer, InfoProdotto>();
		// MDM - Portata.MappaDataScadenza - Inserito metodo get -----
		//MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).MappaDataScadenza;
		MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).getMappaDataScadenza();
		
		if(MappaDataScadenzaTemp.isEmpty())
		{	//Da Gestire con Eccezioni!
			System.out.println("MappaScadenza vuota! Impossibile Erogare!");
		}
		else
		{
			//Calcolo del Numero di Prodotti Erogabili (da inserire in variabile "ritiroScaduti")
			Set <Integer> ChiaviMappaScadenze1 = MappaDataScadenzaTemp.keySet();
			for (Integer n : ChiaviMappaScadenze1)
			{
				if(dataAttuale<=n) //Individua prodotti buoni
				{
					quantiProdottiBuoni = quantiProdottiBuoni + MappaDataScadenzaTemp.get(n).getGiacenza();
				}	
			}
			maxProdottiErogabili = quantiProdottiBuoni;
			System.out.println("Prodotti (buoni) Max che è possibile Erogare: "+quantiProdottiBuoni);
			
		}
										//quantità  richiesta===v
		mappaProdotti.get(k.toString()).erogaCmqOAggiornaRich(quantita, maxProdottiErogabili, risp);
		
		
		//Aggiornamento indici
		System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
		AggiornaIndice("Mancato Guadagno",k);
		if (risp==0){ //Se la risposta è Si, ovvero effettuo comunque erogazione aggiorno anche gli altri indici
			//AggiornaIndice("Spesa",k); // non necessario in fase di erogazine
			AggiornaIndice("Ricavo",k);
			AggiornaIndice("Guadagno",k);
			AggiornaIndice("Valore Magazzino",k);
			AggiornaIndice("Spesa Evitabile",k);
			//log e messaggio di avvenuta erogazione
			log.logErogazione(this.utenteCorrente, k.toString(), maxProdottiErogabili, giacenza(k));
			JOptionPane.showMessageDialog(null, "Erogati "+ maxProdottiErogabili + " di " + k.toString());
		}
		else
		{
			log.logErogazione(this.utenteCorrente, k.toString(), 0, giacenza(k));
			JOptionPane.showMessageDialog(null, "Erogazione non Effettuata");
		}
		System.out.println("Erogazione:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
	}
	
	public int giacenza(IDEsterno k) throws DirittiException, ProdottoException, IDEsternoException
	{
		if (k==null) 
		{
			throw new IDEsternoException("L/identificatore esterno non puo essere nullo\n");
			
		}
			if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("Prodotti " + k.toString() + "  non presenti");
		}
		else	
		{	
			//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
			//return mappaProdotti.get(k.toString()).giacenza();  
			return mappaProdotti.get(k.toString()).getGiacenza();
		}
	}

	//Chiamato dal Confine se ci sono sia ProdottiBuoni che ProdottiScaduti! Il suo compito Ã¨ individuare Prodotti BUONI!
	
	public void erogaIndividuaProdottiBuoni(IDEsterno k, int quantita) throws DirittiException, IDEsternoException, GiacenzaInsufficienteException, ProdottoException
	{
		//if (risp==JOptionPane.YES_OPTION)
		//{
			orologio.aggiorna();
			//int dataAttuale = calcolaDataAttuale();
			int dataAttuale = orologio.calcolaDataFormatoAmericano();
			//System.out.println("Data Formato Americano: "+ dataAttuale);
			
			TreeMap <Integer, InfoProdotto> MappaDataScadenzaTemp = new TreeMap <Integer, InfoProdotto>();
			// MDM - Portata.MappaDataScadenza - Inserito metodo get -----
			//MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).MappaDataScadenza;
			MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).getMappaDataScadenza();
			
			int giacenzaProdottiBuoni = 0;
			Set <Integer> ChiaviMappaScadenze1 = MappaDataScadenzaTemp.keySet();
			for (Integer n : ChiaviMappaScadenze1)
			{
				if(dataAttuale<=n)
				{
					giacenzaProdottiBuoni = giacenzaProdottiBuoni + MappaDataScadenzaTemp.get(n).getGiacenza();
				}
				
			}			
			
			//System.out.println("Giacenza ("+k.toString()+") Prodotti Buoni: "+giacenzaProdottiBuoni);
			
			if (quantita>giacenzaProdottiBuoni)
			{	//Il controllo torna al Confine
				throw new GiacenzaInsufficienteException ("Attenzione, Prodotti insufficienti! Sono stati richiesti "+quantita+" unità  di "+k.toString()+". Ne sono disponibili solamente (" + giacenzaProdottiBuoni + ")\n");
			}
			else
			{
				mappaProdotti.get(k.toString()).eroga(quantita);

				//Aggiornamento indici
				System.out.println("Erogazione:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
				//AggiornaIndice("Spesa",k); // non necessario in fase di erogazine
				AggiornaIndice("Ricavo",k);
				AggiornaIndice("Guadagno",k);
				AggiornaIndice("Valore Magazzino",k);
				AggiornaIndice("Mancato Guadagno",k);
				AggiornaIndice("Spesa Evitabile",k);
				System.out.println("Erogazione:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
				//messaggio di avvenuta erogazione
				if (quantita>0)
					JOptionPane.showMessageDialog(null, "Erogati "+ quantita + " di " + k.toString());
				else 
					JOptionPane.showMessageDialog(null, "Erogazione non Efettuata");
				
				log.logErogazione(this.utenteCorrente, k.toString(), quantita, giacenza(k));
			}		
		//}
	}
	
	
	//Aggiornamento INDICE
	public void AggiornaIndice (String nomeIndice, IDEsterno k) throws IDEsternoException
	{
		int xMatriceGerarchia=0;
		int yMatriceGerarchia = 0;
		int yMatriceGerarchiaClasse =0;
		double indice;
		double indiceValPrecedente = 0.0;
		double indiceValCorrente = 0.0;
		String superClasseProdottoCorrente = "";
		String nomeTipoProdotto ="";
		
		try { //ricavo classe e superclasse del prodotto
			nomeTipoProdotto = getMappaProdotti().get(k.toString()).getClass().getName();
			System.out.println("\nClasse: " + nomeTipoProdotto);

			//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
			//superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getSimpleName();
			superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getName();
			
			System.out.println("Categoria: " + superClasseProdottoCorrente);

			//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
			//if(superClasseProdottoCorrente!="Portata")//se = Portata, allora profondità gerarchia =2
			if(superClasseProdottoCorrente!="entity.Portata")//se = Portata, allora profondità gerarchia =2
			{
				yMatriceGerarchia = CostantiClassiAlimentari.indexCategoriaAlimentare(superClasseProdottoCorrente);
			}
			else
			{
				yMatriceGerarchia = CostantiClassiAlimentari.indexCategoriaAlimentare(nomeTipoProdotto);
			}
				
			yMatriceGerarchiaClasse =  CostantiClassiAlimentari.NUMCATEGORIEALIMENTARI + CostantiClassiAlimentari.indexClasseAlimentareFinale(nomeTipoProdotto);
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (CategoriaAlimentareException e) {
			e.printStackTrace();
		}
		catch (ClasseAlimentareException e) {
			e.printStackTrace();
		}
		
		if (nomeIndice =="Spesa"){
			indiceValPrecedente = mappaProdotti.get(k.toString()).getSpesa(); 
			//System.out.println("\nvPrc: " + indiceValPrecedente);
			mappaProdotti.get(k.toString()).AggiornaSpesa();
			indiceValCorrente = mappaProdotti.get(k.toString()).getSpesa();
			//System.out.println("\nvCorrente: " + indiceValCorrente);
		}
		else if (nomeIndice =="Ricavo"){
			indiceValPrecedente = mappaProdotti.get(k.toString()).getRicavo(); 
			mappaProdotti.get(k.toString()).AggiornaRicavo();
			indiceValCorrente = mappaProdotti.get(k.toString()).getRicavo();
		}
		else if (nomeIndice =="Guadagno"){
			indiceValPrecedente = mappaProdotti.get(k.toString()).getGuadagno(); 
			mappaProdotti.get(k.toString()).AggiornaGuadagno();
			indiceValCorrente = mappaProdotti.get(k.toString()).getGuadagno();
		}
		else if (nomeIndice =="Valore Magazzino"){
			indiceValPrecedente = mappaProdotti.get(k.toString()).getValoreMagazzino(); 
			mappaProdotti.get(k.toString()).AggiornaValoreMagazzino();
			indiceValCorrente = mappaProdotti.get(k.toString()).getValoreMagazzino();
		}
		else if (nomeIndice =="Mancato Guadagno"){
			indiceValPrecedente = mappaProdotti.get(k.toString()).getMancatoGuadagno(); 
			mappaProdotti.get(k.toString()).AggiornaMancatoGuadagno();
			indiceValCorrente = mappaProdotti.get(k.toString()).getMancatoGuadagno();
		}
		else if (nomeIndice =="Spesa Evitabile"){
			indiceValPrecedente = mappaProdotti.get(k.toString()).getSpesaEvitabile(); 
			mappaProdotti.get(k.toString()).AggiornaSpesaEvitabile();
			indiceValCorrente = mappaProdotti.get(k.toString()).getSpesaEvitabile();
		}
		
		//Aggiornamento indice Categoria
		xMatriceGerarchia = ConfineGestoreIndici.indexCatalogoIndici(nomeIndice); 
		indice = indiceValCorrente - indiceValPrecedente;
		//System.out.println("\nvInserito: " + indice);
		VariabiliGerarchia.aggiornaVGerarchia(xMatriceGerarchia, yMatriceGerarchia, indice);
		
		//Aggiornamento indice Classe
		//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
		//if(superClasseProdottoCorrente!="Portata")//se = Portata, allora profondità gerarchia =2
		if(superClasseProdottoCorrente!="entity.Portata")//se = Portata, allora profondità gerarchia =2
		{
			VariabiliGerarchia.aggiornaVGerarchia(xMatriceGerarchia, yMatriceGerarchiaClasse, indice);
		}
		log.logAggiornamentoIndici(this.utenteCorrente, k.toString(), nomeIndice, indiceValCorrente );
		System.out.println("Fornitura:  ***** " + nomeIndice + " Corrente: " + indiceValCorrente + " *****" );
	}
	
}

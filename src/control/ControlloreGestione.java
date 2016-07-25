package control;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeMap;

//import java.util.Vector;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

//my -----------
import util.*;
import exception.*;
import boundary.*;
import entity.*;

// COMMENTI -----
//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()


public class ControlloreGestione extends ControlloreDeposito implements Serializable {
	private static final long	serialVersionUID	= 1L;

		
	//ControlloreErogazione iC= new ControlloreErogazione(); 
	ControlloreErogazione iC;
	public Utente utenteCorrente;
	
	public ControlloreGestione() //PER Threads NUOVA VERSIONE
	{
		super();
		//this.utenteCorrente = utente;
		iC = new ControlloreErogazione(this); 
	}
	
	
	public ControlloreGestione(Utente utente, String admin) //Chiamato dall'Admin
	{
		super(utente, "admin");
		this.utenteCorrente = utente;
		iC = new ControlloreErogazione(utente, "admin"); 
		
	}
	
	public ControlloreGestione(Utente utente)
	{
		new ConfineGestore(this, false); //false Ë per non far apparire subito GestioneProdotti, altrimenti true.
		iC = new ControlloreErogazione(utente, "gestore"); 
		this.utenteCorrente = utente;
		/*ControlloreErogazione.utenteOspite = utente;
		ControlloreDeposito.utenteOspite = utente;
		ControlloreGestione.utenteOspite = utente;*/
	}
	
	//public void ritiraScaduti(IDEsterno k, int quantita) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException
	public void ritiraScaduti(IDEsterno k, int risp, int quantita, String dataAmericana) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{
		if (k==null)
		{
			throw new IDEsternoException("L'identificatore non deve essere nullo\n");
		}
		if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("Prodotti " + k.toString() + "  non sono presenti\n");
		}
		/*if (quantita <=0)
		{
			throw new QuantitaException ("La quantita da depositare deve essere positiva\n");
		}
		if (quantita > mappaProdotti.get(k.toString()).giacenza()) 
		{
			throw new GiacenzaInsufficienteException ("Disponibile (" + giacenza(k) + ") di " + k.toString() + " insufficiente ("  + quantita +")\n");					
		}*/
		
		int dataAttuale = calcolaDataAttuale();
		
		//int ritiroScaduti = 0;
		int quantoRitirare = quantita;
		
		TreeMap <Integer, InfoProdotto> MappaDataScadenzaTemp = new TreeMap <Integer, InfoProdotto>();
		// MDM - Portata.MappaDataScadenza - Trasformata in privata e inserito metodo get -----
		//MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).MappaDataScadenza;
		MappaDataScadenzaTemp = mappaProdotti.get(k.toString()).getMappaDataScadenza();
		
		
				//RITIRA TUTTI I PRODOTTI SCADUTI: Calcola quanti sono i prodotti scaduti
				if(risp==JOptionPane.YES_OPTION)
				{
					quantoRitirare = 0;
					System.out.println("Premuto SI");
						if(MappaDataScadenzaTemp.isEmpty())
						{	//Da Gestire con Eccezioni!
							System.out.println("MappaScadenza vuota! Impossibile Ritirare Scaduti!");
						}
						else
						{
							//Calcolo del Numero di Prodotti Scaduti (da inserire in variabile "ritiroScaduti")
							Set <Integer> ChiaviMappaScadenze1 = MappaDataScadenzaTemp.keySet();
							for (Integer n : ChiaviMappaScadenze1)
							{
								if(dataAttuale>n) //Individua scaduti, cio√® prodotti con dataScadenza minore di dataAttuale
									// ">" solamente, se avessimo messo anche "=" avrebbe incluso anche i prodotti di oggi, considerandoli scaduti!
								{
									quantoRitirare = quantoRitirare + MappaDataScadenzaTemp.get(n).getGiacenza();
									
								}	
							}
							System.out.println("Da ritirare: "+quantoRitirare+ " prodotti scaduti");
							//ritiroScaduti = quantoRitirare;
							
						}
				}
				//RITIRA SOLAMENTE IL PRIMO GRUPPO CIO√à CON DATA DI SCADENZA MINORE: Calcola gli scaduti con Data Pi√π PICCOLA
				else if(risp==JOptionPane.NO_OPTION)
				{
					quantoRitirare = 0;
						//System.out.println("Premuto NO");
						if(MappaDataScadenzaTemp.isEmpty())
						{	//Da Gestire con Eccezioni!
							System.out.println("MappaScadenza vuota! Impossibile Ritirare Scaduti!");
						}
						else
						{
							//Calcolo del Numero di Prodotti Scaduti (da inserire in variabile "ritiroScaduti")
							Set <Integer> ChiaviMappaScadenze1 = MappaDataScadenzaTemp.keySet();
							for (Integer n : ChiaviMappaScadenze1)
							{
								if(dataAttuale>n) //Individua scaduti, cio√® prodotti con dataScadenza minore di dataAttuale
									// ">" solamente, se avessimo messo anche "=" avrebbe incluso anche i prodotti di oggi, considerandoli scaduti!
								{
									quantoRitirare = quantoRitirare + MappaDataScadenzaTemp.get(n).getGiacenza();
									break; //Con questo break esco subito dopo appena trovato il primo gruppo!
								}	
							}
							//System.out.println("Da ritirare: "+quantoRitirare+ " prodotti scaduti");
							//ritiroScaduti = quantoRitirare;
							
						}
				}
		
		
		
		if(quantoRitirare==0) throw new ScadutiNonPresentiException("Attenzione! Non ci sono Prodotti Scaduti di "+ k.toString()+ " in magazzino.");
					
		mappaProdotti.get(k.toString()).ritiraScaduti(quantoRitirare, dataAmericana);
		//log = new LogProdotti();
		log.logRitiraScaduti(this.utenteCorrente, k.toString(), quantoRitirare, giacenza(k));
		String s = mappaProdotti.get(k.toString()).stampaMapScadenza();
		System.out.println(s);
		
		//Aggiornamento indici
		System.out.println("Gestore Ritiro Scaduti:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
		AggiornaIndice("Valore Magazzino",k);
		AggiornaIndice("Spesa Evitabile",k);
		System.out.println("Gestore Ritiro Scaduti:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");

		//Messaggio di conferma ritiro
		JOptionPane.showMessageDialog(null, "Ritirati " + quantoRitirare + " di " + k.toString());
	
	}
	
	/*public void ritiraScadutiDataScad(IDEsterno k, int quantita) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException
	{
		mappaProdotti.get(k.toString()).ritiraScadutiMapScadenza(quantita);
		String s = mappaProdotti.get(k.toString()).stampaMapScadenza();
		System.out.println(s);
	}*/
	
	/*public void put(Portata p, IDEsterno k) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException
	{
		if (k==null )
			throw new IDEsternoException("L/identificatore non deve essere nullo\n");
		if (p==null)
			throw new ClasseAlimentareException("La portata non deve essere nulla\n");
		//mappaProdotti.put(p, k.toString());
		mappaProdotti.inserisciProdotto(k.toString(), p);
	}*/
	
	public void eroga(IDEsterno k, int quantita) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ProdottiScadutiSolamenteException, ProdottiScadutiEProdottiBuoniException, MagazzinoException
	{
		iC.eroga(k, quantita);
	}
	
	public void erogaLB(IDEsterno k, int daErogare, String dataAm) throws IDEsternoException, MagazzinoException, GiacenzaInsufficienteException, DirittiException, ProdottoException, LineUnavailableException 
	{
		iC.erogaLB(k, daErogare, dataAm);
	}
	
	public void erogaThread(IDEsterno k, int quantita, Utente uthread) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ProdottiScadutiSolamenteException, ProdottiScadutiEProdottiBuoniException, MagazzinoException, InterruptedException
	{
		iC.erogaThread(k, quantita, uthread);
	}
		
	public void erogaCmqOAggiornaRich(IDEsterno k, int quantita, int risp) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException
	{
		iC.erogaCmqOAggiornaRich(k, quantita, risp);
	}
	
	public void erogaIndividuaProdottiBuoni(IDEsterno k, int quantita) throws DirittiException, IDEsternoException, GiacenzaInsufficienteException, ProdottoException
	{
		iC.erogaIndividuaProdottiBuoni(k, quantita);
	}
	
	
	//-------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------
	
	public void nuovoProdottoThread(String classeAlimento, IDEsterno k, Utente uthread) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		//Non c'era cmq bisogno di mettere il lock, in quanto si usa un solo Thread "Gestore" che inserisce Prodotti
		//Lo lascio, giusto se in futuro si vuole far partire l'applicazione con pi√π Gestori.
		//Ricordiamo che tutti i Thread hanno lo stesso oggetto "Controllore", in caso contrario non avrebbero senso i lock!
		lock.lock();
		utenteCorrente = uthread;   //I thread e gli utenti "normali" hanno un oggetto Controllore diverso!
		
		
		System.out.println("Gestore "+utenteCorrente.getLogin()+" : lock Gestore");
		if (CostantiClassiAlimentari.containsClasseAlimentareFinale (classeAlimento))
			//containsClasseAlimentareFinale √® un metodo statico che verifica se la Stringa in classeAlimento,
			//cio√® se un certo prodotto √® presente nell'Array "ClasseAlimentareFinale"
		{
			Portata p;
		
			
			//"Creo" oggetto "nomeClasse"
			Class<?> nomeClasseObj = Class.forName(classeAlimento); 
			
			//Imposto parametri
			//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
			Class<?> primoParametroObj = Class.forName("util.IDEsterno");
			Class<?> secondoParametroObj = Class.forName("util.Orologio");
		
			@SuppressWarnings("rawtypes")
			Class [] parametri ={primoParametroObj, secondoParametroObj};
			
			Constructor<?> c=null;
			c= nomeClasseObj.getConstructor(parametri);
			
			Orologio dataCreazione = new Orologio();
			//Creazione vera e propria dell'oggetto
			p = (Portata) c.newInstance(k, dataCreazione); 
			
			/*//Da modificare al piu presto
			if (classeAlimento.equals(CostantiClassiAlimentari.Acqua)) 
				p = new Acqua(k, new Orologio());
			else if (classeAlimento.equals(CostantiClassiAlimentari.BriccoFrutta)) 
				p = new BriccoFrutta(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Carne)) 
				p = new Carne(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Dolce)) 
				p = new Dolce(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Frutta)) 
				p = new Frutta(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Pesce)) 
				p = new Pesce(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Primo)) 
				p = new Primo(k);							
			else if (classeAlimento.equals(CostantiClassiAlimentari.Soda)) 
				p = new Soda(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Vino)) 
				p = new Vino(k);
			else 
				p = new Altro(k, new Orologio());*/
			try
			{
				//mappaProdotti.put(p, k.toString());
				mappaProdotti.inserisciProdotto(k.toString(), p);
				log.logNuovoProdotto(this.utenteCorrente, k.toString());
				
				System.out.println("Gestore "+utenteCorrente.getLogin()+" : Prodotto "+k.toString()+ " aggiunto! (Sblocco Thread Fornitori in attesa)");
				prodottoEsistenza.signalAll();
				
				System.out.println("Gestore "+utenteCorrente.getLogin()+" : unlock Gestore");
			}
			catch (IDEsternoException e) 
			{
				System.out.println("Gestore "+utenteCorrente.getLogin()+" : Prodotto/i gi√† presente/i!");
				
			}
			//log = new LogProdotti();
			
			finally
			{
				lock.unlock();
			}			
		}
		else
			throw new ClasseAlimentareException("\nClasse alimentare " + classeAlimento + " errata. ");
		
		/*
		Class nomeClasseObj = Class.forName(classeAlimento);
		p= (Portata) nomeClasseObj.newInstance((Object) k);
		*/
	}
	
	
	//-------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------
	
	
	public void nuovoProdotto(IDEsterno k, String classeAlimento, Orologio dataCreazione) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{	
		if (CostantiClassiAlimentari.containsClasseAlimentareFinale (classeAlimento))
			//containsClasseAlimentareFinale √® un metodo statico che verifica l'esistenza della Stringa in classeAlimento,
			//cio√® se un certo prodotto √® presente nell'Array "ClasseAlimentareFinale"
		{
			Portata p;
			//"Creo" oggetto "nomeClasse"
			Class<?> nomeClasseObj = Class.forName(classeAlimento); 
			
			//Imposto parametri
			//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
			Class<?> primoParametroObj = Class.forName("util.IDEsterno");
			Class<?> secondoParametroObj = Class.forName("util.Orologio");
		
			@SuppressWarnings("rawtypes")
			Class [] parametri ={primoParametroObj, secondoParametroObj};
			
			Constructor<?> c=null;
			c= nomeClasseObj.getConstructor(parametri);
			
			//Creazione vera e propria dell'oggetto
			p = (Portata) c.newInstance(k, dataCreazione); 
			
			
			/*//Da modificare al piu presto
			if (classeAlimento.equals(CostantiClassiAlimentari.Acqua)) 
				p = new Acqua(k, dataCreazione);
			else if (classeAlimento.equals(CostantiClassiAlimentari.BriccoFrutta)) 
				p = new BriccoFrutta(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Carne)) 
				p = new Carne(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Dolce)) 
				p = new Dolce(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Frutta)) 
				p = new Frutta(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Pesce)) 
				p = new Pesce(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Primo)) 
				p = new Primo(k);							
			else if (classeAlimento.equals(CostantiClassiAlimentari.Soda)) 
				p = new Soda(k);
			else if (classeAlimento.equals(CostantiClassiAlimentari.Vino)) 
				p = new Vino(k);
			else 
				p = new Altro(k, dataCreazione);
			*/
			//mappaProdotti.put(p, k.toString());
			mappaProdotti.inserisciProdotto(k.toString(), p);
			//log = new LogProdotti();
			log.logNuovoProdotto(this.utenteCorrente, k.toString());
			
		}
		else
			throw new ClasseAlimentareException("\nClasse alimentare " + classeAlimento + " errata. ");
		
		/*
		Class nomeClasseObj = Class.forName(classeAlimento);
		p= (Portata) nomeClasseObj.newInstance((Object) k);
		*/
	}
	
	
	public void nuovoProdottoConPrezzo(IDEsterno k, String classeAlimento, Orologio dataCreazione, double pA, double pV) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{	
		if (CostantiClassiAlimentari.containsClasseAlimentareFinale (classeAlimento))

		{
			Portata p;
			//"Creo" oggetto "nomeClasse"
			Class<?> nomeClasseObj = Class.forName(classeAlimento); 
			
			//Imposto parametri
			//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
			Class<?> primoParametroObj = Class.forName("util.IDEsterno");
			Class<?> secondoParametroObj = Class.forName("util.Orologio");
		
			@SuppressWarnings("rawtypes")
			Class [] parametri ={primoParametroObj, secondoParametroObj, double.class, double.class};

			
			Constructor<?> c=null;
			c= nomeClasseObj.getConstructor(parametri);
			
			//Creazione vera e propria dell'oggetto
			p = (Portata) c.newInstance(k, dataCreazione, pA, pV); 
			
			//mappaProdotti.put(p, k.toString());
			mappaProdotti.inserisciProdotto(k.toString(), p);
			//log = new LogProdotti();
			log.logNuovoProdotto(this.utenteCorrente, k.toString());
			
		}
		else
			throw new ClasseAlimentareException("\nClasse alimentare " + classeAlimento + " errata. ");
	}

	
	public void remove(IDEsterno k) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException, GiacenzaNonNullaException, DimensioneMenuException
	{
		if (k==null)
		{
			throw new IDEsternoException("\nControllore Amministrazione remove(). L'identificatore non deve essere nullo\n");
		}
		if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("\nControllore Amministrazione remove(). Prodotti " + k.toString() + "  non sono presenti\n");
		}
		
		//Per cancellare un prodotto, la giacenza deve essere nulla....mahh!
		
		//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.
		//if (mappaProdotti.get(k.toString()).giacenza() != 0)  
		if (mappaProdotti.get(k.toString()).getGiacenza() != 0)
		{
			throw new GiacenzaNonNullaException("\nControllore Amministrazione remove(). La giacenza di " + k.toString() + "  non Ë nulla\n");
		}
		
		//Se la mappaProdotti Ë 1, E non contiene il prodotto "ZZZ", allora --> Eccezione
		if (mappaProdotti.size()== 1 && !mappaProdotti.containsKey(MappaProdotti.inizio)&& !k.toString().equals(MappaProdotti.inizio))
		{
			throw new DimensioneMenuException("\nControllore Amministrazione remove(). Il menu non puÚ essere lasciato vuoto\n");
		}
		
		//Se arriva qui, allora rimuove il prodotto "k" selezionato!
		mappaProdotti.remove(k.toString());
		//log = new LogProdotti();
		log.logRimozioneProdotto(this.utenteCorrente, k.toString());
	}
	
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
	
	public void nuovoProdottoConPrezzoThread(String classeAlimento, IDEsterno k, double pA, double pV ,Utente uthread) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		//Non c'era cmq bisogno di mettere il lock, in quanto si usa un solo Thread "Gestore" che inserisce Prodotti
		//Lo lascio, giusto se in futuro si vuole far partire l'applicazione con pi˘ Gestori.
		//Ricordiamo che tutti i Thread hanno lo stesso oggetto "Controllore", in caso contrario non avrebbero senso i lock!
		lock.lock();
		utenteCorrente = uthread;   //I thread e gli utenti "normali" hanno un oggetto Controllore diverso!
		
		
		System.out.println("Gestore "+utenteCorrente.getLogin()+" : lock Gestore");
		if (CostantiClassiAlimentari.containsClasseAlimentareFinale (classeAlimento))
		{
			Portata p;
		
			
			//"Creo" oggetto "nomeClasse"
			Class<?> nomeClasseObj = Class.forName(classeAlimento); 
			
			//Imposto parametri
			//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
			Class<?> primoParametroObj = Class.forName("util.IDEsterno");
			Class<?> secondoParametroObj = Class.forName("util.Orologio");
					
			@SuppressWarnings("rawtypes")
			Class [] parametri ={primoParametroObj, secondoParametroObj, double.class, double.class};
			
			Constructor<?> c=null;
			c= nomeClasseObj.getConstructor(parametri);
			
		
			Orologio dataCreazione = new Orologio();
			//Creazione vera e propria dell'oggetto
			p = (Portata) c.newInstance(k, dataCreazione, pA, pV); 
			
			try
			{
				mappaProdotti.inserisciProdotto(k.toString(), p);
				log.logNuovoProdotto(this.utenteCorrente, k.toString());
				
				System.out.println("Gestore "+utenteCorrente.getLogin()+" : Prodotto "+k.toString()+ " aggiunto! (Sblocco Thread Fornitori in attesa)");
				prodottoEsistenza.signalAll();
				
				System.out.println("Gestore "+utenteCorrente.getLogin()+" : unlock Gestore");
			}
			catch (IDEsternoException e) 
			{
				System.out.println("Gestore "+utenteCorrente.getLogin()+" : Prodotto gi‡† presente!");
				
			}
			//log = new LogProdotti();
			
			finally
			{
				lock.unlock();
			}			
		}
		else
			throw new ClasseAlimentareException("\nClasse alimentare " + classeAlimento + " errata. ");
		
		/*
		Class nomeClasseObj = Class.forName(classeAlimento);
		p= (Portata) nomeClasseObj.newInstance((Object) k);
		*/
	}
	
	
	//-------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------

	private int calcolaDataAttuale()
	{
		GregorianCalendar dataAttual = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataAttualeStrg = sdf.format(dataAttual.getTime());
		int dataAttuale = (Integer.parseInt(dataAttualeStrg));
		System.out.println("Data Attuale: "+ dataAttuale);
		return dataAttuale;
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
			//if(superClasseProdottoCorrente!="Portata")//se = Portata, allora profondit‡ gerarchia =2
			if(superClasseProdottoCorrente!="entity.Portata")//se = Portata, allora profondit‡ gerarchia =2
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
		//if(superClasseProdottoCorrente!="Portata")//se = Portata, allora profondit‡ gerarchia =2
		if(superClasseProdottoCorrente!="entity.Portata")//se = Portata, allora profondit‡ gerarchia =2
		{
			VariabiliGerarchia.aggiornaVGerarchia(xMatriceGerarchia, yMatriceGerarchiaClasse, indice);
		}
		log.logAggiornamentoIndici(this.utenteCorrente, k.toString(), nomeIndice, indiceValCorrente );
		System.out.println("Fornitura:  ***** " + nomeIndice + " Corrente: " + indiceValCorrente + " *****" );
	}


}

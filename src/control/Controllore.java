package control;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Field;
//import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.sound.sampled.LineUnavailableException;

//my -----------
import util.*;
import exception.*;
import boundary.*;
import entity.*;

// COMMENTI ----------
// MDM - package - Nella divisione in package non era più visibile nei confini.
//                 Reso public il metodo salvaArticoli() ...
//                 protected void salvaArticoli() throws SerializzazioneException {...}
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------

public abstract class Controllore implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	protected static MappaProdotti 	mappaProdotti;

	protected Utente utenteCorrente=null;
	
	//public Lock mappaLock = new ReentrantLock();
	//GC: Mettendo il lock qui si blocca tutto; metterlo in mappa a livello di singolo prodotto.
	public Lock lock = new ReentrantLock();
	//public static Lock lock = new ReentrantLock();
	public Condition prodottoEsistenza = lock.newCondition();
	public Condition prodottoDisponibile = lock.newCondition();
	
	protected LogProdotti log = new LogProdotti();

	public Controllore()
	{
		if (mappaProdotti==null) 
			mappaProdotti = new MappaProdotti(CostantiNumeriche.DIMBASEMENU);
	}	

	public Controllore(String s)
	{
		
	}
	
	public Controllore(Utente utente, String admin)
	{
		this();
		this.utenteCorrente = utente;
		/* if (mappaProdotti==null) 
			mappaProdotti = new MappaProdotti(CostantiNumeriche.DIMBASEMENU); */
	}
	

	public Set<String> keySet() //Non restituisce un Array di String, ma un altro formato, cioe' un oggetto Set che in questo caso e' una collezione di oggetti String
	{
		return mappaProdotti.keySet();   //Restituisce un insieme dei nomi dei Prodotti (che sono le chiavi dell'Hashmap)
	}

	
	public void exit() throws PswException
	{
		ControlloreLogin.gestioneLogin();
	}
	
	public String getPassword() throws PswException
	{
		throw new PswException("Operazione chngPassword() non ammessa per questo utente\n");
	}
	

	public void cancellaUtente(String login) throws LoginException
	{
		throw new LoginException("Operazione cancellaUtente() non ammessa per questo utente\n");
	}
	
	public void nuovoUtente(String login, String ruolo, String password) throws PswException, LoginException, RuoloException
	{
		throw new PswException("Operazione nuovoUtente() non ammessa per questo utente\n");
	}
	public Utente creaNuovoUtenteThread(String ruolo) throws PswException, LoginException, RuoloException
	{
		System.out.println("**** ATTENZIONE: è stato chiamato creaNuovoUtenteThread() in control.Controllore!");
		return new Utente("void", "void", "void");
	}

	public void nuovoUtente(Credenziali credenziali, String ruolo) throws PswException, LoginException, RuoloException
	{
		throw new PswException("Operazione nuovoUtente() non ammessa per questo utente\n");
	}
	
	public boolean chngPassword(String vecchia, String nuova) throws PswException
	{
		throw new PswException("Operazione chngPassword() non ammessa per questo utente\n");
	}


	public Portata get(IDEsterno id) throws DirittiException 
	{
		throw new DirittiException("Operazione get(IDEsterno) non ammessa per questo utente\n");
	}
	
	public Portata getClassProdotto(String nomeProdotto) throws IDEsternoException 
	{
		return mappaProdotti.get(nomeProdotto);
		//throw new DirittiException("Operazione get(IDEsterno) non ammessa per questo utente\n");
	}

	// MDM - package - Nella divisione in package non era più visibile nei confini.
	//void remove(IDEsterno id) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException, GiacenzaNonNullaException, DimensioneMenuException
	public void remove(IDEsterno id) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException, GiacenzaNonNullaException, DimensioneMenuException
	{
		throw new DirittiException("Operazione remove(IDEsterno) non ammessa per questo utente\n");
	}
	
	void put(Portata a, IDEsterno id) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException{
		throw new DirittiException("Operazione put(Portata, IDEsterno) non ammessa per questo utente\n");
	}

	public void erogaLB(IDEsterno k, int quantita, String dataAm) throws IDEsternoException, MagazzinoException, GiacenzaInsufficienteException, DirittiException, ProdottoException, LineUnavailableException 
	{
		throw new DirittiException("Operazione erogaLB (IDEsterno) non ammessa da questo utente\n");
	}
	
	public void eroga(IDEsterno k, int quantita) throws DirittiException, IDEsternoException, ProdottoException, QuantitaException, GiacenzaInsufficienteException, ProdottiScadutiSolamenteException, ProdottiScadutiEProdottiBuoniException, MagazzinoException 
	{
		throw new DirittiException("Operazione eroga (IDEsterno, int) non ammessa da questo utente\n");
	}
	
	public void erogaCmqOAggiornaRich(IDEsterno k, int quantita, int risp) throws DirittiException, IDEsternoException, ProdottoException, QuantitaException, GiacenzaInsufficienteException 
	{
		throw new DirittiException("Operazione erogaCmq (IDEsterno, int) non ammessa da questo utente\n");
	}
	
	public void erogaIndividuaProdottiBuoni(IDEsterno k, int quantita) throws DirittiException, IDEsternoException, GiacenzaInsufficienteException, ProdottoException
	{
		throw new DirittiException("Operazione erogaIndividuaProdottiBuoni (IDEsterno, int) non ammessa da questo utente\n");
	}
	
	public void erogaThread(IDEsterno k, int quantita, Utente uthread) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ProdottiScadutiSolamenteException, ProdottiScadutiEProdottiBuoniException, MagazzinoException, InterruptedException
	{
		
	}
	
	public int giacenza(IDEsterno k) throws DirittiException, IDEsternoException, ProdottoException {
		throw new DirittiException("Operazione giacenza(IDEsterno) non ammessa per questo utente\n");
	}
	
	public void deposita(IDEsterno k, int quantita, int data) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException
	{
        throw new DirittiException("Operazione deposita(IDEsterno, int) non ammessa per questo utente\n");
	}
	public void depositaThread(IDEsterno k, int quantita, int data, Utente uthread) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, InterruptedException
	{
		throw new DirittiException("Operazione depositaThread(IDEsterno, int) non ammessa per questo utente\n");
	}
	
	public void ritiraScaduti(IDEsterno k, int risp, int quantita, String dataAmericana) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{
	      throw new DirittiException("Operazione ritiraScaduti(IDEsterno, int) non ammessa per questo utente\n");
	}
	
	public double successo(IDEsterno k, String metodo) throws IDEsternoException, ProdottoException, DirittiException  
	{
	      throw new DirittiException("Operazione successo(IDEsterno) non ammessa per questo utente\n");
	}
	/*public double successoClasse(String nomeClasse) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, RuoloException, DirittiException 
	{
		throw new DirittiException("Operazione successo(IDEsterno) non ammessa per questo utente\n");
	}*/
	
	public double gradimento(IDEsterno k, String metodo) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
	      throw new DirittiException("Operazione non ammessa per questo utente\n");
	}
	public double deperibilita(IDEsterno k, String metodo) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
	      throw new DirittiException("Operazione deperibilitÃ (IDEsterno) non ammessa per questo utente\n");
	}
	/*public double gradimentoClasse(String nomeClasse) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, RuoloException, DirittiException 
	{
		 throw new DirittiException("Operazione non ammessa per questo utente\n");
	}*/
	
	public double gradimentoMedioGiornaliero(IDEsterno k) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
	      throw new DirittiException("Operazione non ammessa per questo utente\n");
	}
	public double gradimentoMedioGiornalieroClasse(String classeBase) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		throw new DirittiException("Operazione non ammessa per questo utente\n");
	}
	
	public double gradimentoMedioGiornalieroCategoria(String categoria) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		throw new DirittiException("Operazione non ammessa per questo utente\n");
	}
	
	
	/*public double deperibilitaClasse(String nomeClasse) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, RuoloException, DirittiException 
	{
		 throw new DirittiException("Operazione deperibilitaClasse(IDEsterno) non ammessa per questo utente\n");
	}*/
	
	public void clear() throws DirittiException {
	      throw new DirittiException("Operazione clear() non ammessa per questo utente\n");
	}

	public void nuovoProdotto(IDEsterno k, String tipo, Orologio dataCreazione) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException,  SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
	      throw new DirittiException("Operazione nuovoProdotto(IDEsterno) non ammessa per questo utente\n");
	}
	
	public void nuovoProdottoConPrezzo(IDEsterno k, String tipo, Orologio dataCreazione, double pA, double pV) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException,  SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
	      throw new DirittiException("Operazione nuovoProdotto(IDEsterno) non ammessa per questo utente\n");
	}
	
	public void nuovoProdottoThread(String classeAlimento, IDEsterno k, Utente uthread) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		throw new DirittiException("Operazione nuovoProdottoThread(IDEsterno) non ammessa per questo utente\n");
	}
	
	public void nuovoProdottoConPrezzoThread(String classeAlimento, IDEsterno k, double pA, double pV, Utente uthread) throws DirittiException, ClasseAlimentareException, ProdottoException, IDEsternoException, ClassNotFoundException, SecurityException, NoSuchMethodException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
	{
		throw new DirittiException("Operazione nuovoProdottoThread(IDEsterno) non ammessa per questo utente\n");
	}
	
	public void ripristinaCredenziali(String login, String password) throws PswException
	{
			if (!(utenteCorrente.getLogin().equals(login) && utenteCorrente.getPassword().equals(password)))
		throw new PswException ("ripristinaCredenziali: Login o Password errate");		
	}
	public MappaProdotti getMappaProdotti()
	{
		return mappaProdotti;
	}

	public void depositaDataScad(IDEsterno k, int quantita, int data) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException
	{
				
	}
	/*public void ritiraScadutiDataScad(IDEsterno k, int quantita) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, GiacenzaInsufficienteException
	{
		
	}*/
	// MDM - package - Nella divisione in package non era più visibile nei confini.
	// protected void salvaArticoli() throws SerializzazioneException 
	public void salvaArticoli() throws SerializzazioneException 
	{
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/articoliSFile.dat";
		SerializzaOggetti articoliSer = new SerializzaOggetti(path);
		articoliSer.serializza(mappaProdotti);
		
		//Salva utenti
		String path2 = Dir+"/utenti.dat";
		SerializzaOggetti users = new SerializzaOggetti(path2);
		users.serializza(ControlloreLogin.getUtenti());
		
		
		//ATTENZIONE: Salvare solo variabili statiche delle classi "Base"!!
		Altro.salvaVarStatiche();
		Acqua.salvaVarStatiche();
		Vino.salvaVarStatiche();
		Soda.salvaVarStatiche();
		BriccoFrutta.salvaVarStatiche();
		
		PrimoAltro.salvaVarStatiche();
		PrimoCarne.salvaVarStatiche();
		PrimoPesce.salvaVarStatiche();
		
		SecondoCarne.salvaVarStatiche();
		SecondoPesce.salvaVarStatiche();
		
		Dolce.salvaVarStatiche();
		Frutta.salvaVarStatiche();
		
		//Per salvataggio Variabili statiche Gerarchia Alimenti
		VariabiliGerarchia.salvaVarStatiche();
		
		
		
		//Questo FUNGE
		/*SaveVarStatic save = SaveVarStatic.inizializza();
		//save.inserisciVariabile("Altro", "numDepositati", Altro.numDepositati);
		save.inserisciVariabile("Altro", "numDepositati", Altro.numDepositati);	
		
		String path2 = Dir+"/saveStatic.dat";
		SerializzaOggetti saveStatic = new SerializzaOggetti(path2);
		saveStatic.serializza(save);*/
	}	
	

	@SuppressWarnings("unchecked")
	public boolean caricaArticoli() throws DeserializzazioneException //, PswException, CaricamentoUtentiException
	{
		//MDM - Inserito per Debug ---
		String currDir = Funzioni.showDir();
		String Dir = "Save";
		String fullFileNameMagaz = Dir+"/articoliSFile.dat";
		
		 if (!new File(fullFileNameMagaz).exists()) 
		{
			//return false;
			throw new DeserializzazioneException("Nessun File Magazzino ancora disponibile!");
		}
				
		DeserializzaOggetti articoliSer = new DeserializzaOggetti(fullFileNameMagaz);
		mappaProdotti =  (MappaProdotti)articoliSer.deserializza();

		String fullFileNameUsers = Dir+"/utenti.datt";
		if (!new File(fullFileNameUsers).exists()) 
		{
			//return false;
			throw new DeserializzazioneException("Nessun File Utenti ancora disponibile!");
		}
				
		DeserializzaOggetti users = new DeserializzaOggetti(fullFileNameUsers);
		ControlloreLogin.caricaUtenti((Vector<Utente>)users.deserializza());

		
		//ATTENZIONE: Caricare solo variabili statiche delle classi "Base". 
		//I metodi provvederanno ad aggiornare quelle delle superClassi!
		Altro.caricaVarStatiche();
		Acqua.caricaVarStatiche();
		Vino.caricaVarStatiche();
		Soda.caricaVarStatiche();
		BriccoFrutta.caricaVarStatiche();
		
		PrimoAltro.caricaVarStatiche();
		PrimoCarne.caricaVarStatiche();
		PrimoPesce.caricaVarStatiche();
		
		SecondoCarne.caricaVarStatiche();
		SecondoPesce.caricaVarStatiche();
		
		Dolce.caricaVarStatiche();
		Frutta.caricaVarStatiche();
		
		
		if (!new File("Save/VariabiliGerarchia.dat").exists()) 
		{
			//return false;
			throw new DeserializzazioneException("Nessun File Variabili Gerarchia ancora disponibile!");
		}
		VariabiliGerarchia.caricaVarStatiche();
		
		
		/*SaveVarStatic save = SaveVarStatic.inizializza();
		DeserializzaOggetti saveStatic = new DeserializzaOggetti("Save/saveStatic.dat");
		save = (SaveVarStatic) saveStatic.deserializza();
		Integer numDep = save.getNumero("Altro", "numDepositati");
		Altro.numDepositati = numDep;
		//Integer b = save2.getNumero("Altro", "Prova3");
		System.out.println("Il numero Ã¨: "+ numDep);*/
		return true;
	}
	
	public void salvaLog()
	{
		log.salvaLog();
	}
	
	public String caricaLog() throws IOException, FileInesistenteException
	{
		return (new LogProdotti()).caricaLog();
	}
	
	public boolean creaNuovoLog() throws IOException, FileInesistenteException
	{
		return (new LogProdotti()).creaNuovoLog();
		//false se log.txt non esiste
		//true se ha rinominato correttamente il file
	}
	
	public String leggiMenuOrinatoxCategoria()
	{
		Set <String> p= keySet();
		String descrizione;
		int numCategorie= CostantiClassiAlimentari.CategorieAlimentari.length;
		String superClasseProdottoCorrente = "";
		
		if (p.isEmpty())
		{
			descrizione ="Il menu è vuoto";
		}
		else 
		{
			descrizione = "";
		}
		for (int cat = 0; cat < numCategorie; cat++){	
			descrizione = descrizione + "\n" + CostantiClassiAlimentari.CategorieAlimentari[cat].toUpperCase()+":\n";
			int n = 0;
			for (String k : p) 
			{
				n++;
				try 
				{																	  //k è una Stringa: nome Prodotto
					Portata oggettoPortata = getMappaProdotti().get(k);
					String nomeTipoProdotto = oggettoPortata.getClass().getSimpleName();
					try {
						//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
						//superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getSimpleName();
						superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getName();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
					//se la superclasse ha lo stesso nome della categoria
					if (CostantiClassiAlimentari.CategorieAlimentari[cat]==superClasseProdottoCorrente){
						String data = oggettoPortata.getGiornoCreazione();
						//System.out.print(data);
						
						//String nomeEtipo = n+". "+k+" ("+nomeTipoProdotto+"):";
						String nomeEtipo = k+" ("+nomeTipoProdotto+"):";
						int lunghezzaNomeEtipo = nomeEtipo.length();
						if (lunghezzaNomeEtipo<60)
						{
							int aggiuntaSpazi = 60 - lunghezzaNomeEtipo;
							for (int i=0; i<aggiuntaSpazi;i++)
							{
								nomeEtipo = nomeEtipo+" ";
							}
						}
						
						double pA = oggettoPortata.getPrezzoAcquisto();
						double pV = oggettoPortata.getPrezzoVendita();
						//System.out.print("PrezzoA: " + pA);
						 
						//descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\n";
						descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\t\t     "+ pA +"\t     "+ pV +"\n";
					}
					
					//se il prodotto ha lo stesso nome della categoria (es:Frutta e Dolce)
					else if (CostantiClassiAlimentari.CategorieAlimentari[cat]==nomeTipoProdotto){
						String data = oggettoPortata.getGiornoCreazione();
						//System.out.print(data);
						
						//String nomeEtipo = n+". "+k+" ("+nomeTipoProdotto+"):";
						String nomeEtipo = k+" ("+nomeTipoProdotto+"):";
						int lunghezzaNomeEtipo = nomeEtipo.length();
						if (lunghezzaNomeEtipo<60)
						{
							int aggiuntaSpazi = 60 - lunghezzaNomeEtipo;
							for (int i=0; i<aggiuntaSpazi;i++)
							{
								nomeEtipo = nomeEtipo+" ";
							}
						}
						
						double pA = oggettoPortata.getPrezzoAcquisto();
						double pV = oggettoPortata.getPrezzoVendita();
						//System.out.print("PrezzoA: " + pA);
						 
						//descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\n";
						descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\t\t     "+ pA +"\t     "+ pV +"\n";
					}
					
				} 
				catch (DirittiException e) 
				{
						System.out.print(e.getMessage()+"\n");
				} 
				catch (IDEsternoException e) 
				{
						System.out.print(e.getMessage()+"\n");
				} 
				catch (ProdottoException e) 
				{
						System.out.print(e.getMessage()+"\n");
				}
			}
		}
		return descrizione;
	}

	public String leggiMenu()
	{
		Set <String> p= keySet();
		String descrizione;
		
		if (p.isEmpty())
		{
			descrizione ="Il menu è vuoto";
		}
		else 
		{
			descrizione = "";
		}

		int n = 0;
		for (String k : p) 
		{
			n++;
			try 
			{																	  //k è una Stringa: nome Prodotto
				Portata oggettoPortata = getMappaProdotti().get(k);
				String nomeTipoProdotto = oggettoPortata.getClass().getSimpleName();

				String data = oggettoPortata.getGiornoCreazione();
				//System.out.print(data);
				
				String nomeEtipo = n+". "+k+" ("+nomeTipoProdotto+"):";
				int lunghezzaNomeEtipo = nomeEtipo.length();
				if (lunghezzaNomeEtipo<60)
				{
					int aggiuntaSpazi = 60 - lunghezzaNomeEtipo;
					for (int i=0; i<aggiuntaSpazi;i++)
					{
						nomeEtipo = nomeEtipo+" ";
					}
				}
				
				double pA = oggettoPortata.getPrezzoAcquisto();
				double pV = oggettoPortata.getPrezzoVendita();
				//System.out.print("PrezzoA: " + pA);
				 
				//descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\n";
				descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\t\t     "+ pA +"\t     "+ pV +"\n";
				
			} 
			catch (DirittiException e) 
			{
					System.out.print(e.getMessage()+"\n");
			} 
			catch (IDEsternoException e) 
			{
					System.out.print(e.getMessage()+"\n");
			} 
			catch (ProdottoException e) 
			{
					System.out.print(e.getMessage()+"\n");
			}
		}
		
		return descrizione;
	}
	
	public String leggiMenuSenzaPrezzoAcquisto()
	{
		Set <String> p= keySet();
		String descrizione;
		
		if (p.isEmpty())
		{
			descrizione ="Il menu è vuoto";
		}
		else 
		{
			descrizione = "";
		}
		
		int n = 0;
		for (String k : p) 
		{
			n++;
			try 
			{																	  //k è una Stringa: nome Prodotto
				Portata oggettoPortata = getMappaProdotti().get(k);
				String nomeTipoProdotto = oggettoPortata.getClass().getSimpleName();
				
				String data = oggettoPortata.getGiornoCreazione();
				//System.out.print(data);
				
				String nomeEtipo = n+". "+k+" ("+nomeTipoProdotto+"):";
				int lunghezzaNomeEtipo = nomeEtipo.length();
				if (lunghezzaNomeEtipo<60)
				{
					int aggiuntaSpazi = 60 - lunghezzaNomeEtipo;
					for (int i=0; i<aggiuntaSpazi;i++)
					{
						nomeEtipo = nomeEtipo+" ";
					}
				}

				double pV = oggettoPortata.getPrezzoVendita();
				//System.out.print("PrezzoV: " + pV);

				descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\t\t     "+ pV +"\n";

			} 
			catch (DirittiException e) 
			{
					System.out.print(e.getMessage()+"\n");
			} 
			catch (IDEsternoException e) 
			{
					System.out.print(e.getMessage()+"\n");
			} 
			catch (ProdottoException e) 
			{
					System.out.print(e.getMessage()+"\n");
			}
		}
		return descrizione;
	}
	
	
	public String leggiMenuSenzaPrezzoVendita()
	{
		Set <String> p= keySet();
		String descrizione;
		
		if (p.isEmpty())
		{
			descrizione ="Il menu è vuoto";
		}
		else 
		{
			descrizione = "";
		}
		
		int n = 0;
		for (String k : p) 
		{
			n++;
			try 
			{																	  //k è una Stringa: nome Prodotto
				Portata oggettoPortata = getMappaProdotti().get(k);
				String nomeTipoProdotto = oggettoPortata.getClass().getSimpleName();
				
				String data = oggettoPortata.getGiornoCreazione();
				//System.out.print(data);
				
				String nomeEtipo = n+". "+k+" ("+nomeTipoProdotto+"):";
				int lunghezzaNomeEtipo = nomeEtipo.length();
				if (lunghezzaNomeEtipo<60)
				{
					int aggiuntaSpazi = 60 - lunghezzaNomeEtipo;
					for (int i=0; i<aggiuntaSpazi;i++)
					{
						nomeEtipo = nomeEtipo+" ";
					}
				}

				double pA = oggettoPortata.getPrezzoAcquisto();
				//System.out.print("PrezzoA: " + pA);

				descrizione = descrizione +nomeEtipo+"\t"+giacenza(new IDEsterno(k)) +"\t      "+data+"\t\t     "+ pA +"\n";

			} 
			catch (DirittiException e) 
			{
					System.out.print(e.getMessage()+"\n");
			} 
			catch (IDEsternoException e) 
			{
					System.out.print(e.getMessage()+"\n");
			} 
			catch (ProdottoException e) 
			{
					System.out.print(e.getMessage()+"\n");
			}
		}
		return descrizione;
	}
	
	//DA SPOSTARE IN CONTROLLORE GESTIONE!!
	public void nuovaGestione()
	{
		mappaProdotti = new MappaProdotti(CostantiNumeriche.DIMBASEMENU);
		Portata.azzeraVarStatiche();
		
		Bevanda.azzeraVarStatiche();
		Altro.azzeraVarStatiche();
		Acqua.azzeraVarStatiche();
		Vino.azzeraVarStatiche();
		
		Primo.azzeraVarStatiche();
		
		VariabiliGerarchia.azzeraVGerarchia();
		
		
	}
	
	public void comunicaUtenteThread(Utente thread)
	{
		utenteCorrente = thread;
	}
	
	public void controllaPsw(String psw1, String psw2) throws PswException, DirittiException
	{
		throw new DirittiException("Operazione controllaPsw non ammessa per questo utente\n");
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
			if(superClasseProdottoCorrente!="Portata")//se = Portata, allora profondità gerarchia =2
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


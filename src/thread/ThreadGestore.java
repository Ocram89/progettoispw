package thread;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.util.Date;
//import java.util.Vector;

//my -----------
import util.*;
import exception.*;
import control.*;

//COMMENTI ----
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------


public class ThreadGestore implements Runnable
{
//	private Orologio orologio = new Orologio();
//	private boolean stop = false;
	//private int numGusti = 6;
	private Utente utenteGestore;
//	private String login;
	private String ruolo;
//	private String password;
	private Controllore controlloreCorrente;
	//private ControlloreLogin controlloreLogin;
	
	//TraduttoreTipoG_G mgt = new TraduttoreTipoG_G();
	
	public ThreadGestore(Controllore gest) throws PswException
	{
		//creaUtente();
		//controlloreCorrente = new ControlloreGestione(utenteGestore, "threads");
		this.controlloreCorrente = gest;
	}
	
	
	/*private void creaUtente() throws PswException
	{
		login = "tGestore1";
		ruolo = CostantiRuolo.RUOLO[3];   //All'indice 3 corrisponde il GESTORE
		password = "12345678";
		Integer num = new Integer(1);
		final Vector<Utente> utenti =	ControlloreLogin.getUtenti();
		boolean loop = true;
		boolean utenteTrovato = false;
		while(loop)
		{
			for (Utente u: utenti)
			{
				if (u.getLogin().equals(login))
				{
					System.out.println(login+ " esiste!");
					utenteTrovato = true;
					num++;
					login = "tGestore"+num.toString();
					break;
				}
				if(!utenteTrovato) //Se non esiste l'utente cercato esci dal ciclo
				{
					loop = false;
				}		
			}
		}
		
		
		utenteGestore = new Utente(login, ruolo, password);
		ControlloreLogin.inserisciUtente(utenteGestore); //Inserisce l'utente nel Vector<Utente> di ControlloreLogin
	}*/
	
	public void run()
	{
		try 
		{
			ruolo = CostantiRuolo.RUOLO[3];   //All'indice 3 corrisponde il GESTORE
			utenteGestore = controlloreCorrente.creaNuovoUtenteThread(ruolo);
		} 
		catch (PswException e1) {e1.printStackTrace();} 
		catch (LoginException e1) {e1.printStackTrace();} 
		catch (RuoloException e1) {e1.printStackTrace();}
		
		IDEsterno acquaPanna = new IDEsterno("Panna");
		IDEsterno refosco = new IDEsterno("Refosco");
		IDEsterno duca = new IDEsterno("Duca San Felice");
		IDEsterno acquaLete = new IDEsterno("Lete");
		IDEsterno morellino = new IDEsterno("Morellino di Scansano");
		IDEsterno acquaSantAnna = new IDEsterno("Sant'Anna");
		IDEsterno gamberi = new IDEsterno("Gamberi");
		IDEsterno tortellini = new IDEsterno("Tortellini");
		IDEsterno mele = new IDEsterno("Mele");
		IDEsterno pere = new IDEsterno("Pere");
		IDEsterno torta = new IDEsterno("Torta");
		
		try
		{	
			//Crea Nuovi Prodotti
			//controlloreCorrente.mappaLock.lock();
			Thread.sleep(2000); //Ritardo per far in modo che i Fornitori Aspettino
			System.out.println(infoOra() + " ThreadGestore: ***** Inserimento nuovi prodotti...   *****");
			
			//Inserimento prodotti senza Prezzo
			//controlloreCorrente.nuovoProdottoThread("Altro", acquaPanna, utenteGestore);
			//controlloreCorrente.nuovoProdottoThread("Vino", refosco, utenteGestore);
			
			//Inserimento prodotti con Prezzi di Acquisto e Vendita
			
			//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Acqua", acquaPanna, 0.8, 2.5, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Vino", refosco, 3.5, 8.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Vino", duca, 3.5, 10.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Acqua", acquaLete, 0.8, 3.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Vino", morellino, 3.5, 9.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Acqua", acquaSantAnna, 0.5, 2.5, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.SecondoPesce", gamberi, 4.5, 8.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.PrimoCarne", tortellini, 1.0, 6.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Frutta", mele, 0.20, 1.0, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Frutta", pere, 0.20, 1.5, utenteGestore);
			controlloreCorrente.nuovoProdottoConPrezzoThread("entity.Dolce", torta, 1.0, 3.0, utenteGestore);
			
			System.out.println(infoOra() + " ThreadGestore: ***** Fine Inserimento nuovi prodotti *****");
			//controlloreCorrente.mappaLock.unlock();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		} 
		catch (DirittiException e) 
		{
			e.printStackTrace();
		} 
		catch (ClasseAlimentareException e) 
		{
			e.printStackTrace();
		} 
		catch (ProdottoException e) 
		{
			e.printStackTrace();
		} 
		catch (IDEsternoException e) 
		{
			System.out.println("Thread Gestore: Prodotto già presente!");
		}
		/*catch (Exception e)
		{
			e.printStackTrace();
		}*/ catch (SecurityException e) {
			
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			
			e.printStackTrace();
		} catch (InstantiationException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
	
			e.printStackTrace();
		} catch (InvocationTargetException e) {

			e.printStackTrace();
		}
		
	}
	
	/*private int calcolaRandomInt()
	{
		Random random = new Random();
		int numeroMax = numGusti;
		return new Integer(random.nextInt(numeroMax));
	}*/
	


/*	public void interrompi()
	{
		stop = true;
	}*/
	private String infoOra() 
	{
		Date d = new Date(System.currentTimeMillis());
		DateFormat df = DateFormat.getTimeInstance();
		return df.format(d) + ": ";
	}

}

package thread;

import java.text.DateFormat;
import java.util.Date;
//import java.util.Vector;

//my -----------
import util.*;
import exception.*;
import control.*;

public class ThreadFornitore implements Runnable
{
	private Orologio orologio = new Orologio();
//	private boolean stop = false;
	//private int numGusti = 6;
	private Utente utenteFornitore;
//	private String login;
	private String ruolo;
//	private String password;
	private Controllore controlloreCorrente;
	//private ControlloreLogin controlloreLogin;
	
	//TraduttoreTipoG_G mgt = new TraduttoreTipoG_G();
	
	public ThreadFornitore(Controllore forn) throws PswException, LoginException, RuoloException
	
	{
		this.controlloreCorrente = forn;
		
		//controlloreCorrente = new ControlloreDeposito(utenteFornitore, "threads");
	}
	
	
	/*private void creaUtente() throws PswException, LoginException, RuoloException
	{
		login = "tFornitore1";
		ruolo = CostantiRuolo.RUOLO[2];   //All'indice 2 corrisponde il FORNITORE
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
		utenteFornitore = new Utente(login, ruolo, password);
		controlloreCorrente.nuovoUtenteThread(login, ruolo, password);
		//ControlloreLogin.inserisciUtente(utenteFornitore); //Inserisce l'utente nel Vector<Utente> di ControlloreLogin
	}*/
	
	public void run()
	{
		try 
		{
			ruolo = CostantiRuolo.RUOLO[2];   //All'indice 2 corrisponde il FORNITORE
			utenteFornitore = controlloreCorrente.creaNuovoUtenteThread(ruolo);
		} 
		catch (PswException e1) {e1.printStackTrace();} 
		catch (LoginException e1) {e1.printStackTrace();} 
		catch (RuoloException e1) {e1.printStackTrace();}
		
		int oraAmericanaScad = orologio.calcolaDataFormatoAmericano();
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
		
		//while(!stop)
		int a=1;
		while(a>0)
		{
			try
			{
				//Thread.sleep(2000);
				System.out.println(infoOra() + "Thread Fornitore: ***** Deposito prodotti ...   *****");
				//Deposita Prodotti
				
				//controlloreCorrente.mappaLock.lock();
				
				// Deposito Prodotti
				
				controlloreCorrente.depositaThread(refosco, 3, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(duca, 3, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(acquaLete, 10, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(morellino, 3, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(acquaSantAnna, 10, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(gamberi, 5, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(tortellini, 5, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(mele, 10, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(pere, 10, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(torta, 5, oraAmericanaScad, utenteFornitore);
				controlloreCorrente.depositaThread(acquaPanna, 5, oraAmericanaScad, utenteFornitore);
				
				//controlloreCorrente.mappaLock.unlock();
				
			}
			/*catch (ProductTypeException e)
			{
				e.printStackTrace();
			}*/
			catch (InterruptedException e)
			{
				e.printStackTrace();
			} 
			catch (DirittiException e) 
			{
				e.printStackTrace();
			} 
			catch (ProdottoException e) 
			{
				e.printStackTrace();
			}
			catch (IDEsternoException e) 
			{
				e.printStackTrace();
			} 
			catch (QuantitaException e) 
			{
				e.printStackTrace();
			}
			
			//decrementa "a"
			a--;
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


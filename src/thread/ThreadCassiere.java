package thread;

import java.text.DateFormat;
import java.util.Date;

//my -----------
import util.*;
import exception.*;
import control.*;

public class ThreadCassiere implements Runnable
{
//	private Orologio orologio = new Orologio();
//	private boolean stop = false;
	//private int numGusti = 6;
	private Utente utenteCassiere;
//	private String login;
	private String ruolo;
//	private String password;
	private Controllore controlloreCorrente;
	//private ControlloreLogin controlloreLogin;
	
	//TraduttoreTipoG_G mgt = new TraduttoreTipoG_G();
	
	public ThreadCassiere(Controllore cass) throws PswException
	
	{
		//creaUtente();
		controlloreCorrente = cass;
		//controlloreCorrente.comunicaUtenteThread(utenteCassiere);
		//controlloreCorrente = new ControlloreErogazione(utenteCassiere, "threads");
	}
	
	
	/*private void creaUtente() throws PswException
	{
		login = "tCassiere01";
		ruolo = CostantiRuolo.RUOLO[1];   //All'indice 1 corrisponde il CASSIERE
		password = "12345678";
		utenteCassiere = new Utente(login, ruolo, password);
		ControlloreLogin.inserisciUtente(utenteCassiere); //Inserisce l'utente nel Vector<Utente> di ControlloreLogin
	}*/
	
	public void run()
	{
		try 
		{
			ruolo = CostantiRuolo.RUOLO[1];   //All'indice 1 corrisponde il CASSIERE
			utenteCassiere = controlloreCorrente.creaNuovoUtenteThread(ruolo);
		} 
		catch (PswException e1) {e1.printStackTrace();} 
		catch (LoginException e1) {e1.printStackTrace();} 
		catch (RuoloException e1) {e1.printStackTrace();}
		
		//int oraAmericanaScad = orologio.calcolaDataFormatoAmericano();
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
		//int a=1;
		/*while(a>0)
		{*/
		
			try
			{
				//Thread.sleep(2000);
				System.out.println(infoOra() + "Thread Cassiere: ***** Erogazione prodotti...   *****");
				//Deposita Prodotti
				
				//controlloreCorrente.mappaLock.lock();
				
				//Eroga prodotti			
				controlloreCorrente.erogaThread(acquaPanna, 1, utenteCassiere);
				controlloreCorrente.erogaThread(refosco, 2, utenteCassiere);
				controlloreCorrente.erogaThread(duca, 3, utenteCassiere);
				controlloreCorrente.erogaThread(acquaLete, 6, utenteCassiere);
				controlloreCorrente.erogaThread(morellino, 1, utenteCassiere);
				controlloreCorrente.erogaThread(acquaSantAnna, 3, utenteCassiere);
				controlloreCorrente.erogaThread(gamberi, 4, utenteCassiere);
				controlloreCorrente.erogaThread(tortellini, 4, utenteCassiere);
				controlloreCorrente.erogaThread(mele, 5, utenteCassiere);
				controlloreCorrente.erogaThread(pere, 6, utenteCassiere);
				controlloreCorrente.erogaThread(torta, 3, utenteCassiere);
				
				/*controlloreCorrente.eroga(brioBlu, 5);
				controlloreCorrente.eroga(sanBenedetto, 10);*/
				
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
			} catch (GiacenzaInsufficienteException e) {
				e.printStackTrace();
			} catch (ProdottiScadutiSolamenteException e) {
				e.printStackTrace();
			} catch (ProdottiScadutiEProdottiBuoniException e) {
				e.printStackTrace();
			} catch (MagazzinoException e) {
				e.printStackTrace();
			}
			//a--;
		//}
		
		
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

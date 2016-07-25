package thread;

import java.text.DateFormat;
import java.util.Date;
//import java.util.Random;

//my -----------
import util.*;
import exception.*;
import control.*;


// QUESTA CLASSE NON SERVE: DA CANCELLARE!!

public class ThreadAmministratore implements Runnable
{
	private Orologio orologio = new Orologio();
//	private boolean stop = false;
	//private int numGusti = 6;
	private Utente utenteAdmin;
	private String login;
	private String ruolo;
	private String password;
	private ControlloreAmministrazione controlloreCorrente;
	//private ControlloreLogin controlloreLogin;
	
	//TraduttoreTipoG_G mgt = new TraduttoreTipoG_G();
	
	public ThreadAmministratore() throws PswException
	
	{
		creaUtente();
		//controlloreCorrente = new ControlloreAmministrazione(utenteAdmin, "threads");
	}
	
	
	private void creaUtente() throws PswException
	{
		login = "tAdmin01";
		ruolo = CostantiRuolo.RUOLO[4];   //All'indice 4 corrisponde l'AMMINISTRATORE
		password = "12345678";
		utenteAdmin = new Utente(login, ruolo, password);
		ControlloreLogin.inserisciUtente(utenteAdmin); //Inserisce l'utente nel Vector<Utente> di ControlloreLogin
	}
	
	public void run()
	{
		int oraAmericanaScad = orologio.calcolaDataFormatoAmericano();
		IDEsterno sprite = new IDEsterno("Sprite");
//		IDEsterno cocacola = new IDEsterno("CocaCola");
//		IDEsterno aranciata = new IDEsterno("Aranciata");
		/*try
		{	
			
			//Crea Nuovi Prodotti
			
			
			System.out.println(infoOra() + " ThreadAdmin: ***** Inserimento nuovi prodotti... *****");
		//	controlloreCorrente.nuovoProdotto(sprite, "Altro");
			controlloreCorrente.nuovoProdotto("Altro", cocacola);
			controlloreCorrente.nuovoProdotto("Altro", aranciata);
			
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
			System.out.println("Thread Admin: Prodotto/i già presente/i!");
		}*/
		/*catch (Exception e)
		{
			e.printStackTrace();
		}*/
		
		
		//while(!stop)
		int a=5;
		while(a>0)
		{
			try
			{
				Thread.sleep(2000);
				System.out.println(infoOra() + "Thread Admin: Depositati nuovi prodotti");
				//Deposita Prodotti
				
				//controlloreCorrente.mappaLock.lock();
				
				controlloreCorrente.depositaThread(sprite, 10, oraAmericanaScad, utenteAdmin);
				/*controlloreCorrente.deposita(cocacola, 15, oraAmericanaScad);
				controlloreCorrente.deposita(aranciata, 20, oraAmericanaScad);*/
				
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
			a--;
		}
		
		
	}
	
	/*private int calcolaRandomInt()
	{
		Random random = new Random();
		int numeroMax = numGusti;
		return new Integer(random.nextInt(numeroMax));
	}*/
	


	/*public void interrompi()
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


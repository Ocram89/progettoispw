package control;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Vector;


import javax.swing.JOptionPane;

import thread.ThreadFornitore;
import thread.ThreadGestore;
//my -----------
import util.*;
import exception.*;
import boundary.*;
import thread.*;

//COMMENTI --------
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------


//VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE
//VERSIONE INCOMPLETA DI IMPIEGO DI RIFLESSIVITA' (Class)
//INSERITA INVOCAZIONE RIFLESSIVA ANCHE DI COSTRUTTORI CON PARAMETRI
  // CIO' CONSENTE DI RIMUOVERE INVOCAZIONE DI QUESTA INTERFACCIA DA CONTROLLORE (!) PER getUtenteCorrente()

public class ControlloreLogin extends Controllore implements Serializable
{
	private static final long			serialVersionUID	= 1L;
	private static String 				ruolo; 
	private static Credenziali 	 		credenzialiCorrenti=null;
	private static Utente 	 			utenteCorrente=null;
	private static Vector<Utente> 		utenti=null;
	
	//Temporanea per il test
	private static Controllore k;
	private static ControlloreLogin h;   
	private static ConfineLogin c;

	public ControlloreLogin(String dummy)
	{
		//System.out.println("Entra con CambiaUtente!");
	}
	
	//Costruttore
	private ControlloreLogin() throws PswException 
	{
		//System.out.println("Qui entra all'inizio!");
		super(); //Chiama subito il Controllore, così crea subito la MappaProdotti
		
		if (utenti == null)  
		{
			resetUtenti();
			//utenti = new Vector<Utente>();		
			//inizializzazionePerVersioneMemoriaCentrale();
		}
		//Per prima cosa il Costruttore crea un oggetto Vector e chiama "inizializzazionePerVersioneMemoriaCentrale()"		
	}
	
	public static void gestioneLogin() throws PswException 
	{                             //E' chiamato da ConfineAvvio(), quando si preme LOGIN
		h=new ControlloreLogin(); 	
		c= new ConfineLogin(h);    //Fa partire il ConfineLogin!
		
		//**** andava bene anche "this", tanto cmq tutte statiche sono le var
	}
	
	public static void gestioneNuovoLogin()   
	{
		//HO INSERITO ANCHE QUI inizializzazionePerVersioneMemoriaCentrale(); IN MODO CHE ANCHE SE SI ACCEDE VIA "MENU"
		//VENGONO CREATI GLI UTENTI. Il problema era che se si Sospendeva da Anonimo acceduto da "Menu", e si cercava
		//di fare la login, gli utenti non erano mai inizializzati e quindi dava errore!
		if (utenti == null)  
		{
			utenti = new Vector<Utente>();		
			try 
			{
				//E' stato necessario porre il metodo inizializzazionePerVersioneMemoriaCentrale() come STATIC
				inizializzazionePerVersioneMemoriaCentrale(); 
			} 
			catch (PswException e) 
			{
				e.printStackTrace();
			}
		}
		h=new ControlloreLogin("Dummy");
		c= new ConfineLogin(h);
	}
	
	public static ConfineLogin getConfineLogin() 
	{
		return c;
	}
	// Fine 
	
	
	//TOGLIERE STATIC SE QUALCOSA NON FUNZIONA. 
	//RINUIOVERE QUESTI UTENTI SUPERATA LA FASE DI TEST
	private static void inizializzazionePerVersioneMemoriaCentrale() throws PswException
	{
		ruolo = CostantiRuolo.RUOLO[4];   //All'indice 4 corrisponde l'AMMINISTRATORE
		String login = "q";
		String password = "q";
		utenti.add(new Utente (login, ruolo, password));
				
		//AGGIUNGO UN CASSIERE
		ruolo = CostantiRuolo.RUOLO[1];   //All'indice 1 corrisponde il CASSIERE
		String login2 = "c";
		String password2 = "c";
		utenti.add(new Utente (login2, ruolo, password2));
		
		//AGGIUNGI UN FORNITORE
		ruolo = CostantiRuolo.RUOLO[2];   //All'indice 2 corrisponde il FORNITORE
		String login3 = "f";
		String password3 =  "f";
		utenti.add(new Utente (login3, ruolo, password3));
		
		//AGGIUNGI UN GESTORE
		ruolo = CostantiRuolo.RUOLO[3];   //All'indice 3 corrisponde il GESTORE
		String login4 = "g";
		String password4 =  "g";
		utenti.add(new Utente (login4, ruolo, password4));
		
		//AGGIUNGI UN ANONIMO
		ruolo = CostantiRuolo.RUOLO[0];   //All'indice 0 corrisponde il ANONIMO
		String login5 = "a";
		String password5 =  "a";
		utenti.add(new Utente (login5, ruolo, password5));
	}
	
	@SuppressWarnings("unchecked")
	private void controlloCredenziali()throws UtenteException, PswException, ClassNotFoundException, IllegalAccessException, InstantiationException, RuoloException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException 
	{
			boolean utenteEsistente=false;
			boolean passwordCorretta = false;
			ruolo=CostantiRuolo.ANONIMO;
			for (Utente u: utenti)    //Per ogni oggetto Utente nel Vector "utenti"
			{
				/*if (!u.getLogin().equals(credenzialiCorrenti.getLogin())) throw new UtenteException();
				if (!u.getLogin().equals(credenzialiCorrenti.getLogin())) throw new PswException();*/
				//Se le Credenziali dell'utentecorrente corrispondono a uno degli utenti inseriti, ENTRA nel corpo "if"
				if (u.getLogin().equals(credenzialiCorrenti.getLogin()))
				{
					utenteEsistente = true;
					if(u.getPassword().equals(credenzialiCorrenti.getPassword()))
					{
						passwordCorretta = true;
						ruolo = u.getRuolo(); //Assegna il Ruolo assegnato all'Utente all'atto delle registrazione!
						utenteCorrente= new Utente(credenzialiCorrenti.getLogin(), ruolo, credenzialiCorrenti.getPassword());
						break; //Esce dal for
					}					
				}			
				
				/*if (u.getLogin().equals(credenzialiCorrenti.getLogin()) && u.getPassword().equals(credenzialiCorrenti.getPassword()))
				{	 
					ruolo = u.getRuolo(); //Assegna il Ruolo che l'Utente aveva quando si è registrato!
					
					utenteCorrente= new Utente(credenzialiCorrenti.getLogin(), ruolo, credenzialiCorrenti.getPassword());
					break; //Esce dal for
				}*/
				
			}// Il for è servito per identificare chi si è connesso, se un Cassiere, se l'Admin, ecc...
			
			if(!utenteEsistente)throw new UtenteException();
			if(!passwordCorretta)throw new PswException();
						
			//0:ANONIMO, 1:CASSIERE, 2:FORNITORE, 3:GESTORE, 4:AMMINISTRATORE
			
			//In base al tipo di Ruolo, restituisce il tipo di CONTROLLORE
			String nomeClasseString = CostantiRuolo.NOMICLASSICONTROLLORI[CostantiRuolo.index(ruolo)];
			
			//Crea un oggetto Class corrispondente al nome del Controllore cercato!
			@SuppressWarnings("rawtypes")
			Class nomeClasseObj = Class.forName(nomeClasseString);
			
			if (!nomeClasseString.equals(CostantiRuolo.ControlloreAmministrazione)) 
				//Se il nome del Controllore cercato non e' ControlloreAmministrazione
			{
				//Costruttore senza parametri
				//k= (Controllore) nomeClasseObj.newInstance(); //SUD: DECOMMENTARE QUESTO SE QUANTI SEGUE NON FUNZIONA
				
				
				//DA QUI IN POI SCRITTO DA STUDENTE
				@SuppressWarnings("rawtypes")
				//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
				Class primoParametroObj = Class.forName("util.Utente");
				//System.out.println("Qui?");
				@SuppressWarnings("rawtypes")
				Class [] parametri ={primoParametroObj};
				@SuppressWarnings("rawtypes")
				Constructor c=null;
				c= nomeClasseObj.getConstructor(parametri);
				
				if (c!=null) 
				{
					
					k= (Controllore) c.newInstance(utenteCorrente); //Classe di k e' ControlloreAmministrazione
					
					//equivale a:        new ControlloreXXXXXXXXXXXXXXXXX(utenteCorrente);
					
					
				}
				else throw new RuoloException ("\nControllore Login. Costruttore con parametri di ruolo non riconosciuto");
				
				//System.out.println(k.getClass().toString());
				/*crea una nuova istanza che non è ControlloreAmministrazione, ma un altro controllore a seconda del
				 * tipo di utente che si è connesso!
				*/
			}
			else
			{
				//Costruttore con parametri
				
				//solo il Controllo Amministrazione ha il Costruttore con il parametro di tipo Utente!!!
				@SuppressWarnings("rawtypes")
				Class primoParametroObj = Class.forName("util.Utente"); //Crea un oggetto Utente!
				//Non vi sono altri parametri
				@SuppressWarnings("rawtypes")
				Class [] parametri ={primoParametroObj}; //Inserisce in un array dei parametri oggetto! In questo caso, solo Utente
				
				//Class aClassObj = Class.forName("ControlloreAmministrazione");
				@SuppressWarnings("rawtypes")
				Constructor c=null;
				c= nomeClasseObj.getConstructor(parametri); //equivale a -->  c = ControlloreAmministrazione.getConstructor(Utente)
				
				
				if (c!=null) 
				{
					
					k= (Controllore) c.newInstance(utenteCorrente); //Classe di k è ControlloreAmministrazione
					//equivale a:        new ControlloreAmministrazione(utenteCorrente);
					
					//System.out.println(k.getClass().toString());
				}
				else throw new RuoloException ("\nControllore Login. Costruttore con parametri di ruolo non riconosciuto");
			}
	}

	public void passaCredenziali (String login, String password) throws UtenteException, PswException, RuoloException, ClassNotFoundException, IllegalAccessException, InstantiationException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException 
	{
		credenzialiCorrenti= new Credenziali(login, password);
		controlloCredenziali();
	}


	/*
	public static String getRuolo(){
		return ruolo;
	}
	*/
	
	public static Vector<Utente> getUtenti() 
	{
		return utenti;
	}
	
	public static void caricaUtenti(Vector<Utente> u)
	{
		utenti = u; 
	}
	
	public static void resetUtenti() throws PswException
	{
		utenti = new Vector<Utente>();		
		inizializzazionePerVersioneMemoriaCentrale();
	}
	
	public static void inserisciUtente(Utente utente) //throws PswException 
	{
		
		utenti.add(utente);
	}

	/*
	public static Utente getUtenteCorrente() {
		return utenteCorrente;
	}
	*/
	
	public void exit() {
		System.exit(0);
	}
	
	
	//Metodo temporaneo per il test
	public Controllore controlloreCorrente() 
	{
		return k;
	}


//MDM ----------------------------------------------------------------------
	public void avviaThreads()
	{		
		try
		{
			//avviaThreads = false;
			// MDM - Effettuao chiamata al costruttore con utentecorrente,
//			Controllore controlThread = new ControlloreAmministrazione();
			Controllore controlThread = new ControlloreAmministrazione(utenteCorrente);
			
			ThreadGestore tGestore = new ThreadGestore(controlThread);
			Thread ttgestore = new Thread(tGestore);
			ttgestore.start();
		
			ArrayList <Thread> fornitoriArrayThread = new ArrayList<Thread>();
			
			//creo Attori e loro Threads
			for (int i=0; i<2; i++) //2 e il nuemro di Threads cassieri che partiranno
			{
				ThreadFornitore tFornitore = new ThreadFornitore(controlThread);
				Thread ttfornitore = new Thread(tFornitore);
				fornitoriArrayThread.add(ttfornitore);				
			}
			//Partenza di tutti i Thread contemporaneamente
			for (int i=0; i<2; i++) 
			{
				fornitoriArrayThread.get(i).start();	
				//Thread.sleep(1); 
			}						
			
			ArrayList <Thread> cassieriArrayThread = new ArrayList<Thread>();
			//creo Attori e loro Threads
			for (int i=0; i<2; i++)  //2 e il nuemro di Threads cassieri che partiranno
			{
				ThreadCassiere tCassiere = new ThreadCassiere(controlThread);
				Thread ttcassiere = new Thread(tCassiere);
				cassieriArrayThread.add(ttcassiere);				
			}
			//Partenza di tutti i Thread contemporaneamente
			for (int i=0; i<2; i++)
			{
				cassieriArrayThread.get(i).start();	
				//Thread.sleep(1); 
			}				

		} 
		catch (PswException e)
		{
			JOptionPane.showMessageDialog(null, " Attenzione! Password Errata ", "Errore Threads", JOptionPane.ERROR_MESSAGE);
		} 
		catch (RuoloException e) 
		{
			JOptionPane.showMessageDialog(null, " ConfineLogin. LoginAA. RuoloException " + e.getMessage(), "Errore Ruolo Threads", JOptionPane.ERROR_MESSAGE);
		} 
		catch (SecurityException e)
		{
			JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. SecurityException. " + e.getMessage(), "Errore Threads - Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
		} 
		catch (IllegalArgumentException e) 
		{
			JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. IllegalArgumentException. " + e.getMessage(), "Errore Threads - Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
		} 
		catch (LoginException e) 
		{
			e.printStackTrace();
		}
	}
	
}


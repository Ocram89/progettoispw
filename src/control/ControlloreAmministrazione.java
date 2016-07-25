package control;

import java.io.Serializable;
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.Vector;
 
//my -----------
import util.*;
import exception.*;
import boundary.*;
import entity.*;

public class ControlloreAmministrazione extends ControlloreGestione implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
/*
	public ControlloreAmministrazione()
	{
		super();
		this.utenteCorrente = ControlloreLogin.getUtenteCorrente();
		new ConfineAministratoreAmministrazione(this, null);
	}
*/
	public Utente utenteCorrente;
	
	public ControlloreAmministrazione()
	{//Da qui passano i Thread NUOVA VERSIONE
		super();
		System.out.println("Thread in ControlloreAmministrazione");
		/*ControlloreErogazione.utenteOspite = tadmin;
		ControlloreDeposito.utenteOspite = tadmin;
		ControlloreGestione.utenteOspite = tadmin;*/
	}
	
	
	
	/*public ControlloreAmministrazione(Utente tadmin, String Thread)
	{//Da qui passano i Thread
		super(tadmin, "admin");
		System.out.println("Thread in ControlloreAmministrazione");
		ControlloreErogazione.utenteOspite = tadmin;
		ControlloreDeposito.utenteOspite = tadmin;
		ControlloreGestione.utenteOspite = tadmin;
	}*/

	public ControlloreAmministrazione(Utente utenteCorrente)  //Fa partire la Grafica (Confine Amministrazione!)
	{
		super(utenteCorrente, "admin");
		this.utenteCorrente = utenteCorrente;
		/*ControlloreErogazione.utenteOspite = utenteCorrente;
		ControlloreDeposito.utenteOspite = utenteCorrente;
		ControlloreGestione.utenteOspite = utenteCorrente;*/
		
		//System.out.println(utenteCorrente.getLogin());
		
		//new ConfineAministratoreAmministrazione(this, ControlloreLogin.getConfineLogin());
		new ConfineAministratoreAmministrazione(this);
		//Il passaggio del secondo parametro, cioè l'oggetto ConfineLogin serve per collegare i due frame!
	}

	
	public boolean chngPassword(String psw, String nuovaPsw) throws PswException
	{
		if (psw.equals(utenteCorrente.getPassword())) 
			{
			final Vector<Utente> utenti =	ControlloreLogin.getUtenti();
			Utente u = utenti.elementAt(utenti.indexOf(utenteCorrente));
			u.setPassword(nuovaPsw);	
			return true;
		}
		else throw new PswException("\nControllore Amministrazione chngPassword().  Non ammesso cambio di password.");
	}

	public void cancellaUtente(String login) throws LoginException
	{
		final Vector<Utente> utenti =	ControlloreLogin.getUtenti();
		if(utenti.size()>1) {
			boolean b=false;
			for (Utente u: utenti)
			{
				if (u.getLogin().equals(login))
				{
					b= utenti.remove(u);
					break;
				}
			}
			if (!b)  
				throw new LoginException ("\nControllore Amministrazione cancellaUtente(). Login errata.");
		}
		else
			throw new LoginException ("\nControllore Amministrazione cancellaUtente(). Utente unico. Non rimuovibile");		
	}
	

	public Utente creaNuovoUtenteThread(String ruoloUtente) throws PswException, LoginException, RuoloException
	{
		//Thread ----------------------------
		lock.lock();
		String ruolo = ruoloUtente;
		String nomeThread = Thread.currentThread().toString();
		
		char virgola;
		int a=0;
		do
		{
			virgola = nomeThread.charAt(a);
			a++;
		}
		while(virgola != ',');
		a--;
		String numeroThreadString = nomeThread.substring(14, a);
		
		String login = "T"+numeroThreadString+"-"+ruolo;
		
		String password = "12345678";
		/*Integer num = new Integer(1);
		final Vector<Utente> utenti = ControlloreLogin.getUtenti();
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
					login = "tFornitore"+num.toString();
					break;
				}
				if(!utenteTrovato) //Se non esiste l'utente cercato esci dal ciclo
				{
					loop = false;
				}		
			}
		}	*/
		Utente nuovoUtente = new Utente(login, ruolo, password);


		
		/*for (Utente u: utenti)
		{
			if (u.getLogin().equals(login))
				throw new LoginException ("\nControllore Amministrazione nuovoUtente(String).. Login gia presente.");
		}
		if (!CostantiRuolo.contains(ruolo))  
			throw new RuoloException ("\nControllore Amministrazione nuovoUtente(String). Ruolo errato.");*/
		final Vector<Utente> utenti = ControlloreLogin.getUtenti();
		utenti.add(nuovoUtente);
		lock.unlock();
		return nuovoUtente;
	}

	
	public void nuovoUtente(String login, String password, String ruolo) throws PswException, LoginException, RuoloException 
	{
		final Vector<Utente> utenti =	ControlloreLogin.getUtenti();
		for (Utente u: utenti)
		{
			if (u.getLogin().equals(login))
				throw new LoginException ("\nControllore Amministrazione nuovoUtente(String).. Login gia presente.");
		}
		
		if (!CostantiRuolo.contains(ruolo))  
			throw new RuoloException ("\nControllore Amministrazione nuovoUtente(Credenziali). Ruolo errato.");
		Utente nuovoUtente = new Utente(login, ruolo, password);
		utenti.add(nuovoUtente);
	}
	
	public void nuovoUtente(Credenziali credenziali, String ruolo) throws PswException, LoginException, RuoloException 
	{
		
		final Vector<Utente> utenti =	ControlloreLogin.getUtenti();
		for (Utente u: utenti)
		{
			if (u.getLogin().equals(credenziali.getLogin()))
				throw new LoginException ("\nControllore Amministrazione nuovoUtente(Credenziali). Login gia presente.");
		}
		if (!CostantiRuolo.contains(ruolo))  
			throw new RuoloException ("\nControllore Amministrazione nuovoUtente(Credenziali). Ruolo errato.");
		utenti.add(new Utente (credenziali, ruolo));
		
	}
	
	public void controllaPsw(String psw1, String psw2) throws PswException
	{
		//1.Controllo lunghezza psw
		int lunghezzaPsw = psw1.length();
		if(lunghezzaPsw<8)
		{
			throw new PswException("La Password deve essere di almeno 8 caratteri!");
		}
		//2. Controllo che la psw inizi con un carattere alfabetico	
		String primoCarattere = psw1.substring(0,1);
		if(CostantiNumeriche.isNumber(primoCarattere))
		{
			throw new PswException("La password deve iniziare con un carattere alfabetico!");
		}	
		
		//3. Controllo che ci sia almeno un numero
		boolean almenoUnNumero = false;
		String c = "";
		for(int i=1; i<lunghezzaPsw; i++) //Inizio dal secondo carattere
		{
			c = psw1.substring(i,i+1);
			if(CostantiNumeriche.isNumber(c)) // Se c è un numero
			{
				almenoUnNumero = true;
				break;
			}
		}
		if (!almenoUnNumero)
		{
			throw new PswException("La password deve contenere almeno un numero!");
		}
		
		//4. Controllo che le due psw siano uguali
		if(!(psw1.equals(psw2)))
		{
			throw new PswException("Inserite Password Differenti!");
		}		
	}
	
	/*public double successo(IDEsterno k) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		if (k==null)
		{
			throw new IDEsternoException("\nControllore Amministrazione successo(). L'identificatore non deve essere nullo\n");
		}
		if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
		}
		
		Portata p = mappaProdotti.get(k.toString()); 
		//Di quale classe è questo get? E' di MappaProdotti!! Non fa altro che richiedere all'HashMap il Tipo di Prodotto
		//corrispondente al IDEsterno, cioè se è Altro, oppure Acqua, ecc.
		
		//System.out.println("Id Esterno:" + (p.getIDEsterno().toString()));

		//System.out.println("La portata è: " + p.getClass().getName());
		double a = p.successo();
				
		System.out.println("successo è: " + a);
		
		return a;		
		
		
		//return mappaProdotti.get(k.toString()).successo();
		
		//mappaProdotti.get(idEsterno) ---> Portata
		//portata.successo() ---> restituisce un double
	}*/
	public double successo(IDEsterno k, String metodo) throws IDEsternoException, ProdottoException  
	{
		if (k==null)
		{
			throw new IDEsternoException("\nControllore Amministrazione successo(). L'identificatore non deve essere nullo\n");
		}
		
		//VERIFICO SE VOGLIO CALCOLARE L'INDICE DI UNA CLASSE ALIMENTARE o DI UN PRODOTTO
		if(CostantiClassiAlimentari.containsClasseAlimentare(k.toString()))
		{
			System.out.println("Sto calcolando l'indice di una delle classi alimentari");
			if(CostantiClassiAlimentari.containsClasseBase(k.toString()))
			{
				//***METODO A***
				if(metodo.equals("A"))
				{
					System.out.println("Metodo A");
					Double erogazioneTotaleClasse = new Double(0);
					Double richiesteTotaliClasse =  new Double(0);
					Set <String> tutteLePortate= keySet();
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo ClasseBase
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						if((oggettoPortata.getClass().getName()).equals(k.toString()))  //Se è un oggetto di Classe k
						{
							erogazioneTotaleClasse = erogazioneTotaleClasse + oggettoPortata.getErogate();
							richiesteTotaliClasse = richiesteTotaliClasse + oggettoPortata.getRichieste();
						}
					}
					if(richiesteTotaliClasse>0)
					{
						Double successo = new Double(erogazioneTotaleClasse/richiesteTotaliClasse);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return successo;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Successo!");				
				}
				//***METODO B***
				else if(metodo.equals("B"))
				{
					System.out.println("Metodo B");
					//Cerco se nella mappa c'è almeno un oggetto di tipo classeBase (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo ClasseBase
					{
						oggettoPortata = getMappaProdotti().get(s);
						if((oggettoPortata.getClass().getName()).equals(k.toString()))  //Se è un oggetto di Classe k
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.successo(k);
				}
			}
			if(CostantiClassiAlimentari.containsCategoria(k.toString()))
			{
				//***METODO A***
				if(metodo.equals("A"))
				{
					System.out.println("Metodo A");
					Double erogazioneTotaleCategorie = new Double(0);
					Double richiesteTotaliCategorie=  new Double(0);
					Set <String> tutteLePortate= keySet();
					int n=0;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						if(CalcolaSuperClasseDi(oggettoPortata).equals(k.toString()))
						{
							erogazioneTotaleCategorie = erogazioneTotaleCategorie + oggettoPortata.getErogate();
							richiesteTotaliCategorie = richiesteTotaliCategorie + oggettoPortata.getRichieste();
							n++;
						}					
					}
					System.out.println("ErogazioneTotaleCategorie: "+erogazioneTotaleCategorie);
					System.out.println("RichiesteTotaliCategorie: "+richiesteTotaliCategorie);
					System.out.println("Trovati "+n+" prodotti di classe"+k.toString());
					if(richiesteTotaliCategorie>0)
					{
						Double successo = new Double(erogazioneTotaleCategorie/richiesteTotaliCategorie);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return successo;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Successo!");				
				}
				//***METODO B***
				else
				{
					System.out.println("Metodo B");
					System.out.println("E' una categoria");
					//Cerco se nella mappa c'è almeno un oggetto di tipo Categoria (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria, cioè k
					{
						oggettoPortata = getMappaProdotti().get(s);
						if(CalcolaSuperClasseDi(oggettoPortata).equals(k.toString()))  //Se è un oggetto di Classe k, es. Bevanda, ecc.
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.successo(k);
				}
			}
			else	//Indice su classe Madre: Calcolo tutti gli oggetti!
			{
				//***METODO A***
				if(metodo.equals("A"))
				{
					System.out.println("Metodo A");
					if(!k.toString().equals("Portata")) 
					{
							System.out.println("Attenzione Indice Totale non di Portata!?!?!");
							return -1;
					}
					
					Double erogazioneComplessiva = new Double(0);
					Double richiesteComplessive=  new Double(0);
					Set <String> tutteLePortate= keySet();
					int n=0;
					for (String s : tutteLePortate)
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						
						erogazioneComplessiva = erogazioneComplessiva + oggettoPortata.getErogate();
						richiesteComplessive = richiesteComplessive + oggettoPortata.getRichieste();
						n++;			
					}
					System.out.println("Trovati "+n+" prodotti di classe "+k.toString());		
					if(richiesteComplessive>0)
					{
						Double successo = new Double(erogazioneComplessiva/richiesteComplessive);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return successo;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Successo!");				
				}
				//***METODO B***
				else
				{
					System.out.println("Metodo B");
					if(!k.toString().equals("Portata")) 
					{
							System.out.println("Attenzione Indice Totale non di Portata!?!?!");
							return -1;
					}
					
					//Cerco se nella mappa c'è almeno un oggetto di tipo Portata (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria, cioè k
					{
						oggettoPortata = getMappaProdotti().get(s);
						if(oggettoPortata!=null)  //Se c'è almeno un oggetto Portata
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.successo(k);
				}
			}
		}
		else  //è un Prodotto
		{
			if (mappaProdotti.get(k.toString())==null)
			{
				throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
			}
			
			Portata p = mappaProdotti.get(k.toString()); 
			double a = p.successo();
			//System.out.println("successo è: " + a);
			return a;	
		}
	}
	
		
	public double gradimento(IDEsterno k, String metodo) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		if (k==null)
		{
			throw new IDEsternoException("\nControllore Amministrazione gradimento(). L'identificatore non deve essere neullo\n");
		}
		//VERIFICO SE VOGLIO CALCOLARE L'INDICE DI UNA CLASSE ALIMENTARE o DI UN PRODOTTO
		if(CostantiClassiAlimentari.containsClasseAlimentare(k.toString()))
		{
			System.out.println("Sto calcolando l'indice di una delle classi alimentari");
			if(CostantiClassiAlimentari.containsClasseBase(k.toString()))
			{
				//***METODO A***
				if(metodo.equals("A"))
				{
					//Calcolo Formula: erogazione(classeBase) / erogazioneTotaleClassiConStessaPiùVicinaSuperClasse
					//Es: erogazione(Acqua)/Erogazione(Bevande)
					boolean superClasseDaTrovare = true;
					String superClasseDiClasseBase="";
					
					//Trovo NUMERATORE:			
					Double erogazioneTotaleClasse = new Double(0);
					Set <String> tutteLePortate= keySet();
					for (String s : tutteLePortate) 	
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						if((oggettoPortata.getClass().getName()).equals(k.toString()))  //Se è un oggetto di tipo k  (es.Acqua)
						{
							erogazioneTotaleClasse = erogazioneTotaleClasse + oggettoPortata.getErogate();
	
							if(superClasseDaTrovare) //Qui mi serve solo una volta, info che uso poco sotto!
							{
								superClasseDiClasseBase= CalcolaSuperClasseDi(oggettoPortata);  //Calcolo se è una Bevanda, o un Primo, ..ecc.
								superClasseDaTrovare = false;
							}
						}
					}
					
					//Trovo DENOMINATORE:
					Double erogazioneTotalesuperClasse =  new Double(0); //(cioè erogazioneCategoria)
					for (String s : tutteLePortate) 	
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						String superClassObj = CalcolaSuperClasseDi(oggettoPortata);  
						if(superClassObj.equals(superClasseDiClasseBase))  //Se l'oggetto ha la stessa superClasse di "classeBase"
						{
							erogazioneTotalesuperClasse = erogazioneTotalesuperClasse + oggettoPortata.getErogate();
						}
					}
					
					//Calcolo Formula:
					if(erogazioneTotalesuperClasse>0)
					{
						Double gradimentoClasse = new Double(erogazioneTotaleClasse/erogazioneTotalesuperClasse);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return gradimentoClasse;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Gradimento!");
				}
				//***METODO B***
				else if(metodo.equals("B"))
				{
					System.out.println("Metodo B");
					//Cerco se nella mappa c'è almeno un oggetto di tipo classeBase (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo ClasseBase
					{
						oggettoPortata = getMappaProdotti().get(s);
						if((oggettoPortata.getClass().getName()).equals(k.toString()))  //Se è un oggetto di Classe k
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.gradimento(k);				
				}				
			}
			
			if(CostantiClassiAlimentari.containsCategoria(k.toString())) //Es. calcolo indice di Bevanda 
			{
				//***METODO A***
				if(metodo.equals("A"))
				{			
					//Calcolo Formula: erogazione(categoria) / erogazioneComplessiva
					//Es: erogazione(Bevanda)/Erogazione(Portata)
					
					//Trovo NUMERATORE:
					Double erogazioneTotaleCategorie = new Double(0);
					
					Set <String> tutteLePortate= keySet();
					
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						if(CalcolaSuperClasseDi(oggettoPortata).equals(k.toString()))
						{
							erogazioneTotaleCategorie = erogazioneTotaleCategorie + oggettoPortata.getErogate();						
						}					
					}
					
					//Trovo DENOMINATORE:
					Double erogazioneComplessiva = new Double(0);
					for (String s : tutteLePortate)
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						erogazioneComplessiva = erogazioneComplessiva + oggettoPortata.getErogate();		
					}
					
					//Calcolo Formula:
					if(erogazioneComplessiva>0)
					{
						Double gradimento = new Double(erogazioneTotaleCategorie/erogazioneComplessiva);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return gradimento;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Successo!");	
			
				}
				//***METODO B***
				else
				{
					System.out.println("Metodo B");
					System.out.println("E' una categoria");
					//Cerco se nella mappa c'è almeno un oggetto di tipo Categoria (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria, cioè k
					{
						oggettoPortata = getMappaProdotti().get(s);
						if(CalcolaSuperClasseDi(oggettoPortata).equals(k.toString()))  //Se è un oggetto di Classe k, es. Bevanda, ecc.
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.gradimento(k);
				}
			}
			else	//Indice su classe Madre: Per il gradimento restituisce 1, cioè 100%
			{
				if(!k.toString().equals("Portata")) 
				{
						System.out.println("Attenzione Indice Totale non di Portata!?!?!");
						return -1;
				}
				Double gradimento = new Double(1);
				return gradimento;
			}
		}
		
		else  //è un Prodotto
		{
			if (mappaProdotti.get(k.toString())==null)
			{
				throw new ProdottoException("\nControllore Amministrazione gradimento(). Prodotti " + k.toString() + "  non sono presenti\n");
			}
			return mappaProdotti.get(k.toString()).gradimento();
		}		
	}
		
	public double gradimentoMedioGiornaliero(IDEsterno k) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		if (k==null)
		{
			throw new IDEsternoException("\nControllore Amministrazione gradimento(). L'identificatore non deve essere neullo\n");
		}
		if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("\nControllore Amministrazione gradimento(). Prodotti " + k.toString() + "  non sono presenti\n");
		}
		//System.out.println("IDESterno: "+k.toString() + " appartiene a "+ mappaProdotti.get(k.toString()));
		//Portata p = mappaProdotti.get(k.toString());
		//System.out.println("Creato il "+p.getGiornoCreazione() + " alle "+p.getOrarioCreazione());
		
		//Calcolo Formula: erogazioneMedia(k) / erogazioneMediaTotaleDiTuttiProdottiStessaClasseDiK
		Portata p = mappaProdotti.get(k.toString());
		Double erogazioneMediaDiK = new Double(p.erogazioneMediaGiornaliera());    //Trovo Numeratore
		
		String ClasseDiK = mappaProdotti.get(k.toString()).getClass().getName(); //Trovo Classe di K
		Double erogazioneMediaTotalePortateTipoK = new Double(0);
		Set <String> tutteLePortate= keySet();
		for (String s : tutteLePortate) 	//Trovo Denominatore
		{
			Portata oggettoPortata = getMappaProdotti().get(s);
			if((oggettoPortata.getClass().getName()).equals(ClasseDiK))  //Se è un oggetto della stessa Classe di k
			{
				erogazioneMediaTotalePortateTipoK = erogazioneMediaTotalePortateTipoK + oggettoPortata.erogazioneMediaGiornaliera();
			}
		}
		if(erogazioneMediaTotalePortateTipoK>0)
		{
			Double gradimentoMedioGiornaliero = new Double(erogazioneMediaDiK/erogazioneMediaTotalePortateTipoK);
			//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
			return gradimentoMedioGiornaliero;
		}
		else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Gradimento Medio!");
		
	}
	
	public double gradimentoMedioGiornalieroClasse(String classeBase) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		//Calcolo Formula: erogazioneMedia(classeBase) / erogazioneMediaTotaleClassiConStessaPiùVicinaSuperClasse
		//Es: erogazioneMedia(Acqua)/ErogazioneMedia(Bevande)
		boolean superClasseDaTrovare = true;
		String superClasseDiClasseBase="";
		//Trovo NUMERATORE:
		Double erogazioneMediaClasseBase = new Double(0);
		Set <String> tutteLePortate= keySet();
		for (String s : tutteLePortate) 	
		{
			Portata oggettoPortata = getMappaProdotti().get(s);
			if((oggettoPortata.getClass().getName()).equals(classeBase))  //Se è un oggetto di tipo classeBase
			{
				erogazioneMediaClasseBase = erogazioneMediaClasseBase + oggettoPortata.erogazioneMediaGiornaliera();

				if(superClasseDaTrovare) //Qui mi serve solo una volta, info che uso poco sotto!
				{
					superClasseDiClasseBase= CalcolaSuperClasseDi(oggettoPortata);  //Calcolo se è una Bevanda, o un Primo, ..ecc.
					superClasseDaTrovare = false;
				}
			}
		}
		
		//Trovo DENOMINATORE:
		Double erogazioneMediaSuperClasseDiClasseBase = new Double(0); //(cioè erogazioneCategoria)
		for (String s : tutteLePortate) 	
		{
			Portata oggettoPortata = getMappaProdotti().get(s);
			String superClassObj = CalcolaSuperClasseDi(oggettoPortata);  
			if(superClassObj.equals(superClasseDiClasseBase))  //Se l'oggetto ha la stessa superClasse di "classeBase"
			{
				erogazioneMediaSuperClasseDiClasseBase = erogazioneMediaSuperClasseDiClasseBase + oggettoPortata.erogazioneMediaGiornaliera();
			}
		}
		//Calcolo Formula:
		if(erogazioneMediaSuperClasseDiClasseBase>0)
		{
			Double gradimentoMedioGiornalieroClasse = new Double(erogazioneMediaClasseBase/erogazioneMediaSuperClasseDiClasseBase);
			//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
			return gradimentoMedioGiornalieroClasse;
		}
		else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Gradimento Medio!");
	}
	
	public String CalcolaSuperClasseDi(Portata p)
	{
		String risultato ="";
		if(p instanceof Bevanda) risultato =  "Bevanda";
		else if (p instanceof Primo) risultato =  "Primo";
		else if (p instanceof Secondo) risultato =  "Secondo";
		else if (p instanceof Dolce) risultato =  "Dolce";
		else if (p instanceof Frutta) risultato =  "Frutta";
		return risultato;
	}
	
	public double gradimentoMedioGiornalieroCategoria(String categoria) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		//Calcolo Formula: erogazioneMedia(categoria) / erogazioneMediaTotalediTUTTIiProdotti
				//Es: erogazioneMedia(Bevanda)/ErogazioneMedia(Portata)
		
		//Trovo NUMERATORE:
		Double erogazioneMediaCategoria = new Double(0);
		Set <String> tutteLePortate= keySet();
		for (String s : tutteLePortate) 	
		{
			Portata oggettoPortata = getMappaProdotti().get(s);
			String superClassObj = CalcolaSuperClasseDi(oggettoPortata);  
			if(superClassObj.equals(categoria))  //Se l'oggetto ha come superClasse  "categoria"
			{
				erogazioneMediaCategoria = erogazioneMediaCategoria + oggettoPortata.erogazioneMediaGiornaliera();
			}
		}
	
		//Trovo DENOMINATORE:
		Double erogazioneMediaTuttiProdotti = new Double(0);
		for (String s : tutteLePortate) 	
		{
			Portata oggettoPortata = getMappaProdotti().get(s);
			erogazioneMediaTuttiProdotti = erogazioneMediaTuttiProdotti + oggettoPortata.erogazioneMediaGiornaliera();
		}
		//Calcolo Formula
		if(erogazioneMediaTuttiProdotti>0)
		{
			Double gradimentoMedioGiornalieroCategoria = new Double(erogazioneMediaCategoria/erogazioneMediaTuttiProdotti);
			return gradimentoMedioGiornalieroCategoria;
		}
		else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora erogato/a. Impossibile calcolarne l'indice di Gradimento Medio!");
	}
	
	
	
	public double deperibilita(IDEsterno k, String metodo) throws DirittiException, ProdottoException, IDEsternoException, ArithmeticException
	{
		if (k==null)
		{
			throw new IDEsternoException("\nControllore Amministrazione deperibilita(). L'identificatore non deve essere nullo\n");
		}
		
		//VERIFICO SE VOGLIO CALCOLARE L'INDICE DI UNA CLASSE ALIMENTARE o DI UN PRODOTTO
		if(CostantiClassiAlimentari.containsClasseAlimentare(k.toString()))
		{
			System.out.println("Sto calcolando l'indice di una delle classi alimentari");
			if(CostantiClassiAlimentari.containsClasseBase(k.toString()))
			{
				//***METODO A***
				if(metodo.equals("A"))
				{
					Double scadutiTotaliClasse = new Double(0);
					Double depositatiTotaliClasse =  new Double(0);
					Set <String> tutteLePortate= keySet();
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo ClasseBase
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						if((oggettoPortata.getClass().getName()).equals(k.toString()))  //Se è un oggetto di Classe k
						{
							scadutiTotaliClasse = scadutiTotaliClasse + oggettoPortata.getScadute();
							depositatiTotaliClasse = depositatiTotaliClasse + oggettoPortata.getDepositate();
						}
					}
					if(depositatiTotaliClasse>0)
					{
						Double deperibilita = new Double(scadutiTotaliClasse/depositatiTotaliClasse);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return deperibilita;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora depositato. Impossibile calcolarne l'indice di Deperibilità!");	
				}
				//***METODO B***
				else if(metodo.equals("B"))
				{
					System.out.println("Metodo B");
					//Cerco se nella mappa c'è almeno un oggetto di tipo classeBase (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo ClasseBase
					{
						oggettoPortata = getMappaProdotti().get(s);
						if((oggettoPortata.getClass().getName()).equals(k.toString()))  //Se è un oggetto di Classe k
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.deperibilita(k);				
				}	
			}
			if(CostantiClassiAlimentari.containsCategoria(k.toString()))
			{
				//***METODO A***
				if(metodo.equals("A"))
				{	
					Double scadutiTotaliCategorie = new Double(0);
					Double depositatiTotaliCategorie=  new Double(0);
					Set <String> tutteLePortate= keySet();
					int n=0;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						if(CalcolaSuperClasseDi(oggettoPortata).equals(k.toString()))
						{
							scadutiTotaliCategorie = scadutiTotaliCategorie + oggettoPortata.getScadute();
							depositatiTotaliCategorie = depositatiTotaliCategorie + oggettoPortata.getDepositate();
							n++;
						}					
					}
					/*System.out.println("ErogazioneTotaleCategorie: "+erogazioneTotaleCategorie);
					System.out.println("RichiesteTotaliCategorie: "+richiesteTotaliCategorie);*/
					System.out.println("Trovati "+n+" prodotti di classe"+k.toString());
					if(depositatiTotaliCategorie>0)
					{
						Double deperibilita = new Double(scadutiTotaliCategorie/depositatiTotaliCategorie);
						return deperibilita;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora depositato. Impossibile calcolarne l'indice di Deperibilità!");				
				}
				//***METODO B***
				else
				{
					System.out.println("Metodo B");
					System.out.println("E' una categoria");
					//Cerco se nella mappa c'è almeno un oggetto di tipo Categoria (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria, cioè k
					{
						oggettoPortata = getMappaProdotti().get(s);
						if(CalcolaSuperClasseDi(oggettoPortata).equals(k.toString()))  //Se è un oggetto di Classe k, es. Bevanda, ecc.
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.deperibilita(k);
				}
			
			}
			else	//Indice su classe Madre: Calcolo tutti gli oggetti!
			{
				//***METODO A***
				if(metodo.equals("A"))
				{
					if(!k.toString().equals("Portata")) 
					{
							System.out.println("Attenzione Indice Totale non di Portata!?!?!");
							return -1;
					}
					
					Double scadutiComplessivi = new Double(0);
					Double depositatiComplessivi=  new Double(0);
					Set <String> tutteLePortate= keySet();
					int n=0;
					for (String s : tutteLePortate)
					{
						Portata oggettoPortata = getMappaProdotti().get(s);
						
						scadutiComplessivi = scadutiComplessivi + oggettoPortata.getScadute();
						depositatiComplessivi = depositatiComplessivi + oggettoPortata.getDepositate();
						n++;			
					}
					System.out.println("Trovati "+n+" prodotti di classe "+k.toString());		
					if(depositatiComplessivi>0)
					{
						Double deperibilita = new Double(scadutiComplessivi/depositatiComplessivi);
						//return mappaProdotti.get(k.toString()).gradimentoMedioGiornaliero(gradimentoMedioGiornaliero);
						return deperibilita;
					}
					else throw new ArithmeticException ("Al momento nessun prodotto di questo tipo risulta ancora depositato. Impossibile calcolarne l'indice di Deperibilità!");				
				}
				//***METODO B***
				else
				{
					System.out.println("Metodo B");
					if(!k.toString().equals("Portata")) 
					{
							System.out.println("Attenzione Indice Totale non di Portata!?!?!");
							return -1;
					}
					
					//Cerco se nella mappa c'è almeno un oggetto di tipo Portata (cioè k)
					Set <String> tutteLePortate= keySet();
					Portata oggettoPortata=null;
					for (String s : tutteLePortate) 	//Individuo oggetti di Tipo Categoria, cioè k
					{
						oggettoPortata = getMappaProdotti().get(s);
						if(oggettoPortata!=null)  //Se c'è almeno un oggetto Portata
						{
							break;
						}
						else oggettoPortata=null;
					}
					if (oggettoPortata == null) throw new ProdottoException("\nControllore Amministrazione successo(). Prodotti " + k.toString() + "  non sono presenti\n");
					else return oggettoPortata.deperibilita(k);
				}
				
			}			
		}
		else  //è un Prodotto
		{
			if (mappaProdotti.get(k.toString())==null)
			{
				throw new ProdottoException("\nControllore Amministrazione deperibilita(). Prodotti " + k.toString() + "  non sono presenti\n");
			}
			return mappaProdotti.get(k.toString()).deperibilita();
		}		
	}
	
	/*public double deperibilitaClasse(String nomeClasse) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException, RuoloException 
	{
		Portata p;
		Class<?> nomeClasseObj = Class.forName(nomeClasse); //Creo oggetto "nomeClasse"
		
		@SuppressWarnings("rawtypes")
		Constructor c=null;
		c= nomeClasseObj.getConstructor();
		
		p = (Portata) c.newInstance();
		return p.deperibilitaClasse();
	}*/
	public void clear() 
	{
		mappaProdotti.clear();
	}
	
	/*public void comunicaUtenteThread(Utente thread)
	{
		utenteCorrente = thread;
	}*/
	
	public void exit() throws PswException 
	{
		ControlloreLogin.gestioneLogin();
	}
	
}

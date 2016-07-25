package entity;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

// my -----------
import util.*;
import exception.*;


public class Acqua extends Bevanda implements Serializable
{	
	//Variabili di classe
	private static int tErogate=0;
	private static int tRichieste=0;
	private static int tScadute=0;
	private static int tDepositate=0;
	protected static final long	serialVersionUID	= 1L;   

	
	private Orologio dataCreazione;
	double prezzoAcquisto;
	double prezzoVendita;
	
	//Variabili d'istanza
	private boolean frizzante=false;
	
	private static HashMap<String, Integer> saveVarStatic = new HashMap<String, Integer>();
	
	/*
	public Acqua(IDEsterno t, Orologio dataCreazione)
	{
		super(t);
		
		this.dataCreazione = dataCreazione;
	}*/
	
	public  Acqua(IDEsterno t, Orologio dataCreazione, double pA, double pV) // Usata Per Creazioni prodotto con Prezzo A/V
	{
		super(t);
		this.dataCreazione = dataCreazione;
		this.prezzoAcquisto = pA;
		this.prezzoVendita = pV;
	}
	
	public Acqua() //Usato per creare un semplice oggetto che calcola indici
	{
		super();
	}
	
	
	/*public Acqua(IDEsterno t, boolean frizzante)
	{
		super(t);
		this.frizzante = frizzante;
	}*/
	
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
		//------------------------------------
	
	@SuppressWarnings("unchecked")
	public static boolean caricaVarStatiche() throws DeserializzazioneException
	{
		if (!new File("Save/Acqua.dat").exists()) 
		{
			//return false;
			throw new DeserializzazioneException("File Acqua ancora non disponibile!");
		}
				
		DeserializzaOggetti load = new DeserializzaOggetti("Save/Acqua.dat");
		try 
		{
			saveVarStatic =  (HashMap<String, Integer>) load.deserializza();
		} 
		catch (DeserializzazioneException e) 
		{
			JOptionPane.showMessageDialog(null, "File da Deserializzare non ancora compatibile. Procedere al salvataggio");
		}
	
		tDepositate = saveVarStatic.get("tDepositate");
		tBevandeDepositate += tDepositate;
		tPortateDepositate += tDepositate;    //PerchË ogni classe "base" deserializza
		
		tErogate = saveVarStatic.get("tErogate");
		tBevandeErogate += tErogate;
		tPortateErogate += tErogate;
		
		tRichieste = saveVarStatic.get("tRichieste");
		tBevandeRichieste += tRichieste;
		tPortateRichieste += tRichieste;
		
		tScadute = saveVarStatic.get("tScadute");
		tBevandeScadute += tScadute;
		tPortateScadute += tScadute;
		
		return true;
	}
	
	public static void salvaVarStatiche() throws SerializzazioneException 
	{
		//Inserisco Var Statiche nella Mappa
		if(saveVarStatic.containsKey("tDepositate"))  //Se gi‡† esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave gi‡† esistente....aggiorno...");
			saveVarStatic.remove("tDepositate");
		}		
		saveVarStatic.put("tDepositate", new Integer(tDepositate));
		
		if(saveVarStatic.containsKey("tErogate"))  //Se gi‡† esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave gi‡† esistente....aggiorno...");
			saveVarStatic.remove("tErogate");
		}		
		saveVarStatic.put("tErogate", new Integer(tErogate));
		
		if(saveVarStatic.containsKey("tRichieste"))  //Se gi‡† esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave gi‡† esistente....aggiorno...");
			saveVarStatic.remove("tRichieste");
		}		
		saveVarStatic.put("tRichieste", new Integer(tRichieste));
		
		if(saveVarStatic.containsKey("tScadute"))  //Se gi‡† esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave gi‡† esistente....aggiorno...");
			saveVarStatic.remove("tScadute");
		}		
		saveVarStatic.put("tScadute", new Integer(tScadute));
		
		
		//Salvo la Mappa
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/Acqua.dat";
		SerializzaOggetti save = new SerializzaOggetti(path);
		save.serializza(saveVarStatic);		
	}	
	
	//Metodo che calcola una data casuale tra quelle presenti nella mappa
	
	public Orologio prelevaUnaData() throws GiacenzaInsufficienteException
	{
		if(MappaDataScadenza.isEmpty())
		{	
			throw new GiacenzaInsufficienteException("Attenzione: Non ci sono prodotti di tipo "+this.getClass().getName());
		}
		
		Integer data;
		do
		{
			Orologio o = new Orologio();
			o = o.dataCasuale();
			data = o.FromOrologioToIntAmericano();
		}
		while(!contains(data));
		
		return Funzioni.FromIntegerToOrologio(data);
		
	}
	
	private boolean contains(Integer data)
	{
		Set <Integer> ChiaviMappaScadenze = MappaDataScadenza.keySet();
		for (Integer n : ChiaviMappaScadenze )
		{
			if (data.equals(n))
			{
				return true;
			}
		}
		return false;
	}
		
	public void deposita(int data, int quantita)
	{
		Orologio dataAttuale = new Orologio();
		int dataAttualeAmericana = dataAttuale.FromOrologioToIntAmericano();
		verificaSeNuovoGiorno(dataAttualeAmericana, dataMemorizzata);
		
		Integer dataInt = new Integer(data);
		//Integer quantitaInt = new Integer(quantita);
		
		if(!MappaDataScadenza.containsKey(dataInt))	//Inserisci nuova data
		{	
			MappaDataScadenza.put(dataInt, new InfoProdotto(quantita)); 
		}
		else	//Aggiorna stato giacenza, data esistente
		{
			//Prelevo infoProdotto, lo aggiorno, e lo reinserisco
			InfoProdotto infoProdotto = MappaDataScadenza.get(dataInt);
			infoProdotto.addGiacenza(quantita);
			//Integer giacenzaDidata = MappaDataScadenza.get(dataInt).getGiacenza();
			//quantitaInt += giacenzaDidata; //Serve alla mappa
			MappaDataScadenza.remove(dataInt);
			MappaDataScadenza.put(dataInt, infoProdotto);
		}
		
		
		if (data < dataAttualeAmericana)
		{
			nGiacenzaScaduti += quantita;
		}
		
		tDepositate+=quantita;  //Prodotti Depositati di Tipo "Altro"
		super.deposita(quantita);
	}
	
	public String stampaMapScadenza()
	{
		String stampa="";
		//String dataFormatoAmericano="";
		//String giorno, mese, anno;
		if(MappaDataScadenza.isEmpty())
		{	
			stampa = "Lista MappaScadenze vuota!";
		}
		else
		{
			Set <Integer> ChiaviMappaScadenze = MappaDataScadenza.keySet();
			for (Integer n : ChiaviMappaScadenze )
			{
				String data = Funzioni.parsingDataAmericana(n);
			
				//System.out.println("Data: "+giorno+mese+anno);
				stampa = stampa + data+" \t " +new Integer(MappaDataScadenza.get(n).getGiacenza()).toString()+ "  ("+new Integer(MappaDataScadenza.get(n).getSegnalati()).toString()+")\n";
				//stampa = stampa + "Data Scadenza: "+ n.toString()+" -- "+ "Quantit√†: "+MappaDataScadenza.get(n).toString()+"\n";
				
			}
			stampa = stampa +"\nTotale Scad. Prodotto: "+nGiacenzaScaduti;
			//+"\nDataMemorizzata: "+dataMemorizzata;
		}
		return stampa;
		
	}
	
	/*public void deposita(int quantita) // throws QuantitaException
	{
	    //Sostituire parametri con istanza di Movimento
		tDepositate+=quantita;  //Prodotti Depositati di Tipo "Altro"
		super.deposita(quantita);
	}*/
	
	public void erogaLB(int quantita, Utente utenteCorrente, String dataAm) throws GiacenzaInsufficienteException, LineUnavailableException
	{		
		//boolean ricalcola = false;
		//boolean aggiornaRichieste = false;
		int daErogare = quantita;
		Orologio dataCasuale = prelevaUnaData();
		Orologio dataAttuale = new Orologio();
		verificaSeNuovoGiorno(dataAttuale.FromOrologioToIntAmericano(), dataMemorizzata);
		int i = Funzioni.calcolaDiffGiorni(dataAttuale, dataCasuale);
		if (i>=0)    //PRODOTTO BUONO
		{
			System.out.println("Trovata Data di Prodotto Buono: "+ new SimpleDateFormat("dd/MM/yyyy").format(dataCasuale.getData().getTime()));
			Set <Integer> ChiaviMappaScadenze = MappaDataScadenza.keySet();
			int dataCasualeAmericana = dataCasuale.FromOrologioToIntAmericano();
		
			for (Integer n : ChiaviMappaScadenze)
			{
				if(dataCasualeAmericana==n)   //Cerca la data nella mappa su cui lavorare
				{
					int giacenzaQuestaChiave = MappaDataScadenza.get(n).getGiacenza();
					if (daErogare == giacenzaQuestaChiave) //Se Ë l'ultimo prodotto...cancella data
					{
						MappaDataScadenza.remove(n);
					}
					else if(daErogare > giacenzaQuestaChiave)
					{
						daErogare = giacenzaQuestaChiave;
						MappaDataScadenza.remove(n);
					}
					else 
					{
						InfoProdotto infoProdotto = MappaDataScadenza.get(n);
						infoProdotto.sottraiGiacenza(quantita);
						//int nuovaGiacenza = giacenzaQuestaChiave - daErogare;
						MappaDataScadenza.remove(n);
						MappaDataScadenza.put(n, infoProdotto);
					}	
					break;  //deve uscire dal ciclo, perchË ormai ha trovato la data, (e poi cmq sono state fatte modifiche alla mappa e il ciclo non funziona pi√π bene)
				}
				//else System.out.println("Qui Non entrer√† mai!");
			}
			Tone.sound(800,300);
			
			tRichieste += quantita;		
			tErogate += daErogare;
			super.eroga(daErogare);
			LogProdotti log = new LogProdotti();
			log.logErogazione(utenteCorrente, this.iDEsterno.toString(), daErogare, this.giacenza);
		}
		else  //PRODOTTO SCADUTO
		{
			
			System.out.println("Trovata Data di Prodotto Scaduto: "+ new SimpleDateFormat("dd/MM/yyyy").format(dataCasuale.getData().getTime()));
			Set <Integer> ChiaviMappaScadenze = MappaDataScadenza.keySet();
			
			//calcola quanti sono gli scaduti segnalati
			int tScadutiSegnalati= 0;
			for (Integer n : ChiaviMappaScadenze)
			{
				InfoProdotto infoProdotto = MappaDataScadenza.get(n);
				tScadutiSegnalati += infoProdotto.getSegnalati();
			}
			
			if(tScadutiSegnalati == nGiacenzaScaduti)  //Se tutti i prodotti scaduti sono stati tutti segnalati
			{
				//Vedo se ci sono prodotti buoni in magazzino
				boolean TrovatiProdottiBuoni = false;
				Set <Integer> ChiaviMappaScadenze2 = MappaDataScadenza.keySet();
				for (Integer n : ChiaviMappaScadenze2)
				{
					if(dataAttuale.FromOrologioToIntAmericano()<=n)
					{
						TrovatiProdottiBuoni = true;
					}
				}
				
				if(TrovatiProdottiBuoni)
				{
					erogaLB(quantita, utenteCorrente, dataAm);
				}
				else
				{
					erogaCmqOAggiornaRich(quantita, 0, 1); //Aggiorna solo le richieste.
					throw new GiacenzaInsufficienteException("Impossibile trovare altri prodotti buoni nel magazzino");
				}
			}
			else	//Caso in cui ho un prodotto scaduto che devo SEGNALARE
			{
				int risp = 0; //Vuol dire SI (tacito), cerca un altro prodotto. Caso in cui si Ë trovato una confezione appartenente ad un lotto completamente segnalato.
				Set <Integer> ChiaviMappaScadenze2 = MappaDataScadenza.keySet();
				int dataCasualeAmericana = dataCasuale.FromOrologioToIntAmericano();
				for (Integer n1 : ChiaviMappaScadenze2)
				{
					if(dataCasualeAmericana==n1)   //Cerca nella mappa la data "scaduta", per SEGNALARE la confezione
					{
						InfoProdotto infoProdotto = MappaDataScadenza.get(n1);
						
						if (infoProdotto.getSegnalati() < infoProdotto.getGiacenza())
						{
							infoProdotto.addSegnalati(quantita);
							MappaDataScadenza.remove(n1);
							MappaDataScadenza.put(n1, infoProdotto);
							Tone.sound(800,800);
							//JOptionPane.showMessageDialog(null, "Attenzione! Prodotto Scaduto il "+ new SimpleDateFormat("dd/MM/yyyy").format(dataCasuale.getData().getTime())+". Verr√† segnalato!\n Cerchiamo un'altra confezione..." );
							//JOptionPane.showMessageDialog(null, "Cerchiamo un'altra confezione..." );
							risp = JOptionPane.showConfirmDialog(null, "Prodotto Scaduto il "+ new SimpleDateFormat("dd/MM/yyyy").format(dataCasuale.getData().getTime())+". Verr√† segnalato!\nCerchiamo un'altra confezione? Premendo NO, la richiesta del cliente non sar√† soddisfatta!" , "Attenzione: PRODOTTO SCADUTO!", JOptionPane.YES_NO_OPTION);
							
						}
						/*else  //caso in cui infoPsegnalati == infoPgiacenza
						{
							//In questo caso deve ricalcolare automaticamente
							ricalcola= true;
							//JOptionPane.showMessageDialog(null, "Attenzione segnalati > giacenza. Cambio in segnalati = giacenza!");
							//infoProdotto.maxSegnalati(); //segnalati = giacenza!
						}*/
						break;  //deve ormai uscire dal ciclo
					}
				}//fine for
								
				//RICALCOLA ErogaLB se ho trovato un prodotto scaduto, e il numero di scaduti non Ë uguale al numero di segnalati
				
				if (risp == JOptionPane.YES_OPTION)
				{
					System.out.println("Cerchiamo altro prodotto!");
					erogaLB(quantita, utenteCorrente, dataAm);
				}
				else if (risp == JOptionPane.NO_OPTION)
				{
					System.out.println("Richiesta non soddisfatta!");
					tRichieste += quantita;	
					super.erogaCmqOAggiornaRich(quantita, 0, risp); //Poniamo a 0 daErogare per ricordarci che non deve erogare nulla. Cmq non Ë considerato.
				}
				else JOptionPane.showMessageDialog(null, "La richiesta del cliente non Ë mai stata effettuata." );
				
				/*if(ricalcola) erogaLB(quantita);
				else
				{
					erogaCmqOAggiornaRich(quantita, 0, 1); //No, forse Ë sbagliato!...non conviene aggiornare le richieste, ma Ë meglio permettere al cassiere di tentare di trovare prodotti buoni.
					Tone.sound(800,800);
					JOptionPane.showMessageDialog(null, "Attenzione! Prodotto Scaduto il "+ new SimpleDateFormat("dd/MM/yyyy").format(dataCasuale.getData().getTime())+". Verr√† segnalato!" );
				}*/
			}
			
		}
	}
	
	public void eroga(int quantita) //throws GiacenzaInsufficienteException
	{	
		int dataAttuale = calcolaDataAttuale();
		
		int daErogare = quantita;
		
		if(MappaDataScadenza.isEmpty())
		{	//Da Gestire con Eccezioni!
			System.out.println("MappaScadenza vuota! Impossibile Erogare!");
		}
		else
		{							//INDIVIDUO PRODOTTI BUONI E LAVORO SU ESSI
			boolean loop = true;
			while(loop)
			{
				Set <Integer> ChiaviMappaScadenze = MappaDataScadenza.keySet();
				for (Integer n : ChiaviMappaScadenze)
				{
					if(dataAttuale<=n) //"=" perchË da includere anche i prodotti di oggi (che non sono considerati scaduti)
					{
						int giacenzaQuestaChiave = MappaDataScadenza.get(n).getGiacenza();
						if (daErogare >= giacenzaQuestaChiave)
						{
							daErogare = daErogare - giacenzaQuestaChiave;
							MappaDataScadenza.remove(n);
							//System.out.println("Data cancellata");
							if (daErogare ==0) loop = false;
							break;
						}
						else
						{
							int nuovaGiacenza = giacenzaQuestaChiave - daErogare;
							InfoProdotto infoProdotto = MappaDataScadenza.get(n);
							infoProdotto.nuovaGiacenza(nuovaGiacenza);
							MappaDataScadenza.remove(n);
							MappaDataScadenza.put(n, infoProdotto);
							//System.out.println("Data non cancellata");
							loop = false;
							break;
						}							
					}//niente else qui, perchË gli erogabili hanno data uguale o pi√π grande della data di oggi			
				}//fine for	
			}//fine while
			
		}
		
		//System.out.println("MappaScadenze: \n"+stampaMapScadenza());
		
		tRichieste += quantita;		
		tErogate += quantita;
		//System.out.println("Giacenza: " +giacenza + ", Erogate: "+ nErogati +", TotaliALTROErogate: "+tErogate+ ", Richieste: "+ nRichiesti +", Depositate: "+nDepositati+" ,TotaliDepositate: "+tDepositate);

		super.eroga(quantita); 
	}
	
	
	public void erogaCmqOAggiornaRich(int quantita, int daErogare, int risp)
	{	
		//int daErogare = 0;
		if (risp==JOptionPane.YES_OPTION) //Se SI allora risp = 0!
		{
			//System.out.println("Risposta SI uguale a int: "+risp);
			int dataAttuale = calcolaDataAttuale();
				
			//int quantiProdottiBuoni = 0;
			
			
			if(MappaDataScadenza.isEmpty())
			{	//Da Gestire con Eccezioni!
				System.out.println("MappaScadenza vuota! Impossibile Ritirare Scaduti!");
			}
			else
			{				
				//"Cancello" tutte le date di Prodotti Buoni
				boolean loop = true;
				while(loop)
				{
					loop = false; //intanto lo imposto a false, cos√¨ se il for non trova nessuna data "buona", si esce dal ciclo while!
					if(MappaDataScadenza.isEmpty()) //questo perchË nel ciclo, la mappa viene modificata, cancellando delle date.
					{	
						System.out.println("MappaScadenza ora Ë vuota! Non ci sono pi√π date di prodotti erogabili (buoni)!");
						loop=false;
					}
					else
					{
						Set <Integer> ChiaviMappaScadenze2 = MappaDataScadenza.keySet();
						for (Integer n : ChiaviMappaScadenze2)
						{
							if(dataAttuale<=n) //Individua ed eroga prodotti buoni
							{
								loop = true;
								MappaDataScadenza.remove(n);
								//System.out.println("Data di prodotti Buoni cancellata");
								break;
							}							
						}//fine for
					}
				}//fine while
				
			}
			//System.out.println("MappaScadenze: \n"+stampaMapScadenza());
			tRichieste += quantita;		
			//tErogate += giacenza; Sbagliato!!
			tErogate += daErogare;    //E' il num max di prodotti erogabili! Questo Ë il caso in cui chiedo pi√π di quanto c'Ë in giacenza!
			
		}
		else //se la risposta Ë NO, aggiorna solo le richieste, e risp=1!
		{
			
			//System.out.println("Risposta NO uguale a int: "+risp);
			tRichieste += quantita;
			//System.out.println(", TotaliALTROErogate: "+tErogate);
		}
		//super.erogaCmqOAggiornaRich(quantita, risp); 
		super.erogaCmqOAggiornaRich(quantita, daErogare, risp); 
	}

	
	public void ritiraScaduti(int quantita, String dataAmericana) throws GiacenzaInsufficienteException, ScadutiNonPresentiException, DataNonTrovataException
	{
		int dataAttuale = calcolaDataAttuale();
		verificaSeNuovoGiorno(dataAttuale, dataMemorizzata);
		int ritiroScaduti = quantita;
		//int quantoRitirare = ritiroScaduti;
		
		
			boolean loop = true;
			while(loop)
			{
				if(MappaDataScadenza.isEmpty())
				{	//Non serve qui sollevare eccezione, perchË Ë il caso in cui la mappa pu√≤ diventare vuota in seguito ad un ritiro scaduti
					//e quindi non appena Ë vuota, esce dal ciclo.
					System.out.println("MappaScadenza vuota! Impossibile Ritirare Scaduti!");
					loop=false;
				}
				else if(dataAmericana != null) //Se Ë stata inserita una data
				{
					boolean dataTrovata = false;
					Set <Integer> ChiaviMappaScadenze2 = MappaDataScadenza.keySet();
					for (Integer n : ChiaviMappaScadenze2)
					{
						if(dataAmericana.equals(n.toString())) //Rimuove solo prodotti con Data scelta
						{
							if(dataAttuale>n) //Rimuove solo prodotti con Data precedente a quella Attuale
							{
								dataTrovata = true;
								//Qui devo ritirare in base alla data scelta
								System.out.println("Data esistente: "+ dataAmericana);
								int giacenzaQuestaChiave = MappaDataScadenza.get(n).getGiacenza();
								if (ritiroScaduti > giacenzaQuestaChiave)
								{
									MappaDataScadenza.remove(n);
									JOptionPane.showMessageDialog(null, "E' stata inserita una quantit√† maggiore della giacenza del lotto considerato", "Informazione", JOptionPane.ERROR_MESSAGE);
									JOptionPane.showMessageDialog(null, "Il sistema ha rimosso l'intero lotto.", "Informazione", JOptionPane.ERROR_MESSAGE);
									
									quantita = giacenzaQuestaChiave; //serve per aggiornare nGiacenzaScaduti
									//System.out.println("Data cancellata");
									loop = false;
									break;
								}
								else if (ritiroScaduti == giacenzaQuestaChiave)
								{
									MappaDataScadenza.remove(n);
									//System.out.println("Data cancellata");
									loop = false;
									break;
								}
								else
								{
									int nuovaGiacenza = giacenzaQuestaChiave - ritiroScaduti;
									InfoProdotto infoProdotto = MappaDataScadenza.get(n);
									infoProdotto.nuovaGiacenza(nuovaGiacenza);
									
									if (ritiroScaduti>=infoProdotto.getSegnalati())
									{
										infoProdotto.azzeraSegnalati();
									}
									else 
									{
										infoProdotto.sottraiSegnalati(ritiroScaduti);
									}
									
									MappaDataScadenza.remove(n);
									MappaDataScadenza.put(n, infoProdotto);
									
									
									//System.out.println("Data non cancellata");
									loop = false;
									break;
								}
							}
							else
							{
								throw new DataNonTrovataException("Attenzione: Non Ë consentito rimuovere un prodotto non scaduto!");
							}
						}
					}
					if(!dataTrovata)
					{
						throw new DataNonTrovataException("Attenzione: E' stata inserita una Data di Scadenza non presente tra i lotti in magazzino");
					}
					
				}
				else  //Se non Ë stata inserita alcuna data
				{
					Set <Integer> ChiaviMappaScadenze2 = MappaDataScadenza.keySet();
					for (Integer n : ChiaviMappaScadenze2)
					{
						if(dataAttuale>n) //Rimuove solo prodotti con Data precedente a quella Attuale
						{
						
							int giacenzaQuestaChiave = MappaDataScadenza.get(n).getGiacenza();
							if (ritiroScaduti >= giacenzaQuestaChiave)
							{
								ritiroScaduti = ritiroScaduti - giacenzaQuestaChiave;
								MappaDataScadenza.remove(n);
								//System.out.println("Data cancellata");
								break;
							}
							else
							{
								int nuovaGiacenza = giacenzaQuestaChiave - ritiroScaduti;
								InfoProdotto infoProdotto = MappaDataScadenza.get(n);
								infoProdotto.nuovaGiacenza(nuovaGiacenza);
								
								if (ritiroScaduti>=infoProdotto.getSegnalati())
								{
									infoProdotto.azzeraSegnalati();
								}
								else 
								{
									infoProdotto.sottraiSegnalati(ritiroScaduti);
								}
								
								MappaDataScadenza.remove(n);
								MappaDataScadenza.put(n, infoProdotto);
								
								
								//System.out.println("Data non cancellata");
								loop = false;
								break;
							}					
						}
						else
						{
							loop=false;
							//System.out.println("Fine");
							break;
						}
					}//fine for
				}
			}//fine while		
		
			nGiacenzaScaduti -= quantita;
			
			tScadute+=quantita;  // ***Errore corretto: "+="
			super.ritiraScaduti(quantita, null);
	}
	
	private void verificaSeNuovoGiorno(int dataAttuale, int dataMemorizzata)
	{
		if(dataAttuale > dataMemorizzata)
		{
			System.out.println("NuovoGiorno!!");
			this.dataMemorizzata = dataAttuale;
			nGiacenzaScaduti = 0;
			Set <Integer> ChiaviMappaScadenze = MappaDataScadenza.keySet();
			for (Integer n : ChiaviMappaScadenze )
			{
				if(dataAttuale>n)
				{
					nGiacenzaScaduti += MappaDataScadenza.get(n).getGiacenza();
				}
			}
		}
		
	}
	
	public double successo() throws ArithmeticException
	{
		System.out.println("Successo Acqua");
		if (nRichiesti > 0) return (double) nErogati/(double) nRichiesti;
    	throw new ArithmeticException("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora richiesto/a. Impossibile calcolarne indice di successo!");
	}
		
	public double gradimento() throws ArithmeticException
	{
		//System.out.println("Gradimento: nErogati/tErogate: "+nErogati+"/"+tErogate);
		if (tErogate > 0) return (double) nErogati/(double) tErogate;
		
        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora erogato/a. Impossibile calcolarne indice di gradimento!");
	}
		
	public double erogazioneMediaGiornaliera()
	{
		Orologio attuale = new Orologio();
		double differenzaGiorni = Funzioni.calcolaDiffGiorni(dataCreazione, attuale);
		if(differenzaGiorni ==0)
		{
			differenzaGiorni=1;
		}
		//System.out.println("Sono passati "+ differenzaGiorni+" giorni");
		double nErogatiMediaGiornaliera = nErogati/differenzaGiorni;
		return  nErogatiMediaGiornaliera;
	}
	
	/*public double preferenza() throws ArithmeticException
	{
		if (tRichieste > 0) return (double) nRichiesti/(double) tRichieste;
        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora erogato/a. Impossibile calcolarne indice di gradimento!");
	}*/
	
	public double deperibilita() throws ArithmeticException
	{
		if (nDepositati > 0) return (double) nScaduti/(double) nDepositati;
        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora depositato/a perchÔøΩ scaduto. Impossibile calcolarne indice di deperibilitÔøΩ!");
	}

	/*public double deperibilitaRelativa() throws ArithmeticException
	{
		if (tScadute > 0) return (double) nScaduti/(double) tScadute;
        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora rititato perchÔøΩ scaduto. Impossibile calcolarne indice di deperibilitÔøΩ!");
	}*/
	
	
	//METODO (b) Calcolo Indici Gerarchia classi Dominio
			public double successo(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals(this.iDEsterno.toString())) 
					return successo();
				else if(c.toString().equals(this.getClass().getName()))
				{
					System.out.println("Calcolo successoClasse Acqua...");
					if (tRichieste > 0) return (double) tErogate/(double) tRichieste;
					throw new ArithmeticException("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora richiesto/a. Impossibile calcolarne indice di successo di Classe!");
				}
				else return super.successo(c); //Se "c" non Ë ne un prodotto e ne ClasseBase, vai al metodo superiore
			}
			
			public double gradimento(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals(this.iDEsterno.toString()))
				{
					return gradimento();
				}
				else if(c.toString().equals(this.getClass().getName()))
				{
					System.out.println("Calcolo gradimentoClasse Acqua...");
					if (tBevandeErogate > 0) return (double) tErogate/(double) tBevandeErogate;
			        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora erogato/a. Impossibile calcolarne indice di gradimento!");
				}
				else 
				{	System.out.println("E' una Bevanda o una Portata");
					return super.gradimento(c); //Se "c" non Ë ne un prodotto e ne ClasseBase, vai al metodo superiore
				}
			}
			
			
			public double deperibilita(IDEsterno c) throws ArithmeticException
			{
				if (c.toString().equals(this.iDEsterno.toString())) 
					return deperibilita();
				else if(c.toString().equals(this.getClass().getName()))
				{
					System.out.println("Calcolo deperibilit√†Classe Acqua...");
					if (tDepositate > 0) return (double) tScadute/(double) tDepositate;
			        throw new ArithmeticException ("Al momento nessun/a " + this.getClass().getName() + " di questo tipo risulta ancora depositato/a perchË scaduto. Impossibile calcolarne indice di deperibilit√†!");
				}
				else 
				{	System.out.println("E' una Bevanda o una Portata");
					return super.deperibilita(c); //Se "c" non Ë ne un prodotto e ne ClasseBase, vai al metodo superiore
				}
			}
	

	/*public double deperibilitaRelativa(IDEsterno c) throws ArithmeticException
	{
		if (c.toString().equals(this.iDEsterno.toString())) 
			return deperibilita();
		else 
			return super.deperibilita(c) ;
	}*/
	
	
	
	public String getGiornoCreazione()
	{		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		SimpleDateFormat sdfGiorno = new SimpleDateFormat("dd/MM/yyyy");
		//SimpleDateFormat sdfOrario = new SimpleDateFormat("HH:mm:ss");
		String dataGiorno = sdfGiorno.format(dataCreazione.ricavaDataGregorian().getTime());
		
		//String dataOrario = sdfOrario.format(dataCreazione.getTime());
		return dataGiorno;
	}
	
	public String getOrarioCreazione()
	{
		
		//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		SimpleDateFormat sdfOrario = new SimpleDateFormat("HH:mm:ss");
		String dataOrario = sdfOrario.format(dataCreazione.ricavaDataGregorian().getTime());
		return dataOrario;
	}
	
	private int calcolaDataAttuale()
	{
		GregorianCalendar dataAttual = new GregorianCalendar();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String dataAttualeStrg = sdf.format(dataAttual.getTime());
		int dataAttuale = (Integer.parseInt(dataAttualeStrg));
		//System.out.println("Data Attuale: "+ dataAttuale);
		return dataAttuale;
	}
	
	public static void azzeraVarStatiche()
	{
		tErogate=0;
		tRichieste=0;
		tScadute=0;
		tDepositate=0;
	}
	
	public static String getVarStatiche()
	{
		String s = "\nClasse Acqua:\n" +
    			"tDepositate: "+tDepositate+"\n"+
    			"tErogate: "+tErogate+"\n"+
    			"tRichieste: "+tRichieste+"\n"+
    			"tScadute: "+tScadute+"\n";
		return s;
	}
	
	
	public boolean frizzante() {
		return frizzante;
	}
	
}

package control;

import java.io.Serializable;
//import java.util.concurrent.locks.Condition;
//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JOptionPane;

//my -----------
import util.*;
import exception.*;
import boundary.*;

//COMMENTI ------------------
//MDM - Eliminato metodo giacenza() e sostituito da getGiacenza().
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()

public class ControlloreDeposito extends Controllore implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	public Utente utenteCorrente;
	
	/*public Lock lock;
	public Condition prodottoEsistenza;*/
	
	
	public ControlloreDeposito()  
	{
		//super("Dummy");
		super(); //Cambiato il 28 Gen
		//new ConfineFornitoreDeposito(this);
		//System.out.println("Admin!");
	}
	
	public ControlloreDeposito(Utente utente, String admin)  
	{
		super(utente, "admin");
		this.utenteCorrente = utente;
	}
	
	public ControlloreDeposito(Utente utente)
	{
		super();
		//System.out.println("Il Fornitore deve entrare QUI!");
		new ConfineFornitoreDeposito(this);
		this.utenteCorrente = utente;
		/*ControlloreErogazione.utenteOspite = utenteCorrente;
		ControlloreDeposito.utenteOspite = utenteCorrente;
		ControlloreGestione.utenteOspite = utenteCorrente;*/
	}

	public void deposita(IDEsterno k, int quantita, int data) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException
	{	
		if (k==null)
		{
			throw new IDEsternoException("L'identificatore del prodotto deve essere non nullo");
		}
		if (mappaProdotti.get(k.toString())==null)
		{
			throw new ProdottoException("Prodotti " + k.toString() + "  non sono presenti");
		}
		else	
		{	
			if (quantita <=0) 
			{
				throw new QuantitaException ("La quantità da depositare deve essere positiva\n");
			}
			else
				
			{						
				mappaProdotti.get(k.toString()).deposita(data, quantita);
				log.logDeposito(this.utenteCorrente, k.toString(), quantita, giacenza(k), data);

				//Aggiornamento indici
				System.out.println("Fornitura:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
				AggiornaIndice("Spesa",k);
				//AggiornaIndice("Ricavo",k);// non necessario in fase di fornitura
				AggiornaIndice("Guadagno",k);
				AggiornaIndice("Valore Magazzino",k);
				//AggiornaIndice("Mancato Guadagno",k);// non necessario in fase di fornitura
				AggiornaIndice("Spesa Evitabile",k);// necessario  in quanto è permesso il deposito di Alimenti Scaduti	
				System.out.println("Fornitura:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");
				
				//String s = mappaProdotti.get(k.toString()).stampaMapScadenza();
				//System.out.println(s);
				//log = new LogProdotti();
				
				//Messaggio di avvenuto deposito Prodotto
				JOptionPane.showMessageDialog(null, "Depositato " + quantita + " di " + k.toString());
				
			}
			
		}
	}
	
	public void depositaThread(IDEsterno k, int quantita, int data, Utente uthread) throws DirittiException, ProdottoException, IDEsternoException, QuantitaException, InterruptedException
	{
		
		lock.lock();
		utenteCorrente = uthread;
		System.out.println("Fornitore "+utenteCorrente.getLogin()+": lock");		
		
		while (mappaProdotti.get(k.toString())==null)
		{
			System.out.println("Fornitore "+utenteCorrente.getLogin()+": Aspetto che il prodotto venga creato");
			prodottoEsistenza.await();
			System.out.println("Fornitore "+utenteCorrente.getLogin()+": Mi risveglio e vedo se il prodotto è stato creato");
			//throw new ProdottoException("Prodotti " + k.toString() + "  non sono presenti");
		}
		
		try
		{
			mappaProdotti.get(k.toString()).deposita(data, quantita);
			log.logDeposito(this.utenteCorrente, k.toString(), quantita, giacenza(k), data);
			
			//Aggiornamento indici
			System.out.println("Thread Fornitura:  ***** Inizio aggiornamento Indici: "+ k.toString() + " *****");
			AggiornaIndice("Spesa",k);
			//AggiornaIndice("Ricavo",k);// non necessario in fase di fornitura
			AggiornaIndice("Guadagno",k);
			AggiornaIndice("Valore Magazzino",k);
			//AggiornaIndice("Mancato Guadagno",k);// non necessario in fase di fornitura
			//AggiornaIndice("Spesa Evitabile",k);// non necessario in fase di fornitura
			System.out.println("Thread Fornitura:  ***** Fine aggiornamento Indici: "+ k.toString() + " *****");

			prodottoDisponibile.signalAll();
					
			
			//mappaProdotti.get(k.toString()).depositaMapScadenza(dataInt, quantitaInt);
			/*String s = mappaProdotti.get(k.toString()).stampaMapScadenza();
			System.out.println(s);*/
			//log = new LogProdotti();
			
		}
		finally
		{
			System.out.println("Fornitore "+utenteCorrente.getLogin()+": unlock");
			lock.unlock();
		}	
	}
	
	
	public int giacenza(IDEsterno k) throws DirittiException, ProdottoException, IDEsternoException
	{
		if (k==null) 
		{
			throw new IDEsternoException("L'identificatore esterno non puo essere nullo\n");
			
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
			//System.out.println("\nClasse: " + nomeTipoProdotto);
			
			//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
			//superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getSimpleName();
			superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getName();

			//System.out.println("Categoria: " + superClasseProdottoCorrente);
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

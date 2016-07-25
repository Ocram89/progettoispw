package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.Serializable;

//import javax.swing.JOptionPane;

//my -----------
import exception.*;

public class LogProdotti 
{
	private Orologio orologio;
	private static String log="";   // static giustamente altrimenti non scriveva il log di eroga
	
	public LogProdotti() 
	{
		orologio = new Orologio();
	}
	
	public void salvaLog()  //registra il log e "flush" di log
	{
		if(log.equalsIgnoreCase(""))
		{
			System.out.println("Log già  salvato ;)");
		}
		else
		{
			registraLog(log);
			log = "";
		}
	}
	
	public void logErogazione(Utente user,String prodotto, int quantita, int giacenza) 
	{	
		if (quantita>0){
			//String data = Funzioni.parsingDataAmericana(dataScadenza); //Meglio non inserirlo, perchè si visualizzerebbe l'ultima data del set di prodotti appartenenti a piÃ¹ date
			log = log + "[" + orologio.calcolaDataEOra()+ "] ("+ user.getLogin()+") Erogati: "  + quantita +" di " + prodotto + " - (giacenza: " + giacenza + ") \n";
			//System.out.println(log);
			//salvaLog(log);
		}
		else 
		{
			//erogazione non effettuata	
		}
		
	}
	public void logDeposito(Utente user,String prodotto, int quantita, int giacenza, Integer dataScadenza)
	{
		String data = Funzioni.parsingDataAmericana(dataScadenza);
		log = log + "["+orologio.calcolaDataEOra()+"] ("+user.getLogin()+") Depositati: "+ quantita +" di " + prodotto + " - (giacenza: " + giacenza + ")" + " con Data di Scadenza: " + data + "\n";
		//System.out.println(log);
		//salvaLog(log);
	}
	
	public void logAggiornamentoIndici(Utente user, String prodotto, String indice, double valore) 
	{	
		//String data = Funzioni.parsingDataAmericana(dataScadenza); //Meglio non inserirlo, perchè si visualizzerebbe l'ultima data del set di prodotti appartenenti a più date
		log = log + "[" + orologio.calcolaDataEOra()+ "] ("+ user.getLogin()+") Aggiornato Indice: "  + indice + " - Prodotto: " + prodotto + " - Valore: " + valore;
		//System.out.println(log);
		salvaLog();
		
	}
	
	public void logNuovoProdotto(Utente user, String prodotto)
	{
		log = log + "["+orologio.calcolaDataEOra()+"] ("+user.getLogin()+") Inserito un nuovo prodotto: "+prodotto;
		//salvaLog(log);
		salvaLog();
	}
	public void logRimozioneProdotto(Utente user, String prodotto)
	{
		log = log + "["+orologio.calcolaDataEOra()+"] ("+user.getLogin()+") Rimosso il prodotto: "+prodotto;
		//salvaLog(log);
	}
	
	public void logRitiraScaduti(Utente user, String prodotto, int quantita, int giacenza)
	{
		log = log + "["+orologio.calcolaDataEOra()+"] ("+user.getLogin()+") Ritirati Scaduti: " + quantita+ " di " + prodotto + " (giacenza: " + giacenza + ")";
		//salvaLog(log);
	}
	

	private void registraLog(String input)
	{
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		FileWriter fw;
		try 
		{
			File file = new File(path);
			fw = new FileWriter(file,true);
			fw.write(input+"\r\n");
			fw.flush();
			fw.close();
		} 
		catch(IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean creaNuovoLog()  //Rinomino il file log.txt e ne creo un altro.
	{
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		File file1 = new File(path);
		if(!file1.exists())
		{
			System.out.println("Il File: "+path+ " non esiste.");
			return false;
		}
		
		//Rinomino file
		boolean loop = true;
		int n=1;
		while(loop)
		{
			String Dir2 = "Save";
			new File(Dir2).mkdir();
			String path2 = Dir+"/log("+n+").txt";
			File file2 = new File(path2);
			
			if(!file2.exists()) //Se il file2 non esiste, rinomina file1 in file2
			{
				if(!file1.renameTo(file2)) System.out.println("Impossibile rinominare il file");
				break;
			}
			n++;
		}
		
		
		//creo il file "log.txt"
		FileWriter fw;
		try 
		{
			fw = new FileWriter(file1,true);
			fw.write("");
			fw.flush();
			fw.close();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return true;
	}
	
	public String caricaLog() throws FileInesistenteException 
	{
		String contenutoFile = "";
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/log.txt";
		FileReader fr;
		
		File file = new File(path);
		try 
		{			
			fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr); 
			String s;
			while((s = br.readLine()) != null) 
			{
				contenutoFile = contenutoFile + s+"\n";
			} 
			fr.close();
		} 
		catch (FileNotFoundException e) 
		{
			throw new FileInesistenteException("Attenzione! File Inesistente! ");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
			
		return contenutoFile;
	}

	/*public static void main(String[] args) 
	{
		LogProdotti log = new LogProdotti();
		log.creaNuovoLog();
		try 
		{
			//System.out.println(log.caricaLog());
		} 
		catch (FileInesistenteException e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage()+ "Per Salvare il Log, cliccare su \"File --> Salva Log Operazioni\".", "Errore", JOptionPane.ERROR_MESSAGE);
		}
		
	}*/
}

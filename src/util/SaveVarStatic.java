package util;

//import java.io.File;
import java.io.Serializable;
import java.util.HashMap;

//import javax.swing.JOptionPane;

import java.awt.Toolkit;

//my -----------
import entity.*;

public class SaveVarStatic extends HashMap <String, SaveVarStaticHashMap> implements Serializable
{
		private static final long serialVersionUID = 1L;
		
		//private static SaveVarStatic istance=null; //riferimento all' istanza
      
		SaveVarStaticHashMap hashMapVarStaticCorrente = new SaveVarStaticHashMap();
		
		//private static HashMap <String, SaveVarStaticHashMap> ClassiVarStatic;
	
		public static final String inizio ="AAA";
       
		private static SaveVarStatic save = null; 

        private SaveVarStatic() 
        {
        	super();
        }
        public static SaveVarStatic inizializza()
        {
        	if (save==null)
        	{
        		System.out.println("Creo Nuovo Oggetto SAVE!!");
        		save = new SaveVarStatic();
        		return save;
        	}
        	else 
    		{
        		System.out.println("Restituisco Oggetto SAVE!!");
    			return save;
    		}
        }
       
        public void inserisciNuovaVariabile(String Classe, SaveVarStaticHashMap hashMap, String strVar, Integer numVar)
        {
        	//ClassiVarStatic.put(Classe, hashMap);
        	put(Classe, hashMap);
        	hashMap.inserisciVar(strVar, numVar);
        	
        }
        public void inserisciVariabile(String Classe, String strVar, Integer numVar)
    	{
        	if(containsKey(inizio))
    		{	
        		clear();
    		}
        	
        	hashMapVarStaticCorrente = get(Classe);
        	if (hashMapVarStaticCorrente!=null)  //Se la Classe già esiste
        	{
        		hashMapVarStaticCorrente.getHashMap().inserisciVar(strVar, numVar);  //Ottengo l'HashMap corrispondente e aggiungo
        	}
        	else
        	{
        		SaveVarStaticHashMap hashMap = new SaveVarStaticHashMap();
        		inserisciNuovaVariabile(Classe, hashMap, strVar, numVar);
        	}//.put(strVar, numVar);
    	}
    	public Integer getNumero(String Classe, String var)
    	{
    		hashMapVarStaticCorrente = super.get(Classe);   //Ottengo l'hashMap corrispondente alla Classe
    		if (hashMapVarStaticCorrente!=null)
    		{
    			return hashMapVarStaticCorrente.getNumero(var);
    		}
    		else 
    		{
    			System.out.println("Problema nell'ottenere il numero!");
    			return -1;
    		}
    	}
    	
    	public SaveVarStatic getMappa()
    	{
    		return this;
    	}
    	
        
    	
		/*public static void main(String[] args) throws SerializzazioneException, DeserializzazioneException 
    	{
    		SaveVarStatic save = SaveVarStatic.inizializza();
    		save.inserisciVariabile("Altro", "numDepositati", new Integer(250));
    		//save.inserisciVariabile("Altro", "Prova3", new Integer(300));
    		
    		
    		String Dir = "Save";
    		new File(Dir).mkdir();
    		String path2 = Dir+"/saveStatic.dat";
    		SerializzaOggetti saveStatic = new SerializzaOggetti(path2);
    		saveStatic.serializza(save);
    		
    		
    		SaveVarStatic save2 = SaveVarStatic.inizializza();
    		DeserializzaOggetti saveStatic2 = new DeserializzaOggetti("Save/saveStatic.dat");
    		System.out.println("Deserializzo...");
    		save2 = (SaveVarStatic) saveStatic2.deserializza();
    		//if (save == null) System.out.println("Non ha deserializzato una ceppa");
    		
    		Integer a = save2.getNumero("Altro", "numDepositati");
    		//Integer b = save2.getNumero("Altro", "Prova3");
    		System.out.println("Il numero è: "+ a);
    		//System.out.println("Il numero è: "+ b);
    		
    	}  */
    	
    	public static void main(String[] args)  
    	{
    		/*Portata p = new Altro(new IDEsterno("a"), new Orologio());
    		Portata p2 = new Carne(new IDEsterno("a"));
    		
    		SaveVarStatic a = new SaveVarStatic();
    		System.out.println("p2 è instanza di " +a.CalcolaSuperClasseDi(p2));*/
    		//===============================================================================================
    		/*Double d = new Double(5);
    		while (d>3)
    		{
    			System.out.println("d è ancora maggiore di 3");
    			d--;
    		}
    		System.out.println("Ora d è = 3");*/
    		//================================================================================================
    		
    		 System.out.println("Emetto beep");		
    		Toolkit.getDefaultToolkit().beep();
    		
    		
    		System.out.print("\007");
    		System.out.println("beep emesso");	

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
}




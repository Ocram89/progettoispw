package util;

import java.io.Serializable;
import java.util.HashMap;


public class SaveVarStaticHashMap extends HashMap <String,Integer> implements Serializable
{
     
	private static final long serialVersionUID = 1L;
	public static final String inizio ="AAA";
	
	SaveVarStaticHashMap hashMapCorrente;
	
	public SaveVarStaticHashMap() 
    {
		super();     	
    }//costruttore
	/*public SaveVarStaticHashMap(int n) 
    {
		super();
		super.put(inizio, new Integer(0));
     	
    }//costruttore
*/	
	 public SaveVarStaticHashMap getHashMap()
	 {
		 return this;
	 }
	
	public void inserisciVar(String strVar, Integer numVar)
	{
		if(containsKey(inizio))
		{	
			clear();
			System.out.println("HashMap2 pulita");
		}
		if(containsKey(strVar))
		{
			System.out.println("Variabile già  esistente");
		}
		else 
		{
			put(strVar, numVar);
			System.out.println("Inserito in HashMap2");
		}
	}
	public Integer getNumero(String strVar)
	{
		if(!containsKey(strVar))
		{
			System.out.println("La chiave richiesta non è presente");
			return -1;
		}
		else return get(strVar);
	}
        	
}
package util;

//my -----------
import exception.*;

//COMMENTI -----
//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------

public class CostantiRuolo 
{
	final public static String ANONIMO = "ANONIMO";
	final public static String CASSIERE = "CASSIERE";
	final public static String FORNITORE = "FORNITORE";
	final public static String GESTORE = "GESTORE";
	final public static String AMMINISTRATORE = "AMMINISTRATORE";

	final static public String[] RUOLO = 
	{
		ANONIMO, CASSIERE, FORNITORE, GESTORE, AMMINISTRATORE 
	};
	
	//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
	final public static String ControlloreVisualizzazioneMenu = "control.ControlloreVisualizzazioneMenu";
	final public static String ControlloreErogazione = "control.ControlloreErogazione";
	final public static String ControlloreDeposito = "control.ControlloreDeposito";
	final public static String ControlloreGestione = "control.ControlloreGestione";
	final public static String ControlloreAmministrazione = "control.ControlloreAmministrazione";
	

	
	final static public String[] NOMICLASSICONTROLLORI = 
	{
		ControlloreVisualizzazioneMenu, ControlloreErogazione, ControlloreDeposito, ControlloreGestione, ControlloreAmministrazione 
	};
	
	
	final public static int NUMRUOLI = 5;
	

	public static int getSize (){
		return NUMRUOLI;	
	}	
	

	public static String getRuolo (int i){
		return RUOLO[i];	
	}
	
	
	public static boolean contains (String s){
		for (String t: RUOLO)
		{
			if (s.equals(t))return true;
		}
		return false;	
	}
	
	
	public static int index(String s) throws RuoloException {	
		if (CostantiRuolo.contains(s)) //Se esiste il RUOLO in esame, restituisci l'indice!
		{
			int i;
			for (i=0;i<NUMRUOLI;i++) 
			{
				if (s.equals(CostantiRuolo.RUOLO[i])) break;
			}
			return i;
		}
		else 
			throw new RuoloException("\n CostantiRuolo. Il ruolo " + s + " non esiste");
	}
}

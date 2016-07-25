package util;

//my -----------
import exception.*;

//COMMENTI ----
//MDM - DescrClasseAlimentareFinale Aggiunta per la gestione sulle interfacca ----

public class CostantiClassiAlimentari {
	final public static String Altro 		= "entity.Altro";
	final public static String Acqua 		= "entity.Acqua";
	final public static String Bevanda 		= "entity.Bevanda";	
	final public static String BriccoFrutta = "entity.BriccoFrutta";
	final public static String Carne 		= "entity.Carne";
	final public static String Dolce 		= "entity.Dolce";
	final public static String Frutta		= "entity.Frutta";
	final public static String Pesce 		= "entity.Pesce";
	final public static String Portata 		= "entity.Portata";
	final public static String Primo 		= "entity.Primo";
	final public static String Soda 		= "entity.Soda";
	final public static String Vino 		= "entity.Vino";
	final public static String Secondo		= "entity.Secondo";
	final public static String PrimoPesce	= "entity.PrimoPesce";
	final public static String PrimoCarne	= "entity.PrimoCarne";
	final public static String PrimoAltro	= "entity.PrimoAltro";
	final public static String SecondoCarne	= "entity.SecondoCarne";
	final public static String SecondoPesce	= "entity.SecondoPesce";
	
	
	final public static int NUMCLASSIALIMENTARI = 16;
	final static public String[] ClasseAlimentare = 
	{
		Altro, Acqua, Bevanda, BriccoFrutta,  Soda, Vino, Primo, PrimoCarne, PrimoPesce, PrimoAltro, Secondo, SecondoCarne, SecondoPesce, Dolce, Frutta, Portata
	};
	
	final static public String[] DescrClasseAlimentare = 
	{
		"entity.Altro", "entity.Acqua", "entity.Bevanda", "entity.BriccoFrutta",  "entity.Soda", "entity.Vino", "entity.Primo", "entity.PrimoCarne", "entity.PrimoPesce", "entity.PrimoAltro", "entity.Secondo", "entity.SecondoCarne", "entity.SecondoPesce", "entity.Dolce", "entity.Frutta", "entity.Portata"
	};
	 
	
	final public static int NUMCLASSIBASE = 12;
	final static public String[] ClasseBase = 
	{
		Altro, Acqua, BriccoFrutta,  Soda, Vino, PrimoCarne, PrimoPesce, PrimoAltro, SecondoCarne, SecondoPesce, Dolce, Frutta
	};
	
	final public static int NUMCATEGORIEALIMENTARI = 5;
	final static public String[] CategorieAlimentari = 
	{
		Bevanda, Primo, Secondo, Dolce, Frutta
	};
	final static public String[] DescrCategorieAlimentari = 
		{
			"Bevanda", "Primo", "Secondo", "Dolce", "Frutta"
		};
	
	final public static int NUMCLASSIALIMENTARIFINALI = 12;
	//MDM - Class.forName occorre inserire i path dei packag (util, entity, ...) --------
	final static public String[] ClasseAlimentareFinale = 
	{
		Altro, Acqua, BriccoFrutta, Soda, Vino, PrimoPesce, PrimoCarne, PrimoAltro, SecondoCarne, SecondoPesce, Dolce, Frutta
	};
	
	//MDM - DescrClasseAlimentareFinale Aggiunta per la gestione sulle interfacca ----
	final static public String[] DescrClasseAlimentareFinale = 
	{
		"Altro", "Acqua", "BriccoFrutta", "Soda", "Vino", "PrimoPesce", "PrimoCarne", "PrimoAltro", "SecondoCarne", "SecondoPesce", "Dolce", "Frutta"
	};
		
	final static public String[] ClasseAlimentarePrimo = 
		{
			"Tutti", PrimoPesce, PrimoCarne, PrimoAltro
		};
	
	final static public String[] ClasseAlimentareBevanda = 
		{
			"Tutte", Acqua, BriccoFrutta, Soda, Vino, Altro
		};
	
	final static public String[] ClasseAlimentareSecondo = 
		{
			"Tutti", SecondoCarne, SecondoPesce
		};
	
	final static public String[] ClasseAlimentareDolce = 
		{
			Dolce
		};
	final static public String[] ClasseAlimentareFrutta = 
		{
			Frutta
		};
	
	final public static int NUMCLASSIALIMENTARIPREFINALI = NUMCLASSIALIMENTARIFINALI;
	final static public String[] ClasseAlimentarePreFinale = {
		Portata, Bevanda, Bevanda, Portata, Portata, Portata, Portata, Portata,  Bevanda, Bevanda
	};
	

	public static int getNumClassi (){
		return NUMCLASSIALIMENTARI;	
	}	


	public static int getNumClassiFinali (){
		return NUMCLASSIALIMENTARIFINALI;	
	}	


	public static String getClasse (int i){
		return ClasseAlimentare[i];	
	}
	

	public static String getClasseFinale (int i){
		return ClasseAlimentareFinale[i];	
	}
	

	public static String getClassePrefinale (int i){
		return ClasseAlimentarePreFinale[i];	
	}
	
	public static boolean containsClasseAlimentare (String s)
	{
		for (String t: ClasseAlimentare)
		{
			if (s.equals(t))return true;
		}
		return false;	
	}
	
	public static boolean containsCategoria (String s)
	{
		for (String t: CategorieAlimentari)
		{
			if (s.equals(t))return true;
		}
		return false;	
	}

	public static boolean containsClasseBase (String s)
	{
		for (String t: ClasseBase)
		{
			if (s.equals(t))return true;
		}
		return false;	
	}
	
	public static boolean containsClasseAlimentareFinale (String s){
		for (String t: ClasseAlimentareFinale){
			if (s.equals(t))return true;
		}
		return false;	
	}
	
	
	public static boolean containsClasseAlimentarePreFinale (String s){
		for (String t: ClasseAlimentarePreFinale){
			if (s.equals(t))return true;
		}
		return false;	
	}
	
	public static String getClasseName(String s) {	
		if (CostantiClassiAlimentari.containsClasseAlimentare(s)){
			int i;
			for (i=0;i<NUMCLASSIALIMENTARI;i++) 
			{
				if (s.equals(CostantiClassiAlimentari.ClasseAlimentare[i])) break;
			}
			return DescrClasseAlimentare[i];
		}
		else 
			return s;
	}
	
	public static int indexClasseAlimentare(String s) throws ClasseAlimentareException {	
		if (CostantiClassiAlimentari.containsClasseAlimentare(s)){
			int i;
			for (i=0;i<NUMCLASSIALIMENTARI;i++) 
			{
				if (s.equals(CostantiClassiAlimentari.ClasseAlimentare[i])) break;
			}
			return i;
		}
		else 
			throw new ClasseAlimentareException("\n CostantiClassiAlimentari. La classe alimentare " + s + " non prevista dal menu");
	}
	
	public static int indexCategoriaAlimentare(String s) throws CategoriaAlimentareException {	
		if (CostantiClassiAlimentari.containsCategoria(s)){
			int i;
			for (i=0;i<NUMCATEGORIEALIMENTARI;i++) 
			{
				if (s.equals(CostantiClassiAlimentari.CategorieAlimentari[i])) break;
			}
			return i;
		}
		else 
			throw new CategoriaAlimentareException("\n CostantiClassiAlimentari. La categoria alimentare " + s + " non prevista dal menu");
	}
	
	
	
	public static int indexClasseAlimentareFinale(String s) throws ClasseAlimentareException {	
		if (CostantiClassiAlimentari.containsClasseAlimentareFinale(s)){
			int i;
			for (i=0;i<NUMCLASSIALIMENTARIFINALI;i++) 
			{
				if (s.equals(CostantiClassiAlimentari.ClasseAlimentareFinale[i])) break;
			}
			return i;
		}
		else 
			throw new ClasseAlimentareException("\n CostantiClassiAlimentari. La classe aleimentare finale " + s + " non prevista dal menu");
	}
	
	
	public static int indexClasseAlimentarePreFinale(String s) throws ClasseAlimentareException {	
		if (CostantiClassiAlimentari.containsClasseAlimentarePreFinale(s)){
			int i;
			for (i=0;i<NUMCLASSIALIMENTARIPREFINALI;i++) 
			{
				if (s.equals(CostantiClassiAlimentari.ClasseAlimentarePreFinale[i])) break;
			}
			return i;
		}
		else 
			throw new ClasseAlimentareException("\n CostantiClassiAlimentari. La classe aleimentare finale " + s + " non prevista dal menu");
	}
}

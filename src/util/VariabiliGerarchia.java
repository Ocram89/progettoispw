package util;

import java.io.File;
import java.io.Serializable;
import java.util.HashMap;
//import java.util.Map;

import javax.swing.JOptionPane;

//my -----------
import exception.*;
import boundary.*;


public class VariabiliGerarchia implements Serializable
{	
	protected static final long	serialVersionUID	= 1L;

	private static int numCategorie= CostantiClassiAlimentari.CategorieAlimentari.length;
	private static int numClassi= CostantiClassiAlimentari.ClasseAlimentareFinale.length;
	private static int numIndici= ConfineGestoreIndici.CatalogoIndici.length;
	private static double [][] arrayIndici = new double [numIndici] [numCategorie + numClassi];
	//private static double [][] indiciClasse = new double [numIndici] [numClassi];
	
	//private static Map <String, double[]> dataMap = new HashMap<String, double[]>();
	//private static double array[] = { 100.0, 200.0 };
	
	private static HashMap <String, double[][]> dataMap = new HashMap<String, double[][]>();
	/*private static double array[][] = { 
										{100.0, 200.0},
										{300.0, 400.0}
									  };*/

	
	@SuppressWarnings("unchecked")
	public static boolean caricaVarStatiche() throws DeserializzazioneException
	{
		if (!new File("Save/VariabiliGerarchia.dat").exists()) 
		{
			//return false;
			throw new DeserializzazioneException("File VariabiliGerarchia ancora non disponibile!");
		}
				
		DeserializzaOggetti load = new DeserializzaOggetti("Save/VariabiliGerarchia.dat");
		try 
		{
			//saveVarStatic =  (HashMap<String, Integer>) load.deserializza();
			dataMap =  (HashMap<String, double[][]>) load.deserializza();
		} 
		catch (DeserializzazioneException e) 
		{
			JOptionPane.showMessageDialog(null, "File da Deserializzare non ancora compatibile. Procedere al salvataggio");
		}
	
		//array = dataMap.get("Prova");
		arrayIndici = dataMap.get("Indici");


		return true;
	}
	
	
	public static void salvaVarStatiche() throws SerializzazioneException 
	{
		//Inserisco Var Statiche nella Mappa
		/*
		if(dataMap.containsKey("Prova"))  //Se già  esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave già  esistente....aggiorno...");
			dataMap.remove("Prova");
		}		

		dataMap.put("Prova", array);
		
		//Inserisco Var Statiche nella Mappa
		if(dataMap.containsKey("iCategoria"))  //Se già  esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave già  esistente....aggiorno...");
			dataMap.remove("iCategoria");
		}		

		dataMap.put("iCategoria", indiciCategoria);*/
		
		//Inserisco Var Statiche nella Mappa
		if(dataMap.containsKey("Indici"))  //Se già  esiste lo rimuove e lo reinserisce
		{
			System.out.println("Chiave già  esistente....aggiorno...");
			dataMap.remove("Indici");
		}		

		dataMap.put("Indici", arrayIndici);
				
		//Salvo la Mappa
		String Dir = "Save";
		new File(Dir).mkdir();
		String path = Dir+"/VariabiliGerarchia.dat";
		SerializzaOggetti save = new SerializzaOggetti(path);
		save.serializza(dataMap);	
		
		//System.out.println("Ho salvato ProvaArray");
	}
	

	public static void aggiornaVGerarchia(int x, int y, double valore)
	{
		arrayIndici[x][y] = arrayIndici[x][y] + valore;
		//System.out.println("Ho aggiornato posizione [" + x +"] [" + y + "]");
	}
	
	
	public static double getVGerarchia(int x, int y)
	{
		double restituisci = arrayIndici[x][y];
		//System.out.println("Restituisco posizione [" + x +"] [" + y + "]");
		return restituisci;
		
	}
	
	
	public static void getVGerarchiaTutta()
	{
		
		//Array Monodimensionale
		/*
		for (Map.Entry<String, double[]> entry : dataMap.entrySet()) {
			// Get and display the key. 
			System.out.print(entry.getKey() + " : ");
			// Get the array. 
			double[] myArray = entry.getValue();

			//display Valori
			for (int ix = 0; ix < myArray.length; ix++) {
				if (ix < myArray.length - 1) { //the value is not the last one in the array ,allora metti ;
					System.out.print(myArray[ix] + "; ");
				} 
				else { //the value is the last one in the array ,la linea finisce così
					System.out.println(myArray[ix]);
				}
			}
		}*/
		
		//Array Bidimensionale
		/*
		for (Map.Entry<String, double[][]> entry : dataMap.entrySet()) {
			// Get and display the key. 
			System.out.print(entry.getKey() + " : ");
			// Get the array. 

				double[][] M = entry.getValue();
				for (int i=0; i<M.length; i++)
				{
					System.out.print("\nContenuto riga " + i + ": ");
					{ for (int j=0; j<M[0].length; j++)
						System.out.print(M[i][j]+" ");
						System.out.println(); 
					}
				}
		}*/
		
		//Array Bidimensionale già caricato
		for (int i=0; i<arrayIndici.length; i++)
		{
			System.out.print("\nContenuto riga " + i + ": ");
			{ for (int j=0; j<arrayIndici[0].length; j++)
				System.out.print(arrayIndici[i][j]+" ");
				System.out.println(); 
			}
		}
		
		
		
	}
		 

	
	public static void azzeraVGerarchia()
	{
		for (int i=0; i<arrayIndici.length; i++)
		{
			{ for (int j=0; j<arrayIndici[0].length; j++)
				arrayIndici[i][j]=0.0;
				
			}
		}
	}
}

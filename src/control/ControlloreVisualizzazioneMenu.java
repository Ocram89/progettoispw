package control;

import java.io.Serializable;

//my -----------
import util.*;
import exception.*;
import boundary.*;

// COMMENTI ------
//MDM - Elimino metodo giacenza(), sostituito da getGiacenza() che ho creato.

public class ControlloreVisualizzazioneMenu extends Controllore implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	/*	private Elemento [] elementi;

	public class Elemento {
		String chiave;
		int quantita;
		Elemento(String chiave, int quantita){
			this.chiave=chiave;
			this.quantita=quantita;
		}
	}
	
*/
	//ControlloreLogin cl = new ControlloreLogin("anonimo");
	
	public ControlloreVisualizzazioneMenu(Utente utente) throws DeserializzazioneException
	{
		super();  // *********   Prima Richiama il Controllore, cioè la classe Madre
		//new ConfineAnonimoVisualizzazione(this); // ***********   E poi la classe di ConfineAnonimo che "scrive" la Grafica
		this.caricaArticoli();
		new ConfineAnonimo(this);
	}
	

	public ControlloreVisualizzazioneMenu(String s)
	{
		super();
	}
	
/*	protected Elemento[] getMenu(){
		Object chiave[] = keySet().toArray();
		for (int i=0; i<chiave.length; i++){
			elementi[i]= new Elemento((String)chiave[i], ((Portata)chiave[i]).giacenza());
		}
		return elementi;
	}
	*/

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
}


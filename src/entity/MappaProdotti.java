package entity;

import java.io.Serializable;
import java.util.*;

import javax.swing.JOptionPane;

//my -----------
import util.*;
import exception.*;

/* HashMap <String,Portata>
 * Associa una Stringa (Nome prodotto), a un oggetto Portata
 *  
 * */
// Andrebbe spostat in control.Controllore, forse ina una sorte di Bean evoluto interpsoto fra boundary e Control.
public class MappaProdotti extends HashMap <String,Portata> implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	
	public MappaProdotti() 
	{
		super();
	}
	
	public static final String inizio ="ZZZ";
	
	public MappaProdotti(int n) 
	{
		super(n);
/*??*/	Portata p = new Altro(new IDEsterno(inizio), new Orologio());     //MA PERCHÃˆ STA COSA???? ************* ?????????

		//Con super, richiamo il metodo "put" della classe HashMap
		super.put(inizio, p); 
		//Cioè ha inserito nell'HashMap il primo prodotto "ZZZ" associato ad un oggetto "Portata" di Tipo "Altro" 
		//System.out.println("Inserito primo prodotto!");
	}
	
	public void inserisciProdotto(String idEsterno, Portata prodotto) throws IDEsternoException, ProdottoException
	{
		if(!containsKey(idEsterno))
		{	
			if(containsKey(inizio)&& super.size()==1)
				super.clear();		
			super.put(idEsterno, prodotto);  //put(Ferrarelle, Acqua)
		}
		else
		{
			throw new IDEsternoException ("Prodotto: " + idEsterno.toString() + "  gia presente/n"); //ID esterno già presente
		}
	}
	
	
	public Portata get (String k) throws IDEsternoException 
	{
		if(k==null)
		{
			throw new IDEsternoException("Nome Prodotto non deve essere nullo/n");
		}
		else
		{
				return super.get(k);
		}
	}
		
	public void remove (String k) throws IDEsternoException, GiacenzaNonNullaException 
	{
		Portata prodotto    = get(k);		
		if(prodotto==null)
		{
			throw new IDEsternoException("Prodotto " + k.toString() + "  non presente/n");
		}
		else
		{

				prodotto.rimuovi();
				super.remove(k);
				JOptionPane.showMessageDialog(null, " Prodotto Eliminato Correttamente ");
		}
	}
	
	
}

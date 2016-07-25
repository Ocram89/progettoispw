package boundary;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.io.File;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//my -----------
import exception.*;
import control.*;

import util.*;

//PRIMA VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE

// import java.util.*;
public class ConfineRiprendi extends JFrame
{
	private static final long	serialVersionUID	= 1L;
	private Controllore controlloreCorrente =null;
		
	//Pannelli
	public JPanel  pannelloBottoni;
	public JPanel  pannelloLabel;

	//Bottone/
	public JButton bRiprendi; 


	
	// Campi e loro etichette
	public JLabel     loginLabel;
	public JTextField login;
	public JLabel     passwordLabel;
	public JTextField password;
	
	// Ascoltatore di bottone e relativa azioni
	private RiprendiAA   ascoltatoreEtAzioneRiprendi;
	
	private ConfineRiprendi questoConfine;
	StackFrame framePrecedente;
	
	public ConfineRiprendi(Controllore controlloreCorrente, StackFrame confineMittente)
	{
//		pila = StackFrame.pilaCornici.toString();
//		System.out.println("La pila ConfineRiprendiPrima: "+ pila);
//		
//		StackFrame.pilaCornici.push(confineMittente);
//		
//		pila = StackFrame.pilaCornici.toString();
//		System.out.println("La pila ConfineRiprendiDopo: "+ pila);
		
		framePrecedente = confineMittente;
		
		this.controlloreCorrente = controlloreCorrente;
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(10,20));
		this.setTitle("Gestione sospesa Tavola Calda 'LA PERGOLA'");
		questoConfine=this;
		this.setBackground(Color.GREEN);
		
		// Creazione pannelli e bottone con suo nome 
		pannelloBottoni = new JPanel();	
		pannelloLabel   = new JPanel();				
		bRiprendi 		= new JButton("Riprendi"); 
		
		// Creazione etichette campi con relativi nomi 
		loginLabel 	 	= new JLabel("Login");
		login    	 	= new JTextField("", 10);  
		passwordLabel 	= new JLabel("Password");
		password      	= new JTextField("", 15);
		
		// Aggiunta bottone a pannello bottoni
		pannelloBottoni.add(bRiprendi);

		// Aggiunta label e campi a pannello label		
		pannelloLabel.add(loginLabel);
		pannelloLabel.add(login);
		pannelloLabel.add(passwordLabel);
		pannelloLabel.add(password);
		
		// Ascoltatore di bottone Login e relativa azione
		ascoltatoreEtAzioneRiprendi= new RiprendiAA();
		
		// Associazione di bottone Login a relativo ascoltatore
		bRiprendi.addActionListener(ascoltatoreEtAzioneRiprendi);
		
		// Aggiunta pannelli al frame
		this.add(pannelloBottoni);
		this.add(pannelloLabel);

		this.pack();	
		bRiprendi.setEnabled(true); 
			
		confineMittente.setVisible(false);  //modificato l'ordine di qst due righe di codice		
		this.setVisible(true);
	}
	// Fine costruttore
	String pila;
	private class RiprendiAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
				try
				{
					controlloreCorrente.ripristinaCredenziali(login.getText(), password.getText());
					//Questo metodo di Controllore, verifica se le credenziali sono giuste, altrimenti
					//manda un alert e l'esecuzione normale si interrompe. Questo confine ovviamente
					//rimane attivo, e rimane in attesa che l'utente prema un pulsante!
										
					
					//Arrivato a questo punto, le Credenziali sono corrette, e quindi bisogna nascondere questo confine
					//per tornare a quello precedente! Come si fa? 
					questoConfine.setVisible(false); //Si nasconde il confine e....
					pila = StackFrame.pilaCornici.toString();
					System.out.println("La pila: "+ pila);
					//StackFrame.pilaCornici.pop().setVisible(true); // ..e si torna al confine precedente!
														//Esso infatti era in cima allo StackFrame, e per 
														//riprenderlo, lo si rende visibile, e cancellandolo (pop)
														//dallo stackframe!
					framePrecedente.setVisible(true);
				}
				catch (PswException e)
				{
					JOptionPane.showMessageDialog(null, " RiprendiAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
		}		
	}
}

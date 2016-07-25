package boundary;

//import java.awt.Color;
//import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
// import java.io.File;
import javax.swing.JButton;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

//my -----------
import exception.*;
import control.*;


//VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE

// import java.util.*;
public class ConfineAmministratoreAmministrazioneUtentiCancellaUtente extends StackFrame
{
	private static final long	serialVersionUID	= 1L;
	private Controllore controlloreCorrente =null;
	
	
	//Pannelli
	public JPanel  	pannelloAdmin4 = new JPanel();
	
	//Label Titolo
	public JLabel     labelCancUtente;
	
	public JPanel  pannelloBottoni;
	public JPanel  pannelloLabel;

	//Bottone/
	public JButton bInvia; 
	public JButton 	bChiudiPannello;


	// Campi e loro etichette
	public JLabel     labelLoginCancUtente;
	public JTextField campoLoginCancUtente;


	//Ascoltatore tendina
//	private TendinaRuoliAA ascoltatoreEtAzioneTendianaRuoli;
	// Ascoltatore di bottone e relativa azioni
	private InviaAA   	ascoltatoreEtAzioneInvia;
//	private RitornaAA   ascoltatoreEtAzioneRitorna;
	private ChiudiPannelloAA	ascoltatoreEtAzioneChiudiPannello;
	//private StackFrame questoConfine;
	
	int altezzaConfine;
	int altezzaPannelloAdmin4;
	int larghezzaPannelloAdmin4;
	int locazioneYPrimaRiga;
	
	public ConfineAmministratoreAmministrazioneUtentiCancellaUtente(Controllore controlloreCorrente, int locazioneYPannelloAdmin4)
	{
		//StackFrame.pilaCornici.push(confineMittente);
		this.controlloreCorrente = controlloreCorrente;
		
		/*this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(10,20));
		this.setTitle("Cancellazione Utente Sistema"+CostantiDiInstallazione.NOME_CLIENTE);
		this.setBackground(Color.GREEN);
		questoConfine=this;*/
		
		int lunghezzaScrittaTitolo=200;
		int lunghezzaScritta=80;
		int altezzaScritta = 30;
		
		altezzaConfine = ConfineAvvio.Confine.getHeight();
		ConfineAvvio.Confine.add(pannelloAdmin4);
		ConfineAvvio.Confine.setLayout(null);
		pannelloAdmin4.setLayout(null);
		pannelloAdmin4.setVisible(true);
		
		locazioneYPannelloAdmin4 += 5;
		altezzaPannelloAdmin4 = altezzaConfine - locazioneYPannelloAdmin4 -38;
		larghezzaPannelloAdmin4 = ConfineAvvio.Confine.getWidth();
        pannelloAdmin4.setBounds(0,locazioneYPannelloAdmin4,larghezzaPannelloAdmin4, altezzaPannelloAdmin4);
		pannelloAdmin4.setBackground(new Color(190,190,190));
		
		int larghezzaBottoni = 150;			
		int altezzaBottoni = 30;
		
		//TITOLO
		labelCancUtente = new JLabel("Cancella Utente");
		labelCancUtente.setBounds((larghezzaPannelloAdmin4/2)-88,10, lunghezzaScrittaTitolo, altezzaScritta);
		labelCancUtente.setFont(new Font("Vedana", Font.BOLD, 18));
		
			
		//Calcolo Misura delle Righe
		locazioneYPrimaRiga =	(altezzaPannelloAdmin4)/4;
		
		int locazioneXLabels = larghezzaPannelloAdmin4/6;
		int lunghezzaCampoTesto = 300;
		int locazioneXCampiTesto = locazioneXLabels + lunghezzaScritta;
		
		labelLoginCancUtente = new JLabel("Login:");
		labelLoginCancUtente.setBounds(locazioneXLabels,locazioneYPrimaRiga, lunghezzaScritta-20, altezzaScritta);
		labelLoginCancUtente.setFont(new Font("Verdana", 0, 18));
		
		campoLoginCancUtente = new JTextField("", 20);
		campoLoginCancUtente.setBounds(locazioneXCampiTesto,locazioneYPrimaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoLoginCancUtente.setFont(new Font("Verdana",0,18));
		
		int locazioneXBottInvia = locazioneXCampiTesto + lunghezzaCampoTesto + 30;
		//Bottone "Invia"
		bInvia = new JButton(new ImageIcon("Save/immagini/invia.gif"));
		bInvia.setRolloverIcon(new ImageIcon("Save/immagini/inviaR.gif"));
		bInvia.setBounds(locazioneXBottInvia ,locazioneYPrimaRiga, larghezzaBottoni, altezzaBottoni);
		bInvia.setBorderPainted(false);
		bInvia.setMargin (new Insets (0, 0, 0, 0));
		bInvia.setContentAreaFilled(false);
		bInvia.setOpaque(true);
		bInvia.setToolTipText("Invia");
		
		//"X" chiusura Pannello	
		bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
		bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
		bChiudiPannello.setBounds(827, 5, 48, 20);
		bChiudiPannello.setBorderPainted(false);
		bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
		bChiudiPannello.setContentAreaFilled(false);
		bChiudiPannello.setOpaque(true);
		bChiudiPannello.setToolTipText("Chiusura Pannello");
		
		pannelloAdmin4.add(labelCancUtente);
		pannelloAdmin4.add(labelLoginCancUtente);
		pannelloAdmin4.add(campoLoginCancUtente);
		pannelloAdmin4.add(bInvia);
		pannelloAdmin4.add(bChiudiPannello);
		
		
		//Ascoltatori
	    ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
	    ascoltatoreEtAzioneInvia = new InviaAA();
	    
	    //Associazione
	    bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
	    bInvia.addActionListener(ascoltatoreEtAzioneInvia);
	    
		/*// Creazione pannelli e bottone con suo nome 
		pannelloBottoni  = new JPanel();	
		pannelloLabel    = new JPanel();				
		bInvia = new JButton("Invia"); 
		bRitorna = new JButton("Ritorna menu precedente"); 
		
		// Creazione etichette campi con relativi nomi 
		loginLabel 	 			= new JLabel("Login");
		login    	 			= new JTextField("", 10);  

		// Aggiunta bottoni a pannello bottoni
		pannelloBottoni.add(bInvia);
		pannelloBottoni.add(bRitorna);

		// Aggiunta label e campi a pannello label		
		pannelloLabel.add(loginLabel);
		pannelloLabel.add(login);
	

//		ascoltatoreEtAzioneTendianaRuoli = new TendinaRuoliAA();
		
		// Ascoltatori di bottoni  e relative azioni
		ascoltatoreEtAzioneInvia 	= new InviaAA();
		ascoltatoreEtAzioneRitorna 	= new RitornaAA();
		
		// Associazione di bottoni ia relativi ascoltatori
		bInvia.addActionListener(ascoltatoreEtAzioneInvia);
		bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
		
//		tendinaRuoli.addActionListener(this);

		
		// Aggiunta pannelli al frame
		this.add(pannelloBottoni);
		this.add(pannelloLabel);

		this.pack();	
		bInvia.setEnabled(true); 
		this.setVisible(true);	
		confineMittente.setVisible(false);*/
	}
	// Fine costruttore
	
	private class InviaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try 
			{
					controlloreCorrente.cancellaUtente(campoLoginCancUtente.getText());
					//questoConfine.setVisible(false);
//					new ConfineAministratoreAmministrazioneUtenti(controlloreCorrente,confineMittente);				
					//StackFrame.pilaCornici.peek().setVisible(true);
			} 
			catch (LoginException e) 
			{
					JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private class ChiudiPannelloAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdmin4.setVisible(false);
				ConfineAministratoreAmministrazioneUtenti.bNuovoUtente.setEnabled(true);
				ConfineAministratoreAmministrazioneUtenti.bCancUtente.setEnabled(true);
				ConfineAministratoreAmministrazioneUtenti.bListaUtenti.setEnabled(true);
				ConfineAministratoreAmministrazioneUtenti.bChiudiPannelloGestioneUtenti.setEnabled(true);
			}
	}

	/*private class RitornaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			questoConfine.setVisible(false);
			StackFrame.rimuoviDalTop(questoConfine);
			if (StackFrame.pilaCornici.empty())
				try {
					ControlloreLogin.gestioneLogin();
				} catch (PswException e) {
					JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneUtentiCancellaUtente.RitornaAA. PswException " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			else
				StackFrame.pilaCornici.pop().setVisible(true);
		}
	}*/
}

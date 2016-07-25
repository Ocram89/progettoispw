package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
	
import javax.swing.ImageIcon;
// import java.io.File;
	import javax.swing.JButton;
//	import javax.swing.JFrame;
	import javax.swing.JLabel;
//	import javax.swing.JOptionPane;
	import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
	// import java.util.*;

//my -----------
import exception.*;
import control.*;


//PRIMA VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE


public class ConfineAmministratoreAmministrazioneUtentiCambiaPassword extends StackFrame
{
	private static final long	serialVersionUID	= 1L;
	private Controllore controlloreCorrente =null;
	

	
	
	//Pannelli
	public JPanel pannelloAdmin4 = new JPanel();
	
	public JPanel  pannelloBottoni;
	public JPanel  pannelloLabel;

	//Label
	public JLabel	labelCambiaPswAdmin;
	
	//Bottone	
	
	public JButton 		bInvia; 
	public JButton    	bChiudiPannello;
//	public JButton bRitorna; 


	
	// Campi e loro etichette
	public JLabel		labelVecchiaPsw;
	public JPasswordField 	campoVecchiaPsw;
	public JLabel     	labelNuovaPsw;
	public JPasswordField 	campoNuovaPsw;
	public JLabel     	labelRipetiNuovaPsw;
	public JPasswordField 	campoRipetiNuovaPsw;

	
	// Ascoltatore di bottone e relativa azioni
//	private InviaAA   ascoltatoreEtAzioneInvia;
//	private RitornaAA ascoltatoreEtAzioneRitorna;
	
	private ChiudiPannelloAA	ascoltatoreEtAzioneChiudiPannello;
	private InviaAA				ascoltatoreEtAzioneInviaAA;
	
	int altezzaConfine;
	int altezzaPannelloAdmin4;
	int larghezzaPannelloAdmin4;
	int locazioneYPrimaRiga;
	int locazioneYSecondaRiga;
	int locazioneYTerzaRiga;
//	int savelocazioneYPannelloAdmin2;
	
//	private StackFrame questoConfine;

	
	public ConfineAmministratoreAmministrazioneUtentiCambiaPassword(Controllore controlloreCorrente, int locazioneYPannelloAdmin4)
	{
		//StackFrame.pilaCornici.push(confineMittente);
		
		
//QUI FINISCE IL PERCORSO PER il controllore!!		
		this.controlloreCorrente = controlloreCorrente;
		
		//this.setLayout(new GridLayout(10,20));
		//this.setTitle("Cambio Password Personale Sistema Tavola Calda 'LA PERGOLA'");
		//this.setBackground(Color.GREEN);
		//questoConfine=this;
		
		int lunghezzaScritta=220;
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
		labelCambiaPswAdmin = new JLabel("Cambia Password");
		labelCambiaPswAdmin.setBounds((larghezzaPannelloAdmin4/3)+55,10, lunghezzaScritta, altezzaScritta);
		labelCambiaPswAdmin.setFont(new Font("Verdana", Font.BOLD, 18));
		
		locazioneYPrimaRiga =	(altezzaPannelloAdmin4)/4;
		locazioneYSecondaRiga = (altezzaPannelloAdmin4)/2;
		locazioneYTerzaRiga = (altezzaPannelloAdmin4)*3/4;
		
		//PRIMA RIGA
		int locazioneXlabelVecchiaPsw = larghezzaPannelloAdmin4/10;
		labelVecchiaPsw = new JLabel("Vecchia Password:");
		labelVecchiaPsw.setBounds(locazioneXlabelVecchiaPsw,locazioneYPrimaRiga, lunghezzaScritta, altezzaScritta);
		labelVecchiaPsw.setFont(new Font("Verdana", 0, 18));
		
		int lunghezzaCampoTesto = 300;
		int locazioneXCampoVecchiaPsw = locazioneXlabelVecchiaPsw + lunghezzaScritta;
		campoVecchiaPsw      	 = new JPasswordField("", 20);
		campoVecchiaPsw.setBounds(locazioneXCampoVecchiaPsw,locazioneYPrimaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoVecchiaPsw.setFont(new Font("Verdana", 0, 18));
		
		//SECONDA RIGA
		int locazioneXlabelNuovaPsw = larghezzaPannelloAdmin4/10;
		labelNuovaPsw = new JLabel("Nuova Password:");
		labelNuovaPsw.setBounds(locazioneXlabelNuovaPsw,locazioneYSecondaRiga, lunghezzaScritta, altezzaScritta);
		labelNuovaPsw.setFont(new Font("Verdana", 0, 18));
		
		int locazioneXCampoNuovaPsw = locazioneXlabelNuovaPsw + lunghezzaScritta;
		campoNuovaPsw      	 = new JPasswordField("", 20);
		campoNuovaPsw.setBounds(locazioneXCampoNuovaPsw,locazioneYSecondaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoNuovaPsw.setFont(new Font("Verdana", 0, 18));

		
		//TERZA RIGA
		int locazioneXlabelRipetiNuovaPsw = larghezzaPannelloAdmin4/10;
		labelRipetiNuovaPsw = new JLabel("Ripeti Nuova Password:");
		labelRipetiNuovaPsw.setBounds(locazioneXlabelRipetiNuovaPsw,locazioneYTerzaRiga, lunghezzaScritta, altezzaScritta);
		labelRipetiNuovaPsw.setFont(new Font("Verdana", 0, 18));
		
		int locazioneXCampoRipetiNuovaPsw = locazioneXlabelRipetiNuovaPsw + lunghezzaScritta;
		campoRipetiNuovaPsw      	 = new JPasswordField("", 20);
		campoRipetiNuovaPsw.setBounds(locazioneXCampoRipetiNuovaPsw,locazioneYTerzaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoRipetiNuovaPsw.setFont(new Font("Arial",0,18));
		
		
		//"X" chiusura Pannello	
		bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
		bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
		bChiudiPannello.setBounds(827, 5, 48, 20);
		bChiudiPannello.setBorderPainted(false);
		bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
		bChiudiPannello.setContentAreaFilled(false);
		bChiudiPannello.setOpaque(true);
		bChiudiPannello.setToolTipText("Chiusura Pannello");
		
		int locazioneXBottInvia = locazioneXCampoRipetiNuovaPsw + lunghezzaCampoTesto + 30;
		//Bottone "Invia"
		bInvia = new JButton(new ImageIcon("Save/immagini/invia.gif"));
		bInvia.setRolloverIcon(new ImageIcon("Save/immagini/inviaR.gif"));
		bInvia.setBounds(locazioneXBottInvia ,locazioneYTerzaRiga, larghezzaBottoni, altezzaBottoni);
		bInvia.setBorderPainted(false);
		bInvia.setMargin (new Insets (0, 0, 0, 0));
		bInvia.setContentAreaFilled(false);
		bInvia.setOpaque(true);
		bInvia.setToolTipText("Invia");
		
		pannelloAdmin4.add(labelCambiaPswAdmin);
		pannelloAdmin4.add(labelVecchiaPsw);
		pannelloAdmin4.add(campoVecchiaPsw);
	    pannelloAdmin4.add(labelNuovaPsw);
	    pannelloAdmin4.add(campoNuovaPsw);
	    pannelloAdmin4.add(labelRipetiNuovaPsw);
	    pannelloAdmin4.add(campoRipetiNuovaPsw);
	    pannelloAdmin4.add(bInvia);
	    pannelloAdmin4.add(bChiudiPannello);
		
		//Ascoltatori
	    ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
	    ascoltatoreEtAzioneInviaAA = new InviaAA();
	    
	    //Associazione
	    bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
	    bInvia.addActionListener(ascoltatoreEtAzioneInviaAA);
	    
		
		
		
	/*	// Creazione pannelli e bottone con suo nome 
		pannelloBottoni  = new JPanel();	
		pannelloLabel    = new JPanel();				
		bInvia = new JButton("Invia"); 
		bRitorna = new JButton("Ritorna menu precedente"); 
		
		// Creazione etichette campi con relativi nomi 
		vecchiaPasswordLabel 	= new JLabel("Vecchia Password");
		vecchiaPassword      	= new JTextField("", 15);
		nuovaPasswordLabel 		= new JLabel("Nuova Password");
		nuovaPassword      		= new JTextField("", 15);
		passwordRipetutaLabel 	= new JLabel("Ripeti Nuova Password");
		passwordRipetuta      	= new JTextField("", 15);
	
		// Aggiunta bottoni a pannello bottoni
		pannelloBottoni.add(bInvia);
		pannelloBottoni.add(bRitorna);

		// Aggiunta label e campi a pannello label		
		pannelloLabel.add(vecchiaPasswordLabel);
		pannelloLabel.add(vecchiaPassword);
		pannelloLabel.add(nuovaPasswordLabel);
		pannelloLabel.add(nuovaPassword);
		pannelloLabel.add(passwordRipetutaLabel);
		pannelloLabel.add(passwordRipetuta);
			
		// Ascoltatori di bottoni  e relative azioni
		ascoltatoreEtAzioneInvia 	= new InviaAA();
		ascoltatoreEtAzioneRitorna 	= new RitornaAA();
		
		// Associazione di bottoni ia relativi ascoltatori
		bInvia.addActionListener(ascoltatoreEtAzioneInvia);
		bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
		
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
			if(campoNuovaPsw.getText().equals (campoRipetiNuovaPsw.getText()))
			{

				try 
				{
					controlloreCorrente.chngPassword(campoVecchiaPsw.getText(), campoNuovaPsw.getText());
					//questoConfine.setVisible(false);
					//new ConfineAministratoreAmministrazioneUtenti(controlloreCorrente,confineMittente);				
					//StackFrame.pilaCornici.peek().setVisible(true);
				} 
				catch (PswException e) 
				{
						JOptionPane.showMessageDialog(null, " Cambia password " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, " Inserite password differenti ", "Errore", JOptionPane.ERROR_MESSAGE);
			}
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
					JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneUtentiCambiaPassword.RitornaAA. PswException " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			else
				StackFrame.pilaCornici.pop().setVisible(true);
		}
	}*/
	
	private class ChiudiPannelloAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdmin4.setVisible(false);
				ConfineAministratoreAmministrazioneUtenti.bCambiaPsw.setEnabled(true);
				ConfineAministratoreAmministrazioneUtenti.bChiudiPannelloOpzioniAdmin.setEnabled(true);
			}
	}

}

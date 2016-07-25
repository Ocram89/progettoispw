package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
// import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//my -----------
import util.*;
import exception.*;
import control.*;


//PRIMA VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE

public class ConfineAmministratoreAmministrazioneUtentiNuovoUtente extends StackFrame
{
	private static final long	serialVersionUID	= 1L;
	private Controllore controlloreCorrente =null;
	

	
	
	//Pannelli
	public JPanel  	pannelloAdmin4 = new JPanel();
	
	
	public JPanel  	pannelloBottoni;
	public JPanel  	pannelloLabel;

	
	//Label
	public JLabel   labelNuovoUtente;
	public JLabel   labelScegliUtente;
	
	//Bottone/
	public JButton 	bInvia; 
	public JButton 	bChiudiPannello; 
	

	// Tendine
	public JComboBox tendinaRuoli;
	
	// Campi e loro etichette
	public JLabel     	labelLoginNuovoUtente;
	public JTextField 	campoLoginNuovoUtente;
	public JLabel     	labelPswNuovoUtente;
	public JPasswordField 	campoPswNuovoUtente;
	public JLabel     	labelRipetiPswNuovoUtente;
	public JPasswordField 	campoRipetiPswNuovoUtente;

	// Ascoltatore di bottone e relativa azioni
	private ChiudiPannelloAA	ascoltatoreEtAzioneChiudiPannello;
	private InviaAA				ascoltatoreEtAzioneInvia;

//	private RitornaAA   ascoltatoreEtAzioneRitorna;
	
	//Ascoltatore tendina
	private TendinaRuoliAA   ascoltatoreEtAzioneTendina;
	
	
	//private StackFrame questoConfine;
	private String 	ruoloCorrente;

	int altezzaConfine;
	int altezzaPannelloAdmin4;
	int larghezzaPannelloAdmin4;
	int locazioneYPrimaRiga;
	int locazioneYSecondaRiga;
	int locazioneYTerzaRiga;
	int locazioneYQuartaRiga;
	
	public ConfineAmministratoreAmministrazioneUtentiNuovoUtente(Controllore controlloreCorrente, int locazioneYPannelloAdmin4)
	{
		
		//***** CONTINUARE DA QUI PRENDENDO SPUNTO DA ConfineAministratoreAmministrazioneUtentiCambiaPassword
		
		//StackFrame.pilaCornici.push(confineMittente);
		this.controlloreCorrente = controlloreCorrente;
		
		/*this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(10,20));
		this.setTitle("Definizione Nuovo Utente Sistema Tavola Calda 'LA PERGOLA'");
		this.setBackground(Color.GREEN);
		questoConfine=this;*/
		
		ruoloCorrente=CostantiRuolo.RUOLO[0]; //ANONIMO
		
		int lunghezzaScritta=200;
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
		labelNuovoUtente = new JLabel("Nuovo Utente");
		labelNuovoUtente.setBounds((larghezzaPannelloAdmin4/2)-70,10, lunghezzaScritta, altezzaScritta);
		labelNuovoUtente.setFont(new Font("Verdana", Font.BOLD, 18));
		
		//Calcolo Misura delle Righe
		locazioneYPrimaRiga =	(altezzaPannelloAdmin4)/5;
		locazioneYSecondaRiga = (altezzaPannelloAdmin4)*2/5;
		locazioneYTerzaRiga = (altezzaPannelloAdmin4)*3/5;
		locazioneYQuartaRiga = (altezzaPannelloAdmin4)*4/5;
		
		int locazioneXLabels = larghezzaPannelloAdmin4/10;
		int lunghezzaCampoTesto = 300;
		int locazioneXCampiTesto = locazioneXLabels + lunghezzaScritta;
		
		//PRIMA RIGA
		//int locazioneXlabelScegliUtente = larghezzaPannelloAdmin4/10;
		labelScegliUtente = new JLabel("Scegli Tipo di Utente:");
		labelScegliUtente.setBounds(locazioneXLabels,locazioneYPrimaRiga, lunghezzaScritta-20, altezzaScritta-10);
		labelScegliUtente.setFont(new Font("Verdana", 0, 16));
		
		// Tendina
		//int locazioneXtendinaScegliUtente = locazioneXlabelScegliUtente + lunghezzaScritta;
		tendinaRuoli = new JComboBox(CostantiRuolo.RUOLO);
		tendinaRuoli.setBounds(locazioneXCampiTesto,locazioneYPrimaRiga,lunghezzaCampoTesto,altezzaScritta);
		tendinaRuoli.setSelectedIndex(0); //ANONIMO

		//SECONDA RIGA
		//int locazioneXlabelLoginNuovoUtente = larghezzaPannelloAdmin4/10;
		labelLoginNuovoUtente = new JLabel("Login:");
		labelLoginNuovoUtente.setBounds(locazioneXLabels,locazioneYSecondaRiga, lunghezzaScritta, altezzaScritta);
		labelLoginNuovoUtente.setFont(new Font("Verdana", 0, 18));
		
		//int locazioneXCampoNuovaPsw = locazioneXlabelNuovaPsw + lunghezzaScritta;
		campoLoginNuovoUtente      	 = new JTextField("", 20);
		campoLoginNuovoUtente.setBounds(locazioneXCampiTesto,locazioneYSecondaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoLoginNuovoUtente.setFont(new Font("Verdana",0,18));
		
		//TERZA RIGA
		//int locazioneXlabelRipetiNuovaPsw = larghezzaPannelloAdmin4/10;
		labelPswNuovoUtente = new JLabel("Password:");
		labelPswNuovoUtente.setBounds(locazioneXLabels,locazioneYTerzaRiga, lunghezzaScritta, altezzaScritta);
		labelPswNuovoUtente.setFont(new Font("Verdana", 0, 18));
		
		//int locazioneXCampoRipetiNuovaPsw = locazioneXlabelRipetiNuovaPsw + lunghezzaScritta;
		campoPswNuovoUtente      	 = new JPasswordField("", 20);
		campoPswNuovoUtente.setBounds(locazioneXCampiTesto,locazioneYTerzaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoPswNuovoUtente.setFont(new Font("Verdana",0,18));

		//QUARTA RIGA
		//int locazioneXlabelRipetiNuovaPsw = larghezzaPannelloAdmin4/10;
		labelRipetiPswNuovoUtente = new JLabel("Ripeti Password:");
		labelRipetiPswNuovoUtente.setBounds(locazioneXLabels,locazioneYQuartaRiga, lunghezzaScritta, altezzaScritta);
		labelRipetiPswNuovoUtente.setFont(new Font("Verdana", 0, 18));
		
		//int locazioneXCampoRipetiNuovaPsw = locazioneXlabelRipetiNuovaPsw + lunghezzaScritta;
		campoRipetiPswNuovoUtente      	 = new JPasswordField("", 20);
		campoRipetiPswNuovoUtente.setBounds(locazioneXCampiTesto,locazioneYQuartaRiga,lunghezzaCampoTesto,altezzaScritta);
		campoRipetiPswNuovoUtente.setFont(new Font("Verdana",0,18));
		
		int locazioneXBottInvia = locazioneXCampiTesto + lunghezzaCampoTesto + 30;
		//Bottone "Invia"
		bInvia = new JButton(new ImageIcon("Save/immagini/invia.gif"));
		bInvia.setRolloverIcon(new ImageIcon("Save/immagini/inviaR.gif"));
		bInvia.setBounds(locazioneXBottInvia ,locazioneYTerzaRiga, larghezzaBottoni, altezzaBottoni);
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
		
		//Aggiunta Componenti al Pannello
		pannelloAdmin4.add(labelNuovoUtente);
		pannelloAdmin4.add(labelScegliUtente);
		pannelloAdmin4.add(tendinaRuoli);
		pannelloAdmin4.add(labelLoginNuovoUtente);
	    pannelloAdmin4.add(campoLoginNuovoUtente);
	    pannelloAdmin4.add(labelPswNuovoUtente);
	    pannelloAdmin4.add(campoPswNuovoUtente);
	    pannelloAdmin4.add(labelRipetiPswNuovoUtente);
	    pannelloAdmin4.add(campoRipetiPswNuovoUtente);
	    pannelloAdmin4.add(bInvia);
	    pannelloAdmin4.add(bChiudiPannello);
		
		//Ascoltatori
	    ascoltatoreEtAzioneTendina 	= new TendinaRuoliAA();
	    ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
	    ascoltatoreEtAzioneInvia = new InviaAA();
	    
	    //Associazione
	    tendinaRuoli.addActionListener(ascoltatoreEtAzioneTendina);
	    bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
	    bInvia.addActionListener(ascoltatoreEtAzioneInvia);
	    
	    
		
		
		
		/*// Creazione pannelli e bottone con suo nome 
		pannelloBottoni  	= new JPanel();	
		pannelloLabel    	= new JPanel();				
		bInvia 				= new JButton("Invia"); 
		bRitorna 			= new JButton("Ritorna menu precedente"); 
		

		
		// Creazione etichette campi con relativi nomi 
		loginLabel 	 			= new JLabel("Login");
		login    	 			= new JTextField("", 10);  
		passwordLabel 			= new JLabel("Password");
		password      			= new JTextField("", 15);
		passwordRipetutaLabel 	= new JLabel("Ripeti Password");
		passwordRipetuta      	= new JTextField("", 15);
	
		// Aggiunta bottoni a pannello bottoni
		pannelloBottoni.add(bInvia);
		pannelloBottoni.add(bRitorna);

		// Aggiunta label e campi a pannello label		
		pannelloLabel.add(loginLabel);
		pannelloLabel.add(login);
		pannelloLabel.add(tendinaRuoli);		
		pannelloLabel.add(passwordLabel);
		pannelloLabel.add(password);
		pannelloLabel.add(passwordRipetutaLabel);
		pannelloLabel.add(passwordRipetuta);
		

//		ascoltatoreEtAzioneTendinaRuoli = new TendinaRuoliAA();
		
		// Ascoltatori di bottoni  e relative azioni
		ascoltatoreEtAzioneInvia 	= new InviaAA();
		ascoltatoreEtAzioneRitorna 	= new RitornaAA();
		ascoltatoreEtAzioneTendina 	= new TendinaRuoliAA();
		
		// Associazione di bottoni ia relativi ascoltatori
		bInvia.addActionListener(ascoltatoreEtAzioneInvia);
		bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
		tendinaRuoli.addActionListener(ascoltatoreEtAzioneTendina);

		
		// Aggiunta pannelli al frame
		this.add(pannelloBottoni);
		this.add(pannelloLabel);

		this.pack();	
		bInvia.setEnabled(true); 
		this.setVisible(true);	
		confineMittente.setVisible(false);*/
	    campoLoginNuovoUtente.requestFocus();
	}
	// Fine costruttore
	
	private class InviaAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			String password1 = campoPswNuovoUtente.getText();
			String password2 = campoRipetiPswNuovoUtente.getText();
			try 
			{
					//Esegue 4 controlli: psw almeno 8 caratteri, psw inizia con carattere, psw almeno un numero, confronto psw
					controlloreCorrente.controllaPsw(password1, password2);
					
					controlloreCorrente.nuovoUtente(campoLoginNuovoUtente.getText(), campoPswNuovoUtente.getText(), ruoloCorrente);
					ruoloCorrente=CostantiRuolo.RUOLO[0]; //ANONIMO
					campoLoginNuovoUtente.setText("");
					campoPswNuovoUtente.setText("");
					campoRipetiPswNuovoUtente.setText("");
					campoLoginNuovoUtente.requestFocus();
			} 
			catch (PswException e) 
			{
				JOptionPane.showMessageDialog(null, "Attenzione! " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			} 
			catch (LoginException e) 
			{
				JOptionPane.showMessageDialog(null, " Cambia login " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			} 
			catch (RuoloException e) 
			{
				JOptionPane.showMessageDialog(null, " Cambia ruolo " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			} 
			catch (DirittiException e) 
			{
				JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}			
		}
		
		
		/*private void controllaPsw(String psw1, String psw2) throws PswException
		{
			//1.Controllo lunghezza psw
			int lunghezzaPsw = psw1.length();
			if(lunghezzaPsw<8)
			{
				throw new PswException("La Password deve essere di almeno 8 caratteri!");
			}
				
			String primoCarattere = psw1.substring(0,1);
			if(CostantiNumeriche.isNumber(primoCarattere))
			{
				//System.out.println("Il primo Carattere è un numero!!");
				throw new PswException("La password deve iniziare con un carattere!");
			}	
			
			//2. Controllo che ci sia almeno un numero
			boolean almenoUnNumero = false;
			String c = "";
			for(int i=1; i<lunghezzaPsw-1; i++) //Inizio dal secondo carattere
			{
				c = psw1.substring(i,i+1);
				if(CostantiNumeriche.isNumber(c)) // Se c è un numero
				{
					almenoUnNumero = true;
					break;
				}
			}
			if (!almenoUnNumero)
			{
				throw new PswException("La password deve iniziare con un carattere e avere almeno un numero!");
			}
			
			//3. Controllo che le due psw siano uguali
			if(!(psw1.equals(psw2)))
			{
				throw new PswException("Inserite Password Differenti!");
			}		
		}*/		
	}
	

	public class TendinaRuoliAA implements ActionListener 
	{ 
	    public void actionPerformed(ActionEvent e) 
	    {
	        JComboBox cb = (JComboBox)e.getSource();
	        ruoloCorrente = (String)cb.getSelectedItem();
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

}

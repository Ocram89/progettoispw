package boundary;

//PRIMA VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE


	import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
//	import java.io.File;
//	import java.io.IOException;
	import javax.swing.JButton;
//	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//	import javax.swing.JTextField;
//	import java.util.*;

//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;

	public class ConfineAministratoreAmministrazioneUtenti extends StackFrame
	{
		//Versione temporanea per modificare al minimo il main di test
		private static final long	serialVersionUID	= 1L;
		
		// Relazione con controllore. Mettere Privato?!
		protected Controllore controlloreCorrente;
			
		//Pannelli
		public JPanel pannelloAdmin2 = new JPanel();
		public JPanel pannelloAdmin3OpzioniAdmin = new JPanel();
		public JPanel pannelloAdmin3GestioneUtenti = new JPanel();
		
		public JPanel  		pannelloBottoni;
		public JPanel  		pannelloLabel;
		
		//Jlabel
		public JLabel		labelGestioneUtenti;
		
		//Bottoni Admin2
		public JButton 		bOpzioniAdmin; 
		public JButton 		bGestioneUtenti;
		public JButton		bChiudiPannello;
		
		//Bottoni Admin3Opzioni
		public static JButton		bChiudiPannelloOpzioniAdmin;
		public static JButton 		bCambiaPsw;
		
		//Bottoni Admin3GestioneUtenti
		public static JButton bListaUtenti;
		public static JButton bNuovoUtente;
		public static JButton bCancUtente;
		public static JButton bChiudiPannelloGestioneUtenti;
		
		// Campi e loro etichette
		
		// Ascoltatori di bottoni e relative azioni
		private ListaUtentiAA 		ascoltatoreEtAzioneListaUtenti;
		private NuovoUtenteAA 		ascoltatoreEtAzioneNuovoUtente;
		private CancellaUtenteAA 	ascoltatoreEtAzioneCancellaUtente;
		
		private ChiudiPannelloAA 		ascoltatoreEtAzioneChiudiPannello;
		private OpzioniAdminAA 			ascoltatoreEtAzioneOpzioniAdminAA;
		private GestioneUtentiAA		ascoltatoreEtAzioneGestioneUtentiAA;
		
		private ChiudiPannelloAdmin3OpzioniAdminAA 	ascoltatoreEtAzioneChiudiPannelloAdmin3OpzioniAdmin;
		private CambiaPswAA 						ascoltatoreEtAzioneCambiaPswAA;
		
		private ChiudiPannelloAdmin3GestioneUtentiAA 	ascoltatoreEtAzioneChiudiPannelloAdmin3GestioneUtenti;
		
		int altezzaConfine;
		int altezzaPannelloAdmin2;
		int larghezzaPannelloAdmin2;
		int altezzaProssimoPannelloAdmin3;
		int locazioneProssimoPannelloAdmin4;
		int savelocazioneYPannelloAdmin2;
		
		public ConfineAministratoreAmministrazioneUtenti(Controllore controllore, int locazioneYPannelloAdmin2, boolean subitoOpzioni, boolean subitoGestioneUtenti)
		{
			
			pannelloAdmin2.setVisible(false);
			pannelloAdmin3OpzioniAdmin.setVisible(false);
			pannelloAdmin3GestioneUtenti.setVisible(false);
			//if (confineMittente!=null)
			//	StackFrame.pilaCornici.push(confineMittente);
			this.setTitle("Amministrazione Utenti Tavola Calda 'LA PERGOLA'");
			//questoConfine = this;
			this.controlloreCorrente = controllore;
			
			
			int lunghezzaScritta=300;
			int altezzaScritta = 30;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdmin2);
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin2.setLayout(null);
			pannelloAdmin2.setVisible(true);
			savelocazioneYPannelloAdmin2 = locazioneYPannelloAdmin2;
			//int locazioneYPannelloAdmin2 = ConfineAvvio.Confine.getHeight()/5 - 5;
			locazioneYPannelloAdmin2 += 5;
			altezzaPannelloAdmin2 = altezzaConfine/6;
			larghezzaPannelloAdmin2 = ConfineAvvio.Confine.getWidth();
			altezzaProssimoPannelloAdmin3 = locazioneYPannelloAdmin2 + altezzaPannelloAdmin2;
	        pannelloAdmin2.setBounds(0,locazioneYPannelloAdmin2,larghezzaPannelloAdmin2, altezzaPannelloAdmin2); //+100
			pannelloAdmin2.setBackground(new Color(190,190,190));
			
			int larghezzaBottoni = 150;			
			int altezzaBottoni = 30;
			
			labelGestioneUtenti = new JLabel("Gestione Admin e Utenti");
			labelGestioneUtenti.setBounds((larghezzaPannelloAdmin2/3)+30,10, lunghezzaScritta, altezzaScritta);
			labelGestioneUtenti.setFont(new Font("Verdana", Font.BOLD, 20));
			
			//Bottone "Opzioni Admin"
			bOpzioniAdmin = new JButton(new ImageIcon("Save/immagini/opzionia.gif"));
			bOpzioniAdmin.setRolloverIcon(new ImageIcon("Save/immagini/opzioniaR.gif"));
			bOpzioniAdmin.setBounds(250, (altezzaPannelloAdmin2/2), larghezzaBottoni, altezzaBottoni);
			bOpzioniAdmin.setBorderPainted(false);
			bOpzioniAdmin.setMargin (new Insets (0, 0, 0, 0));
			bOpzioniAdmin.setContentAreaFilled(false);
			bOpzioniAdmin.setOpaque(true);
			bOpzioniAdmin.setToolTipText("Impostazioni per utente Amministratore");
			
			//Bottone "Gestione Utenti"
			bGestioneUtenti = new JButton(new ImageIcon("Save/immagini/gestione_utenti.gif"));
			bGestioneUtenti.setRolloverIcon(new ImageIcon("Save/immagini/gestione_utentiR.gif"));
			bGestioneUtenti.setBounds(500, (altezzaPannelloAdmin2/2), larghezzaBottoni, altezzaBottoni);
			bGestioneUtenti.setBorderPainted(false);
			bGestioneUtenti.setMargin (new Insets (0, 0, 0, 0));
			bGestioneUtenti.setContentAreaFilled(false);
			bGestioneUtenti.setOpaque(true);
			bGestioneUtenti.setToolTipText("Gestione Account Utenti");
			
			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");
			
			pannelloAdmin2.add(labelGestioneUtenti);
			pannelloAdmin2.add(bOpzioniAdmin);
		    pannelloAdmin2.add(bGestioneUtenti);
		    pannelloAdmin2.add(bChiudiPannello);
			
			//Ascoltatori
		    ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
		    ascoltatoreEtAzioneOpzioniAdminAA = new OpzioniAdminAA();
		    ascoltatoreEtAzioneGestioneUtentiAA = new GestioneUtentiAA();
			
		    //Associazione
		    bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
		    bOpzioniAdmin.addActionListener(ascoltatoreEtAzioneOpzioniAdminAA);
		    bGestioneUtenti.addActionListener(ascoltatoreEtAzioneGestioneUtentiAA);
		    
		    
		    if (subitoOpzioni) creaPannelloAdmin3OpzioniAdmin(altezzaProssimoPannelloAdmin3);
		}
		// Fine costruttore
			
		
		private void creaPannelloAdmin3OpzioniAdmin(int locazioneYPannelloAdmin3OpzioniAdmin)
		{
			
			int lunghezzaScritta=300;
			int altezzaScritta = 30;
			int altezzaPannelloAdmin3OpzioniAdmin;
			int larghezzaPannelloAdmin3OpzioniAdmin;
			
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdmin3OpzioniAdmin);
			//ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin3OpzioniAdmin.setLayout(null);
			pannelloAdmin3OpzioniAdmin.setVisible(true);
			
			//int locazioneYPannelloAdmin2 = ConfineAvvio.Confine.getHeight()/5 - 5;
			locazioneYPannelloAdmin3OpzioniAdmin += 5;
			altezzaPannelloAdmin3OpzioniAdmin = altezzaConfine/6;
			larghezzaPannelloAdmin3OpzioniAdmin = ConfineAvvio.Confine.getWidth();
			locazioneProssimoPannelloAdmin4 = locazioneYPannelloAdmin3OpzioniAdmin + altezzaPannelloAdmin3OpzioniAdmin;
			pannelloAdmin3OpzioniAdmin.setBounds(0,locazioneYPannelloAdmin3OpzioniAdmin,larghezzaPannelloAdmin3OpzioniAdmin, altezzaPannelloAdmin3OpzioniAdmin); //+100
			pannelloAdmin3OpzioniAdmin.setBackground(new Color(190,190,190));
			
			int larghezzaBottoni = 150;			
			int altezzaBottoni = 30;
			
			JLabel labelOpzioniAdmin = new JLabel("Opzioni Amministratore");
			labelOpzioniAdmin.setBounds((larghezzaPannelloAdmin3OpzioniAdmin/3)+10, 10, lunghezzaScritta, altezzaScritta);
			labelOpzioniAdmin.setFont(new Font("Verdana", Font.BOLD, 20));

			//Bottone "Cambia Password"
			bCambiaPsw = new JButton(new ImageIcon("Save/immagini/cambia_passwd.gif"));
			bCambiaPsw.setRolloverIcon(new ImageIcon("Save/immagini/cambia_passwdR.gif"));
			bCambiaPsw.setBounds(((larghezzaPannelloAdmin3OpzioniAdmin-larghezzaBottoni)/2),(altezzaPannelloAdmin3OpzioniAdmin/2), larghezzaBottoni, altezzaBottoni);
			bCambiaPsw.setBorderPainted(false);
			bCambiaPsw.setMargin (new Insets (0, 0, 0, 0));
			bCambiaPsw.setContentAreaFilled(false);
			bCambiaPsw.setOpaque(true);
			bCambiaPsw.setToolTipText("Cambia Password Amministratore");
			
			//"X" chiusura Pannello	
			bChiudiPannelloOpzioniAdmin = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannelloOpzioniAdmin.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannelloOpzioniAdmin.setBounds(827, 5, 48, 20);
			bChiudiPannelloOpzioniAdmin.setBorderPainted(false);
			bChiudiPannelloOpzioniAdmin.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannelloOpzioniAdmin.setContentAreaFilled(false);
			bChiudiPannelloOpzioniAdmin.setOpaque(true);
			bChiudiPannelloOpzioniAdmin.setToolTipText("Chiusura Pannello");
			
			pannelloAdmin3OpzioniAdmin.add(labelOpzioniAdmin);
			pannelloAdmin3OpzioniAdmin.add(bCambiaPsw);;
			pannelloAdmin3OpzioniAdmin.add(bChiudiPannelloOpzioniAdmin);
			
			//Ascoltatori
		    ascoltatoreEtAzioneChiudiPannelloAdmin3OpzioniAdmin = new ChiudiPannelloAdmin3OpzioniAdminAA();
		    ascoltatoreEtAzioneCambiaPswAA = new CambiaPswAA();
			
			
		    //Associazione
		    bChiudiPannelloOpzioniAdmin.addActionListener(ascoltatoreEtAzioneChiudiPannelloAdmin3OpzioniAdmin);
		    bCambiaPsw.addActionListener(ascoltatoreEtAzioneCambiaPswAA);
			
		} // Fine creaPannelloAdmin3OpzioniAdmin()
		
		private void creaPannelloAdmin3GestioneUtenti(int locazioneYPannelloAdmin3GestioneUtenti)
		{
			
			int lunghezzaScritta=300;
			int altezzaScritta = 30;
			int altezzaPannelloAdmin3GestioneUtenti;
			int larghezzaPannelloAdmin3GestioneUtenti;
			int distanzaTraBottoni = 40;
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdmin3GestioneUtenti);
			//ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin3GestioneUtenti.setLayout(null);
			pannelloAdmin3GestioneUtenti.setVisible(true);
			
			//int locazioneYPannelloAdmin2 = ConfineAvvio.Confine.getHeight()/5 - 5;
			locazioneYPannelloAdmin3GestioneUtenti += 5;
			altezzaPannelloAdmin3GestioneUtenti = altezzaConfine/6;
			larghezzaPannelloAdmin3GestioneUtenti = ConfineAvvio.Confine.getWidth();
			locazioneProssimoPannelloAdmin4 = locazioneYPannelloAdmin3GestioneUtenti + altezzaPannelloAdmin3GestioneUtenti;
			pannelloAdmin3GestioneUtenti.setBounds(0,locazioneYPannelloAdmin3GestioneUtenti,larghezzaPannelloAdmin3GestioneUtenti, altezzaPannelloAdmin3GestioneUtenti);
			pannelloAdmin3GestioneUtenti.setBackground(new Color(190,190,190));
			
			int larghezzaBottoni = 150;			
			int altezzaBottoni = 30;
			
			JLabel labelGestioneUtenti = new JLabel("Gestione Utenti");
			labelGestioneUtenti.setBounds((larghezzaPannelloAdmin3GestioneUtenti/3)+65,10, lunghezzaScritta, altezzaScritta);
			labelGestioneUtenti.setFont(new Font("Verdana", Font.BOLD, 20));
			
			int locazioneXBottoneListaUtenti = larghezzaPannelloAdmin3GestioneUtenti/15 + 120;
			//Bottone "Lista Utenti"
			bListaUtenti = new JButton(new ImageIcon("Save/immagini/lista_u.gif"));
			bListaUtenti.setRolloverIcon(new ImageIcon("Save/immagini/lista_uR.gif"));
			bListaUtenti.setBounds(locazioneXBottoneListaUtenti,(altezzaPannelloAdmin3GestioneUtenti/2), larghezzaBottoni, altezzaBottoni);
			bListaUtenti.setBorderPainted(false);
			bListaUtenti.setMargin (new Insets (0, 0, 0, 0));
			bListaUtenti.setContentAreaFilled(false);
			bListaUtenti.setOpaque(true);
			bListaUtenti.setToolTipText("Visualizza Lista Utenti");
			
			int locazioneXBottoneNuovoUtente = locazioneXBottoneListaUtenti + larghezzaBottoni+ distanzaTraBottoni; 
			//Bottone "Nuovo Utente"
			bNuovoUtente = new JButton(new ImageIcon("Save/immagini/nuovo_u.gif"));
			bNuovoUtente.setRolloverIcon(new ImageIcon("Save/immagini/nuovo_uR.gif"));
			bNuovoUtente.setBounds(locazioneXBottoneNuovoUtente,(altezzaPannelloAdmin3GestioneUtenti/2), larghezzaBottoni, altezzaBottoni);
			bNuovoUtente.setBorderPainted(false);
			bNuovoUtente.setMargin (new Insets (0, 0, 0, 0));
			bNuovoUtente.setContentAreaFilled(false);
			bNuovoUtente.setOpaque(true);
			bNuovoUtente.setToolTipText("Crea Nuovo Utente");
			
			int locazioneXBottoneCancUtente = locazioneXBottoneNuovoUtente + larghezzaBottoni+ distanzaTraBottoni; 
			//Bottone "Cancella Utente"
			bCancUtente = new JButton(new ImageIcon("Save/immagini/cancella_u.gif"));
			bCancUtente.setRolloverIcon(new ImageIcon("Save/immagini/cancella_uR.gif"));
			bCancUtente.setBounds(locazioneXBottoneCancUtente,(altezzaPannelloAdmin3GestioneUtenti/2), larghezzaBottoni, altezzaBottoni);
			bCancUtente.setBorderPainted(false);
			bCancUtente.setMargin (new Insets (0, 0, 0, 0));
			bCancUtente.setContentAreaFilled(false);
			bCancUtente.setOpaque(true);
			bCancUtente.setToolTipText("Cancella Utente");
			
			//"X" chiusura Pannello	
			bChiudiPannelloGestioneUtenti = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannelloGestioneUtenti.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannelloGestioneUtenti.setBounds(827, 5, 48, 20);
			bChiudiPannelloGestioneUtenti.setBorderPainted(false);
			bChiudiPannelloGestioneUtenti.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannelloGestioneUtenti.setContentAreaFilled(false);
			bChiudiPannelloGestioneUtenti.setOpaque(true);
			bChiudiPannelloGestioneUtenti.setToolTipText("Chiusura Pannello");
			
			pannelloAdmin3GestioneUtenti.add(labelGestioneUtenti);
			pannelloAdmin3GestioneUtenti.add(bListaUtenti);
			pannelloAdmin3GestioneUtenti.add(bNuovoUtente);
			pannelloAdmin3GestioneUtenti.add(bCancUtente);
			pannelloAdmin3GestioneUtenti.add(bChiudiPannelloGestioneUtenti);
			
			//Ascoltatori
		    ascoltatoreEtAzioneChiudiPannelloAdmin3GestioneUtenti = new ChiudiPannelloAdmin3GestioneUtentiAA();
		    ascoltatoreEtAzioneListaUtenti = new ListaUtentiAA();
		    ascoltatoreEtAzioneNuovoUtente = new NuovoUtenteAA();
		    ascoltatoreEtAzioneCancellaUtente = new CancellaUtenteAA();
			
			
		    //Associazione
		    bChiudiPannelloGestioneUtenti.addActionListener(ascoltatoreEtAzioneChiudiPannelloAdmin3GestioneUtenti);
		    bListaUtenti.addActionListener(ascoltatoreEtAzioneListaUtenti);
		    bNuovoUtente.addActionListener(ascoltatoreEtAzioneNuovoUtente);
		    bCancUtente.addActionListener(ascoltatoreEtAzioneCancellaUtente);
			
		} //Fine creaPannelloAdmin3GestioneUtenti()
	
		
		private class ListaUtentiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per freccetta tendina, rendo invisibile e visibile il pannello che lascio!
				pannelloAdmin3GestioneUtenti.setVisible(false);
				pannelloAdmin3GestioneUtenti.setVisible(true);
				
				bListaUtenti.setEnabled(false);
				bNuovoUtente.setEnabled(false);
				bCancUtente.setEnabled(false);
				bChiudiPannelloGestioneUtenti.setEnabled(false);
				new ConfineAmministratoreAmministrazioneListaUtenti(controlloreCorrente, locazioneProssimoPannelloAdmin4);
			}
		}
		
		//Vecchia soluzione. Solo amministratore poteva cambiare password utente. Da modiificare.
		private class CambiaPswAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
			//	questoConfine.setVisible(false);
				bChiudiPannelloOpzioniAdmin.setEnabled(false);
				bCambiaPsw.setEnabled(false);
				new ConfineAmministratoreAmministrazioneUtentiCambiaPassword(controlloreCorrente, locazioneProssimoPannelloAdmin4);			
			}
		}
		private class NuovoUtenteAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//try
				//{
					//Importante per freccetta tendina
					pannelloAdmin3GestioneUtenti.setVisible(false);
					pannelloAdmin3GestioneUtenti.setVisible(true);
					
					bListaUtenti.setEnabled(false);
					bNuovoUtente.setEnabled(false);
					bCancUtente.setEnabled(false);
					bChiudiPannelloGestioneUtenti.setEnabled(false);
					new ConfineAmministratoreAmministrazioneUtentiNuovoUtente(controlloreCorrente, locazioneProssimoPannelloAdmin4);
				//}
				//catch (/*SerializzazioneException */ Exception e)
				//{
				//	JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneUtentiNuovoUtenteAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				//}
			}
		}
		private class CancellaUtenteAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per freccetta tendina
				pannelloAdmin3GestioneUtenti.setVisible(false);
				pannelloAdmin3GestioneUtenti.setVisible(true);
				
				bListaUtenti.setEnabled(false);
				bNuovoUtente.setEnabled(false);
				bCancUtente.setEnabled(false);
				bChiudiPannelloGestioneUtenti.setEnabled(false);
				new ConfineAmministratoreAmministrazioneUtentiCancellaUtente(controlloreCorrente, locazioneProssimoPannelloAdmin4);

			}
		}
		
		private class OpzioniAdminAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					bGestioneUtenti.setEnabled(false);
					bOpzioniAdmin.setEnabled(false);
					bChiudiPannello.setEnabled(false);
					
/*SE QUALCOSA NON VA RIATTIVARLO!! */ //pannelloAdmin3OpzioniAdmin.setVisible(false); 
					
					creaPannelloAdmin3OpzioniAdmin(altezzaProssimoPannelloAdmin3);
				}
				catch (/*SerializzazioneException */ Exception e)
				{
					JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneOpzioniAdminAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		}	
		
		private class GestioneUtentiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					bGestioneUtenti.setEnabled(false);
					bOpzioniAdmin.setEnabled(false);
					bChiudiPannello.setEnabled(false);
					
					pannelloAdmin3GestioneUtenti.setVisible(false);
					
					creaPannelloAdmin3GestioneUtenti(altezzaProssimoPannelloAdmin3);
				}
				catch (/*SerializzazioneException */ Exception e)
				{
					JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneOpzioniAdminAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		}	
		
		private class ChiudiPannelloAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				pannelloAdmin2.setVisible(false);
			
				//ConfineAvvio.Confine.remove(pannelloAdmin2);
				ConfineAministratoreAmministrazione.bGestioneUtenti.setEnabled(true);
				ConfineAministratoreAmministrazione.bGestioneProdotti.setEnabled(true);
				ConfineAministratoreAmministrazione.bGestioneIndici.setEnabled(true);
				ConfineAministratoreAmministrazione.bCambiaUtente.setEnabled(true);
				ConfineAministratoreAmministrazione.bSospendi.setEnabled(true);
				ConfineAministratoreAmministrazione.bLog.setEnabled(true);
				ConfineAministratoreAmministrazione.bMenu.setEnabled(true);
			}
		}
		
		private class ChiudiPannelloAdmin3OpzioniAdminAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean subitoOpzioni = false;
				boolean subitoGestioneUtenti = false;
				pannelloAdmin3OpzioniAdmin.setVisible(false);
				//ConfineAvvio.Confine.remove(pannelloAdmin3OpzioniAdmin);
				//ConfineAvvio.Confine.remove(pannelloAdmin2);
				bGestioneUtenti.setEnabled(true);
				bOpzioniAdmin.setEnabled(true);
				bChiudiPannello.setEnabled(true);
				pannelloAdmin2.setVisible(false); //Importante!! Alla Chiusura del pannello nella Funzione, settare Invisibile il pannello che si richiama
				new ConfineAministratoreAmministrazioneUtenti(controlloreCorrente, savelocazioneYPannelloAdmin2, subitoOpzioni, subitoGestioneUtenti);
			}
		}
		
		private class ChiudiPannelloAdmin3GestioneUtentiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean subitoOpzioni = false;
				boolean subitoGestioneUtenti = false;
				pannelloAdmin3GestioneUtenti.setVisible(false);
				//ConfineAvvio.Confine.remove(pannelloAdmin3OpzioniAdmin);
				//ConfineAvvio.Confine.remove(pannelloAdmin2);
				bGestioneUtenti.setEnabled(true);
				bOpzioniAdmin.setEnabled(true);
				bChiudiPannello.setEnabled(true);
				pannelloAdmin2.setVisible(false); //Importante!!
				new ConfineAministratoreAmministrazioneUtenti(controlloreCorrente, savelocazioneYPannelloAdmin2, subitoOpzioni, subitoGestioneUtenti);
			}
		}
	
}
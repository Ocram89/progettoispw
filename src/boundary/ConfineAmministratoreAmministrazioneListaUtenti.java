package boundary;


import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
// import java.io.File;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JComboBox;
//import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JOptionPane;
import javax.swing.JPanel;
//import javax.swing.JTextField;

//my -----------
import control.*;
import util.*;

public class ConfineAmministratoreAmministrazioneListaUtenti
{
	private static final long	serialVersionUID	= 1L;
	private Controllore controlloreCorrente =null;
	
	//Pannelli
	public JPanel  	pannelloAdmin4 = new JPanel();
	
	//Label Titolo
	public JLabel     labelListaUtenti;
	
	//Bottone/
	public JButton 	bChiudiPannello;
	public JButton 	bAggiornaLista;
	
	public JTextArea listaUtentiAdmin;
	public JScrollPane scrollListaUtentiAdmin;
	
	public JTextArea listaUtentiCassiere;
	public JScrollPane scrollListaUtentiCassiere;
	
	public JTextArea listaUtentiFornitore;
	public JScrollPane scrollListaUtentiFornitore;
	
	public JTextArea listaUtentiGestore;
	public JScrollPane scrollListaUtentiGestore;
	
	public JTextArea listaUtentiAnonimo;
	public JScrollPane scrollListaUtentiAnonimo;
	
	
	//Ascoltatore tendina
	private ChiudiPannelloAA	ascoltatoreEtAzioneChiudiPannello;
	private AggiornaListaAA 	ascoltatoreEtAzioneAggiornaLista;
	
	int altezzaConfine;
	int altezzaPannelloAdmin4;
	int larghezzaPannelloAdmin4;
	int locazioneYPrimaRiga;
	
	public ConfineAmministratoreAmministrazioneListaUtenti(Controllore controlloreCorrente, int locazioneYPannelloAdmin4)
	{
		this.controlloreCorrente = controlloreCorrente;
		
		int lunghezzaScrittaTitolo=200;
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
		
		int larghezzaBottoni = 100;
		int altezzaBottoni = 20;
		//TITOLO
		labelListaUtenti = new JLabel("Lista Utenti");
		labelListaUtenti.setBounds((larghezzaPannelloAdmin4/2)-70,10, lunghezzaScrittaTitolo, altezzaScritta);
		labelListaUtenti.setFont(new Font("Arial", 0, 20));
		
		
		int altezzaListaUtenti = altezzaPannelloAdmin4-50;
		int larghezzaListe = 150;
		
		//Prima Area
		int locazioneXListaAdmin = 5;
		listaUtentiAdmin = new JTextArea();
		//listaUtentiAdmin.setEditable(false);
		listaUtentiAdmin.setLineWrap(true);
		listaUtentiAdmin.append("     AMMINISTRATORI:\n");
		//listaUtenti.setTabSize(larghezzaListaUtenti/2);
		scrollListaUtentiAdmin = new JScrollPane(listaUtentiAdmin);
		scrollListaUtentiAdmin.setBounds(locazioneXListaAdmin, 40, larghezzaListe, altezzaListaUtenti);
		
		//Seconda Area
		int locazioneXListaCassieri = locazioneXListaAdmin + larghezzaListe +5;
		listaUtentiCassiere = new JTextArea();
		listaUtentiCassiere.setEditable(false);
		listaUtentiCassiere.setLineWrap(true);
		listaUtentiCassiere.append("           CASSIERI:\n");
		//listaUtenti.setTabSize(larghezzaListaUtenti/2);
		scrollListaUtentiCassiere = new JScrollPane(listaUtentiCassiere);
		scrollListaUtentiCassiere.setBounds(locazioneXListaCassieri, 40, larghezzaListe, altezzaListaUtenti);
		
		//Terza Area
		int locazioneXListaFornitori = locazioneXListaCassieri + larghezzaListe +5;
		listaUtentiFornitore = new JTextArea();
		listaUtentiFornitore.setEditable(false);
		listaUtentiFornitore.setLineWrap(true);
		listaUtentiFornitore.append("         FORNITORI:\n");
		//listaUtenti.setTabSize(larghezzaListaUtenti/2);
		scrollListaUtentiFornitore = new JScrollPane(listaUtentiFornitore);
		scrollListaUtentiFornitore.setBounds(locazioneXListaFornitori, 40, larghezzaListe, altezzaListaUtenti);
		
		//Quarta Area
		int locazioneXListaGestori = locazioneXListaFornitori + larghezzaListe +5;
		listaUtentiGestore = new JTextArea();
		listaUtentiGestore.setEditable(false);
		listaUtentiGestore.setLineWrap(true);
		listaUtentiGestore.append("          GESTORI:\n");
		//listaUtenti.setTabSize(larghezzaListaUtenti/2);
		scrollListaUtentiGestore = new JScrollPane(listaUtentiGestore);
		scrollListaUtentiGestore.setBounds(locazioneXListaGestori, 40, larghezzaListe, altezzaListaUtenti);
		
		//Quinta Area
		int locazioneXListaAnonimi = locazioneXListaGestori + larghezzaListe +5;
		listaUtentiAnonimo = new JTextArea();
		listaUtentiAnonimo.setEditable(false);
		listaUtentiAnonimo.setLineWrap(true);
		listaUtentiAnonimo.append("          ANONIMI:\n");
		//listaUtenti.setTabSize(larghezzaListaUtenti/2);
		scrollListaUtentiAnonimo = new JScrollPane(listaUtentiAnonimo);
		scrollListaUtentiAnonimo.setBounds(locazioneXListaAnonimi, 40, larghezzaListe, altezzaListaUtenti);
		
		int locazioneXBottAggiornaLista = locazioneXListaAnonimi + larghezzaListe +5;
		//Bottone "Aggiorna Lista"
		bAggiornaLista = new JButton(new ImageIcon("Save/immagini/aggiorna_u.gif"));
		bAggiornaLista.setRolloverIcon(new ImageIcon("Save/immagini/aggiorna_uR.gif"));
		bAggiornaLista.setBounds(locazioneXBottAggiornaLista,100, larghezzaBottoni, altezzaBottoni);
		bAggiornaLista.setBorderPainted(false);
		bAggiornaLista.setMargin (new Insets (0, 0, 0, 0));
		bAggiornaLista.setContentAreaFilled(false);
		bAggiornaLista.setOpaque(true);
		bAggiornaLista.setToolTipText("Aggiona liste utenti");
		
		
		//"X" chiusura Pannello	
		bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
		bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
		bChiudiPannello.setBounds(827, 5, 48, 20);
		bChiudiPannello.setBorderPainted(false);
		bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
		bChiudiPannello.setContentAreaFilled(false);
		bChiudiPannello.setOpaque(true);
		bChiudiPannello.setToolTipText("Chiusura Pannello");
		
		pannelloAdmin4.add(labelListaUtenti);
		pannelloAdmin4.add(scrollListaUtentiAdmin);
		pannelloAdmin4.add(scrollListaUtentiCassiere);
		pannelloAdmin4.add(scrollListaUtentiFornitore);
		pannelloAdmin4.add(scrollListaUtentiGestore);
		pannelloAdmin4.add(scrollListaUtentiAnonimo);
		pannelloAdmin4.add(bAggiornaLista);
		pannelloAdmin4.add(bChiudiPannello);
		
		
		//Ascoltatori
	    ascoltatoreEtAzioneChiudiPannello	= new ChiudiPannelloAA();
	    ascoltatoreEtAzioneAggiornaLista	= new AggiornaListaAA(); 	
	    //Associazione
	    bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);	
	    bAggiornaLista.addActionListener(ascoltatoreEtAzioneAggiornaLista);
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
	
	private class AggiornaListaAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				listaUtentiAdmin.setText("     AMMINISTRATORI:\n");
				listaUtentiCassiere.setText("           CASSIERI:\n");
				listaUtentiFornitore.setText("         FORNITORI:\n");
				listaUtentiGestore.setText("          GESTORI:\n");
				listaUtentiAnonimo.setText("          ANONIMI:\n");
				
				int admin = 0;
				int cass = 0;
				int forn = 0;
				int gest = 0;
				int anon = 0;
				Vector<Utente> utenti = ControlloreLogin.getUtenti();
				for (Utente u: utenti)
				{
					if(u.getRuolo().equals("AMMINISTRATORE"))
					{
						admin++;
						listaUtentiAdmin.append(admin+". "+u.getLogin()+"\n");
					}
					if(u.getRuolo().equals("CASSIERE"))
					{
						cass++;
						listaUtentiCassiere.append(cass+". "+u.getLogin()+"\n");
					}
					if(u.getRuolo().equals("FORNITORE"))
					{
						forn++;
						listaUtentiFornitore.append(forn+". "+u.getLogin()+"\n");
					}
					if(u.getRuolo().equals("GESTORE"))
					{
						gest++;
						listaUtentiGestore.append(gest+". "+u.getLogin()+"\n");
					}
					if(u.getRuolo().equals("ANONIMO"))
					{
						anon++;
						listaUtentiAnonimo.append(anon+". "+u.getLogin()+"\n");
					}
					
				}				
			}
	}
}

package boundary;


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
//	import javax.swing.JComboBox;
//	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//	import java.util.*;
//	import javax.swing.JTextField;

//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;
	
public class ConfineGestore
{
	
	// Relazione con controllore
			private Controllore controlloreCorrente;
			
			//Barra dei Menù
			JMenuBar barraMenu = new JMenuBar();
			JMenu file;
			JMenuItem nuovoItem;
			JMenuItem caricaItem;
			JMenuItem salvaItem;
			JMenuItem chiudiItem;
			/*JMenuItem salvaLogItem;
			JMenuItem creaNuovoLogItem;*/
			
			//Pannelli
			public  JPanel 	pannelloGestore1				= new JPanel(); 
			public  JPanel 	pannelloGestore2				= new JPanel(); 
			public  JPanel 	pannelloGestore2Gestione		= new JPanel();
			public 	JPanel	pannelloTitolo 					= new JPanel();
			public  JPanel	pannelloBottoniGestore			= new JPanel();	
			public  JPanel 	pannelloGestoreSospensione 		= new JPanel();
			
			//JLabel
			public JLabel 	titoloConnesso					= new JLabel();
			
			//Bottoni
			public static	JButton		bGestione;
			public static	JButton 	bIndici; 
			public static	JButton 	bMenu; 	

			public static	JButton 	bEsci;
			public static	JButton 	bCambiaUtente; 
			public static	JButton 	bSospendi; 
				
			//PannelloGestore3
			public JLabel 		labelGestioneProdotti;
			
			public static JButton 		bAggiuntaProdotti;
			public static JButton 		bRimozioneProdotti;
			public static JButton 		bChiudiPannelloGestore2Gestione;
						
			
			// Ascoltatori BARRA DEI MENU
			private NuovaGestioneAA     ascoltatoreEtAzioneNuovaGestione;
			private SerializzaAA		ascoltatoreEtAzioneSerializza;
			private DeserializzaAA		ascoltatoreEtAzioneDeserializza;
			//private SalvaLogAA			ascoltatoreEtAzioneSalvaLog;
			//private CreaNuovoLogAA		ascoltatoreEtAzioneCreaNuovoLog;
			

			//Ascoltatori Gestore1 e 2
			private GestioneProdottiAA 				ascoltatoreEtAzioneGestioneProdotti;
			private MenuAA							ascoltatoreEtAzioneMenu;
			private GestioneIndiciAA				ascoltatoreEtAzioneGestioneIndici;
			private CambiaUtenteAA					ascoltatoreEtAzioneCambiaUtente;
			private SospendiAA 						ascoltatoreEtAzioneSospendi;
			private EsciAA 							ascoltatoreEtAzioneEsci;
			private RiprendiGestoreAA				ascoltatoreEtAzioneRiprendiGestore;
			
			//Ascoltatori pannelloGestore3Gestione
			private AggiuntaProdottiAA					ascoltatoreAggiuntaProdotti;
			private RimozioneProdottiAA					ascoltatoreRimozioneProdotti;

			private ChiudiPannelloGestore2GestioneAA	ascoltatoreChiudiPannelloGestore2Gestione;
			
			
			//Per Pannelli Fornitore1 e Fornitore2
			int altezzaConfine;
			int larghezzaConfine;
			int larghezzaPannelli;
			int altezzaPannelloGestore1;
			int altezzaPannelloGestore2;
			public static int locazioneYProssimoPannello;
			
			int altezzaBarraMenu;
			//Per Pannello Forniture
			int altezzaPannelloGestore2Gestione;
			int larghezzaPannelloGestore2Gestione;
			
			int locazioneProssimoPannelloGestore3;
			
			public ConfineGestore(Controllore controllore, boolean subitoGestioneProdotti)
			{
				this.controlloreCorrente= controllore;
				
				int altezzaBottoni = 40;
				int altezzaBottoniTitolo;
				int larghezzaBottoniTitolo;
				int bordoPannelli = 5;
				int bordoBottoni = 30;
				int larghezzaBottoni=200; //Da inizializzare dopo!!
				int altezzaPannelloTitoloGestore = 40;
				int altezzaPannelloGestoreIndici;
				
				altezzaConfine = ConfineAvvio.Confine.getHeight();
				larghezzaConfine = ConfineAvvio.Confine.getWidth();
				altezzaBarraMenu = 25;
				larghezzaPannelli = larghezzaConfine;
				altezzaPannelloGestore1 = altezzaConfine/5 - 10;
				locazioneYProssimoPannello = altezzaPannelloGestore1+ altezzaBarraMenu;
				// Costruisco pannello Amministratore
				ConfineAvvio.Confine.setLayout(null);
				pannelloGestore1.setBounds(0,altezzaBarraMenu,larghezzaPannelli, altezzaPannelloGestore1);
				//pannelloGestore1.setSize(larghezzaPannelli, altezzaPannelloGestore1);
				pannelloGestore1.setBackground(new Color(200, 200, 200));
				pannelloGestore1.setLayout(null);
				ConfineAvvio.Confine.add(pannelloGestore1);
				//pannelloGestore1.setVisible(true);
				
				altezzaPannelloGestore2 = altezzaConfine - altezzaPannelloGestore1 -43;
				pannelloGestore2.setLayout(null);
				pannelloGestore2.setBounds(0,altezzaPannelloGestore1+5+altezzaBarraMenu,larghezzaPannelli, altezzaPannelloGestore2);
				pannelloGestore2.setBackground(new Color(190, 190, 190));
				ConfineAvvio.Confine.add(pannelloGestore2);
				pannelloGestore2.setVisible(true);
								
				//Barra dei Menu
				barraMenu.setBounds(0, 0, larghezzaPannelli, altezzaBarraMenu);
				ConfineAvvio.Confine.add(barraMenu);
				
				file = new JMenu("File");
				barraMenu.add(file);
				
				nuovoItem = new JMenuItem("Nuovo Magazzino");
				file.add(nuovoItem);
				
				caricaItem = new JMenuItem("Carica Magazzino");
				file.add(caricaItem);
				
				salvaItem = new JMenuItem("Salva Magazzino");
				file.add(salvaItem);
				
				/*salvaLogItem = new JMenuItem("Salva Log Operazioni");
				file.add(salvaLogItem);
				
				creaNuovoLogItem = new JMenuItem("Crea Nuovo Log Operazioni");
				file.add(creaNuovoLogItem);*/
				
				chiudiItem = new JMenuItem("Esci dal Programma");
				file.add(chiudiItem);
				
				
				//COSTRUISCO PANNELLO TITOLO
				pannelloTitolo.setLayout(null);
		        pannelloTitolo.setBackground(new Color(190, 190, 190));
		        pannelloTitolo.setBounds(0, 5, larghezzaPannelli, altezzaPannelloTitoloGestore); 
		        pannelloTitolo.add(titoloConnesso); 
		        
		        //COMPONENTI DEL PANNELLO TITOLO:
		        //1.Titolo
		        titoloConnesso.setFont(new Font("Verdana", Font.BOLD, 20));
		        titoloConnesso.setLocation(0, 0);
		        titoloConnesso.setSize(larghezzaPannelli, altezzaPannelloTitoloGestore);
		        titoloConnesso.setHorizontalAlignment(JLabel.CENTER);
		        titoloConnesso.setVerticalAlignment(JLabel.CENTER);
		        titoloConnesso.setText("Sei Connesso come GESTORE");
				
		        //2.Bottone Indietro
		        larghezzaBottoniTitolo = 150;
		        altezzaBottoniTitolo = 30;
				/*bRitorna 			= new JButton("INDIETRO"); 
				bRitorna.setLocation(5,(altezzaPannelloTitolo/2)-(altezzaBottoniTitolo/2));
		        bRitorna.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);*/
		        
		        //Bottone Cambia Utente
		        bCambiaUtente = new JButton(new ImageIcon("Save/immagini/cambia_utente.gif"));
		        bCambiaUtente.setRolloverIcon(new ImageIcon("Save/immagini/cambia_utentiR.gif"));
		        bCambiaUtente.setBorderPainted(false);
		        bCambiaUtente.setMargin (new Insets (0, 0, 0, 0));
		        bCambiaUtente.setContentAreaFilled(false);
		        bCambiaUtente.setOpaque(true);
		        bCambiaUtente.setToolTipText("Cambia Utente");
		        bCambiaUtente.setBounds(750, 0, 42, 42);
				
		        /*int locazioneYBott = (altezzaPannelloTitoloGestore/2)-(altezzaBottoniTitolo/2);
		        bCambiaUtente 		= new JButton("CAMBIA UTENTE"); 
				bCambiaUtente.setLocation(larghezzaPannelli - larghezzaBottoniTitolo - bordoBottoni, locazioneYBott);
		        bCambiaUtente.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);
		        bCambiaUtente.setBackground(Color.orange);
		        
		        bSospendi 		= new JButton("SOSPENDI"); 
				bSospendi.setLocation(12 ,locazioneYBott);
		        bSospendi.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);
		        bSospendi.setBackground(Color.orange); */
		        
		        //Bottone Sospendi
		        bSospendi = new JButton(new ImageIcon("Save/immagini/sospendi.gif"));
		        bSospendi .setRolloverIcon(new ImageIcon("Save/immagini/sospendiR.gif"));
		        bSospendi .setBorderPainted(false);
		        bSospendi .setMargin (new Insets (0, 0, 0, 0));
		        bSospendi .setContentAreaFilled(false);
		        bSospendi .setOpaque(true);
		        bSospendi .setToolTipText("Sospensione");
		        bSospendi .setBounds(800, 0, 42, 42);
		        
		        //Bottone Esci
		        bEsci = new JButton(new ImageIcon("Save/immagini/esci.gif"));
		        bEsci.setRolloverIcon(new ImageIcon("Save/immagini/esciR.gif"));
		        bEsci.setBorderPainted(false);
		        bEsci.setMargin (new Insets (0, 0, 0, 0));
		        bEsci.setContentAreaFilled(false);
		        bEsci.setOpaque(true);
		        bEsci.setToolTipText("Esci");
		        bEsci.setBounds(850, 0, 42, 42);
				
		        //CREAZIONE PANNELLO BOTTONI
		        pannelloBottoniGestore.setLayout(null);
		        pannelloBottoniGestore.setBounds(0, bordoPannelli*2 +altezzaPannelloTitoloGestore,larghezzaPannelli, altezzaConfine/10);
		        pannelloBottoniGestore.setBackground(new Color(190, 190, 190));
		        
		        //System.out.println(altezzaPannelloBottoniAdmin);
		        
		         //Attenzione: larghezzaBottoni deve essere inizializzato QUI! per via di pannelloBottoni.
		        //larghezzaBottoni = pannelloBottoniGestore.getWidth()/5;  

		        //COMPONENTI PANNELLO BOTTONI	
		               
		        // Bottone Eroga
		        int altezzaPannelloBottoniForniture = pannelloBottoniGestore.getHeight();
		        int locazioneYBottoni = (altezzaPannelloBottoniForniture/2)-(altezzaBottoni/2);
		        
		        
		        //Bottone Menu
		        bMenu= new JButton(new ImageIcon("Save/immagini/menu40p.gif"));
		        bMenu.setRolloverIcon(new ImageIcon("Save/immagini/menu40pR.gif"));
		        bMenu.setBorderPainted(false);
		        bMenu.setMargin (new Insets (0, 0, 0, 0));
		        bMenu.setContentAreaFilled(false);
		        bMenu.setOpaque(true);
		        bMenu.setToolTipText("Visualizza Menù");
		        bMenu.setBounds(100,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
		        
		        /*
		        int locazioneXBottoneMenu = (larghezzaPannelli-(larghezzaBottoni*3))/3;
		        bMenu	= new JButton("MENU'"); 
		        bMenu.setBounds(locazioneXBottoneMenu,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
		        bMenu.setBackground(Color.green);
		        
		        int locazioneXBottonebGestione = locazioneXBottoneMenu + larghezzaBottoni + 60;
		        bGestione	= new JButton("GESTIONE"); 
		        bGestione.setBounds(locazioneXBottonebGestione,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
		        bGestione.setBackground(Color.magenta);*/
		        
		        //Bottone Gestione
		        bGestione= new JButton(new ImageIcon("Save/immagini/gestione_prodotti.gif"));
		        bGestione.setRolloverIcon(new ImageIcon("Save/immagini/gestione_prodottiR.gif"));
		        bGestione.setBorderPainted(false);
		        bGestione.setMargin (new Insets (0, 0, 0, 0));
		        bGestione.setContentAreaFilled(false);
		        bGestione.setOpaque(true);
		        bGestione.setToolTipText("Aggiunta/Rimozione Prodotti");
		        bGestione.setBounds(350,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
		        
		        //Bottone Indici
		        bIndici= new JButton(new ImageIcon("Save/immagini/indici_gestione.gif"));
		        bIndici.setRolloverIcon(new ImageIcon("Save/immagini/indici_gestioneR.gif"));
		        bIndici.setBorderPainted(false);
		        bIndici.setMargin (new Insets (0, 0, 0, 0));
		        bIndici.setContentAreaFilled(false);
		        bIndici.setOpaque(true);
		        bIndici.setToolTipText("Indici Gestore");
		        bIndici.setBounds(600,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
		       
		        /*
		        //4. Bottone ESCI
		        int locazioneXBottEsci = locazioneXBottonebGestione + larghezzaBottoni + 60;
				bEsci = new JButton("ESCI"); 
				bEsci.setBounds(locazioneXBottEsci, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
				bEsci.setBackground(Color.red);
				*/
				
	 //AGGIUNTA BOTTONI AI VARI PANNELLI
				
				pannelloTitolo.add(bCambiaUtente);
				pannelloTitolo.add(bSospendi);
				pannelloTitolo.add(bEsci);
				
				pannelloBottoniGestore.add(bMenu);
				pannelloBottoniGestore.add(bGestione);
				pannelloBottoniGestore.add(bIndici);
			    
				 // Aggiunta dei Vari Pannelli al PANNELLO PRINCIPALE
		        pannelloGestore1.add(pannelloTitolo);
		        pannelloGestore1.add(pannelloBottoniGestore);
		        
		        // Definisci ascoltatori di bottoni e relative azioni
		        ascoltatoreEtAzioneGestioneProdotti	= new GestioneProdottiAA();
		        ascoltatoreEtAzioneMenu				= new MenuAA();
		        ascoltatoreEtAzioneGestioneIndici	= new GestioneIndiciAA();
		        ascoltatoreEtAzioneEsci				= new EsciAA();
		        
				ascoltatoreEtAzioneCambiaUtente		= new CambiaUtenteAA();
				ascoltatoreEtAzioneSospendi			= new SospendiAA();
				
				//Ascoltatoti BARRA
				ascoltatoreEtAzioneNuovaGestione    = new NuovaGestioneAA();
				ascoltatoreEtAzioneSerializza		= new SerializzaAA();
				ascoltatoreEtAzioneDeserializza     = new DeserializzaAA();
				//ascoltatoreEtAzioneSalvaLog			= new SalvaLogAA();
				//ascoltatoreEtAzioneCreaNuovoLog		= new CreaNuovoLogAA();
				
				bEsci.addActionListener(ascoltatoreEtAzioneEsci);
				//bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
				bGestione.addActionListener(ascoltatoreEtAzioneGestioneProdotti);
				bMenu.addActionListener(ascoltatoreEtAzioneMenu);
				bIndici.addActionListener(ascoltatoreEtAzioneGestioneIndici);
				bCambiaUtente.addActionListener(ascoltatoreEtAzioneCambiaUtente);
				bSospendi.addActionListener(ascoltatoreEtAzioneSospendi);
				
				nuovoItem.addActionListener(ascoltatoreEtAzioneNuovaGestione);
				salvaItem.addActionListener(ascoltatoreEtAzioneSerializza);
				caricaItem.addActionListener(ascoltatoreEtAzioneDeserializza);
				//salvaLogItem.addActionListener(ascoltatoreEtAzioneSalvaLog);
				//creaNuovoLogItem.addActionListener(ascoltatoreEtAzioneCreaNuovoLog);
				chiudiItem.addActionListener(ascoltatoreEtAzioneEsci);
				
				if (subitoGestioneProdotti) gestione(locazioneYProssimoPannello);
			} //Fine Costruttore
			
			private void sospensione()
			{
				int lunghezzaScritta=420;
				int altezzaScritta = 30;
				int altezzaPannelloGestoreSospensione;
				int larghezzaPannelloGestoreSospensione;
				
				
				altezzaConfine = ConfineAvvio.Confine.getHeight();
				ConfineAvvio.Confine.add(pannelloGestoreSospensione);
				//ConfineAvvio.Confine.setLayout(null);
				pannelloGestoreSospensione.setLayout(null);
				pannelloGestoreSospensione.setVisible(true);
				
				altezzaPannelloGestoreSospensione = altezzaConfine;
				larghezzaPannelloGestoreSospensione = ConfineAvvio.Confine.getWidth();
				
				pannelloGestoreSospensione.setBounds(0,0,larghezzaPannelloGestoreSospensione, altezzaPannelloGestoreSospensione);
				pannelloGestoreSospensione.setBackground(new Color(190,190,190));
				
				int larghezzaBottoni = larghezzaPannelloGestoreSospensione/4;			
				//int bordoBottoni = 30;
				int altezzaBottoni = 40;
				
				JLabel labelSospensioneGestore = new JLabel("Modalità  GESTORE in Sospensione...");
				labelSospensioneGestore.setBounds((900-lunghezzaScritta)/2,30, lunghezzaScritta, altezzaScritta);
				labelSospensioneGestore.setFont(new Font("Verdana", Font.BOLD, 20));
				
				int locazioneXBottRiprendiGestore = larghezzaPannelloGestoreSospensione/5;
				int locazioneYBottoni = altezzaPannelloGestoreSospensione/6;
				//JButton bRiprendiGestore = new JButton("Riprendi");
				//bRiprendiGestore.setBounds(locazioneXBottRiprendiGestore ,locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
				//bRiprendiGestore.setBackground(Color.green);
				JButton bRiprendiGestore = new JButton(new ImageIcon("Save/immagini/riprendi.gif"));
		        bRiprendiGestore.setRolloverIcon(new ImageIcon("Save/immagini/riprendiR.gif"));
		        bRiprendiGestore.setBorderPainted(false);
		        bRiprendiGestore.setMargin (new Insets (0, 0, 0, 0));
		        bRiprendiGestore.setContentAreaFilled(false);
		        bRiprendiGestore.setOpaque(true);
		        bRiprendiGestore.setToolTipText("Riprendi");
		        bRiprendiGestore.setBounds(200 ,locazioneYBottoni, 200, 40);
				
				int locazioneXBottEsci = locazioneXBottRiprendiGestore + larghezzaBottoni + 60;
				/*JButton bEsci = new JButton("Esci");
				bEsci.setBounds(locazioneXBottEsci, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
				bEsci.setBackground(Color.red);*/
				
				JButton bEsci = new JButton(new ImageIcon("Save/immagini/escib.gif"));
		        bEsci.setRolloverIcon(new ImageIcon("Save/immagini/escibR.gif"));
		        bEsci.setBorderPainted(false);
		        bEsci.setMargin (new Insets (0, 0, 0, 0));
		        bEsci.setContentAreaFilled(false);
		        bEsci.setOpaque(true);
		        bEsci.setToolTipText("Chiudi Programma");
		        bEsci.setBounds(500, locazioneYBottoni, 200, 40);
				
				pannelloGestoreSospensione.add(labelSospensioneGestore);
				pannelloGestoreSospensione.add(bRiprendiGestore);;
				pannelloGestoreSospensione.add(bEsci);
				
				//Ascoltatori
			    ascoltatoreEtAzioneRiprendiGestore = new RiprendiGestoreAA();
			    ascoltatoreEtAzioneEsci = new EsciAA();
				
				
			    //Associazione
			    bRiprendiGestore.addActionListener(ascoltatoreEtAzioneRiprendiGestore);
			    bEsci.addActionListener(ascoltatoreEtAzioneEsci);
				
			}// Fine sospensione()
			
			private void gestione(int locazioneYPannelloGestore2Gestione)
			{
						
				//pannelloGestore2.setVisible(false);
				altezzaConfine = ConfineAvvio.Confine.getHeight();
				ConfineAvvio.Confine.add(pannelloGestore2Gestione);
				ConfineAvvio.Confine.setLayout(null);
				pannelloGestore2Gestione.setLayout(null);
				pannelloGestore2Gestione.setVisible(true);
				
				locazioneYPannelloGestore2Gestione += 5;
				altezzaPannelloGestore2Gestione = altezzaConfine/6;
				int larghezzaPannello= ConfineAvvio.Confine.getWidth();
				int lunghezzaScritta=200;
				int altezzaScritta = 40;
				
				locazioneProssimoPannelloGestore3 = locazioneYPannelloGestore2Gestione + altezzaPannelloGestore2Gestione;
				
				pannelloGestore2Gestione.setBounds(0,locazioneYPannelloGestore2Gestione,larghezzaPannello, altezzaPannelloGestore2Gestione);
				pannelloGestore2Gestione.setBackground(new Color(190,190,190));
				
				//altezzaPannelloFornitore2Forniture = pannelloFornitore2Forniture.getHeight(); //Questo deve stare qui (cioÃ¨ dopo aver impostato il size di pannelloAdmin3)
				larghezzaPannelloGestore2Gestione = larghezzaPannello;			
				
				//Titolo
				labelGestioneProdotti = new JLabel("Gestione Prodotti");
				labelGestioneProdotti.setBounds((pannelloGestore2Gestione.getWidth()/3+65),10, lunghezzaScritta, altezzaScritta);
				labelGestioneProdotti.setFont(new Font("Verdana", Font.BOLD, 20));

				//Scritte e Tendine
				
				int larghezzaBottoni = 200;
				int altezzaBottoni = 40;
				int distanzaTraBottoni = 65;
				int locazioneYBottoni = 50;

				//Bottone Aggiunta Nuovi Prodotti
				int locazioneXBottAggiuntaProdotti = 220;
				bAggiuntaProdotti = new JButton(new ImageIcon("Save/immagini/bottone_aggiungi_prodotto.gif"));
				bAggiuntaProdotti.setRolloverIcon(new ImageIcon("Save/immagini/bottone_aggiungi_prodottoR.gif"));
				bAggiuntaProdotti.setBorderPainted(false);
				bAggiuntaProdotti.setMargin (new Insets (0, 0, 0, 0));
				bAggiuntaProdotti.setContentAreaFilled(false);
				bAggiuntaProdotti.setOpaque(true);
				bAggiuntaProdotti.setToolTipText("Gestione Aggiunta Nuovi Prodotti");
				bAggiuntaProdotti.setBounds(locazioneXBottAggiuntaProdotti, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
				
				//Bottone Rimozione Prodotti
				int locazioneXBottRimozioneProdotti = locazioneXBottAggiuntaProdotti + larghezzaBottoni + distanzaTraBottoni;
				bRimozioneProdotti = new JButton(new ImageIcon("Save/immagini/bottone_rimuovi_prodotto.gif"));
				bRimozioneProdotti.setRolloverIcon(new ImageIcon("Save/immagini/bottone_rimuovi_prodottoR.gif"));
				bRimozioneProdotti.setBorderPainted(false);
				bRimozioneProdotti.setMargin (new Insets (0, 0, 0, 0));
				bRimozioneProdotti.setContentAreaFilled(false);
				bRimozioneProdotti.setOpaque(true);
				bRimozioneProdotti.setToolTipText("Informazioni e Rimozione Prodotti");
				bRimozioneProdotti.setBounds(locazioneXBottRimozioneProdotti, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
				
				//"X" chiusura Pannello	
				bChiudiPannelloGestore2Gestione = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
				bChiudiPannelloGestore2Gestione.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
				bChiudiPannelloGestore2Gestione.setBounds(827, 5, 48, 20);
				bChiudiPannelloGestore2Gestione.setBorderPainted(false);
				bChiudiPannelloGestore2Gestione.setMargin (new Insets (0, 0, 0, 0));
				bChiudiPannelloGestore2Gestione.setContentAreaFilled(false);
				bChiudiPannelloGestore2Gestione.setOpaque(true);
				bChiudiPannelloGestore2Gestione.setToolTipText("Chiusura Pannello");
							
				pannelloGestore2Gestione.add(labelGestioneProdotti);
				
				pannelloGestore2Gestione.add(bAggiuntaProdotti);
				pannelloGestore2Gestione.add(bRimozioneProdotti);
				pannelloGestore2Gestione.add(bChiudiPannelloGestore2Gestione);
				
				
				//ASCOLTATORI
				ascoltatoreChiudiPannelloGestore2Gestione	= new ChiudiPannelloGestore2GestioneAA();
				ascoltatoreAggiuntaProdotti 				= new AggiuntaProdottiAA();				
				ascoltatoreRimozioneProdotti 				= new RimozioneProdottiAA();
							
				//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
				
				
				//ASSOCIAZIONI
				bChiudiPannelloGestore2Gestione.addActionListener(ascoltatoreChiudiPannelloGestore2Gestione);
				bAggiuntaProdotti.addActionListener(ascoltatoreAggiuntaProdotti);
				bRimozioneProdotti.addActionListener(ascoltatoreRimozioneProdotti);
			}
			
			protected class SospendiAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						pannelloGestore1.setVisible(false);
						pannelloGestore2.setVisible(false);
						barraMenu.setVisible(false);
						ConfineAvvio.Confine.remove(barraMenu);
						try
						{
							controlloreCorrente.salvaArticoli();
						} 
						catch (SerializzazioneException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
						sospensione();
					}
					catch (/*SerializzazioneException */ Exception e)
					{
						JOptionPane.showMessageDialog(null, " SospendiAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
			}	
			
			private class MenuAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					//Importante per una eventuale freccetta della tendina
					pannelloGestore1.setVisible(false);
					pannelloGestore1.setVisible(true);
			        pannelloGestore2.setVisible(false);
			        //Disabilito Pulsanti
					bGestione.setEnabled(false);
					bIndici.setEnabled(false);
					bCambiaUtente.setEnabled(false);
					bSospendi.setEnabled(false);
					bMenu.setEnabled(false);
					
					new ConfineGestoreVisualizzaMenu(controlloreCorrente, locazioneYProssimoPannello);	
				}
			}

			private class EsciAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try  //Salva gli articoli all'uscita
					{
						controlloreCorrente.salvaArticoli();
					}
					catch (SerializzazioneException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					controlloreCorrente.salvaLog();
					System.exit(0);
				}
			}
			
			private class CambiaUtenteAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					pannelloGestore1.setVisible(true);
					pannelloGestore1.setVisible(false);
					pannelloGestore2.setVisible(true);
					pannelloGestore2.setVisible(false);
					barraMenu.setVisible(true);
					barraMenu.setVisible(false);
					try
					{
						controlloreCorrente.salvaArticoli();
					} 
					catch (SerializzazioneException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
					ControlloreLogin.gestioneNuovoLogin();	
				}
			}
			
			private class GestioneProdottiAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					//try
					//{
						pannelloGestore2Gestione.setVisible(true);
						pannelloGestore2Gestione.setVisible(false);
						pannelloGestore2.setVisible(false);
						bGestione.setEnabled(false);
						bCambiaUtente.setEnabled(false);
						bIndici.setEnabled(false);
						bSospendi.setEnabled(false);
						bMenu.setEnabled(false);
						//file.setEnabled(false);
						
	/*SE QUALCOSA NON VA RIATTIVARLO!! */ //pannelloAdmin3OpzioniAdmin.setVisible(false); 
						
						//pannelloGestore1.setVisible(false);	//disattivo e riattivo
						//pannelloGestore1.setVisible(true);
						
						//pannelloGestore2.setVisible(false); //IMPORTANTISSIMO NON RIATTIVARE INVECE QUESTO, ALTRIMENTI DA PROBLEMI LA TENDINA
						
						//
						gestione(locazioneYProssimoPannello);
					/*}
					catch (SerializzazioneException  Exception e)
					{
						JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneOpzioniAdminAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}*/
				
					
				}
			}

			private class GestioneIndiciAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{			
					
					//Importante per la freccetta della tendina
					pannelloGestore1.setVisible(false);
					pannelloGestore1.setVisible(true);
					
					pannelloGestore2.setVisible(false);
					pannelloGestore2Gestione.setVisible(false);

					bGestione.setEnabled(false);
					bCambiaUtente.setEnabled(false);
					bIndici.setEnabled(false);
					bSospendi.setEnabled(false);
					bMenu.setEnabled(false);
					//file.setEnabled(false);
					
					try {new ConfineGestoreIndici(controlloreCorrente, locazioneYProssimoPannello);
					} catch (IDEsternoException e) {e.printStackTrace();}

				}
			}
			private class RiprendiGestoreAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					boolean subitoGestioneProdotti = false;
					pannelloGestoreSospensione.setVisible(false);
					pannelloGestore1.setVisible(false);
					new ConfineGestore(controlloreCorrente, subitoGestioneProdotti);	
				}
			}
			private class ChiudiPannelloGestore2GestioneAA implements ActionListener
			{
					public void actionPerformed(ActionEvent arg0)
					{
						boolean subitoGestioneProdotti = false;
						pannelloGestore2Gestione.setVisible(false);
						pannelloGestore2.setVisible(true);
						pannelloGestore2.setVisible(false);
						file.setEnabled(true);
						bGestione.setEnabled(true);
						bSospendi.setEnabled(true);
						bIndici.setEnabled(true);
						bCambiaUtente.setEnabled(true);
						bMenu.setEnabled(true);
						barraMenu.setVisible(false);
						//pannelloGestore1.setVisible(false);
						
						pannelloGestore1.setVisible(false);
						new ConfineGestore(controlloreCorrente, subitoGestioneProdotti);
						
					}
			}
			
			
			
			private class AggiuntaProdottiAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					//try
					//{
						//Importante per freccetta tendina
						pannelloGestore2Gestione.setVisible(false);
						pannelloGestore2Gestione.setVisible(true);
						
						bAggiuntaProdotti.setEnabled(false);
						bRimozioneProdotti.setEnabled(false);
						bChiudiPannelloGestore2Gestione.setEnabled(false);
						new ConfineGestoreAggiuntaProdotti(controlloreCorrente, locazioneProssimoPannelloGestore3);
					//}
					//catch (/*SerializzazioneException */ Exception e)
					//{
					//	JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneUtentiNuovoUtenteAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					//}
				}
			}
			
			private class RimozioneProdottiAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					//try
					//{
						//Importante per freccetta tendina
						pannelloGestore2Gestione.setVisible(false);
						pannelloGestore2Gestione.setVisible(true);
						
						bAggiuntaProdotti.setEnabled(false);
						bRimozioneProdotti.setEnabled(false);
						bChiudiPannelloGestore2Gestione.setEnabled(false);
						new ConfineGestoreRimozioneProdotti(controlloreCorrente, locazioneProssimoPannelloGestore3);
					//}
					//catch (/*SerializzazioneException */ Exception e)
					//{
					//	JOptionPane.showMessageDialog(null, " ConfineAmministratoreAmministrazioneUtentiNuovoUtenteAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					//}
				}
			}
			
			private class SerializzaAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					try
					{
						controlloreCorrente.salvaArticoli();
					}
					catch (SerializzazioneException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}
				}
			} 
			private class DeserializzaAA implements ActionListener
			{
					public void actionPerformed(ActionEvent arg0)
					{
						try 
						{
							controlloreCorrente.caricaArticoli();
						}
						catch (DeserializzazioneException e)
						{
							JOptionPane.showMessageDialog(null, " Confine. Problemi in Deserializza. " + e.toString());
						}
					}
			}
			
			/*private class SalvaLogAA implements ActionListener
			{
					public void actionPerformed(ActionEvent arg0)
					{
						try 
						{
							controlloreCorrente.salvaLog();
						}
						catch (Exception e)
						{
							JOptionPane.showMessageDialog(null, "Problemi con SalvaLog... " + e.toString());
						}
					}
			}
			
			private class CreaNuovoLogAA implements ActionListener
			{
					public void actionPerformed(ActionEvent arg0)
					{
						try 
						{
							controlloreCorrente.creaNuovoLog();
						}
						catch (Exception e)
						{
							JOptionPane.showMessageDialog(null, "Problemi con CreaNuovoLog... " + e.toString());
						}
					}
			}*/
			
			private class NuovaGestioneAA implements ActionListener
			{
					public void actionPerformed(ActionEvent arg0)
					{
						try 
						{
							controlloreCorrente.nuovaGestione();
						}
						catch (Exception e)
						{
							JOptionPane.showMessageDialog(null, "Problemi con SalvaLog... " + e.toString());
						}
					}
			}
			
}

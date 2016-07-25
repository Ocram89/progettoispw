package boundary;

	import java.awt.Color;
import java.awt.Font;
	import java.awt.Insets;
//import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
	
import javax.swing.ImageIcon;
//import java.io.File;
	//import java.io.IOException;
	import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


//my -----------
import util.*;
import exception.*;
import control.*;

public class ConfineAministratoreAmministrazione extends JFrame
{

		//Versione temporanea per modificare al minimo il main di test
		private static final long	serialVersionUID	= 1L;
		
		//Barra dei Menù
		JMenuBar barraMenu = new JMenuBar();
		public static JMenu file;
		JMenuItem nuovoItem;
		JMenuItem caricaItem;
		JMenuItem salvaItem;
		JMenuItem chiudiItem;
		JMenuItem salvaLogItem;
		JMenuItem creaNuovoLogItem;
		JMenuItem resetUtenti;
		
		//Dichiaro Pannelli
		public  JPanel pannelloAdmin1= new JPanel(); 
		public  JPanel pannelloAdmin2= new JPanel(); 
		public  JPanel pannelloAdmin3= new JPanel(); 
		public 	JPanel pannelloTitolo= new JPanel();
		public  JPanel pannelloBottoniAdmin= new JPanel();	
		
		public  JPanel pannelloAdminSospensione = new JPanel();
		
		public JPanel pannelloSfondo= new JPanel();	
		
		
		public JPanel pannelloIndici = new JPanel();
		
		//Titolo
		public JLabel titoloConnesso= new JLabel();
		
		//Pannelli
		public JPanel pannelloBotton= new JPanel();
		
		//Bottoni/
		public static JButton 		bGestioneUtenti; 
		public static JButton 		bGestioneProdotti; 
		public static JButton 		bGestioneIndici;
		public static JButton 		bCambiaUtente; 
		public static JButton 		bSospendi; 
		public static JButton 		bLog;
		public static JButton 		bMenu; 
		
		public JButton 		bEsci; 
		
				
		int altezzaPannelloTitoloAdmin = 40;
		//int altezzaPannelloBottoniAdmin = 80;
		
		// Ascoltatori BARRA DEI MENU
		private NuovaGestioneAA     ascoltatoreEtAzioneNuovaGestione;
		private SerializzaAA		ascoltatoreEtAzioneSerializza;
		private DeserializzaAA		ascoltatoreEtAzioneDeserializza;
		private SalvaLogAA			ascoltatoreEtAzioneSalvaLog;
		private CreaNuovoLogAA		ascoltatoreEtAzioneCreaNuovoLog;
		private ResetUtentiAA		ascoltatoreEtAzioneResetUtenti;
		
		//ASCOLTATORI Primo pannello
		private SospendiAA 			ascoltatoreEtAzioneSospendi;
		private CambiaUtenteAA 		ascoltatoreEtAzioneCambiaUtente;
		
		//ASCOLTATORI Secondo pannello
		private GestioneIndiciAA 	ascoltatoreEtAzionebGestioneIndici;
		private GestioneProdottiAA 	ascoltatoreEtAzioneGestioneProdotti;
		private LogAA 				ascoltatoreEtAzioneLog;
		
		private MenuAA				ascoltatoreEtAzioneMenu;
		
		private GestioneUtentiAA 	ascoltatoreEtAzioneGestioneUtenti;
		private EsciAA 				ascoltatoreEtAzioneEsci;
		
		
		private RiprendiAdminAA		ascoltatoreEtAzioneRiprendiAdmin; 

		
/*		public Controllore getcontrollore() {
			return controlloreCorrente;
		}
		*/
		
		int altezzaConfine;
		int altezzaBarraMenu;
		int altezzaPannelloAdmin1;
		int altezzaPannelloAdmin2;
		public static int locazioneYProssimoPannello;
		
		//private StackFrame questoConfine;
		//StackFrame confinePrecedente;
		private Controllore controlloreCorrente;

		
		int altezzaBottoni = 40;
		int altezzaBottoniTitolo;
		int larghezzaBottoniTitolo;
		int bordoPannelli = 5;
		int bordoBottoni = 30;
		int larghezzaBottoniGrandi; //Da inizializzare dopo!!
		int larghezzaBottoniMedi;
		int larghezzaBottoniPiccoli;
		int larghezzaPannelli;

		public ConfineAministratoreAmministrazione(Controllore controllore)
		{
			this.setTitle("Amministrazione" + CostantiDiInstallazione.NOME_CLIENTE);
			
			this.controlloreCorrente = controllore; 	//ControlloreAmministrazione
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			altezzaPannelloAdmin1 = altezzaConfine/5 - 10;
			altezzaBarraMenu = 25;
			larghezzaPannelli = ConfineAvvio.Confine.getWidth();
			locazioneYProssimoPannello = altezzaPannelloAdmin1 + altezzaBarraMenu;
			// Costruisco pannello Amministratore
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin1.setBounds(0,altezzaBarraMenu,ConfineAvvio.Confine.getWidth(), altezzaPannelloAdmin1);
			pannelloAdmin1.setBackground(new Color(200, 200, 200));
			pannelloAdmin1.setLayout(null);
			ConfineAvvio.Confine.add(pannelloAdmin1);
			
			altezzaPannelloAdmin2 = altezzaConfine - altezzaPannelloAdmin1 -43;
			pannelloAdmin2.setLayout(null);
			pannelloAdmin2.setBounds(0,altezzaPannelloAdmin1+5+altezzaBarraMenu,ConfineAvvio.Confine.getWidth(), altezzaPannelloAdmin2);
			pannelloAdmin2.setBackground(new Color(190, 190, 190));
			ConfineAvvio.Confine.add(pannelloAdmin2);
			
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
			
			salvaLogItem = new JMenuItem("Salva Log Operazioni");
			file.add(salvaLogItem);
			
			creaNuovoLogItem = new JMenuItem("Crea Nuovo Log Operazioni");
			file.add(creaNuovoLogItem);
			
			resetUtenti = new JMenuItem("Reset Utenti");
			file.add(resetUtenti);
			
			chiudiItem = new JMenuItem("Esci dal Programma");
			file.add(chiudiItem);
						
			//COSTRUISCO PANNELLO TITOLO
			pannelloTitolo.setLayout(null);
	        /*pannelloTitolo.setSize(ConfineAvvio.Confine.getWidth(), altezzaPannelloTitoloAdmin);
	        pannelloTitolo.setLocation(0, 5);  */
	        pannelloTitolo.setBackground(new Color(190, 190, 190));
	        pannelloTitolo.setBounds(0, 5, ConfineAvvio.Confine.getWidth(), altezzaPannelloTitoloAdmin); 
	        pannelloTitolo.add(titoloConnesso);
	        
	        
	        //COMPONENTI DEL PANNELLO TITOLO:
	        //1.Titolo
	        titoloConnesso.setFont(new Font("Verdana", Font.BOLD, 20));
	        titoloConnesso.setLocation(0, 0);
	        titoloConnesso.setSize(pannelloTitolo.getWidth(), altezzaPannelloTitoloAdmin);
	        titoloConnesso.setHorizontalAlignment(JLabel.CENTER);
	        titoloConnesso.setVerticalAlignment(JLabel.CENTER);
	        titoloConnesso.setText("Sei Connesso come AMMINISTRATORE");
			
	        //2.Bottone Indietro
	        larghezzaBottoniTitolo = 150;
	        altezzaBottoniTitolo = 30;
			/*bRitorna 			= new JButton("INDIETRO"); 
			bRitorna.setLocation(5,(altezzaPannelloTitolo/2)-(altezzaBottoniTitolo/2));
	        bRitorna.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);*/
	        
	        /*
			bCambiaUtente 		= new JButton("CAMBIA UTENTE"); 
			bCambiaUtente.setLocation(ConfineAvvio.Confine.getWidth() - larghezzaBottoniTitolo - bordoBottoni ,(altezzaPannelloTitoloAdmin/2)-(altezzaBottoniTitolo/2));
	        bCambiaUtente.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);
	        bCambiaUtente.setBackground(Color.orange);
	        */
	        //Bottone Cambia Utente
	        bCambiaUtente = new JButton(new ImageIcon("Save/immagini/cambia_utente.gif"));
	        bCambiaUtente.setRolloverIcon(new ImageIcon("Save/immagini/cambia_utentiR.gif"));
	        bCambiaUtente.setBorderPainted(false);
	        bCambiaUtente.setMargin (new Insets (0, 0, 0, 0));
	        bCambiaUtente.setContentAreaFilled(false);
	        bCambiaUtente.setOpaque(true);
	        bCambiaUtente.setToolTipText("Cambia Utente");
	        bCambiaUtente.setBounds(750, 0, 42, 42);
	        
	        /*
	        bSospendi 		= new JButton("SOSPENDI"); 
			bSospendi.setLocation(12 ,(altezzaPannelloTitoloAdmin/2)-(altezzaBottoniTitolo/2));
	        bSospendi.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);
	        bSospendi.setBackground(Color.orange);
	        */
	        
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
	        pannelloBottoniAdmin.setLayout(null);
	        /*pannelloBottoniAdmin.setSize(ConfineAvvio.Confine.getWidth(), altezzaPannelloBottoniAdmin);
	        pannelloBottoniAdmin.setLocation(0, bordoPannelli*2 +altezzaPannelloTitoloAdmin);  */
	        pannelloBottoniAdmin.setBounds(0, bordoPannelli*2 +altezzaPannelloTitoloAdmin,ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/10);
	        
	        pannelloBottoniAdmin.setBackground(new Color(190, 190, 190));
	        
	        //System.out.println(altezzaPannelloBottoniAdmin);
	        
	         //Attenzione: larghezzaBottoni deve essere inizializzato QUI! per via di pannelloBottoni.
	        
	        larghezzaBottoniGrandi = pannelloBottoniAdmin.getWidth()/5;  
	        larghezzaBottoniMedi = pannelloBottoniAdmin.getWidth()/6;
	        larghezzaBottoniPiccoli = larghezzaBottoniGrandi/2;

	        //COMPONENTI PANNELLO BOTTONI
	        
	        
	        int distanzaTraBottoni = 25;
	        int altezzaPannelloBottoniAdmin = pannelloBottoniAdmin.getHeight();
	        int locazioneYBottoni = (altezzaPannelloBottoniAdmin/2)-(altezzaBottoni/2) +5;
	        
	        /*int distanzaTraBottoni = 20;
	        int altezzaPannelloBottoniAdmin = pannelloBottoniAdmin.getHeight();
	        int locazioneYBottoni = (altezzaPannelloBottoniAdmin/2)-(altezzaBottoni/2);
	        //1.Bottone INDICI (Bottone Medio)
	        int locazioneXBottIndici = larghezzaPannelli/60;        
	        bGestioneIndici = new JButton("Gestione INDICI");
	        bGestioneIndici.setBounds(locazioneXBottIndici,locazioneYBottoni,larghezzaBottoniMedi, altezzaBottoni);
	        bGestioneIndici.setBackground(Color.green);
	        //bGestioneIndici.setSize(larghezzaBottoniMedi, altezzaBottoni);
	        //bGestioneIndici.setFont(new Font("Arial", 0, 20));
			
	        //2. Bottone PRODOTTI (Bottone Grande)
	        int locazioneXBottGestProd = locazioneXBottIndici + larghezzaBottoniMedi + distanzaTraBottoni;
	        bGestioneProdotti	= new JButton("Gestione PRODOTTI"); 
	        bGestioneProdotti.setLocation(locazioneXBottGestProd,locazioneYBottoni);
	        bGestioneProdotti.setSize(larghezzaBottoniGrandi, altezzaBottoni);
	        bGestioneProdotti.setBackground(Color.magenta);
	        //bGestioneProdotti.setFont(new Font("Arial", 0, 18));
	        
	        //4. Bottone LOG
	        int locazioneXBottLog = locazioneXBottGestProd +larghezzaBottoniGrandi + distanzaTraBottoni;
			bLog 				= new JButton("LOG"); 
			bLog.setLocation(locazioneXBottLog,locazioneYBottoni);
		    bLog.setSize(larghezzaBottoniPiccoli, altezzaBottoni);
		    bLog.setBackground(Color.blue);
	              
	        //5. Bottone MENU
			int locazioneXBottMenu = locazioneXBottLog +larghezzaBottoniPiccoli + distanzaTraBottoni;
	        bMenu 				= new JButton("MENU'"); 
			bMenu.setLocation(locazioneXBottMenu,locazioneYBottoni);
		    bMenu.setSize(larghezzaBottoniPiccoli, altezzaBottoni);
		    bMenu.setBackground(Color.green);
		    
		    //3. Bottone GESTIONE UTENTI (Bottone Medi)
	        int locazioneXBottGestUtenti = locazioneXBottMenu + larghezzaBottoniPiccoli + distanzaTraBottoni;
	        bGestioneUtenti	 	= new JButton("Gestione UTENTI"); 
	        bGestioneUtenti.setLocation(locazioneXBottGestUtenti,locazioneYBottoni);
	        bGestioneUtenti.setSize(larghezzaBottoniMedi, altezzaBottoni);
	        //bGestioneProdotti.setFont(new Font("Arial", 0, 18));
	         * 
		    */
	        
	        int larghezzaBottoni=150;
	        int altezzaBottoni=30;
	        
	        //1.Bottone Menu
		    int locazioneXBottMenu = distanzaTraBottoni;
	        bMenu= new JButton(new ImageIcon("Save/immagini/menu30p.gif"));
	        bMenu.setRolloverIcon(new ImageIcon("Save/immagini/menu30pR.gif"));
	        bMenu.setBorderPainted(false);
	        bMenu.setMargin (new Insets (0, 0, 0, 0));
	        bMenu.setContentAreaFilled(false);
	        bMenu.setOpaque(true);
	        bMenu.setToolTipText("Visualizza Menù");
	        bMenu.setBounds(locazioneXBottMenu,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        
	        //2.Gestione Prodotti
		    int locazioneXBottGestProd = locazioneXBottMenu + larghezzaBottoni +  distanzaTraBottoni;
	        bGestioneProdotti= new JButton(new ImageIcon("Save/immagini/gestione_prodotti30p.gif"));
	        bGestioneProdotti.setRolloverIcon(new ImageIcon("Save/immagini/gestione_prodotti30pR.gif"));
	        bGestioneProdotti.setBorderPainted(false);
	        bGestioneProdotti.setMargin (new Insets (0, 0, 0, 0));
	        bGestioneProdotti.setContentAreaFilled(false);
	        bGestioneProdotti.setOpaque(true);
	        bGestioneProdotti.setToolTipText("Aggiunta/Rimozione prodotti");
	        bGestioneProdotti.setBounds(locazioneXBottGestProd,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);

	        //3.Gestione Indici
		    int locazioneXBottGestInd = locazioneXBottGestProd + larghezzaBottoni +  distanzaTraBottoni;
	        bGestioneIndici= new JButton(new ImageIcon("Save/immagini/gestione_indici30p.gif"));
	        bGestioneIndici.setRolloverIcon(new ImageIcon("Save/immagini/gestione_indici30pR.gif"));
	        bGestioneIndici.setBorderPainted(false);
	        bGestioneIndici.setMargin (new Insets (0, 0, 0, 0));
	        bGestioneIndici.setContentAreaFilled(false);
	        bGestioneIndici.setOpaque(true);
	        bGestioneIndici.setToolTipText("Visualizza Indici");
	        bGestioneIndici.setBounds(locazioneXBottGestInd,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        
	        //4.Gestione Utenti
		    int locazioneXBottGestUt = locazioneXBottGestInd + larghezzaBottoni +  distanzaTraBottoni;
	        bGestioneUtenti= new JButton(new ImageIcon("Save/immagini/gestione_utenti30p.gif"));
	        bGestioneUtenti.setRolloverIcon(new ImageIcon("Save/immagini/gestione_utenti30pR.gif"));
	        bGestioneUtenti.setBorderPainted(false);
	        bGestioneUtenti.setMargin (new Insets (0, 0, 0, 0));
	        bGestioneUtenti.setContentAreaFilled(false);
	        bGestioneUtenti.setOpaque(true);
	        bGestioneUtenti.setToolTipText("Gestione Utenti");
	        bGestioneUtenti.setBounds(locazioneXBottGestUt,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        
	        //4.Log
		    int locazioneXBottLog = locazioneXBottGestUt + larghezzaBottoni + distanzaTraBottoni;
	        bLog= new JButton(new ImageIcon("Save/immagini/log.gif"));
	        bLog.setRolloverIcon(new ImageIcon("Save/immagini/logR.gif"));
	        bLog.setBorderPainted(false);
	        bLog.setMargin (new Insets (0, 0, 0, 0));
	        bLog.setContentAreaFilled(false);
	        bLog.setOpaque(true);
	        bLog.setToolTipText("Visualizza Log");
	        bLog.setBounds(locazioneXBottLog,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        
		    //6. Bottone ESCI
	        /*
	        int locazioneXBottEsci = locazioneXBottGestUtenti +larghezzaBottoniMedi + distanzaTraBottoni;
			bEsci 				= new JButton("ESCI"); 
			bEsci.setLocation(locazioneXBottEsci,locazioneYBottoni);
		    bEsci.setSize(larghezzaBottoniPiccoli, altezzaBottoni);
		    bEsci.setBackground(Color.red);
		    */
		    
	        //AGGIUNTA BOTTONI AI VARI PANNELLI
			pannelloTitolo.add(bCambiaUtente);
			pannelloTitolo.add(bSospendi);
			pannelloTitolo.add(bEsci);
			
			pannelloBottoniAdmin.add(bGestioneIndici);
			pannelloBottoniAdmin.add(bGestioneProdotti);
			pannelloBottoniAdmin.add(bGestioneUtenti);
			pannelloBottoniAdmin.add(bLog);
			pannelloBottoniAdmin.add(bMenu);
			//pannelloBottoniAdmin.add(bEsci);
			
			
	        // Aggiunta dei Vari Pannelli al PANNELLO PRINCIPALE
	        pannelloAdmin1.add(pannelloTitolo);
	        pannelloAdmin1.add(pannelloBottoniAdmin);
	          
			// Definisci ascoltatori di bottoni e relative azioni
			ascoltatoreEtAzionebGestioneIndici	= new GestioneIndiciAA();
			ascoltatoreEtAzioneGestioneProdotti = new GestioneProdottiAA();
			ascoltatoreEtAzioneGestioneUtenti	= new GestioneUtentiAA();
			ascoltatoreEtAzioneCambiaUtente		= new CambiaUtenteAA();
			ascoltatoreEtAzioneSospendi			= new SospendiAA();
			ascoltatoreEtAzioneLog				= new LogAA();
			ascoltatoreEtAzioneMenu				= new MenuAA();
			ascoltatoreEtAzioneEsci				= new EsciAA();	
			
			//Ascoltatoti BARRA
			ascoltatoreEtAzioneNuovaGestione    = new NuovaGestioneAA();
			ascoltatoreEtAzioneSerializza		= new SerializzaAA();
			ascoltatoreEtAzioneDeserializza     = new DeserializzaAA();
			ascoltatoreEtAzioneSalvaLog			= new SalvaLogAA();
			ascoltatoreEtAzioneCreaNuovoLog		= new CreaNuovoLogAA();
			ascoltatoreEtAzioneResetUtenti		= new ResetUtentiAA();
			
		    // Associa ascoltatori e bottoni
			bEsci.addActionListener(ascoltatoreEtAzioneEsci);
			
			//bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
			bGestioneIndici.addActionListener(ascoltatoreEtAzionebGestioneIndici);
			bGestioneProdotti.addActionListener(ascoltatoreEtAzioneGestioneProdotti);
			bGestioneUtenti.addActionListener(ascoltatoreEtAzioneGestioneUtenti);
			bLog.addActionListener(ascoltatoreEtAzioneLog);
			bMenu.addActionListener(ascoltatoreEtAzioneMenu);
			bCambiaUtente.addActionListener(ascoltatoreEtAzioneCambiaUtente);
			bSospendi.addActionListener(ascoltatoreEtAzioneSospendi);
			
			nuovoItem.addActionListener(ascoltatoreEtAzioneNuovaGestione);
			salvaItem.addActionListener(ascoltatoreEtAzioneSerializza);
			caricaItem.addActionListener(ascoltatoreEtAzioneDeserializza);
			salvaLogItem.addActionListener(ascoltatoreEtAzioneSalvaLog);
			creaNuovoLogItem.addActionListener(ascoltatoreEtAzioneCreaNuovoLog);
			resetUtenti.addActionListener(ascoltatoreEtAzioneResetUtenti);
			chiudiItem.addActionListener(ascoltatoreEtAzioneEsci);
			

		}// Fine costruttore
			
		
		private void sospensione()
		{
			int lunghezzaScritta=420;
			int altezzaScritta = 30;
			int altezzaPannelloAdminSospensione;
			int larghezzaPannelloAdminSospensione;
			
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdminSospensione);
			pannelloAdminSospensione.setLayout(null);
			pannelloAdminSospensione.setVisible(true);
			
			altezzaPannelloAdminSospensione = altezzaConfine;
			larghezzaPannelloAdminSospensione = ConfineAvvio.Confine.getWidth();
			
			pannelloAdminSospensione.setBounds(0,0,larghezzaPannelloAdminSospensione, altezzaPannelloAdminSospensione);
			pannelloAdminSospensione.setBackground(new Color(190,190,190));
			
			int larghezzaBottoni = larghezzaPannelloAdminSospensione/4;			
			//int bordoBottoni = 30;
			int altezzaBottoni = 40;
			
			JLabel labelSospensioneAdmin = new JLabel("Amministratore in Sospensione...");
			labelSospensioneAdmin.setBounds((larghezzaPannelloAdminSospensione/3 -20),30, lunghezzaScritta, altezzaScritta);
			labelSospensioneAdmin.setFont(new Font("Verdana", Font.BOLD, 20));
			
			int locazioneXBottRiprendiAdmin = larghezzaPannelloAdminSospensione/5;
			int locazioneYBottoni = altezzaPannelloAdminSospensione/6;
			/*
			JButton bRiprendiAdmin = new JButton("Riprendi");
			bRiprendiAdmin.setBounds(locazioneXBottRiprendiAdmin ,locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
			bRiprendiAdmin.setBackground(Color.green);
			*/
			JButton bRiprendiAdmin = new JButton(new ImageIcon("Save/immagini/riprendi.gif"));
	        bRiprendiAdmin.setRolloverIcon(new ImageIcon("Save/immagini/riprendiR.gif"));
	        bRiprendiAdmin.setBorderPainted(false);
	        bRiprendiAdmin.setMargin (new Insets (0, 0, 0, 0));
	        bRiprendiAdmin.setContentAreaFilled(false);
	        bRiprendiAdmin.setOpaque(true);
	        bRiprendiAdmin.setToolTipText("Riprendi");
	        bRiprendiAdmin.setBounds(200 ,locazioneYBottoni, 200, 40);
			
			int locazioneXBottEsci = locazioneXBottRiprendiAdmin + larghezzaBottoni + 60;
			/*
			JButton bEsci = new JButton("Esci");
			bEsci.setBounds(locazioneXBottEsci, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
			bEsci.setBackground(Color.red);
			*/
			JButton bEsci = new JButton(new ImageIcon("Save/immagini/escib.gif"));
	        bEsci.setRolloverIcon(new ImageIcon("Save/immagini/escibR.gif"));
	        bEsci.setBorderPainted(false);
	        bEsci.setMargin (new Insets (0, 0, 0, 0));
	        bEsci.setContentAreaFilled(false);
	        bEsci.setOpaque(true);
	        bEsci.setToolTipText("Chiudi Programma");
	        bEsci.setBounds(500, locazioneYBottoni, 200, 40);
			
			pannelloAdminSospensione.add(labelSospensioneAdmin);
			pannelloAdminSospensione.add(bRiprendiAdmin);;
			pannelloAdminSospensione.add(bEsci);
			
			//Ascoltatori
		    ascoltatoreEtAzioneRiprendiAdmin = new RiprendiAdminAA();
		    ascoltatoreEtAzioneEsci = new EsciAA();
			
			
		    //Associazione
		    bRiprendiAdmin.addActionListener(ascoltatoreEtAzioneRiprendiAdmin);
		    bEsci.addActionListener(ascoltatoreEtAzioneEsci);	
		}

		private class RiprendiAdminAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdminSospensione.setVisible(false);
				pannelloAdmin1.setVisible(false);
				barraMenu.setVisible(true);
				new ConfineAministratoreAmministrazione(controlloreCorrente);
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
		
		private class LogAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per una eventuale freccetta della tendina
				pannelloAdmin1.setVisible(false);
				pannelloAdmin1.setVisible(true);
		        pannelloAdmin2.setVisible(false);
		        //Disabilito Pulsanti
				bGestioneProdotti.setEnabled(false);
				bGestioneIndici.setEnabled(false);
				bGestioneUtenti.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bLog.setEnabled(false);
				bMenu.setEnabled(false);
				
				new ConfineAmministratoreVisualizzaLog(controlloreCorrente, locazioneYProssimoPannello);								
			}
		}
		
		private class MenuAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per una eventuale freccetta della tendina
				pannelloAdmin1.setVisible(false);
				pannelloAdmin1.setVisible(true);
		        pannelloAdmin2.setVisible(false);
		        //Disabilito Pulsanti
				bGestioneProdotti.setEnabled(false);
				bGestioneIndici.setEnabled(false);
				bGestioneUtenti.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bLog.setEnabled(false);
				bMenu.setEnabled(false);
				
				new ConfineAmministratoreVisualizzaMenu(controlloreCorrente, locazioneYProssimoPannello);								
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
		
		private class SalvaLogAA implements ActionListener
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
		}
		
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
		
		private class ResetUtentiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					ControlloreLogin.resetUtenti();
				} 
				catch (PswException e) 
				{
					JOptionPane.showMessageDialog(null, "Problema Psw, dopo ResetUtenti", "Errore", JOptionPane.ERROR_MESSAGE);
				}				
			}
		} 
		
		private class GestioneIndiciAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per la freccetta della tendina
				pannelloAdmin1.setVisible(false);
				pannelloAdmin1.setVisible(true);
				
				pannelloAdmin2.setVisible(false);
		      
		        //NON SERVE!!! Anzi per usarlo dovevo dichiarare "static" il pannelloAdmin2 di Indici
		        //e una volta che si creava l'oggetto, quello non cambiava piÃ¹!! Quindi attenzione nel dichiarare static un pannello
				//ConfineAministratoreAmministrazioneIndici.pannelloAdmin2.setVisible(false);
				bGestioneProdotti.setEnabled(false);
				bGestioneIndici.setEnabled(false);
				bGestioneUtenti.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bLog.setEnabled(false);
				bMenu.setEnabled(false);
				
				try {new ConfineAministratoreAmministrazioneIndici(controlloreCorrente, locazioneYProssimoPannello);
				} catch (IDEsternoException e) {e.printStackTrace();}
			}
		}

		private class GestioneProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdmin1.setVisible(false);
				pannelloAdmin1.setVisible(true);
				pannelloAdmin2.setVisible(false);
				
				bGestioneProdotti.setEnabled(false);
				bGestioneIndici.setEnabled(false);
				bGestioneUtenti.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bLog.setEnabled(false);
				bMenu.setEnabled(false);
				new ConfineAministratoreAmministrazioneProdotti(controlloreCorrente, locazioneYProssimoPannello);
			}
		}

		private class GestioneUtentiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				boolean subitoOpzioni = false;
				boolean subitoGestioneUtenti = false;		
				pannelloAdmin1.setVisible(false);
				pannelloAdmin1.setVisible(true);
				pannelloAdmin2.setVisible(false);
				
				bGestioneProdotti.setEnabled(false);
				bGestioneIndici.setEnabled(false);
				bGestioneUtenti.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bLog.setEnabled(false);
				bMenu.setEnabled(false);
				new ConfineAministratoreAmministrazioneUtenti(controlloreCorrente, locazioneYProssimoPannello, subitoOpzioni, subitoGestioneUtenti);
			}
		}
		
		
		

		private class SospendiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdmin1.setVisible(false);
				pannelloAdmin2.setVisible(false);
				
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
		}
		
		private class CambiaUtenteAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdmin1.setVisible(true);
				pannelloAdmin1.setVisible(false);
				pannelloAdmin2.setVisible(true);
				pannelloAdmin2.setVisible(false);
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
}
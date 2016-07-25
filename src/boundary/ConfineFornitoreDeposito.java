package boundary;

	import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;


//	import java.io.File;
//	import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
//	import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
import javax.swing.JTextField;
//	import java.util.*;
import javax.swing.text.MaskFormatter;

//my -----------
import util.*;
import exception.*;
import control.*;


	public class ConfineFornitoreDeposito extends StackFrame
	{

		//Versione temporanea per modificare al minimo il main di test
		private static final long	serialVersionUID	= 1L;
		

		// Relazione con controllore
		private Controllore controlloreCorrente;
		
		//Barra dei Menù
		JMenuBar barraMenu = new JMenuBar();
		JMenu file;
		//JMenuItem nuovoItem;
		//JMenuItem caricaItem;
		JMenuItem salvaItem;
		JMenuItem chiudiItem;
		//JMenuItem salvaLogItem;
		//JMenuItem creaNuovoLogItem;

		//Pannelli
		public  JPanel 	pannelloFornitore1				= new JPanel(); 
		public  JPanel 	pannelloFornitore2				= new JPanel(); 
		
		public  JPanel 	pannelloFornitore2Forniture		= new JPanel();
		
		public 	JPanel	pannelloTitolo 					= new JPanel();
		public  JPanel	pannelloBottoniFornitore		= new JPanel();	
		public  JPanel 	pannelloFornitoreSospensione 	= new JPanel();
		
//		public JPanel  		pannelloBottoni;
//		public JPanel  		pannelloLabel;
//		public JPanel  		pannelloProdotto;
//		public JPanel  		pannelloSalva;
	
		//JLabel
		public JLabel 		titoloConnesso				= new JLabel();
		
		//Bottoni
		public static JButton		bForniture;
		
		private JButton 	bEsci; 
		public static JButton 	bMenu; 	

		public static JButton 		bCambiaUtente; 
		public static JButton 		bSospendi; 
		
		
		//public JButton 		bDeposita;
		public JButton 		bGiacenza;

		
		public JLabel 		labelCodiceBarre;
		public JFormattedTextField 	codiceDataScadenzaGiorno;
		public JFormattedTextField 	codiceDataScadenzaMese;
		public JFormattedTextField 	codiceDataScadenzaAnno;
		
		public JTextField	nomeCommerciale;
		public JTextField	dimensione;
		
		public JLabel     	labelQuantita;
		public JTextField   valoreQuantita; //NUOVA VERSIONE
		
	
		
		public JLabel 		labelForniture;
		public JLabel		labelTendinaProdotti;
		public JLabel		labelDataScadenza;
		public JLabel		labelFormatoData;
		
		public JLabel 		labelDataScad;
		public JLabel 		labelFormatoCodiceBarre;
		public JLabel 		labeldimensione;
		public JLabel 		labeldataScadenza;
		JFormattedTextField	campoDataScadGiorno;
		JFormattedTextField	campoDataScadMese;
		JFormattedTextField	campoDataScadAnno;
		
		// Tendine
		public JComboBox 	tendinaProdotti;
		
		//Bottoni/
		public JButton 		bDepositaLettoreBarre; 
		
		public JButton		bListaScadenze;
		public JScrollPane 	scrollListaScadenze;
		public JTextArea 	areaListaScadenze;
		
		public JButton    	bChiudiPannelloForniture;		
//		public JButton 		bDescrivi;
		public JButton 		bGiacenzaProdotti;
//		public JButton 		bLogGiacenza;

		// Campi e loro etichette	
		
		
	
		public JTextField 	valoreGiacenzaProdotti;
		
		
		// Ascoltatori di bottoni e relative azioni
		private CambiaUtenteAA		ascoltatoreEtAzioneCambiaUtente;
		private FornitureAA			ascoltatoreEtAzioneForniture;
		private MenuAA				ascoltatoreEtAzioneMenu;
		private SospendiAA 			ascoltatoreEtAzioneSospendi;
		private EsciAA 				ascoltatoreEtAzioneEsci;
		private RiprendiFornitoreAA	ascoltatoreEtAzioneRiprendiFornitore;
		
		private GiacenzaProdottiAA 				ascoltatoreEtAzioneGiacenzaProdotti;
		private DepositaProdottiAA				ascoltatoreEtAzioneDepositaProdotti;
		private DepositaLettoreBarreAA			ascoltatoreEtAzioneDepositaLB;
		private BottoneListaScadenzeAA			ascoltatoreBottoneListaScadenze;
		
		// Ascoltatori BARRA DEI MENU
		//private NuovaGestioneAA     ascoltatoreEtAzioneNuovaGestione;
		private SerializzaAA		ascoltatoreEtAzioneSerializza;
		//private DeserializzaAA		ascoltatoreEtAzioneDeserializza;
		//private SalvaLogAA			ascoltatoreEtAzioneSalvaLog;
		//private CreaNuovoLogAA		ascoltatoreEtAzioneCreaNuovoLog;

		private TendinaProdottiAA  	ascoltatoreEtAzioneTendinaProdotti;
		private ChiudiPannelloAA	ascoltatoreChiudiPannello;

		private String 	nomeEsternoCorrente;

		//Per Pannelli Fornitore1 e Fornitore2
		int altezzaConfine;
		int larghezzaConfine;
		int larghezzaPannelli;
		int altezzaPannelloFornitore1;
		int altezzaPannelloFornitore2;
		public static int locazioneYProssimoPannello;
		
		//Per Pannello Forniture
		int altezzaPannelloFornitore2Forniture;
		int larghezzaPannelloFornitore2Forniture;
		int locazioneYPrimaRiga;
		int locazioneYSecondaRiga;
		int	locazioneYTerzaRiga;
		int altezzaBarraMenu;

		public ConfineFornitoreDeposito(Controllore controllore)
		{
			this.controlloreCorrente= controllore;
		
			int altezzaBottoni = 40;
			int altezzaBottoniTitolo;
			int larghezzaBottoniTitolo;
			int bordoPannelli = 5;
			int bordoBottoni = 30;
			int larghezzaBottoni; //Da inizializzare dopo!!
			int altezzaPannelloTitoloFornitore = 40;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			larghezzaConfine = ConfineAvvio.Confine.getWidth();
			altezzaBarraMenu = 25;
			larghezzaPannelli = larghezzaConfine;
			altezzaPannelloFornitore1 = altezzaConfine/5 - 10;
			locazioneYProssimoPannello = altezzaPannelloFornitore1 + altezzaBarraMenu;
			// Costruisco pannello Amministratore
			ConfineAvvio.Confine.setLayout(null);
			pannelloFornitore1.setBounds(0,altezzaBarraMenu,larghezzaPannelli, altezzaPannelloFornitore1);
			pannelloFornitore1.setBackground(new Color(200, 200, 200));
			pannelloFornitore1.setLayout(null);
			ConfineAvvio.Confine.add(pannelloFornitore1);
			
			altezzaPannelloFornitore2 = altezzaConfine - altezzaPannelloFornitore1 -43;
			pannelloFornitore2.setLayout(null);
			pannelloFornitore2.setBounds(0,altezzaPannelloFornitore1+5+altezzaBarraMenu,larghezzaPannelli, altezzaPannelloFornitore2);
			pannelloFornitore2.setBackground(new Color(190, 190, 190));
			ConfineAvvio.Confine.add(pannelloFornitore2);
		
			
			//Barra dei Menu
			barraMenu.setBounds(0, 0, larghezzaPannelli, altezzaBarraMenu);
			ConfineAvvio.Confine.add(barraMenu);
			
			file = new JMenu("File");
			barraMenu.add(file);
			
			/*nuovoItem = new JMenuItem("Nuovo Magazzino");
			file.add(nuovoItem);
			
			caricaItem = new JMenuItem("Carica Magazzino");
			file.add(caricaItem);*/
			
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
	        /*pannelloTitolo.setSize(ConfineAvvio.Confine.getWidth(), altezzaPannelloTitoloAdmin);
	        pannelloTitolo.setLocation(0, 5);  */
	        pannelloTitolo.setBackground(new Color(190, 190, 190));
	        pannelloTitolo.setBounds(0, 5, larghezzaPannelli, altezzaPannelloTitoloFornitore); 
	        pannelloTitolo.add(titoloConnesso); 
	        
	        //COMPONENTI DEL PANNELLO TITOLO:
	        //1.Titolo
	        titoloConnesso.setFont(new Font("Verdana", Font.BOLD, 20));
	        titoloConnesso.setLocation(0, 0);
	        titoloConnesso.setSize(larghezzaPannelli, altezzaPannelloTitoloFornitore);
	        titoloConnesso.setHorizontalAlignment(JLabel.CENTER);
	        titoloConnesso.setVerticalAlignment(JLabel.CENTER);
	        titoloConnesso.setText("Sei Connesso come FORNITORE");
			
	        //2.Bottone Indietro
	        larghezzaBottoniTitolo = 150;
	        altezzaBottoniTitolo = 30;
			/*bRitorna 			= new JButton("INDIETRO"); 
			bRitorna.setLocation(5,(altezzaPannelloTitolo/2)-(altezzaBottoniTitolo/2));
	        bRitorna.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);*/
	        
	        int locazioneYBott = (altezzaPannelloTitoloFornitore/2)-(altezzaBottoniTitolo/2);
			//bCambiaUtente 		= new JButton("CAMBIA UTENTE"); 
			//bCambiaUtente.setLocation(larghezzaPannelli - larghezzaBottoniTitolo - bordoBottoni, locazioneYBott);
	        //bCambiaUtente.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);
	        //bCambiaUtente.setBackground(Color.orange);
	        
	        //Bottone Cambia Utente
	        bCambiaUtente = new JButton(new ImageIcon("Save/immagini/cambia_utente.gif"));
	        bCambiaUtente.setRolloverIcon(new ImageIcon("Save/immagini/cambia_utentiR.gif"));
	        bCambiaUtente.setBorderPainted(false);
	        bCambiaUtente.setMargin (new Insets (0, 0, 0, 0));
	        bCambiaUtente.setContentAreaFilled(false);
	        bCambiaUtente.setOpaque(true);
	        bCambiaUtente.setToolTipText("Cambia Utente");
	        bCambiaUtente.setBounds(750, 0, 42, 42);
	        
	        
	        //bSospendi 		= new JButton("SOSPENDI"); 
			//bSospendi.setLocation(12 ,locazioneYBott);
	        //bSospendi.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);
	        //bSospendi.setBackground(Color.orange);
	        
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
	        pannelloBottoniFornitore.setLayout(null);
	        pannelloBottoniFornitore.setBounds(0, bordoPannelli*2 +altezzaPannelloTitoloFornitore,larghezzaPannelli, altezzaConfine/10);
	        pannelloBottoniFornitore.setBackground(new Color(190, 190, 190));
	        
	        //System.out.println(altezzaPannelloBottoniAdmin);
	        
	         //Attenzione: larghezzaBottoni deve essere inizializzato QUI! per via di pannelloBottoni.
	        larghezzaBottoni = pannelloBottoniFornitore.getWidth()/5;  

	        //COMPONENTI PANNELLO BOTTONI	
	               
	        // Bottoni
	        int altezzaPannelloBottoniForniture = pannelloBottoniFornitore.getHeight();
	        int locazioneYBottoni = (altezzaPannelloBottoniForniture/2)-(altezzaBottoni/2);
	        
	        int locazioneXBottoneMenu = larghezzaPannelli/8;
	        //bMenu	= new JButton("MENU'"); 
	        //bMenu.setBounds(locazioneXBottoneMenu,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        //bMenu.setBackground(Color.green);
	        
	        //Bottone Menu
	        larghezzaBottoni=200;
	        bMenu= new JButton(new ImageIcon("Save/immagini/menu40p.gif"));
	        bMenu.setRolloverIcon(new ImageIcon("Save/immagini/menu40pR.gif"));
	        bMenu.setBorderPainted(false);
	        bMenu.setMargin (new Insets (0, 0, 0, 0));
	        bMenu.setContentAreaFilled(false);
	        bMenu.setOpaque(true);
	        bMenu.setToolTipText("Visualizza Menù");
	        bMenu.setBounds(200,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        
	        //int locazioneXBottoneForniture = locazioneXBottoneMenu + larghezzaBottoni + 60;
	        //bForniture	= new JButton("FORNITURE"); 
	        //bForniture.setBounds(locazioneXBottoneForniture,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	        //bForniture.setBackground(Color.magenta);
	        bForniture= new JButton(new ImageIcon("Save/immagini/forniture.gif"));
	        bForniture.setRolloverIcon(new ImageIcon("Save/immagini/fornitureR.gif"));
	        bForniture.setBorderPainted(false);
	        bForniture.setMargin (new Insets (0, 0, 0, 0));
	        bForniture.setContentAreaFilled(false);
	        bForniture.setOpaque(true);
	        bForniture.setToolTipText("Forniture");
	        bForniture.setBounds(500,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	       
	        //4. Bottone ESCI
	        //int locazioneXBottEsci = locazioneXBottoneForniture + larghezzaBottoni + 60;
			//bEsci = new JButton("ESCI"); 
			//bEsci.setBounds(locazioneXBottEsci, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
			//bEsci.setBackground(Color.red);
			
 //AGGIUNTA BOTTONI AI VARI PANNELLI
			
			pannelloTitolo.add(bCambiaUtente);
			pannelloTitolo.add(bSospendi);
			pannelloTitolo.add(bEsci);
			
			pannelloBottoniFornitore.add(bMenu);
			pannelloBottoniFornitore.add(bForniture);
			//pannelloBottoniFornitore.add(bEsci);
		    
			 // Aggiunta dei Vari Pannelli al PANNELLO PRINCIPALE
	        pannelloFornitore1.add(pannelloTitolo);
	        pannelloFornitore1.add(pannelloBottoniFornitore);
	        
	        // Definisci ascoltatori di bottoni e relative azioni
	        ascoltatoreEtAzioneForniture		= new FornitureAA();
	        ascoltatoreEtAzioneMenu				= new MenuAA();
			ascoltatoreEtAzioneCambiaUtente		= new CambiaUtenteAA();
			ascoltatoreEtAzioneSospendi			= new SospendiAA();
			ascoltatoreEtAzioneEsci				= new EsciAA();	
			
			//Ascoltatoti BARRA
			//ascoltatoreEtAzioneNuovaGestione    = new NuovaGestioneAA();
			ascoltatoreEtAzioneSerializza		= new SerializzaAA();
			//ascoltatoreEtAzioneDeserializza     = new DeserializzaAA();
			//ascoltatoreEtAzioneSalvaLog			= new SalvaLogAA();
			//ascoltatoreEtAzioneCreaNuovoLog		= new CreaNuovoLogAA();
			
			bEsci.addActionListener(ascoltatoreEtAzioneEsci);
			//bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
			bForniture.addActionListener(ascoltatoreEtAzioneForniture);
			bMenu.addActionListener(ascoltatoreEtAzioneMenu);
			bCambiaUtente.addActionListener(ascoltatoreEtAzioneCambiaUtente);
			bSospendi.addActionListener(ascoltatoreEtAzioneSospendi);
			
			//nuovoItem.addActionListener(ascoltatoreEtAzioneNuovaGestione);
			salvaItem.addActionListener(ascoltatoreEtAzioneSerializza);
			//caricaItem.addActionListener(ascoltatoreEtAzioneDeserializza);
			//salvaLogItem.addActionListener(ascoltatoreEtAzioneSalvaLog);
			//creaNuovoLogItem.addActionListener(ascoltatoreEtAzioneCreaNuovoLog);
			chiudiItem.addActionListener(ascoltatoreEtAzioneEsci);
			
		}
		// Fine costruttore
		
		private void sospensione()
		{
			int lunghezzaScritta=440;
			int altezzaScritta = 30;
			int altezzaPannelloFornitoreSospensione;
			int larghezzaPannelloFornitoreSospensione;
			
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloFornitoreSospensione);
			//ConfineAvvio.Confine.setLayout(null);
			pannelloFornitoreSospensione.setLayout(null);
			pannelloFornitoreSospensione.setVisible(true);
			
			altezzaPannelloFornitoreSospensione = altezzaConfine;
			larghezzaPannelloFornitoreSospensione = ConfineAvvio.Confine.getWidth();
			
			pannelloFornitoreSospensione.setBounds(0,0,larghezzaPannelloFornitoreSospensione, altezzaPannelloFornitoreSospensione);
			pannelloFornitoreSospensione.setBackground(new Color(190,190,190));
			
			int larghezzaBottoni = larghezzaPannelloFornitoreSospensione/4;			
			//int bordoBottoni = 30;
			int altezzaBottoni = 40;
			
			//JLabel labelSospensioneFornitore = new JLabel("ModalitÃ  FORNITORE in Sospensione...");
			//labelSospensioneFornitore.setBounds((larghezzaPannelloFornitoreSospensione/3 -20),30, lunghezzaScritta, altezzaScritta);
			//labelSospensioneFornitore.setFont(new Font("Arial", 0, 20));
			JLabel labelSospensioneFornitore = new JLabel("Modalità FORNITORE in Sospensione...");
			labelSospensioneFornitore.setBounds((900-lunghezzaScritta)/2,30, lunghezzaScritta, altezzaScritta);
			labelSospensioneFornitore.setFont(new Font("Verdana", Font.BOLD, 20));
			
			int locazioneXBottRiprendiFornitore = larghezzaPannelloFornitoreSospensione/5;
			int locazioneYBottoni = altezzaPannelloFornitoreSospensione/6;
			
			JButton bRiprendiFornitore = new JButton(new ImageIcon("Save/immagini/riprendi.gif"));
	        bRiprendiFornitore.setRolloverIcon(new ImageIcon("Save/immagini/riprendiR.gif"));
	        bRiprendiFornitore.setBorderPainted(false);
	        bRiprendiFornitore.setMargin (new Insets (0, 0, 0, 0));
	        bRiprendiFornitore.setContentAreaFilled(false);
	        bRiprendiFornitore.setOpaque(true);
	        bRiprendiFornitore.setToolTipText("Riprendi");
	        bRiprendiFornitore.setBounds(200 ,locazioneYBottoni, 200, 40);
			
			JButton bEsci = new JButton(new ImageIcon("Save/immagini/escib.gif"));
	        bEsci.setRolloverIcon(new ImageIcon("Save/immagini/escibR.gif"));
	        bEsci.setBorderPainted(false);
	        bEsci.setMargin (new Insets (0, 0, 0, 0));
	        bEsci.setContentAreaFilled(false);
	        bEsci.setOpaque(true);
	        bEsci.setToolTipText("Chiudi Programma");
	        bEsci.setBounds(500, locazioneYBottoni, 200, 40);
			
			pannelloFornitoreSospensione.add(labelSospensioneFornitore);
			pannelloFornitoreSospensione.add(bRiprendiFornitore);;
			pannelloFornitoreSospensione.add(bEsci);
			
			//Ascoltatori
		    ascoltatoreEtAzioneRiprendiFornitore = new RiprendiFornitoreAA();
		    ascoltatoreEtAzioneEsci = new EsciAA();
			
			
		    //Associazione
		    bRiprendiFornitore.addActionListener(ascoltatoreEtAzioneRiprendiFornitore);
		    bEsci.addActionListener(ascoltatoreEtAzioneEsci);
			
		}// Fine sospensione()

		private void forniture(int locazioneYPannelloForniture)
		{
			
			nomeEsternoCorrente= (String) controlloreCorrente.keySet().toArray()[0];
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloFornitore2Forniture);
			ConfineAvvio.Confine.setLayout(null);
			pannelloFornitore2Forniture.setLayout(null);
			pannelloFornitore2Forniture.setVisible(true);
			
			locazioneYPannelloForniture += 5;
			altezzaPannelloFornitore2Forniture = altezzaConfine - locazioneYPannelloForniture -38;
			
			int larghezzaPannello= ConfineAvvio.Confine.getWidth();
			int lunghezzaScritta=200;
			int altezzaScrittaTitolo = 20;
			int altezzaScritta = 20;
			
			pannelloFornitore2Forniture.setBounds(0,locazioneYPannelloForniture,larghezzaPannello, altezzaPannelloFornitore2Forniture);
			pannelloFornitore2Forniture.setBackground(new Color(190,190,190));
			
			//altezzaPannelloFornitore2Forniture = pannelloFornitore2Forniture.getHeight(); //Questo deve stare qui (cioÃ¨ dopo aver impostato il size di pannelloAdmin3)
			larghezzaPannelloFornitore2Forniture = larghezzaPannello;			
			locazioneYPrimaRiga = (altezzaPannelloFornitore2Forniture)*25/100;
			locazioneYSecondaRiga =	(altezzaPannelloFornitore2Forniture)*50/100;
			locazioneYTerzaRiga =	(altezzaPannelloFornitore2Forniture)*80/100;
			
			//Titolo
			labelForniture = new JLabel("Forniture");
			labelForniture.setBounds((pannelloFornitore2Forniture.getWidth()/3+100),10, lunghezzaScritta, altezzaScrittaTitolo);
			labelForniture.setFont(new Font("Verdana", Font.BOLD, 20));

			
			int altezzaBottoni = 40;
			//Scritte e Tendine
					
			//int larghezzaBottoni = pannelloFornitore2Forniture.getWidth()/5;	
			
		
			//PRIMA RIGA
			//CAMPI DI TESTO che simulano il Codice A Barre
			int lunghezzaScrittalabelCodiceBarre = 150;
			int locazioneXlabelCodiceBarre = larghezzaPannelloFornitore2Forniture/40;
			labelCodiceBarre = new JLabel("Codice a Barre:");
			labelCodiceBarre.setFont(new Font("Verdana",Font.BOLD,14));
			labelCodiceBarre.setBounds(locazioneXlabelCodiceBarre,locazioneYPrimaRiga+10,lunghezzaScrittalabelCodiceBarre, altezzaScritta);
			
			int lunghezzaCampoTestoNomeCommerciale = 230;
			int locazioneXCampoTestoNomeCommerciale = locazioneXlabelCodiceBarre+lunghezzaScrittalabelCodiceBarre;
			nomeCommerciale      	 = new JTextField("", 10);
			nomeCommerciale.setBounds(locazioneXCampoTestoNomeCommerciale,locazioneYPrimaRiga,lunghezzaCampoTestoNomeCommerciale,altezzaBottoni);
			nomeCommerciale.setFont(new Font("Verdana",0,12));
			
			int locazioneXlabelNomeCommerciale =  locazioneXCampoTestoNomeCommerciale;
			int lunghezzaScrittaNomeCommerciale = 500;
			labelFormatoCodiceBarre = new JLabel("              Nome Commerciale               Dimensione    Data Scadenza");
			labelFormatoCodiceBarre.setFont(new Font("Verdana",0,12));
			labelFormatoCodiceBarre.setBounds(locazioneXlabelNomeCommerciale,locazioneYPrimaRiga-15, lunghezzaScrittaNomeCommerciale, altezzaScritta);
			
			
			int lunghezzaCampoTestoDimensione = 90;
			int locazioneXCampoTestoDimensione = locazioneXCampoTestoNomeCommerciale+lunghezzaCampoTestoNomeCommerciale;
			dimensione	     	 = new JTextField("", 10);
			dimensione.setBounds(locazioneXCampoTestoDimensione,locazioneYPrimaRiga,lunghezzaCampoTestoDimensione,altezzaBottoni);
			dimensione.setFont(new Font("Verdana",0,12));
			
			int lunghezzaCodiceDataScadenza = 25;
			int locazioneXCodiceDataScadenzaGiorno = locazioneXCampoTestoDimensione + lunghezzaCampoTestoDimensione+5;
			try	{
			codiceDataScadenzaGiorno = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			//campoDataScadenzaGiorno = new JTextField(1);
			codiceDataScadenzaGiorno.setBounds(locazioneXCodiceDataScadenzaGiorno,locazioneYPrimaRiga,lunghezzaCodiceDataScadenza,altezzaBottoni);
			codiceDataScadenzaGiorno.setFont(new Font("Verdana",0,12));
			
			int locazioneXCodiceDataScadenzaMese = locazioneXCodiceDataScadenzaGiorno + lunghezzaCodiceDataScadenza;
			try {
			codiceDataScadenzaMese    	 = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			codiceDataScadenzaMese.setBounds(locazioneXCodiceDataScadenzaMese,locazioneYPrimaRiga,lunghezzaCodiceDataScadenza,altezzaBottoni);
			codiceDataScadenzaMese.setFont(new Font("Verdana",0,12));
			
			int locazioneXCodiceDataScadenzaAnno = locazioneXCodiceDataScadenzaMese + lunghezzaCodiceDataScadenza;
			try {
			codiceDataScadenzaAnno    	 = new JFormattedTextField(new MaskFormatter("####"));
			} catch (ParseException e) {e.printStackTrace();}
			codiceDataScadenzaAnno.setBounds(locazioneXCodiceDataScadenzaAnno,locazioneYPrimaRiga,lunghezzaCodiceDataScadenza*2,altezzaBottoni);
			codiceDataScadenzaAnno.setFont(new Font("Verdana",0,12));
			
			
			//BOTTONE E TEXTAREA   **LISTA SCADENZE**
			int locazioneXBottonelistaGiacenze = larghezzaPannelloFornitore2Forniture*3/4 -30;
			int larghezzaBottoneListaScad = 220;
			int locazioneYBottListaScad = locazioneYPrimaRiga-20;
	        bListaScadenze= new JButton(new ImageIcon("Save/immagini/lista.gif"));
	        bListaScadenze.setRolloverIcon(new ImageIcon("Save/immagini/listaR.gif"));
	        bListaScadenze.setBorderPainted(false);
	        bListaScadenze.setMargin (new Insets (0, 0, 0, 0));
	        bListaScadenze.setContentAreaFilled(false);
	        bListaScadenze.setOpaque(true);
	        bListaScadenze.setToolTipText("Mostra lista prodotti con scadenza");
	        bListaScadenze.setBounds(locazioneXBottonelistaGiacenze,locazioneYBottListaScad,larghezzaBottoneListaScad, 30);
			
			int altezzaMenu = (altezzaPannelloFornitore2Forniture*2)/4 +15;
			int larghezzaMenu = larghezzaBottoneListaScad;
			int locazioneXScrollListaScadenze = locazioneXBottonelistaGiacenze;
			int locazioneYScrollListaScadenze = locazioneYBottListaScad + altezzaBottoni-10;
			areaListaScadenze = new JTextArea(320, 300);
			//areaMenu.setFont(new Font("Arial", 0 , 16));
			areaListaScadenze.setFont(new Font("Arial", 1, 14));
			//areaMenu.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 15));
			areaListaScadenze.setEditable(false);
			areaListaScadenze.setLineWrap(true);
			scrollListaScadenze = new JScrollPane(areaListaScadenze);
			scrollListaScadenze.setBounds(locazioneXScrollListaScadenze, locazioneYScrollListaScadenze, larghezzaMenu, altezzaMenu);
			
			int locazioneXBottoneGiacenzaProdotto = locazioneXBottonelistaGiacenze;
			int locazioneYBottoneGiacenzaProdotto = locazioneYScrollListaScadenze + altezzaMenu;
			int larghezzaBottoneGiacenza = larghezzaPannelloFornitore2Forniture/6;
	        bGiacenzaProdotti= new JButton(new ImageIcon("Save/immagini/giacenza30p.gif"));
	        bGiacenzaProdotti.setRolloverIcon(new ImageIcon("Save/immagini/giacenza30pR.gif"));
	        bGiacenzaProdotti.setBorderPainted(false);
	        bGiacenzaProdotti.setMargin (new Insets (0, 0, 0, 0));
	        bGiacenzaProdotti.setContentAreaFilled(false);
	        bGiacenzaProdotti.setOpaque(true);
	        bGiacenzaProdotti.setToolTipText("Mostra giacenza prodotto selezionato");
	        bGiacenzaProdotti.setBounds(locazioneXBottoneGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto, 150, 30);
	        
			int locazioneXvaloreGiacenzaProdotto = locazioneXBottoneGiacenzaProdotto + larghezzaBottoneGiacenza;
			int lunghezzaCampoTesto = 70;
			valoreGiacenzaProdotti    	= new JTextField("", 10);  
			valoreGiacenzaProdotti.setBounds(locazioneXvaloreGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto,lunghezzaCampoTesto, altezzaBottoni-10);
			valoreGiacenzaProdotti.setFont(new Font("Verdana", 1, 15));
			valoreGiacenzaProdotti.setEditable(false);
			valoreGiacenzaProdotti.setHorizontalAlignment(JLabel.CENTER);
			
			
			
			creaScritteTendinaCampoTesto();
			
			
			
			
	
			//SECONDA RIGA
			//BOTTONE DEPOSITA: LETTORE A BARRE
			
			int locazioneXlabelQuantita = (larghezzaPannelloFornitore2Forniture)*10/100;
			int lunghezzaScrittaQuantita = 80;
			labelQuantita 	 	= new JLabel("Quantità:");
			labelQuantita.setFont(new Font("Verdana",Font.BOLD,14));
			labelQuantita.setForeground(Color.red);
			labelQuantita.setHorizontalAlignment(JLabel.CENTER);
			labelQuantita.setVerticalAlignment(JLabel.CENTER);
			labelQuantita.setBounds(locazioneXlabelQuantita,locazioneYSecondaRiga,lunghezzaScrittaQuantita, altezzaScritta);
			
			int locazioneXvaloreQuantitaProdotti = locazioneXlabelQuantita + lunghezzaScrittaQuantita;
			int lunghezzaValoreQuantita = 130;
			valoreQuantita    	= new JTextField("", 10);  
			valoreQuantita.setBounds(locazioneXvaloreQuantitaProdotti,locazioneYSecondaRiga,lunghezzaValoreQuantita, altezzaScritta);
			valoreQuantita.setFont(new Font("Verdana", 1, 12));
			
			
			int locazioneXBottDeposita = locazioneXvaloreQuantitaProdotti + lunghezzaValoreQuantita + 70;
			int larghezzaBottoni = 150;
			altezzaBottoni =30;
	        //Bottone Erogazione Prodotti
	        bDepositaLettoreBarre= new JButton(new ImageIcon("Save/immagini/deposita.gif"));
	        bDepositaLettoreBarre.setRolloverIcon(new ImageIcon("Save/immagini/depositaR.gif"));
	        bDepositaLettoreBarre.setBorderPainted(false);
	        bDepositaLettoreBarre.setMargin (new Insets (0, 0, 0, 0));
	        bDepositaLettoreBarre.setContentAreaFilled(false);
	        bDepositaLettoreBarre.setOpaque(true);
	        bDepositaLettoreBarre.setToolTipText("Deposita prodotto");
	        bDepositaLettoreBarre.setBounds(locazioneXBottDeposita,locazioneYSecondaRiga-5,larghezzaBottoni, altezzaBottoni);
	        
			
			int lunghezzaLinea = (larghezzaPannelloFornitore2Forniture)*3/4;
			String line = "____________________________________________________________________________________________________________________________________________________";
			JLabel linea = new JLabel(line);
			linea.setBounds(0,locazioneYSecondaRiga+46, lunghezzaLinea, altezzaScritta);
			
			JLabel linea2 = new JLabel(line);
			linea2.setBounds(0,locazioneYSecondaRiga+44, lunghezzaLinea, altezzaScritta);
			
			
			//DATA SCADENZA
			int locazioneXlabelDataScad = larghezzaPannelloFornitore2Forniture/2 - 80;
			int lunghezzaScrittaData = 140;
			labelDataScad = new JLabel("Data Scadenza:");
			labelDataScad.setFont(new Font("Verdana",Font.BOLD,14));
			labelDataScad.setBounds(locazioneXlabelDataScad,locazioneYTerzaRiga,lunghezzaScrittaData, altezzaScritta);
			
			int lunghezzaCampoTestoDataScad = 25;
			int locazioneXCampoTestoDataScadGiorno = locazioneXlabelDataScad + lunghezzaScrittaData;
			try	{
			campoDataScadGiorno = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			//campoDataScadenzaGiorno = new JTextField(1);
			campoDataScadGiorno.setBounds(locazioneXCampoTestoDataScadGiorno,locazioneYTerzaRiga,lunghezzaCampoTestoDataScad,altezzaScritta);
			campoDataScadGiorno.setFont(new Font("Verdana",0,12));
			
			int locazioneXCampoTestoDataScadMese = locazioneXCampoTestoDataScadGiorno + lunghezzaCampoTestoDataScad;
			try {
			campoDataScadMese    	 = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			campoDataScadMese.setBounds(locazioneXCampoTestoDataScadMese,locazioneYTerzaRiga,lunghezzaCampoTestoDataScad,altezzaScritta);
			campoDataScadMese.setFont(new Font("Verdana",0,12));
			
			int locazioneXCampoTestoDataScadAnno = locazioneXCampoTestoDataScadMese + lunghezzaCampoTestoDataScad;
			try {
			campoDataScadAnno    	 = new JFormattedTextField(new MaskFormatter("####"));
			} catch (ParseException e) {e.printStackTrace();}
			campoDataScadAnno.setBounds(locazioneXCampoTestoDataScadAnno,locazioneYTerzaRiga,lunghezzaCampoTestoDataScad*2,altezzaScritta);
			campoDataScadAnno.setFont(new Font("Verdana",0,12));
			
			int locazioneXlabelFormatoData = locazioneXCampoTestoDataScadGiorno;
			int lunghezzaScrittaFormatoData = 140;
			labelFormatoData = new JLabel("(gg | mm | aaaa)");
			labelFormatoData.setFont(new Font("Verdana",0,11));
			labelFormatoData.setBounds(locazioneXlabelFormatoData,locazioneYTerzaRiga+altezzaScritta,lunghezzaScrittaFormatoData, altezzaScritta);
						
			//"X" chiusura Pannello	
			bChiudiPannelloForniture = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannelloForniture.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannelloForniture.setBounds(827, 5, 48, 20);
			bChiudiPannelloForniture.setBorderPainted(false);
			bChiudiPannelloForniture.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannelloForniture.setContentAreaFilled(false);
			bChiudiPannelloForniture.setOpaque(true);
			bChiudiPannelloForniture.setToolTipText("Chiusura Pannello");
			
			pannelloFornitore2Forniture.add(labelForniture);
			
			pannelloFornitore2Forniture.add(labelCodiceBarre);
			pannelloFornitore2Forniture.add(nomeCommerciale);
			pannelloFornitore2Forniture.add(dimensione);
			pannelloFornitore2Forniture.add(labelFormatoCodiceBarre);
			pannelloFornitore2Forniture.add(codiceDataScadenzaGiorno);
			pannelloFornitore2Forniture.add(codiceDataScadenzaMese);
			pannelloFornitore2Forniture.add(codiceDataScadenzaAnno);
			
			pannelloFornitore2Forniture.add(labelTendinaProdotti);
			pannelloFornitore2Forniture.add(tendinaProdotti);
			pannelloFornitore2Forniture.add(labelQuantita);
			pannelloFornitore2Forniture.add(valoreQuantita);
			
			pannelloFornitore2Forniture.add(labelDataScad);
			pannelloFornitore2Forniture.add(campoDataScadGiorno);
			pannelloFornitore2Forniture.add(campoDataScadMese);
			pannelloFornitore2Forniture.add(campoDataScadAnno);
			pannelloFornitore2Forniture.add(labelFormatoData);
			
			pannelloFornitore2Forniture.add(bGiacenzaProdotti);
			pannelloFornitore2Forniture.add(valoreGiacenzaProdotti);
			pannelloFornitore2Forniture.add(scrollListaScadenze);
			pannelloFornitore2Forniture.add(bListaScadenze);
			pannelloFornitore2Forniture.add(linea);
			pannelloFornitore2Forniture.add(linea2);
			pannelloFornitore2Forniture.add(bDepositaLettoreBarre);
			
			pannelloFornitore2Forniture.add(bChiudiPannelloForniture);
			
			
			//ASCOLTATORI
			ascoltatoreChiudiPannello 					= new ChiudiPannelloAA();
			
			
			//ascoltatoreEtAzioneNuovoProdotto			= new NuovoProdottoAA();
			ascoltatoreEtAzioneTendinaProdotti 			= new TendinaProdottiAA();
			ascoltatoreEtAzioneGiacenzaProdotti			= new GiacenzaProdottiAA();
			ascoltatoreEtAzioneDepositaProdotti			= new DepositaProdottiAA();
			ascoltatoreEtAzioneDepositaLB 				= new DepositaLettoreBarreAA();
			ascoltatoreBottoneListaScadenze 			= new BottoneListaScadenzeAA();	
			//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
			
			
			//ASSOCIAZIONI
			bChiudiPannelloForniture.addActionListener(ascoltatoreChiudiPannello);
			tendinaProdotti.addActionListener(ascoltatoreEtAzioneTendinaProdotti);
			bGiacenzaProdotti.addActionListener(ascoltatoreEtAzioneGiacenzaProdotti);
			bDepositaLettoreBarre.addActionListener(ascoltatoreEtAzioneDepositaLB);
			bListaScadenze.addActionListener(ascoltatoreBottoneListaScadenze);
			//bGiacenzaClassiAlim.addActionListener(ascoltatoreEtAzioneGiacenzaClasseAlim);
		}
		
		private void creaScritteTendinaCampoTesto()
		{
			
			
			//int bordoTendina = 30;
			if (controlloreCorrente.keySet()==null)
				JOptionPane.showMessageDialog(null, " Il magazzino è vuoto. ");
			else 
			{
				//int bordoScritta = 30;
				int lunghezzaScritta = 150;
				//int lunghezzaScrittaQuantita = 80;
				int altezzaScritta= 20;
				int larghezzaTendina = 170;
				int altezzaTendina = 20;
				
				int locazioneXlabelTendina = larghezzaPannelloFornitore2Forniture/40;
				labelTendinaProdotti = new JLabel("Scegli il prodotto:");
				labelTendinaProdotti.setFont(new Font("Verdana",Font.BOLD,14));
				labelTendinaProdotti.setBounds(locazioneXlabelTendina,locazioneYTerzaRiga,lunghezzaScritta, altezzaScritta);
				
				//Carica la tendina di tutti i prodotti!
				tendinaProdotti = new JComboBox(controlloreCorrente.keySet().toArray());
				//imposta visibile il primo prodotto della lista, quando la tendina Ã¨ chiusa
				tendinaProdotti.setSelectedIndex(0); //PRIMO NOME
				tendinaProdotti.setBounds(locazioneXlabelTendina+lunghezzaScritta,locazioneYTerzaRiga,larghezzaTendina, altezzaTendina);
			}
		}
		
		protected class SospendiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					pannelloFornitore1.setVisible(false);
					//pannelloAdmin2.setVisible(true);
					pannelloFornitore2.setVisible(false);
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
	
		private String controllaEAcquisisciDataScadenza(String giornoScad, String meseScad, String annoScad) throws DataNonCorrettaException
		{
			String dataAmericana="";
			if(giornoScad.equalsIgnoreCase("  ") && meseScad.equalsIgnoreCase("  ") && annoScad.equalsIgnoreCase("    "))
			{
				dataAmericana = String.valueOf((new Orologio().calcolaDataFormatoAmericano()));
			}
			else
			{
					//System.out.println("Data: "+Integer.parseInt(campoDataScadenzaGiorno.getText()) + " "+Integer.parseInt(campoDataScadenzaMese.getText()));
					int giorno = Integer.parseInt(giornoScad);
					int mese = Integer.parseInt(meseScad);
					int anno = Integer.parseInt(annoScad);
								
					if( (giorno<1) || (giorno>31) )	throw new DataNonCorrettaException("Il giorno deve essere un numero da 1 a 31!");
					if( (mese<1) || (mese>12) )	throw new DataNonCorrettaException("Il mese deve essere un numero da 1 a 12!");
					if((anno<2010) || (anno>2100) )	throw new DataNonCorrettaException("L'anno deve essere un numero da 2010 a 2100");
					if((mese==4 || mese==6 || mese==9 || mese==11) &&(giorno>30)) throw new DataNonCorrettaException("Il mese inserito è di massimo 30 giorni");	
					if((mese==2) && (giorno>29)) throw new DataNonCorrettaException("Febbraio è di 28 giorni! 29 se l'anno è bisestile.");
					
					int j=4;
					for(int i=2008; i<2100; i++)
					{
						if (j==0)
						{
							j=3;
							continue;
						}
						if(anno==i && mese==2)
						{
							if(giorno>28)
							{
								throw new DataNonCorrettaException("L'anno inserito non è bisestile!");
								//System.out.println(i+": Data Non corretta => "+ j);
								//break;
							}
						}
						j--;
					}
					dataAmericana = annoScad+meseScad+giornoScad;
					
			}//fine else
			return dataAmericana;
		}
		
		private class DepositaLettoreBarreAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{					
					try 
					{	
						//SE NON C'E' IL CODICE A BARRE
						if(nomeCommerciale.getText().equals("") && dimensione.getText().equals("") && codiceDataScadenzaGiorno.getText().equalsIgnoreCase("  ") && codiceDataScadenzaMese.getText().equalsIgnoreCase("  ")  && codiceDataScadenzaAnno.getText().equalsIgnoreCase("    "))
						{
							System.out.println("Codice A Barre Non Presente");
							JOptionPane.showMessageDialog(null,"Codice A Barre Non presente. Verranno presi i dati inviati da Tastiera", "Informazione", JOptionPane.WARNING_MESSAGE);
							
							ascoltatoreEtAzioneDepositaProdotti.actionPerformed(null);
						}
				
						else  //SE C'E' IL CODICE A BARRE
						{				
							if (valoreQuantita.getText().equals(""))
								JOptionPane.showMessageDialog(null, " Quantita nulla. ");
							
							else
							{
								LettoreABarre lab = new LettoreABarre();
								Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText());
								IDEsterno articolo = new IDEsterno(d.getIDEsterno());
								String dataAmericana = controllaEAcquisisciDataScadenza(codiceDataScadenzaGiorno.getText(), codiceDataScadenzaMese.getText(), codiceDataScadenzaAnno.getText());
								System.out.println("Data Americana: "+dataAmericana);
								
								controlloreCorrente.deposita(articolo,Integer.parseInt(valoreQuantita.getText()),Integer.parseInt(dataAmericana));
								Tone.sound(550,150);
								Tone.sound(450,250);
							}
						}
					} 
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  Numero non valido. ");
					}
					catch(ProdottoException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}				
					catch (QuantitaException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  QuantitÃ  non valida. ");
					}
					catch(DirittiException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA " + e.getMessage(), "non autorizzato", JOptionPane.ERROR_MESSAGE);
					}
					catch (DataNonCorrettaException e) 
					{
						JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (LineUnavailableException e) 
					{
						JOptionPane.showMessageDialog(null,"Problema Audio Beep Lettore a Barre", "Errore", JOptionPane.ERROR_MESSAGE);
					}
			}
		}
		
		private class DepositaProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				
				if (valoreQuantita.getText().equals(""))   
					JOptionPane.showMessageDialog(null, " Quantità nulla. ");

				else
				{
					System.out.println("Valore:"+campoDataScadAnno.getText()+"!");
					try
					{
						
						String dataAmericana = controllaEAcquisisciDataScadenza(campoDataScadGiorno.getText(), campoDataScadMese.getText(), campoDataScadAnno.getText());
						
						System.out.println("Data Americana: "+dataAmericana);
						
						IDEsterno tipo = new IDEsterno(nomeEsternoCorrente);		
						//controlloreCorrente.deposita(tipo,Integer.parseInt(valoreQuantitaProdotti.getText()));
						controlloreCorrente.deposita(tipo,Integer.parseInt(valoreQuantita.getText()),Integer.parseInt(dataAmericana));
						
						
						
						//Aggiorno i componenti
					    // Simulo la pressione dei tasti Giacenza e Lista Scadenze
						GiacenzaProdottiAA aggiornoVQuantita = new GiacenzaProdottiAA();
						aggiornoVQuantita.actionPerformed(null);
						BottoneListaScadenzeAA aggiornoListaScadenze = new BottoneListaScadenzeAA();
						aggiornoListaScadenze.actionPerformed(null);

						campoDataScadGiorno.setText("");
						campoDataScadMese.setText("");
						campoDataScadAnno.setText("");
						valoreQuantita.setText("");
					    valoreQuantita.requestFocus();
						
					    //System.out.println("Depositate unità  di "+nomeEsternoCorrente);
						
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  Numero non valido. ");
					}
					catch(ProdottoException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}				
					catch (QuantitaException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  Quantità  non valida. ");
					}
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  Nome prodotto non non valido. ");
					}
					catch(DirittiException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA " + e.getMessage(), "non autorizzato", JOptionPane.ERROR_MESSAGE);
					}
					catch (DataNonCorrettaException e) 
					{
						JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}			
				}
			}
		}
	
		protected class GiacenzaProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (nomeEsternoCorrente.equals(""))
					JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
				else 
				try
				{
					Integer n = new Integer(controlloreCorrente.giacenza(new IDEsterno(nomeEsternoCorrente)));
					valoreGiacenzaProdotti.setText(n.toString());
				}
				catch(DirittiException e)
				{
					JOptionPane.showMessageDialog(null, " GiacenzaaAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
				}				
				catch(ProdottoException e)
				{
					JOptionPane.showMessageDialog(null, " GiacenzaaAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				}				
				catch (IDEsternoException e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage(), "GiacenzaaAA Errore", JOptionPane.ERROR_MESSAGE);
				}
			}
		}

		private class CambiaUtenteAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloFornitore1.setVisible(true);
				pannelloFornitore1.setVisible(false);
				pannelloFornitore2.setVisible(true);
				pannelloFornitore2.setVisible(false);
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
		
		private class MenuAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per una eventuale freccetta della tendina
				pannelloFornitore1.setVisible(false);
				pannelloFornitore1.setVisible(true);
		        pannelloFornitore2.setVisible(false);
		        //Disabilito Pulsanti
				bForniture.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bMenu.setEnabled(false);
				
				new ConfineFornitoreVisualizzaMenu(controlloreCorrente, locazioneYProssimoPannello);	
			}
		}
		
		private class FornitureAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				bForniture.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bMenu.setEnabled(false);
									
				pannelloFornitore1.setVisible(false);	//disattivo e riattivo
				pannelloFornitore1.setVisible(true);
				file.setEnabled(false);
				
				pannelloFornitore2.setVisible(false); //IMPORTANTISSIMO NON RIATTIVARE INVECE QUESTO, ALTRIMENTI DA PROBLEMI LA TENDINA
				pannelloFornitore2Forniture.setVisible(false);
				forniture(locazioneYProssimoPannello);				
			}
		}
		private class RiprendiFornitoreAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloFornitoreSospensione.setVisible(false);
				pannelloFornitore1.setVisible(false);
				new ConfineFornitoreDeposito(controlloreCorrente);
			}
		}

		public class TendinaProdottiAA implements ActionListener 
		{ 
			    public void actionPerformed(ActionEvent e) 
			    {
			        JComboBox cb = (JComboBox)e.getSource();
			        nomeEsternoCorrente = (String)cb.getSelectedItem();
			        System.out.println("Tendina");
			        //System.out.println("Il nomeEsterno ora e': "+nomeEsternoCorrente);
			        //azzero Giacenza, Lista Scadenze e Quantità
					areaListaScadenze.setText("");
					valoreGiacenzaProdotti.setText("");
					campoDataScadGiorno.setText("");
					campoDataScadMese.setText("");
					campoDataScadAnno.setText("");
					valoreQuantita.setText("");
			    }
		}
		
		
		private class BottoneListaScadenzeAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String mappaScadenze = "";			
				areaListaScadenze.setText("DATA:\t Giacenza:\n");
				try 
				{
					mappaScadenze = (controlloreCorrente.getMappaProdotti()).get(nomeEsternoCorrente).stampaMapScadenza();
				} 
				catch (IDEsternoException e) 
				{
						System.out.print(e.getMessage()+"\n");
				} 				
				areaListaScadenze.append(mappaScadenze);
			}
		}
		
		private class ChiudiPannelloAA implements ActionListener
		{
				public void actionPerformed(ActionEvent arg0)
				{
					pannelloFornitore2Forniture.setVisible(false);
					bForniture.setEnabled(true);
					bSospendi.setEnabled(true);
					bCambiaUtente.setEnabled(true);
					file.setEnabled(true);
					
					pannelloFornitore1.setVisible(false);
					pannelloFornitore2.setVisible(false);
					barraMenu.setVisible(false);
					new ConfineFornitoreDeposito(controlloreCorrente);
					//ConfineAvvio.Confine.remove(pannelloFornitore2Forniture);
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
		
		/*private class DeserializzaAA implements ActionListener
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
		}*/
}
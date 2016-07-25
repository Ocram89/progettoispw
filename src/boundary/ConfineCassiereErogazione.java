package boundary;

	import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

//my -----------
import util.*;
import exception.*;
import control.*;

	public class ConfineCassiereErogazione extends StackFrame
	{

		//Versione temporanea per modificare al minimo il main di test
		private static final long	serialVersionUID	= 1L;
					
		//Barra dei Menù
		JMenuBar barraMenu = new JMenuBar();
		JMenu file;
		//JMenuItem nuovoItem;
		//JMenuItem caricaItem;
		JMenuItem salvaItem;
		JMenuItem chiudiItem;
		
		//Pannelli
		public  JPanel 	pannelloCassiere1= new JPanel(); 
		public  JPanel 	pannelloCassiere2= new JPanel(); 
		
		public  JPanel 	pannelloCassiere2Erogazione = new JPanel();
		
		public 	JPanel pannelloTitolo= new JPanel();
		public  JPanel pannelloBottoniCassiere= new JPanel();	
		public  JPanel pannelloCassiereSospensione = new JPanel();
			
		//JLabel
		public JLabel titoloConnesso= new JLabel();
		
		//Bottoni/
		public static JButton	bErogazione;
		
		private JButton 		bEsci; 
		public static JButton 	bMenu; 	

		public static JButton 	bCambiaUtente; 
		public static JButton 	bSospendi; 

		public JLabel 				labelCodiceBarre;
		public JFormattedTextField 	codiceDataScadenzaGiorno;
		public JFormattedTextField 	codiceDataScadenzaMese;
		public JFormattedTextField 	codiceDataScadenzaAnno;
		
		public JTextField	nomeCommerciale;
		public JTextField	dimensione;
		public JTextField   valoreQuantita;
		
		public JLabel		labelFormatoData;
		public JLabel 		labelErogazione;
		public JLabel		labelTendinaProdotti;
		
		// Tendine
		public JComboBox 	tendinaProdotti;
			
		public JLabel 		labelDataScad;
		public JLabel 		labelFormatoCodiceBarre;
		public JLabel 		labeldimensione;
		public JLabel 		labeldataScadenza;
		JFormattedTextField	campoDataScadGiorno;
		JFormattedTextField	campoDataScadMese;
		JFormattedTextField	campoDataScadAnno;
		
		//Bottoni
		//public JButton 		bErogaProdotti; 
		public JButton		bErogaLettoreBarre;
		public JButton    	bChiudiPannelloErogazione;
		
		public JButton 		bGiacenzaProdotti;
		public JButton		bListaScadenze;
			
		public JScrollPane 	scrollListaScadenze;
		public JTextArea 	areaListaScadenze;
				
		// Campi e loro etichette	
		
		//public JLabel     	labelQuantitaProdotti;
		public JLabel     	labelQuantita;
		//public JTextField 	valoreQuantitaProdotti;
		public JTextField 	valoreGiacenzaProdotti;
		
		
		//Ascoltatori
		private GiacenzaProdottiAA 			ascoltatoreEtAzioneGiacenzaProdotti;
		private ErogaProdottiAA				ascoltatoreEtAzioneErogaProdotti;
		private ErogaLettoreBarreAA			ascoltatoreEtAzioneErogaLB;
				
		// Ascoltatori di bottoni e relative azioni
		private CambiaUtenteAA		ascoltatoreEtAzioneCambiaUtente;
		private ErogazioneAA		ascoltatoreEtAzioneErogazione;
		private MenuAA				ascoltatoreEtAzioneMenu;
		private SospendiAA 			ascoltatoreEtAzioneSospendi;
		private EsciAA 				ascoltatoreEtAzioneEsci;
		private RiprendiCassiereAA	ascoltatoreEtAzioneRiprendiCassiere;
		
		private SerializzaAA			ascoltatoreEtAzioneSerializza;
		//private DeserializzaAA		ascoltatoreEtAzioneDeserializza;
		//private NuovaGestioneAA    	ascoltatoreEtAzioneNuovaGestione;
		//private SalvaLogAA			ascoltatoreEtAzioneSalvaLog;

		private TendinaProdottiAA   		ascoltatoreEtAzioneTendinaProdotti;
		private BottoneListaScadenzeAA		ascoltatoreBottoneListaScadenze;
		
		private ChiudiPannelloAA			ascoltatoreChiudiPannello;
		
		private String 	nomeEsternoCorrente;
		
		//Per Pannelli Cassiere1 e Cassiere2
		int altezzaConfine;
		int larghezzaConfine;
		int larghezzaPannelli;
		int altezzaPannelloCassiere1;
		int altezzaPannelloCassiere2;
		public static int locazioneYProssimoPannello;
		
		//Per Pannello Erogazione
		int altezzaPannelloCassiere2Erogazione;
		int larghezzaPannelloCassiere2Erogazione;
		int locazioneYPrimaRiga;
		int locazioneYSecondaRiga;
		int	locazioneYTerzaRiga;
		int altezzaBarraMenu;
		
		private Controllore controlloreCorrente;

		public ConfineCassiereErogazione(Controllore controllore)
		{
			this.controlloreCorrente=controllore;
			
			
			int altezzaBottoni = 40;
			int altezzaBottoniTitolo;
			int larghezzaBottoniTitolo;
			int bordoPannelli = 5;
			int bordoBottoni = 30;
			int larghezzaBottoni; //Da inizializzare dopo!!
			int altezzaPannelloTitoloCassiere = 40;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			larghezzaConfine = ConfineAvvio.Confine.getWidth();
			altezzaBarraMenu = 25;
			larghezzaPannelli = larghezzaConfine;
			altezzaPannelloCassiere1 = altezzaConfine/5 - 10;
			locazioneYProssimoPannello = altezzaPannelloCassiere1 + altezzaBarraMenu;
			// Costruisco pannello Amministratore
			ConfineAvvio.Confine.setLayout(null);
			pannelloCassiere1.setBounds(0,altezzaBarraMenu,larghezzaPannelli, altezzaPannelloCassiere1);
			pannelloCassiere1.setBackground(new Color(200, 200, 200));
			pannelloCassiere1.setLayout(null);
			ConfineAvvio.Confine.add(pannelloCassiere1);
			
			altezzaPannelloCassiere2 = altezzaConfine - altezzaPannelloCassiere1 -43;
			pannelloCassiere2.setLayout(null);
			pannelloCassiere2.setBounds(0,altezzaPannelloCassiere1+5 + altezzaBarraMenu,larghezzaPannelli, altezzaPannelloCassiere2);
			pannelloCassiere2.setBackground(new Color(190, 190, 190));
			ConfineAvvio.Confine.add(pannelloCassiere2);
			
			//Barra dei Menu
			barraMenu.setBounds(0, 0, larghezzaPannelli, altezzaBarraMenu);
			ConfineAvvio.Confine.add(barraMenu);
			
			file = new JMenu("File");
			barraMenu.add(file);
				
			salvaItem = new JMenuItem("Salva Magazzino");
			file.add(salvaItem);
			
			chiudiItem = new JMenuItem("Esci dal Programma");
			file.add(chiudiItem);
				
			//COSTRUISCO PANNELLO TITOLO
			pannelloTitolo.setLayout(null);
	        pannelloTitolo.setBackground(new Color(190, 190, 190));
	        pannelloTitolo.setBounds(0, 5, larghezzaPannelli, altezzaPannelloTitoloCassiere); 
	        pannelloTitolo.add(titoloConnesso); 
	        
	        //COMPONENTI DEL PANNELLO TITOLO:
	        //1.Titolo
	        titoloConnesso.setFont(new Font("Verdana", Font.BOLD, 20));
	        titoloConnesso.setLocation(0, 0);
	        titoloConnesso.setSize(larghezzaPannelli, altezzaPannelloTitoloCassiere);
	        titoloConnesso.setHorizontalAlignment(JLabel.CENTER);
	        titoloConnesso.setVerticalAlignment(JLabel.CENTER);
	        titoloConnesso.setText("Sei Connesso come CASSIERE");
			
	        //2.Bottone Indietro
	        larghezzaBottoniTitolo = 150;
	        altezzaBottoniTitolo = 30;
			/*bRitorna 			= new JButton("INDIETRO"); 
			bRitorna.setLocation(5,(altezzaPannelloTitolo/2)-(altezzaBottoniTitolo/2));
	        bRitorna.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);*/
	        
	        //Bottone Sospendi
	        bCambiaUtente = new JButton(new ImageIcon("Save/immagini/cambia_utente.gif"));
	        bCambiaUtente.setRolloverIcon(new ImageIcon("Save/immagini/cambia_utentiR.gif"));
	        bCambiaUtente.setBorderPainted(false);
	        bCambiaUtente.setMargin (new Insets (0, 0, 0, 0));
	        bCambiaUtente.setContentAreaFilled(false);
	        bCambiaUtente.setOpaque(true);
	        bCambiaUtente.setToolTipText("Cambia Utente");
	        bCambiaUtente.setBounds(750, 0, 42, 42);
	        
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
	        pannelloBottoniCassiere.setLayout(null);
	        pannelloBottoniCassiere.setBounds(0, bordoPannelli*2 +altezzaPannelloTitoloCassiere,larghezzaPannelli, altezzaConfine/10);
	        pannelloBottoniCassiere.setBackground(new Color(190, 190, 190));
	        
	        //System.out.println(altezzaPannelloBottoniAdmin);
	        
	        larghezzaBottoniTitolo = 150;
	        altezzaBottoniTitolo = 30;

	        //COMPONENTI PANNELLO BOTTONI
	               
	        // Bottone Eroga
	        int altezzaPannelloBottoniCassiere = pannelloBottoniCassiere.getHeight();
	        int locazioneYBottoni = (altezzaPannelloBottoniCassiere/2)-(altezzaBottoni/2);
	        
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
	                
	        bErogazione= new JButton(new ImageIcon("Save/immagini/erogazioni.gif"));
	        bErogazione.setRolloverIcon(new ImageIcon("Save/immagini/erogazioniR.gif"));
	        bErogazione.setBorderPainted(false);
	        bErogazione.setMargin (new Insets (0, 0, 0, 0));
	        bErogazione.setContentAreaFilled(false);
	        bErogazione.setOpaque(true);
	        bErogazione.setToolTipText("Erogazioni");
	        bErogazione.setBounds(500,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
	       
			
	        //AGGIUNTA BOTTONI AI VARI PANNELLI
			
			pannelloTitolo.add(bCambiaUtente);
			pannelloTitolo.add(bSospendi);
			pannelloTitolo.add(bEsci);
			
			pannelloBottoniCassiere.add(bMenu);
			pannelloBottoniCassiere.add(bErogazione);
			
		    
			 // Aggiunta dei Vari Pannelli al PANNELLO PRINCIPALE
	        pannelloCassiere1.add(pannelloTitolo);
	        pannelloCassiere1.add(pannelloBottoniCassiere);
	        
	        // Definisci ascoltatori di bottoni e relative azioni
	        ascoltatoreEtAzioneErogazione		= new ErogazioneAA();
	        ascoltatoreEtAzioneMenu 			= new MenuAA();
			ascoltatoreEtAzioneCambiaUtente		= new CambiaUtenteAA();
			ascoltatoreEtAzioneSospendi			= new SospendiAA();
			ascoltatoreEtAzioneEsci				= new EsciAA();	
			
			
			//ascoltatoreEtAzioneNuovaGestione    		= new NuovaGestioneAA();
			ascoltatoreEtAzioneSerializza				= new SerializzaAA();
			//ascoltatoreEtAzioneDeserializza    		 	= new DeserializzaAA();
			//ascoltatoreEtAzioneSalvaLog					= new SalvaLogAA();
			
			bEsci.addActionListener(ascoltatoreEtAzioneEsci);
			//bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
			bErogazione.addActionListener(ascoltatoreEtAzioneErogazione);
			bMenu.addActionListener(ascoltatoreEtAzioneMenu);
			bCambiaUtente.addActionListener(ascoltatoreEtAzioneCambiaUtente);
			bSospendi.addActionListener(ascoltatoreEtAzioneSospendi);
			//nuovoItem.addActionListener(ascoltatoreEtAzioneNuovaGestione);
			salvaItem.addActionListener(ascoltatoreEtAzioneSerializza);
			//caricaItem.addActionListener(ascoltatoreEtAzioneDeserializza);
			//salvaLogItem.addActionListener(ascoltatoreEtAzioneSalvaLog);
			chiudiItem.addActionListener(ascoltatoreEtAzioneEsci);		
		}
		// Fine costruttore
			
		private void sospensione()
		{
			int lunghezzaScritta=900;
			int altezzaScritta = 30;
			int altezzaPannelloCassiereSospensione;
			int larghezzaPannelloCassiereSospensione;
			
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloCassiereSospensione);
			//ConfineAvvio.Confine.setLayout(null);
			pannelloCassiereSospensione.setLayout(null);
			pannelloCassiereSospensione.setVisible(true);
			
			altezzaPannelloCassiereSospensione = altezzaConfine;
			larghezzaPannelloCassiereSospensione = ConfineAvvio.Confine.getWidth();
			
			pannelloCassiereSospensione.setBounds(0,0,larghezzaPannelloCassiereSospensione, altezzaPannelloCassiereSospensione);
			pannelloCassiereSospensione.setBackground(new Color(190,190,190));
				
			JLabel labelSospensioneCassiere = new JLabel("Modalità  CASSIERE in Sospensione...");
			labelSospensioneCassiere.setBounds(0,30, lunghezzaScritta, altezzaScritta);
			labelSospensioneCassiere.setFont(new Font("Verdana", Font.BOLD, 20));
			labelSospensioneCassiere.setHorizontalAlignment(JLabel.CENTER);
			labelSospensioneCassiere.setVerticalAlignment(JLabel.CENTER);
			
			int locazioneYBottoni = altezzaPannelloCassiereSospensione/6;
			JButton bRiprendiCassiere= new JButton(new ImageIcon("Save/immagini/riprendi.gif"));
	        bRiprendiCassiere.setRolloverIcon(new ImageIcon("Save/immagini/riprendiR.gif"));
	        bRiprendiCassiere.setBorderPainted(false);
	        bRiprendiCassiere.setMargin (new Insets (0, 0, 0, 0));
	        bRiprendiCassiere.setContentAreaFilled(false);
	        bRiprendiCassiere.setOpaque(true);
	        bRiprendiCassiere.setToolTipText("Riprendi");
	        bRiprendiCassiere.setBounds(200 ,locazioneYBottoni, 200, 40);
			
			JButton bEsci = new JButton(new ImageIcon("Save/immagini/escib.gif"));
	        bEsci.setRolloverIcon(new ImageIcon("Save/immagini/escibR.gif"));
	        bEsci.setBorderPainted(false);
	        bEsci.setMargin (new Insets (0, 0, 0, 0));
	        bEsci.setContentAreaFilled(false);
	        bEsci.setOpaque(true);
	        bEsci.setToolTipText("Chiudi Programma");
	        bEsci.setBounds(500, locazioneYBottoni, 200, 40);
			
			pannelloCassiereSospensione.add(labelSospensioneCassiere);
			pannelloCassiereSospensione.add(bRiprendiCassiere);;
			pannelloCassiereSospensione.add(bEsci);
			
			//Ascoltatori
		    ascoltatoreEtAzioneRiprendiCassiere = new RiprendiCassiereAA();
		    ascoltatoreEtAzioneEsci = new EsciAA();
			
			
		    //Associazione
		    bRiprendiCassiere.addActionListener(ascoltatoreEtAzioneRiprendiCassiere);
		    bEsci.addActionListener(ascoltatoreEtAzioneEsci);
			
		}// Fine sospensione()

		
		private void erogazione(int locazioneYPannelloErogazione)
		{
			
			nomeEsternoCorrente= (String) controlloreCorrente.keySet().toArray()[0];
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloCassiere2Erogazione);
			ConfineAvvio.Confine.setLayout(null);
			pannelloCassiere2Erogazione.setLayout(null);
			pannelloCassiere2Erogazione.setVisible(true);
			
			locazioneYPannelloErogazione += 5;
			altezzaPannelloCassiere2Erogazione = altezzaConfine - locazioneYPannelloErogazione -38;
			
			int larghezzaPannello= ConfineAvvio.Confine.getWidth();
			int lunghezzaScritta=200;
			int altezzaScrittaTitolo = 40;
			int altezzaScritta = 20;
			
			pannelloCassiere2Erogazione.setBounds(0,locazioneYPannelloErogazione,larghezzaPannello, altezzaPannelloCassiere2Erogazione);
			pannelloCassiere2Erogazione.setBackground(new Color(190,190,190));
			
			altezzaPannelloCassiere2Erogazione = pannelloCassiere2Erogazione.getHeight(); //Questo deve stare qui (cioÃ¨ dopo aver impostato il size di pannelloAdmin3)
			larghezzaPannelloCassiere2Erogazione = pannelloCassiere2Erogazione.getWidth();	
			
			locazioneYPrimaRiga = (altezzaPannelloCassiere2Erogazione)*25/100;
			locazioneYSecondaRiga =	(altezzaPannelloCassiere2Erogazione)*50/100;
			locazioneYTerzaRiga =	(altezzaPannelloCassiere2Erogazione)*80/100;
			
			//Titolo
			labelErogazione = new JLabel("Erogazione");
			labelErogazione.setBounds((pannelloCassiere2Erogazione.getWidth()/3+90),10, lunghezzaScritta, altezzaScrittaTitolo);
			labelErogazione.setFont(new Font("Verdana", Font.BOLD, 20));


			int altezzaBottoni = 40;
			
			//PRIMA RIGA
			//CAMPI DI TESTO che simulano il Codice A Barre
			int lunghezzaScrittalabelCodiceBarre = 150;
			int locazioneXlabelCodiceBarre = larghezzaPannelloCassiere2Erogazione/40;
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
			int locazioneXBottonelistaGiacenze = larghezzaPannelloCassiere2Erogazione*3/4 -30;
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
			
			int altezzaMenu = (altezzaPannelloCassiere2Erogazione*2)/4 +15;
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
			int larghezzaBottoneGiacenza = larghezzaPannelloCassiere2Erogazione/6;
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
			
			
			//Scritte e Tendine

			//int larghezzaBottoni = pannelloCassiere2Erogazione.getWidth()/5;	
			
		//	int distanzaTraBottoni = 50;
			
			creaScritteTendinaCampoTesto();
			
			
			//SECONDA RIGA
			//BOTTONI EROGA: LETTORE A BARRE
			
			int locazioneXlabelQuantita = (larghezzaPannelloCassiere2Erogazione)*10/100-20;
			int lunghezzaScrittaQuantita = 80;
			labelQuantita 	 	= new JLabel("Quantità:");
			labelQuantita.setFont(new Font("Verdana",Font.BOLD,14));
			labelQuantita.setForeground(Color.red);
			labelQuantita.setHorizontalAlignment(JLabel.CENTER);
			labelQuantita.setVerticalAlignment(JLabel.CENTER);
			labelQuantita.setBounds(locazioneXlabelQuantita,locazioneYSecondaRiga,lunghezzaScrittaQuantita, altezzaScritta);
			
			int locazioneXvaloreQuantitaProdotti = locazioneXlabelQuantita + lunghezzaScrittaQuantita + 20;
			int lunghezzaValoreQuantita = 130;
			valoreQuantita    	= new JTextField("", 10);  
			valoreQuantita.setBounds(locazioneXvaloreQuantitaProdotti,locazioneYSecondaRiga,lunghezzaValoreQuantita, altezzaScritta);
			valoreQuantita.setFont(new Font("Verdana", 1, 12));
			
			
			int locazioneXBottoneErogaLettore = locazioneXvaloreQuantitaProdotti + lunghezzaValoreQuantita + 70;
			int larghezzaBottoni = 150;
			altezzaBottoni =30;
	        //Bottone Erogazione Prodotti
	        bErogaLettoreBarre= new JButton(new ImageIcon("Save/immagini/eroga.gif"));
	        bErogaLettoreBarre.setRolloverIcon(new ImageIcon("Save/immagini/erogaR.gif"));
	        bErogaLettoreBarre.setBorderPainted(false);
	        bErogaLettoreBarre.setMargin (new Insets (0, 0, 0, 0));
	        bErogaLettoreBarre.setContentAreaFilled(false);
	        bErogaLettoreBarre.setOpaque(true);
	        bErogaLettoreBarre.setToolTipText("Eroga prodotto");
	        bErogaLettoreBarre.setBounds(locazioneXBottoneErogaLettore,locazioneYSecondaRiga-5,larghezzaBottoni, altezzaBottoni);
	        
				
			
			int lunghezzaLinea = (larghezzaPannelloCassiere2Erogazione)*3/4;
			String line = "____________________________________________________________________________________________________________________________________________________";
			JLabel linea = new JLabel(line);
			linea.setBounds(0,locazioneYSecondaRiga+46, lunghezzaLinea, altezzaScritta);
			
			JLabel linea2 = new JLabel(line);
			linea2.setBounds(0,locazioneYSecondaRiga+44, lunghezzaLinea, altezzaScritta);
			
			
			//DATA SCADENZA
			int locazioneXlabelDataScad = larghezzaPannelloCassiere2Erogazione/2 - 80;
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
			
			
			/*int locazioneXBottEroga = larghezzaPannelloCassiere2Erogazione/3 +40;
			bErogaProdotti 			= new JButton("Eroga");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bErogaProdotti.setBounds(locazioneXBottEroga,locazioneYSecondaRiga,larghezzaBottoni,altezzaBottoni);
			*/
			
			//"X" chiusura Pannello	
			bChiudiPannelloErogazione = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannelloErogazione.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannelloErogazione.setBounds(827, 5, 48, 20);
			bChiudiPannelloErogazione.setBorderPainted(false);
			bChiudiPannelloErogazione.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannelloErogazione.setContentAreaFilled(false);
			bChiudiPannelloErogazione.setOpaque(true);
			bChiudiPannelloErogazione.setToolTipText("Chiusura Pannello");
			
			//Aggiunta
			pannelloCassiere2Erogazione.add(labelErogazione);
			
			pannelloCassiere2Erogazione.add(labelCodiceBarre);
			pannelloCassiere2Erogazione.add(nomeCommerciale);
			pannelloCassiere2Erogazione.add(dimensione);
			pannelloCassiere2Erogazione.add(codiceDataScadenzaGiorno);
			pannelloCassiere2Erogazione.add(codiceDataScadenzaMese);
			pannelloCassiere2Erogazione.add(codiceDataScadenzaAnno);
			
			
			pannelloCassiere2Erogazione.add(labelDataScad);
			pannelloCassiere2Erogazione.add(campoDataScadGiorno);
			pannelloCassiere2Erogazione.add(campoDataScadMese);
			pannelloCassiere2Erogazione.add(campoDataScadAnno);
			pannelloCassiere2Erogazione.add(labelFormatoData);
			pannelloCassiere2Erogazione.add(labelFormatoCodiceBarre);
			
			
			pannelloCassiere2Erogazione.add(labelTendinaProdotti);
			pannelloCassiere2Erogazione.add(tendinaProdotti);
			pannelloCassiere2Erogazione.add(labelQuantita);
			pannelloCassiere2Erogazione.add(valoreQuantita);
			
			pannelloCassiere2Erogazione.add(bListaScadenze);
			pannelloCassiere2Erogazione.add(scrollListaScadenze);
			
			pannelloCassiere2Erogazione.add(bGiacenzaProdotti);
			pannelloCassiere2Erogazione.add(valoreGiacenzaProdotti);
			
			pannelloCassiere2Erogazione.add(linea);
			pannelloCassiere2Erogazione.add(linea2);
			pannelloCassiere2Erogazione.add(bErogaLettoreBarre);
			
			pannelloCassiere2Erogazione.add(bChiudiPannelloErogazione);
			
			
			//ASCOLTATORI
			ascoltatoreChiudiPannello 					= new ChiudiPannelloAA();
			
			
			//ascoltatoreEtAzioneNuovoProdotto			= new NuovoProdottoAA();
			ascoltatoreEtAzioneTendinaProdotti 			= new TendinaProdottiAA();
			ascoltatoreEtAzioneGiacenzaProdotti			= new GiacenzaProdottiAA();
			ascoltatoreEtAzioneErogaProdotti			= new ErogaProdottiAA();
			ascoltatoreEtAzioneErogaLB					= new ErogaLettoreBarreAA();
			ascoltatoreBottoneListaScadenze 			= new BottoneListaScadenzeAA();	
			//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
			
			
			//ASSOCIAZIONI
			bChiudiPannelloErogazione.addActionListener(ascoltatoreChiudiPannello);
			tendinaProdotti.addActionListener(ascoltatoreEtAzioneTendinaProdotti);
			bGiacenzaProdotti.addActionListener(ascoltatoreEtAzioneGiacenzaProdotti);
			bErogaLettoreBarre.addActionListener(ascoltatoreEtAzioneErogaLB);
			bListaScadenze.addActionListener(ascoltatoreBottoneListaScadenze);
			
			
			//bGiacenzaClassiAlim.addActionListener(ascoltatoreEtAzioneGiacenzaClasseAlim);
		}
		
		private void creaScritteTendinaCampoTesto()
		{
			//int bordoTendina = 30;
			if (controlloreCorrente.keySet()==null)
				JOptionPane.showMessageDialog(null, " Il magazzino Ã¨ vuoto. ");
			else 
			{
				//int bordoScritta = 30;
				int lunghezzaScritta = 150;
				//int lunghezzaScrittaQuantita = 80;
				int altezzaScritta= 20;
				int larghezzaTendina = 170;
				int altezzaTendina = 20;
				
				int locazioneXlabelTendina = larghezzaPannelloCassiere2Erogazione/40;
				labelTendinaProdotti = new JLabel("Scegli il prodotto:");
				labelTendinaProdotti.setFont(new Font("Verdana",Font.BOLD,14));
				labelTendinaProdotti.setBounds(locazioneXlabelTendina,locazioneYTerzaRiga,lunghezzaScritta, altezzaScritta);
				
				//Carica la tendina di tutti i prodotti!
				tendinaProdotti = new JComboBox(controlloreCorrente.keySet().toArray());
				//imposta visibile il primo prodotto della lista, quando la tendina Ã¨ chiusa
				tendinaProdotti.setSelectedIndex(0); //PRIMO NOME
				tendinaProdotti.setBounds(locazioneXlabelTendina+lunghezzaScritta,locazioneYTerzaRiga,larghezzaTendina, altezzaTendina);
								
				/*int locazioneXlabelQuantitaProdotti = larghezzaPannelloCassiere2Erogazione/3 +40;
				labelQuantita 	 	= new JLabel("QuantitÃ :");
				labelQuantita.setFont(new Font("Arial",0,18));
				labelQuantita.setBounds(locazioneXlabelQuantitaProdotti,locazioneYPrimaRiga,lunghezzaScrittaQuantita, altezzaScritta);
				
				int locazioneXvaloreQuantitaProdotti = locazioneXlabelQuantitaProdotti + lunghezzaScrittaQuantita;
				valoreQuantita    	= new JTextField("", 10);  
				valoreQuantita.setBounds(locazioneXvaloreQuantitaProdotti,locazioneYPrimaRiga,100, altezzaScritta);
				valoreQuantita.setFont(new Font("Arial", 1, 15));*/
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
					if((mese==4 || mese==6 || mese==9 || mese==11) &&(giorno>30)) throw new DataNonCorrettaException("Il mese inserito Ã¨ di massimo 30 giorni");	
					if((mese==2) && (giorno>29)) throw new DataNonCorrettaException("Febbraio Ã¨ di 28 giorni! 29 se l'anno Ã¨ bisestile.");
					
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
								throw new DataNonCorrettaException("L'anno inserito non Ã¨ bisestile!");
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
		
		private class RiprendiCassiereAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloCassiereSospensione.setVisible(false);
				pannelloCassiere1.setVisible(false);
				new ConfineCassiereErogazione(controlloreCorrente);
				
			}
		}

		private class SospendiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try
				{
					pannelloCassiere1.setVisible(false);
					//pannelloAdmin2.setVisible(true);
					pannelloCassiere2.setVisible(false);
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
					JOptionPane.showMessageDialog(null, " ChiudAA. " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
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

		private class ErogaProdottiAA implements ActionListener
		{
			IDEsterno articolo;
			public void actionPerformed(ActionEvent arg0)
			{
				
				if (valoreQuantita.getText().equals(""))
					JOptionPane.showMessageDialog(null, " Quantita nulla. ");
				else if (nomeEsternoCorrente.equals(""))
					JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
				else
					try
					{
						articolo = new IDEsterno(nomeEsternoCorrente);	
						controlloreCorrente.eroga(articolo,Integer.parseInt(valoreQuantita.getText()));
						//eroga() di ControlloreGestione!
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA Quantità non valida. " + e.getMessage());
					}
					catch(DirittiException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
					}				
					catch(ProdottoException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}				
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA Nome prodotto non valido. " + e.getMessage());
					}
					catch (QuantitaException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA Quantità  negativa o nulla. " + e.getMessage());
					}
					catch (MagazzinoException e) 
					{
						JOptionPane.showMessageDialog(null,"Attenzione! "+ e.getMessage());
					}
					catch (GiacenzaInsufficienteException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA Giacenza insufficiente. " + e.getMessage());
						int risp = JOptionPane.showConfirmDialog(null, "Vuoi proseguire comunque con l'erogazione?", "Attenzione!", JOptionPane.YES_NO_OPTION);
						System.out.println("Giacenza Insufficente");
						try
						{
//									if(risp==JOptionPane.YES_OPTION) 
//									{   //Se "risp" == OptionPane.YES_OPTION allora ErogaCmq, altrimenti aggiorna solo Richieste
								controlloreCorrente.erogaCmqOAggiornaRich(articolo,Integer.parseInt(valoreQuantita.getText()), risp);
//									}
//									else 
//									{
//										//Aggiorna Richieste!!
//									}
							
						}
						catch (Exception e1)
						{
							System.out.println("Succede qualcosa?");
						}
					} 
					catch (ProdottiScadutiSolamenteException e) 
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA ProdottiScaduti. " + e.getMessage());
					} 
					catch (ProdottiScadutiEProdottiBuoniException e) 
					{
						System.out.println("Trovati prodotti Scaduti e Prodotti buoni");
						JOptionPane.showMessageDialog(null, e.getMessage()+"Il Sistema comunque procederà  all'erogazione di Prodotti non Scaduti.");
						//JOptionPane.showMessageDialog(null, e.getMessage());
						//int risp = JOptionPane.showConfirmDialog(null, "Vuoi proseguire comunque con l'erogazione?", "Attenzione!", JOptionPane.YES_NO_OPTION);
						
						
						//IL SISTEMA PROCEDE OBLIGATORIAMENTE ALL'EROGAZIONE DEI PRODOTTI BUONI
						//int risp = JOptionPane.showConfirmDialog(null, "Il Sistema cercherÃ  Prodotti Buoni! Continuare con l'erogazione?", "Attenzione!", JOptionPane.YES_NO_OPTION);
						try
						{
							//if(risp==JOptionPane.YES_OPTION) 
							//{   //Se "risp" == OptionPane.YES_OPTION allora ErogaCmq, altrimenti aggiorna solo Richieste
								controlloreCorrente.erogaIndividuaProdottiBuoni(articolo,Integer.parseInt(valoreQuantita.getText()));
								
							//}
							//else 
							//{
								//Aggiorna Richieste!!
								System.out.println("Erogazione non effettuata");
							//}
	
						}
						
						catch (IDEsternoException e1)
						{
							JOptionPane.showMessageDialog(null, "\n ErogaAA Nome prodotto non valido. " + e1.getMessage());
						} 
						catch (NumberFormatException e1) 
						{
							e1.printStackTrace();
						} 
						catch (DirittiException e1) 
						{
							e1.printStackTrace();
						} 
						catch (GiacenzaInsufficienteException e1) 
						{
							JOptionPane.showMessageDialog(null, "\n ErogaAA ProdottiScadutiEBuoni." + e1.getMessage());
							
							int risp2 = JOptionPane.showConfirmDialog(null, "Vuoi proseguire comunque con l'erogazione?", "Attenzione!", JOptionPane.YES_NO_OPTION);
							try 
							{
								//Passo da qua se la giacenza è minore della richiesta
								controlloreCorrente.erogaCmqOAggiornaRich(articolo,Integer.parseInt(valoreQuantita.getText()), risp2);
							} 
							catch (NumberFormatException e2) {
								e2.printStackTrace();
							} catch (DirittiException e2) {
								e2.printStackTrace();
							} catch (IDEsternoException e2) {								
								e2.printStackTrace();
							} catch (ProdottoException e2) {
								e2.printStackTrace();
							} catch (QuantitaException e2) {
								e2.printStackTrace();
							} catch (GiacenzaInsufficienteException e2) {
								e2.printStackTrace();
							}			
						} 
						catch (ProdottoException e1) 
						{
							e.printStackTrace();
						} 
					} 
				

				//Aggiorno i componenti
			    // Simulo la pressione dei tasti Giacenza e Lista Scadenze
				GiacenzaProdottiAA aggiornoVQuantita = new GiacenzaProdottiAA();
				aggiornoVQuantita.actionPerformed(null);
				BottoneListaScadenzeAA aggiornoListaScadenze = new BottoneListaScadenzeAA();
				aggiornoListaScadenze.actionPerformed(null);

				valoreQuantita.setText("");
			    valoreQuantita.requestFocus();
			}
		}
		
		private class ErogaLettoreBarreAA implements ActionListener
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
							
							ascoltatoreEtAzioneErogaProdotti.actionPerformed(null);
						}
				
						else  //SE C'E' IL CODICE A BARRE
						{		
							LettoreABarre lab = new LettoreABarre();
							Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText());
							IDEsterno articolo = new IDEsterno(d.getIDEsterno());
							
							//controlloreCorrente.erogaLB(articolo);
						}
					} 
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					/*catch (MagazzinoException e) 
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (GiacenzaInsufficienteException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (DirittiException e) 
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (ProdottoException e) 
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (LineUnavailableException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}*/
					
				}
		}

		private class GiacenzaProdottiAA implements ActionListener
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
				pannelloCassiere1.setVisible(true);
				pannelloCassiere1.setVisible(false);
				pannelloCassiere2.setVisible(true);
				pannelloCassiere2.setVisible(false);
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
	
		private class ErogazioneAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
					bErogazione.setEnabled(false);
					bCambiaUtente.setEnabled(false);
					bSospendi.setEnabled(false);
					bMenu.setEnabled(false);
					
/*SE QUALCOSA NON VA RIATTIVARLO!! */ //pannelloAdmin3OpzioniAdmin.setVisible(false); 
					
					pannelloCassiere1.setVisible(false);	//disattivo e riattivo
					pannelloCassiere1.setVisible(true);
					file.setEnabled(false);
					
					pannelloCassiere2.setVisible(false); //IMPORTANTISSIMO NON RIATTIVARE INVECE QUESTO, ALTRIMENTI DA PROBLEMI LA TENDINA
					pannelloCassiere2Erogazione.setVisible(false);
					erogazione(locazioneYProssimoPannello);	
			}
		}
		
		private class MenuAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//Importante per una eventuale freccetta della tendina
				pannelloCassiere1.setVisible(false);
				pannelloCassiere1.setVisible(true);
		        pannelloCassiere2.setVisible(false);
		        //Disabilito Pulsanti
				bErogazione.setEnabled(false);
				bCambiaUtente.setEnabled(false);
				bSospendi.setEnabled(false);
				bMenu.setEnabled(false);
				
				new ConfineCassiereVisualizzaMenu(controlloreCorrente, locazioneYProssimoPannello);	
			}
		}
		
		private class TendinaProdottiAA implements ActionListener 
		{ 
			    public void actionPerformed(ActionEvent e) 
			    {
			        JComboBox cb = (JComboBox)e.getSource();
			        nomeEsternoCorrente = (String)cb.getSelectedItem();
			        System.out.println("Cambiato valore Tendina: \"Scelta Prodotto\"");
			        //System.out.println("Il nomeEsterno ora e': "+nomeEsternoCorrente);
			        //azzero Giacenza, Lista Scadenze e Quantità
					areaListaScadenze.setText("");
					valoreGiacenzaProdotti.setText("");
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
					pannelloCassiere2Erogazione.setVisible(false);
					bErogazione.setEnabled(true);
					bSospendi.setEnabled(true);
					bCambiaUtente.setEnabled(true);
					file.setEnabled(true);
					pannelloCassiere1.setVisible(false);
					pannelloCassiere2.setVisible(false);
					barraMenu.setVisible(false);
					new ConfineCassiereErogazione(controlloreCorrente);
					//ConfineAvvio.Confine.remove(pannelloCassiere2Erogazione);
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
		
		
}
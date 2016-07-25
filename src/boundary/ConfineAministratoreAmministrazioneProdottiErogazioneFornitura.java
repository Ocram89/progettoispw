package boundary;

//VERSIONE INCOMPLETA SU IMPIEGO DI REFLECTION E BOUNDARY CON INTERFACCE GRAFICHE, COMPRENSIVE DI COMBOBOX.


	import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//	import java.io.File;
//	import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import java.text.ParseException;
//import java.util.*;

//my -----------
import util.*;
import exception.*;
import control.*;

//COMMENTI -------------- 
//MDM - agginto metodo cleanPanel(); per la pulizia e aggiornameno del pannello


	public class ConfineAministratoreAmministrazioneProdottiErogazioneFornitura extends JFrame
	{

		//Draft
		private static final long	serialVersionUID	= 1L;
		
		
		private Controllore controlloreCorrente;
			
		//Pannelli
		public JPanel 		pannelloAdmin3 = new JPanel();
		
			
//		public JPanel 		pannello;
//		public JPanel  		pannelloSalva;
		
		//Label e JformattedTextField
		public JLabel 		labelDataScad;
		public JLabel 		labelFormatoCodiceBarre;
		public JLabel 		labeldimensione;
		public JLabel 		labeldataScadenza;
		JFormattedTextField	campoDataScadGiorno;
		JFormattedTextField	campoDataScadMese;
		JFormattedTextField	campoDataScadAnno;
		
		
		//Label
		public JLabel 		labelCodiceBarre;
		public JLabel       labelQuantita;
		
		public JLabel 		labelErogazioneEForniture;
		public JLabel		labelTendinaProdotti;
		//public JLabel		labelDataScadenza;
		public JLabel		labelFormatoData;
		
		// Tendine
		public JComboBox 	tendinaProdotti;
		
		
		//Bottoni
		public JButton 		bErogaLettoreBarre;
		public JButton 		bDepositaLettoreBarre;
		public JButton 		bRitiraScadutiLettoreBarre;
		
		
	/*	public JButton 		bErogaProdotti; 
		public JButton 		bDepositaProdotti;
		public JButton 		bRitiraScaduti; */
		
		//public JButton    	bResetData;
		public JButton    	bChiudiPannello;
		
		public JButton 		bGiacenzaProdotti;
		public JButton		bListaScadenze;
		
// Campi e loro etichette	
		
		public JLabel     	labelQuantitaProdotti;
	
		public JTextField	nomeCommerciale;
		public JTextField	dimensione;
		public JTextField   valoreQuantita; //NUOVA VERSIONE
		
		public JTextField	codiceBarre;
		
//		public JTextField 	valoreQuantitaProdotti; //VECCHIA VERSIONE
		public JTextField 	valoreGiacenzaProdotti;
		
		//public JTextField 	campoDataScadenzaGiorno;
		public JFormattedTextField 	codiceDataScadenzaGiorno;
		public JFormattedTextField 	codiceDataScadenzaMese;
		public JFormattedTextField 	codiceDataScadenzaAnno;
		
		public JScrollPane 	scrollListaScadenze;
		public JTextArea 	areaListaScadenze;
		
		// Ascoltatori di bottoni e relative azioni

		private GiacenzaProdottiAA 			ascoltatoreEtAzioneGiacenzaProdotti;
		private ErogaProdottiAA				ascoltatoreEtAzioneErogaProdotti;
		private DepositaProdottiAA			ascoltatoreEtAzioneDepositaProdotti;
		private RitiraScadutiAA				ascoltatoreEtAzioneRitiraScaduti;
		//private	ResetDataAA					ascoltatoreEtAzioneResetData;
		
		private ErogaLettoreBarreAA			ascoltatoreEtAzioneErogaLB;
		private DepositaLettoreBarreAA		ascoltatoreEtAzioneDepositaLB;
		private RitiraScadutiLettoreBarreAA	ascoltatoreEtAzioneRitiraScadutiLB;
				
		//Ascoltatore tendina
		private TendinaProdottiAA   		ascoltatoreEtAzioneTendinaProdotti;
		
		private BottoneListaScadenzeAA		ascoltatoreBottoneListaScadenze;

		private ChiudiPannelloAA			ascoltatoreChiudiPannello;
		
		private String 	nomeEsternoCorrente;

		int altezzaPannelloAdmin3;
		int larghezzaPannelloAdmin3;
		
		int locazioneYPrimaRiga;
		int locazioneYSecondaRiga;
		int locazioneYTerzaRiga;
		
		
				
		public ConfineAministratoreAmministrazioneProdottiErogazioneFornitura(Controllore controllore, StackFrame confineMittente, int locazioneYPannelloAdmin3) 
		{
			
			this.controlloreCorrente=controllore;
			
			nomeEsternoCorrente= (String) controlloreCorrente.keySet().toArray()[0];
/*			Allora, questa riga significa che, viene impostato in "nomeEsternoCorrente", il primo elemento
 * 			dell'hashmap! Siccome il primo elemento dell'hashmap è fatto corrispondere a quello selezionato per default sulla
 * 			tendina, basta premere il pulsante voluto, per avere una azione sul prodotto.
 * 			Più giù (alla fine del sorgente) c'è il codice relativo alla selezione di un prodotto nella tendina.
*/			
					
			int altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdmin3);
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin3.setLayout(null);
			pannelloAdmin3.setVisible(true);
			
			locazioneYPannelloAdmin3 += 5;
			altezzaPannelloAdmin3 = altezzaConfine - locazioneYPannelloAdmin3 -38;
					
			int larghezzaPannello= ConfineAvvio.Confine.getWidth();
			int lunghezzaScritta=300;
			int altezzaScrittaTitolo = 40;
			int altezzaScritta = 20;
		
			pannelloAdmin3.setBounds(0,locazioneYPannelloAdmin3,larghezzaPannello, altezzaPannelloAdmin3);
			pannelloAdmin3.setBackground(new Color(190,190,190));
			
			altezzaPannelloAdmin3 = pannelloAdmin3.getHeight(); //Questo deve stare qui (cioè dopo aver impostato il size di pannelloAdmin3)
			larghezzaPannelloAdmin3 = pannelloAdmin3.getWidth();			
			locazioneYPrimaRiga = (altezzaPannelloAdmin3)*25/100;
			locazioneYSecondaRiga =	(altezzaPannelloAdmin3)*50/100;
			locazioneYTerzaRiga =	(altezzaPannelloAdmin3)*80/100;
			//locazioneYQuartaRiga = (altezzaPannelloAdmin3)*88/100;
			
			
			//Titolo
			labelErogazioneEForniture = new JLabel("Erogazione e Forniture");
			labelErogazioneEForniture.setBounds((larghezzaPannelloAdmin3/3+30),5, lunghezzaScritta, altezzaScrittaTitolo);
			labelErogazioneEForniture.setFont(new Font("Verdana", Font.BOLD, 20));

			//Scritte e Tendine
						
			//int larghezzaBottoni = larghezzaPannelloAdmin3/6;	
			int altezzaBottoni = 40;
			//int distanzaTraBottoni = 55;
			
			//PRIMA RIGA
			//CAMPI DI TESTO che simulano il Codice A Barre
			int lunghezzaScrittalabelCodiceBarre = 150;
			int locazioneXlabelCodiceBarre = larghezzaPannelloAdmin3/40;
			labelCodiceBarre = new JLabel("Codice a Barre:");
			labelCodiceBarre.setFont(new Font("Verdana",Font.BOLD,14));
			labelCodiceBarre.setBounds(locazioneXlabelCodiceBarre,locazioneYPrimaRiga+10,lunghezzaScrittalabelCodiceBarre, altezzaScritta);
			
			
			int lunghezzaCampoTestoNomeCommerciale = 320;
			int locazioneXCampoTestoNomeCommerciale = locazioneXlabelCodiceBarre + lunghezzaScrittalabelCodiceBarre;
			codiceBarre      	 = new JTextField("", 20);
			codiceBarre.setBounds(locazioneXCampoTestoNomeCommerciale,locazioneYPrimaRiga,lunghezzaCampoTestoNomeCommerciale,altezzaBottoni);
			codiceBarre.setFont(new Font("Verdana",0,12));
			
			int locazioneXlabelNomeCommerciale =  locazioneXCampoTestoNomeCommerciale;
			int lunghezzaScrittaNomeCommerciale = 500;
			labelFormatoCodiceBarre = new JLabel("(Data Scadenza) (Nome Commerciale) (dimensione)     ");
			labelFormatoCodiceBarre.setFont(new Font("Verdana",0,11));
			labelFormatoCodiceBarre.setBounds(locazioneXlabelNomeCommerciale,locazioneYPrimaRiga-20, lunghezzaScrittaNomeCommerciale, altezzaScritta);
			
			
			/*int lunghezzaCodiceDataScadenza = 25;
			int locazioneXCodiceDataScadenzaAnno = locazioneXlabelCodiceBarre + lunghezzaScrittalabelCodiceBarre;
			try {
			codiceDataScadenzaAnno    	 = new JFormattedTextField(new MaskFormatter("####"));
			} catch (ParseException e) {e.printStackTrace();}
			codiceDataScadenzaAnno.setBounds(locazioneXCodiceDataScadenzaAnno,locazioneYPrimaRiga,lunghezzaCodiceDataScadenza*2,altezzaBottoni);
			codiceDataScadenzaAnno.setFont(new Font("Arial",0,18));
			
			int locazioneXlabelNomeCommerciale =  locazioneXCodiceDataScadenzaAnno;
			int lunghezzaScrittaNomeCommerciale = 500;
			labelFormatoCodiceBarre = new JLabel("(Data Scadenza)            (Nome Commerciale)                (dimensione)     ");
			labelFormatoCodiceBarre.setFont(new Font("Arial",0,13));
			labelFormatoCodiceBarre.setBounds(locazioneXlabelNomeCommerciale,locazioneYPrimaRiga-15, lunghezzaScrittaNomeCommerciale, altezzaScritta);
			
			
			int locazioneXCodiceDataScadenzaMese = locazioneXCodiceDataScadenzaAnno + lunghezzaCodiceDataScadenza*2;
			try {
			codiceDataScadenzaMese    	 = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			codiceDataScadenzaMese.setBounds(locazioneXCodiceDataScadenzaMese,locazioneYPrimaRiga,lunghezzaCodiceDataScadenza,altezzaBottoni);
			codiceDataScadenzaMese.setFont(new Font("Arial",0,18));
			
			
			
			int locazioneXCodiceDataScadenzaGiorno = locazioneXCodiceDataScadenzaMese + lunghezzaCodiceDataScadenza;
			try	{
			codiceDataScadenzaGiorno = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			//campoDataScadenzaGiorno = new JTextField(1);
			codiceDataScadenzaGiorno.setBounds(locazioneXCodiceDataScadenzaGiorno,locazioneYPrimaRiga,lunghezzaCodiceDataScadenza,altezzaBottoni);
			codiceDataScadenzaGiorno.setFont(new Font("Arial",0,18));
			
			
			int lunghezzaCampoTestoNomeCommerciale = 230;
			int locazioneXCampoTestoNomeCommerciale = locazioneXCodiceDataScadenzaGiorno + lunghezzaCodiceDataScadenza;
			nomeCommerciale      	 = new JTextField("", 20);
			nomeCommerciale.setBounds(locazioneXCampoTestoNomeCommerciale,locazioneYPrimaRiga,lunghezzaCampoTestoNomeCommerciale,altezzaBottoni);
			nomeCommerciale.setFont(new Font("Arial",0,18));
			
			
			
			int lunghezzaCampoTestoDimensione = 90;
			int locazioneXCampoTestoDimensione = locazioneXCampoTestoNomeCommerciale+lunghezzaCampoTestoNomeCommerciale;
			dimensione	     	 = new JTextField("", 20);
			dimensione.setBounds(locazioneXCampoTestoDimensione,locazioneYPrimaRiga,lunghezzaCampoTestoDimensione,altezzaBottoni);
			dimensione.setFont(new Font("Arial",0,18));*/
			
			
			
			/*int locazioneXbResetData = locazioneXCodiceDataScadenzaAnno + lunghezzaCodiceDataScadenza*2;
			bResetData = new JButton(".");
			bResetData.setFont(new Font("Arial",0,5));
			//bChiudiPannello.setFont(new Font("Arial", 0, 6));
			bResetData.setBounds(locazioneXbResetData+10,locazioneYPrimaRiga+10, 15, 15);
			bResetData.setBackground(Color.red);
			bResetData.setToolTipText("Reset Data");*/
			
			
			
			creaScritteTendinaCampoTesto();
			
			
			//BOTTONE E TEXTAREA   **LISTA SCADENZE**
			int locazioneXBottonelistaGiacenze = larghezzaPannelloAdmin3*3/4 -30;
			int larghezzaBottoneListaScad = 220;
			int locazioneYBottListaScad = locazioneYPrimaRiga-20;
			/*
			bListaScadenze 			= new JButton("Lista Scadenze Prodotto:");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bListaScadenze.setBounds(locazioneXBottonelistaGiacenze,locazioneYBottListaScad,larghezzaBottoneListaScad,altezzaBottoni-10);
			*/
			
	        bListaScadenze= new JButton(new ImageIcon("Save/immagini/lista.gif"));
	        bListaScadenze.setRolloverIcon(new ImageIcon("Save/immagini/listaR.gif"));
	        bListaScadenze.setBorderPainted(false);
	        bListaScadenze.setMargin (new Insets (0, 0, 0, 0));
	        bListaScadenze.setContentAreaFilled(false);
	        bListaScadenze.setOpaque(true);
	        bListaScadenze.setToolTipText("Mostra lista prodotti con scadenza");
	        bListaScadenze.setBounds(locazioneXBottonelistaGiacenze,locazioneYBottListaScad,larghezzaBottoneListaScad, 30);
			
			int altezzaMenu = (altezzaPannelloAdmin3*2)/4 +15;
			int larghezzaMenu = larghezzaBottoneListaScad;
			int locazioneXScrollListaScadenze = locazioneXBottonelistaGiacenze;
			int locazioneYScrollListaScadenze = locazioneYBottListaScad + altezzaBottoni-10;
			areaListaScadenze = new JTextArea(320, 300);
			//areaMenu.setFont(new Font("Arial", 0 , 16));
			areaListaScadenze.setFont(new Font("Arial", 1, 12));
			//areaMenu.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 15));
			areaListaScadenze.setEditable(false);
			areaListaScadenze.setLineWrap(true);
			scrollListaScadenze = new JScrollPane(areaListaScadenze);
			scrollListaScadenze.setBounds(locazioneXScrollListaScadenze, locazioneYScrollListaScadenze, larghezzaMenu, altezzaMenu);
			
			int locazioneXBottoneGiacenzaProdotto = locazioneXBottonelistaGiacenze;
			int locazioneYBottoneGiacenzaProdotto = locazioneYScrollListaScadenze + altezzaMenu;
			
			/*
			bGiacenzaProdotti 			= new JButton("Giacenza Prodotto:");
			bGiacenzaProdotti.setFont(new Font("Arial", 1, 12));
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bGiacenzaProdotti.setBounds(locazioneXBottoneGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto,larghezzaBottoneGiacenza,30);
			*/
	        bGiacenzaProdotti= new JButton(new ImageIcon("Save/immagini/giacenza30p.gif"));
	        bGiacenzaProdotti.setRolloverIcon(new ImageIcon("Save/immagini/giacenza30pR.gif"));
	        bGiacenzaProdotti.setBorderPainted(false);
	        bGiacenzaProdotti.setMargin (new Insets (0, 0, 0, 0));
	        bGiacenzaProdotti.setContentAreaFilled(false);
	        bGiacenzaProdotti.setOpaque(true);
	        bGiacenzaProdotti.setToolTipText("Mostra giacenza prodotto selezionato");
	        bGiacenzaProdotti.setBounds(locazioneXBottoneGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto, 150, 30);
	        
			int locazioneXvaloreGiacenzaProdotto = locazioneXBottoneGiacenzaProdotto + 150 +3;
			int lunghezzaCampoTesto = 68;
			valoreGiacenzaProdotti    	= new JTextField("", 10);  
			valoreGiacenzaProdotti.setBounds(locazioneXvaloreGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto,lunghezzaCampoTesto, altezzaBottoni-10);
			valoreGiacenzaProdotti.setFont(new Font("Verdana", 1, 12));
			valoreGiacenzaProdotti.setEditable(false);
			
			//BOTTONi EROGA - DEPOSITA - RITIRA SCADUTI: LETTORE A BARRE
			
			int locazioneXBottoneErogaLettore = larghezzaPannelloAdmin3/40;
						/*
			int larghezzaBottoneErogaLettore = larghezzaPannelloAdmin3/7;
						bErogaLettoreBarre 			= new JButton("Eroga LB");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bErogaLettoreBarre.setBounds(locazioneXBottoneErogaLettore,locazioneYSecondaRiga,larghezzaBottoneErogaLettore,altezzaBottoni);
			*/
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
	        bErogaLettoreBarre.setBounds(locazioneXBottoneErogaLettore,locazioneYSecondaRiga,larghezzaBottoni, altezzaBottoni);
	        
			int locazioneXBottoneDepositaLettore = locazioneXBottoneErogaLettore + larghezzaBottoni + 7;
			
			/*
			int larghezzaBottoneDepositaLettore = larghezzaPannelloAdmin3/7;
			bDepositaLettoreBarre 			= new JButton("Deposita LB");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bDepositaLettoreBarre.setBounds(locazioneXBottoneDepositaLettore,locazioneYSecondaRiga,larghezzaBottoneDepositaLettore,altezzaBottoni);
			*/
			
	        //Bottone Deposito Prodotti
	        bDepositaLettoreBarre= new JButton(new ImageIcon("Save/immagini/deposita.gif"));
	        bDepositaLettoreBarre.setRolloverIcon(new ImageIcon("Save/immagini/depositaR.gif"));
	        bDepositaLettoreBarre.setBorderPainted(false);
	        bDepositaLettoreBarre.setMargin (new Insets (0, 0, 0, 0));
	        bDepositaLettoreBarre.setContentAreaFilled(false);
	        bDepositaLettoreBarre.setOpaque(true);
	        bDepositaLettoreBarre.setToolTipText("Deposita prodotto");
	        bDepositaLettoreBarre.setBounds(locazioneXBottoneDepositaLettore,locazioneYSecondaRiga,larghezzaBottoni, altezzaBottoni);
	        
			int locazioneXBottoneRitiraLettore = locazioneXBottoneDepositaLettore + larghezzaBottoni + 7;
			/*
			int larghezzaBottoneRitiraLettore = larghezzaPannelloAdmin3/6;
			bRitiraScadutiLettoreBarre 			= new JButton("Ritira Scaduti LB");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bRitiraScadutiLettoreBarre.setBounds(locazioneXBottoneRitiraLettore,locazioneYSecondaRiga,larghezzaBottoneRitiraLettore,altezzaBottoni);
			*/
			
	        //Bottone Ritira Prodotti Scauti
	        bRitiraScadutiLettoreBarre= new JButton(new ImageIcon("Save/immagini/ritira.gif"));
	        bRitiraScadutiLettoreBarre.setRolloverIcon(new ImageIcon("Save/immagini/ritiraR.gif"));
	        bRitiraScadutiLettoreBarre.setBorderPainted(false);
	        bRitiraScadutiLettoreBarre.setMargin (new Insets (0, 0, 0, 0));
	        bRitiraScadutiLettoreBarre.setContentAreaFilled(false);
	        bRitiraScadutiLettoreBarre.setOpaque(true);
	        bRitiraScadutiLettoreBarre.setToolTipText("Ritira prodotti scaduti");
	        bRitiraScadutiLettoreBarre.setBounds(locazioneXBottoneRitiraLettore,locazioneYSecondaRiga,larghezzaBottoni, altezzaBottoni);
			
			int locazioneXlabelQuantita = locazioneXBottoneRitiraLettore + larghezzaBottoni + 40;
			int lunghezzaScrittaQuantita = 80;
			labelQuantita 	 	= new JLabel("Quantit�");
			labelQuantita.setFont(new Font("Verdana",0,12));
			labelQuantita.setForeground(Color.red);
			labelQuantita.setBounds(locazioneXlabelQuantita,locazioneYSecondaRiga-12,lunghezzaScrittaQuantita, altezzaScritta);
			labelQuantita.setHorizontalAlignment(JLabel.CENTER);
			labelQuantita.setVerticalAlignment(JLabel.CENTER);
			
			int locazioneXvaloreQuantitaProdotti = locazioneXBottoneRitiraLettore + larghezzaBottoni + 15;
			valoreQuantita    	= new JTextField("", 10);  
			valoreQuantita.setBounds(locazioneXvaloreQuantitaProdotti,locazioneYSecondaRiga+8,130, altezzaScritta);
			valoreQuantita.setFont(new Font("Verdana", Font.BOLD, 12));
			
			
			
			int lunghezzaLinea = (larghezzaPannelloAdmin3)*3/4;
			String line = "____________________________________________________________________________________________________________________________________________________";
			JLabel linea = new JLabel(line);
			linea.setBounds(0,locazioneYSecondaRiga+46, lunghezzaLinea, altezzaScritta);
			
			JLabel linea2 = new JLabel(line);
			linea2.setBounds(0,locazioneYSecondaRiga+44, lunghezzaLinea, altezzaScritta);
			
			
						
			//DATA SCADENZA
			int locazioneXlabelDataScad = larghezzaPannelloAdmin3/2 - 80;
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
			campoDataScadGiorno.setFont(new Font("Vendana",0,12));
			
			int locazioneXCampoTestoDataScadMese = locazioneXCampoTestoDataScadGiorno + lunghezzaCampoTestoDataScad;
			try {
			campoDataScadMese    	 = new JFormattedTextField(new MaskFormatter("##"));
			} catch (ParseException e) {e.printStackTrace();}
			campoDataScadMese.setBounds(locazioneXCampoTestoDataScadMese,locazioneYTerzaRiga,lunghezzaCampoTestoDataScad,altezzaScritta);
			campoDataScadMese.setFont(new Font("Vendana",0,12));
			
			int locazioneXCampoTestoDataScadAnno = locazioneXCampoTestoDataScadMese + lunghezzaCampoTestoDataScad;
			try {
			campoDataScadAnno    	 = new JFormattedTextField(new MaskFormatter("####"));
			} catch (ParseException e) {e.printStackTrace();}
			campoDataScadAnno.setBounds(locazioneXCampoTestoDataScadAnno,locazioneYTerzaRiga,lunghezzaCampoTestoDataScad*2,altezzaScritta);
			campoDataScadAnno.setFont(new Font("Vendana",0,12));
			
			int locazioneXlabelFormatoData = locazioneXCampoTestoDataScadGiorno;
			int lunghezzaScrittaFormatoData = 140;
			labelFormatoData = new JLabel("(gg | mm | aaaa)");
			labelFormatoData.setFont(new Font("Vendana",0,13));
			labelFormatoData.setBounds(locazioneXlabelFormatoData,locazioneYTerzaRiga+altezzaScritta,lunghezzaScrittaFormatoData, altezzaScritta);
			
			
			
			/*//VECCHIA VERSIONE
			//BOTTONI **EROGA** - **DEPOSITA** - **RITIRA SCADUTI**
			int locazioneXBottEroga = larghezzaPannelloAdmin3/40;
			bErogaProdotti 			= new JButton("Eroga");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bErogaProdotti.setBounds(locazioneXBottEroga,locazioneYQuartaRiga,larghezzaBottoni,altezzaBottoni-10);
			
			int locazioneXBottDeposita = locazioneXBottEroga+larghezzaBottoni+distanzaTraBottoni;
			bDepositaProdotti 			= new JButton("Deposita");		
			bDepositaProdotti.setBounds(locazioneXBottDeposita,locazioneYQuartaRiga,larghezzaBottoni,altezzaBottoni-10);
			
			int locazioneXBottRitiraScad = locazioneXBottDeposita+larghezzaBottoni+distanzaTraBottoni;
			bRitiraScaduti			= new JButton("Ritira Scaduti");		
			bRitiraScaduti.setBounds(locazioneXBottRitiraScad,locazioneYQuartaRiga,larghezzaBottoni,altezzaBottoni-10);
			*/
			
			/*int locazioneXlabelQuantitaProdotti = locazioneXBottRitiraScad + larghezzaBottoni + 70;
			labelQuantitaProdotti 	 	= new JLabel("Quantità:");
			labelQuantitaProdotti.setFont(new Font("Arial",0,18));
			labelQuantitaProdotti.setBounds(locazioneXlabelQuantitaProdotti,locazioneYQuartaRiga,lunghezzaScrittaQuantita, altezzaScritta);
			
			int locazioneXvaloreQuantitaProd = locazioneXlabelQuantitaProdotti + lunghezzaScrittaQuantita;
			valoreQuantitaProdotti    	= new JTextField("", 10);  
			valoreQuantitaProdotti.setBounds(locazioneXvaloreQuantitaProd,locazioneYQuartaRiga,100, altezzaScritta);
			valoreQuantitaProdotti.setFont(new Font("Arial", 1, 15));
			*/
			// --------------- FIne Vecchia Versione -------------------
			
			
			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");
			
			
			//Aggiungi COMPONENTI AL PANNELLO
			
			pannelloAdmin3.add(labelErogazioneEForniture);
			pannelloAdmin3.add(labelCodiceBarre);
			
			pannelloAdmin3.add(codiceBarre);
		/*	pannelloAdmin3.add(nomeCommerciale);
			pannelloAdmin3.add(dimensione);
			pannelloAdmin3.add(codiceDataScadenzaGiorno);
			pannelloAdmin3.add(codiceDataScadenzaMese);
			pannelloAdmin3.add(codiceDataScadenzaAnno);
			*/
			
			
			pannelloAdmin3.add(labelTendinaProdotti);
			pannelloAdmin3.add(tendinaProdotti);
//			pannelloAdmin3.add(labelQuantitaProdotti);
//			pannelloAdmin3.add(valoreQuantitaProdotti);
			
			pannelloAdmin3.add(bGiacenzaProdotti);
			pannelloAdmin3.add(valoreGiacenzaProdotti);
			
			
			//pannelloAdmin3.add(labelcodiceDataScadenza);
			
			//pannelloAdmin3.add(bResetData);
			
			
			
			pannelloAdmin3.add(labelDataScad);
			pannelloAdmin3.add(campoDataScadGiorno);
			pannelloAdmin3.add(campoDataScadMese);
			pannelloAdmin3.add(campoDataScadAnno);
			pannelloAdmin3.add(labelFormatoData);
			pannelloAdmin3.add(labelFormatoCodiceBarre);
			pannelloAdmin3.add(labelQuantita);
			pannelloAdmin3.add(valoreQuantita);
			
			pannelloAdmin3.add(bListaScadenze);
			pannelloAdmin3.add(scrollListaScadenze);
			
			pannelloAdmin3.add(linea);
			pannelloAdmin3.add(bErogaLettoreBarre);	
			pannelloAdmin3.add(bDepositaLettoreBarre);
			pannelloAdmin3.add(bRitiraScadutiLettoreBarre);
			
			pannelloAdmin3.add(linea2);
			
			/*pannelloAdmin3.add(bErogaProdotti);
			pannelloAdmin3.add(bDepositaProdotti);
			pannelloAdmin3.add(bRitiraScaduti);*/
			
			pannelloAdmin3.add(bChiudiPannello);
			
			
			//ASCOLTATORI
			ascoltatoreChiudiPannello 					= new ChiudiPannelloAA();
			
			//ascoltatoreEtAzioneNuovoProdotto			= new NuovoProdottoAA();
			ascoltatoreEtAzioneTendinaProdotti 			= new TendinaProdottiAA();
			ascoltatoreEtAzioneGiacenzaProdotti			= new GiacenzaProdottiAA();
			
			//Usati all'interno dei metodi Nuovi!!
			ascoltatoreEtAzioneErogaProdotti			= new ErogaProdottiAA();
			ascoltatoreEtAzioneDepositaProdotti			= new DepositaProdottiAA();
			ascoltatoreEtAzioneRitiraScaduti			= new RitiraScadutiAA();
		
			ascoltatoreEtAzioneErogaLB 				= new ErogaLettoreBarreAA();	
			ascoltatoreEtAzioneDepositaLB 			= new DepositaLettoreBarreAA();
			ascoltatoreEtAzioneRitiraScadutiLB		= new RitiraScadutiLettoreBarreAA();
			
			//ascoltatoreEtAzioneResetData			= new ResetDataAA();
			ascoltatoreBottoneListaScadenze 			= new BottoneListaScadenzeAA();	
			//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
			
			
			//ASSOCIAZIONI
			bChiudiPannello.addActionListener(ascoltatoreChiudiPannello);
				
			tendinaProdotti.addActionListener(ascoltatoreEtAzioneTendinaProdotti);
			bGiacenzaProdotti.addActionListener(ascoltatoreEtAzioneGiacenzaProdotti);
			//bResetData.addActionListener(ascoltatoreEtAzioneResetData);
			/*bErogaProdotti.addActionListener(ascoltatoreEtAzioneErogaProdotti);
			bDepositaProdotti.addActionListener(ascoltatoreEtAzioneDepositaProdotti);
			bRitiraScaduti.addActionListener(ascoltatoreEtAzioneRitiraScaduti);*/
			//bGiacenzaClassiAlim.addActionListener(ascoltatoreEtAzioneGiacenzaClasseAlim);
				
			bListaScadenze.addActionListener(ascoltatoreBottoneListaScadenze);
			bErogaLettoreBarre.addActionListener(ascoltatoreEtAzioneErogaLB);
			bDepositaLettoreBarre.addActionListener(ascoltatoreEtAzioneDepositaLB);
			bRitiraScadutiLettoreBarre.addActionListener(ascoltatoreEtAzioneRitiraScadutiLB);
		}
		// Fine costruttore
			

		private void creaScritteTendinaCampoTesto()
		{
			
			
			//int bordoTendina = 30;
			if (controlloreCorrente.keySet()==null)
				JOptionPane.showMessageDialog(null, " Il magazzino è vuoto. ");
			else 
			{
				//int bordoScritta = 30;
				//int lunghezzaScrittaQuantita = 80;
				int lunghezzaScritta = 150;
				int altezzaScritta= 20;
				int larghezzaTendina = 150;
				int altezzaTendina = 20;
				
				int locazioneXlabelTendina = larghezzaPannelloAdmin3/40;
				labelTendinaProdotti = new JLabel("Scegli il prodotto:");
				labelTendinaProdotti.setFont(new Font("Verdana",Font.BOLD,14));
				labelTendinaProdotti.setBounds(locazioneXlabelTendina,locazioneYTerzaRiga,lunghezzaScritta, altezzaScritta);
				
				//Carica la tendina di tutti i prodotti!
				tendinaProdotti = new JComboBox(controlloreCorrente.keySet().toArray());
				//imposta visibile il primo prodotto della lista, quando la tendina è chiusa
				tendinaProdotti.setSelectedIndex(0); //PRIMO NOME
				tendinaProdotti.setBounds(locazioneXlabelTendina+lunghezzaScritta,locazioneYTerzaRiga,larghezzaTendina, altezzaTendina);
								
				
				
				//quantita    	 	= new JTextField("1", 10);  
				//descrizioneLabel 	= new JLabel("Descrizione");
				//descrizione 		= new JTextField("", 10);  
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
		
		private void cleanPanel()
		{
			//Aggiorno i componenti del pannello 
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
		}
		
		private class DepositaProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("Vecchia Versione Deposita!");
				
				if (valoreQuantita.getText().equals(""))   //era vecchia versione
					JOptionPane.showMessageDialog(null, " Quantita nulla. ");

					//Modificare
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
						
					    //System.out.println("Depositate unità di "+nomeEsternoCorrente);
						
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
						JOptionPane.showMessageDialog(null, " DepositaAA  Quantità non valida. ");
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
		
		private class ErogaProdottiAA implements ActionListener
		{
			IDEsterno articolo;
			
			public void actionPerformed(ActionEvent arg0)
			{
				System.out.println("Vecchia Versione Eroga!");
				
				if (valoreQuantita.getText().equals(""))
					JOptionPane.showMessageDialog(null, " Quantita nulla. ");
				else if (nomeEsternoCorrente.equals(""))
					JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
				else
					try
					{
						articolo = new IDEsterno(nomeEsternoCorrente);					
						controlloreCorrente.eroga(articolo,Integer.parseInt(valoreQuantita.getText()));
						
						//MDM - agginto metodo per la pulizia e aggiornameno del pannello
						cleanPanel();
						
						//eroga() di ControlloreGestione!
						
						/*
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
					    */
						cleanPanel();
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA Quantità non valida. " + e.getMessage());
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
						JOptionPane.showMessageDialog(null, "\n ErogaAA Quantità negativa o nulla. " + e.getMessage());
					}
					catch (MagazzinoException e) 
					{
						JOptionPane.showMessageDialog(null,"Attenzione! "+ e.getMessage());
					}
					catch (GiacenzaInsufficienteException e)
					{
						JOptionPane.showMessageDialog(null, "\n ErogaAA Giacenza insufficiente. " + e.getMessage());
						int risp = JOptionPane.showConfirmDialog(null, "Vuoi proseguire comunque con l'erogazione?", "Attenzione!", JOptionPane.YES_NO_OPTION);
						System.out.println("Giacenza Insuff.");
						try
						{
//									if(risp==JOptionPane.YES_OPTION) 
//									{   //Se "risp" == OptionPane.YES_OPTION allora ErogaCmq, altrimenti aggiorna solo Richieste
								controlloreCorrente.erogaCmqOAggiornaRich(articolo,Integer.parseInt(valoreQuantita.getText()), risp);
								
								//MDM - agginto metodo per la pulizia e aggiornameno del pannello
								cleanPanel();
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
						JOptionPane.showMessageDialog(null, e.getMessage()+"Il Sistema comunque procederà all'erogazione di Prodotti non Scaduti.");
						//int risp = JOptionPane.showConfirmDialog(null, "Il Sistema cercherà Prodotti Buoni! Continuare con l'erogazione?", "Attenzione!", JOptionPane.YES_NO_OPTION);
						try
						{
//									if(risp==JOptionPane.YES_OPTION) 
//									{   //Se "risp" == OptionPane.YES_OPTION allora ErogaCmq, altrimenti aggiorna solo Richieste
								controlloreCorrente.erogaIndividuaProdottiBuoni(articolo,Integer.parseInt(valoreQuantita.getText()));

								//MDM - agginto metodo per la pulizia e aggiornameno del pannello
								cleanPanel();
//									}
//									else 
//									{
//										//Aggiorna Richieste!!
//									}
							
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
								controlloreCorrente.erogaCmqOAggiornaRich(articolo,Integer.parseInt(valoreQuantita.getText()), risp2);
								
								//MDM - agginto metodo per la pulizia e aggiornameno del pannello
								cleanPanel();
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

		private class RitiraScadutiAA implements ActionListener
		{
				public void actionPerformed(ActionEvent arg0)
				{
					
					//System.out.println("Vecchia Versione Ritira Scaduti!");
					
//					if (iDEsternoProdotto.getText().equals(""))
					if(nomeEsternoCorrente.equals(""))
						JOptionPane.showMessageDialog(null, " ID esterno prodotto deve essere non nullo. ");
					/*else if (valoreQuantitaProdotti.getText().equals(""))
						JOptionPane.showMessageDialog(null, " Quantità nulla!! ", "Errore", JOptionPane.ERROR_MESSAGE);*/
					else
					{						
						try
						{
							
							boolean data = true;
							if (campoDataScadGiorno.getText().equalsIgnoreCase("  ") || campoDataScadMese.getText().equalsIgnoreCase("  ")  || campoDataScadAnno.getText().equalsIgnoreCase("    "))
							{
								System.out.println("Non ho inserito una data");
								data = false;
							}
							
							IDEsterno articolo = new IDEsterno(nomeEsternoCorrente);
							String dataAmericana = controllaEAcquisisciDataScadenza(campoDataScadGiorno.getText(), campoDataScadMese.getText(), campoDataScadAnno.getText());
							
							if (!data)  //Se non � stata inserita una data
							{	
								if (valoreQuantita.getText().equals("")) //se non ho inserito una quantit�
								{
									int risp = JOptionPane.showConfirmDialog(null, "Ritirare tutti i prodotti Scaduti? Premere NO, se si vuole ritirare solo il gruppo con Data di scadenza minore.", "Attenzione!", JOptionPane.YES_NO_OPTION);
															
									if(risp==JOptionPane.YES_OPTION)  //risp == 0
									{
										IDEsterno tipo = new IDEsterno(nomeEsternoCorrente);
										controlloreCorrente.ritiraScaduti(tipo, risp, 0, null);   //Lo zero verra cmq ignorato
									}
									else if(risp==JOptionPane.NO_OPTION) //risp == 1
									{
										IDEsterno tipo = new IDEsterno(nomeEsternoCorrente);
										controlloreCorrente.ritiraScaduti(tipo, risp, 0, null);	//Lo zero verra cmq ignorato
									}
								}
								else	//Se non ho inserito una data, ma una quantit�  
								{
									controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), null);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantit�
								}
								
								//MDM - agginto metodo cleanPanel(); per la pulizia e aggiornameno del pannello
								cleanPanel();
								
								/*
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
							    */
							    						
							}
							else if (valoreQuantita.getText().equals(""))  //Se � stata inserita una Data e no una quantit�
							{
								throw new QuantitaException("Se hai inserito la Data di Scadenza di un lotto di prodotti � necessario inserire la quantit� da rimuovere!");
							}
							else //Se ho inserito la data e una quantita
							{
								controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), dataAmericana);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantità

								//Aggiorno i componenti
							    // Simulo la pressione dei tasti Giacenza e Lista Scadenze
								
								//MDM - agginto metodo cleanPanel(); per la pulizia e aggiornameno del pannello
								cleanPanel();
								
								/*
								GiacenzaProdottiAA aggiornoVQuantita = new GiacenzaProdottiAA();
								aggiornoVQuantita.actionPerformed(null);
								BottoneListaScadenzeAA aggiornoListaScadenze = new BottoneListaScadenzeAA();
								aggiornoListaScadenze.actionPerformed(null);

								campoDataScadGiorno.setText("");
								campoDataScadMese.setText("");
								campoDataScadAnno.setText("");
								valoreQuantita.setText("");
							    valoreQuantita.requestFocus();
							    */

							}
						}
						catch (NumberFormatException e)
						{
							JOptionPane.showMessageDialog(null, " Quantit� non valida. ", "Errore", JOptionPane.ERROR_MESSAGE);
						}
						catch(DirittiException e)
						{
							JOptionPane.showMessageDialog(null, " RitiraScadutiAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
						}				
						catch(ProdottoException e)
						{
							JOptionPane.showMessageDialog(null, " RitiraScadutiAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}				
						catch (IDEsternoException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}		
						catch (QuantitaException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}		
						catch (GiacenzaInsufficienteException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						} 
						catch (ScadutiNonPresentiException e) 
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Attenzione", JOptionPane.ERROR_MESSAGE);
						} 
						catch (DataNonTrovataException e) 
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
							
						}
						catch (DataNonCorrettaException e) 
						{
							JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						} 
					}
				}
		}
		
		public class TendinaProdottiAA implements ActionListener 
		{ 
			    public void actionPerformed(ActionEvent e) 
			    {
			        JComboBox cb = (JComboBox)e.getSource();
			        nomeEsternoCorrente = (String)cb.getSelectedItem();
			        //System.out.println("Il nomeEsterno ora e': "+nomeEsternoCorrente);
			        //azzero Giacenza, Lista Scadenze e Quantit�
					areaListaScadenze.setText("");
					valoreGiacenzaProdotti.setText("");
					campoDataScadGiorno.setText("");
					campoDataScadMese.setText("");
					campoDataScadAnno.setText("");
					valoreQuantita.setText("");
			    }
			}	
	
		private class ChiudiPannelloAA implements ActionListener
		{
				public void actionPerformed(ActionEvent arg0)
				{
					ConfineAministratoreAmministrazioneProdotti.bRinnovoProdotti.setEnabled(true);
					ConfineAministratoreAmministrazioneProdotti.bErogazioneFornitura.setEnabled(true);
					ConfineAministratoreAmministrazioneProdotti.bChiudiPannello.setEnabled(true);
					ConfineAministratoreAmministrazione.file.setEnabled(true);
					pannelloAdmin3.setVisible(false);
					ConfineAvvio.Confine.remove(pannelloAdmin3);
				}
		}
		
		
		private class ErogaLettoreBarreAA implements ActionListener
		{
				public void actionPerformed(ActionEvent arg0)
				{
					try 
					{
						//SE NON C'E' IL CODICE A BARRE
						//if(nomeCommerciale.getText().equals("") && dimensione.getText().equals("") && codiceDataScadenzaGiorno.getText().equalsIgnoreCase("  ") && codiceDataScadenzaMese.getText().equalsIgnoreCase("  ")  && codiceDataScadenzaAnno.getText().equalsIgnoreCase("    "))
						if(codiceBarre.getText().equals(""))
						{
							System.out.println("Codice A Barre Non Presente");
							JOptionPane.showMessageDialog(null,"Codice A Barre Non presente. Verranno presi i dati inviati da Tastiera", "Informazione", JOptionPane.WARNING_MESSAGE);
							
							ascoltatoreEtAzioneErogaProdotti.actionPerformed(null);
						}
				
						else  //SE C'E' IL CODICE A BARRE
						{	
							int daErogare;
							if (valoreQuantita.getText().equals(""))
							{
								daErogare = 1;
							}
							else
							{
								daErogare = Integer.parseInt(valoreQuantita.getText());
							}
							
							LettoreABarre lab = new LettoreABarre();
							//Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText(), codiceDataScadenzaGiorno.getText(), codiceDataScadenzaMese.getText(), codiceDataScadenzaAnno.getText());
							
							String codice = lab.read(codiceBarre.getText());
							Decodifica dec = new Decodifica(codice);
							String idEst = dec.getId();
							IDEsterno articolo = new IDEsterno(idEst);
							String dataAm = dec.getData();
							
							/*LettoreABarre lab = new LettoreABarre();
							Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText());
							IDEsterno articolo = new IDEsterno(d.getIDEsterno());*/
							
							controlloreCorrente.erogaLB(articolo, daErogare, dataAm);
						}
					} 
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (MagazzinoException e) 
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
					}
					
				}
		}
		
		private class DepositaLettoreBarreAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{					
					try 
					{	
						//SE NON C'E' IL CODICE A BARRE
						//if(nomeCommerciale.getText().equals("") && dimensione.getText().equals("") && codiceDataScadenzaGiorno.getText().equalsIgnoreCase("  ") && codiceDataScadenzaMese.getText().equalsIgnoreCase("  ")  && codiceDataScadenzaAnno.getText().equalsIgnoreCase("    "))
						if(codiceBarre.getText().equals(""))
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
								//Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText(), codiceDataScadenzaGiorno.getText(), codiceDataScadenzaMese.getText(), codiceDataScadenzaAnno.getText());
								
								String codice = lab.read(codiceBarre.getText());
								Decodifica dec = new Decodifica(codice);
								String idEst = dec.getId();
								IDEsterno articolo = new IDEsterno(idEst);
								String dataAm = dec.getData();
								
								//IDEsterno articolo = new IDEsterno(d.getIDEsterno());
								//String dataAmericana = controllaEAcquisisciDataScadenza(codiceDataScadenzaGiorno.getText(), codiceDataScadenzaMese.getText(), codiceDataScadenzaAnno.getText());
								//System.out.println("Data Americana: "+dataAmericana);
								
								//controlloreCorrente.deposita(articolo,Integer.parseInt(valoreQuantita.getText()),Integer.parseInt(dataAmericana));
								
								System.out.println("idEst: "+idEst);
								System.out.println("dataAm: "+dataAm);
								controlloreCorrente.deposita(articolo,Integer.parseInt(valoreQuantita.getText()),Integer.parseInt(dataAm));
								
								
								Tone.sound(550,150);
								Tone.sound(450,250);
								
								//MDM - agginto metodo cleanPanel(); per la pulizia e aggiornameno del pannello
								cleanPanel();
								/*
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
							    */
							}
						}
					} 
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  Data o Nome del prodotto nel Codice Barre non validi. ");
					}
					catch(ProdottoException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}				
					catch (QuantitaException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA  Quantità non valida. ");
					}
					catch(DirittiException e)
					{
						JOptionPane.showMessageDialog(null, " DepositaAA " + e.getMessage(), "non autorizzato", JOptionPane.ERROR_MESSAGE);
					}
					/*catch (DataNonCorrettaException e) 
					{
						JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} */
					catch (LineUnavailableException e) 
					{
						JOptionPane.showMessageDialog(null,"Problema Audio Beep Lettore a Barre", "Errore", JOptionPane.ERROR_MESSAGE);
					}
			}
		}
		
		
		private class RitiraScadutiLettoreBarreAA implements ActionListener
		{
				public void actionPerformed(ActionEvent arg0)
				{				
					try 
					{	
						//SE NON C'E' IL CODICE A BARRE
						//if(nomeCommerciale.getText().equals("") && dimensione.getText().equals("") && codiceDataScadenzaGiorno.getText().equalsIgnoreCase("  ") && codiceDataScadenzaMese.getText().equalsIgnoreCase("  ")  && codiceDataScadenzaAnno.getText().equalsIgnoreCase("    "))
						if(codiceBarre.getText().equals(""))
						{
							System.out.println("Codice A Barre Non Presente");
							JOptionPane.showMessageDialog(null,"Codice A Barre Non presente. Verranno presi i dati inviati da Tastiera", "Informazione", JOptionPane.WARNING_MESSAGE);
							
							ascoltatoreEtAzioneRitiraScaduti.actionPerformed(null);
							
						}
						else  //SE C'E' IL CODICE A BARRE
						{
							/*System.out.println("C'è il Codice Barre");
							boolean data = true;
							if (codiceDataScadenzaGiorno.getText().equalsIgnoreCase("  ") || codiceDataScadenzaMese.getText().equalsIgnoreCase("  ")  || codiceDataScadenzaAnno.getText().equalsIgnoreCase("    "))
							{
								System.out.println("Data non Presente nel Codice A Barre");
								data = false;
							}
							
							LettoreABarre lab = new LettoreABarre();
							Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText());
							IDEsterno articolo = new IDEsterno(d.getIDEsterno());
							String dataAmericana = controllaEAcquisisciDataScadenza(codiceDataScadenzaGiorno.getText(), codiceDataScadenzaMese.getText(), codiceDataScadenzaAnno.getText());
							System.out.println("Data Americana: "+dataAmericana);*/
							
							//if (!data)  //Se nel codice a barre non c'è una data
							//{	
							
							LettoreABarre lab = new LettoreABarre();
							//Decodifica d = lab.read(nomeCommerciale.getText(), dimensione.getText(), codiceDataScadenzaGiorno.getText(), codiceDataScadenzaMese.getText(), codiceDataScadenzaAnno.getText());
							
							String codice = lab.read(codiceBarre.getText());
							Decodifica dec = new Decodifica(codice);
							String idEst = dec.getId();
							IDEsterno articolo = new IDEsterno(idEst);
							String dataAm = dec.getData();
							
							System.out.println("Devo Ritirare in base alla data!");
							controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), dataAm);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantità
							//Aggiorno i componenti
							valoreGiacenzaProdotti.setText("");
							valoreQuantita.setText("");
						    valoreQuantita.requestFocus();
						    Tone.sound(400,500);
						}
								/*if (valoreQuantita.getText().equals(""))
								{
									int risp = JOptionPane.showConfirmDialog(null, "Ritirare tutti i prodotti Scaduti? Premere NO, se si vuole ritirare solo il gruppo con Data di scadenza minore.", "Attenzione!", JOptionPane.YES_NO_OPTION);
															
									if(risp==JOptionPane.YES_OPTION)  //risp == 0
									{
										controlloreCorrente.ritiraScaduti(articolo, risp, 0, null); //Lo zero (quantita) verrà cmq ignorato
									}
									else if(risp==JOptionPane.NO_OPTION) //risp == 1
									{
										controlloreCorrente.ritiraScaduti(articolo, risp, 0, null); //Lo zero (quantita) verrà cmq ignorato
									}
									//Aggiorno i componenti
									valoreGiacenzaProdotti.setText("");
									valoreQuantita.setText("");
								    valoreQuantita.requestFocus();
								    Tone.sound(400,500);
								}
								else
								{
									controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), null);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantità
									//Aggiorno i componenti
									valoreGiacenzaProdotti.setText("");
									valoreQuantita.setText("");
								    valoreQuantita.requestFocus();
								    Tone.sound(400,500);
								}*/
								
							
							/*else if (valoreQuantita.getText().equals(""))  //Se è stata inserita una Data e no una quantita
							{
								throw new QuantitaException("Se nel Codice A Barre è presente la Data di Scadenza è necessario inserire la quantità da rimuovere!");
							}*/
							//else  //Se ho inserito la data e una quantita
							
						//}
							    
								    
					}
					catch (NumberFormatException e)
					{
						JOptionPane.showMessageDialog(null, " Quantità non valida. ", "Errore", JOptionPane.ERROR_MESSAGE);
					}
					catch(DirittiException e)
					{
						JOptionPane.showMessageDialog(null, " RitiraScadutiAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
					}				
					catch(ProdottoException e)
					{
						JOptionPane.showMessageDialog(null, " RitiraScadutiAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}				
					catch (IDEsternoException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}		
					catch (QuantitaException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					}		
					catch (GiacenzaInsufficienteException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} 
					catch (ScadutiNonPresentiException e) 
					{
						JOptionPane.showMessageDialog(null, e.getMessage(), "Attenzione", JOptionPane.ERROR_MESSAGE);
					}
					/*catch (DataNonCorrettaException e) 
					{
						JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
					} */
					catch (LineUnavailableException e) 
					{
						JOptionPane.showMessageDialog(null,"Problema Audio Beep Lettore a Barre", "Errore", JOptionPane.ERROR_MESSAGE);
					} 
						catch (DataNonTrovataException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage()+ "Il Codice A Barre del prodotto ha una Data di Scadenza non presente tra i lotti in magazzino", "Errore", JOptionPane.ERROR_MESSAGE);	
					}
				
				}
		}
		
		
		
		private class BottoneListaScadenzeAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				/*int a = 5;
				int b = 4;
				int c = 4;
				
				b+=a; //9
				c=+a; //9? NOO! c=5!
				System.out.println("Numero b: "+b);
				System.out.println("Numero c: "+c);*/
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
		
}
	

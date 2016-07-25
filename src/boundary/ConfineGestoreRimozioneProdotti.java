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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//	import java.util.*;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

//my -----------
import util.*;
import exception.*;
import control.*;

public class ConfineGestoreRimozioneProdotti 
{
	private Controllore controlloreCorrente;
	
	public JPanel 		pannelloGestore3 = new JPanel();

//	public JPanel  		pannelloSalva;
	
	public JLabel 		labelRimuoviProdotti;
	public JLabel		labelTendinaProdotti;
	public JLabel 		labelTendinaClassiAlimFin;
	
	
	public JLabel 		labelCodiceBarre;
	public JLabel       labelQuantita;
	
	public JLabel		labelDataScadenza;
	public JLabel		labelFormatoData;
	
	public JTextField	nomeCommerciale;
	public JTextField	dimensione;
	public JTextField   valoreQuantita; //NUOVA VERSIONE
	
	
	public JLabel 		labelDataScad;
	public JLabel 		labelFormatoCodiceBarre;
	public JLabel 		labeldimensione;
	public JLabel 		labeldataScadenza;
	JFormattedTextField	campoDataScadGiorno;
	JFormattedTextField	campoDataScadMese;
	JFormattedTextField	campoDataScadAnno;
	
	
	//public JTextField 	campoDataScadenzaGiorno;
	public JFormattedTextField 	codiceDataScadenzaGiorno;
	public JFormattedTextField 	codiceDataScadenzaMese;
	public JFormattedTextField 	codiceDataScadenzaAnno;
	
	public JTextField 	valoreGiacenzaProdotti;
	public JTextField 	valoreGiacenzaClasseAlim;
	
	// Tendine
	//public JComboBox tendinaClassiAlimentariFinali; //Per ora non usato
	public JComboBox tendinaProdotti;
	

	public JButton 		bGiacenzaProdotto;
	public JButton		bListaScadenze;
	public JButton 		bEliminaProdotto;
	public JButton 		bRitiraScadutiLettoreBarre;
	
	public JScrollPane 	scrollListaScadenze;
	public JTextArea 	areaListaScadenze;
	
	
	public JButton 		bGiacenzaClassiAlim; //Credo
	public JButton 		bEliminaClasseAlim;	 //Credo
	
	//public JButton 		bGiacenza; //Solo se zero, il prodotto e' eliminabile
	//public JButton 		bRitorna; 
	//public JButton 		bSospendi; 	
	
	public JButton    	bChiudiPannello;
	
	private EliminaProdottoAA 		ascoltatoreEtAzioneEliminaProdotto;
	private GiacenzaProdottiAA 		ascoltatoreEtAzioneGiacenzaProdotti;
	private BottoneListaScadenzeAA		ascoltatoreBottoneListaScadenze;
	private RitiraScadutiAA				ascoltatoreEtAzioneRitiraScaduti;
	private RitiraScadutiLettoreBarreAA	ascoltatoreEtAzioneRitiraScadutiLB;
	
	
	private ChiudiPannelloAA			ascoltatoreChiudiPannello;
	
	//Ascoltatore tendina
	private TendinaClassiAlimentariFinaliCorrentiAA   	ascoltatoreEtAzioneTendinaClassiAlimentari;
	private TendinaNomiEsterniAA   						ascoltatoreEtAzioneTendinaNomiEsterni;

	//Per ora classeAlimentareFinaleCorrente non e' usata. Potra' servire come preselettore di nomi esterni
    private String 	classeAlimentareFinaleCorrente;
	private String 	nomeEsternoCorrente;

	int altezzaPannelloGestore3;
	int larghezzaPannelloGestore3;
	
	int locazioneYPrimaRiga;
	int locazioneYSecondaRiga;
	int locazioneYTerzaRiga;
	
	int locazioneYPannelloGestore3;
	
	public ConfineGestoreRimozioneProdotti (Controllore controllore, int locazioneYPannelloGestore3)
	{
		this.locazioneYPannelloGestore3 = locazioneYPannelloGestore3;
		this.controlloreCorrente=controllore;
		classeAlimentareFinaleCorrente=CostantiClassiAlimentari.ClasseAlimentareFinale[0]; //ALTRO
		nomeEsternoCorrente= (String) controlloreCorrente.keySet().toArray()[0];
		
		int altezzaConfine = ConfineAvvio.Confine.getHeight();
		
		ConfineAvvio.Confine.add(pannelloGestore3);
		ConfineAvvio.Confine.setLayout(null);
		pannelloGestore3.setLayout(null);
		pannelloGestore3.setVisible(true);
		
		locazioneYPannelloGestore3 += 5;
		altezzaPannelloGestore3 = altezzaConfine - locazioneYPannelloGestore3 -38;
		larghezzaPannelloGestore3= ConfineAvvio.Confine.getWidth();
		
		int lunghezzaScritta=300;
		int altezzaScritta = 20;
		
		pannelloGestore3.setBounds(0,locazioneYPannelloGestore3,ConfineAvvio.Confine.getWidth(), altezzaPannelloGestore3);
		pannelloGestore3.setBackground(new Color(190,190,190));
		
		altezzaPannelloGestore3 = pannelloGestore3.getHeight(); //Questo deve stare qui (cioè dopo aver impostato il size di pannelloAdmin4)

		locazioneYPrimaRiga = (altezzaPannelloGestore3)*25/100;
		locazioneYSecondaRiga =	(altezzaPannelloGestore3)*50/100;
		locazioneYTerzaRiga =	(altezzaPannelloGestore3)*80/100;
		
		//Titolo
		labelRimuoviProdotti = new JLabel("Rimozione Prodotti");
		labelRimuoviProdotti.setBounds((pannelloGestore3.getWidth()/3)+50,10, lunghezzaScritta, altezzaScritta);
		labelRimuoviProdotti.setFont(new Font("Verdana", Font.BOLD, 20));

		int larghezzaBottoni = 175;	
		int altezzaBottoni = 40;
		
		//PRIMA RIGA
		int lunghezzaScrittalabelCodiceBarre = 150;
		int locazioneXlabelCodiceBarre = larghezzaPannelloGestore3/40;
		labelCodiceBarre = new JLabel("Codice a Barre:");
		labelCodiceBarre.setFont(new Font("Verdana",Font.BOLD,14));
		labelCodiceBarre.setBounds(locazioneXlabelCodiceBarre,locazioneYPrimaRiga+10,lunghezzaScrittalabelCodiceBarre, altezzaScritta);
		
		int lunghezzaCampoTestoNomeCommerciale = 230;
		int locazioneXCampoTestoNomeCommerciale = locazioneXlabelCodiceBarre+lunghezzaScrittalabelCodiceBarre;
		nomeCommerciale      	 = new JTextField("", 20);
		nomeCommerciale.setBounds(locazioneXCampoTestoNomeCommerciale,locazioneYPrimaRiga,lunghezzaCampoTestoNomeCommerciale,altezzaBottoni);
		nomeCommerciale.setFont(new Font("Verdana",0,12));
		
		int locazioneXlabelNomeCommerciale =  locazioneXCampoTestoNomeCommerciale;
		int lunghezzaScrittaNomeCommerciale = 500;
		labelFormatoCodiceBarre = new JLabel("             Nome Commerciale                Dimensione    Data Scadenza");
		labelFormatoCodiceBarre.setFont(new Font("Verdana",0,12));
		labelFormatoCodiceBarre.setBounds(locazioneXlabelNomeCommerciale,locazioneYPrimaRiga-15, lunghezzaScrittaNomeCommerciale, altezzaScritta);
		
		
		int lunghezzaCampoTestoDimensione = 90;
		int locazioneXCampoTestoDimensione = locazioneXCampoTestoNomeCommerciale+lunghezzaCampoTestoNomeCommerciale;
		dimensione	     	 = new JTextField("", 20);
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
		int locazioneXBottonelistaGiacenze = larghezzaPannelloGestore3*3/4 -30;
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
		
        
		int altezzaMenu = (altezzaPannelloGestore3*2)/4 +15;
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
		int larghezzaBottoneGiacenza = larghezzaPannelloGestore3/6;
        bGiacenzaProdotto= new JButton(new ImageIcon("Save/immagini/giacenza30p.gif"));
        bGiacenzaProdotto.setRolloverIcon(new ImageIcon("Save/immagini/giacenza30pR.gif"));
        bGiacenzaProdotto.setBorderPainted(false);
        bGiacenzaProdotto.setMargin (new Insets (0, 0, 0, 0));
        bGiacenzaProdotto.setContentAreaFilled(false);
        bGiacenzaProdotto.setOpaque(true);
        bGiacenzaProdotto.setToolTipText("Mostra giacenza prodotto selezionato");
        bGiacenzaProdotto.setBounds(locazioneXBottoneGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto, 150, 30);
        
		int locazioneXvaloreGiacenzaProdotto = locazioneXBottoneGiacenzaProdotto + larghezzaBottoneGiacenza;
		int lunghezzaCampoTesto = 70;
		valoreGiacenzaProdotti    	= new JTextField("", 10);  
		valoreGiacenzaProdotti.setBounds(locazioneXvaloreGiacenzaProdotto,locazioneYBottoneGiacenzaProdotto,lunghezzaCampoTesto, altezzaBottoni-10);
		valoreGiacenzaProdotti.setFont(new Font("Verdana", 1, 15));
		valoreGiacenzaProdotti.setEditable(false);
		valoreGiacenzaProdotti.setHorizontalAlignment(JLabel.CENTER);
		
			
		//BOTTONI RITIRA SCADUTI: LETTORE A BARRE
		
		
		int locazioneXlabelQuantita = larghezzaPannelloGestore3/40;
		int lunghezzaScrittaQuantita = 80;
		labelQuantita 	 	= new JLabel("Quantit�:");
		labelQuantita.setBounds(locazioneXlabelQuantita,locazioneYSecondaRiga,lunghezzaScrittaQuantita, altezzaScritta);
		labelQuantita.setFont(new Font("Verdana",Font.BOLD,14));
		labelQuantita.setForeground(Color.red);
		labelQuantita.setHorizontalAlignment(JLabel.CENTER);
		labelQuantita.setVerticalAlignment(JLabel.CENTER);
		
		
		int locazioneXvaloreQuantitaProdotti = locazioneXlabelQuantita + lunghezzaScrittaQuantita;
		int lunghezzaValoreQuantita = 130;
		valoreQuantita    	= new JTextField("", 10);  
		valoreQuantita.setBounds(locazioneXvaloreQuantitaProdotti,locazioneYSecondaRiga,lunghezzaValoreQuantita, altezzaScritta);
		valoreQuantita.setFont(new Font("Vendana",1,12));
		
		int locazioneXBottoneRitiraLettore = locazioneXvaloreQuantitaProdotti + lunghezzaValoreQuantita + 42;
		larghezzaBottoni = 150;
		altezzaBottoni =30;
        //Bottone Ritira Prodotti Scaduti
		bRitiraScadutiLettoreBarre = new JButton(new ImageIcon("Save/immagini/ritira.gif"));
		bRitiraScadutiLettoreBarre.setRolloverIcon(new ImageIcon("Save/immagini/ritiraR.gif"));
		bRitiraScadutiLettoreBarre.setBorderPainted(false);
		bRitiraScadutiLettoreBarre.setMargin (new Insets (0, 0, 0, 0));
		bRitiraScadutiLettoreBarre.setContentAreaFilled(false);
		bRitiraScadutiLettoreBarre.setOpaque(true);
		bRitiraScadutiLettoreBarre.setToolTipText("Ritira prodotti Scaduti");
		bRitiraScadutiLettoreBarre.setBounds(locazioneXBottoneRitiraLettore,locazioneYSecondaRiga-5,larghezzaBottoni, altezzaBottoni);
        
		int locazioneXBottElimProd = locazioneXBottoneRitiraLettore + larghezzaBottoni + 20;
        //Bottone Elimina Prodotti
		bEliminaProdotto = new JButton(new ImageIcon("Save/immagini/elimina30p.gif"));
		bEliminaProdotto.setRolloverIcon(new ImageIcon("Save/immagini/elimina30pR.gif"));
		bEliminaProdotto.setBorderPainted(false);
		bEliminaProdotto.setMargin (new Insets (0, 0, 0, 0));
		bEliminaProdotto.setContentAreaFilled(false);
		bEliminaProdotto.setOpaque(true);
		bEliminaProdotto.setToolTipText("Elimina Prodotto");
		bEliminaProdotto.setBounds(locazioneXBottElimProd,locazioneYSecondaRiga-5,larghezzaBottoni, altezzaBottoni);
        
		
		int lunghezzaLinea = (larghezzaPannelloGestore3)*3/4;
		String line = "____________________________________________________________________________________________________________________________________________________";
		JLabel linea = new JLabel(line);
		linea.setBounds(0,locazioneYSecondaRiga+46, lunghezzaLinea, altezzaScritta);
		
		JLabel linea2 = new JLabel(line);
		linea2.setBounds(0,locazioneYSecondaRiga+44, lunghezzaLinea, altezzaScritta);
		
		
		creaScritteETendine();
		
		
		//DATA SCADENZA
		int locazioneXlabelDataScad = larghezzaPannelloGestore3/2 - 80;
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
		labelFormatoData.setFont(new Font("Vendana",0,12));
		labelFormatoData.setBounds(locazioneXlabelFormatoData,locazioneYTerzaRiga+altezzaScritta,lunghezzaScrittaFormatoData, altezzaScritta);
		
	
		/*bGiacenzaClassiAlim			= new JButton("Giacenza Classe:");		
		bGiacenzaClassiAlim.setBounds(locazioneXBottoneGiacenzaProdotto,locazioneYTerzaRiga-10,larghezzaBottoni,altezzaBottoni);
		
		valoreGiacenzaClasseAlim    	= new JTextField("", 10);  
		valoreGiacenzaClasseAlim.setBounds(locazioneXBottoneGiacenzaProdotto + larghezzaBottoni+2,locazioneYTerzaRiga,100, altezzaBottoni);
		valoreGiacenzaClasseAlim.setFont(new Font("Arial", 1, 15));
		valoreGiacenzaClasseAlim.setEditable(false);
		
		bEliminaClasseAlim 			= new JButton("Elimina Classe??");		
		bEliminaClasseAlim.setBounds(locazioneXBottoneGiacenzaProdotto+larghezzaBottoni+20+110,locazioneYTerzaRiga-10,larghezzaBottoni,altezzaBottoni);
		*/
		
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
		
		pannelloGestore3.add(labelRimuoviProdotti);

		pannelloGestore3.add(labelCodiceBarre);
		pannelloGestore3.add(nomeCommerciale);
		pannelloGestore3.add(dimensione);
		
		pannelloGestore3.add(labelFormatoCodiceBarre);
		
		pannelloGestore3.add(codiceDataScadenzaGiorno);
		pannelloGestore3.add(codiceDataScadenzaMese);
		pannelloGestore3.add(codiceDataScadenzaAnno);
		
		
		pannelloGestore3.add(labelDataScad);
		pannelloGestore3.add(campoDataScadGiorno);
		pannelloGestore3.add(campoDataScadMese);
		pannelloGestore3.add(campoDataScadAnno);
		pannelloGestore3.add(labelFormatoData);
		
		pannelloGestore3.add(labelQuantita);
		pannelloGestore3.add(valoreQuantita);
		
		
		
		pannelloGestore3.add(labelTendinaProdotti);
		
		pannelloGestore3.add(tendinaProdotti);
	//	pannelloGestore3.add(tendinaClassiAlimentariFinali);
				
		pannelloGestore3.add(bListaScadenze);
		pannelloGestore3.add(scrollListaScadenze);
		
		
		pannelloGestore3.add(bGiacenzaProdotto);
		pannelloGestore3.add(valoreGiacenzaProdotti);
		pannelloGestore3.add(bEliminaProdotto);
		pannelloGestore3.add(bRitiraScadutiLettoreBarre);
		pannelloGestore3.add(linea);
		pannelloGestore3.add(linea2);
		
		
	//	pannelloGestore3.add(bRitiraScaduti);
		
	//	pannelloGestore3.add(labelTendinaClassiAlimFin);	
	//	pannelloGestore3.add(bGiacenzaClassiAlim);
	//	pannelloGestore3.add(bEliminaClasseAlim);
		
		pannelloGestore3.add(bChiudiPannello);
		
		
		//ASCOLTATORI
		ascoltatoreChiudiPannello = new ChiudiPannelloAA();
		//ascoltatoreEtAzioneNuovoProdotto			= new NuovoProdottoAA();
		ascoltatoreEtAzioneTendinaClassiAlimentari 	= new TendinaClassiAlimentariFinaliCorrentiAA();
		ascoltatoreEtAzioneTendinaNomiEsterni 		= new TendinaNomiEsterniAA();
		ascoltatoreEtAzioneGiacenzaProdotti			= new GiacenzaProdottiAA();
		ascoltatoreBottoneListaScadenze 			= new BottoneListaScadenzeAA();	
		ascoltatoreEtAzioneRitiraScaduti			= new RitiraScadutiAA();
		ascoltatoreEtAzioneRitiraScadutiLB			= new RitiraScadutiLettoreBarreAA();
		
		//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
		ascoltatoreEtAzioneEliminaProdotto			= new EliminaProdottoAA();
		
		//ASSOCIAZIONI
		bChiudiPannello.addActionListener(ascoltatoreChiudiPannello);
		//tendinaClassiAlimentariFinali.addActionListener(ascoltatoreEtAzioneTendinaClassiAlimentari);
		tendinaProdotti.addActionListener(ascoltatoreEtAzioneTendinaNomiEsterni);
		bGiacenzaProdotto.addActionListener(ascoltatoreEtAzioneGiacenzaProdotti);
		bListaScadenze.addActionListener(ascoltatoreBottoneListaScadenze);
		
		bRitiraScadutiLettoreBarre.addActionListener(ascoltatoreEtAzioneRitiraScadutiLB);
	//	bRitiraScaduti.addActionListener(ascoltatoreEtAzioneRitiraScaduti);
		//bGiacenzaClassiAlim.addActionListener(ascoltatoreEtAzioneGiacenzaClasseAlim);
		bEliminaProdotto.addActionListener(ascoltatoreEtAzioneEliminaProdotto);
	
	}
	
	private void creaScritteETendine()
	{
		//int bordoTendina = 30;
		if (controlloreCorrente.keySet()==null)
			JOptionPane.showMessageDialog(null, " Il magazzino � vuoto. ");
		else 
		{
			//int bordoScritta = 30;
			//int lunghezzaScrittaQuantita = 80;
			int lunghezzaScritta = 150;
			int altezzaScritta= 20;
			int larghezzaTendina = 150;
			int altezzaTendina = 20;
			
			int locazioneXlabelTendina = larghezzaPannelloGestore3/40;
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
							throw new DataNonCorrettaException("L'anno inserito non � bisestile!");
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
	
	
	
	private class EliminaProdottoAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				//metodo "remove" in ControlloreAmministrazione
				controlloreCorrente.remove(new IDEsterno(nomeEsternoCorrente));	
				
				//Richiamo la classe così, si aggiorna la tendina
				pannelloGestore3.setVisible(false);
				new ConfineGestoreRimozioneProdotti(controlloreCorrente, locazioneYPannelloGestore3);
				
			}
			catch(DirittiException e)
			{
				JOptionPane.showMessageDialog(null, " EliminaProdottoAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
			}				
				catch(ProdottoException e)
			{
				JOptionPane.showMessageDialog(null, " EliminaProdottoAA " + e.getMessage(), " Errore", JOptionPane.ERROR_MESSAGE);
			}				
			catch (IDEsternoException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}
			catch (DimensioneMenuException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
			}					
			catch (GiacenzaNonNullaException e)
			{
				JOptionPane.showMessageDialog(null, e.getMessage());
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
				//valoreGiacenza.setText(controlloreCorrente.giacenza(new IDEsterno(nomeEsternoCorrente)).toString());
				
				Integer n = new Integer(controlloreCorrente.giacenza(new IDEsterno(nomeEsternoCorrente)));
				valoreGiacenzaProdotti.setText(n.toString());
				
				//String.format("%.2f%% \n", risultatoDeperibilita*100)   .toString()
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
				
				System.out.println("Vecchia Versione Ritira Scaduti!");
				
//				if (iDEsternoProdotto.getText().equals(""))
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
						
						if (!data)  //Se non è stata inserita una data
						{	
							if (valoreQuantita.getText().equals("")) //se non ho inserito una quantità
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
							else	//Se non ho inserito una data, ma una quantità  
							{
								controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), null);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantità

							}
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
						}
						else if (valoreQuantita.getText().equals(""))  //Se è stata inserita una Data e no una quantita
						{
							throw new QuantitaException("Se hai inserito la Data di Scadenza di un lotto di prodotti � necessario inserire la quantità da rimuovere!");
						}
						else //Se ho inserito la data e una quantita
						{
							controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), dataAmericana);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantità
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
						}
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
	
	private class RitiraScadutiLettoreBarreAA implements ActionListener
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
						
						ascoltatoreEtAzioneRitiraScaduti.actionPerformed(null);
						
					}
			
					else  //SE C'E' IL CODICE A BARRE
					{
						System.out.println("C'� il Codice Barre");
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
						System.out.println("Data Americana: "+dataAmericana);
						
						if (!data)  //Se nel codice a barre non c'è una data
						{	
							if (valoreQuantita.getText().equals(""))
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
							    Tone.sound(400,500);
							}
							
						}
						else if (valoreQuantita.getText().equals(""))  //Se è stata inserita una Data e no una quantita
						{
							throw new QuantitaException("Se nel Codice A Barre � presente la Data di Scadenza � necessario inserire la quantit� da rimuovere!");
						}
						else  //Se ho inserito la data e una quantita
						{
							System.out.println("Devo Ritirare in base alla data!");
							controlloreCorrente.ritiraScaduti(articolo, 2, Integer.parseInt(valoreQuantita.getText()), dataAmericana);  //Risp =2 vuol dire che ritiro quanto ho inserito in Quantità
							//Aggiorno i componenti
							valoreGiacenzaProdotti.setText("");
							valoreQuantita.setText("");
						    valoreQuantita.requestFocus();
						    Tone.sound(400,500);							    
						}
					}
						    
							    
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
				catch (DataNonCorrettaException e) 
				{
					JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
				} 
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
	
	
	
	
	private class ChiudiPannelloAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfineGestore.bAggiuntaProdotti.setEnabled(true);
				ConfineGestore.bRimozioneProdotti.setEnabled(true);
				ConfineGestore.bChiudiPannelloGestore2Gestione.setEnabled(true);
				pannelloGestore3.setVisible(false);
			}
	}
	
	
	public class TendinaClassiAlimentariFinaliCorrentiAA implements ActionListener 
	{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		        JComboBox cb = (JComboBox)e.getSource();
		        classeAlimentareFinaleCorrente = (String)cb.getSelectedItem();
		    }
	}	
	

	
	public class TendinaNomiEsterniAA implements ActionListener
	{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		        JComboBox cb = (JComboBox)e.getSource();
		        nomeEsternoCorrente = (String)cb.getSelectedItem();
		        //azzero Giacenza, Lista Scadenze e Quantit�
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


}

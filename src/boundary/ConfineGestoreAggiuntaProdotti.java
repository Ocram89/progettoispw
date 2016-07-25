package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import javax.swing.ImageIcon;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;


//my -----------
import util.*;
import exception.*;
import control.*;

//COMMENTO ----
//MDM - Aggiunto nuovo array DescrClasseAlimentareFinale per la visualizzazione testo delle classi alimentari
//MDM - inserito indice del JComboBox idClasseAlimentareFinaleCorrente per prendere la classe invece che la stringa per UI ----

public class ConfineGestoreAggiuntaProdotti 
{
	private Controllore controlloreCorrente;
	
	public JPanel 		pannelloGestore3 = new JPanel();

	public JLabel		labelAggiuntaProdotti;	
	public JLabel 		labelTendinaClassiAlimFin;
	
	//Label per inserimento data
	public JLabel		labelDataCreazione;
	public JLabel		labelFormatoData;
	public JLabel		labelNotaData;
	
	public JFormattedTextField 	campoDataCreazioneProdottoGiorno;
	public JFormattedTextField 	campoDataCreazioneProdottoMese;
	public JFormattedTextField 	campoDataCreazioneProdottoAnno;
	
	public JLabel		labelNomeNuovoProd;
	
	public JButton 		bNuovoProdotto;
    public JButton 		bChiudiPannello;
	
	public JLabel     	iDEsternoProdottoLabel;
	public JTextField 	iDEsternoProdotto; 
	
	public JLabel     	PrezzoAcquistoProdottoLabel;
	public JTextField 	PrezzoAcquistoProdotto;
	public JLabel     	PrezzoAcquistoProdottoLabelPunto;
	public JTextField 	PrezzoAcquistoProdottoCC;
	
	public JLabel     	PrezzoVenditaProdottoLabel;
	public JTextField 	PrezzoVenditaProdotto;
	public JLabel     	PrezzoVenditaProdottoLabelPunto;
	public JTextField 	PrezzoVenditaProdottoCC;
	
	public JComboBox tendinaClassiAlimentariFinali;
	
	private NuovoProdottoAA 			ascoltatoreEtAzioneNuovoProdotto;
	private ChiudiPannelloAA			ascoltatoreChiudiPannello;
	
	//Ascoltatore tendina
	private TendinaClassiAlimentariFinaliCorrentiAA   	ascoltatoreEtAzioneTendinaClassiAlimentari;

	private String 	classeAlimentareFinaleCorrente;
	//MDM - inserito indice del JComboBox per prendere la classe invece che la stringa per UI ----
	private int 	idClasseAlimentareFinaleCorrente;
	
	int locazioneYPrimaRiga;
	int locazioneYSecondaRiga;
	int locazioneYTerzaRiga;
	int altezzaPannelloGestore3;
	int larghezzaPannelloGestore3;
	int altezzaConfine;
	
	public ConfineGestoreAggiuntaProdotti (Controllore controllore, int locazioneYPannelloGestore3)
	{
		
		this.controlloreCorrente=controllore;
		classeAlimentareFinaleCorrente=CostantiClassiAlimentari.ClasseAlimentareFinale[0]; //ALTRO
		
		altezzaConfine = ConfineAvvio.Confine.getHeight();
	
		ConfineAvvio.Confine.add(pannelloGestore3);
		ConfineAvvio.Confine.setLayout(null);
		pannelloGestore3.setLayout(null);
		pannelloGestore3.setVisible(true);
		
		locazioneYPannelloGestore3 += 5;
		altezzaPannelloGestore3 = altezzaConfine - locazioneYPannelloGestore3 -38;
		
		larghezzaPannelloGestore3= ConfineAvvio.Confine.getWidth();
		
        pannelloGestore3.setBounds(0, locazioneYPannelloGestore3,larghezzaPannelloGestore3, altezzaPannelloGestore3);
        pannelloGestore3.setBackground(new Color(190,190,190));
		
		labelAggiuntaProdotti = new JLabel("Aggiunta Prodotti");
		labelAggiuntaProdotti.setBounds((pannelloGestore3.getWidth()/3)+60,10, 200, 30);
		labelAggiuntaProdotti.setFont(new Font("Verdana", Font.BOLD, 20));
		//creaScrittaETendinaClassiAlimFinali();
		
		
		//PRIMA RIGA
		locazioneYPrimaRiga =	(altezzaPannelloGestore3*2)/7;
		/*int bordoScritta = 30;
		int lunghezzaScritta = 300;
		int altezzaScritta= 20;
		int larghezzaTendina = pannelloGestore3.getWidth()/5;
		int altezzaTendina = 20;*/
		
		int locazioneXlabelTendina = 30;
		int lunghezzaScrittaTendinaClassAlim = 190;
		int altezzaScrittaClassAlim= 30;
		labelTendinaClassiAlimFin = new JLabel("Scegli la Classe Alimentare:");
		labelTendinaClassiAlimFin.setFont(new Font("Verdana",0,12));
		labelTendinaClassiAlimFin.setBounds(locazioneXlabelTendina,locazioneYPrimaRiga-10,lunghezzaScrittaTendinaClassAlim, altezzaScrittaClassAlim);
		
//		int bordoTendina = 30;
		if (controlloreCorrente.keySet()==null)
			JOptionPane.showMessageDialog(null, " Il magazzino � vuoto. ");
		else 
		{
			int locazioneXTendinaClassiAlimFin = locazioneXlabelTendina+lunghezzaScrittaTendinaClassAlim;
			int larghezzaTendina = 150;
			int altezzaTendina = 20;
			
			//MDM - Aggiunto nuovo array DescrClasseAlimentareFinale per la visualizzazione testo delle classi alimentari
			//tendinaClassiAlimentariFinali = new JComboBox(CostantiClassiAlimentari.ClasseAlimentareFinale);
			tendinaClassiAlimentariFinali = new JComboBox(CostantiClassiAlimentari.DescrClasseAlimentareFinale);
			
			
			tendinaClassiAlimentariFinali.setBounds(locazioneXTendinaClassiAlimFin,locazioneYPrimaRiga,larghezzaTendina, altezzaTendina);
			tendinaClassiAlimentariFinali.setSelectedIndex(0); //Seleziona il primo della lista dell'array ClasseAlimentareFinale, cio� "Altro" 
		}
		
		//CAMPO DATA CREAZIONE
		int locazioneXlabelDataCreaz = 380;
		int lunghezzaScrittaData = 115;
		int altezzaScrittaDataCreaz = 20;	  
		labelDataCreazione = new JLabel("Data Creazione:");
		labelDataCreazione.setFont(new Font("Verdana",0,12));
		labelDataCreazione.setBounds(locazioneXlabelDataCreaz,locazioneYPrimaRiga,lunghezzaScrittaData, altezzaScrittaDataCreaz);
		
		int lunghezzaCampoTestoDataScad = 25;
		int locazioneXCampoTestoDataScadGiorno = locazioneXlabelDataCreaz + lunghezzaScrittaData;
		try	{
		campoDataCreazioneProdottoGiorno = new JFormattedTextField(new MaskFormatter("##"));
		} catch (ParseException e) {e.printStackTrace();}
		//campoDataScadenzaGiorno = new JTextField(1);
		campoDataCreazioneProdottoGiorno.setBounds(locazioneXCampoTestoDataScadGiorno,locazioneYPrimaRiga,lunghezzaCampoTestoDataScad,altezzaScrittaDataCreaz);
		campoDataCreazioneProdottoGiorno.setFont(new Font("Verdana",Font.BOLD,12));
		
		int locazioneXCampoTestoDataScadMese = locazioneXCampoTestoDataScadGiorno + lunghezzaCampoTestoDataScad;
		try {
		campoDataCreazioneProdottoMese    	 = new JFormattedTextField(new MaskFormatter("##"));
		} catch (ParseException e) {e.printStackTrace();}
		campoDataCreazioneProdottoMese.setBounds(locazioneXCampoTestoDataScadMese,locazioneYPrimaRiga,lunghezzaCampoTestoDataScad,altezzaScrittaDataCreaz);
		campoDataCreazioneProdottoMese.setFont(new Font("Verdana",Font.BOLD,12));
		
		int locazioneXCampoTestoDataScadAnno = locazioneXCampoTestoDataScadMese + lunghezzaCampoTestoDataScad;
		try {
		campoDataCreazioneProdottoAnno    	 = new JFormattedTextField(new MaskFormatter("####"));
		} catch (ParseException e) {e.printStackTrace();}
		campoDataCreazioneProdottoAnno.setBounds(locazioneXCampoTestoDataScadAnno,locazioneYPrimaRiga,lunghezzaCampoTestoDataScad*2,altezzaScrittaDataCreaz);
		campoDataCreazioneProdottoAnno.setFont(new Font("Verdana",Font.BOLD,12));
		
		int locazioneXlabelFormatoData = locazioneXCampoTestoDataScadGiorno;
		int lunghezzaScrittaFormatoData = 140;
		labelFormatoData = new JLabel("(gg | mm | aaaa)");
		labelFormatoData.setFont(new Font("Verdana",0,11));
		labelFormatoData.setBounds(locazioneXlabelFormatoData,locazioneYPrimaRiga+altezzaScrittaDataCreaz,lunghezzaScrittaFormatoData, altezzaScrittaDataCreaz);

		int locazioneXlabelNotaData = 600;
		int lunghezzaScrittaNotaData = 270;
		int altezzaScrittaNotaData= 30;
		labelNotaData = new JLabel("(Se il campo Data � vuoto verr� selezionata la Data corrente)");
		labelNotaData.setBounds(locazioneXlabelNotaData,locazioneYPrimaRiga, lunghezzaScrittaNotaData, altezzaScrittaNotaData);
		labelNotaData.setFont(new Font("Verdana", 0, 8));
		
		
		//SECONDA RIGA
		locazioneYSecondaRiga = (altezzaPannelloGestore3*4)/7;
		
		//Inizio Nome Prodotto
		int locazioneXScrittaNuovoProd = 30;
		int lunghezzaScrittaNuovoProd = 105;
		int altezzaScrittaNuovoProd= 30;
		labelNomeNuovoProd = new JLabel("Nome Prodotto:");
		labelNomeNuovoProd.setBounds(locazioneXScrittaNuovoProd,locazioneYSecondaRiga, lunghezzaScrittaNuovoProd, altezzaScrittaNuovoProd);
		labelNomeNuovoProd.setFont(new Font("Verdana", 0, 12));
		int lunghezzaCampoTesto = 235;
		int locazioneXIDEsterno = locazioneXScrittaNuovoProd+lunghezzaScrittaNuovoProd;
		iDEsternoProdotto      	 = new JTextField("", 20);
		iDEsternoProdotto.setBounds(locazioneXIDEsterno,locazioneYSecondaRiga,lunghezzaCampoTesto,altezzaScrittaNuovoProd);
		iDEsternoProdotto.setFont(new Font("Verdana",Font.BOLD,12));
		//Fine Nome Prodotto
		
		//Inizio Prezzo Acquisto Prodotto
		int locazioneXLabelPrezzoA = 380;
		int lunghezzaScrittaPrezzoA = 135;
		int altezzaScrittaPrezzoA= 30;
		PrezzoAcquistoProdottoLabel = new JLabel("Prezzo Acquisto (�):");
		PrezzoAcquistoProdottoLabel.setBounds(locazioneXLabelPrezzoA,locazioneYSecondaRiga, lunghezzaScrittaPrezzoA, altezzaScrittaPrezzoA);
		PrezzoAcquistoProdottoLabel.setFont(new Font("Verdana", 0, 12));
		int lunghezzaPrezzoAcquistoProdotto = 55;
		int locazioneXPrezzoAcquistoProdotto = locazioneXLabelPrezzoA+lunghezzaScrittaPrezzoA;
		PrezzoAcquistoProdotto      	 = new JTextField("", 20);
		PrezzoAcquistoProdotto.setBounds(locazioneXPrezzoAcquistoProdotto,locazioneYSecondaRiga,lunghezzaPrezzoAcquistoProdotto,altezzaScrittaPrezzoA);
		PrezzoAcquistoProdotto.setFont(new Font("Verdana",Font.BOLD,12));
		int lunghezzaScrittaPrezzoAPunto = 5;
		int locazioneXPrezzoAcquistoProdottoLabelPunto = locazioneXPrezzoAcquistoProdotto+lunghezzaPrezzoAcquistoProdotto;
		PrezzoAcquistoProdottoLabelPunto = new JLabel(".");
		PrezzoAcquistoProdottoLabelPunto.setBounds(locazioneXPrezzoAcquistoProdottoLabelPunto,locazioneYSecondaRiga, lunghezzaScrittaPrezzoAPunto, altezzaScrittaPrezzoA);
		PrezzoAcquistoProdottoLabelPunto.setFont(new Font("Verdana", Font.BOLD, 12));
		int lunghezzaPrezzoAcquistoProdottoCC = 30;
		int locazioneXPrezzoAcquistoProdottoCC = locazioneXPrezzoAcquistoProdottoLabelPunto+lunghezzaScrittaPrezzoAPunto;
		PrezzoAcquistoProdottoCC      	 = new JTextField("00", 20);
		PrezzoAcquistoProdottoCC.setBounds(locazioneXPrezzoAcquistoProdottoCC,locazioneYSecondaRiga,lunghezzaPrezzoAcquistoProdottoCC,altezzaScrittaPrezzoA);
		PrezzoAcquistoProdottoCC.setFont(new Font("Verdana",Font.BOLD,12));
		//Fine Prezzo Acquisto Prodotto
		
		//Inizio Prezzo Vendita Prodotto
		int locazioneXLabelPrezzoV = 615;
		int lunghezzaScrittaPrezzoV = 135;
		int altezzaScrittaPrezzoV= 30;
		PrezzoVenditaProdottoLabel = new JLabel("Prezzo Vendita (�):");
		PrezzoVenditaProdottoLabel.setBounds(locazioneXLabelPrezzoV,locazioneYSecondaRiga, lunghezzaScrittaPrezzoV, altezzaScrittaPrezzoV);
		PrezzoVenditaProdottoLabel.setFont(new Font("Verdana", 0, 12));
		int lunghezzaPrezzoVenditaProdotto = 55;
		int locazioneXPrezzoVenditaProdotto = locazioneXLabelPrezzoV+lunghezzaScrittaPrezzoV;
		PrezzoVenditaProdotto      	 = new JTextField("", 20);
		PrezzoVenditaProdotto.setBounds(locazioneXPrezzoVenditaProdotto,locazioneYSecondaRiga,lunghezzaPrezzoVenditaProdotto,altezzaScrittaPrezzoV);
		PrezzoVenditaProdotto.setFont(new Font("Verdana",Font.BOLD,12));
		int lunghezzaScrittaPrezzoVPunto = 5;
		int locazioneXPrezzoVenditaProdottoLabelPunto = locazioneXPrezzoVenditaProdotto+lunghezzaPrezzoVenditaProdotto;
		PrezzoVenditaProdottoLabelPunto = new JLabel(".");
		PrezzoVenditaProdottoLabelPunto.setBounds(locazioneXPrezzoVenditaProdottoLabelPunto,locazioneYSecondaRiga, lunghezzaScrittaPrezzoVPunto, altezzaScrittaPrezzoV);
		PrezzoVenditaProdottoLabelPunto.setFont(new Font("Verdana", Font.BOLD, 12));
		int lunghezzaPrezzoVenditaProdottoCC = 30;
		int locazioneXPrezzoVenditaProdottoCC = locazioneXPrezzoVenditaProdottoLabelPunto+lunghezzaScrittaPrezzoVPunto;
		PrezzoVenditaProdottoCC      	 = new JTextField("00", 20);
		PrezzoVenditaProdottoCC.setBounds(locazioneXPrezzoVenditaProdottoCC,locazioneYSecondaRiga,lunghezzaPrezzoVenditaProdottoCC,altezzaScrittaPrezzoV);
		PrezzoVenditaProdottoCC.setFont(new Font("Verdana",Font.BOLD,12));
		//Fine Prezzo Vendita Prodotto
		
		//Terza RIGA - Bottone Creazione Nuovo Prodotto
		locazioneYTerzaRiga = (altezzaPannelloGestore3*6)/7 -30;
		bNuovoProdotto = new JButton(new ImageIcon("Save/immagini/bottone_nuovo_prodotto.gif"));
		//bNuovoProdotto.setBackground(new Color(180, 180, 180));
		bNuovoProdotto.setRolloverIcon(new ImageIcon("Save/immagini/bottone_nuovo_prodottoR.gif"));
		bNuovoProdotto.setBorderPainted(false);
		bNuovoProdotto.setMargin (new Insets (0, 0, 0, 0));
		bNuovoProdotto.setContentAreaFilled(false);
		bNuovoProdotto.setOpaque(true);
		bNuovoProdotto.setToolTipText("Crea Nuovo Prodotto");
		bNuovoProdotto.setBounds(325, locazioneYTerzaRiga, 250, 50);


		
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
		
		pannelloGestore3.add(iDEsternoProdotto);
		pannelloGestore3.add(labelAggiuntaProdotti);
		pannelloGestore3.add(bChiudiPannello);
		
		pannelloGestore3.add(labelTendinaClassiAlimFin);
		pannelloGestore3.add(tendinaClassiAlimentariFinali);
		
		pannelloGestore3.add(labelDataCreazione);
		pannelloGestore3.add(campoDataCreazioneProdottoGiorno);
		pannelloGestore3.add(campoDataCreazioneProdottoMese);
		pannelloGestore3.add(campoDataCreazioneProdottoAnno);
		pannelloGestore3.add(labelFormatoData);
		pannelloGestore3.add(labelNotaData);
		
		pannelloGestore3.add(labelNomeNuovoProd);
		pannelloGestore3.add(PrezzoAcquistoProdottoLabel);
		pannelloGestore3.add(PrezzoAcquistoProdotto);
		pannelloGestore3.add(PrezzoAcquistoProdottoLabelPunto);
		pannelloGestore3.add(PrezzoAcquistoProdottoCC);
		
		pannelloGestore3.add(PrezzoVenditaProdottoLabel);
		pannelloGestore3.add(PrezzoVenditaProdotto);
		pannelloGestore3.add(PrezzoVenditaProdottoLabelPunto);
		pannelloGestore3.add(PrezzoVenditaProdottoCC);
		
		pannelloGestore3.add(bNuovoProdotto);
	
		//ASCOLTATORI
		ascoltatoreChiudiPannello 					= new ChiudiPannelloAA();
		ascoltatoreEtAzioneNuovoProdotto			= new NuovoProdottoAA();
		ascoltatoreEtAzioneTendinaClassiAlimentari 	= new TendinaClassiAlimentariFinaliCorrentiAA();
		
		
		//ASSOCIAZIONI
		bChiudiPannello.addActionListener(ascoltatoreChiudiPannello);
		bNuovoProdotto.addActionListener(ascoltatoreEtAzioneNuovoProdotto);
		tendinaClassiAlimentariFinali.addActionListener(ascoltatoreEtAzioneTendinaClassiAlimentari);
		iDEsternoProdotto.addActionListener(ascoltatoreEtAzioneNuovoProdotto);
	
		
		iDEsternoProdotto.requestFocusInWindow();
	}
	
	private class ChiudiPannelloAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfineGestore.bAggiuntaProdotti.setEnabled(true);
				ConfineGestore.bRimozioneProdotti.setEnabled(true);
				ConfineGestore.bChiudiPannelloGestore2Gestione.setEnabled(true);
				pannelloGestore3.setVisible(false);
				//ConfineAvvio.Confine.remove(pannelloGestore3);
			}
	}
	
	private class NuovoProdottoAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				String CampoPrezzoAcquistoProdotto = PrezzoAcquistoProdotto.getText();
				String CampoPrezzoAcquistoProdottoCC = PrezzoAcquistoProdottoCC.getText();
				String CampoPrezzoVenditaProdotto = PrezzoVenditaProdotto.getText();
				String CampoPrezzoVenditaProdottoCC = PrezzoVenditaProdottoCC.getText();
				
				if (iDEsternoProdotto.getText().equals("") || CampoPrezzoAcquistoProdotto.isEmpty() || CampoPrezzoAcquistoProdottoCC.isEmpty() || CampoPrezzoVenditaProdotto.isEmpty() || CampoPrezzoVenditaProdottoCC.isEmpty())
				{
					JOptionPane.showMessageDialog(null, "Campo \"Nome Prodotto\" e \"Prezzo Acquisto/Vendita\" non possono essere vuoti");
				}
				else 
				{
					try
						{
							Orologio dataCreazione;
	/*						GregorianCalendar dataScadenza;
							int g=Integer.parseInt(gScadenzaText.getText())-1;
							int m=Integer.parseInt(mScadenzaText.getText())-1;
							int a=Integer.parseInt(aScadenzaText.getText());
							dataScadenza=new GregorianCalendar(g,m,a);
	*/
							
							String CampoCreazioneGiorno = campoDataCreazioneProdottoGiorno.getText();
							String CampoCreazioneMese = campoDataCreazioneProdottoMese.getText();
							String CampoCreazioneAnno = campoDataCreazioneProdottoAnno.getText();
							
							if(CampoCreazioneGiorno.equalsIgnoreCase("  ") && CampoCreazioneMese.equalsIgnoreCase("  ") && CampoCreazioneAnno.equalsIgnoreCase("    "))
							{
								dataCreazione = new Orologio(); //Crea data Corrente
							}
							else
							{
								//System.out.println("Data: "+Integer.parseInt(campoDataScadenzaGiorno.getText()) + " "+Integer.parseInt(campoDataScadenzaMese.getText()));
								int giorno = Integer.parseInt(campoDataCreazioneProdottoGiorno.getText());
								int mese = Integer.parseInt(campoDataCreazioneProdottoMese.getText());
								int anno = Integer.parseInt(campoDataCreazioneProdottoAnno.getText());
								
								
								if( (giorno<1) || (giorno>31) )	throw new DataNonCorrettaException("Il giorno deve essere un numero da 1 a 31!");
								if( (mese<1) || (mese>12) )	throw new DataNonCorrettaException("Il mese deve essere un numero da 1 a 12!");
								if((anno<2010) || (anno>2100) )	throw new DataNonCorrettaException("L'anno deve essere un numero da 2010 a 2100");
								if((mese==2 || mese==4 || mese==6 || mese==9 || mese==11) &&(giorno>30)) throw new DataNonCorrettaException("Il mese inserito ha al massimo 30 giorni");	
								if((mese==2) && (giorno>29)) throw new DataNonCorrettaException("Febbraio ha 28 giorni! 29 se l'anno � bisestile.");
								
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
								dataCreazione = new Orologio(giorno, mese, anno);
								System.out.println(giorno+mese+anno);
							}
							
							int prezzo, prezzoCC;//usata solo per avere un eccezione in caso di inserimento errato
							
							//Controllo Prezzo Acquisto
							prezzo = Integer.parseInt(PrezzoAcquistoProdotto.getText());
							prezzoCC = Integer.parseInt(PrezzoAcquistoProdottoCC.getText());
							String CampoPrezzoAcquisto = PrezzoAcquistoProdotto.getText() + "." + PrezzoAcquistoProdottoCC.getText();
							double pA = Double.parseDouble(CampoPrezzoAcquisto);
							
							//Controllo Prezzo Vendita
							prezzo = Integer.parseInt(PrezzoVenditaProdotto.getText());
							prezzoCC = Integer.parseInt(PrezzoVenditaProdottoCC.getText());
							String CampoPrezzoVendita = PrezzoVenditaProdotto.getText() + "." + PrezzoVenditaProdottoCC.getText();
							double pV = Double.parseDouble(CampoPrezzoVendita);
							
							//Creazione Nuovo Prodotto
							//controlloreCorrente.nuovoProdotto(new IDEsterno(iDEsternoProdotto.getText()), classeAlimentareFinaleCorrente, dataCreazione);
							
							//Creazione Nuovo Prodotto con Prezzo A/V
							
							//MDM - utilizzo indice JComboBox per prendere la classe alimento e non il testo della UI ---
							//controlloreCorrente.nuovoProdottoConPrezzo(new IDEsterno(iDEsternoProdotto.getText()), classeAlimentareFinaleCorrente, dataCreazione, pA, pV);
							controlloreCorrente.nuovoProdottoConPrezzo(new IDEsterno(iDEsternoProdotto.getText()), CostantiClassiAlimentari.ClasseAlimentareFinale[idClasseAlimentareFinaleCorrente], dataCreazione, pA, pV);

							System.out.println("Aggiunto: " + iDEsternoProdotto.getText() + " - prezzoA: " + CampoPrezzoAcquisto + " - prezzoV: " + CampoPrezzoVendita);
							//Messaggio di avvenuta creazione Nuovo Prodotto
							JOptionPane.showMessageDialog(null, " Nuovo Prodotto Aggiunto Correttamente ");
							
							iDEsternoProdotto.setText("");
							PrezzoAcquistoProdotto.setText("");
							PrezzoVenditaProdotto.setText("");
							PrezzoAcquistoProdottoCC.setText("00");
							PrezzoVenditaProdottoCC.setText("00");
							iDEsternoProdotto.requestFocus();	
						}
				
						catch(DirittiException e)
						{
							JOptionPane.showMessageDialog(null, " NuovoProdottoAA " + e.getMessage(), " non autorizzati", JOptionPane.ERROR_MESSAGE);
						}				
						catch(ClasseAlimentareException e)
						{
							JOptionPane.showMessageDialog(null, " NuovoProdottoAA " + e.getMessage(), " Errore di tipo", JOptionPane.ERROR_MESSAGE);
						}				
							catch(ProdottoException e)
						{
							JOptionPane.showMessageDialog(null, " NuovoProdottoAA " + e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}				
						catch (IDEsternoException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						}
						catch (NumberFormatException e) {
					         JOptionPane.showMessageDialog(null, "Formato Prezzo Acquisto o Vendita non valido");
					                       
					    }
						catch (DataNonCorrettaException e) 
						{
							JOptionPane.showMessageDialog(null,"Attenzione! Inserisci una data corretta! "+ e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
						} catch (SecurityException e) {
							
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
						
							e.printStackTrace();
						} catch (InstantiationException e) {
							
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							
							e.printStackTrace();
						}	
					}//else
			}
	}//fine nuovoProdottoAA
	
	public class TendinaClassiAlimentariFinaliCorrentiAA implements ActionListener
	{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		        JComboBox cb = (JComboBox)e.getSource();
		        classeAlimentareFinaleCorrente = (String)cb.getSelectedItem();
		        //MDM - inserito indice del JComboBox per prendere la classe invece che la stringa per UI ----
		        idClasseAlimentareFinaleCorrente = (int) cb.getSelectedIndex();
		    }
	}	
	
	
	
}

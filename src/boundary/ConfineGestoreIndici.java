package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.IOException;
import java.util.Set;

//import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//import javax.swing.JRadioButton;
import javax.swing.JTextField;

//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;

//COMMENTI ---
//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()

public class ConfineGestoreIndici extends JFrame {

	private static final long	serialVersionUID	= 1L;
	
	//Relazione con controllore
	private Controllore controlloreCorrente;
	
	//Pannelli
	public JPanel pannelloGestoreIndici = new JPanel();
			
	//Tendine
	public JComboBox tendinaCategoriaProdotto;
	public JComboBox tendinaClasseProdotto;
	public JComboBox tendinaNomeProdotto;
	public JComboBox tendinaIndice;
	public JComboBox tendinaIndice2;

	//Label
	public JLabel 		labelFiltro;
	public JLabel 		labelFiltroIndice;
	public JLabel		labelGestioneIndici;
	public JLabel		labelTCategoria;
	public JLabel		labelTClasse;
	public JLabel		labelTNome;
	public JLabel		labelTIndice;
	public JLabel		labelTIndice2;
	public JLabel		labelAreaIndice;

	//Bottoni
	public JButton 		bCalcolaIndice;
	public JButton 		bCalcolaTopIndice;
	public JButton 		bCalcolaGerarchia;
	public JButton 		bCalcolaGerarchiaVS;
	public JButton 		bChiudiPannello;
			
	//Valore Indice
	public JLabel     	labelVIndice;
	public JTextField 	textVIndice;
	public JLabel     	labelVIndiceTop;
	public JTextField 	textVIndiceTop;
	public JLabel     	labelVIndiceTop2;
	public JTextField 	textVIndiceTop2;
	
	//Area Indici Gerarchia
	public JScrollPane 	scrollIndice;
	public JTextArea 	areaIndice;
	
	// Ascoltatori di bottoni e relative azioni
	private CalcolaIndiceAA 		ascoltatoreEtCalcolaIndice;
	private CalcolaTopIndiceAA 		ascoltatoreEtCalcolaTopIndice;
	private CalcolaGerarchiaAA 		ascoltatoreEtCalcolaGerarchia;
	private CalcolaGerarchiaVSAA 	ascoltatoreEtCalcolaGerarchiaVS;
	private ChiudiPannelloAA 		ascoltatoreEtAzioneChiudiPannello;
	
	
	//Ascoltatori tendine
	private TendinaCategoriaProdottoAA   	ascoltatoreEtAzioneTendinaCategoriaProdotto;
	private TendinaClasseProdottoAA			ascoltatoreEtAzioneTendinaClasseProdotto;
	private TendinaIndice2AA				ascoltatoreEtAzioneTendinaIndice2;
	//private TendinaNomeProdottoAA   		ascoltatoreEtAzioneTendinaNomeProdotto;
	

	final public static String[] CatalogoIndici = 
		{
			"Spesa", "Ricavo" , "Guadagno", "Valore Magazzino", "Mancato Guadagno" , "Spesa Evitabile"
		};
	
	int altezzaConfine;
	int altezzaPannelloGestoreIndici;
	int larghezzaPannelloGestoreIndici;
	int locazioneYPrimaRiga;
	int locazioneYSecondaRiga;
	int locazioneYTerzaRiga;
	int locazioneYQuartaRiga;
	int locazioneYQuintaRiga;

	public ConfineGestoreIndici(Controllore controllore, int locazioneYPannelloGestoreIndici) throws IDEsternoException
	{
		
		this.controlloreCorrente=controllore;
			
		altezzaConfine = ConfineAvvio.Confine.getHeight();
		ConfineAvvio.Confine.add(pannelloGestoreIndici);
		ConfineAvvio.Confine.setLayout(null);
		pannelloGestoreIndici.setLayout(null);
		pannelloGestoreIndici.setVisible(true);
		
		locazioneYPannelloGestoreIndici += 5;
		larghezzaPannelloGestoreIndici = ConfineAvvio.Confine.getWidth();
		altezzaPannelloGestoreIndici = altezzaConfine - locazioneYPannelloGestoreIndici -38;
		pannelloGestoreIndici.setBounds(0,locazioneYPannelloGestoreIndici, larghezzaPannelloGestoreIndici, altezzaPannelloGestoreIndici);
		pannelloGestoreIndici.setBackground(new Color(190,190,190));
		
		//---------------------------------------------------------------------------------------------------------
		// TITOLO E BOTTONE CHIUSURA
		//--------------------------------------------------------------------------------------------------------------------------------
		
		//Titolo
		int lunghezzaScrittaTitolo=300;
		int altezzaScrittaTitolo = 40;
		int locazioneXTitoloGestioneIndici = (larghezzaPannelloGestoreIndici-lunghezzaScrittaTitolo)/2;
		labelGestioneIndici = new JLabel("Visualizza Indici Gestione");
		labelGestioneIndici.setBounds(locazioneXTitoloGestioneIndici,10, lunghezzaScrittaTitolo, altezzaScrittaTitolo);
		labelGestioneIndici.setFont(new Font("Verdana", Font.BOLD, 20));
		
		//"X" chiusura Pannello	
		bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
		bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
		bChiudiPannello.setBounds(827, 5, 48, 20);
		bChiudiPannello.setBorderPainted(false);
		bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
		bChiudiPannello.setContentAreaFilled(false);
		bChiudiPannello.setOpaque(true);
		bChiudiPannello.setToolTipText("Chiusura Pannello");
		//--------------------------------------------------------------------------------------------------------------------------------
		
		
		//--------------------------------------------------------------------------------------------------------------------------------
		// PRIMA RIGA - Label Tendine
		//--------------------------------------------------------------------------------------------------------------------------------		
		locazioneYPrimaRiga = 70;
		int altezzaLabelT = 20;
		int larghezzaLabelT = 150;
		int larghezzaTendina = 150;
		int altezzaTendina = 20;
		
		int locazioneXLabelTCategoria = 120;
		labelTCategoria = new JLabel("Categoria");
		labelTCategoria.setBounds(locazioneXLabelTCategoria,locazioneYPrimaRiga, larghezzaLabelT, altezzaLabelT);
		labelTCategoria.setFont(new Font("Verdana", 0, 12));
		labelTCategoria.setHorizontalAlignment(JLabel.CENTER);
		labelTCategoria.setVerticalAlignment(JLabel.CENTER);
		
		int locazioneXLabelTClasse = locazioneXLabelTCategoria + larghezzaTendina;
		labelTClasse = new JLabel("Classe");
		labelTClasse.setBounds(locazioneXLabelTClasse,locazioneYPrimaRiga, larghezzaLabelT, altezzaLabelT);
		labelTClasse.setFont(new Font("Verdana", 0, 12));
		labelTClasse.setHorizontalAlignment(JLabel.CENTER);
		labelTClasse.setVerticalAlignment(JLabel.CENTER);
	
		int locazioneXLabelTNome = locazioneXLabelTClasse + larghezzaTendina;
		labelTNome = new JLabel("Nome Prodotto");
		labelTNome.setBounds(locazioneXLabelTNome,locazioneYPrimaRiga, larghezzaLabelT, altezzaLabelT);
		labelTNome.setFont(new Font("Verdana", 0, 12));
		labelTNome.setForeground(Color.RED);
		labelTNome.setHorizontalAlignment(JLabel.CENTER);
		labelTNome.setVerticalAlignment(JLabel.CENTER);
		
		int locazioneXLabelTIndice = locazioneXLabelTNome + larghezzaTendina;
		labelTIndice = new JLabel("Indice");
		labelTIndice.setBounds(locazioneXLabelTIndice,locazioneYPrimaRiga, larghezzaLabelT, altezzaLabelT);
		labelTIndice.setFont(new Font("Verdana", 0, 12));
		labelTIndice.setForeground(Color.RED);
		labelTIndice.setHorizontalAlignment(JLabel.CENTER);
		labelTIndice.setVerticalAlignment(JLabel.CENTER);

		//--------------------------------------------------------------------------------------------------------------------------------
		
		
		//-------------------------------------------------------------------------------------------------------------------------------
		// SECONDA RIGA - Tendine Filtro
		//--------------------------------------------------------------------------------------------------------------------------------		
		
		locazioneYSecondaRiga = 95;
		
		int larghezzaLabelFiltro = 105;
		int altezzaLabelFiltro = 20;
		int locazioneXLabelFiltro = 10;
		labelFiltro = new JLabel("Filtro Prodotti: ");
		labelFiltro.setBounds(locazioneXLabelFiltro,locazioneYSecondaRiga, larghezzaLabelFiltro, altezzaLabelFiltro);
		labelFiltro.setFont(new Font("Verdana", Font.BOLD, 12));
				
		// Tendina Categoria Prodotto
		int locazioneXTendinaCategoriaProd = locazioneXLabelFiltro + larghezzaLabelFiltro ;
		//Aggiunta voce "Tutte" alla tendina
		int numOggettiArrayCategoria= CostantiClassiAlimentari.CategorieAlimentari.length;
		//System.out.println("Numero Categorie:" + numOggettiArrayCategoria);
		String [] Categorie = new String [numOggettiArrayCategoria+1];
		Categorie[0] = ("Tutte");
		//Aggiunta altre Voci
		for (int i = 0; i < CostantiClassiAlimentari.CategorieAlimentari.length; i++)
			Categorie[i+1] = CostantiClassiAlimentari.CategorieAlimentari[i];
		tendinaCategoriaProdotto = new JComboBox(Categorie);
		tendinaCategoriaProdotto.setBounds(locazioneXTendinaCategoriaProd,locazioneYSecondaRiga,larghezzaTendina, altezzaTendina);
		tendinaCategoriaProdotto.setSelectedIndex(0);

		
		// Tendina Classe Prodotto
		int locazioneXTendinaClasseProd = locazioneXTendinaCategoriaProd +  + larghezzaTendina +5 ;
		//Tendina Tutte Classi
		//Aggiunta voce "Tutte" alla tendina
		int numOggettiArrayClasse= CostantiClassiAlimentari.ClasseAlimentareFinale.length;
		//System.out.println("Numero Classi:" + numOggettiArrayClasseAlimentareFinale);
		String [] Classi = new String [numOggettiArrayClasse+1];
		Classi[0] = ("Tutte");
		//Aggiunta altre Voci
		for (int i = 0; i < numOggettiArrayClasse; i++)
			Classi[i+1] = CostantiClassiAlimentari.ClasseAlimentareFinale[i];
		tendinaClasseProdotto = new JComboBox(Classi);
		tendinaClasseProdotto.setBounds(locazioneXTendinaClasseProd,locazioneYSecondaRiga,larghezzaTendina, altezzaTendina);
		tendinaClasseProdotto.setSelectedIndex(0);
		tendinaClasseProdotto.setVisible(true);

			
		//Tendina Nome Prodotto
		int locazioneXTendinaNomiProd = locazioneXTendinaClasseProd + larghezzaTendina +5 ;
		tendinaNomeProdotto = new JComboBox(controlloreCorrente.keySet().toArray());
		tendinaNomeProdotto.setSelectedIndex(0); //PRIMO NOME
		tendinaNomeProdotto.setBounds(locazioneXTendinaNomiProd,locazioneYSecondaRiga,larghezzaTendina, altezzaTendina);
		
		//Tendina Scelta Indice
		int locazioneXTendinaIndice = locazioneXTendinaNomiProd + larghezzaTendina +5 ;
		tendinaIndice = new JComboBox(CatalogoIndici);
		tendinaIndice.setSelectedIndex(0); //PRIMO NOME
		tendinaIndice.setBounds(locazioneXTendinaIndice,locazioneYSecondaRiga,larghezzaTendina, altezzaTendina);
		
		//Bottone "Calcola Indici"
		int locazioneXBottoneIndice = locazioneXTendinaIndice  + larghezzaTendina +5 ;
		bCalcolaIndice = new JButton(new ImageIcon("Save/immagini/calcola_indice.gif"));
		bCalcolaIndice.setRolloverIcon(new ImageIcon("Save/immagini/calcola_indiceR.gif"));
		bCalcolaIndice.setBounds(locazioneXBottoneIndice, (locazioneYSecondaRiga-5), 150, 30);
		bCalcolaIndice.setBorderPainted(false);
		bCalcolaIndice.setMargin (new Insets (0, 0, 0, 0));
		bCalcolaIndice.setContentAreaFilled(false);
		bCalcolaIndice.setOpaque(true);
		bCalcolaIndice.setToolTipText("Calcola Indice Selezionato");
		
		//--------------------------------------------------------------------------------------------------------------------------------
		
		//--------------------------------------------------------------------------------------------------------------------------------
		// TERZA RIGA - Valore Indice
		//--------------------------------------------------------------------------------------------------------------------------------		
		locazioneYTerzaRiga = 140;
		int altezzalabelVIndice = 20;
		int larghezzaLabelVIndice = 100;
		
		int locazioneXLabelVIndice = 350;
		labelVIndice = new JLabel("Valore Indice:");
		labelVIndice.setBounds(locazioneXLabelVIndice,locazioneYTerzaRiga, larghezzaLabelVIndice, altezzalabelVIndice);
		labelVIndice.setFont(new Font("Verdana", Font.BOLD, 12));
		
		int altezzaTextVIndice = 20;
		int larghezzaTextVIndice = 100;
		int locazioneXTextVIndice = locazioneXLabelVIndice + larghezzaLabelVIndice + 5;
		textVIndice = new JTextField("-", 10); 
		textVIndice.setBounds(locazioneXTextVIndice,locazioneYTerzaRiga,larghezzaTextVIndice , altezzaTextVIndice);
		textVIndice.setFont(new Font("Verdana", Font.BOLD, 12));
		textVIndice.setEditable(false);
		
		//--------------------------------------------------------------------------------------------------------------------------------

		//--------------------------------------------------------------------------------------------------------------------------------
		// QUARTA RIGA - Indice TOP
		//--------------------------------------------------------------------------------------------------------------------------------		
		locazioneYQuartaRiga = 175;
		
		int locazioneXLabelTIndice2 = 115;
		labelTIndice2 = new JLabel("Indice");
		labelTIndice2.setBounds(locazioneXLabelTIndice2,locazioneYQuartaRiga, larghezzaLabelT, altezzaLabelT);
		labelTIndice2.setFont(new Font("Verdana", 0, 12));
		labelTIndice2.setForeground(Color.RED);
		labelTIndice2.setHorizontalAlignment(JLabel.CENTER);
		labelTIndice2.setVerticalAlignment(JLabel.CENTER);
	
		//--------------------------------------------------------------------------------------------------------------------------------

		//--------------------------------------------------------------------------------------------------------------------------------
		// QUINTA RIGA - Indice TOP
		//--------------------------------------------------------------------------------------------------------------------------------		
		locazioneYQuintaRiga = 200;

		int larghezzaLabelFiltroIndice = 105;
		int altezzaLabelFiltroIndice = 20;
		int locazioneXLabelFiltroIndice = 10;
		labelFiltroIndice = new JLabel("Filtro Indice: ");
		labelFiltroIndice.setBounds(locazioneXLabelFiltroIndice,locazioneYQuintaRiga, larghezzaLabelFiltroIndice, altezzaLabelFiltroIndice);
		labelFiltroIndice.setFont(new Font("Verdana", Font.BOLD, 12));
		
		//Tendina Scelta Indice2
		int locazioneXTendinaIndice2 = locazioneXLabelFiltroIndice + larghezzaLabelFiltroIndice ;
		tendinaIndice2 = new JComboBox(CatalogoIndici);
		tendinaIndice2.setSelectedIndex(0); //PRIMO NOME
		tendinaIndice2.setBounds(locazioneXTendinaIndice2,locazioneYQuintaRiga,larghezzaTendina, altezzaTendina);
		
		int altezzalabelVIndiceTop = 20;
		int larghezzaLabelVIndiceTop = 30;
		int locazioneXLabelVIndiceTop = locazioneXTendinaIndice2 + larghezzaTendina +5;
		labelVIndiceTop = new JLabel("Top:");
		labelVIndiceTop.setBounds(locazioneXLabelVIndiceTop,locazioneYQuintaRiga, larghezzaLabelVIndiceTop, altezzalabelVIndiceTop);
		labelVIndiceTop.setFont(new Font("Verdana", Font.BOLD, 12));
		
		int altezzaTextVIndiceTop = 20;
		int larghezzaTextVIndiceTop = 130;
		int locazioneXTextVIndiceTop = locazioneXLabelVIndiceTop + larghezzaLabelVIndiceTop + 5;
		textVIndiceTop = new JTextField("-", 10); 
		textVIndiceTop.setBounds(locazioneXTextVIndiceTop,locazioneYQuintaRiga,larghezzaTextVIndiceTop , altezzaTextVIndiceTop);
		textVIndiceTop.setFont(new Font("Verdana", Font.BOLD, 12));
		textVIndiceTop.setEditable(false);
		
		int altezzalabelVIndiceTop2 = 20;
		int larghezzaLabelVIndiceTop2 = 50;
		int locazioneXLabelVIndiceTop2 = locazioneXTextVIndiceTop + larghezzaTextVIndiceTop + 5;
		labelVIndiceTop2 = new JLabel("Valore:");
		labelVIndiceTop2.setBounds(locazioneXLabelVIndiceTop2,locazioneYQuintaRiga, larghezzaLabelVIndiceTop2, altezzalabelVIndiceTop2);
		labelVIndiceTop2.setFont(new Font("Verdana", Font.BOLD, 12));
		
		int altezzaTextVIndiceTop2 = 20;
		int larghezzaTextVIndiceTop2 = 80;
		int locazioneXTextVIndiceTop2 = locazioneXLabelVIndiceTop2 + larghezzaLabelVIndiceTop2 + 5;
		textVIndiceTop2 = new JTextField("-", 10); 
		textVIndiceTop2.setBounds(locazioneXTextVIndiceTop2,locazioneYQuintaRiga,larghezzaTextVIndiceTop2 , altezzaTextVIndiceTop2);
		textVIndiceTop2.setFont(new Font("Verdana", Font.BOLD, 12));
		textVIndiceTop2.setEditable(false);
		
		//Bottone "Calcola Top Indici"
		int locazioneXBottoneTopIndice = 580 ;
		bCalcolaTopIndice = new JButton(new ImageIcon("Save/immagini/calcola_top.gif"));
		bCalcolaTopIndice.setRolloverIcon(new ImageIcon("Save/immagini/calcola_topR.gif"));
		bCalcolaTopIndice.setBounds(locazioneXBottoneTopIndice, (locazioneYQuintaRiga-5), 150, 30);
		bCalcolaTopIndice.setBorderPainted(false);
		bCalcolaTopIndice.setMargin (new Insets (0, 0, 0, 0));
		bCalcolaTopIndice.setContentAreaFilled(false);
		bCalcolaTopIndice.setOpaque(true);
		bCalcolaTopIndice.setToolTipText("Calcola Top Prodotto per Indice Selezionato");
		
		//Bottone "Calcola Indici Gerarchia"
		int locazioneXBottoneGerarchia = 735 ;
		bCalcolaGerarchia = new JButton(new ImageIcon("Save/immagini/gerarchia.gif"));
		bCalcolaGerarchia.setRolloverIcon(new ImageIcon("Save/immagini/gerarchiaR.gif"));
		bCalcolaGerarchia.setBounds(locazioneXBottoneGerarchia, (locazioneYQuintaRiga-5), 150, 30);
		bCalcolaGerarchia.setBorderPainted(false);
		bCalcolaGerarchia.setMargin (new Insets (0, 0, 0, 0));
		bCalcolaGerarchia.setContentAreaFilled(false);
		bCalcolaGerarchia.setOpaque(true);
		bCalcolaGerarchia.setToolTipText("Calcola Valori Gerarchia per Indice Selezionato");
		//--------------------------------------------------------------------------------------------------------------------------------

		//--------------------------------------------------------------------------------------------------------------------------------
		// SESTA RIGA - Label Indice Gerarchia
		//--------------------------------------------------------------------------------------------------------------------------------		
		
		int locazioneYSestaRiga = 245;

		int larghezzaLabelAreaIndice = 400;
		int altezzaLabelAreaIndice = 20;
		int locazioneXLabelAreaIndice = 250;
		labelAreaIndice = new JLabel("Valori Indice per Gerarchia");
		labelAreaIndice.setBounds(locazioneXLabelAreaIndice,locazioneYSestaRiga, larghezzaLabelAreaIndice, altezzaLabelAreaIndice);
		labelAreaIndice.setFont(new Font("Verdana", Font.BOLD, 12));
		labelAreaIndice.setForeground(Color.RED);
		labelAreaIndice.setHorizontalAlignment(JLabel.CENTER);
		labelAreaIndice.setVerticalAlignment(JLabel.CENTER);
		
		//Bottone "Calcola Indici Gerarchia Variabili Statiche"
		int locazioneXBottoneGerarchiaVS = 735 ;
		bCalcolaGerarchiaVS = new JButton(new ImageIcon("Save/immagini/gerarchia_vs.gif"));
		bCalcolaGerarchiaVS.setRolloverIcon(new ImageIcon("Save/immagini/gerarchia_vsR.gif"));
		bCalcolaGerarchiaVS.setBounds(locazioneXBottoneGerarchiaVS, (locazioneYSestaRiga-10), 150, 30);
		bCalcolaGerarchiaVS.setBorderPainted(false);
		bCalcolaGerarchiaVS.setMargin (new Insets (0, 0, 0, 0));
		bCalcolaGerarchiaVS.setContentAreaFilled(false);
		bCalcolaGerarchiaVS.setOpaque(true);
		bCalcolaGerarchiaVS.setToolTipText("Calcola Valori Gerarchia per Indice Selezionato");
		
		//--------------------------------------------------------------------------------------------------------------------------------

		//--------------------------------------------------------------------------------------------------------------------------------
		// SETTIMA RIGA - Area Indice Gerarchia
		//--------------------------------------------------------------------------------------------------------------------------------		
		
		int locazioneYSettimaRiga = 270;
		
		int larghezzaAreaIndice = 850;
		int altezzaAreaIndice = 140;
		
		int locazioneXScrollAreaIndice = 25;
		areaIndice = new JTextArea(larghezzaAreaIndice, altezzaAreaIndice);
		areaIndice.setFont(new Font("Arial", 1, 12));
		areaIndice.setEditable(false);
		areaIndice.setLineWrap(true);
		scrollIndice = new JScrollPane(areaIndice);
		scrollIndice.setBounds(locazioneXScrollAreaIndice, locazioneYSettimaRiga, larghezzaAreaIndice, altezzaAreaIndice);
		
		//--------------------------------------------------------------------------------------------------------------------------------

		
		//Aggiunta Label
		pannelloGestoreIndici.add(labelGestioneIndici);
		pannelloGestoreIndici.add(labelTCategoria);
		pannelloGestoreIndici.add(labelTClasse);
		pannelloGestoreIndici.add(labelTNome);
		pannelloGestoreIndici.add(labelTIndice);
		pannelloGestoreIndici.add(labelFiltro);
		pannelloGestoreIndici.add(labelVIndice);
		pannelloGestoreIndici.add(labelTIndice2);
		pannelloGestoreIndici.add(labelFiltroIndice);
		pannelloGestoreIndici.add(labelVIndiceTop);
		pannelloGestoreIndici.add(labelVIndiceTop2);
		pannelloGestoreIndici.add(labelAreaIndice);
		
		//Aggiunta TextBox
		pannelloGestoreIndici.add(textVIndice);
		pannelloGestoreIndici.add(textVIndiceTop);
		pannelloGestoreIndici.add(textVIndiceTop2);
				
		//Aggiunta Tendine
		pannelloGestoreIndici.add(tendinaCategoriaProdotto);
		pannelloGestoreIndici.add(tendinaClasseProdotto);
		pannelloGestoreIndici.add(tendinaNomeProdotto);		
		pannelloGestoreIndici.add(tendinaIndice);	
		pannelloGestoreIndici.add(tendinaIndice2);
		
		//Aggiunta Bottoni
		pannelloGestoreIndici.add(bChiudiPannello);
		pannelloGestoreIndici.add(bCalcolaIndice);
		pannelloGestoreIndici.add(bCalcolaTopIndice);
		pannelloGestoreIndici.add(bCalcolaGerarchia);
		pannelloGestoreIndici.add(bCalcolaGerarchiaVS);
		
		//Area Indice Gerarchia
		pannelloGestoreIndici.add(scrollIndice);

		//Ascoltatori
		ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
		ascoltatoreEtCalcolaIndice = new CalcolaIndiceAA();
		ascoltatoreEtCalcolaTopIndice = new CalcolaTopIndiceAA();
		ascoltatoreEtCalcolaGerarchia = new CalcolaGerarchiaAA();
		ascoltatoreEtCalcolaGerarchiaVS = new CalcolaGerarchiaVSAA();
		
		//Associazione Bottone-Evento
		bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
		bCalcolaIndice.addActionListener(ascoltatoreEtCalcolaIndice);
		bCalcolaTopIndice.addActionListener(ascoltatoreEtCalcolaTopIndice);
		bCalcolaGerarchia.addActionListener(ascoltatoreEtCalcolaGerarchia);
		bCalcolaGerarchiaVS.addActionListener(ascoltatoreEtCalcolaGerarchiaVS);
	
		// Ascoltatori di tendine e relative azioni			
		ascoltatoreEtAzioneTendinaCategoriaProdotto = new TendinaCategoriaProdottoAA();
		ascoltatoreEtAzioneTendinaClasseProdotto 	= new TendinaClasseProdottoAA();
		ascoltatoreEtAzioneTendinaIndice2 	= new TendinaIndice2AA();

		// Associazione di tendine ad ascoltatori
		tendinaCategoriaProdotto.addActionListener(ascoltatoreEtAzioneTendinaCategoriaProdotto);
		tendinaClasseProdotto.addActionListener(ascoltatoreEtAzioneTendinaClasseProdotto);
		tendinaIndice2.addActionListener(ascoltatoreEtAzioneTendinaIndice2);
		
	}	
	
	//-----------------
	//ChiudiPannelloAA
	//-----------------
	private class ChiudiPannelloAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			
			ConfineGestore.bGestione.setEnabled(true);
			ConfineGestore.bMenu.setEnabled(true);
			ConfineGestore.bIndici.setEnabled(true);
			
			ConfineGestore.bCambiaUtente.setEnabled(true);
			ConfineGestore.bSospendi.setEnabled(true);
				
			pannelloGestoreIndici.setVisible(false);		
		}
	}

	//-----------------
	//TendinaCategoriaProdAA
	//-----------------
	private class TendinaCategoriaProdottoAA implements ActionListener 
	{ 
	    public void actionPerformed(ActionEvent e) 
	    {
	    	// resetto il "valore visualizzato" di un eventuale indice calcolato in precedenza
	    	textVIndice.setText("-");
	    	
	    	//in base alla Categoria selezionata, setta quale Tendina Classe Visualizzare
	    	String contenutoBox = (String) tendinaCategoriaProdotto.getSelectedItem();
			
			if(contenutoBox=="Primo"){
				//cancella tutti gli elementi presenti				
				tendinaClasseProdotto.removeAllItems();
				//Carica nuovi Elementi , Classe delle Categoria scelta
				for (int i = 0; i < CostantiClassiAlimentari.ClasseAlimentarePrimo.length; i++)
					{
					//System.out.print(" aggiunto: " + CostantiClassiAlimentari.ClasseAlimentarePrimo[i]);	
					tendinaClasseProdotto.addItem(CostantiClassiAlimentari.ClasseAlimentarePrimo[i]);
					}
			}	
			
			else if(contenutoBox=="Secondo"){
				tendinaClasseProdotto.removeAllItems();
				for (int i = 0; i < CostantiClassiAlimentari.ClasseAlimentareSecondo.length; i++)
					{
					tendinaClasseProdotto.addItem(CostantiClassiAlimentari.ClasseAlimentareSecondo[i]);
					}
			}
			
			else if(contenutoBox=="Dolce"){
				tendinaClasseProdotto.removeAllItems();
				for (int i = 0; i < CostantiClassiAlimentari.ClasseAlimentareDolce.length; i++)
					{
					tendinaClasseProdotto.addItem(CostantiClassiAlimentari.ClasseAlimentareDolce[i]);
					}
			}
			
			else if(contenutoBox=="Frutta"){
				tendinaClasseProdotto.removeAllItems();
				for (int i = 0; i < CostantiClassiAlimentari.ClasseAlimentareFrutta.length; i++)
					{
					tendinaClasseProdotto.addItem(CostantiClassiAlimentari.ClasseAlimentareFrutta[i]);
					}
			}
			
			else if(contenutoBox=="Bevanda"){
				tendinaClasseProdotto.removeAllItems();
				for (int i = 0; i < CostantiClassiAlimentari.ClasseAlimentareBevanda.length; i++)
					{	
					tendinaClasseProdotto.addItem(CostantiClassiAlimentari.ClasseAlimentareBevanda[i]);
					}
			}
			
			else if(contenutoBox=="Tutte"){
				tendinaClasseProdotto.removeAllItems();
				//Ricarica la tendina Orizinale, senza filtro e con l'aggiunta della voce "Tutte"
				tendinaClasseProdotto.addItem("Tutte");
				for (int i = 0; i < CostantiClassiAlimentari.ClasseAlimentareFinale.length; i++){
					tendinaClasseProdotto.addItem(CostantiClassiAlimentari.ClasseAlimentareFinale[i]);
				}	
			}

	    }
	}
	
	//-----------------
	//TendinaClasseProdAA - Bevande
	//-----------------
	private class TendinaClasseProdottoAA implements ActionListener 
	{ 
	    public void actionPerformed(ActionEvent e)
	    {
	    	// resetto il "valore visualizzato" di un eventuale indice calcolato in precedenza
	    	textVIndice.setText("-");
	    	
			String contenutoBox = (String) tendinaClasseProdotto.getSelectedItem();
			String contenutoBoxCategoria = (String) tendinaCategoriaProdotto.getSelectedItem();
			String getCategoria = "";
			//in base alla Bevanda selezionata, setta quale prodotto Visualizzare
			
				//cancella tutti gli elementi presenti
				tendinaNomeProdotto.removeAllItems();
			
				Set <String> p= controlloreCorrente.keySet();
				//Controllo che l'elenco non sia vuoto
				if (p.isEmpty())
				{
					tendinaNomeProdotto.addItem("-");
				}
				else 
				{
					int count = 0;
					for (String k : p) 
					{				
							Portata oggettoPortata;
							try {
								oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
								//Ricavo la Classe della portata
								String nomeTipoProdotto = oggettoPortata.getClass().getName();
								//Carico il nome della portata se la sua classe corrisponde a quella selezionata
								//controlla se la categoria selezionata è "Tutte"
								if (contenutoBoxCategoria=="Tutte"){
									//controlla se la categoria selezionata è "Tutte"
									if((contenutoBox=="Tutte") || (contenutoBox=="Tutti")){	
										//carico tutti i Prodotti
										tendinaNomeProdotto.addItem(k);
										count++;
									}
									else{  //Contenuto Classe divesa da "Tutte"
										if(nomeTipoProdotto == contenutoBox){
											tendinaNomeProdotto.addItem(k);
											count++;
											//System.out.print("nome: " + k);	
										}
									}
	
								}
								else{	//Contenuto Categoria divesa da "Tutte"
									if(oggettoPortata instanceof Bevanda) getCategoria =  "Bevanda";
									else if (oggettoPortata instanceof Primo) getCategoria =  "Primo";
									else if (oggettoPortata instanceof Secondo) getCategoria =  "Secondo";
									else if (oggettoPortata instanceof Dolce) getCategoria =  "Dolce";
									else if (oggettoPortata instanceof Frutta) getCategoria =  "Frutta";
									
									if((contenutoBox=="Tutti") || (contenutoBox=="Tutte")){	
										if(getCategoria == contenutoBoxCategoria){
											tendinaNomeProdotto.addItem(k);
											count++;
											//System.out.print("nome: " + k);	
										}
									}	
									else {  //Contenuto Classe divesa da "Tutte"
										if(nomeTipoProdotto == contenutoBox){
											tendinaNomeProdotto.addItem(k);
											count++;
											//System.out.print("nome: " + k);	
										}
									}
								}
							}
							catch (IDEsternoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
					} 
					if (count==0){
						tendinaNomeProdotto.addItem("-");
					}
				}					

			}
		}
	
		//-----------------
		//CalcolaIndiceAA
		//-----------------
		private class CalcolaIndiceAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String indice = "-";
				String prodottoSelezionato = (String) tendinaNomeProdotto.getSelectedItem();
				String indiceSelezionato = (String) tendinaIndice.getSelectedItem();
				//System.out.print("Ho selezionato: Prodotto: " + prodottoSelezionato + " - Indice: " + indiceSelezionato);
				
				if(prodottoSelezionato == "-"){
					JOptionPane.showMessageDialog(null,"Selezionare un Prodotto");
				}
				else{
						try {
						//Spesa
						if (indiceSelezionato == "Spesa"){
							
							indice = Double.toString(controlloreCorrente.getMappaProdotti().get(prodottoSelezionato).getSpesa());
						}
						
						//Ricavo
						else if (indiceSelezionato == "Ricavo"){	
								indice = Double.toString(controlloreCorrente.getMappaProdotti().get(prodottoSelezionato).getRicavo());
						}
						
						//Guadagno
						else if (indiceSelezionato == "Guadagno"){
								indice = Double.toString(controlloreCorrente.getMappaProdotti().get(prodottoSelezionato).getGuadagno());
						}
						
						//Valore Magazzino
						else if (indiceSelezionato == "Valore Magazzino"){
								indice = Double.toString(controlloreCorrente.getMappaProdotti().get(prodottoSelezionato).getValoreMagazzino());
						}
						
						//Mancato Guadagno
						else if (indiceSelezionato == "Mancato Guadagno"){
								indice = Double.toString(controlloreCorrente.getMappaProdotti().get(prodottoSelezionato).getMancatoGuadagno());
						}
						
						//Spesa Evitabile
						else if (indiceSelezionato == "Spesa Evitabile"){
								indice = Double.toString(controlloreCorrente.getMappaProdotti().get(prodottoSelezionato).getSpesaEvitabile());
						}
						
						//Nuovo Indice
						/*
						else if (indiceSelezionato == "Nuovo Indice"){				
						}
						*/
					} catch (IDEsternoException e) {
						e.printStackTrace();
					}
					
				}	
				
				System.out.print("\n***** Gestore - Inizio Calcolo Indice *****");
				System.out.print("\nProdotto:" + prodottoSelezionato + " - " + indiceSelezionato + ": " + indice);
				System.out.print("\n***** Gestore - Fine Calcolo Indice *****");
				//Setta l'indice restituito da uno degli IF precedenti
				textVIndice.setText(indice);	
						
			}
		}
		
		
		//-----------------
		//TendinaIndice2
		//-----------------
		private class TendinaIndice2AA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e)
		    {
		    	// resetto il "valore visualizzato" di un eventuale indice calcolato in precedenza
		    	textVIndiceTop.setText("-");
		    	textVIndiceTop2.setText("-");
		    	areaIndice.setText("");
				}
			}
		
		//-----------------
		//CalcolaTopIndiceAA
		//-----------------
		private class CalcolaTopIndiceAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String indiceSelezionato = (String) tendinaIndice2.getSelectedItem();
				double indice = 0.0;
				double indiceMax = 0.0;
				String nomeIndiceMax = "-";
				
				Set <String> p= controlloreCorrente.keySet();
				//Controllo che l'elenco non sia vuoto
				if (p.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Magazzino Vuoto");
				}
				else 
				{
					for (String k : p) 
					{				
						try {
							//Spesa
							if (indiceSelezionato == "Spesa"){	
									indice = controlloreCorrente.getMappaProdotti().get(k).getSpesa();
							}
							
							//Ricavo
							else if (indiceSelezionato == "Ricavo"){
									indice = controlloreCorrente.getMappaProdotti().get(k).getRicavo();
							}
							
							//Guadagno
							else if (indiceSelezionato == "Guadagno"){
									indice = controlloreCorrente.getMappaProdotti().get(k).getGuadagno();
							}
							
							//Valore Magazzino
							else if (indiceSelezionato == "Valore Magazzino"){
									indice = controlloreCorrente.getMappaProdotti().get(k).getValoreMagazzino();
							}
							
							//Mancato Guadagno
							else if (indiceSelezionato == "Mancato Guadagno"){
									indice = controlloreCorrente.getMappaProdotti().get(k).getMancatoGuadagno();
							}
							
							//Spesa Evitabile
							else if (indiceSelezionato == "Spesa Evitabile"){
									indice = controlloreCorrente.getMappaProdotti().get(k).getSpesaEvitabile();	
							}
						
						} catch (IDEsternoException e) {
							e.printStackTrace();
						}
						
						if (indice > indiceMax){
							indiceMax=indice;
							nomeIndiceMax = k;
						}
						
						
					} 
				}				
					
				System.out.print("\n***** Gestore - Inizio Calcolo Top Prodotto *****");
				System.out.print("\nIndice Selezionato:" + indiceSelezionato + " - Nome Top Prodotto:" + nomeIndiceMax + " - Valore Indice:" + indiceMax);
				System.out.print("\n***** Gestore - Fine Calcolo Top Prodotto: *****");
				
				//Setta nome e valore dell'indice restituito da uno degli IF precedenti
				textVIndiceTop.setText(nomeIndiceMax);
				textVIndiceTop2.setText(Double.toString(indiceMax));
						
			}
		}
	
		//-----------------
		//CalcolaGerarchiaAA
		//-----------------
		private class CalcolaGerarchiaAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String indiceSelezionato = (String) tendinaIndice2.getSelectedItem();
				String superClasseProdottoCorrente="";
				String nomeTipoProdotto = "";
				double indiceProdottoCorrente = 0.0;
				int numCategorie= CostantiClassiAlimentari.CategorieAlimentari.length;
				int numClassi= CostantiClassiAlimentari.ClasseAlimentareFinale.length;
				double [] indiceCategoria = new double [numCategorie];
				double [] indiceClasse = new double [numClassi];
				String superClasseCorrente="";
				
				
				Set <String> p= controlloreCorrente.keySet();
				//Controllo che l'elenco non sia vuoto
				if (p.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Magazzino Vuoto");
				}
				else 
				{

					for (String k : p) 
					{				
						
						Portata oggettoPortata;
						try {
							oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
							nomeTipoProdotto = oggettoPortata.getClass().getName();
							//Class<?> c = Class.forName(nomeTipoProdotto);
							//Class<?> superclass = c.getSuperclass();
							//classeProdottoCorrente = superclass.getSimpleName();
							
							//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
							//superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getSimpleName();
							superClasseProdottoCorrente = Class.forName(nomeTipoProdotto).getSuperclass().getName();
							
							//Spesa
							if (indiceSelezionato == "Spesa")
								indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getSpesa();
							//Ricavo
							else if (indiceSelezionato == "Ricavo")
								indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getRicavo();
							//Guadagno
							else if (indiceSelezionato == "Guadagno")
								indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getGuadagno();
							//Valore Magazzino
							else if (indiceSelezionato == "Valore Magazzino")
								indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getValoreMagazzino();
							//Mancato Guadagno
							else if (indiceSelezionato == "Mancato Guadagno")
								indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getMancatoGuadagno();
							//Spesa Evitabile
							else if (indiceSelezionato == "Spesa Evitabile")
								indiceProdottoCorrente=controlloreCorrente.getMappaProdotti().get(k).getSpesaEvitabile();
							
							
							for (int i = 0; i < numCategorie; i++){
								//se la superclasse ha lo stesso nome della categoria
								if (CostantiClassiAlimentari.CategorieAlimentari[i]==superClasseProdottoCorrente){
									//System.out.println("SUPER: " + superClasseProdottoCorrente);
									indiceCategoria[i]= indiceCategoria[i] + indiceProdottoCorrente;
								}
								//se il prodotto ha lo stesso nome della categoria (es:Frutta e Dolce)
								else if (CostantiClassiAlimentari.CategorieAlimentari[i]==nomeTipoProdotto){
									//System.out.println("SUPER: " + superClasseProdottoCorrente);
									indiceCategoria[i]= indiceCategoria[i] + indiceProdottoCorrente;
								}
								
							}
							for (int i = 0; i < numClassi; i++){
								if (CostantiClassiAlimentari.ClasseAlimentareFinale[i]==nomeTipoProdotto){
									//System.out.println("NOMETIPO: " + nomeTipoProdotto);
									indiceClasse[i]= indiceClasse[i] + indiceProdottoCorrente;
								}
								
							}
							
						} catch (IDEsternoException e) {
							e.printStackTrace();
						}
						catch (ClassNotFoundException e) {
							e.printStackTrace();
						}

					} 
					
					//Output Testuale:
			    	areaIndice.setText("INDICE SELEZIONATO: " + indiceSelezionato + "- Metodo Interrogazione Oggetti\n\n");
			    	String addAreaIndice = "";
			    					    	
					for (int i = 0; i < numCategorie; i++){
						addAreaIndice = addAreaIndice + " |\n |\n";
						//Stampo a video la categoria e l'indice
						System.out.println(indiceSelezionato + " Categoria: " + CostantiClassiAlimentari.CategorieAlimentari[i] +  "=" + indiceCategoria[i]);	
						addAreaIndice = addAreaIndice + CostantiClassiAlimentari.CategorieAlimentari[i] +  " (" + indiceCategoria[i] + ")\n |\n";
						
						for (int j = 0; j < numClassi; j++){
							try {
								//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
								//superClasseCorrente = Class.forName(CostantiClassiAlimentari.ClasseAlimentareFinale[j]).getSuperclass().getSimpleName();
								superClasseCorrente = Class.forName(CostantiClassiAlimentari.ClasseAlimentareFinale[j]).getSuperclass().getName();
								if(CostantiClassiAlimentari.CategorieAlimentari[i] == superClasseCorrente){
									if(superClasseCorrente != "Portata"){
										//Stampo a video la classe e l'indice
										System.out.println(indiceSelezionato + " Classe: " + CostantiClassiAlimentari.ClasseAlimentareFinale[j] +  "=" + indiceClasse[j]);
										addAreaIndice = addAreaIndice + " |---------- " + CostantiClassiAlimentari.ClasseAlimentareFinale[j] +  " (" + indiceClasse[j] + ")\n";
									 
										for (String k : p) 
										{				
											Portata oggettoPortata;
											try {
												oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
												nomeTipoProdotto = oggettoPortata.getClass().getName();
	
												//Spesa
												if (indiceSelezionato == "Spesa")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getSpesa();
												//Ricavo
												else if (indiceSelezionato == "Ricavo")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getRicavo();
												//Guadagno
												else if (indiceSelezionato == "Guadagno")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getGuadagno();
												//Valore Magazzino
												else if (indiceSelezionato == "Valore Magazzino")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getValoreMagazzino();
												//Mancato Guadagno
												else if (indiceSelezionato == "Mancato Guadagno")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getMancatoGuadagno();
												//Spesa Evitabile
												else if (indiceSelezionato == "Spesa Evitabile")
													indiceProdottoCorrente=controlloreCorrente.getMappaProdotti().get(k).getSpesaEvitabile();
												
												//Stampo a video il nome del prodotto e l'indice
												if(nomeTipoProdotto == CostantiClassiAlimentari.ClasseAlimentareFinale[j]){
													System.out.println("Prodotto:" + k +  " - " + indiceSelezionato +": " + indiceProdottoCorrente);
													addAreaIndice = addAreaIndice + " |       	|---------- " + k +  " (" + indiceProdottoCorrente + ")\n";
												}
											} 
											catch (IDEsternoException e) {
												e.printStackTrace();
											}
										}
									}
								}	
								else if (superClasseCorrente == "Portata"){ 
									for (String k : p) 
									{				
										Portata oggettoPortata;
										try {
											oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
											nomeTipoProdotto = oggettoPortata.getClass().getName();

											//Spesa
											if (indiceSelezionato == "Spesa")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getSpesa();
											//Ricavo
											else if (indiceSelezionato == "Ricavo")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getRicavo();
											//Guadagno
											else if (indiceSelezionato == "Guadagno")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getGuadagno();
											//Valore Magazzino
											else if (indiceSelezionato == "Valore Magazzino")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getValoreMagazzino();
											//Mancato Guadagno
											else if (indiceSelezionato == "Mancato Guadagno")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getMancatoGuadagno();
											//Spesa Evitabile
											else if (indiceSelezionato == "Spesa Evitabile")
												indiceProdottoCorrente=controlloreCorrente.getMappaProdotti().get(k).getSpesaEvitabile();
										
											//Stampo a video il nome del prodotto e l'indice
											if(nomeTipoProdotto == CostantiClassiAlimentari.ClasseAlimentareFinale[j] && nomeTipoProdotto==CostantiClassiAlimentari.CategorieAlimentari[i]){
												System.out.println("Prodotto:" + k +  " - " + indiceSelezionato +": " + indiceProdottoCorrente);
												addAreaIndice = addAreaIndice + " |---------- " + k +  " (" + indiceProdottoCorrente + ")\n";
											}
											
										} 
										catch (IDEsternoException e) {
											e.printStackTrace();
										}
									}
								}								
							} 
							catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
					areaIndice.append(addAreaIndice);
				}
			}
		}
		

		//-----------------
		//CalcolaGerarchiaVSAA Variabili Statice per indici di Classi e Gerarchie
		//-----------------
		private class CalcolaGerarchiaVSAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String indiceSelezionato = (String) tendinaIndice2.getSelectedItem();
				int xMatriceGerarchia = ConfineGestoreIndici.indexCatalogoIndici(indiceSelezionato);
				String nomeTipoProdotto = "";
				double indiceProdottoCorrente = 0.0;
				int numCategorie= CostantiClassiAlimentari.CategorieAlimentari.length;
				int numClassi= CostantiClassiAlimentari.ClasseAlimentareFinale.length;
				String superClasseCorrente="";
				
				Set <String> p= controlloreCorrente.keySet();
				//Controllo che l'elenco non sia vuoto
				if (p.isEmpty())
				{
					JOptionPane.showMessageDialog(null,"Magazzino Vuoto");
				}
				else 
				{

					//Output Testuale:
			    	areaIndice.setText("INDICE SELEZIONATO: " + indiceSelezionato + " - Metodo Variabili Statiche\n\n");
			    	String addAreaIndice = "";
			    					    	
					for (int i = 0; i < numCategorie; i++){
						addAreaIndice = addAreaIndice + " |\n |\n";
						//Stampo a video la categoria e l'indice
						System.out.println(indiceSelezionato + " Categoria: " + CostantiClassiAlimentari.CategorieAlimentari[i] +  "=" + VariabiliGerarchia.getVGerarchia(xMatriceGerarchia, i));	
						addAreaIndice = addAreaIndice + CostantiClassiAlimentari.CategorieAlimentari[i] +  " (" + VariabiliGerarchia.getVGerarchia(xMatriceGerarchia, i) + ")\n |\n";
						
						for (int j = 0; j < numClassi; j++){
							try {
								//MDM - occorre inserire i path dei package. Sostituito il metodo finale .getSimpleName() con .getName()
								//superClasseCorrente = Class.forName(CostantiClassiAlimentari.ClasseAlimentareFinale[j]).getSuperclass().getSimpleName();
								superClasseCorrente = Class.forName(CostantiClassiAlimentari.ClasseAlimentareFinale[j]).getSuperclass().getName();
								if(CostantiClassiAlimentari.CategorieAlimentari[i] == superClasseCorrente){
									if(superClasseCorrente != "Portata"){
										//Stampo a video la classe e l'indice
										System.out.println(indiceSelezionato + " Classe: " + CostantiClassiAlimentari.ClasseAlimentareFinale[j] +  "=" + VariabiliGerarchia.getVGerarchia(xMatriceGerarchia, numCategorie+j));
										addAreaIndice = addAreaIndice + " |---------- " + CostantiClassiAlimentari.ClasseAlimentareFinale[j] +  " (" + VariabiliGerarchia.getVGerarchia(xMatriceGerarchia, numCategorie+j) + ")\n";
									 
										for (String k : p) 
										{				
											Portata oggettoPortata;
											try {
												oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
												nomeTipoProdotto = oggettoPortata.getClass().getName();
	
												//Spesa
												if (indiceSelezionato == "Spesa")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getSpesa();
												//Ricavo
												else if (indiceSelezionato == "Ricavo")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getRicavo();
												//Guadagno
												else if (indiceSelezionato == "Guadagno")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getGuadagno();
												//Valore Magazzino
												else if (indiceSelezionato == "Valore Magazzino")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getValoreMagazzino();
												//Mancato Guadagno
												else if (indiceSelezionato == "Mancato Guadagno")
													indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getMancatoGuadagno();
												//Spesa Evitabile
												else if (indiceSelezionato == "Spesa Evitabile")
													indiceProdottoCorrente=controlloreCorrente.getMappaProdotti().get(k).getSpesaEvitabile();
												
												//Stampo a video il nome del prodotto e l'indice
												if(nomeTipoProdotto == CostantiClassiAlimentari.ClasseAlimentareFinale[j]){
													System.out.println("Prodotto:" + k +  " - " + indiceSelezionato +": " + indiceProdottoCorrente);
													addAreaIndice = addAreaIndice + " |       	|---------- " + k +  " (" + indiceProdottoCorrente + ")\n";
												}
											} 
											catch (IDEsternoException e) {
												e.printStackTrace();
											}
										}
									}
								}	
								else if (superClasseCorrente == "Portata"){ 
									for (String k : p) 
									{				
										Portata oggettoPortata;
										try {
											oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
											nomeTipoProdotto = oggettoPortata.getClass().getName();

											//Spesa
											if (indiceSelezionato == "Spesa")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getSpesa();
											//Ricavo
											else if (indiceSelezionato == "Ricavo")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getRicavo();
											//Guadagno
											else if (indiceSelezionato == "Guadagno")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getGuadagno();
											//Valore Magazzino
											else if (indiceSelezionato == "Valore Magazzino")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getValoreMagazzino();
											//Mancato Guadagno
											else if (indiceSelezionato == "Mancato Guadagno")
												indiceProdottoCorrente = controlloreCorrente.getMappaProdotti().get(k).getMancatoGuadagno();
											//Spesa Evitabile
											else if (indiceSelezionato == "Spesa Evitabile")
												indiceProdottoCorrente=controlloreCorrente.getMappaProdotti().get(k).getSpesaEvitabile();
										
											//Stampo a video il nome del prodotto e l'indice
											if(nomeTipoProdotto == CostantiClassiAlimentari.ClasseAlimentareFinale[j] && nomeTipoProdotto==CostantiClassiAlimentari.CategorieAlimentari[i]){
												System.out.println("Prodotto:" + k +  " - " + indiceSelezionato +": " + indiceProdottoCorrente);
												addAreaIndice = addAreaIndice + " |---------- " + k +  " (" + indiceProdottoCorrente + ")\n";
											}
											
										} 
										catch (IDEsternoException e) {
											e.printStackTrace();
										}
									}
								}								
							} 
							catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
					}
					areaIndice.append(addAreaIndice);
				}
			}    
		}
		
		public static int indexCatalogoIndici(String s)
		{
			int i;	
			for (i=0;i<CatalogoIndici.length;i++) 
				{
					if (s.equals(CatalogoIndici[i])) break;
				}
				return i;
		}
		
	}





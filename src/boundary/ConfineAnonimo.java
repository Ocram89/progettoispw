package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.GregorianCalendar;
import java.util.Set;

import javax.swing.ImageIcon;
//	import java.io.File;
//	import java.io.IOException;
	import javax.swing.JButton;
//import javax.swing.JComboBox;
//	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//	import java.util.*;
//import javax.swing.JTextField;

//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;

public class ConfineAnonimo 
{
	
	private Controllore controlloreCorrente;
	
	public  JPanel 	pannelloAnonimo1				= new JPanel(); 
	public  JPanel 	pannelloAnonimo2				= new JPanel(); 
	
	public  JPanel 	pannelloAnonimo2VistaMenu		= new JPanel();
	
	public 	JPanel	pannelloTitolo 					= new JPanel();
	public  JPanel	pannelloBottoniAnonimo			= new JPanel();	
	public  JPanel 	pannelloAnonimoSospensione 		= new JPanel();
	
	//JLabel
	public JLabel 		titoloConnesso				= new JLabel();
	
	//Bottoni
	private JButton 	bEsci; 
	private JButton 	bMenu; 	

	public JButton 		bCambiaUtente; 
	public JButton 		bSospendi; 
	
//	public JButton 		bLogGiacenza;			
//	public JButton 		bSerializza;
//	public JButton 		bDeserializza;
//	public JButton 		bSalvaFile;
	
	//PannelloGestore3
	public JLabel 		labelMenuProdotti;
	
	public static JButton 		bAggiornaMenu;
	public static JButton 		bChiudiPannelloAnonimo2VistaMenu;
	

	//Ascoltatori Gestore1 e 2
	private MenuAA							ascoltatoreEtAzioneMenu;
	
	private CambiaUtenteAA					ascoltatoreEtAzioneCambiaUtente;
	private SospendiAA 						ascoltatoreEtAzioneSospendi;
	private EsciAA 							ascoltatoreEtAzioneEsci;
	private RiprendiAnonimoAA				ascoltatoreEtAzioneRiprendiAnonimo;
	
	//Ascoltatori pannelloGestore3Gestione
	
	private ChiudiPannelloAnonimo2VistaMenuAA	ascoltatoreChiudiPannelloAnonimo2VistaMenu;
	private AggiornaMenuAA						ascoltatoreAggiornaMenu;		
	
	
	//Per Pannelli Anonimo1 e 2
	int altezzaConfine;
	int larghezzaConfine;
	int larghezzaPannelli;
	int altezzaPannelloAnonimo1;
	int altezzaPannelloAnonimo2;
	public static int locazioneYProssimoPannello;
	
	//Per Pannello Menu
	public JScrollPane scrollMenu;
	public JTextArea 	areaMenu;
	public JTextArea 	areaMenu2;
	int altezzaPannelloAnonimo2VistaMenu;
	int larghezzaPannelloAnonimo2VistaMenu;
	int locazioneYPrimaRiga;
	
	public ConfineAnonimo(Controllore controllore)
	{
		this.controlloreCorrente= controllore;
		
		int altezzaBottoni = 40;
		int altezzaBottoniTitolo;
		int larghezzaBottoniTitolo;
		int bordoPannelli = 5;
		int bordoBottoni = 30;
		int larghezzaBottoni; //Da inizializzare dopo!!
		int altezzaPannelloTitoloAnonimo = 40;
		
		altezzaConfine = ConfineAvvio.Confine.getHeight();
		larghezzaConfine = ConfineAvvio.Confine.getWidth();
		larghezzaPannelli = larghezzaConfine;
		altezzaPannelloAnonimo1 = altezzaConfine/5 - 10;
		locazioneYProssimoPannello = altezzaPannelloAnonimo1;
		// Costruisco pannello Amministratore
		ConfineAvvio.Confine.setLayout(null);
		pannelloAnonimo1.setSize(larghezzaPannelli, altezzaPannelloAnonimo1);
		pannelloAnonimo1.setBackground(new Color(200, 200, 200));
		pannelloAnonimo1.setLayout(null);
		ConfineAvvio.Confine.add(pannelloAnonimo1);
		//pannelloGestore1.setVisible(true);
		
		altezzaPannelloAnonimo2 = altezzaConfine - altezzaPannelloAnonimo1 -43;
		pannelloAnonimo2.setLayout(null);
		pannelloAnonimo2.setBounds(0,altezzaPannelloAnonimo1+5,larghezzaPannelli, altezzaPannelloAnonimo2);
		pannelloAnonimo2.setBackground(new Color(190, 190, 190));
		ConfineAvvio.Confine.add(pannelloAnonimo2);
		pannelloAnonimo2.setVisible(true);
		
		
		//COSTRUISCO PANNELLO TITOLO
		pannelloTitolo.setLayout(null);
        pannelloTitolo.setBackground(new Color(190, 190, 190));
        pannelloTitolo.setBounds(0, 5, larghezzaPannelli, altezzaPannelloTitoloAnonimo); 
        pannelloTitolo.add(titoloConnesso); 
        
        //COMPONENTI DEL PANNELLO TITOLO:
        //1.Titolo
        titoloConnesso.setFont(new Font("Verdana", Font.BOLD, 20));
        titoloConnesso.setLocation(0, 0);
        titoloConnesso.setSize(larghezzaPannelli, altezzaPannelloTitoloAnonimo);
        titoloConnesso.setHorizontalAlignment(JLabel.CENTER);
        titoloConnesso.setVerticalAlignment(JLabel.CENTER);
        titoloConnesso.setText("Sei Connesso come ANONIMO");
		
        //2.Bottone Indietro
        larghezzaBottoniTitolo = 150;
        altezzaBottoniTitolo = 30;
		/*bRitorna 			= new JButton("INDIETRO"); 
		bRitorna.setLocation(5,(altezzaPannelloTitolo/2)-(altezzaBottoniTitolo/2));
        bRitorna.setSize(larghezzaBottoniTitolo, altezzaBottoniTitolo);*/
        
        int locazioneYBott = (altezzaPannelloTitoloAnonimo/2)-(altezzaBottoniTitolo/2);
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
        pannelloBottoniAnonimo.setLayout(null);
        pannelloBottoniAnonimo.setBounds(0, bordoPannelli*2 +altezzaPannelloTitoloAnonimo,larghezzaPannelli, altezzaConfine/10);
        pannelloBottoniAnonimo.setBackground(new Color(190, 190, 190));
        
        //System.out.println(altezzaPannelloBottoniAdmin);
        
         //Attenzione: larghezzaBottoni deve essere inizializzato QUI! per via di pannelloBottoni.
        larghezzaBottoni = pannelloBottoniAnonimo.getWidth()/5;  

        //COMPONENTI PANNELLO BOTTONI	
               
        // Bottone bMenu
        int altezzaPannelloBottoniAnonimo = pannelloBottoniAnonimo.getHeight();
        int locazioneYBottoni = (altezzaPannelloBottoniAnonimo/2)-(altezzaBottoni/2);
        
        //int locazioneXBottoneMenu = larghezzaPannelli/4;
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
        bMenu.setToolTipText("Visualizza Menu");
        bMenu.setBounds(350,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
        
                
        // Bottone ESCI
        //int locazioneXBottEsci = locazioneXBottoneMenu + larghezzaBottoni + 60;
		//bEsci = new JButton("ESCI"); 
		//bEsci.setBounds(locazioneXBottEsci, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
		//bEsci.setBackground(Color.red);
		
//AGGIUNTA BOTTONI AI VARI PANNELLI
		
		pannelloTitolo.add(bCambiaUtente);
		pannelloTitolo.add(bSospendi);
		pannelloTitolo.add(bEsci);
		
		pannelloBottoniAnonimo.add(bMenu);
		//pannelloBottoniAnonimo.add(bEsci);
	    
		 // Aggiunta dei Vari Pannelli al PANNELLO PRINCIPALE
        pannelloAnonimo1.add(pannelloTitolo);
        pannelloAnonimo1.add(pannelloBottoniAnonimo);
        
        // Definisci ascoltatori di bottoni e relative azioni
        
		ascoltatoreEtAzioneCambiaUtente		= new CambiaUtenteAA();
		ascoltatoreEtAzioneSospendi			= new SospendiAA();
		ascoltatoreEtAzioneEsci				= new EsciAA();	
		ascoltatoreEtAzioneMenu 			= new MenuAA();
		
		bEsci.addActionListener(ascoltatoreEtAzioneEsci);
		//bRitorna.addActionListener(ascoltatoreEtAzioneRitorna);
		bCambiaUtente.addActionListener(ascoltatoreEtAzioneCambiaUtente);
		bSospendi.addActionListener(ascoltatoreEtAzioneSospendi);
		bMenu.addActionListener(ascoltatoreEtAzioneMenu);
		
		operazioniOra();
	}

	private void sospensione()
	{
		int lunghezzaScritta=420;
		int altezzaScritta = 30;
		int altezzaPannelloAnonimoSospensione;
		int larghezzaPannelloAnonimoSospensione;
		
		
		altezzaConfine = ConfineAvvio.Confine.getHeight();
		ConfineAvvio.Confine.add(pannelloAnonimoSospensione);
		//ConfineAvvio.Confine.setLayout(null);
		pannelloAnonimoSospensione.setLayout(null);
		pannelloAnonimoSospensione.setVisible(true);
		
		altezzaPannelloAnonimoSospensione = altezzaConfine;
		larghezzaPannelloAnonimoSospensione = ConfineAvvio.Confine.getWidth();
		
		pannelloAnonimoSospensione.setBounds(0,0,larghezzaPannelloAnonimoSospensione, altezzaPannelloAnonimoSospensione);
		pannelloAnonimoSospensione.setBackground(new Color(190,190,190));
		
		int larghezzaBottoni = larghezzaPannelloAnonimoSospensione/4;			
		//int bordoBottoni = 30;
		int altezzaBottoni = 40;
		
		JLabel labelSospensioneAnonimo = new JLabel("Modalit‡ ANONIMO in Sospensione...");
		labelSospensioneAnonimo.setBounds((900-lunghezzaScritta)/2,30, lunghezzaScritta, altezzaScritta);
		labelSospensioneAnonimo.setFont(new Font("Verdana", Font.BOLD, 20));
		
		int locazioneXBottRiprendiAnonimo = larghezzaPannelloAnonimoSospensione/5;
		int locazioneYBottoni = altezzaPannelloAnonimoSospensione/6;
		//JButton bRiprendiAnonimo = new JButton("Riprendi");
		//bRiprendiAnonimo.setBounds(locazioneXBottRiprendiAnonimo ,locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
		//bRiprendiAnonimo.setBackground(Color.green);
		
		JButton bRiprendiAnonimo = new JButton(new ImageIcon("Save/immagini/riprendi.gif"));
        bRiprendiAnonimo.setRolloverIcon(new ImageIcon("Save/immagini/riprendiR.gif"));
        bRiprendiAnonimo.setBorderPainted(false);
        bRiprendiAnonimo.setMargin (new Insets (0, 0, 0, 0));
        bRiprendiAnonimo.setContentAreaFilled(false);
        bRiprendiAnonimo.setOpaque(true);
        bRiprendiAnonimo.setToolTipText("Riprendi");
        bRiprendiAnonimo.setBounds(200 ,locazioneYBottoni, 200, 40);
        
		
		int locazioneXBottEsci = locazioneXBottRiprendiAnonimo + larghezzaBottoni + 60;
		//JButton bEsci = new JButton("Esci");
		//bEsci.setBounds(locazioneXBottEsci, locazioneYBottoni, larghezzaBottoni, altezzaBottoni);
		//bEsci.setBackground(Color.red);
		
		JButton bEsci = new JButton(new ImageIcon("Save/immagini/escib.gif"));
        bEsci.setRolloverIcon(new ImageIcon("Save/immagini/escibR.gif"));
        bEsci.setBorderPainted(false);
        bEsci.setMargin (new Insets (0, 0, 0, 0));
        bEsci.setContentAreaFilled(false);
        bEsci.setOpaque(true);
        bEsci.setToolTipText("Chiudi Programma");
        bEsci.setBounds(500, locazioneYBottoni, 200, 40);
		
		pannelloAnonimoSospensione.add(labelSospensioneAnonimo);
		pannelloAnonimoSospensione.add(bRiprendiAnonimo);;
		pannelloAnonimoSospensione.add(bEsci);
		
		//Ascoltatori
	    ascoltatoreEtAzioneRiprendiAnonimo = new RiprendiAnonimoAA();
	    ascoltatoreEtAzioneEsci = new EsciAA();
		
		
	    //Associazione
	    bRiprendiAnonimo.addActionListener(ascoltatoreEtAzioneRiprendiAnonimo);
	    bEsci.addActionListener(ascoltatoreEtAzioneEsci);
		
	}// Fine sospensione()
	
	private void menu(int locazioneYPannelloAnonimo2VistaMenu)
	{
				
		//pannelloGestore2.setVisible(false);
		altezzaConfine = ConfineAvvio.Confine.getHeight();
		ConfineAvvio.Confine.add(pannelloAnonimo2VistaMenu);
		ConfineAvvio.Confine.setLayout(null);
		pannelloAnonimo2VistaMenu.setLayout(null);
		pannelloAnonimo2VistaMenu.setVisible(true);
		
		locazioneYPannelloAnonimo2VistaMenu += 5;
		altezzaPannelloAnonimo2VistaMenu = altezzaConfine - locazioneYPannelloAnonimo2VistaMenu -38;
		int larghezzaPannello= ConfineAvvio.Confine.getWidth();
		int lunghezzaScritta=200;
		int altezzaScritta = 40;
		
		//locazioneProssimoPannelloGestore3 = locazioneYPannelloGestore2Gestione + altezzaPannelloGestore2Gestione;
		
		pannelloAnonimo2VistaMenu.setBounds(0,locazioneYPannelloAnonimo2VistaMenu,larghezzaPannello, altezzaPannelloAnonimo2VistaMenu);
		pannelloAnonimo2VistaMenu.setBackground(new Color(190,190,190));
		
		locazioneYPrimaRiga =	(altezzaPannelloAnonimo2VistaMenu)*1/8;
		
		//altezzaPannelloFornitore2Forniture = pannelloFornitore2Forniture.getHeight(); //Questo deve stare qui (cio√® dopo aver impostato il size di pannelloAdmin3)
		larghezzaPannelloAnonimo2VistaMenu = larghezzaPannello;			
		
		//Titolo
		labelMenuProdotti = new JLabel("Menu");
		labelMenuProdotti.setBounds((pannelloAnonimo2VistaMenu.getWidth()/3+110),10, lunghezzaScritta, altezzaScritta);
		labelMenuProdotti.setFont(new Font("Verdana", Font.BOLD, 20));

		
		
		//Scritte e Tendine
				
		//int larghezzaBottoni = pannelloAnonimo2VistaMenu.getWidth()/5;	
		int larghezzaBottoni = 200;
		int altezzaBottoni = 40;
		//int distanzaTraBottoni = 65;
		
		//textArea(larghezzaPannelloAdmin2)*95/100;
		int altezzaMenu = (altezzaPannelloAnonimo2VistaMenu)*3/4;
		int larghezzaMenu = (larghezzaPannelloAnonimo2VistaMenu)*95/100;
		
		int locazioneXScrollAreaMenu = larghezzaPannelloAnonimo2VistaMenu/60;
		areaMenu = new JTextArea(larghezzaMenu, altezzaMenu);
		//areaMenu.setFont(new Font("Arial", 0 , 16));
		//areaMenu.setFont(new Font("Arial", 1, 16));
		//areaMenu.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		areaMenu.setFont(new Font("Arial", 1, 12));
		areaMenu.setEditable(false);
		areaMenu.setLineWrap(true);
		scrollMenu = new JScrollPane(areaMenu);
		scrollMenu.setBounds(locazioneXScrollAreaMenu, locazioneYPrimaRiga, larghezzaMenu, altezzaMenu);
		
		int locazioneXBottAggiornaMenu = larghezzaPannello/2 - larghezzaBottoni/2;
		int locazioneYBottAggiornaMenu = locazioneYPrimaRiga + altezzaMenu + 10;
		//bAggiornaMenu 	= new JButton("Aggiorna Menu"); 
		//bAggiornaMenu.setBounds(locazioneXBottAggiornaMenu,locazioneYBottAggiornaMenu,larghezzaBottoni, altezzaBottoni);
		//bAggiornaMenu.setBackground(Color.green);
		
		
		//Bottone Aggiorna Menu
		bAggiornaMenu = new JButton(new ImageIcon("Save/immagini/aggiorna_menu.gif"));
		bAggiornaMenu.setRolloverIcon(new ImageIcon("Save/immagini/aggiorna_menuR.gif"));
		bAggiornaMenu.setBorderPainted(false);
		bAggiornaMenu.setMargin (new Insets (0, 0, 0, 0));
		bAggiornaMenu.setContentAreaFilled(false);
		bAggiornaMenu.setOpaque(true);
		bAggiornaMenu.setToolTipText("Aggiorna Menu");
		bAggiornaMenu.setBounds(locazioneXBottAggiornaMenu,locazioneYBottAggiornaMenu, larghezzaBottoni, altezzaBottoni);
		
		
		
		//int locazioneXBottRimozioneProdotti = locazioneXBottAggiornaMenu + larghezzaBottoni + distanzaTraBottoni;
		//bRimozioneProdotti 	= new JButton("Rimozione Prodotti"); 
		//bRimozioneProdotti.setBounds(locazioneXBottRimozioneProdotti,locazioneYBottoni,larghezzaBottoni, altezzaBottoni);
		
		
		//bChiudiPannelloAnonimo2VistaMenu = new JButton("X");
		//bChiudiPannelloAnonimo2VistaMenu.setLocation(larghezzaPannello -70 ,5);
		//bChiudiPannelloAnonimo2VistaMenu.setSize(45, 20);
		//bChiudiPannelloAnonimo2VistaMenu.setBackground(Color.red);
		
		//"X" chiusura Pannello	
		bChiudiPannelloAnonimo2VistaMenu = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
		bChiudiPannelloAnonimo2VistaMenu.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
		bChiudiPannelloAnonimo2VistaMenu.setBounds(827, 5, 48, 20);
		bChiudiPannelloAnonimo2VistaMenu.setBorderPainted(false);
		bChiudiPannelloAnonimo2VistaMenu.setMargin (new Insets (0, 0, 0, 0));
		bChiudiPannelloAnonimo2VistaMenu.setContentAreaFilled(false);
		bChiudiPannelloAnonimo2VistaMenu.setOpaque(true);
		bChiudiPannelloAnonimo2VistaMenu.setToolTipText("Chiusura Pannello");
		
		
		
		pannelloAnonimo2VistaMenu.add(labelMenuProdotti);
		
		pannelloAnonimo2VistaMenu.add(bAggiornaMenu);
		pannelloAnonimo2VistaMenu.add(scrollMenu);
		pannelloAnonimo2VistaMenu.add(bChiudiPannelloAnonimo2VistaMenu);
		
		
		//ASCOLTATORI
		ascoltatoreChiudiPannelloAnonimo2VistaMenu	= new ChiudiPannelloAnonimo2VistaMenuAA();
		ascoltatoreAggiornaMenu 					= new AggiornaMenuAA();				

		//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
		
		
		//ASSOCIAZIONI
		bChiudiPannelloAnonimo2VistaMenu.addActionListener(ascoltatoreChiudiPannelloAnonimo2VistaMenu);
		bAggiornaMenu.addActionListener(ascoltatoreAggiornaMenu);
		//bRimozioneProdotti.addActionListener(ascoltatoreRimozioneProdotti);
		
	}
	
	private String aggiungiSpazzi(String campo, int len)
	{
		int lunghezzaNomeEtipo = campo.length();
		if (lunghezzaNomeEtipo<len)
		{
			int aggiuntaSpazi = len - lunghezzaNomeEtipo;
			for (int i=0; i<aggiuntaSpazi;i++)
			{
				campo = campo+" ";
			}
		}
		return campo;
		
	}
	
	
	protected class SospendiAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			try
			{
				pannelloAnonimo1.setVisible(false);
				//pannelloAdmin2.setVisible(true);
				pannelloAnonimo2.setVisible(false);
				
				//pannelloAdminSospensione.setVisible(false);
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
			pannelloAnonimo2VistaMenu.setVisible(true);
			pannelloAnonimo2VistaMenu.setVisible(false);
			pannelloAnonimo2.setVisible(false);
			bCambiaUtente.setEnabled(false);
			bSospendi.setEnabled(false);
			bMenu.setEnabled(false);
			
/*SE QUALCOSA NON VA RIATTIVARLO!! */ //pannelloAdmin3OpzioniAdmin.setVisible(false); 
			
			//pannelloGestore1.setVisible(false);	//disattivo e riattivo
			//pannelloGestore1.setVisible(true);
			
			//pannelloGestore2.setVisible(false); //IMPORTANTISSIMO NON RIATTIVARE INVECE QUESTO, ALTRIMENTI DA PROBLEMI LA TENDINA
			
			//
			menu(locazioneYProssimoPannello);
			//menu();
			
		}
	}
	
	private class AggiornaMenuAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
						
			//areaMenu.setText("     PRODOTTI \t\t                       GIACENZE \t\tDATA IMMISSIONE\n\n");
			areaMenu.setText("     PRODOTTI \t\t                       DISPONIBILITA' \t\tPREZZO\n\n");
			
			//Inserisco in "p", l'insieme dei nomi dei Prodotti presenti nella mappa
			Set <String> p= controlloreCorrente.keySet();
			String descrizione;
			
			if (p.isEmpty())
			{
				descrizione ="Il menu Ë vuoto\n";
			}
			else 
			{
				descrizione = "";
			}
			
			int n = 0;
			for (String k : p) 
			{
				n++;
				try 
				{																	  //k Ë una Stringa: nome Prodotto
					Portata oggettoPortata = controlloreCorrente.getMappaProdotti().get(k);
					String nomeTipoProdotto = oggettoPortata.getClass().getSimpleName();
					
					String data = oggettoPortata.getGiornoCreazione();
					
					String nomeEtipo = n+". "+k+" ("+nomeTipoProdotto+"):";
					nomeEtipo=aggiungiSpazzi(nomeEtipo,60);

					double pV = oggettoPortata.getPrezzoVendita();
					
					//descrizione = descrizione +nomeEtipo+"\t"+controlloreCorrente.giacenza(new IDEsterno(k)) +"\t\t      "+data+"\n";
					descrizione = descrizione +nomeEtipo+"\t"+controlloreCorrente.giacenza(new IDEsterno(k)) +"\t\t      "+pV+"\n";
				} 
				catch (DirittiException e) 
				{
						System.out.print(e.getMessage()+"\n");
				} 
				catch (IDEsternoException e) 
				{
						System.out.print(e.getMessage()+"\n");
				} 
				catch (ProdottoException e) 
				{
						System.out.print(e.getMessage()+"\n");
				}
			}
			areaMenu.append(descrizione);
		}
	}

	private class EsciAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			//Salva tutto
			System.exit(0);
		}
	}
	
	private class CambiaUtenteAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			pannelloAnonimo1.setVisible(true);
			pannelloAnonimo1.setVisible(false);
			pannelloAnonimo2.setVisible(true);
			pannelloAnonimo2.setVisible(false);
			ControlloreLogin.gestioneNuovoLogin();	
		}
	}
	
	private class RiprendiAnonimoAA implements ActionListener
	{
		public void actionPerformed(ActionEvent arg0)
		{
			pannelloAnonimoSospensione.setVisible(false);
			pannelloAnonimo1.setVisible(false);
			new ConfineAnonimo(controlloreCorrente);	
		}
	}
	private class ChiudiPannelloAnonimo2VistaMenuAA implements ActionListener
	{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAnonimo2VistaMenu.setVisible(false);
				pannelloAnonimo2.setVisible(true);
				pannelloAnonimo2.setVisible(false);
				
				bSospendi.setEnabled(true);
				bCambiaUtente.setEnabled(true);
				bMenu.setEnabled(true);
				//pannelloGestore1.setVisible(false);
				
				pannelloAnonimo1.setVisible(false);
				new ConfineAnonimo(controlloreCorrente);
				
			}
	}
	
	private void operazioniOra()
	{
		Orologio g1 = new Orologio(1, 1, 2011);
		Orologio attuale = new Orologio();
		
		int diffGiorni = Funzioni.calcolaDiffGiorni(g1, attuale);
		System.out.println("Dalla prima Data alla seconda sono passati "+diffGiorni+" giorni!");
		
	}	
}

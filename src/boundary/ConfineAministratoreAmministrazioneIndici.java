package boundary;

	import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;

//COMMENTI ---
//MDM - Visualizzo solo il nome della classe senza il packaga ...


	public class ConfineAministratoreAmministrazioneIndici extends JFrame
	{
		private static final long	serialVersionUID	= 1L;
		
		Double risultatoSuccesso;
		Double risultatoGradimento;
		Double risultatoGradimentoMedio;
		Double risultatoDeperibilita;
		
		// Relazione con controllore
		private Controllore controlloreCorrente;
		
		public JPanel pannelloAdmin2 = new JPanel();
				
		// Tendine
		public JComboBox tendinaCategoriaProd;
		public JComboBox tendinaNomiEsterni;
		public JComboBox tendinaClassiProd;
		
		//Label
		public JLabel 		labelSceltaCalcolo;
		public JLabel		labelGestioneIndici;
		public JLabel 		nomiCategoriaProd;
		public JLabel 		classeProdotto;
		public JLabel 		nomiEsterni;
		public JLabel 		nomiClassiProd;
		
		
		public ButtonGroup 	gruppoMetodoButton;
		public JRadioButton metodoA;
		public JRadioButton metodoB;
		
		public ButtonGroup 	gruppoRadioButton;
		public JRadioButton radioProdotti;
		public JRadioButton radioClassi;
		public JRadioButton radioCategorie;
		public JRadioButton radioTotali;
		//Bottoni
				
		public JButton 		bSuccesso;
		public JButton 		bGradimento;
		public JButton 		bGradimentoMedioGiornaliero;
		public JButton 		bDeperibilita;
		
		public JButton 		bChiudiPannello;
				
		public JLabel     	valoreLabel;
		public JTextField 	valoreFUscita;
		
		// Ascoltatori di bottoni e relative azioni
		
		private MetodoAAA 						ascoltatoreEtAzioneMetodoA;
		private MetodoBAA 						ascoltatoreEtAzioneMetodoB;
		
		
		private DeperibilitaAA 						ascoltatoreEtAzioneDeperibilita;
		private GradimentoAA 						ascoltatoreEtAzioneGradimento;
		private GradimentoMedioGiornalieroAA 		ascoltatoreEtAzioneGradimentoMedioGiornaliero;
		private SuccessoAA 							ascoltatoreEtAzioneSuccesso;

		private ChiudiPannelloAA 					ascoltatoreEtAzioneChiudiPannello;
		
		//Ascoltatori tendine
		private TendinaCategoriaProdAA   			ascoltatoreEtAzioneTendinaCategoriaProd;
		private TendinaClasseAlimentareProdAA		ascoltatoreEtAzioneTendinaClassiProd;
		private TendinaNomiEsterniAA   				ascoltatoreEtAzioneTendinaNomiEsterni;

		private RadioProdottiAA						ascoltatoreEtAzioneRadioProdotti;
		private RadioClassiAA						ascoltatoreEtAzioneRadioClassi;
		private RadioCategorieAA					ascoltatoreEtAzioneRadioCategorie;
		private RadioTotaliAA						ascoltatoreEtAzioneRadioTotali;

		private String 	nomeEsternoCorrente;
		private String 	classeAlimentareFinaleCorrente;
		private String 	categoriaAlimentare;
		
		private String 	sceltaMetodo = "A";
		
		int altezzaConfine;
		int altezzaPannelloAdmin2;
		int larghezzaPannelloAdmin2;
		int locazioneYPrimaRiga;
		int locazioneYSecondaRiga;
		int locazioneYTerzaRiga;
		int locazioneYQuartaRiga;
		int locazioneYQuintaRiga;
		
		public ConfineAministratoreAmministrazioneIndici(Controllore controllore, int locazioneYPannelloAdmin2) throws IDEsternoException
		{
			
			this.controlloreCorrente=controllore;
			
			nomeEsternoCorrente= (String) controlloreCorrente.keySet().toArray()[0];
			classeAlimentareFinaleCorrente = CostantiClassiAlimentari.ClasseAlimentareFinale[0]; //ALTRO
			categoriaAlimentare = (String) CostantiClassiAlimentari.CategorieAlimentari[0];
		    //Quest'ultimo che fa? Prima invoca la mappa restituendo le chiavi in un oggetto Set. Poi il "toArray"
			//converte il set in Array, di cui prende il primo elemento. Il cast poi lo converte in String
			//La mappa è inizializzata con un nomeEsterno iniziale che dovrebbe essere "ZZZ":
			//Ma nella JComboBox c'è da subito quel valore perchè.....=> Leggere il metodo creaCampiEdEtichetteBase()
		
										 
			int lunghezzaScrittaTitolo=250;
			int altezzaScrittaTitolo = 40;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdmin2);
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin2.setLayout(null);
			pannelloAdmin2.setVisible(true);
			
			//int locazioneYPannelloAdmin2 = ConfineAvvio.Confine.getHeight()/5 - 5;
			locazioneYPannelloAdmin2 += 5;
			larghezzaPannelloAdmin2 = ConfineAvvio.Confine.getWidth();
			altezzaPannelloAdmin2 = altezzaConfine - locazioneYPannelloAdmin2 -38;
	        pannelloAdmin2.setBounds(0,locazioneYPannelloAdmin2, larghezzaPannelloAdmin2, altezzaPannelloAdmin2);
			pannelloAdmin2.setBackground(new Color(190,190,190));
			
			locazioneYPrimaRiga =	(altezzaPannelloAdmin2)*13/100;
			locazioneYSecondaRiga = (altezzaPannelloAdmin2)*30/100;
			locazioneYTerzaRiga = (altezzaPannelloAdmin2)*50/100;
			locazioneYQuartaRiga = (altezzaPannelloAdmin2)*65/100;
			locazioneYQuintaRiga = (altezzaPannelloAdmin2)*85/100;
			
			//Titolo
			int locazioneXTitoloGestioneIndici = (larghezzaPannelloAdmin2)*37/100;
			labelGestioneIndici = new JLabel("GESTIONE INDICI");
			labelGestioneIndici.setBounds(locazioneXTitoloGestioneIndici,10, lunghezzaScrittaTitolo, altezzaScrittaTitolo);
			labelGestioneIndici.setFont(new Font("Verdana", Font.BOLD, 20));
			
			//int bordoScritta = 30;
			//int lunghezzaScritta = 150;
			int altezzaScritta= 20;
			//int bordoTendina = 30;
			int altezzaTendina = 20;
			int larghezzaTendina = 180;
			
			
			int locazioneXmetodo = (larghezzaPannelloAdmin2)*3/100;
			int locazioneYmetodo = 40;
			int lunghezzaRadioMetodo = 150;
			metodoA = new JRadioButton("Metodo (a)", true);
			metodoA.setBounds(locazioneXmetodo, locazioneYmetodo, lunghezzaRadioMetodo, altezzaScritta);
			metodoA.setFont(new Font("Verdana", 0, 12));
			metodoA.setBackground(new Color(190,190,190));
			
			metodoB = new JRadioButton("Metodo (b)", false);
			metodoB.setBounds(locazioneXmetodo,locazioneYmetodo+altezzaScritta, lunghezzaRadioMetodo, altezzaScritta);
			metodoB.setFont(new Font("Verdana", 0, 12));
			metodoB.setBackground(new Color(190,190,190));
				
			gruppoMetodoButton = new ButtonGroup();
			gruppoMetodoButton.add(metodoA);
			gruppoMetodoButton.add(metodoB);
			
			
			//PRIMA RIGA
			int locazioneXSceltaCalcoloLabel = (larghezzaPannelloAdmin2)*29/80;
			int lunghezzaScrittaSceltaCalcolo = 250;
			int altezzaScrittaSceltaCalcolo = 40;
			labelSceltaCalcolo = new JLabel("Scegli il tipo di calcolo:");
			labelSceltaCalcolo.setBounds(locazioneXSceltaCalcoloLabel,locazioneYPrimaRiga,lunghezzaScrittaSceltaCalcolo, altezzaScrittaSceltaCalcolo);
			labelSceltaCalcolo.setFont(new Font("Verdana", 0, 20));
			
			//SECONDA RIGA
			
			int locazioneXRadioProdotti = (larghezzaPannelloAdmin2)*3/100;
			int lunghezzaRadioProdotti = 150;
			radioProdotti = new JRadioButton("Indici Prodotti", true);
			radioProdotti.setBounds(locazioneXRadioProdotti,locazioneYSecondaRiga, lunghezzaRadioProdotti, altezzaScritta);
			radioProdotti.setFont(new Font("Verdana", Font.BOLD, 12));
			radioProdotti.setBackground(new Color(190,190,190));
			
			int locazioneXRadioClassiAlim = (larghezzaPannelloAdmin2)*28/100;
			int lunghezzaRadioClassiAlim = 150;
			radioClassi = new JRadioButton("Indici Classi", false);
			radioClassi.setBounds(locazioneXRadioClassiAlim, locazioneYSecondaRiga,lunghezzaRadioClassiAlim, altezzaScritta);
			radioClassi.setFont(new Font("Verdana", Font.BOLD, 12));
			radioClassi.setBackground(new Color(190,190,190));
			
			int locazioneXRadioCategorie = (larghezzaPannelloAdmin2)*53/100;
			int lunghezzaRadioCategorie = 150;
			radioCategorie = new JRadioButton("Indici Categorie", false);
			radioCategorie.setBounds(locazioneXRadioCategorie,locazioneYSecondaRiga,lunghezzaRadioCategorie, altezzaScritta);
			radioCategorie.setFont(new Font("Verdana", Font.BOLD, 12));
			radioCategorie.setBackground(new Color(190,190,190));
			
			int locazioneXRadioTotali = (larghezzaPannelloAdmin2)*78/100;
			int lunghezzaRadioTotali = 150;
			radioTotali = new JRadioButton("Indici Totali", false);
			radioTotali.setBounds(locazioneXRadioTotali,locazioneYSecondaRiga, lunghezzaRadioTotali, altezzaScritta);
			radioTotali.setFont(new Font("Verdana", Font.BOLD, 12));
			radioTotali.setBackground(new Color(190,190,190));
			
			gruppoRadioButton = new ButtonGroup();
			gruppoRadioButton.add(radioProdotti);
			gruppoRadioButton.add(radioClassi);
			gruppoRadioButton.add(radioCategorie);
			gruppoRadioButton.add(radioTotali);
			
			
			//TERZA RIGA
			int locazioneXLabelNomeProd = (larghezzaPannelloAdmin2)*1/100;
			int lunghezzaScrittaNomeProd = 120;
			nomiEsterni = new JLabel("Nome Prodotto:");
			nomiEsterni.setBounds(locazioneXLabelNomeProd,locazioneYTerzaRiga,lunghezzaScrittaNomeProd, altezzaScritta);
			nomiEsterni.setFont(new Font("Verdana", 0, 12));
			
			int locazioneXTendinaNomiProd = locazioneXLabelNomeProd + lunghezzaScrittaNomeProd;
			tendinaNomiEsterni = new JComboBox(controlloreCorrente.keySet().toArray());
			tendinaNomiEsterni.setSelectedIndex(0); //PRIMO NOME
			tendinaNomiEsterni.setBounds(locazioneXTendinaNomiProd,locazioneYTerzaRiga,larghezzaTendina, altezzaTendina);
			
			int locazioneXClasseProdotto = locazioneXTendinaNomiProd;
			classeProdotto = new JLabel("("+controlloreCorrente.getClassProdotto(nomeEsternoCorrente).getClass().getSimpleName()+")");
			classeProdotto.setBounds(locazioneXClasseProdotto,locazioneYTerzaRiga-30, lunghezzaRadioProdotti, altezzaScritta);
			classeProdotto.setFont(new Font("Verdana", 0, 14));		
			classeProdotto.setHorizontalAlignment(JLabel.CENTER);
			classeProdotto.setVerticalAlignment(JLabel.CENTER);
			
			int locazioneXLabelClassiProd = (larghezzaPannelloAdmin2)*39/100;
			int lunghezzaScrittaNomeClassi = 120;
			nomiClassiProd = new JLabel("Classi Prodotto:");
			nomiClassiProd.setBounds(locazioneXLabelClassiProd,locazioneYTerzaRiga,lunghezzaScrittaNomeClassi, altezzaScritta);
			nomiClassiProd.setEnabled(false);
			nomiClassiProd.setFont(new Font("Verdana", 0, 12));
			
			int locazioneXTendinaClassiProd = locazioneXLabelClassiProd + lunghezzaScrittaNomeClassi;
			int larghezzaTendinaClassi = 130;
			tendinaClassiProd = new JComboBox(CostantiClassiAlimentari.DescrClasseAlimentareFinale);
			tendinaClassiProd.setBounds(locazioneXTendinaClassiProd,locazioneYTerzaRiga,larghezzaTendinaClassi, altezzaTendina);
			tendinaClassiProd.setSelectedIndex(0);
			tendinaClassiProd.setEnabled(false);
			
			int locazioneXLabelCategoriaProd = (larghezzaPannelloAdmin2)*70/100;
			int lunghezzaScrittaNomiCategoria = 150;
			nomiCategoriaProd = new JLabel("Categoria Prodotto:");
			nomiCategoriaProd.setBounds(locazioneXLabelCategoriaProd,locazioneYTerzaRiga,lunghezzaScrittaNomiCategoria, altezzaScritta);
			nomiCategoriaProd.setEnabled(false);
			nomiCategoriaProd.setFont(new Font("Verdana", 0, 12));
			
			int locazioneXTendinaCategoriaProd = locazioneXLabelCategoriaProd + lunghezzaScrittaNomiCategoria;
			int larghezzaTendinaCategoria = 100;
			tendinaCategoriaProd = new JComboBox(CostantiClassiAlimentari.DescrCategorieAlimentari);
			tendinaCategoriaProd.setBounds(locazioneXTendinaCategoriaProd,locazioneYTerzaRiga,larghezzaTendinaCategoria, altezzaTendina);
			tendinaCategoriaProd.setSelectedIndex(0);
			tendinaCategoriaProd.setEnabled(false);
			
			
			//QUARTA RIGA
			int distanzaTraBottoni = 30;
			int altezzaBottoni = 30;
			int larghezzaBottoni = 150;	
			
			//Bottone "Successo"
			int locazioneXBottSuccesso = larghezzaPannelloAdmin2/40 +50 ;
	        bSuccesso = new JButton(new ImageIcon("Save/immagini/successo.gif"));
	        bSuccesso.setRolloverIcon(new ImageIcon("Save/immagini/successoR.gif"));
	        bSuccesso.setBounds(locazioneXBottSuccesso, locazioneYQuartaRiga, larghezzaBottoni, altezzaBottoni);
	        bSuccesso.setBorderPainted(false);
	        bSuccesso.setMargin (new Insets (0, 0, 0, 0));
	        bSuccesso.setContentAreaFilled(false);
	        bSuccesso.setOpaque(true);
	        bSuccesso.setToolTipText("Calcola Successo");
			
	        //Bottone "Gradiemnto"
	        int locazioneXBottGradim = locazioneXBottSuccesso + larghezzaBottoni + distanzaTraBottoni;
	        bGradimento = new JButton(new ImageIcon("Save/immagini/gradimento.gif"));
	        bGradimento.setRolloverIcon(new ImageIcon("Save/immagini/gradimentoR.gif"));
	        bGradimento.setBounds(locazioneXBottGradim, locazioneYQuartaRiga, larghezzaBottoni, altezzaBottoni);
	        bGradimento.setBorderPainted(false);
	        bGradimento.setMargin (new Insets (0, 0, 0, 0));
	        bGradimento.setContentAreaFilled(false);
	        bGradimento.setOpaque(true);
	        bGradimento.setToolTipText("Calcola Gradiemnto");
	       
	      //Bottone "Gradiemnto Medio Giornaliero"
			int locazioneXBottGradimMedio = locazioneXBottGradim + larghezzaBottoni + distanzaTraBottoni;
	        bGradimentoMedioGiornaliero = new JButton(new ImageIcon("Save/immagini/gradmedio.gif"));
	        bGradimentoMedioGiornaliero.setRolloverIcon(new ImageIcon("Save/immagini/gradmedioR.gif"));
	        bGradimentoMedioGiornaliero.setBounds(locazioneXBottGradimMedio, locazioneYQuartaRiga, 220, altezzaBottoni);
	        bGradimentoMedioGiornaliero.setBorderPainted(false);
	        bGradimentoMedioGiornaliero.setMargin (new Insets (0, 0, 0, 0));
	        bGradimentoMedioGiornaliero.setContentAreaFilled(false);
	        bGradimentoMedioGiornaliero.setOpaque(true);
	        bGradimentoMedioGiornaliero.setToolTipText("Calcola Gradiemnto Medio Giornaliero");
	        
	        int locazioneXBottDeperib = locazioneXBottGradimMedio+ 220 + distanzaTraBottoni;
	        bDeperibilita = new JButton(new ImageIcon("Save/immagini/deperibilita.gif"));
	        bDeperibilita.setRolloverIcon(new ImageIcon("Save/immagini/deperibilitaR.gif"));
	        bDeperibilita.setBounds(locazioneXBottDeperib, locazioneYQuartaRiga, larghezzaBottoni, altezzaBottoni);
	        bDeperibilita.setBorderPainted(false);
	        bDeperibilita.setMargin (new Insets (0, 0, 0, 0));
	        bDeperibilita.setContentAreaFilled(false);
	        bDeperibilita.setOpaque(true);
	        bDeperibilita.setToolTipText("Calcola Deperibilit�");
	        

			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");
							
			
			String line = "____________________________________________________________________________________________________________________________________________________";
			JLabel linea = new JLabel(line);
			linea.setBounds(0,locazioneYSecondaRiga+20, larghezzaPannelloAdmin2, altezzaScritta);
			
			JLabel linea2 = new JLabel(line);
			linea2.setBounds(0,locazioneYSecondaRiga-35, larghezzaPannelloAdmin2, altezzaScritta);

			//QUINTA RIGA
			int locazioneXValoreLabel = (larghezzaPannelloAdmin2)/3;
			int lunghezzaScrittaValore =	120;
			int altezzaScrittaValore = 30;
			valoreLabel 	 	= new JLabel("Valore Indice:");
			valoreLabel.setBounds(locazioneXValoreLabel,locazioneYQuintaRiga,lunghezzaScrittaValore, altezzaScrittaValore);
			valoreLabel.setFont(new Font("Verdana", 0, 12));
			valoreFUscita    	= new JTextField("1", 10); 
			int locazioneXValoreCampo = locazioneXValoreLabel + lunghezzaScrittaValore;
			valoreFUscita.setBounds(locazioneXValoreCampo,locazioneYQuintaRiga,lunghezzaScrittaValore, altezzaScrittaValore);
			valoreFUscita.setFont(new Font("Verdana", 0, 20));
			valoreFUscita.setEditable(false);
			valoreFUscita.setHorizontalAlignment(JLabel.CENTER);
	        
			
			//Aggiunta di tendine al Pannello Admin2
			pannelloAdmin2.add(labelSceltaCalcolo);
			
			pannelloAdmin2.add(metodoA);
			pannelloAdmin2.add(metodoB);
			
			pannelloAdmin2.add(radioProdotti);
			pannelloAdmin2.add(radioClassi);
			pannelloAdmin2.add(radioCategorie);
			pannelloAdmin2.add(radioTotali);
			
	        pannelloAdmin2.add(labelGestioneIndici);
	        pannelloAdmin2.add(classeProdotto);
	        pannelloAdmin2.add(nomiEsterni);
			pannelloAdmin2.add(nomiCategoriaProd);
			pannelloAdmin2.add(nomiClassiProd);
		
			pannelloAdmin2.add(tendinaNomiEsterni);
			pannelloAdmin2.add(tendinaCategoriaProd);
			pannelloAdmin2.add(tendinaClassiProd);
			
			//Aggiunta Bottoni
			pannelloAdmin2.add(bGradimento);
	        pannelloAdmin2.add(bSuccesso);
	        pannelloAdmin2.add(bDeperibilita);
	            
	        pannelloAdmin2.add(valoreLabel);
	        pannelloAdmin2.add(valoreFUscita);
	        pannelloAdmin2.add(linea);
	        pannelloAdmin2.add(linea2);
	        pannelloAdmin2.add(bGradimentoMedioGiornaliero);
	        
	        pannelloAdmin2.add(bChiudiPannello);
		
			// Ascoltatori di tendine e relative azioni			
			ascoltatoreEtAzioneTendinaCategoriaProd 	= new TendinaCategoriaProdAA();
			ascoltatoreEtAzioneTendinaNomiEsterni 		= new TendinaNomiEsterniAA();
			ascoltatoreEtAzioneTendinaClassiProd		= new TendinaClasseAlimentareProdAA();
						
			ascoltatoreEtAzioneMetodoA 					= new MetodoAAA();
			ascoltatoreEtAzioneMetodoB 					= new MetodoBAA();
			
			// Ascoltatori di bottoni e relative azioni
			ascoltatoreEtAzioneDeperibilita				= new DeperibilitaAA();
			ascoltatoreEtAzioneGradimento				= new GradimentoAA();
			ascoltatoreEtAzioneGradimentoMedioGiornaliero = new GradimentoMedioGiornalieroAA();
			ascoltatoreEtAzioneSuccesso					= new SuccessoAA();
			
			ascoltatoreEtAzioneRadioProdotti			= new RadioProdottiAA();
			ascoltatoreEtAzioneRadioClassi				= new RadioClassiAA();
			ascoltatoreEtAzioneRadioCategorie			= new RadioCategorieAA();
			ascoltatoreEtAzioneRadioTotali				= new RadioTotaliAA();
			
			ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
			
		    // Associazione di tendine ad ascoltatori
			tendinaCategoriaProd.addActionListener(ascoltatoreEtAzioneTendinaCategoriaProd);
			tendinaNomiEsterni.addActionListener(ascoltatoreEtAzioneTendinaNomiEsterni);
			tendinaClassiProd.addActionListener(ascoltatoreEtAzioneTendinaClassiProd);
			
			metodoA.addActionListener(ascoltatoreEtAzioneMetodoA);
			metodoB.addActionListener(ascoltatoreEtAzioneMetodoB);
			
			
			radioProdotti.addActionListener(ascoltatoreEtAzioneRadioProdotti);
			radioClassi.addActionListener(ascoltatoreEtAzioneRadioClassi);
			radioCategorie.addActionListener(ascoltatoreEtAzioneRadioCategorie);
			radioTotali.addActionListener(ascoltatoreEtAzioneRadioTotali);
			
		    // Associazione di bottoni ad ascoltatori
			bDeperibilita.addActionListener(ascoltatoreEtAzioneDeperibilita);
			bGradimento.addActionListener(ascoltatoreEtAzioneGradimento);
			bGradimentoMedioGiornaliero.addActionListener(ascoltatoreEtAzioneGradimentoMedioGiornaliero);
			bSuccesso.addActionListener(ascoltatoreEtAzioneSuccesso);	
			bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
		}
		// Fine costruttore
			
		
		private class ChiudiPannelloAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfineAministratoreAmministrazione.bGestioneUtenti.setEnabled(true);
				ConfineAministratoreAmministrazione.bGestioneProdotti.setEnabled(true);
				ConfineAministratoreAmministrazione.bGestioneIndici.setEnabled(true);
				ConfineAministratoreAmministrazione.bCambiaUtente.setEnabled(true);
				ConfineAministratoreAmministrazione.bSospendi.setEnabled(true);
				ConfineAministratoreAmministrazione.bLog.setEnabled(true);
				ConfineAministratoreAmministrazione.bMenu.setEnabled(true);
				pannelloAdmin2.setVisible(false);
			}
		}

		private class DeperibilitaAA implements ActionListener
			{
				public void actionPerformed(ActionEvent arg0)
				{
					if(radioProdotti.isSelected())
					{	
						System.out.println("RadioProdotti selezionato!");
						if (nomeEsternoCorrente.equals(""))
							JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
						else 
						{
							try
							{
								risultatoDeperibilita = new Double(controlloreCorrente.deperibilita(new IDEsterno(nomeEsternoCorrente), sceltaMetodo));
				
								valoreFUscita.setText(String.format("%.2f%% \n", risultatoDeperibilita*100).toString());		
							}
							catch(DirittiException e)
							{
								JOptionPane.showMessageDialog(null, " DeperibilitaAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
							}				
							catch(ProdottoException e)
							{
								JOptionPane.showMessageDialog(null, " DeperibilitaAA " + e.getMessage(), " Errore", JOptionPane.ERROR_MESSAGE);
							}				
							catch (IDEsternoException e)
							{
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
							catch (ArithmeticException e)
							{
								JOptionPane.showMessageDialog(null, e.getMessage());
							}
						}
					}
					if(radioClassi.isSelected())
					{
						System.out.println("RadioClassi selezionato!");
						try 
						{
							risultatoDeperibilita = controlloreCorrente.deperibilita(new IDEsterno(classeAlimentareFinaleCorrente), sceltaMetodo);
							valoreFUscita.setText(String.format("%.2f%% \n", risultatoDeperibilita*100).toString());
						} 
						catch (DirittiException e) {e.printStackTrace();} 
						catch (IDEsternoException e) {e.printStackTrace();}	
						catch (ProdottoException e) {e.printStackTrace();} 
						catch (ArithmeticException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
					if(radioCategorie.isSelected())
					{
						System.out.println("RadioCategorie selezionato!");
						try 
						{
							risultatoDeperibilita = controlloreCorrente.deperibilita(new IDEsterno(categoriaAlimentare), sceltaMetodo);
							valoreFUscita.setText(String.format("%.2f%% \n", risultatoDeperibilita*100).toString());
						} 
						catch (DirittiException e) {e.printStackTrace();} 
						catch (IDEsternoException e) {e.printStackTrace();}	
						catch (ProdottoException e) {e.printStackTrace();} 
						catch (ArithmeticException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
					if(radioTotali.isSelected())
					{
						System.out.println("RadioTotali selezionato!");
						try 
						{
							risultatoDeperibilita = controlloreCorrente.deperibilita(new IDEsterno("Portata"), sceltaMetodo);
							valoreFUscita.setText(String.format("%.2f%% \n", risultatoDeperibilita*100).toString());
						} 
						catch (DirittiException e) {e.printStackTrace();} 
						catch (IDEsternoException e) {e.printStackTrace();}	
						catch (ProdottoException e) {e.printStackTrace();} 
						catch (ArithmeticException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}					
				}
		}

		//CALCOLO GRADIMENTO DI UN CERTO PRODOTTO: Es. Ferrarelle.
		//Gradimento: Rapporto tra tutti gli Erogati di un Prodotto (Ferrarelle) e tutti gli Erogati della stessa Classe di Prodotto (Acqua)
		private class GradimentoAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(radioProdotti.isSelected())
				{
					System.out.println("RadioProdotti selezionato!");
					if (nomeEsternoCorrente.equals(""))
						JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
					else 
					{
						try
						{
							risultatoGradimento = new Double(controlloreCorrente.gradimento(new IDEsterno(nomeEsternoCorrente), sceltaMetodo));
			
							valoreFUscita.setText(String.format("%.2f%% \n", risultatoGradimento*100).toString());
						}
						catch(DirittiException e)
						{
							JOptionPane.showMessageDialog(null, " GradimentoAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
						}				
						catch(ProdottoException e)
						{
							JOptionPane.showMessageDialog(null, " GradimentoAA " + e.getMessage(), " Errore", JOptionPane.ERROR_MESSAGE);
						}				
						catch (IDEsternoException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						catch (ArithmeticException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				}
				if(radioClassi.isSelected())
				{
					System.out.println("RadioClassi selezionato!");
					try 
					{
						risultatoGradimento = controlloreCorrente.gradimento(new IDEsterno(classeAlimentareFinaleCorrente), sceltaMetodo);
						valoreFUscita.setText(String.format("%.2f%% \n", risultatoGradimento*100).toString());
					} 
					catch (DirittiException e) {e.printStackTrace();} 
					catch (SecurityException e) {e.printStackTrace();} 
					catch (IllegalArgumentException e){e.printStackTrace();}
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					} 
					catch (ProdottoException e) {e.printStackTrace();} 
					catch (IDEsternoException e) {e.printStackTrace();}	
				}
				if(radioCategorie.isSelected())
				{
					System.out.println("RadioCategorie selezionato!");
					try 
					{
						risultatoGradimento = controlloreCorrente.gradimento(new IDEsterno(categoriaAlimentare), sceltaMetodo);
						valoreFUscita.setText(String.format("%.2f%% \n", risultatoGradimento*100).toString());
					} 
					catch (DirittiException e) {e.printStackTrace();} 
					catch (SecurityException e) {e.printStackTrace();}
					catch (IllegalArgumentException e){e.printStackTrace();} 
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (ProdottoException e) {e.printStackTrace();}
					catch (IDEsternoException e) {e.printStackTrace();}
				}
				if(radioTotali.isSelected()) //Non serve.....Restituirebbe cmq 100%
				{
					System.out.println("RadioTotali selezionato!");
					try 
					{
						//risultatoGradimento = controlloreCorrente.gradimentoClasse(new IDEsterno("Portata"));
						valoreFUscita.setText(String.format("%.2f%% \n", 100.0).toString());
					} 
					catch (SecurityException e) {e.printStackTrace();} 
					catch (IllegalArgumentException e){e.printStackTrace();}
				}		
			}
		}
		
		private class GradimentoMedioGiornalieroAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if(radioProdotti.isSelected())
				{
					System.out.println("RadioProdotti selezionato!");
					if (nomeEsternoCorrente.equals(""))
						JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
					else 
					{
						try
						{
							risultatoGradimentoMedio = new Double(controlloreCorrente.gradimentoMedioGiornaliero(new IDEsterno(nomeEsternoCorrente)));
			
							valoreFUscita.setText(String.format("%.2f%% \n", risultatoGradimentoMedio*100).toString());	
						}
						catch(DirittiException e)
						{
							JOptionPane.showMessageDialog(null, " GradimentoAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
						}				
						catch(ProdottoException e)
						{
							JOptionPane.showMessageDialog(null, " GradimentoAA " + e.getMessage(), " Errore", JOptionPane.ERROR_MESSAGE);
						}				
						catch (IDEsternoException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
						catch (ArithmeticException e)
						{
							JOptionPane.showMessageDialog(null, e.getMessage());
						}
					}
				}
				if(radioClassi.isSelected())
				{
					System.out.println("RadioClassi selezionato!");
					
					try 
					{
						risultatoGradimentoMedio = controlloreCorrente.gradimentoMedioGiornalieroClasse(classeAlimentareFinaleCorrente);
					} 
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (DirittiException e) {e.printStackTrace();} 
					catch (ProdottoException e) {e.printStackTrace();}
					catch (IDEsternoException e) {e.printStackTrace();}
					
					valoreFUscita.setText(String.format("%.2f%% \n", risultatoGradimentoMedio*100).toString());		
				}
				if(radioCategorie.isSelected())
				{
					System.out.println("RadioCategorie selezionato!");
					try 
					{
						risultatoGradimentoMedio = controlloreCorrente.gradimentoMedioGiornalieroCategoria(categoriaAlimentare);
					} 
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (DirittiException e) {e.printStackTrace();} 
					catch (ProdottoException e) {e.printStackTrace();}
					catch (IDEsternoException e) {e.printStackTrace();}
					
					valoreFUscita.setText(String.format("%.2f%% \n", risultatoGradimentoMedio*100).toString());		
				}
				if(radioTotali.isSelected()) //100%
				{
					System.out.println("RadioTotali selezionato!");
					valoreFUscita.setText(String.format("%.2f%% \n", 100.0).toString());	
				}				
			}
		}
		
		//CALCOLO SUCCESSO DI UN CERTO PRODOTTO: Es. Ferrarelle.	
		private class SuccessoAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{	
				if(radioProdotti.isSelected())
				{
					System.out.println("RadioProdotti selezionato!");
					if (nomeEsternoCorrente.equals(""))
						JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
					else 
					{
						try
						{
							risultatoSuccesso = new Double(controlloreCorrente.successo(new IDEsterno(nomeEsternoCorrente), sceltaMetodo));
							valoreFUscita.setText(String.format("%.2f%% \n", risultatoSuccesso*100).toString());	
						}
						catch(DirittiException e)
						{
							JOptionPane.showMessageDialog(null, " SuccessoAA " + e.getMessage(), " non autorizzato", JOptionPane.ERROR_MESSAGE);
						}				
						catch(ProdottoException e)
						{
							JOptionPane.showMessageDialog(null, " SuccessoAA " + e.getMessage(), " Errore", JOptionPane.ERROR_MESSAGE);
						}				
						catch (IDEsternoException e){JOptionPane.showMessageDialog(null, e.getMessage());}
						catch (ArithmeticException e){JOptionPane.showMessageDialog(null, e.getMessage());} 
						catch (SecurityException e) {e.printStackTrace();}
						catch (IllegalArgumentException e) {e.printStackTrace();} 
					}
				}
				if(radioClassi.isSelected())
				{
					System.out.println("RadioClassi selezionato!");
					try 
					{
						risultatoSuccesso = controlloreCorrente.successo(new IDEsterno(classeAlimentareFinaleCorrente), sceltaMetodo);
						valoreFUscita.setText(String.format("%.2f%% \n", risultatoSuccesso*100).toString());
					} 
					catch (DirittiException e) {e.printStackTrace();} 
					catch (SecurityException e) {e.printStackTrace();} 
					catch (IllegalArgumentException e){e.printStackTrace();}
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (ProdottoException e) {e.printStackTrace();}
					catch (IDEsternoException e) {e.printStackTrace();}
				}
				if(radioCategorie.isSelected())
				{
					System.out.println("RadioCategorie selezionato!");
					try 
					{
						risultatoSuccesso = controlloreCorrente.successo(new IDEsterno(categoriaAlimentare), sceltaMetodo);
						valoreFUscita.setText(String.format("%.2f%% \n", risultatoSuccesso*100).toString());
					} 
					catch (DirittiException e) {e.printStackTrace();} 
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (ProdottoException e) {e.printStackTrace();}
					catch (IDEsternoException e) {e.printStackTrace();}
				}
				if(radioTotali.isSelected())
				{
					System.out.println("RadioTotali selezionato!");
					try 
					{
						risultatoSuccesso = controlloreCorrente.successo(new IDEsterno("Portata"), sceltaMetodo);
						valoreFUscita.setText(String.format("%.2f%% \n", risultatoSuccesso*100).toString());
					} 
					catch (DirittiException e) {e.printStackTrace();} 
					catch (SecurityException e) {e.printStackTrace();} 
					catch (IllegalArgumentException e){e.printStackTrace();}
					catch (ArithmeticException e)
					{
						JOptionPane.showMessageDialog(null, e.getMessage());
					}
					catch (ProdottoException e) {e.printStackTrace();}
					catch (IDEsternoException e) {e.printStackTrace();}
				}	
			}
		}
		private class TendinaNomiEsterniAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		        JComboBox cb = (JComboBox)e.getSource();
		        
		        //MDM - Leggo il nome con package della classe ...
		        //nomeEsternoCorrente = (String)cb.getSelectedItem();
		        nomeEsternoCorrente = CostantiClassiAlimentari.getClasseName((String)cb.getSelectedItem());
		        
		        String nomeClasse ="null";
		        try
		        {
					Portata p = controlloreCorrente.getClassProdotto(nomeEsternoCorrente);
					//MDM - Visualizzo solo il nome della classe senza il packaga ...
					//nomeClasse = "("+p.getClass().getName()+")";
					nomeClasse = "("+p.getClass().getSimpleName()+")";
					classeProdotto.setText(nomeClasse);					
				} 
		        catch (IDEsternoException e1) {e1.printStackTrace();}
		        
			}
		}	
		
		private class TendinaClasseAlimentareProdAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	JComboBox cb = (JComboBox)e.getSource();
		        
		    	//MDM - Leggo il nome con package della classe ...
		        //classeAlimentareFinaleCorrente = (String)cb.getSelectedItem();
		    	classeAlimentareFinaleCorrente = CostantiClassiAlimentari.getClasseName((String)cb.getSelectedItem());

		    	System.out.println("Classe: "+ classeAlimentareFinaleCorrente);
		    }
		}	
		private class TendinaCategoriaProdAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	JComboBox cb = (JComboBox)e.getSource();
		    	//MDM - Leggo il nome con package della classe ...
		        categoriaAlimentare = CostantiClassiAlimentari.getClasseName((String)cb.getSelectedItem());
		    }
		}	
		
		
		private class MetodoAAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	sceltaMetodo = "A";
			}
		}	
		
		private class MetodoBAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	sceltaMetodo = "B";
			}
		}	
		
		private class RadioProdottiAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	nomiEsterni.setEnabled(true);
		    	classeProdotto.setEnabled(true);
				tendinaNomiEsterni.setEnabled(true);
				nomiClassiProd.setEnabled(false);
				tendinaClassiProd.setEnabled(false);
				nomiCategoriaProd.setEnabled(false);
				tendinaCategoriaProd.setEnabled(false);	
			}
		}	
		
		private class RadioClassiAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	nomiEsterni.setEnabled(false);
		    	classeProdotto.setEnabled(false);
				tendinaNomiEsterni.setEnabled(false);
				nomiClassiProd.setEnabled(true);
				tendinaClassiProd.setEnabled(true);
				nomiCategoriaProd.setEnabled(false);
				tendinaCategoriaProd.setEnabled(false);	
			}
		}	
		
		private class RadioCategorieAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	nomiEsterni.setEnabled(false);
		    	classeProdotto.setEnabled(false);
				tendinaNomiEsterni.setEnabled(false);
				nomiClassiProd.setEnabled(false);
				tendinaClassiProd.setEnabled(false);
				nomiCategoriaProd.setEnabled(true);
				tendinaCategoriaProd.setEnabled(true);	
			}
		}	
		
		private class RadioTotaliAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent e) 
		    {
		    	nomiEsterni.setEnabled(false);
		    	classeProdotto.setEnabled(false);
				tendinaNomiEsterni.setEnabled(false);
				nomiCategoriaProd.setEnabled(false);
				tendinaCategoriaProd.setEnabled(false);	
			}
		}	
			
	}
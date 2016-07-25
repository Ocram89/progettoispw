package boundary;

//VERSIONE INCOMPLETA SU IMPIEGO DI REFLECTION E BOUNDARY CON INTERFACCE GRAFICHE, COMPRENSIVE DI COMBOBOX.


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
import javax.swing.JComboBox;
//	import javax.swing.JFrame;
	import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//	import java.util.*;
import javax.swing.JTextField;

//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;

	//http://download.oracle.com/javase/tutorial/uiswing/examples/components/index.html#ComboBoxDemo
	
	public class ConfineAministratoreAmministrazioneProdottiRinnovoProdottiRimozione extends StackFrame
	{

		//Draft
		private static final long	serialVersionUID	= 1L;
		
		//private StackFrame questoConfine;
		private Controllore controlloreCorrente;

				
		//Pannelli
//		public JPanel 		pannello;
		
		public JPanel 		pannelloAdmin4 = new JPanel();

//		public JPanel  		pannelloSalva;
		
		public JLabel 		labelRimuoviProdotti;
		public JLabel		labelTendinaProdotti;
		public JLabel 		labelTendinaClassiAlimFin;
		
		public JTextField 	valoreGiacenzaProdotti;
		public JTextField 	valoreGiacenzaClasseAlim;
		
		// Tendine
		public JComboBox tendinaClassiAlimentariFinali; //Per ora non usato
		public JComboBox tendinaNomiEsterni;
		
		
		//Bottoni/
//		public JButton 		bDistruggiMg; 
//		public JButton 		bDescrivi;
		
		public JButton 		bGiacenzaProdotto;
		public JButton 		bEliminaProdotto;
		
		public JButton 		bGiacenzaClassiAlim; //Credo
		public JButton 		bEliminaClasseAlim;	 //Credo
		
		
		public JButton 		bGiacenza; //Solo se zero, il prodotto e' eliminabile
		public JButton 		bRitorna; 
		public JButton 		bSospendi; 	
		
		public JButton    	bChiudiPannello;
		
//		public JButton bSerializza;
//		public JButton bDeserializza;
//		public JButton bSalvaFile;
		
// Campi e loro etichette	
//		public JLabel     	descrizioneLabel;
//		public JTextField 	descrizione; 
/*		public JLabel     gScadenzaLabel;
		public JTextField gScadenzaText; 
		public JLabel     mScadenzaLabel;
		public JTextField mScadenzaText; 
		public JLabel     aScadenzaLabel;
		public JTextField aScadenzaText; 
*/
		
		// Ascoltatori di bottoni e relative azioni
//		private SospendiAA 			ascoltatoreEtAzioneSospendi;
//		private CreaMgAA 			ascoltatoreEtAzioneCreaMg;
//		private DescriviAA 			ascoltatoreEtAzioneDescrivi;
//		private DeserializzaAA 		ascoltatoreEtAzioneDeserializza;
		private EliminaProdottoAA 		ascoltatoreEtAzioneEliminaProdotto;
		private GiacenzaProdottiAA 		ascoltatoreEtAzioneGiacenzaProdotti;
		//private GiacenzaClasseAlimAA 	ascoltatoreEtAzioneGiacenzaClasseAlim;  //CREDO??
//		private LogGiacenzaAA 		ascoltatoreEtAzioneLogGiacenza;
//		private RitornaAA 			ascoltatoreEtAzioneRitorna;
//		private SalvaFileAA 		ascoltatoreEtAzioneSalvaFile;
//		private SerializzaAA 		ascoltatoreEtAzioneSerializza;
		
		private ChiudiPannelloAA			ascoltatoreChiudiPannello;
		
		//Ascoltatore tendina
		//private TendinaClassiAlimentariFinaliCorrentiAA   	ascoltatoreEtAzioneTendinaClassiAlimentari;
		private TendinaNomiEsterniAA   						ascoltatoreEtAzioneTendinaNomiEsterni;



		//Per ora classeAlimentareFinaleCorrente non e' usata. Potra' servire come preselettore di nomi esterni
        //private String 	classeAlimentareFinaleCorrente;
		private String 	nomeEsternoCorrente;

		int altezzaPannelloAdmin4;
		int larghezzaPannelloAdmin4;
		int locazioneYPrimaRiga;
		//int locazioneYSecondaRiga;
		//StackFrame framePrecedente;
		int locazioneYPannelloAdmin4;
		
		public ConfineAministratoreAmministrazioneProdottiRinnovoProdottiRimozione (Controllore controllore, int locazioneYPannelloAdmin4)
		{
			this.locazioneYPannelloAdmin4 = locazioneYPannelloAdmin4;
			//framePrecedente = confineMittente;
			
			//StackFrame.pilaCornici.push(confineMittente);
			/*questoConfine = this;
			questoConfine.setTitle("Amministrazione Prodotti" + CostantiDiInstallazione.NOME_CLIENTE);*/
			this.controlloreCorrente=controllore;
			//classeAlimentareFinaleCorrente=CostantiClassiAlimentari.ClasseAlimentareFinale[0]; //ALTRO
			nomeEsternoCorrente= (String) controlloreCorrente.keySet().toArray()[0];
		
			//pannello = new JPanel();
			
			int altezzaConfine = ConfineAvvio.Confine.getHeight();
			
			ConfineAvvio.Confine.add(pannelloAdmin4);
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin4.setLayout(null);
			pannelloAdmin4.setVisible(true);
			
			locazioneYPannelloAdmin4 += 5;
			altezzaPannelloAdmin4 = altezzaConfine - locazioneYPannelloAdmin4 -38;
			larghezzaPannelloAdmin4= ConfineAvvio.Confine.getWidth();
			
			//int altezzaPannelliAdmin = ConfineAvvio.Confine.getHeight()/4;
			//int altezzaPannelloAmmProd = ConfineAvvio.Confine.getHeight()/6;
			//int altezzaPannelloAggiuntaProd = ConfineAvvio.Confine.getHeight()/3;
			//int locazionePannello = altezzaPannelliAdmin+altezzaPannelloAmmProd*2 +5 +5;
			//int larghezzaPannello= ConfineAvvio.Confine.getWidth();
			int lunghezzaScritta=220;
			int altezzaScritta = 40;
			
			pannelloAdmin4.setBounds(0,locazioneYPannelloAdmin4,ConfineAvvio.Confine.getWidth(), altezzaPannelloAdmin4);
			pannelloAdmin4.setBackground(new Color(190,190,190));
			
			altezzaPannelloAdmin4 = pannelloAdmin4.getHeight(); //Questo deve stare qui (cioÃ¨ dopo aver impostato il size di pannelloAdmin4)

			locazioneYPrimaRiga =	(altezzaPannelloAdmin4)*4/10;
			//locazioneYSecondaRiga = (altezzaPannelloAdmin4*2)/3;
			
			
			//Titolo
			labelRimuoviProdotti = new JLabel("Rimozione Prodotti");
			labelRimuoviProdotti.setBounds((pannelloAdmin4.getWidth()/3)+50,10, lunghezzaScritta, altezzaScritta);
			labelRimuoviProdotti.setFont(new Font("Verdana", Font.BOLD, 20));

			//Scritte e Tendine
			
			
			
			int larghezzaBottoni = pannelloAdmin4.getWidth()/5;	
			int altezzaBottoni = 40;
			
			
			creaScritteETendine();
			
			int locazioneXBottGiacenza = larghezzaPannelloAdmin4/2-100;
			
			/*
			int locazioneXBottGiacenza = larghezzaPannelloAdmin4/2-60;
			bGiacenzaProdotto 			= new JButton("Giacenza Prodotto:");	
			//bGiacenzaProdotto.setFont(new Font("Arial",1,12));
			bGiacenzaProdotto.setBounds(locazioneXBottGiacenza,locazioneYPrimaRiga-10,larghezzaBottoni,altezzaBottoni);
			*/
			//Bottone "Giacenza"
			bGiacenzaProdotto = new JButton(new ImageIcon("Save/immagini/giacenza.gif"));
			bGiacenzaProdotto.setRolloverIcon(new ImageIcon("Save/immagini/giacenzaR.gif"));
			bGiacenzaProdotto.setBounds(locazioneXBottGiacenza, (locazioneYPrimaRiga-10), 200, 40);
			bGiacenzaProdotto.setBorderPainted(false);
			bGiacenzaProdotto.setMargin (new Insets (0, 0, 0, 0));
			bGiacenzaProdotto.setContentAreaFilled(false);
			bGiacenzaProdotto.setOpaque(true);
			bGiacenzaProdotto.setToolTipText("Verifica Giacenza Prodotto");
			
			valoreGiacenzaProdotti    	= new JTextField("", 10);  
			valoreGiacenzaProdotti.setBounds(locazioneXBottGiacenza + 210,locazioneYPrimaRiga-10,80, 40);
			valoreGiacenzaProdotti.setFont(new Font("Verdana", 1, 12));
			valoreGiacenzaProdotti.setEditable(false);
			
			//bEliminaProdotto 			= new JButton("Elimina Prodotto!");		
			//bEliminaProdotto.setBounds(locazioneXBottGiacenza+larghezzaBottoni+20+110,locazioneYPrimaRiga-10,larghezzaBottoni,altezzaBottoni);
			
			//Bottone "Elimina"
			bEliminaProdotto = new JButton(new ImageIcon("Save/immagini/elimina.gif"));
			bEliminaProdotto.setRolloverIcon(new ImageIcon("Save/immagini/eliminaR.gif"));
			bEliminaProdotto.setBounds(locazioneXBottGiacenza +320, (locazioneYPrimaRiga-10), 200, 40);
			bEliminaProdotto.setBorderPainted(false);
			bEliminaProdotto.setMargin (new Insets (0, 0, 0, 0));
			bEliminaProdotto.setContentAreaFilled(false);
			bEliminaProdotto.setOpaque(true);
			bEliminaProdotto.setToolTipText("Elimina Prodotto");
			
			/*bGiacenzaClassiAlim			= new JButton("Giacenza Classe:");		
			bGiacenzaClassiAlim.setBounds(locazioneXBottGiacenza,locazioneYSecondaRiga-10,larghezzaBottoni,altezzaBottoni);
			
			valoreGiacenzaClasseAlim    	= new JTextField("", 10);  
			valoreGiacenzaClasseAlim.setBounds(locazioneXBottGiacenza + larghezzaBottoni+2,locazioneYPrimaRiga-10,100, altezzaBottoni);
			valoreGiacenzaClasseAlim.setFont(new Font("Arial", 1, 15));
			valoreGiacenzaClasseAlim.setEditable(false);
			
			bEliminaClasseAlim 			= new JButton("Elimina Classe??");		
			bEliminaClasseAlim.setBounds(locazioneXBottGiacenza+larghezzaBottoni+20+110,locazioneYSecondaRiga-10,larghezzaBottoni,altezzaBottoni);
			*/
			
			/*
			bChiudiPannello = new JButton("X");
			//bChiudiPannello.setFont(new Font("Arial", 0, 6));
			bChiudiPannello.setLocation(larghezzaPannelloAdmin4 -70 ,5);
			bChiudiPannello.setSize(45, 20);
			bChiudiPannello.setBackground(Color.red);
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
			
			pannelloAdmin4.add(labelRimuoviProdotti);

			pannelloAdmin4.add(labelTendinaProdotti);
			//pannelloAdmin4.add(labelTendinaClassiAlimFin);
			
			pannelloAdmin4.add(tendinaNomiEsterni);
			//pannelloAdmin4.add(tendinaClassiAlimentariFinali);
			pannelloAdmin4.add(tendinaNomiEsterni);
			
			pannelloAdmin4.add(bGiacenzaProdotto);
			pannelloAdmin4.add(valoreGiacenzaProdotti);
			pannelloAdmin4.add(bEliminaProdotto);
			//pannelloAdmin4.add(bGiacenzaClassiAlim);
			//pannelloAdmin4.add(bEliminaClasseAlim);
			
			//pannelloAdmin4.add(tendinaClassiAlimentariFinali);
			pannelloAdmin4.add(bChiudiPannello);
			
			
			//ASCOLTATORI
			ascoltatoreChiudiPannello = new ChiudiPannelloAA();
			//ascoltatoreEtAzioneNuovoProdotto			= new NuovoProdottoAA();
			//ascoltatoreEtAzioneTendinaClassiAlimentari 	= new TendinaClassiAlimentariFinaliCorrentiAA();
			ascoltatoreEtAzioneTendinaNomiEsterni 		= new TendinaNomiEsterniAA();
			ascoltatoreEtAzioneGiacenzaProdotti			= new GiacenzaProdottiAA();
			//ascoltatoreEtAzioneGiacenzaClasseAlim		= new GiacenzaClasseAlimAA();
			ascoltatoreEtAzioneEliminaProdotto			= new EliminaProdottoAA();
			
			//ASSOCIAZIONI
			bChiudiPannello.addActionListener(ascoltatoreChiudiPannello);
			//bNuovoProdotto.addActionListener(ascoltatoreEtAzioneNuovoProdotto);
			//tendinaClassiAlimentariFinali.addActionListener(ascoltatoreEtAzioneTendinaClassiAlimentari);
			tendinaNomiEsterni.addActionListener(ascoltatoreEtAzioneTendinaNomiEsterni);
			bGiacenzaProdotto.addActionListener(ascoltatoreEtAzioneGiacenzaProdotti);
			//bGiacenzaClassiAlim.addActionListener(ascoltatoreEtAzioneGiacenzaClasseAlim);
			bEliminaProdotto.addActionListener(ascoltatoreEtAzioneEliminaProdotto);
			
			
			
		}// Fine costruttore
			
		private void creaScritteETendine()
		{
			
			//int bordoScritta = 30;
			int lunghezzaScritta = 120;
			int altezzaScritta= 20;
			int larghezzaTendina = 180;
			int altezzaTendina = 20;
			
			int locazioneXlabelTendina = larghezzaPannelloAdmin4/40;
			labelTendinaProdotti = new JLabel("Scegli il prodotto:");
			labelTendinaProdotti.setFont(new Font("Verdana",0,12));
			labelTendinaProdotti.setBounds(locazioneXlabelTendina,locazioneYPrimaRiga,lunghezzaScritta, altezzaScritta);
			
			/*labelTendinaClassiAlimFin = new JLabel("Scegli la Classe Alimentare:");
			labelTendinaClassiAlimFin.setFont(new Font("Arial",0,16));
			labelTendinaClassiAlimFin.setBounds(locazioneXlabelTendina,(locazioneYSecondaRiga),lunghezzaScritta, altezzaScritta);
			//int bordoTendina = 30;
			*/
			
			if (controlloreCorrente.keySet()==null)
				JOptionPane.showMessageDialog(null, " Il magazzino è vuoto. ");
			else 
			{
				
				tendinaNomiEsterni = new JComboBox(controlloreCorrente.keySet().toArray());
				tendinaNomiEsterni.setSelectedIndex(0); //PRIMO NOME
				tendinaNomiEsterni.setBounds(locazioneXlabelTendina+lunghezzaScritta,locazioneYPrimaRiga,larghezzaTendina, altezzaTendina);
				
				/*tendinaClassiAlimentariFinali = new JComboBox(CostantiClassiAlimentari.ClasseAlimentareFinale);
				tendinaClassiAlimentariFinali.setSelectedIndex(0); //ALTRO
				tendinaClassiAlimentariFinali.setBounds(locazioneXlabelTendina+lunghezzaScritta,locazioneYSecondaRiga,larghezzaTendina, altezzaTendina);
				*///descrizioneLabel 	= new JLabel("Descrizione");
				//descrizione 		= new JTextField("", 10);  
			}
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
						pannelloAdmin4.setVisible(false);
						new ConfineAministratoreAmministrazioneProdottiRinnovoProdottiRimozione(controlloreCorrente, locazioneYPannelloAdmin4);
						
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


		
		
		/*private class GiacenzaClasseAlimAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				if (nomeEsternoCorrente.equals(""))
					JOptionPane.showMessageDialog(null, " ID Esterno nullo. ");
				else 
				try
				{
					valoreGiacenzaClasseAlim.setText(labelPrefix + controlloreCorrente.giacenza(new IDEsterno(nomeEsternoCorrente)));
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
		}*/

		
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

		private class ChiudiPannelloAA implements ActionListener
		{
				public void actionPerformed(ActionEvent arg0)
				{
				
					ConfineAministratoreAmministrazioneProdottiRinnovoProdotti.bAggiuntaProdotti.setEnabled(true);
					ConfineAministratoreAmministrazioneProdottiRinnovoProdotti.bEliminazioneProdotti.setEnabled(true);
					ConfineAministratoreAmministrazioneProdottiRinnovoProdotti.bChiudiPannello.setEnabled(true);
					pannelloAdmin4.setVisible(false);
					ConfineAvvio.Confine.remove(pannelloAdmin4);
				}
		}
		
		/*public class TendinaClassiAlimentariFinaliCorrentiAA implements ActionListener { 
			    public void actionPerformed(ActionEvent e) {
			        JComboBox cb = (JComboBox)e.getSource();
			        classeAlimentareFinaleCorrente = (String)cb.getSelectedItem();
			    }
			}	
		*/

		
		public class TendinaNomiEsterniAA implements ActionListener { 
			    public void actionPerformed(ActionEvent e) {
			        JComboBox cb = (JComboBox)e.getSource();
			        nomeEsternoCorrente = (String)cb.getSelectedItem();
					valoreGiacenzaProdotti.setText("");;
			    }
			}	
	
	
	
}


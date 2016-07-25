package boundary;

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
//	import javax.swing.JComboBox;
//	import javax.swing.JFrame;
	import javax.swing.JLabel;
//	import javax.swing.JOptionPane;
import javax.swing.JPanel;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//	import javax.swing.JTextField;
//	import java.util.*;

//my -----------
import util.*;
import exception.*;
import control.*;
import entity.*;

	public class ConfineAministratoreAmministrazioneProdottiRinnovoProdotti extends StackFrame
	{
		private static final long	serialVersionUID	= 1L;
		
		private Controllore controlloreCorrente;
				
		//Pannelli
		public JPanel     	pannelloAdmin3 = new JPanel();
		
		public JLabel 		labelRinnovoProdotti;
		
		public static JButton 		bAggiuntaProdotti;
		public static JButton 		bEliminazioneProdotti;
		public static JButton    	bChiudiPannello;
		
		int altezzaProssimoPannello;
		
		// Ascoltatori di bottoni e relative azioni
		private AggiuntaProdottiAA 			ascoltatoreEtAzioneAggiuntaProdotti;
		private EliminazioneProdottiAA 		ascoltatoreEtAzioneEliminazioneProdotti;
		private ChiudiPannelloAA			ascoltatoreChiudiPannello;
		
		//Ascoltatore tendina
		int altezzaConfine;
		int altezzaPannelloAdmin3;
		int larghezzaPannelloAdmin3;
		
		int larghezzaBottoni;
		int altezzaBottoni;
			
		public ConfineAministratoreAmministrazioneProdottiRinnovoProdotti (Controllore controllore, int locazioneYPannelloAdmin3)
		{			
			ConfineAvvio.Confine.add(pannelloAdmin3);
			
			this.controlloreCorrente=controllore;
		
			//pannello = new JPanel();
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			locazioneYPannelloAdmin3 += 5;
						
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin3.setLayout(null);
								
			larghezzaPannelloAdmin3= ConfineAvvio.Confine.getWidth();
			
			
			//int locazioneYPannelloAdmin3 = locazioneYPannelloAdmin2+altezzaPannelloAdmin2 +5;
			altezzaPannelloAdmin3 = altezzaConfine/6;
			altezzaProssimoPannello = locazioneYPannelloAdmin3+altezzaPannelloAdmin3;
			
	        pannelloAdmin3.setBounds(0,locazioneYPannelloAdmin3,larghezzaPannelloAdmin3, altezzaPannelloAdmin3);
			pannelloAdmin3.setBackground(new Color(190,190,190));
			//int altezzaPannelloAdmin3 = pannelloAdmin3.getHeight(); //Questo deve stare qui (cioè dopo aver impostato il size di pannelloAdmin3)
			
					
			labelRinnovoProdotti = new JLabel("Rinnovo Prodotti");
			labelRinnovoProdotti.setBounds((larghezzaPannelloAdmin3/3)+60,10, 200, 30);
			labelRinnovoProdotti.setFont(new Font("Verdana", Font.BOLD, 20));
			
			//larghezzaBottoni = pannelloAdmin3.getWidth()/4;
			larghezzaBottoni = 200;
			altezzaBottoni = 40;
			/*
			bAggiuntaProdotti 	= new JButton("Aggiunta Prodotti"); 
			bAggiuntaProdotti.setLocation(larghezzaPannelloAdmin3/4 -60,(altezzaPannelloAdmin3/2));
			bAggiuntaProdotti.setSize(larghezzaBottoni, altezzaBottoni);
			
			bEliminazioneProdotti 	= new JButton("Eliminazione Prodotti"); 
			bEliminazioneProdotti.setLocation(larghezzaPannelloAdmin3/2 +30,(altezzaPannelloAdmin3/2));
			bEliminazioneProdotti.setSize(larghezzaBottoni, altezzaBottoni);		
			
			bChiudiPannello = new JButton("X");
			//bChiudiPannello.setFont(new Font("Arial", 0, 6));
			bChiudiPannello.setLocation(larghezzaPannelloAdmin3 -70 ,5);
			bChiudiPannello.setSize(45, 20);
			bChiudiPannello.setBackground(Color.red);
			
			*/

			//Bottone Aggiunta Nuovi Prodotti
			bAggiuntaProdotti = new JButton(new ImageIcon("Save/immagini/bottone_aggiungi_prodotto.gif"));
			bAggiuntaProdotti.setRolloverIcon(new ImageIcon("Save/immagini/bottone_aggiungi_prodottoR.gif"));
			bAggiuntaProdotti.setBorderPainted(false);
			bAggiuntaProdotti.setMargin (new Insets (0, 0, 0, 0));
			bAggiuntaProdotti.setContentAreaFilled(false);
			bAggiuntaProdotti.setOpaque(true);
			bAggiuntaProdotti.setToolTipText("Gestione Aggiunta Nuovi Prodotti");
			bAggiuntaProdotti.setBounds(larghezzaPannelloAdmin3/4 -60,(altezzaPannelloAdmin3/2), larghezzaBottoni, altezzaBottoni);
			
			
			//Bottone Rimozione Prodotti
			bEliminazioneProdotti = new JButton(new ImageIcon("Save/immagini/bottone_rimuovi_prodotto.gif"));
			bEliminazioneProdotti.setRolloverIcon(new ImageIcon("Save/immagini/bottone_rimuovi_prodottoR.gif"));
			bEliminazioneProdotti.setBorderPainted(false);
			bEliminazioneProdotti.setMargin (new Insets (0, 0, 0, 0));
			bEliminazioneProdotti.setContentAreaFilled(false);
			bEliminazioneProdotti.setOpaque(true);
			bEliminazioneProdotti.setToolTipText("Informazioni e Rimozione Prodotti");
			bEliminazioneProdotti.setBounds(larghezzaPannelloAdmin3/2 +30,(altezzaPannelloAdmin3/2), larghezzaBottoni, altezzaBottoni);
			
			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");

			pannelloAdmin3.add(labelRinnovoProdotti);
			pannelloAdmin3.add(bAggiuntaProdotti);
			pannelloAdmin3.add(bEliminazioneProdotti);
			pannelloAdmin3.add(bChiudiPannello);
			
			
			ascoltatoreEtAzioneEliminazioneProdotti		= new EliminazioneProdottiAA();
			ascoltatoreEtAzioneAggiuntaProdotti			= new AggiuntaProdottiAA();
			ascoltatoreChiudiPannello = new ChiudiPannelloAA();
			
			bEliminazioneProdotti.addActionListener(ascoltatoreEtAzioneEliminazioneProdotti);	
			bChiudiPannello.addActionListener(ascoltatoreChiudiPannello);
			bAggiuntaProdotti.addActionListener(ascoltatoreEtAzioneAggiuntaProdotti);
			
		}// Fine costruttore
			
		private class AggiuntaProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//questoConfine.setVisible(false);	
				bAggiuntaProdotti.setEnabled(false);
				bEliminazioneProdotti.setEnabled(false);
				bChiudiPannello.setEnabled(false);
				
				//Importante per freccetta tendina
				pannelloAdmin3.setVisible(false);
				pannelloAdmin3.setVisible(true);
				new ConfineAministratoreAmministrazioneProdottiRinnovoProdottiAggiunta(controlloreCorrente, altezzaProssimoPannello);
			}
		}

		private class EliminazioneProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				bAggiuntaProdotti.setEnabled(false);
				bEliminazioneProdotti.setEnabled(false);
				bChiudiPannello.setEnabled(false);
				
				//Importante per freccetta tendina
				pannelloAdmin3.setVisible(false);
				pannelloAdmin3.setVisible(true);
				//questoConfine.setVisible(false);			
				new ConfineAministratoreAmministrazioneProdottiRinnovoProdottiRimozione(controlloreCorrente, altezzaProssimoPannello);
			}
		}

		private class ChiudiPannelloAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfineAministratoreAmministrazioneProdotti.bRinnovoProdotti.setEnabled(true);
				ConfineAministratoreAmministrazioneProdotti.bErogazioneFornitura.setEnabled(true);
				ConfineAministratoreAmministrazioneProdotti.bChiudiPannello.setEnabled(true);
				pannelloAdmin3.setVisible(false);
				ConfineAvvio.Confine.remove(pannelloAdmin3);
			}
		}
}
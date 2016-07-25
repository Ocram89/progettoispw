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


	public class ConfineAministratoreAmministrazioneProdotti extends StackFrame
	{
		private static final long	serialVersionUID	= 1L;
		
		private StackFrame questoConfine;
		private Controllore controlloreCorrente;
				
		//Pannelli				
		public JPanel     	pannelloAdmin2 = new JPanel();
		
		public JLabel		labelGestioneProdotti;
		
		//Bottoni/
		public static JButton 		bErogazioneFornitura; 
		public static JButton 		bRinnovoProdotti;
		public static JButton 		bChiudiPannello;
		
		public JButton bProva;

		// Ascoltatori di bottoni e relative azioni
		private RinnovoProdottiAA 		ascoltatoreEtAzioneRinnovoProdotti;
		private ErogazioneFornituraAA 	ascoltatoreEtAzioneErogazioneFornitura;
		private ChiudiPannelloAA 		ascoltatoreEtAzioneChiudiPannello;
		
		int altezzaPannelloAdmin2;
		int larghezzaPannelloAdmin2;
		
		int altezzaProssimoPannello;
		
		public ConfineAministratoreAmministrazioneProdotti(Controllore controllore, int locazioneYPannelloAdmin2)
		{
			pannelloAdmin2.setVisible(true);
			ConfineAvvio.Confine.add(pannelloAdmin2);
			
			this.controlloreCorrente=controllore;		
			
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin2.setLayout(null);
						
			//int locazioneYPannelloAdmin2 = ConfineAvvio.Confine.getHeight()/5 - 5;
			locazioneYPannelloAdmin2 += 5;
			altezzaPannelloAdmin2 = ConfineAvvio.Confine.getHeight()/6;
			larghezzaPannelloAdmin2 = ConfineAvvio.Confine.getWidth();
			altezzaProssimoPannello = locazioneYPannelloAdmin2+altezzaPannelloAdmin2;
			pannelloAdmin2.setBounds(0,locazioneYPannelloAdmin2,larghezzaPannelloAdmin2, altezzaPannelloAdmin2);
			pannelloAdmin2.setBackground(new Color(190,190,190));
				        
			//int larghezzaBottoni = larghezzaPannelloAdmin2/4;			
			//int bordoBottoni = 30;
			int altezzaBottoni = 40;
			int larghezzaBottoni = 200;
						
			labelGestioneProdotti = new JLabel("Gestione Prodotti");
			labelGestioneProdotti.setBounds((pannelloAdmin2.getWidth()/3)+50,10, 200, 30);
			labelGestioneProdotti.setFont(new Font("Verdana", Font.BOLD, 20));
			
			/*
			bRinnovoProdotti 	= new JButton("Rinnova Prodotti"); 
			bRinnovoProdotti.setLocation(larghezzaPannelloAdmin2/4 -60,(altezzaPannelloAdmin2/2));
			bRinnovoProdotti.setSize(larghezzaBottoni, altezzaBottoni);
			
			bErogazioneFornitura 	= new JButton("Erogazioni e Forniture"); 
			bErogazioneFornitura.setLocation(larghezzaPannelloAdmin2/2 +30,(altezzaPannelloAdmin2/2));
			bErogazioneFornitura.setSize(larghezzaBottoni, altezzaBottoni);
			
			bChiudiPannello = new JButton("X");
			//bChiudiPannello.setFont(new Font("Arial", 0, 6));
			bChiudiPannello.setLocation(larghezzaPannelloAdmin2 -70 ,5);
			bChiudiPannello.setSize(45, 20);
			bChiudiPannello.setBackground(Color.red);
			
			*/
			
	        //Bottone Rinnovo Prodotti
	        bRinnovoProdotti= new JButton(new ImageIcon("Save/immagini/rinnova_prodotti.gif"));
	        bRinnovoProdotti.setRolloverIcon(new ImageIcon("Save/immagini/rinnova_prodottiR.gif"));
	        bRinnovoProdotti.setBorderPainted(false);
	        bRinnovoProdotti.setMargin (new Insets (0, 0, 0, 0));
	        bRinnovoProdotti.setContentAreaFilled(false);
	        bRinnovoProdotti.setOpaque(true);
	        bRinnovoProdotti.setToolTipText("Aggingi/Elimina prodotti");
	        bRinnovoProdotti.setBounds(larghezzaPannelloAdmin2/4 -60,(altezzaPannelloAdmin2/2),larghezzaBottoni, altezzaBottoni);
	        
	        //Bottone Erogazione Forniture
	        bErogazioneFornitura= new JButton(new ImageIcon("Save/immagini/erogazioni_forniture.gif"));
	        bErogazioneFornitura.setRolloverIcon(new ImageIcon("Save/immagini/erogazioni_fornitureR.gif"));
	        bErogazioneFornitura.setBorderPainted(false);
	        bErogazioneFornitura.setMargin (new Insets (0, 0, 0, 0));
	        bErogazioneFornitura.setContentAreaFilled(false);
	        bErogazioneFornitura.setOpaque(true);
	        bErogazioneFornitura.setToolTipText("Erogazioni e Forniture");
	        bErogazioneFornitura.setBounds(larghezzaPannelloAdmin2/2 +30,(altezzaPannelloAdmin2/2),larghezzaBottoni, altezzaBottoni);
	        
			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");
			
			pannelloAdmin2.add(labelGestioneProdotti);
			pannelloAdmin2.add(bRinnovoProdotti);
		    pannelloAdmin2.add(bErogazioneFornitura);
		    pannelloAdmin2.add(bChiudiPannello);
			
			// Ascoltatori di bottoni e relative azioni
			ascoltatoreEtAzioneRinnovoProdotti			= new RinnovoProdottiAA();
			ascoltatoreEtAzioneErogazioneFornitura		= new ErogazioneFornituraAA();
			ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
			
		    // Associazione di bottoni ad ascoltatori
			bRinnovoProdotti.addActionListener(ascoltatoreEtAzioneRinnovoProdotti);
			bErogazioneFornitura.addActionListener(ascoltatoreEtAzioneErogazioneFornitura);	
			bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);	
		}	// Fine costruttore
				
		private class ChiudiPannelloAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloAdmin2.setVisible(false);
				//ConfineAvvio.Confine.remove(pannelloAdmin2);
				ConfineAministratoreAmministrazione.bGestioneUtenti.setEnabled(true);
				ConfineAministratoreAmministrazione.bGestioneProdotti.setEnabled(true);
				ConfineAministratoreAmministrazione.bGestioneIndici.setEnabled(true);
				ConfineAministratoreAmministrazione.bCambiaUtente.setEnabled(true);
				ConfineAministratoreAmministrazione.bSospendi.setEnabled(true);
				ConfineAministratoreAmministrazione.bLog.setEnabled(true);
				ConfineAministratoreAmministrazione.bMenu.setEnabled(true);
			}
		}
						
		private class ErogazioneFornituraAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//questoConfine.setVisible(false);	
				bRinnovoProdotti.setEnabled(false);
				bErogazioneFornitura.setEnabled(false);
				bChiudiPannello.setEnabled(false);
				ConfineAministratoreAmministrazione.file.setEnabled(false);
				
				//TECNICA ASSURDA PER NON FAR SPARIRE LA FRECCETTA DELLE TENDINE
				pannelloAdmin2.setVisible(false);
				pannelloAdmin2.setVisible(true);
				
				new ConfineAministratoreAmministrazioneProdottiErogazioneFornitura(controlloreCorrente, questoConfine, altezzaProssimoPannello);
			}
		}

		private class RinnovoProdottiAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				//questoConfine.setVisible(false);		
				bRinnovoProdotti.setEnabled(false);
				bErogazioneFornitura.setEnabled(false);
				bChiudiPannello.setEnabled(false);
				
				//TECNICA ASSURDA PER NON FAR SPARIRE LA FRECCETTA DELLE TENDINE
				pannelloAdmin2.setVisible(false);
				pannelloAdmin2.setVisible(true);
				
				new ConfineAministratoreAmministrazioneProdottiRinnovoProdotti(controlloreCorrente, altezzaProssimoPannello);
			}
		}	
}
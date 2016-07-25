package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
//import java.io.IOException;
//	import java.io.File;
//	import java.io.IOException;
	import javax.swing.JButton;
//	import javax.swing.JComboBox;
	import javax.swing.JFrame;
import javax.swing.JLabel;
//	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//import javax.swing.JTextField;

//my -----------
import control.*;
import entity.*;

public class ConfineAmministratoreVisualizzaMenu extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Controllore controlloreCorrente;
	
	//Pannelli
		public JPanel pannelloAdmin2 = new JPanel();
		
		//Label Titolo
		public JLabel		labelTitoloMenu;
		
		//Area Log
		public JScrollPane 	scrollMenu;
		public JTextArea 	areaMenu;
		
		//Bottoni
		public JButton 		bAggiornaMenu;
		public JButton 		bChiudiPannello;
		
		//Ascoltatori Bottoni
		private AggiornaMenuAA 						ascoltatoreEtAzioneAggiornaMenu;
		private ChiudiPannelloAA					ascoltatoreEtAzioneChiudiPannello;
		
		int altezzaConfine;
		int altezzaPannelloAdmin2;
		int larghezzaPannelloAdmin2;
		int locazioneYPrimaRiga;
		
		public ConfineAmministratoreVisualizzaMenu(Controllore controllore, int locazioneYPannelloAdmin2)
		{
			this.controlloreCorrente=controllore;
			
			int lunghezzaScritta=200;
			int altezzaScritta = 40;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloAdmin2);
			ConfineAvvio.Confine.setLayout(null);
			pannelloAdmin2.setLayout(null);
			pannelloAdmin2.setVisible(true);
			
			locazioneYPannelloAdmin2 += 5;
			
			larghezzaPannelloAdmin2 = ConfineAvvio.Confine.getWidth();
			altezzaPannelloAdmin2 = altezzaConfine - locazioneYPannelloAdmin2 -38;
			pannelloAdmin2.setBounds(0,locazioneYPannelloAdmin2, larghezzaPannelloAdmin2, altezzaPannelloAdmin2);
			pannelloAdmin2.setBackground(new Color(190,190,190));
			
			locazioneYPrimaRiga =	(altezzaPannelloAdmin2)*1/8;
			
			//Titolo LOG OPERAZIONI
			labelTitoloMenu = new JLabel("Menù");
			labelTitoloMenu.setBounds((pannelloAdmin2.getWidth()/3)+130,10, lunghezzaScritta, altezzaScritta);
			labelTitoloMenu.setFont(new Font("Arial", 0, 20));
			
			int altezzaBottoni = 40;
			int larghezzaBottoni = 200;
			
			//Area Log
			int larghezzaAreaLog = (larghezzaPannelloAdmin2)*95/100;
			int altezzaAreaLog = (altezzaPannelloAdmin2)*3/4;
			
			int locazioneXScrollAreaLog = larghezzaPannelloAdmin2/60;
			areaMenu = new JTextArea(larghezzaAreaLog, altezzaAreaLog);
			//areaMenu.setFont(new Font("Arial", 0 , 16));
			//areaMenu.setFont(new Font("Arial", 1, 12));
			//areaMenu.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 15));
			areaMenu.setFont(new Font("Arial", 1, 12));
			areaMenu.setEditable(false);
			areaMenu.setLineWrap(true);
			scrollMenu = new JScrollPane(areaMenu);
			scrollMenu.setBounds(locazioneXScrollAreaLog, locazioneYPrimaRiga, larghezzaAreaLog, altezzaAreaLog);
					
			
			//Bottone Aggiorna Menù
			int locazioneXBottAggiornaMenu = larghezzaPannelloAdmin2/2 - larghezzaBottoni/2  +5;
			int locazioneYBottAggiornaMenu = locazioneYPrimaRiga + altezzaAreaLog + 10;
			/*
			bAggiornaMenu 	= new JButton("Aggiorna Menù"); 
			bAggiornaMenu.setBounds(locazioneXBottAggiornaMenu,locazioneYBottAggiornaMenu,larghezzaBottoni, altezzaBottoni);
			*/
			
			//Bottone Aggiorna Menu
			bAggiornaMenu = new JButton(new ImageIcon("Save/immagini/aggiorna_menu.gif"));
			bAggiornaMenu.setRolloverIcon(new ImageIcon("Save/immagini/aggiorna_menuR.gif"));
			bAggiornaMenu.setBorderPainted(false);
			bAggiornaMenu.setMargin (new Insets (0, 0, 0, 0));
			bAggiornaMenu.setContentAreaFilled(false);
			bAggiornaMenu.setOpaque(true);
			bAggiornaMenu.setToolTipText("Aggiorna Menù");
			bAggiornaMenu.setBounds(locazioneXBottAggiornaMenu,locazioneYBottAggiornaMenu, larghezzaBottoni, altezzaBottoni);
			
			
			//Bottone Chiudi Pannello
			/*
			bChiudiPannello = new JButton("X");
		    bChiudiPannello.setLocation(larghezzaPannelloAdmin2 -70 ,5);
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
			
			
		    pannelloAdmin2.add(labelTitoloMenu);
		    pannelloAdmin2.add(scrollMenu);
			pannelloAdmin2.add(bAggiornaMenu);
			pannelloAdmin2.add(bChiudiPannello);
			
			//Ascoltatori
			ascoltatoreEtAzioneAggiornaMenu    = new AggiornaMenuAA();
			ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
			
			//Associazione Bottone-Evento
			bAggiornaMenu.addActionListener(ascoltatoreEtAzioneAggiornaMenu);
			bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
			
		}
		
		private class AggiornaMenuAA implements ActionListener 
		{ 
		    public void actionPerformed(ActionEvent ev) 
		    {
		    	areaMenu.setText("     PRODOTTI \t\t                       GIACENZE \t DATA IMMISSIONE \t P.ACQUISTO  \t  P.VENDITA\n\n");
		    	String menu = controlloreCorrente.leggiMenu();
		    	areaMenu.append(menu);
		    	
		    	//SITUAZIONE CLASSI
		    	
		    	String situazioneClassi = "";
		    	situazioneClassi = Altro.getVarStatiche()+Bevanda.getVarStatiche()+Portata.getVarStatiche();
		    	areaMenu.append(situazioneClassi);
			}
		}	
		
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
				//ConfineAvvio.Confine.remove(pannelloAdmin2);
				//ConfineAministratoreAmministrazione.pannelloAdmin2.setVisible(false);
				
				
			}
		}

}

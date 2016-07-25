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

public class ConfineCassiereVisualizzaMenu extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Controllore controlloreCorrente;
	
	//Pannelli
		public JPanel pannelloCassiere2 = new JPanel();
		
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
		int altezzaPannelloCassiere2;
		int larghezzaPannelloCassiere2;
		int locazioneYPrimaRiga;
		
		public ConfineCassiereVisualizzaMenu(Controllore controllore, int locazioneYPannelloCassiere2)
		{
			this.controlloreCorrente=controllore;
			
			int lunghezzaScritta=200;
			int altezzaScritta = 40;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloCassiere2);
			ConfineAvvio.Confine.setLayout(null);
			pannelloCassiere2.setLayout(null);
			pannelloCassiere2.setVisible(true);
			
			locazioneYPannelloCassiere2 += 5;
			
			larghezzaPannelloCassiere2 = ConfineAvvio.Confine.getWidth();
			altezzaPannelloCassiere2 = altezzaConfine - locazioneYPannelloCassiere2 -38;
			pannelloCassiere2.setBounds(0,locazioneYPannelloCassiere2, larghezzaPannelloCassiere2, altezzaPannelloCassiere2);
			pannelloCassiere2.setBackground(new Color(190,190,190));
			
			locazioneYPrimaRiga =	(altezzaPannelloCassiere2)*1/8;
			
			//Titolo LOG OPERAZIONI
			labelTitoloMenu = new JLabel("Menù");
			labelTitoloMenu.setBounds((pannelloCassiere2.getWidth()/3)+120,10, lunghezzaScritta, altezzaScritta);
			labelTitoloMenu.setFont(new Font("Verdana", Font.BOLD, 20));
			
			int altezzaBottoni = 40;
			//int larghezzaBottoni = larghezzaPannelloCassiere2/6;
			int larghezzaBottoni = 200;
			
			//Area Log
			int larghezzaAreaMenu = (larghezzaPannelloCassiere2)*95/100;
			int altezzaAreaMenu = (altezzaPannelloCassiere2)*3/4;
			
			int locazioneXScrollAreaMenu = larghezzaPannelloCassiere2/60;
			areaMenu = new JTextArea(larghezzaAreaMenu, altezzaAreaMenu);
			areaMenu.setFont(new Font("Arial", 1, 12));
			areaMenu.setEditable(false);
			areaMenu.setLineWrap(true);
			scrollMenu = new JScrollPane(areaMenu);
			scrollMenu.setBounds(locazioneXScrollAreaMenu, locazioneYPrimaRiga, larghezzaAreaMenu, altezzaAreaMenu);
			
			//Bottone Aggiorna Menu
			int locazioneXBottAggiornaMenu = larghezzaPannelloCassiere2/2 - larghezzaBottoni/2  +5;
			int locazioneYBottAggiornaMenu = locazioneYPrimaRiga + altezzaAreaMenu + 10;
			bAggiornaMenu = new JButton(new ImageIcon("Save/immagini/aggiorna_menu.gif"));
			bAggiornaMenu.setRolloverIcon(new ImageIcon("Save/immagini/aggiorna_menuR.gif"));
			bAggiornaMenu.setBorderPainted(false);
			bAggiornaMenu.setMargin (new Insets (0, 0, 0, 0));
			bAggiornaMenu.setContentAreaFilled(false);
			bAggiornaMenu.setOpaque(true);
			bAggiornaMenu.setToolTipText("Aggiorna Menù");
			bAggiornaMenu.setBounds(locazioneXBottAggiornaMenu,locazioneYBottAggiornaMenu, larghezzaBottoni, altezzaBottoni);
			
			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");
			
		    pannelloCassiere2.add(labelTitoloMenu);
		    pannelloCassiere2.add(scrollMenu);
			pannelloCassiere2.add(bAggiornaMenu);
			pannelloCassiere2.add(bChiudiPannello);
			
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
		    	areaMenu.setText("     PRODOTTI \t\t                       GIACENZE \t DATA IMMISSIONE  \t  P.VENDITA\n\n");
		    	
		    	String menu = controlloreCorrente.leggiMenuSenzaPrezzoAcquisto();
		    	
		    	areaMenu.append(menu);
			}
		}	
		
		private class ChiudiPannelloAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfineCassiereErogazione.bErogazione.setEnabled(true);
				ConfineCassiereErogazione.bMenu.setEnabled(true);
				
				ConfineCassiereErogazione.bCambiaUtente.setEnabled(true);
				ConfineCassiereErogazione.bSospendi.setEnabled(true);
					
				pannelloCassiere2.setVisible(false);		
			}
		}

}

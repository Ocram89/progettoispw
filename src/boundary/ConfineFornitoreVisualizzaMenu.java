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

public class ConfineFornitoreVisualizzaMenu extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Controllore controlloreCorrente;
	
	//Pannelli
		public JPanel pannelloFornitore2 = new JPanel();
		
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
		int altezzaPannelloFornitore2;
		int larghezzaPannelloFornitore2;
		int locazioneYPrimaRiga;
		
		public ConfineFornitoreVisualizzaMenu(Controllore controllore, int locazioneYPannelloFornitore2)
		{
			this.controlloreCorrente=controllore;
			
			int lunghezzaScritta=200;
			int altezzaScritta = 40;
			
			altezzaConfine = ConfineAvvio.Confine.getHeight();
			ConfineAvvio.Confine.add(pannelloFornitore2);
			ConfineAvvio.Confine.setLayout(null);
			pannelloFornitore2.setLayout(null);
			pannelloFornitore2.setVisible(true);
			
			locazioneYPannelloFornitore2 += 5;
			
			larghezzaPannelloFornitore2 = ConfineAvvio.Confine.getWidth();
			altezzaPannelloFornitore2 = altezzaConfine - locazioneYPannelloFornitore2 -38;
			pannelloFornitore2.setBounds(0,locazioneYPannelloFornitore2, larghezzaPannelloFornitore2, altezzaPannelloFornitore2);
			pannelloFornitore2.setBackground(new Color(190,190,190));
			
			locazioneYPrimaRiga =	(altezzaPannelloFornitore2)*1/8;
			
			//Titolo LOG OPERAZIONI
			labelTitoloMenu = new JLabel("Menù");
			labelTitoloMenu.setBounds((pannelloFornitore2.getWidth()/3)+120,10, lunghezzaScritta, altezzaScritta);
			labelTitoloMenu.setFont(new Font("Verdana", Font.BOLD, 20));
			
			int altezzaBottoni = 40;
			//int larghezzaBottoni = larghezzaPannelloFornitore2/6;
			int larghezzaBottoni = 200;
			
			//Area Log
			int larghezzaAreaMenu = (larghezzaPannelloFornitore2)*95/100;
			int altezzaAreaMenu = (altezzaPannelloFornitore2)*3/4;
			
			int locazioneXScrollAreaMenu = larghezzaPannelloFornitore2/60;
			areaMenu = new JTextArea(larghezzaAreaMenu, altezzaAreaMenu);
			//areaMenu.setFont(new Font("Arial", 0 , 16));
			//areaMenu.setFont(new Font("Arial", 1, 12));
			//areaMenu.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 15));
			areaMenu.setFont(new Font("Arial", 1, 12));
			areaMenu.setEditable(false);
			areaMenu.setLineWrap(true);
			scrollMenu = new JScrollPane(areaMenu);
			scrollMenu.setBounds(locazioneXScrollAreaMenu, locazioneYPrimaRiga, larghezzaAreaMenu, altezzaAreaMenu);
			
			//Bottone Aggiorna Menu
			int locazioneXBottAggiornaMenu = larghezzaPannelloFornitore2/2 - larghezzaBottoni/2  +5;
			int locazioneYBottAggiornaMenu = locazioneYPrimaRiga + altezzaAreaMenu + 10;
			//bAggiornaMenu 	= new JButton("Aggiorna Menu"); 
			//bAggiornaMenu.setBounds(locazioneXBottAggiornaMenu,locazioneYBottAggiornaMenu,larghezzaBottoni, altezzaBottoni);
			
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
			//bChiudiPannello = new JButton("X");
		    //bChiudiPannello.setLocation(larghezzaPannelloFornitore2 -70 ,5);
			//bChiudiPannello.setSize(45, 20);
			//bChiudiPannello.setBackground(Color.red);
			
			//"X" chiusura Pannello	
			bChiudiPannello = new JButton(new ImageIcon("Save/immagini/bottone_chiusura.gif"));
			bChiudiPannello.setRolloverIcon(new ImageIcon("Save/immagini/bottone_chiusuraR.gif"));
			bChiudiPannello.setBounds(827, 5, 48, 20);
			bChiudiPannello.setBorderPainted(false);
			bChiudiPannello.setMargin (new Insets (0, 0, 0, 0));
			bChiudiPannello.setContentAreaFilled(false);
			bChiudiPannello.setOpaque(true);
			bChiudiPannello.setToolTipText("Chiusura Pannello");
			
		    pannelloFornitore2.add(labelTitoloMenu);
		    pannelloFornitore2.add(scrollMenu);
			pannelloFornitore2.add(bAggiornaMenu);
			pannelloFornitore2.add(bChiudiPannello);
			
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
		    	areaMenu.setText("     PRODOTTI \t\t                       GIACENZE \t DATA IMMISSIONE \t P.ACQUISTO\n\n");
		    	
		    	String menu = controlloreCorrente.leggiMenuSenzaPrezzoVendita();
		    	
		    	areaMenu.append(menu);
			}
		}	
		
		private class ChiudiPannelloAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				ConfineFornitoreDeposito.bForniture.setEnabled(true);
				ConfineFornitoreDeposito.bMenu.setEnabled(true);
				
				ConfineFornitoreDeposito.bCambiaUtente.setEnabled(true);
				ConfineFornitoreDeposito.bSospendi.setEnabled(true);
					
				pannelloFornitore2.setVisible(false);		
			}
		}

}


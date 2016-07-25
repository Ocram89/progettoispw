package boundary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
//	import java.awt.GridLayout;
	import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
//	import java.io.File;
//	import java.io.IOException;
	import javax.swing.JButton;
//	import javax.swing.JComboBox;
	import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
//	import javax.swing.JScrollPane;
//	import javax.swing.JTextArea;
//import javax.swing.JTextField;

//my -----------
import exception.*;
import control.*;

public class ConfineAmministratoreVisualizzaLog extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Controllore controlloreCorrente;
	
	//Pannelli
	public JPanel pannelloAdmin2 = new JPanel();
	
	//Label Titolo
	public JLabel		labelTitoloLog;
	
	//Area Log
	public JScrollPane 	scrollLog;
	public JTextArea 	areaLog;
	
	//Bottoni
	public JButton 		bAggiornaLog;
	public JButton 		bChiudiPannello;
	
	//Ascoltatori Bottoni
	private AggiornaLogAA 						ascoltatoreEtAzioneAggiornaLog;
	private ChiudiPannelloAA					ascoltatoreEtAzioneChiudiPannello;
	
	int altezzaConfine;
	int altezzaPannelloAdmin2;
	int larghezzaPannelloAdmin2;
	int locazioneYPrimaRiga;
	
	public ConfineAmministratoreVisualizzaLog(Controllore controllore, int locazioneYPannelloAdmin2)
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
		labelTitoloLog = new JLabel("Log Operazioni");
		labelTitoloLog.setBounds((pannelloAdmin2.getWidth()/3)+90,10, lunghezzaScritta, altezzaScritta);
		labelTitoloLog.setFont(new Font("Arial", 0, 20));
		
		int altezzaBottoni = 40;
		int larghezzaBottoni = 200;
		
		//Area Log
		int larghezzaAreaLog = (larghezzaPannelloAdmin2)*95/100;
		int altezzaAreaLog = (altezzaPannelloAdmin2)*3/4;
		
		int locazioneXScrollAreaLog = larghezzaPannelloAdmin2/60;
		areaLog = new JTextArea(larghezzaAreaLog, altezzaAreaLog);
		//areaMenu.setFont(new Font("Arial", 0 , 16));
		areaLog.setFont(new Font("Arial", 1, 12));
		//areaMenu.setFont(new Font("Monospaced", Font.LAYOUT_LEFT_TO_RIGHT, 15));
		areaLog.setEditable(false);
		areaLog.setLineWrap(true);
		scrollLog = new JScrollPane(areaLog);
		scrollLog.setBounds(locazioneXScrollAreaLog, locazioneYPrimaRiga, larghezzaAreaLog, altezzaAreaLog);
				
		
		//Bottone Aggiorna Log
		int locazioneXBottAggiornaLog = larghezzaPannelloAdmin2/2 - larghezzaBottoni/2  +5;
		int locazioneYBottAggiornaLog = locazioneYPrimaRiga + altezzaAreaLog + 10;
		/*
		bAggiornaLog 	= new JButton("Aggiorna Log"); 
		bAggiornaLog.setBounds(locazioneXBottAggiornaLog,locazioneYBottAggiornaLog,larghezzaBottoni, altezzaBottoni);
		*/
		
		//Bottone Aggiorna Log
		bAggiornaLog = new JButton(new ImageIcon("Save/immagini/aggiorna_log.gif"));
		bAggiornaLog.setRolloverIcon(new ImageIcon("Save/immagini/aggiorna_logR.gif"));
		bAggiornaLog.setBorderPainted(false);
		bAggiornaLog.setMargin (new Insets (0, 0, 0, 0));
		bAggiornaLog.setContentAreaFilled(false);
		bAggiornaLog.setOpaque(true);
		bAggiornaLog.setToolTipText("Aggiorna Log");
		bAggiornaLog.setBounds(locazioneXBottAggiornaLog,locazioneYBottAggiornaLog, larghezzaBottoni, altezzaBottoni);
		
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
		
		
	    pannelloAdmin2.add(labelTitoloLog);
	    pannelloAdmin2.add(scrollLog);
		pannelloAdmin2.add(bAggiornaLog);
		pannelloAdmin2.add(bChiudiPannello);
		
		//Ascoltatori
		ascoltatoreEtAzioneAggiornaLog    = new AggiornaLogAA();
		ascoltatoreEtAzioneChiudiPannello = new ChiudiPannelloAA();
		
		//Associazione Bottone-Evento
		bAggiornaLog.addActionListener(ascoltatoreEtAzioneAggiornaLog);
		bChiudiPannello.addActionListener(ascoltatoreEtAzioneChiudiPannello);
	}
		
	
	private class AggiornaLogAA implements ActionListener 
	{ 
	    public void actionPerformed(ActionEvent ev) 
	    {
	        try 
	        {
				String contenutoLog = controlloreCorrente.caricaLog();
				areaLog.setText(contenutoLog);
			} 
	        catch (FileInesistenteException e) 
	        {
	        	JOptionPane.showMessageDialog(null, e.getMessage()+ "Per Salvare il Log, cliccare su \"File --> Salva Log Operazioni\".", "Errore", JOptionPane.ERROR_MESSAGE);
			} 
	        catch (IOException e) 
			{
	        	JOptionPane.showMessageDialog(null, e.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
			}
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

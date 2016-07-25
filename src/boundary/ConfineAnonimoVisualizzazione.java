package boundary;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.util.*;
	
//my -----------
import util.*;
import exception.*;
import control.*;

//PRIMA VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE

public class ConfineAnonimoVisualizzazione extends JFrame
{

		//Versione temporanea per modificare al minimo il main di test
		private static final long	serialVersionUID	= 1L;
		

		
		// Relazione con controllore
		private Controllore controlloreCorrente;
		
		//Pannelli
		private JScrollPane pannelloSfogliaMenu;
		private JTextArea 	menuArea;
		public JPanel  		pannelloBottoni;
		
		//Bottoni/
		public JButton 		bMostraMenu; 
		public JButton 		bRimuoviMenu; 

		private ConfineAnonimoVisualizzazione questoConfine; 

		// Ascoltatori di bottoni e relative azioni
		private MostraMenuAA 	ascoltatoreEtAzioneMostraMenu;
		private RimuoviMenuAA 	ascoltatoreEtAzioneRimuoviMenu;
	
		public ConfineAnonimoVisualizzazione(Controllore controllore)
		{
			questoConfine=this;
			this.controlloreCorrente = controllore;
			
			// Inserire richiesta se si intende veramente uscire
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setTitle("Cliente" + CostantiDiInstallazione.NOME_CLIENTE);
			this.setLayout(new GridLayout(10,20));
			
			menuArea = new JTextArea(10, 18);		
//			pannelloSfogliaDescr = new JScrollPane(descrizioneArea);
			
			pannelloBottoni  = new JPanel();	
			bMostraMenu = new JButton("MostraMenu"); 
			bRimuoviMenu = new JButton("RimuoviMenu"); 

			// Ascoltatori di bottoni e relative azioni
			ascoltatoreEtAzioneMostraMenu 			= new MostraMenuAA();
			ascoltatoreEtAzioneRimuoviMenu 			= new RimuoviMenuAA();

		    // Associazione di bottoni ad ascoltatori
			bMostraMenu.addActionListener(ascoltatoreEtAzioneMostraMenu);
			bRimuoviMenu.addActionListener(ascoltatoreEtAzioneRimuoviMenu);

			pannelloBottoni.add(bMostraMenu);
			pannelloBottoni.add(bRimuoviMenu);

			
			// Aggiunta pannelli al frame
//			this.add(pannelloSfogliaDescr);	
			this.add(pannelloBottoni);
			this.pack();
			bMostraMenu.setEnabled(true);
			bRimuoviMenu.setEnabled(false);
			this.setVisible(true);
		}
		// Fine costruttore
		
		private class RimuoviMenuAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				questoConfine.setVisible(false);
				pannelloSfogliaMenu.removeAll();
				bRimuoviMenu.setEnabled(false);
				questoConfine.pack();
				questoConfine.setVisible(true);
			}
		}
		
		
		private class MostraMenuAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				pannelloSfogliaMenu = new JScrollPane(menuArea);
				questoConfine.add(pannelloSfogliaMenu);	
				bRimuoviMenu.setEnabled(true);
				questoConfine.pack();
				questoConfine.setVisible(true);
				displayMenu();
			}
		}
		
		private void displayMenu() 
		{
			Set <String> p= controlloreCorrente.keySet();
			String descrizione;
			// System.getProperty("line.separator");
			// System.getProperty("line.tab");
			String spazio= " ";
			if (p.isEmpty())
				descrizione ="Il menù è vuoto\n";
				else descrizione = "";
			for (String k : p) 
			{
				try 
				{
					descrizione = descrizione + spazio + k + ";"+spazio + spazio + controlloreCorrente.giacenza(new IDEsterno(k))+spazio+spazio;
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
			menuArea.setText(descrizione);
		}

		
		private int salvaEChiedi() {
			// Chiedere se si intende veramente uscire dall'applicazione
			questoConfine.setVisible(false);
			return 0;
		}


}


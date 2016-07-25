package boundary;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.ImageIcon;

//my -----------
import util.*;
import exception.*;
import control.*;


	public class ConfineAvvio extends StackFrame
	{

		private static final long	serialVersionUID	= 1L;
						
		//Pannelli
		public static JPanel pannello = new JPanel();	
		public JPanel panelTitolo = new JPanel();
		public JPanel panelButtons = new JPanel();
		
		//Label
		public JLabel titolo = new JLabel();
		public JLabel titolo2 = new JLabel();
		
		//Bottoni
		//public JButton 		bMenu = new JButton("MENU"); 
		//public JButton      bLogin = new JButton("LOGIN");

		//Bottoni con Immagine
		public JButton bMenu = new JButton(new ImageIcon("Save/immagini/bottone_menu.gif"));
		public JButton bLogin = new JButton(new ImageIcon("Save/immagini/bottone_login.gif"));
		
		//Ascoltatori di bottoni e relative azioni
		private MenuAA 		ascoltatoreEtAzioneMenu;
		private LoginAA 	ascoltatoreEtAzioneLogin;
		
		//Immagine su pannello principale
		public JLabel immagine = new JLabel(new ImageIcon("Save/immagini/sfondo_principale.gif"));	
		
		//StackFrame questoConfine=null;
		public static JFrame Confine;

		public ConfineAvvio()
		{
			
			Confine = this;
			this.setTitle("Amministrazione" + CostantiDiInstallazione.NOME_CLIENTE);
			
			Confine.setLayout(null);
			final int BASECONFINE = 900;
			final int ALTEZZACONFINE = 600;
			setSize(BASECONFINE, ALTEZZACONFINE);
			Dimension dim = getToolkit().getScreenSize();
	        setLocation(dim.width/2 - getWidth()/2, dim.height/2 - getHeight()/2);
	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	        setResizable(false);
	        
	        pannello.setSize(Confine.getWidth(), Confine.getHeight());	
	        this.add(pannello);
	        pannello.setLayout(null);
	       
	        final int BORDO = 5;
			final int ALTEZZATITOLO = 30;
	             
	        panelTitolo.setLayout(null);
	        panelTitolo.setSize(this.getWidth(), ALTEZZATITOLO*3);
	        panelTitolo.setLocation(BORDO, BORDO);  
	        //panelTitolo.setBounds(0, 0, 800, 400); 
	        panelTitolo.add(titolo);
	        panelTitolo.add(titolo2);     
	        
	        titolo.setFont(new Font("Verdana", 0, 20));
	        titolo.setLocation(BORDO, BORDO);
	        titolo.setSize(panelTitolo.getWidth(), 30);
	        titolo.setHorizontalAlignment(JLabel.CENTER);
	        titolo.setVerticalAlignment(JLabel.CENTER);
	        titolo.setText("Benvenuti alla Gestione della Trattoria - Enoteca: La Pergola");
	        
	        titolo2.setFont(new Font("Verdana", 0, 16));
	        titolo2.setLocation(BORDO, ALTEZZATITOLO);
	        titolo2.setSize(panelTitolo.getWidth(), 50);
	        titolo2.setHorizontalAlignment(JLabel.CENTER);
	        titolo2.setVerticalAlignment(JLabel.CENTER);
	        titolo2.setText("Visualizzare il Menu o Effettuare il Login per autenticarsi.");
	        
	        panelButtons.setLayout(null);
	        panelButtons.setSize(getWidth(), 150);
	        panelButtons.setLocation(BORDO, ALTEZZATITOLO);
	        
	        panelButtons.add(bMenu);
        
			bMenu.setRolloverIcon(new ImageIcon("Save/immagini/bottone_menuR.gif"));
			bMenu.setBorderPainted(false);
			bMenu.setMargin (new Insets (0, 0, 0, 0));
			bMenu.setContentAreaFilled(false);
			bMenu.setOpaque(true);
			bMenu.setToolTipText("Visualizza Menu");
			//setBounds: Distanza Margine Sx/Distanza Margine Sup/Lunghezza/Altezza
	        bMenu.setBounds(95, 100, 250, 50);


	        panelButtons.add(bLogin);
			bLogin.setRolloverIcon(new ImageIcon("Save/immagini/bottone_loginR.gif"));
			bLogin.setBorderPainted(false);
			bLogin.setMargin (new Insets (0, 0, 0, 0));
			bLogin.setContentAreaFilled(false);
			bLogin.setOpaque(true);
			bLogin.setToolTipText("Effettua Login");
			//setBounds: Distanza Margine Sx/Distanza Margine Sup/Lunghezza/Altezza
	        bLogin.setBounds(545, 100, 250, 50);
	                
	        pannello.add(panelTitolo);
	        pannello.add(panelButtons);
			
	        //creaBottoniBase	
						
			// Definisci ascoltatori di bottoni e relative azioni
			ascoltatoreEtAzioneMenu				= new MenuAA();
			ascoltatoreEtAzioneLogin			= new LoginAA();
		
			
		    // Associa ascoltatori e bottoni
			bMenu.addActionListener(ascoltatoreEtAzioneMenu);
			bLogin.addActionListener(ascoltatoreEtAzioneLogin);

			
			/*bMenu.setEnabled(true); 
			bLogin.setEnabled(true); */
			
			//Immagine
			pannello.add(immagine);
			///setBounds: Distanza Margine Sx/Distanza Margine Sup/Lunghezza/Altezza
			immagine.setBounds(100, 200, 700, 350);
			

			//Rendi visibile questo frame;			
			this.setVisible(true);	
			
		}
		// Fine costruttore
			
				
		private class MenuAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{
				try 
				{
					pannello.setVisible(false);
					new ControlloreVisualizzazioneMenu(new Utente("a","a","a"));
				} 
				catch (PswException e) 
				{
					e.printStackTrace();
				} catch (DeserializzazioneException e) {
					e.printStackTrace();
				}
			}
		}
	
		private class LoginAA implements ActionListener
		{
			public void actionPerformed(ActionEvent arg0)
			{				
				try 
				{
					
					pannello.setVisible(false);
					ControlloreLogin.gestioneLogin();					
				} 
				catch (PswException e) 
				{				
					e.printStackTrace();					
				}
			}
		}		
}
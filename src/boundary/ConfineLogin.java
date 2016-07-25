package boundary;

import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
//import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
// import java.io.File;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

//my -----------
import exception.*;
import control.*;
import thread.*;

//PRIMA VERSIONE INCOMPLETA DI IMPIEGO DI BOUNDARY CON INTERFACCE GRAFICHE

public class ConfineLogin extends JFrame
{
	private static final long	serialVersionUID	= 1L;
	private ControlloreLogin controlloreLogin =null;

	private static boolean avviaThreads = true;
	//private static boolean avviaThreads = false;
	
	public JPanel  pannelloLogin;

	//Testo di Presentazione
	public static JLabel testoPresentazione = new JLabel();
	
	
	public JPanel panelTitolo = new JPanel();
	public JPanel panelCredenzialiLogin = new JPanel();
	public JPanel panelCredenzialiPsw = new JPanel();
	public JPanel panelButtonLogin = new JPanel();
	
	public JLabel titolo = new JLabel();
	
	//Bottone
	public JButton bLogin; 

	// Campi e loro etichette
	public JLabel     loginLabel = new JLabel();
	public JTextField loginF = new JTextField();
	public JLabel     passwordLabel = new JLabel();
	//public JTextField passwordF= new JTextField();
	public JPasswordField passwordF= new JPasswordField();
	
	// Ascoltatore di bottone e relativa azioni
	private LoginAA   ascoltatoreEtAzioneLogin;
	

	
	public ConfineLogin(ControlloreLogin controlloreLogin)   //Qui parte la Deserializzazione
	{
		int border = 5;
	    
		this.controlloreLogin = controlloreLogin;
				
		pannelloLogin = new JPanel();
		
		pannelloLogin.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight());	
		ConfineAvvio.Confine.add(pannelloLogin);
		pannelloLogin.setLayout(null);
		
		        
        panelTitolo.setLayout(null);
        panelTitolo.setSize(ConfineAvvio.Confine.getWidth(), 45);
        panelTitolo.setLocation(5, 5);  
        //panelTitolo.setBounds(0, 0, 800, 400); 
        panelTitolo.add(titolo);
        
        titolo.setFont(new Font("Verdana", Font.BOLD, 20));
        titolo.setLocation(border, border);
        titolo.setSize(panelTitolo.getWidth(), 35);
        titolo.setHorizontalAlignment(JLabel.CENTER);
        titolo.setVerticalAlignment(JLabel.CENTER);
        titolo.setText("Inserire le proprie credenziali: Login e Password");
        
        pannelloLogin.add(panelTitolo);
//		ImageIcon icona = new ImageIcon("150820091211.jpg");
//		bLogin.setMaximumSize(new Dimension(40,100));
		// Creazione etichette campi con relativi nomi
		
        loginLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        loginLabel.setLocation(150, 50);
        loginLabel.setSize(panelTitolo.getWidth()/2, 30);
        loginLabel.setText("Login: ");
		
        
		loginF = new JTextField("", 60); 
		loginF.setFont(new Font("Verdana", 0, 15));
		loginF.setLocation(300,50);
		loginF.setSize(panelTitolo.getWidth()/2, 30);
		
		
		
		passwordLabel.setFont(new Font("Verdana", Font.BOLD, 15));
		passwordLabel.setLocation(150, 50);
		passwordLabel.setSize(panelTitolo.getWidth()/2, 30);
		passwordLabel.setText("Password: ");
        
		passwordF    	 	= new JPasswordField("", 60); 
		passwordF.setLocation(300,50);
		passwordF.setSize(panelTitolo.getWidth()/2, 30);
		passwordF.setFont(new Font("Verdana", 0, 15));
			
		// Creazione bottone
		/*bLogin 			= new JButton("Login");
		bLogin.setLocation(300,50);
		bLogin.setSize(panelTitolo.getWidth()/4, 50);
		bLogin.setFont(new Font("Arial", 0, 20));*/
		
        bLogin = new JButton(new ImageIcon("Save/immagini/bottone_login.gif"));
        bLogin.setRolloverIcon(new ImageIcon("Save/immagini/bottone_loginR.gif"));
        bLogin.setBorderPainted(false);
        bLogin.setMargin (new Insets (0, 0, 0, 0));
        bLogin.setContentAreaFilled(false);
        bLogin.setOpaque(true);
        bLogin.setToolTipText("Login");
        bLogin.setBounds(325, 40, 250, 50);

		panelCredenzialiLogin.setLayout(null);
		panelCredenzialiLogin.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelCredenzialiLogin.setLocation(5, 70); 
		panelCredenzialiLogin.setBackground(new Color(190, 190, 190));
		panelCredenzialiLogin.add(loginLabel);
		panelCredenzialiLogin.add(loginF);
		
		panelCredenzialiPsw.setLayout(null);
		panelCredenzialiPsw.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelCredenzialiPsw.setLocation(5, 200); 
		panelCredenzialiPsw.setBackground(new Color(190, 190, 190));
		panelCredenzialiPsw.add(passwordLabel);
		panelCredenzialiPsw.add(passwordF);
		
		panelButtonLogin.setLayout(null);
		panelButtonLogin.setSize(ConfineAvvio.Confine.getWidth(), ConfineAvvio.Confine.getHeight()/5);
		panelButtonLogin.setLocation(5, 330); 
		//panelButtonLogin.setBackground(new Color(190, 190, 190));
		panelButtonLogin.add(bLogin);
		
				
		pannelloLogin.add(panelCredenzialiLogin);
		pannelloLogin.add(panelCredenzialiPsw);
		pannelloLogin.add(panelButtonLogin);
		
	  	
					
		// Ascoltatore di bottone Login e relativa azione
		ascoltatoreEtAzioneLogin = new LoginAA();
		
		
		// Associazione di bottone Login a relativo ascoltatore
		bLogin.addActionListener(ascoltatoreEtAzioneLogin);
		passwordF.addActionListener(ascoltatoreEtAzioneLogin);
		
		loginF.requestFocusInWindow();
		
		deserializza();
	}
	// Fine costruttore
	
	private void deserializza()
	{
		try 
		{
			controlloreLogin.caricaArticoli();
		}
		catch (DeserializzazioneException e)
		{
			JOptionPane.showMessageDialog(null, " Confine. Problemi in Deserializza. " + e.toString());
		}
	}
	
	private class LoginAA implements ActionListener
	{
		@SuppressWarnings("deprecation")
		public void actionPerformed(ActionEvent arg0)
		{
				try
				{
					//Passa Credenziali (Login e Psw) al ControlloreLogin
					pannelloLogin.setVisible(false);
	
					controlloreLogin.passaCredenziali(loginF.getText(), passwordF.getText());
					
					//Avvia i Threads una sola volta.
//avviaThreads=false;
					if (avviaThreads)
					{	
						avviaThreads=false;
//						avviaThreads();
//						controlloreLogin.avviaThreads();
						avviaThreads();
					}
					
				} 
				catch (PswException e)
				{
					pannelloLogin.setVisible(true);
					JOptionPane.showMessageDialog(null, " Attenzione! Password Errata ", "Errore", JOptionPane.ERROR_MESSAGE);
				} 
				catch (UtenteException e)
				{
					pannelloLogin.setVisible(true);
					JOptionPane.showMessageDialog(null, " Attenzione! L'Utente inserito non esiste! ", "Errore", JOptionPane.ERROR_MESSAGE);
				} 
				catch (RuoloException e) 
				{
					JOptionPane.showMessageDialog(null, " ConfineLogin. LoginAA. RuoloException " + e.getMessage(), "Ruolo Errato", JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. ClassNotFoundException " + e.getMessage(), "Classe del dato ruolo non trovata", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalAccessException e) {
					JOptionPane.showMessageDialog(null, " ConfineLoginAA. IllegalAccessException " + e.getMessage(), "Accesso errato a vettore dei ruoli", JOptionPane.ERROR_MESSAGE);
				} catch (InstantiationException e) {
					JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. InstantiationException " + e.getMessage(), "Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
				} catch (SecurityException e) {
					JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. SecurityException. " + e.getMessage(), "Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. IllegalArgumentException. " + e.getMessage(), "Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
				} catch (NoSuchMethodException e) {
					JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. NoSuchMethodException. " + e.getMessage(), "Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
				} catch (InvocationTargetException e) 
				{
					JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. InvocationTargetException. " + e.getMessage(), "Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
				}
				
		}
		
		// Non più utilizzato - Spostaro in ControlloreLogin() - Faceva la chiamata a ControlloreAmministrazione() senza passare utente corrente
		private void avviaThreads()
		{		
			try
			{
				//avviaThreads = false;
				Controllore controlThread = new ControlloreAmministrazione();
				
				ThreadGestore tGestore = new ThreadGestore(controlThread);
				Thread ttgestore = new Thread(tGestore);
				ttgestore.start();
				
			
				ArrayList <Thread> fornitoriArrayThread = new ArrayList<Thread>();
				
				//creo Attori e loro Threads
				for (int i=0; i<2; i++) //2 e il nuemro di Threads cassieri che partiranno
				{
					ThreadFornitore tFornitore = new ThreadFornitore(controlThread);
					Thread ttfornitore = new Thread(tFornitore);
					fornitoriArrayThread.add(ttfornitore);				
				}
				//Partenza di tutti i Thread contemporaneamente
				for (int i=0; i<2; i++) 
				{
					fornitoriArrayThread.get(i).start();	
					//Thread.sleep(1); 
				}						
				
				ArrayList <Thread> cassieriArrayThread = new ArrayList<Thread>();
				//creo Attori e loro Threads
				for (int i=0; i<2; i++)  //2 e il nuemro di Threads cassieri che partiranno
				{
					ThreadCassiere tCassiere = new ThreadCassiere(controlThread);
					Thread ttcassiere = new Thread(tCassiere);
					cassieriArrayThread.add(ttcassiere);				
				}
				//Partenza di tutti i Thread contemporaneamente
				for (int i=0; i<2; i++)
				{
					cassieriArrayThread.get(i).start();	
					//Thread.sleep(1); 
				}				

			} 
			catch (PswException e)
			{
				JOptionPane.showMessageDialog(null, " Attenzione! Password Errata ", "Errore Threads", JOptionPane.ERROR_MESSAGE);
			} 
			catch (RuoloException e) 
			{
				JOptionPane.showMessageDialog(null, " ConfineLogin. LoginAA. RuoloException " + e.getMessage(), "Errore Ruolo Threads", JOptionPane.ERROR_MESSAGE);
			} 
			catch (SecurityException e)
			{
				JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. SecurityException. " + e.getMessage(), "Errore Threads - Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
			} 
			catch (IllegalArgumentException e) 
			{
				JOptionPane.showMessageDialog(null, " ConfineLogin.LoginAA. IllegalArgumentException. " + e.getMessage(), "Errore Threads - Classe del dato ruolo non istanziabile", JOptionPane.ERROR_MESSAGE);
			} 
			catch (LoginException e) 
			{
				e.printStackTrace();
			}
		}
	}	
}

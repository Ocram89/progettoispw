package util;

//my -----------
import exception.*;

import java.io.Serializable;


public class Credenziali implements Serializable{
	
	private static final long	serialVersionUID	= 1L;
	
	private String login;
	private Psw password;
	public Credenziali(String login, String password) throws PswException 
	{		
		this.login = login;
		this.password = new Psw(password); //La password deve essere inserita in un oggetto Psw, e non in una String!
	}
	
	public String getLogin() 
	{
		return login;
	}

	public String getPassword()
	{
		return password.toString();
	}
}

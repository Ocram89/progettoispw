package util;

import java.io.Serializable;

//my -----------
import exception.*;



public class Utente implements Serializable{
	
	private static final long	serialVersionUID	= 1L;
	
	private String login;
	private String ruolo;
	private Psw password;
	
	public Utente(String login, String ruolo, String password) throws PswException
	{		
		this.login = login;
		this.ruolo = ruolo;
		this.password = new Psw(password);
	}
	
	public Utente(Credenziali credenziali, String ruolo) throws PswException
	{		
		this.login = credenziali.getLogin();
		this.ruolo = ruolo;
		this.password = new Psw(credenziali.getPassword());
	}
	
	public String getLogin() 
	{
		return login;
	}
	public String getRuolo()
	{
		return ruolo;
	}
	public String getPassword()
	{
		return password.toString();
	}
	public void setPassword(String password) throws PswException
	{
		this.password = new Psw(password);
	}
	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((ruolo == null) ? 0 : ruolo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		if (login == null) 
		{
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) 
		{
			if (other.password != null)
				return false;
		} else if (!password.toString().equals(other.password.toString()))
			return false;
		if (ruolo == null) 
		{
			if (other.ruolo != null)
				return false;
		} else if (!ruolo.equals(other.ruolo))
			return false;
		return true;
	}
	
	
	
	

}

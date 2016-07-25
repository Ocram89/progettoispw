package exception;

import java.io.Serializable;

public class Psw implements Serializable
{
	private static final long	serialVersionUID	= 1L;
	private String password;
	
	public Psw(String nuovaPass) throws PswException
	{
		password = nuovaPass;
		validaPassword();
	}
	
	public String toString()
	{
		return password;
	}
	

	private void validaPassword() throws PswException {
		if (!testPasswordSyntax(password))  
			throw new PswException ("Password non ammissibile");
	}
	
	private boolean testPasswordSyntax(String s) {
		return true;
	}
}

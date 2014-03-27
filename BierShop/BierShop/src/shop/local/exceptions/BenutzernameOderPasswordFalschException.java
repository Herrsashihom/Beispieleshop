package shop.local.exceptions;

public class BenutzernameOderPasswordFalschException extends Exception{
	
	public BenutzernameOderPasswordFalschException(){
		super("Benutzername oder Passwort ist nicht richtig, bitte pruefen Sie noch mal!!");
	}

}

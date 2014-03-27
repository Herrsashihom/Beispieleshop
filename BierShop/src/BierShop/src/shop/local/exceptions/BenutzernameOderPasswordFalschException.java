package BierShop.src.shop.local.exceptions;

@SuppressWarnings("serial")
public class BenutzernameOderPasswordFalschException extends Exception{
	
	public BenutzernameOderPasswordFalschException(){
		super("Benutzername oder Passwort ist nicht richtig, bitte pruefen Sie noch mal!!");
	}

}

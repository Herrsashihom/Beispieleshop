package shop.local.exceptions;

import shop.local.valueobjects.Kunde;

/**
 * @exception Fehler auffangen - Person ist bereits vorhanden
 **/

public class KundeExistiertBereitsException extends Exception{
	
	public KundeExistiertBereitsException (Kunde k , String zusatzMsg) {
		
		super("Kunde mit Benutzername " + k.getBenutzername() + " und Name " + k.getNachname() 
				+ " existiert bereits" + zusatzMsg);
	}

}

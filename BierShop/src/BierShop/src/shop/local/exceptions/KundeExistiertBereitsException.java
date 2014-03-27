package BierShop.src.shop.local.exceptions;

import BierShop.src.shop.local.valueobjects.Kunde;

/**
 * @exception Fehler auffangen - Person ist bereits vorhanden
 **/

@SuppressWarnings("serial")
public class KundeExistiertBereitsException extends Exception{
	
	public KundeExistiertBereitsException (Kunde k , String zusatzMsg) {
		
		super("Kunde mit Benutzername " + k.getBenutzername() + " und Name " + k.getNachname() 
				+ " existiert bereits" + zusatzMsg);
	}

}

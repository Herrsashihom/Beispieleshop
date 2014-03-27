package BierShop.src.shop.local.exceptions;

import BierShop.src.shop.local.valueobjects.Mitarbeiter;

/**
 * @exception Fehler auffangen - Person ist bereits vorhanden
 **/

@SuppressWarnings("serial")
public class MitarbeiterExistiertBereitsException extends Exception{
	
	public MitarbeiterExistiertBereitsException (Mitarbeiter m , String zusatzMsg) {
		
		super("Mitarbeiter mit Benutzername " + m.getBenutzername() + " und Name " + m.getNachname() 
				+ " existiert bereits" + zusatzMsg);
	}

}

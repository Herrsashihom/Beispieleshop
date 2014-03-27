package shop.local.exceptions;

import shop.local.valueobjects.Bier;

public class BierExistiertBereitsException extends Exception {
	
	public BierExistiertBereitsException(Bier bier, String zusatzMsg) {
		
		super("Bier ID " + bier.getID() + ", Name " + bier.getbierName() 
				+ " existiert bereits" + zusatzMsg);
	}
	
	public BierExistiertBereitsException(Bier bier) {
		
		super("Bier ID " + bier.getID() + ", Name " + bier.getbierName() 
				+ " existiert bereits" );
	}	

}
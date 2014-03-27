package BierShop.src.shop.local.exceptions;

import BierShop.src.shop.local.valueobjects.Bier;

@SuppressWarnings("serial")
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
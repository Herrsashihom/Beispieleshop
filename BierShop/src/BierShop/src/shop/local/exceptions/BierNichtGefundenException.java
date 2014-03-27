package BierShop.src.shop.local.exceptions;

import BierShop.src.shop.local.valueobjects.Bier;

/**
 * @Exception Fehler, wenn man ein Bier gesucht, und nicht gefunden hat
 */

@SuppressWarnings({ "unused", "serial" })
public class BierNichtGefundenException extends Exception {
	
	public BierNichtGefundenException(String name) {
		
		super("Dieses Bier m�t dem Namen " + name + " ist nicht gefunden!");
	}
}
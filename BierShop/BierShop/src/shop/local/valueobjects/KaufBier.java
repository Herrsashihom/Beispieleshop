package shop.local.valueobjects;

public class KaufBier extends Bier{

	int kaufAnzahl = 0;

	public KaufBier(String id, String name, float preis, String inhalt, String type, int menge, int kaufAnzahl) {
		
		super(id, name, preis, inhalt, type, menge);
		this.kaufAnzahl = kaufAnzahl;
		
	}
	
	public int getKaufAnzahl(){
		return kaufAnzahl;
	}
	
	public void setKaufAnzahl(int anzahl){
		this.kaufAnzahl = anzahl;
	}

	public String toString() {
		return ("ID : " + bierID + " / Name : " + bierName + " / Preis : " + bierPreis + " / Type : " + type +" / Inhalt : " + bierInhalt + "/ Menge : "+kaufAnzahl);
	}
	
}

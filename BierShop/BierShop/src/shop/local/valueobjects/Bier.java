package shop.local.valueobjects;

public class Bier {
	
	protected String bierID;
	protected String bierName;
	protected float bierPreis;
	protected String bierInhalt;
	protected String type;
	protected int bierMenge;
	protected boolean verfuegbar; 
	
	
	public Bier(String id, String name, float preis, String inhalt, String type, int menge){
	
		this.bierID = id;
		this.bierName = name;
		this.bierPreis = preis;
		this.bierInhalt = inhalt;
		this.type = type;
		this.bierMenge = menge;
				
	}
	
	public Bier(String id, String name, float preis, String inhalt, String type, int menge, boolean verfuegbar){
	
		this.bierID = id;
		this.bierName = name;
		this.bierPreis = preis;
		this.bierInhalt = inhalt;
		this.type = type;
		this.bierMenge = menge;
		this.verfuegbar = verfuegbar;
		
	}
	
	
	/////get-set- Methode
	public String getID(){
		return bierID;
	}
	public void setID(String neuID){
		this.bierID = neuID;
	}
	
	
	public String getbierName(){
		return bierName;
	}
	public void setbierName(String name){
		this.bierName = name;
	}
	
	
	public float getbierPreis(){
		return bierPreis;
	}
	public void setbierPreis(float neuPreis){
		this.bierPreis = neuPreis;
	}
	
	
	public String getbierInhalt(){
		return bierInhalt;
	}
	public void setbierInhalt(String neuInhalt){
		bierInhalt = neuInhalt;
	}
	
	
	public String getType(){
		return type;
	}
	public void setType(String neuType){
		type = neuType;
	}
	
	
	public int getbierMenge(){
		return bierMenge;
	}
	public void setbierMenge(int menge){
		this.bierMenge = menge;
	}
	
	
	public String toString() {
		return ("ID: " + bierID + " / Name: " + bierName + "/ Preis: " + bierPreis + "/ Inhalt: "+ bierInhalt + "/ Typ: "+ type + "/ Menge: "+bierMenge);
	}
	
	
	public boolean equals(Object obj) {
//		if (obj == null) return false;
//		if (obj == this) return true;
		if (obj instanceof Bier) {
			if (this.bierID == ((Bier)obj).bierID)
				return true;
		}
		return false;
	}

	
	public boolean isVerfuegbar() {
		return verfuegbar;
	}
	
}



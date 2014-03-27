package BierShop.src.shop.local.valueobjects;

public class Person {

	protected String benutzername;	
	protected String password;
	protected String email;
	protected String vorname;
	protected String nachname;
	protected String name;
	protected String strasseUndHausnummer;
	protected String wohnort;
	protected int plz;
	protected float umsatz = 0;
	
	protected String lieferungVorname;
	protected String lieferungNachname;
	protected String lieferungStrasseUndHausnummer;
	protected String lieferungWohnort;
	protected int lieferungPlz;
	
	
	public String getBenutzername(){
		return benutzername;
	}
	public void setBenutzername(String b){
		this.benutzername = b;
	}
	
	
	public String getPassword(){
		return password;
	}
	public void setPassword(String pass){
		this.password = pass;
	}
	
	
	public String getEmail(){
		return email;
	}
	public void setEmail(String email){
		this.email = email;
	}
	
	
	public String getVorname(){
		return vorname;
	}
	public void setVorname(String vorname){
		this.vorname = vorname;
	}
	
	
	public String getNachname(){
		return nachname;
	}
	public void setNachname(String nachname){
		this.nachname = nachname;
	}
	
	
	public String getName(){
		
		String name = vorname +" "+ nachname;
		
		return name;
	}
	
	
	public String getStrasseUndHausnummer(){
		return strasseUndHausnummer;
	}
	public void setStrasseUndHausnummer(String strasseUndHausnr){
		this.strasseUndHausnummer = strasseUndHausnr;
	}
	
	
	public String getWohnort(){
		return wohnort;
	}
	public void setWohnort(String wohnort){
		this.wohnort = wohnort;
	}
	
	
	public int getPLZ(){
		return plz;
	}
	public void setPLZ(int plz){
		this.plz = plz;
	}
	
	public float getUmsatz(){
		return umsatz;
	}
	public void setUmsatz(float umsatz){
		this.umsatz += umsatz;
	}
	
	
	public String getLieferungVorname(){
		return lieferungVorname ;
	}
	public void setLieferungVorname(String vorname){
		this.lieferungVorname = vorname;
	}
	
	public String getLieferungNachname(){
		return lieferungNachname;
	}
	public void setLieferungNachname(String nachname){
		this.lieferungNachname = nachname;
	}
	
	public String getLieferungStrasseUndHausnummer(){
		return lieferungStrasseUndHausnummer;
	}
	public void setLieferungStrasseUndHausnummer(String strasseUndHausnr){
		this.lieferungStrasseUndHausnummer = strasseUndHausnr;
	}
	
	public int getLieferungPLZ(){
		return lieferungPlz;
	}
	public void setLieferungPLZ(int plz){
		this.lieferungPlz = plz;
	}
	
	public String getLieferungWohnort(){
		return lieferungWohnort;
	}
	public void setLieferungWohnort(String wohnort){
		this.lieferungWohnort = wohnort;
	}
		
}

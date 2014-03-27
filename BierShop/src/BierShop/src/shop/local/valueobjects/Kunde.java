package BierShop.src.shop.local.valueobjects;

public class Kunde extends Person{

	public Kunde(){

	}
	
	
	public Kunde(String benutzername,String password,String email,String vorname,String nachname, String strasseNr, String wohnort,int plz){

		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasseUndHausnummer = strasseNr;			
		this.wohnort = wohnort;
		this.plz = plz;
		
	}

	
	public Kunde(String benutzername,String password,String email,String vorname,String nachname, String strasseNr, String wohnort,int plz,float umsatz, String lvorname,String lnachname,String lstrNr, int lplz, String lwohnort){
		
		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasseUndHausnummer = strasseNr;			
		this.wohnort = wohnort;
		this.plz = plz;
		this.umsatz = umsatz;
			
		this.lieferungVorname = lvorname;
		this.lieferungNachname = lnachname;
		this.lieferungStrasseUndHausnummer = lstrNr;
		this.lieferungPlz = lplz;
		this.lieferungWohnort = lwohnort;
			
		}
	

}

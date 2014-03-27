package shop.local.valueobjects;

public class Mitarbeiter extends Person{

	public Mitarbeiter(){

	}
	

	public Mitarbeiter(String benutzername,String password,String email,String vorname,String nachname, String strasseNr ,String wohnort,int plz){

		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
		this.strasseUndHausnummer = strasseNr;
		this.wohnort = wohnort;
		this.plz = plz;
	}

	
	public Mitarbeiter(String benutzername,String password,String email,String vorname,String nachname){
		
		this.benutzername = benutzername;
		this.password = password;
		this.email = email;
		this.vorname = vorname;
		this.nachname = nachname;
	}
	
}

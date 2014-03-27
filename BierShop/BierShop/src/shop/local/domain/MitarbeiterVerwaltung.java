package shop.local.domain;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import shop.local.exceptions.BenutzernameOderPasswordFalschException;
import shop.local.exceptions.MitarbeiterExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Mitarbeiter;
import shop.local.valueobjects.Person;


public class MitarbeiterVerwaltung {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	Vector<Mitarbeiter> mitarbeiterListe = new Vector<Mitarbeiter>();
	
	private PersistenceManager pm = new FilePersistenceManager(); 
	
	private static Person mitarbeiterBesitzer = new Person();
	
	
	public void liesDaten(String datei) throws IOException{
		
		pm.openForReading(datei);
		
		Mitarbeiter einMitarbeiter;
		do{
			einMitarbeiter = pm.ladeMitarbeiter();
			if(einMitarbeiter!=null){
				try{
				einfuegen(einMitarbeiter);
				}catch(MitarbeiterExistiertBereitsException e){
					
				}
			}
		}while(einMitarbeiter!=null);
		pm.close();
		
	}
	
	
	public void schreibeDaten(String datei) throws IOException {
		
		pm.openForWriting(datei);

		if (!mitarbeiterListe.isEmpty()) {
			Iterator<Mitarbeiter> iter = mitarbeiterListe.iterator();
			while (iter.hasNext()) {
				Mitarbeiter m = (Mitarbeiter) iter.next();
				pm.speichereMitarbeiter(m);				
			}
		}				

		pm.close();
		
	}
	
	
	public Vector<Mitarbeiter> getMitarbeiter() {
		
		return mitarbeiterListe;
		
	}
	
	
	public boolean mitarbeiterLogin() throws IOException{
		
		String benutzername = "";
		String passwort = "";
		
		System.out.println("-Mitarbeiter Login-");
		System.out.print("Benutzername : ");
		benutzername = liesEingabe();
		
		System.out.print("Passwort : ");
		passwort = liesEingabe();
		
		Iterator<Mitarbeiter> iter = mitarbeiterListe.iterator();
		while(iter.hasNext()){
			Mitarbeiter m = (Mitarbeiter) iter.next();
			if(m.getBenutzername().equals(benutzername)&&m.getPassword().equals(passwort)){
				setzeBesitzer(m);
				return true;
			}
		}
		return false;
		
	}
	
	
	public void setzeBesitzer(Person p) {
		
		mitarbeiterBesitzer = p;
		
	}
	
	
	public Person gibBesitzer() {
		
		return  mitarbeiterBesitzer;
		
	}
	// Mitarbeiter Login
	/*public Mitarbeiter mitarbeiterLogin(String benutzername, String passwort) throws BenutzernameOderPasswordFalschException {

		mitarbeiterListe = getMitarbeiter();
		Iterator iter = mitarbeiterListe.iterator();
		while(iter.hasNext()){
			Mitarbeiter m = (Mitarbeiter) iter.next();
			if(m.getBenutzername().equals(benutzername) && m.getPassword().equals(passwort)){
				// Achtung, später haben Sie evtl. mehrere eingeloggte Mitarbeiter
				setzeBesitzer(m);
				return m;
			}
		}

		throw new BenutzernameOderPasswordFalschException(benutzername);
	}
	
	
	public void setzeBesitzer(Mitarbeiter m) {
		
		mitarbeiterBesitzer = m;
		
	}
	
	*/
	
	
	
	
	public void einfuegen(Mitarbeiter einMitarbeiter)  throws  MitarbeiterExistiertBereitsException {
		
		if (mitarbeiterListe.contains(einMitarbeiter)){
			throw new MitarbeiterExistiertBereitsException(einMitarbeiter, " - in 'einfuegen()' ");
		}
		mitarbeiterListe.add(einMitarbeiter);
	}
	
	

	public static String now(String dateFormat) {
		
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
		    
	}
	

	protected String liesEingabe() throws IOException {
		
		return in.readLine();
		
	}
	
}	
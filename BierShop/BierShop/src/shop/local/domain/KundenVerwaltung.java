package shop.local.domain;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import shop.local.exceptions.KundeExistiertBereitsException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Kunde;
import shop.local.valueobjects.Person;


public class KundenVerwaltung {
	
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	Vector<Kunde> kundenListe = new Vector<Kunde>();
	
	private PersistenceManager pm = new FilePersistenceManager(); 
	
	private static Person kundeBesitzer = new Kunde();

	
	public void liesDaten(String datei) throws IOException{
		
		pm.openForReading(datei);
		
		Kunde einKunde;
		do{
			einKunde = pm.ladeKunde();
			if(einKunde!=null){
				try{
				einfuegen(einKunde);
				}catch(KundeExistiertBereitsException e){
					
				}
			}
		}while(einKunde!=null);
		pm.close();
		
	}
	
	
	public void schreibeDaten(String datei) throws IOException {
		
		pm.openForWriting(datei);

		if (!kundenListe.isEmpty()) {
			Iterator<Kunde> iter = kundenListe.iterator();
			while (iter.hasNext()) {
				Kunde k = (Kunde) iter.next();
				pm.speichereKunde(k);				
			}
		}				

		pm.close();
		
	}
	
	
	public Vector<Kunde> getKunde() {
		
		return kundenListe;
		
	}
	
	
	public boolean kundenLogin() throws IOException{
				
		String benutzername = "";
		String passwort = "";
		
		System.out.println("-Kunden Login-");
		System.out.print("Benutzername : ");
		benutzername = liesEingabe();
		
		System.out.print("Passwort : ");
		passwort = liesEingabe();
		
		Iterator<Kunde> iter = kundenListe.iterator();
		while(iter.hasNext()){
			Kunde k = (Kunde) iter.next();
			if(k.getBenutzername().equals(benutzername)&& k.getPassword().equals(passwort)){
				setzeKunde(k);
				return true;
			}
		}
		return false;
		
	}

	
	public void setzeKunde(Person p) {

		kundeBesitzer = p;
		
	}
	
	
	public Person gibBesitzer() {
		
		return  kundeBesitzer;
		
	}

	
	public void einfuegen(Kunde einKunde) throws  KundeExistiertBereitsException {
		
		if (kundenListe.contains(einKunde)){
			throw new KundeExistiertBereitsException(einKunde, " - in 'einfuegen()' ");
		}
		kundenListe.add(einKunde);
		
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
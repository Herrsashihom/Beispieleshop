package BierShop.src.shop.local.domain;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import BierShop.src.shop.local.exceptions.BierExistiertBereitsException;
import BierShop.src.shop.local.exceptions.BierNichtGefundenException;
import BierShop.src.shop.local.exceptions.KundeExistiertBereitsException;
import BierShop.src.shop.local.exceptions.MitarbeiterExistiertBereitsException;
import BierShop.src.shop.local.ui.cui.MitarbeiterClientCUI;
import BierShop.src.shop.local.ui.cui.KundenClientCUI;
import BierShop.src.shop.local.valueobjects.*;

public class BierShopVerwaltung {

	SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" );
	private String date = df.format(new Date());
	
	private String datei = "";
	
	private BierVerwaltung bierVt;
	private KundenVerwaltung kundenVt;
	private MitarbeiterVerwaltung mitarbeiterVt;
	private EreignisVerwaltung ereignisVt;
	

	public BierShopVerwaltung(String datei) throws IOException {
		
		this.datei = datei;
		
		bierVt = new BierVerwaltung();
		bierVt.liesDaten(datei+"Shop.txt");
		
		mitarbeiterVt = new MitarbeiterVerwaltung();
		mitarbeiterVt.liesDaten(datei+"Shop_Mitarbeiter_Liste.txt");
		
		kundenVt = new KundenVerwaltung();
		kundenVt.liesDaten(datei+"Shop_Kunden_Liste.txt");
		
		ereignisVt = new EreignisVerwaltung();
		ereignisVt.liesDaten(datei+"Shop_Log.txt");		
		
	}
	
	
	//Mitarbeiter- und KundenZone
	
	public void mitarbeiterZone() throws Exception{
		
		boolean logInOk = false;
		
		try{
		do{
			logInOk = mitarbeiterVt.mitarbeiterLogin();
			if(logInOk){
				MitarbeiterClientCUI m = new MitarbeiterClientCUI("Bier");
				m.mClRun();
			}	
			else{
				System.out.println("Benutzername oder Passwort ist falsch!");
			}
				
		}while(!logInOk);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	

	public void kundenZone() throws Exception{

		boolean logInOk = false;
		
		try{
		do{
			logInOk = kundenVt.kundenLogin();
			if(logInOk){
				KundenClientCUI k = new KundenClientCUI("Bier");				
				k.kClRun();
			}	
			else{
				System.out.println("Benutzername oder Passwort ist falsch!");
			}
			
		}while(!logInOk);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	//Daten zuruckgeben
	
	public List<Bier> gibAlleBier(){
		
		return bierVt.getBier();
		
	}
	
	
	public Vector<Mitarbeiter> gibAlleMitarbeiter() {
		
		return mitarbeiterVt.getMitarbeiter();
		
	}
	
	
	public Vector<Kunde> gibAlleKunden() {
		
		return kundenVt.getKunde();
		
	}

	
	public Person gibPerson(String p) {

		Person person = null;
		
		if(p.equals("m")){
			person = mitarbeiterVt.gibBesitzer();
		}
		if(p.equals("k")){
			person = kundenVt.gibBesitzer();
		}
		return person;
		
	}
	
	
	//Daten schreiben
	
	public void schreibeBier() throws IOException {
		
		bierVt.schreibeDaten(datei+"Shop.txt");
		
	}
	
	
	public void schreibeMitarbeiter() throws IOException {
		
		mitarbeiterVt.schreibeDaten(datei+"Shop_Mitarbeiter_Liste.txt");
				
	}
	
	
	public void schreibeKunde() throws IOException {
		  
		kundenVt.schreibeDaten(datei+"Shop_Kunden_Liste.txt");
		
	}
	

	public void schreibeEreignis() throws IOException {
		
		ereignisVt.schreibeDaten(datei+"Shop_Log.txt");
				
	}
	
	
	//Bier-Verwaltung
	
	public Bier sucheBier(String bierName) {
		Bier bier;
		try {
			bier = bierVt.suchEinBier(bierName);
		} catch (BierNichtGefundenException e) {
			e.printStackTrace();
			return null;
		}
		return bier;
	}
	
	
	public Vector<Bier> sucheBierNachName (String bierName) throws BierNichtGefundenException {
		
		Vector<Bier> ergebnisBierName = new Vector<Bier>();
//		try{
			ergebnisBierName = bierVt.sucheBierNachName(bierName);
//		}catch (BierNichtGefundenException e){
//			e.printStackTrace();
//			return null;
//		}

		return ergebnisBierName;
	}
	
	
	public void fuegeBierEin(String bierID, String bierName, float bierPreis, String bierInhalt, String type, int bierMenge, String personName) throws BierExistiertBereitsException {
		
		String zustand ="Einlagern: ";
		
		Bier b = new Bier(bierID, bierName, bierPreis, bierInhalt, type, bierMenge);
		Ereignis e = new Ereignis(date, zustand, personName, bierName, bierMenge);
		
		bierVt.einfuegen(b);
		ereignisVt.einfuegen(e);

	}
	
	
	public void bierLoeschen(Bier b, String personName) throws BierNichtGefundenException {
			
		    String zustand = "Auslagern: ";
		    
			String bierName = b.getbierName();
			int bierMenge = b.getbierMenge();
						
			Ereignis e = new Ereignis(date, zustand, personName, bierName, bierMenge);
				
			bierVt.loeschenBier(b);
			ereignisVt.einfuegen(e);
			
	}
	
	
	public void nachBierIDSortieren() throws IOException {
		
		boolean ok = bierVt.nachIDSortieren();
		
		if(ok){
			schreibeBier();	
		}
		else{
			System.out.println("Error beim Sortieren");
		}
		
	}
	
	
	public void setBierMenge(Bier suchBier, int i, String personName) throws BierNichtGefundenException {
		
		String zustand = "Menge aendern: ";
		
		String bierName = suchBier.getbierName();
					
		Ereignis e = new Ereignis(date, zustand, personName, bierName, i);
		
		suchBier.setbierMenge(i);
		ereignisVt.einfuegen(e);
		
	}
	
	
	public void setBierMenge(Vector<KaufBier> warenkorb) throws Exception{
		
		Iterator<KaufBier> iter = warenkorb.iterator();
		while(iter.hasNext()){
			KaufBier kBier = (KaufBier)iter.next();
			Bier b = sucheBier(kBier.getbierName());
			b.setbierMenge(b.getbierMenge()- kBier.getKaufAnzahl());
			
		}
	}
	
	
	public void setMengeNachEinkauf(Vector<KaufBier> warenkorb, String personName) throws Exception {
		
		String zustand = "Einkaufen: ";
		
		Iterator<KaufBier> iter = warenkorb.iterator();
		while(iter.hasNext()){
			
			KaufBier kBier = (KaufBier)iter.next();
			
			Ereignis e = new Ereignis(date, zustand, personName, kBier.getbierName(), kBier.getKaufAnzahl());
			ereignisVt.einfuegen(e);
			
		}
		
		bierVt.mengeNachEinkauf(warenkorb);

	}
	
	
	//Kunde-Einfuegen
	
	public void fuegeKundeEin(String benutzername, String password, String email, String vorname, String nachname, String strUndHausnummer, String ort, int plz)  throws KundeExistiertBereitsException {
	
		Kunde k = new Kunde(benutzername, password, email, vorname, nachname, strUndHausnummer, ort, plz);

		kundenVt.einfuegen(k);
		
	}
	
	
	//Mitarbeiter-Einfuegen
	
	public void fuegeMitarbeiterEin (String benutzername, String password, String email, String vorname, String nachname, String strUndHausnummer, String ort, int plz) throws MitarbeiterExistiertBereitsException {
	
		Mitarbeiter m = new Mitarbeiter (benutzername, password, email, vorname, nachname, strUndHausnummer, ort, plz);

		mitarbeiterVt.einfuegen(m);
		
	}
	
}
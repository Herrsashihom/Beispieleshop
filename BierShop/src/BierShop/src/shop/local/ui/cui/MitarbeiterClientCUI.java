package BierShop.src.shop.local.ui.cui;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import BierShop.src.shop.local.domain.BierShopVerwaltung;
import BierShop.src.shop.local.exceptions.BierNichtGefundenException;
import BierShop.src.shop.local.valueobjects.Bier;
import BierShop.src.shop.local.valueobjects.Person;


public class MitarbeiterClientCUI extends ShopClientCUI{
	
	
	private BierShopVerwaltung bierShopVt;
	
    private Person p;
	
	
	public MitarbeiterClientCUI(String datei) throws IOException, Exception {
	
		super(datei);
		
		bierShopVt = new  BierShopVerwaltung(datei);
		
	}
	
	
	private void gibMenueAus(){
		
		System.out.println("Bierliste ausgeben (1)");
		System.out.println("Bier suchen (2)");
		System.out.println("Bier anlegen (3)");
		System.out.println("Bier loeschen (4)");
		System.out.println("Biermenge bearbeiten (5)");
		System.out.println("Mitarbeiter anlegen (6)");		
		System.out.println("Beenden (q)");
		System.out.println(": ");
		System.out.flush();
		
	}


	private void verarbeiteEingabe(String line, String personName) throws IOException, Exception {
		
		if (line.equals("1")) { 
			
			List<Bier> bierListe = bierShopVt.gibAlleBier();
			gibBierlisteAus(bierListe);
			
		}
		
		else if (line.equals("2")) {
			
			System.out.println("Biername  > ");
			String biername = liesEingabe();
			
			Vector<Bier> liste;
			try {
				liste = bierShopVt.sucheBierNachName(biername);
				gibBierlisteAus(liste);
			} catch (BierNichtGefundenException e) {
				System.out.println(e.getMessage());
			}			
		}
		
		else if (line.equals("3")) {

			System.out.print("Bier-ID  > ");
			String bierID = liesEingabe();
			
			System.out.print("Biername  > ");
			String bierName = liesEingabe();
			
			System.out.print("Preis  > ");
			String bierPreis = liesEingabe();
			float preis = Float.parseFloat(bierPreis);
				
			System.out.print("Menge  > ");
			String bierMenge = liesEingabe();
			int menge = Integer.parseInt(bierMenge);
			
			System.out.print("Inhalt  > ");
			String inhalt = liesEingabe();
			
			System.out.print("Biertyp  > ");
			String type = liesEingabe();
			
			bierShopVt.fuegeBierEin(bierID, bierName, preis, inhalt, type, menge, personName);
			
			System.out.println("Einfuegen erfolgreich.");
			
			bierShopVt.nachBierIDSortieren();
			bierShopVt.schreibeEreignis();

		}
		
		else if (line.equals("4")) {
			
			System.out.println("Biername  > ");
			String removeName = liesEingabe();
			
			Bier b = bierShopVt.sucheBier(removeName);
			
			if(b != null){
				
				bierShopVt.bierLoeschen(b, personName);
				System.out.println("Das Bier wurde ausgelagert.\n");
				
				bierShopVt.schreibeEreignis();
			
			}else{
				System.out.println("Bier nicht gefunden!");
			}

		}
		
		else if (line.equals("5")) {
			
			System.out.println("Biername  > ");
			String biername = liesEingabe();
			
			Bier suchBier = bierShopVt.sucheBier(biername);
			
			if(suchBier!=null){
				System.out.println("Menge  > ");
				String StringMenge = liesEingabe();
				int menge = Integer.parseInt(StringMenge);
				
				if((suchBier.getbierMenge()+menge)<0){
					System.out.print("Falsche Menge!!");
				}
				else{
					
					bierShopVt.setBierMenge(suchBier, menge+suchBier.getbierMenge(), personName);
					System.out.println("Die Menge wurde geandert.");
					
					bierShopVt.schreibeEreignis();
					
				}
			}
			
			else{
				System.out.print("Bier nicht gefunden!");
			}
			
		}
		
		else if (line.equals("6")) {
	
			System.out.println("**** Mitarbeiter anlegen ****\n");
			System.out.print("Geben Sie den Benutzername ein > ");
			String benutzername = liesEingabe();
			System.out.print("Geben Sie Passwort ein > ");
			String password = liesEingabe();
			System.out.print("Geben Sie Ihre E-Mailadresse ein > ");
			String email = liesEingabe();
			System.out.print("Geben Sie Ihren Vorname ein > ");
			String vorname = liesEingabe();
			System.out.print("Geben Sie Ihren Nachname ein > ");
			String nachname = liesEingabe();
			System.out.print("Geben Sie Ihre Strasse und Hausnummer ein > ");
			String strUndHausnummer = liesEingabe();
			System.out.print("Geben Sie Ihren Ort ein > ");
			String ort = liesEingabe();
			System.out.print("Geben Sie Ihre Postleizahl ein > ");
			int plz = Integer.parseInt(liesEingabe());
			
			bierShopVt.fuegeMitarbeiterEin(benutzername, password, email, vorname, nachname, strUndHausnummer, ort, plz);
			
			bierShopVt.schreibeMitarbeiter();
			System.out.print("Mitarbeiter angelegt!\n");
			
		}

	}
	
	
	private void gibBierlisteAus(List<Bier> bierliste) {
		
		if (bierliste.isEmpty()) {
			System.out.println("Bierliste ist leer.");
		} else {
			Iterator<Bier> iter = bierliste.iterator();
			while (iter.hasNext()) {
				Bier bier = iter.next();
				System.out.println(bier.toString());
			}
		}
		
	}
	

	public void mClRun() throws Exception {
		
		String input = "";
		
		p = bierShopVt.gibPerson("m");
		String pName = p.getName();
		
		do{
			gibMenueAus();
			try{
				input = liesEingabe();
				verarbeiteEingabe(input, pName);
				bierShopVt.schreibeBier();
			}catch (IOException e){
				e.printStackTrace();
			}
		}while (!input.equals("q"));
	}
	
}

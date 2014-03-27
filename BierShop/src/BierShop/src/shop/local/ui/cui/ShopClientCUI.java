package BierShop.src.shop.local.ui.cui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import BierShop.src.shop.local.ui.cui.ShopClientCUI;
import BierShop.src.shop.local.domain.BierShopVerwaltung;


public class ShopClientCUI {

	private static BufferedReader in;
	
	private BierShopVerwaltung bierShopVt;
	
	
	public ShopClientCUI(String datei) throws IOException {
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		bierShopVt = new BierShopVerwaltung(datei);
		
	}
	
	
	public static void main(String[] args) throws Exception {
		
		ShopClientCUI cui;
		try {
			cui = new ShopClientCUI("Bier");
			cui.run();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void run() throws Exception {

		String input = ""; 
	
			try {
				input = zoneWaehlen();
				
				if(input.equals("1")){
					bierShopVt.mitarbeiterZone();
				}
				else if(input.equals("2")){
					kundenWahl();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}

	
	public String zoneWaehlen() throws IOException{
		
		String wahl;
		do{
			System.out.println("Mitarbeiter Zone(1)");
			System.out.println("Kunden Zone(2)");
			System.out.print(": ");
			
			wahl = liesEingabe();
			
		}while(!((wahl.equals("1") || wahl.equals("2"))));
		return wahl;
		
	}
	
	
	public void kundenWahl() throws Exception{
		
		String kWahl;
		
		do{
			System.out.println("Neue Kunde Registrieren(1)");
			System.out.println("Kunden Einloggen(2)");
			System.out.println("Beenden (q)");
			System.out.print(": ");
			kWahl = liesEingabe();
			
			if (kWahl.equals("1")){
				
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
				
				bierShopVt.fuegeKundeEin(benutzername, password, email, vorname, nachname, strUndHausnummer, ort, plz);
				
				bierShopVt.schreibeKunde();
				System.out.print("Registrieren erfolgreich!\n");
				
			}else if (kWahl.equals("2")) {
				bierShopVt.kundenZone();
			}
			
		}while((kWahl.equals("1") || kWahl.equals("2")));
			
	}
	
	
	protected String liesEingabe() throws IOException {
		
		return in.readLine();
		
	}	
	
}

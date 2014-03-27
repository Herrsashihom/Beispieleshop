package shop.local.domain;


import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.local.exceptions.BierExistiertBereitsException;
import shop.local.exceptions.BierNichtGefundenException;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;
import shop.local.valueobjects.Bier;
import shop.local.valueobjects.KaufBier;


public class BierVerwaltung {
	
	private List<Bier> bierListe = new Vector<Bier>();
	
	private PersistenceManager pm = new FilePersistenceManager();
	

	public void liesDaten(String datei) throws IOException {
	
		pm.openForReading(datei);

		Bier einBier;
		do {
			einBier = pm.ladeBier();
			if (einBier != null) {
				try {
					einfuegen(einBier);
				} catch (BierExistiertBereitsException e) {}
			}
		} while (einBier != null);

		pm.close();
		
	}


	public void schreibeDaten(String datei) throws IOException  {

		pm.openForWriting(datei);

		Iterator<Bier> iter = bierListe.iterator();
		while (iter.hasNext()) {
			Bier b = iter.next();
			pm.speichereBier(b);				
		}
		
		pm.close();
		
	}
		
	
	public Bier suchEinBier(String bierName) throws BierNichtGefundenException {
				
		Iterator<Bier> iter = bierListe.iterator();
		while (iter.hasNext()) {
			Bier bier = iter.next();
			if ( bier.getbierName().equals(bierName)) {
				return bier;
			}
		}
		throw new BierNichtGefundenException(bierName);
		
	}
	

	public Vector<Bier> sucheBierNachName (String bierName) throws BierNichtGefundenException{
		
		Vector<Bier> ergebnis = new Vector<Bier>();
		
		Iterator<Bier> iter = bierListe.iterator();
		while (iter.hasNext()){
			Bier b = iter.next();
			if (b.getbierName().equals(bierName)){
				ergebnis.add(b);
			}
		}
		if(ergebnis.isEmpty()){
			throw new BierNichtGefundenException(bierName);
		}

		return ergebnis;
	}
	
	
	public List<Bier> getBier() {
		
		return bierListe;
		
	}
	
	
	public void einfuegen(Bier einBier) throws BierExistiertBereitsException {
		
		if (bierListe.contains(einBier)) {
			throw new BierExistiertBereitsException(einBier, " - in 'einfuegen()'");
		}

		bierListe.add(einBier);
		
	}
	
	
	public boolean loeschenBier(Bier b) throws BierNichtGefundenException{
		
			bierListe.remove(b);
			return true;

	}
	

	public boolean nachIDSortieren() {
		
		try{
		Collections.sort(bierListe, new Comparator<Object>() {
			public int compare(Object t1, Object t2) {
				Bier b1 = (Bier) t1;
				Bier b2 = (Bier) t2;
				return (b1.getID()).compareTo((b2.getID()));
			}
		});
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	///---Warenkorb---///

	public float summeBerechnen(Vector<KaufBier> bier) {

		float summe = 0;
		Iterator<KaufBier> iter = bier.iterator();
		while(iter.hasNext()){
			KaufBier kBier = (KaufBier)iter.next();
			summe = summe + (kBier.getbierPreis()* kBier.getKaufAnzahl());
		}
		return summe;
		
	}


	///---Kasse---///
	
	public void mengeNachEinkauf(Vector<KaufBier> warenkorb) {
		
		Iterator<Bier> iter = bierListe.iterator();
		
		while(iter.hasNext()){
			
			Bier b = (Bier)iter.next();
			
			Iterator<KaufBier> iter2 = warenkorb.iterator();
			
			while(iter2.hasNext()){
				KaufBier kBier = (KaufBier)iter2.next();
				if(b.getID() == kBier.getID()){
					b.setbierMenge(b.getbierMenge()- kBier.getKaufAnzahl());
				}				
			}
		}
		
	}

}
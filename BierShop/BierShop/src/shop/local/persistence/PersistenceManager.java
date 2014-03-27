package shop.local.persistence;


import java.io.IOException;

import shop.local.valueobjects.*;


public interface PersistenceManager {

	
	public void openForReading(String datenquelle) throws IOException;
	
	
	public void openForWriting(String datenquelle) throws IOException;
	
	
	public boolean close();
	
	
	///---EREIGNIS---///
	
	
	public boolean speichereEreignis(Ereignis e) throws IOException;
	
	
	public Ereignis ladeEreignis()  throws IOException;


	///---BIER---///
	
	public boolean speichereBier(Bier b) throws IOException;
	
	
	public Bier ladeBier() throws IOException;


	///---MITARBEITER---///
	
	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException ;
	
	
	public Mitarbeiter ladeMitarbeiter() throws IOException;
	
		
	///---KUNDE---///
	
	public boolean speichereKunde(Kunde k) throws IOException;

	
	public Kunde ladeKunde() throws IOException;
	
}
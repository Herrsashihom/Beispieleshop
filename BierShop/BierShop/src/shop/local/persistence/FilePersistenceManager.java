package shop.local.persistence;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import shop.local.valueobjects.*;


public class FilePersistenceManager implements PersistenceManager {

	private BufferedReader reader = null;
	private PrintWriter writer = null;	
	
	
	public void openForReading(String datei) throws FileNotFoundException {
		
		reader = new BufferedReader(new FileReader(datei));
	}

	
	public void openForWriting(String datei) throws IOException {
		
		writer = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
	}

	
	public boolean close() {
		
		if (writer != null)
			writer.close();
		
		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				return false;
			}
		}

		return true;
		
	}
	
	
	private String liesDaten() throws IOException {
		
		if (reader != null)
			return reader.readLine();
		else
			return "";
		
	}

	
	private void schreibeDaten(String daten) {
		
		if (writer != null)
			writer.println(daten);
		
	}

	
	///---EREIGNIS--///

	public boolean speichereEreignis(Ereignis e) throws IOException {
		
		schreibeDaten(e.getDate());
		schreibeDaten(e.getZustand());
		schreibeDaten(e.getPersonName());
		schreibeDaten(e.getbierName());
		schreibeDaten(new Integer(e.getMenge()).toString());
		
        return true;
		
	}
	
	
	public Ereignis ladeEreignis() throws IOException{
		
		String date = liesDaten();
		if (date == null) {
			return null;
		}

		String zustand = liesDaten();
		
		String personName = liesDaten();
		
		String bierName = liesDaten();
		
		String stringMenge = liesDaten();
		int bierMenge = Integer.parseInt(stringMenge);
		
		return new Ereignis(date, zustand, personName, bierName, bierMenge);
		
	}
	
	
	///---BIER---///
		
	public boolean speichereBier(Bier b) throws IOException {
		
		schreibeDaten(b.getID());
		schreibeDaten(b.getbierName());		
		schreibeDaten(new Float(b.getbierPreis()).toString());
		schreibeDaten(b.getbierInhalt());
		schreibeDaten(b.getType());
		schreibeDaten(new Integer(b.getbierMenge()).toString());
		
		if (b.isVerfuegbar())
			schreibeDaten("t");
		else
			schreibeDaten("f");
		
		return true;
		
	}
	
	
	public Bier ladeBier() throws IOException {
		
		String bierID = liesDaten();
		if (bierID == null) {
			return null;
		}
		
		String bierName = liesDaten();
		
		String bierPreisString = liesDaten();
		float bierPreis = Float.parseFloat(bierPreisString);
		
		String bierInhalt = liesDaten();
		
		String type = liesDaten();
		
		String bierMengeString = liesDaten();
		int bierMenge = Integer.parseInt(bierMengeString);
		
		String verfuegbarCode = liesDaten();

		boolean verfuegbar = false;
		if (verfuegbarCode.equals("t"))
			verfuegbar = true;

		return new Bier(bierID, bierName, bierPreis, bierInhalt, type, bierMenge, verfuegbar);
	}

	
	///---MITARBEITER---///

	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException{
		
		schreibeDaten(m.getBenutzername());
		schreibeDaten(m.getPassword());
		schreibeDaten(m.getEmail());
		schreibeDaten(m.getVorname());
		schreibeDaten(m.getNachname());
		schreibeDaten(m.getStrasseUndHausnummer());
		schreibeDaten(m.getWohnort());
		schreibeDaten(new Integer (m.getPLZ()).toString());
					
		return true;
		
	}
	
	
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		
		String benutzername = liesDaten();
		if(benutzername == null){
			return null;
		}
		
		String password = liesDaten();
		String email = liesDaten();
		String vorname = liesDaten();
		String nachname = liesDaten();
		String strUndHausnummer = liesDaten();
		String ort = liesDaten();
		
		String StringPlz = liesDaten();
		int plz = Integer.parseInt(StringPlz);
		

		return new Mitarbeiter (benutzername, password,email,vorname,nachname,strUndHausnummer,ort,plz);
		
	}


	///---KUNDE---///
	
	public boolean speichereKunde(Kunde k) throws IOException{

		schreibeDaten(k.getBenutzername());
		schreibeDaten(k.getPassword());
		schreibeDaten(k.getEmail());
		schreibeDaten(k.getVorname());
		schreibeDaten(k.getNachname());
		schreibeDaten(k.getStrasseUndHausnummer());
		schreibeDaten(k.getWohnort());
		schreibeDaten(new Integer (k.getPLZ()).toString());
		
		return true;
		
	}

	
	public Kunde ladeKunde() throws IOException {

		String benutzername = liesDaten();
		if(benutzername == null){
			return null;
		}
		
		String password = liesDaten();
		String email = liesDaten();
		String vorname = liesDaten();
		String nachname = liesDaten();
		String strUndHausnummer = liesDaten();
		String ort = liesDaten();
		
		String StringPlz = liesDaten();
		int plz = Integer.parseInt(StringPlz);
		

		return new Kunde (benutzername, password,email,vorname,nachname,strUndHausnummer,ort,plz);
		
	}
	
}

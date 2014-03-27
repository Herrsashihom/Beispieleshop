package shop.local.domain;


import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;
import java.util.List;

import shop.local.valueobjects.Ereignis;
import shop.local.persistence.FilePersistenceManager;
import shop.local.persistence.PersistenceManager;


public class EreignisVerwaltung {

    private List<Ereignis> ereignisListe = new Vector<Ereignis>();
        
    private PersistenceManager pm = new FilePersistenceManager();
    

    public void liesDaten(String datei) throws IOException {
        
        pm.openForReading(datei);
                
        Ereignis einEreignis;
        do {
            einEreignis = pm.ladeEreignis();
            if (einEreignis != null) {
            	einfuegen(einEreignis);
            }
        } while (einEreignis != null);

        pm.close();
        
    }
        
        
    public void schreibeDaten(String datei) throws IOException  {         
                
        pm.openForWriting(datei);
                        
        Iterator<Ereignis> iter = ereignisListe.iterator();
        while (iter.hasNext()) {
            Ereignis e = iter.next();
            pm.speichereEreignis(e);                                
        }
                        
        pm.close();
        
    }
                
        
    public void einfuegen(Ereignis einEreignis) {
                
    	ereignisListe.add(einEreignis);
        
    }

        
    public List<Ereignis> getEreignisListe() {
    	
        return ereignisListe;
        
    }
        
      
}
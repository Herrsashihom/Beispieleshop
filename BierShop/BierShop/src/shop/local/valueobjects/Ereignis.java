package shop.local.valueobjects;

public class Ereignis {
	
	protected String date;
	protected String zustand;
	protected String personName;
	protected String bierName;
	protected int bierMenge;	
	
	
	public Ereignis(String date,String zustand, String personName, String bierName, int menge) {
		
        this.date = date;
        this.zustand = zustand;
        this.personName = personName;
        this.bierName = bierName;
        this.bierMenge = menge; 
                
	}


	public String getDate() {
        return date;
    }
    public void setDate(String ndate) {
        this.date = ndate;
    }
    
    
    public String getZustand() {
        return zustand;
    }
    public void setZustand(String nzustand) {
        this.zustand = nzustand;
    }

        
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String npName) {
        this.personName = npName;
    }
    
    
    public String getbierName() {
        return bierName;
    }
    public void getbierName(String nbName) {
        this.bierName = nbName;
    }
    
    
    public int getMenge() {
      	return bierMenge; 
     }    
    public void setMenge(int nmenge) {
       	this.bierMenge = nmenge; 
    }
 
}
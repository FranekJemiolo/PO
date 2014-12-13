package wymiana;

public class Strona {
	private int numer;
	private boolean bit;
	private int czasWPamieci;
	private int czasOdUzycia;
	
	public Strona(int nr){
		this.numer = nr;
		this.bit = false;
		this.czasWPamieci = 0;
		this.czasOdUzycia = 0;
	}
	
	
	public int getNumer(){
		return this.numer;
	}
	
	public boolean getBit(){
		return this.bit;
	}
	
	public int getCzasWPamieci(){
		return this.czasWPamieci;
	}
	
	public int getCzasOdUzycia(){
		return this.czasOdUzycia;
	}
	
	public void setBit(boolean b){
		this.bit = b;
	}
	
	public void setCzasWPamieci(int czas){
		this.czasWPamieci = czas;
	}
	
	public void setCzasOdUzywania(int czas){
		this.czasOdUzycia = czas;
	}
}

package wymiana;

public class Ramka {
	private Strona strona;
	public void setStrona(Strona s){
		this.strona = s;
	}
	public Strona getStrona(){
		return this.strona;
	}
	public boolean czyPusta(){
		return this.strona == null;
	}
	public Ramka(){
		this.strona = null;
	}
}

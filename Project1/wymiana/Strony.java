package wymiana;

public class Strony {
	protected Strona[] strony;
	protected int length;
	public Strony(int rozmiar){
		//Tworzymy tablicę stron i inicjalizujemy je na null.
		this.strony = new Strona[rozmiar];
		this.length = rozmiar;
		for(int i=0; i < rozmiar; i++){
			this.strony[i] = null;
		}
	}
	public Strona getStrona(int nr){
		//Zwraca stronę o numerze zadanym przez parametr.
		int i=0;
		while(i < this.strony.length){
			if((this.strony[i]!= null)&&(this.strony[i].getNumer() == nr)){
				return this.strony[i];
			}
			else{
				i++;
			}
		}
		return null;
	}
	public void utwórzStrone(int nr){
		//Tworzy stronę o numerze zadanym przez parametr.
		int j = 0;
		boolean twórz = true;
		//Sprawdzamy czy istnieje już taka strona.
		for(int k = 0; k < this.strony.length; k++){
			if((this.strony[k] != null)&&(this.strony[k].getNumer() == nr)){
				twórz = false;
			}
		}
		if(twórz){
		while(j < this.strony.length){
			if(this.strony[j] == null){
				this.strony[j] = new Strona(nr);
				return;
			}
			else{
				j++;
			}
		}
		}
	}
}

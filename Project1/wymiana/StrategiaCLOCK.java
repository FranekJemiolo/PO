package wymiana;

public class StrategiaCLOCK extends Strategia {
	public StrategiaCLOCK(Odwołania o){
		super(o);
	}
	
	@Override
	public boolean wstawDoRamki(Strona s){
		int j = 0;
		while(j < ramki.length){
			if(this.ramki[j].czyPusta()){
				s.setBit(true);
				this.ramki[j].setStrona(s);
				//Wstawiamy na koniec kolejki stronę.
				this.kolejka.wstaw(s);
				kom.wypisz(0, s.getNumer());
				return true;
			}
			else{
				j++;
			}
		}
		return false;
		
	}
	
	@Override
	public void zwolnijRamkeiWstaw(Strona s){
		s.setBit(true);
		Strona usuwana;
		Strona zostaje;	
		usuwana = this.kolejka.poczatek();
		//W poniższej pętli znajdujemy ramkę do zwolnienia.
		while(usuwana.getBit()){
			zostaje = this.kolejka.usunPoczatek();
			zostaje.setBit(false);
			this.kolejka.wstaw(zostaje);
			usuwana = this.kolejka.poczatek();
		}
		usuwana = this.kolejka.usunPoczatek();
		//Wstawiamy do kolejki nową stronę.
		this.kolejka.wstaw(s);
		//Wypisujemy komunikat.
		kom.wypisz(usuwana.getNumer(), s.getNumer());
		for(int i = 0; i < ramki.length; i++){
			if(this.ramki[i].getStrona().getNumer() == usuwana.getNumer()){
				//Ustawiamy stronę w ramce zwolnionej.
				this.ramki[i].setStrona(s);
			}
		}
	}
	@Override
	public void obslugaOdwolania(Strona s){
		for(int j = 0; j < ramki.length; j++){
			if((this.ramki[j]!=null)&&(this.ramki[j].getStrona()!= null)){
				if(this.ramki[j].getStrona().getNumer() == s.getNumer()){
					//Ustawiamy bit odwołania na 1 dla strony do której sie odwołujemy.
					s.setBit(true);
					this.ramki[j].setStrona(s);
					this.kolejka.ustaw(s);
				}
			}
		}
	}
}

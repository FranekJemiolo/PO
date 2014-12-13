package wymiana;

public class StrategiaLRU extends Strategia {
	public StrategiaLRU(Odwołania o){
		super(o);
	}
	
	@Override
	public boolean wstawDoRamki(Strona s){
		int j = 0;
		while(j < ramki.length){
			if(this.ramki[j].czyPusta()){
				this.ramki[j].setStrona(s);
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
		int j = 0;
		this.kolejka.wstaw(s);
		while(j < this.ramki.length){
			//Usuwamy z początku kolejki.
			if(this.ramki[j].getStrona().getNumer() == this.kolejka.poczatek().getNumer()){
				kom.wypisz(this.kolejka.usunPoczatek().getNumer(),s.getNumer());
				this.ramki[j].setStrona(s);
				return;
			}
			else{
				j++;
			}
		}
	}
	@Override
	public void obslugaOdwolania(Strona s){
		Strona ss;
		for(int j = 0; j < ramki.length; j++){
			if((this.ramki[j]!=null)&&(this.ramki[j].getStrona()!= null)){
				ss = this.ramki[j].getStrona();
				//Ustawiamy na koniec kolejki stronę, do której nastąpiło odwołanie.
				//Czasy w stronach były używane tylko do debugowanie.
				if(this.ramki[j].getStrona().getNumer() == s.getNumer()){
					ss = s;
					this.kolejka.usun(s);
					ss.setCzasOdUzywania(0);
					this.ramki[j].setStrona(ss);
					this.kolejka.wstaw(ss);
				}
				else{
					ss.setCzasOdUzywania(ss.getCzasOdUzycia()+1);
				}
				ss.setCzasWPamieci(ss.getCzasWPamieci()+1);
				this.ramki[j].setStrona(ss);

			}
		}
	}
	
}

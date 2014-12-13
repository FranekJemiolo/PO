package wymiana;

public class StrategiaFIFO extends Strategia {
	public StrategiaFIFO(Odwołania o){
		super(o);
	}
	
	@Override
	public boolean wstawDoRamki(Strona s){
		int j = 0;
		while(j < ramki.length){
			if(this.ramki[j].czyPusta()){
				this.ramki[j].setStrona(s);
				//Wstawiamy na koniec kolejki.
				this.kolejka.wstaw(s);
				kom.wypisz(0,s.getNumer());
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
		//Wstawiamy na koniec kolejki.
		while(j < this.ramki.length){
			if(this.ramki[j].getStrona().getNumer() == this.kolejka.poczatek().getNumer()){
				kom.wypisz(this.kolejka.usunPoczatek().getNumer(), s.getNumer());
				//Usuwamy z poczatku kolejki stronę.
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
	//W zasadzie wszystko zostało tutaj wykomentowane bo służyło jedynie do debugowania.
	/*	Strona ss;

		for(int j = 0; j < ramki.length; j++){
			if((this.ramki[j]!=null)&&(this.ramki[j].getStrona()!= null)){
				ss = this.ramki[j].getStrona();

				if(this.ramki[j].getStrona().getNumer() == s.getNumer()){
					ss = s;
					ss.setCzasOdUzywania(0);
					this.ramki[j].setStrona(ss);
				}
				else{
					ss.setCzasOdUzywania(ss.getCzasOdUzycia()+1);
				}
				ss.setCzasWPamieci(ss.getCzasWPamieci()+1);
				this.ramki[j].setStrona(ss);

			}
		}*/
	}
}

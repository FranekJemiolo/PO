package wymiana;

public class StrategiaOPT extends Strategia {
	public StrategiaOPT(Odwołania o){
		super(o);
		this.indeks = 0;

	}
	@Override
	public boolean wstawDoRamki(Strona s){
		int j = 0;
		while(j < this.ramki.length){
			if(this.ramki[j].czyPusta()){
				this.ramki[j].setStrona(s);
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
		int akt = 0;
		int max = 0;
		int k = 0;
		Ramka zwalniana = this.ramki[0];
		Ramka aktualna;
		//Znajdujemy najdłużej nie używaną stronę.
		for(int j = 0; j < this.ramki.length; j++){
			aktualna = this.ramki[j];
			akt = 0;
			k = this.indeks+1; 
			while((k < this.tablicaWejscia.length)&&(aktualna.getStrona().getNumer() != this.tablicaWejscia[k])){
				k++;
				akt++;
			}
			if (akt > max){
				max = akt;
				zwalniana = aktualna;
			}
			else if(akt == max){
				if(aktualna.getStrona().getNumer() < zwalniana.getStrona().getNumer()){
					zwalniana = aktualna;
				}
			}
		}
		kom.wypisz(zwalniana.getStrona().getNumer(), s.getNumer());
		int i = 0;
		while(i < this.ramki.length){
			//Musimy znaleźć ramkę, w której jest zwalniana strona.
			if(this.ramki[i] == zwalniana){
				break;
			}
			else{
				i++;
			}
		}
			this.ramki[i].setStrona(s);
	}
	@Override
	public void obslugaOdwolania(Strona s){
		//Zwiększamy indeks tablicy.
		this.indeks++;
	}
}

package wymiana;

public abstract class Strategia {
	String nazwa;
	Kolejka kolejka = new Kolejka();
	Strony strony;
	Ramka[] ramki;
	Komunikaty kom = new Komunikaty();
	int indeks;
	int[] tablicaWejscia;
	int iloscStron;
	Strona wstawiana;
	Strona usuwana;
	public Strategia(Odwołania o){
		//Inicjalizacja Strategii
		int akt;
		this.iloscStron = 0;
		this.nazwa = o.nazwaStrategii;
		//W tej pętli zliczamy ilość różnych stron występujących w podanej tablicy.
		for(int i = 0; i < o.bufor.length; i++){
			akt = o.bufor[i];
			int rozne = 1;
			int j = i+1;
			while (j < o.bufor.length){
				if (o.bufor[j] != akt){
					rozne++;
				}
				j++;
			}
			if (rozne == o.bufor.length-i){
				this.iloscStron++;
			}
			
		}
		//Tworzymy strony i ramki.
		this.strony = new Strony(iloscStron);
		this.ramki = new Ramka[o.iloscRamek];
		//Każdą ramkę inicjujemy pustą stroną.
		for(int k = 0; k < o.iloscRamek; k++){
			this.ramki[k] = new Ramka();
		}
		//Kopiujemy tablicę podaną na wejściu.
		this.tablicaWejscia = new int[o.bufor.length];
		this.tablicaWejscia = o.bufor;
		this.indeks = 0;
	}
	
	public boolean czyWstawic(Strona s){
		//W tej procedurze sprawdzamy czy potrzeba wstawić do pamięci jakąś stronę.
		int j = 0;
		while(j < ramki.length){
			if((!ramki[j].czyPusta())&&(ramki[j].getStrona().getNumer() == s.getNumer())){
				return false;
			}
			else{
				j++;
			}
		}
		return true;
	}
	
	public abstract boolean wstawDoRamki(Strona s);
	//Procedura służąca do wstawienia strony do ramki, jeśli posiadamy pustą ramkę.
	
	public abstract void zwolnijRamkeiWstaw(Strona s);
	//W tej procedurze będziemy zwalniać wybraną według strategii stronę w ramce i wstawiać tam nową.
	
	public abstract void obslugaOdwolania(Strona s);
	//W tej procedurze będziemy obsługiwali moment odwołania się do strony.
}

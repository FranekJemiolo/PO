package wymiana;

public class Odwołania {
	
	private Strategia strategia;
	protected String nazwaStrategii;
	protected int[] bufor;
	private int bledy;
	private int odwolania;
	protected int iloscRamek;
	public Odwołania(int[] tab, String s, int rozmiar){
		this.bufor = new int[tab.length];
		this.bufor = tab;
		this.nazwaStrategii = s;
		this.iloscRamek = rozmiar;

	}
	
	public void wykonaj() throws Exception{
		switch(this.nazwaStrategii){
		case "OPT":{
			this.strategia = new StrategiaOPT(this);
			break;
		}
		case "FIFO":{
			this.strategia = new StrategiaFIFO(this);
			break;
		}
		case "CLOCK":{
			this.strategia = new StrategiaCLOCK(this);
			break;
		}
		case "LRU":{
			this.strategia = new StrategiaLRU(this);
			break;
		}
		default:{
			throw new Exception("Niepoprawna strategia, nie mogę uruchomić metody wykonaj");
		}
	}
		this.bledy = 0;
		this.odwolania = 0;
		Komunikaty kom = new Komunikaty();
		kom.wypiszPoczatek(this.nazwaStrategii, this.iloscRamek);
		for(int i = 0; i < this.bufor.length; i++){
			//Tworzymy stronę, jeśli jeszcze taka nie istnieje.
			this.strategia.strony.utwórzStrone(this.bufor[i]);
			if(this.strategia.czyWstawic(this.strategia.strony.getStrona(this.bufor[i]))){
				//Sprawdzamy czy mamy wstawić stronę do ramki, jeśli nie to tylko obsługujemy odwołanie do niej.
				if(this.strategia.wstawDoRamki(this.strategia.strony.getStrona(this.bufor[i]))){
				}
				else{
					//Zwalniamy stronę w ramcę i wstawiamy na jej miejsce stronę.
					this.strategia.zwolnijRamkeiWstaw(this.strategia.strony.getStrona(this.bufor[i]));
				}
				//Zwiększamy ilość błędów braku strony.
				this.bledy++;
			}
			//Obsługujemy odwołanie do strony.
			this.strategia.obslugaOdwolania(this.strategia.strony.getStrona(this.bufor[i]));
			this.odwolania++;
		}
		//Wypisujemy komunikat końcowy.
		kom.wypiszKoniec(this.odwolania, this.bledy);
		
	}
}
